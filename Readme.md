Testing the Garbage Collector
============================

KillTheGarbageCollector.java is a Java Class taken from http://nerds-central.blogspot.ch/2011/11/comparing-java-7-garbage-collectors.html

mark/sweep/compact 
==================
    java -Xms3g -Xmx3g -XX:+UseSerialGC  -verbose:GC -jar StressTestGui.jar

CMS
===
    java -Xms3g -Xmx3g -XX:+UseConcMarkSweepGC -verbose:GC -jar StressTestGui.jar

G1
==
    java -Xms3g -Xmx3g -XX:+UseG1GC -XX:MaxGCPauseMillis=250 -XX:+PrintGCDetails -jar StressTestGui.jar


