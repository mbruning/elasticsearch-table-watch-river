package org.elasticsearch.plugin.river.tablewatch

/**
 * Created with IntelliJ IDEA.
 * User: marc
 * Date: 02/12/13
 * Time: 16:45
 * To change this template use File | Settings | File Templates.
 */

import org.elasticsearch.river.tablewatch.TableWatchRiver.TableWatchRiverModule
import org.elasticsearch.plugins.AbstractPlugin
import org.elasticsearch.common.inject.{Inject, Module}
import org.elasticsearch.river.RiversModule


class TableWatchRiverPlugin @Inject() extends AbstractPlugin {

  override def name = "tablewatch"

  override def description = "sync elasticsearch index with mysql table via binlogstream"

  override def processModule(module:  Module) = {
    if (module.isInstanceOf[RiversModule]) {
      module.asInstanceOf[RiversModule].registerRiver("tablewatch", classOf[TableWatchRiverModule])
    }
  }
}