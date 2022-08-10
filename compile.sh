#!/bin/sh
mkdir -p out
javac -classpath {JAVA_8_HOME}/jre/lib/rt.jar:./out src/me/coconan/chibicc/Application.java -d out
