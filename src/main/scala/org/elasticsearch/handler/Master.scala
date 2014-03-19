package org.elasticsearch.handler

/**
 * Created with IntelliJ IDEA.
 * User: marc
 * Date: 19/03/14
 * Time: 14:56
 * To change this template use File | Settings | File Templates.
 */

import akka.actor._
import org.elasticsearch.river.tablewatch.TableWatchRiver.{Start, Stop}
import org.elasticsearch.common.logging.ESLoggerFactory
import com.github.shyiko.mysql.binlog._
import com.github.shyiko.mysql.binlog.event._

class Master(system: ActorSystem) extends Actor{

  val client = new BinaryLogClient("127.0.0.1", 3306, "testread", "jsdhkasd78s78sa78")
  val logger = ESLoggerFactory.getLogger(getClass.getName)

  def receive = {
    case Start => registerListener
    case Stop => client.disconnect
  }

  def registerListener {
    // track life cycle
    client.registerLifecycleListener(new BinaryLogClient.LifecycleListener() {
      def onConnect(client: BinaryLogClient) {
        logger.info("connected")
      }
      def onCommunicationFailure(client: BinaryLogClient, ex: Exception) {
        logger.info("communication failure {}", ex)
      }
      def onEventDeserializationFailure(client: BinaryLogClient, ex: Exception) {
        logger.info("event deserialization failure {}", ex)
      }
      def onDisconnect(client: BinaryLogClient) {
          println("disconnected")
      }
    })
    // track events
    client.registerEventListener(new BinaryLogClient.EventListener() {
      def onEvent(event: Event) {
        logger.info("=====>RECEIVED EVENT {}", event)
      }
    })

    client.connect
  }
}
