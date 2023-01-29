@ECHO OFF
set pkg=me\theentropyshard\jdarkroom
mkdir out
javac -encoding utf8 -classpath src\%pkg% -d out -sourcepath . src\%pkg%\*.java
cd out
jar cfe0 JDarkroom.jar me.theentropyshard.jdarkroom.Main %pkg%\*.class -C ..\resources .
move JDarkroom.jar ..\
cd ..\