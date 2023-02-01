#!/usr/bin/env bash

rm -rf out
mkdir out
javac -encoding utf8 -classpath src/me/theentropyshard/jdarkroom -d out -sourcepath . src/me/theentropyshard/jdarkroom/*.java
cd out
jar cfe0 JDarkroom.jar me.theentropyshard.jdarkroom.Main me/theentropyshard/jdarkroom/*.class -C ../resources .
mv ./JDarkroom.jar ../
cd ../