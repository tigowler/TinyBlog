#!/bin/bash

REPOSITORY=/home/ec2-user/app/deploy

cd $REPOSITORY

JAR_NAME=$(ls $REPOSITORY/ | grep '.jar' | tail -n 1)
JAR_PATH=$REPOSITORY/$JAR_NAME

java -jar $JAR_PATH
