#!/bin/sh

# Command-line interpretation
usage() {
  echo "Usage:"
  echo "    update.sh [CF repo dir] [BULKIO repo dir] [FRONTEND repo dir]"
  exit 1
}

if [ $# -ne 3 ]; then
  usage
fi

cfdir=$1
shift
if [ ! -d $cfdir ]; then
  echo "Invalid CF directory"
  exit 1
fi

bulkiodir=$1
shift
if [ ! -d $bulkiodir ]; then
  echo "Invalid BULKIO directory"
  exit 1
fi

frontenddir=$1
shift
if [ ! -d $frontenddir ]; then
  echo "Invalid FRONTEND directory"
  exit 1
fi

# Verify relative location of script
rootdir=`dirname $0`/..
if [ ! -d ${rootdir}/plugins ] || [ ! -d ${rootdir}/features ]; then
  echo "Script has been relocated"
  exit 1
fi

# Update libraries
rm -rf ${rootdir}/plugins/bulkio/src/*
rm -rf ${rootdir}/plugins/idl.bulkio.jni/src/*
rm -rf ${rootdir}/plugins/idl.cf.jni/src/*
rm -rf ${rootdir}/plugins/omnijni/src/*
rm -rf ${rootdir}/plugins/org.ossie/src/*

cp -r ${bulkiodir}/libsrc/java/src/* ${rootdir}/plugins/bulkio/src

cp -r ${bulkiodir}/src/java/* ${rootdir}/plugins/idl.bulkio.jni/src
find ${rootdir}/plugins/idl.bulkio.jni/src -type f ! -path "*/jni/*" -delete
find ${rootdir}/plugins/idl.bulkio.jni/src -mindepth 1 -type d -empty -delete

cp -r ${cfdir}/src/base/framework/java/cf/src/* ${rootdir}/plugins/idl.cf.jni/src
find ${rootdir}/plugins/idl.cf.jni/src -type f ! -path "*/jni/*" -delete
find ${rootdir}/plugins/idl.cf.jni/src -mindepth 1 -type d -empty -delete

cp -r ${cfdir}/src/omnijni/src/java/* ${rootdir}/plugins/omnijni/src
find ${rootdir}/plugins/omnijni/src -type f ! -name "*.java" -delete

cp -r ${cfdir}/src/base/framework/java/ossie/src/* ${rootdir}/plugins/org.ossie/src
rm ${rootdir}/plugins/org.ossie/src/org/ossie/properties/.gitignore

