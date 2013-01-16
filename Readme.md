Testing the Garbage Collector
============================

mark/sweep/compact 
==================
java -jar KillTheGarbageCollector.jar -Xms4g -Xmx4g -XX:+UseSerialGC

CMS
===
java -jar KillTheGarbageCollector.jar -Xms4g -Xmx4g -XX:+UseConcMarkSweepGC

G1
==
java -jar KillTheGarbageCollector.jar -Xms4g -Xmx4g -XX:MaxGCPauseMillis=250
-XX:+UseG1GC 
