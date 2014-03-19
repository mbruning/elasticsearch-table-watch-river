package org.elasticsearch.river.tablewatch.TableWatchRiver

import org.elasticsearch.common.inject.Inject
import akka.actor.Props
import akka.actor.ActorSystem
import org.elasticsearch.handler.Master
import org.elasticsearch.river.{AbstractRiverComponent, River, RiverName, RiverSettings}
import org.elasticsearch.client.{Requests, Client}

sealed trait TableWatchMessage
case object Start extends TableWatchMessage
case object Stop extends TableWatchMessage

// main river class
class TableWatchRiver @Inject()(name: RiverName, settings: RiverSettings, client: Client)
  extends AbstractRiverComponent(name, settings) with River {

  val system = ActorSystem("TableWatch")
  val master = system.actorOf(Props(new Master(system)), name="MasterActor")

  override def close() {
    master ! Stop
    logger.info("Closing river")
  }

  override def start() {
    master ! Start
    logger.info("Started river")
  }
}