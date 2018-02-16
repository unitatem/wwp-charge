#!/bin/bash

Clean bin folder
mkdir -p ./bin
cd ./bin
rm -rf ./*
cd ..


# load java module
module load java


# pull git repo
git pull


# compile Java code
javac -d bin -sourcepath src -cp lib/PCJ-5.0.6.jar:lib/OSMonaut-1.1-SNAPSHOT.jar src/Main.java

