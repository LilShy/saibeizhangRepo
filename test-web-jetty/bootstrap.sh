#!/bin/bash
CUR_PATH=`pwd`
LIB_PATH=${CUR_PATH}"/lib/*"
CLASSPATH=${CLASSPATH}":"${LIB_PATH}
JAVA_OPTS="-server"
java ${JAVA_OPTS} -classpath ${CLASSPATH} com.acar.saibei.jetty.Bootstrap $@ &
