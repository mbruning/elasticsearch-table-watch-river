package org.elasticsearch.river.tablewatch.TableWatchRiver

/**
 * Created with IntelliJ IDEA.
 * User: marc
 * Date: 02/12/13
 * Time: 16:41
 * To change this template use File | Settings | File Templates.
 */

import org.elasticsearch.common.inject.AbstractModule
import org.elasticsearch.river.River

class TableWatchRiverModule extends AbstractModule {
  override def configure = bind(classOf[River]).to(classOf[TableWatchRiver]).asEagerSingleton()
}