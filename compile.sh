#!/bin/sh
mkdir -p out
javac -classpath {JAVA_8_HOME}/jre/lib/rt.jar:./out src/me/coconan/chibicc/TokenKind.java -d out
javac -classpath {JAVA_8_HOME}/jre/lib/rt.jar:./out src/me/coconan/chibicc/Token.java -d out
javac -classpath {JAVA_8_HOME}/jre/lib/rt.jar:./out src/me/coconan/chibicc/Application.java -d out
