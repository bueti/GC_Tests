Testing the Garbage Collector
============================

KillTheGarbageCollector.java is a Java Class taken from http://nerds-central.blogspot.ch/2011/11/comparing-java-7-garbage-collectors.html

mark/sweep/compact 
==================
```java -jar KillTheGarbageCollector.jar -Xms4g -Xmx4g -XX:+UseSerialGC
```

CMS
===
```java -jar KillTheGarbageCollector.jar -Xms4g -Xmx4g -XX:+UseConcMarkSweepGC
```

G1
==
```java -jar KillTheGarbageCollector.jar -Xms4g -Xmx4g -XX:MaxGCPauseMillis=250
-XX:+UseG1GC
```

Other options
=============
```-verbose:GC
-XX:+PrintGCDetails
```
