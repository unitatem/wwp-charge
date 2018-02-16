#!/bin/bash

Clean bin folder
mkdir -p ./bin
cd ./bin
rm -rf ./*
cd ..


# load java module
module load java


# pull git repo
cd wwp-charge/
git pull
cd ..


# compile Java code
javac -d bin -sourcepath wwp-charge/src -cp wwp-charge/lib/PCJ-5.0.6.jar:wwp-charge/lib/OSMonaut-1.1-all.jar:wwp-charge/lib/commons-math3-3.6.1.jar wwp-charge/src/main/java/Main.java

