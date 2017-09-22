@echo off
java -Doutput.bin="./gen" -Dlib.rt=%1 -jar regnac-debug.jar %2