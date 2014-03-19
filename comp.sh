curl -XDELETE 127.0.0.1:9200/_river/tablewatch-river
sudo service elasticsearch stop
sbt compile
sbt assembly
sudo /usr/share/elasticsearch/bin/plugin -remove tablewatch-river
sudo /usr/share/elasticsearch/bin/plugin install tablewatch-river -url file:///home/marc/projects/river/table-watch/target/scala-2.10/elasticsearch-tablewatch-river-assembly-1.0.jar 
sudo service elasticsearch start
