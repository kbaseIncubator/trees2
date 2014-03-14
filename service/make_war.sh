#!/bin/bash

if (( $# != 2 ))
then
    echo "Usage: make_war <jarsdir> <deploycfg>"
    exit
fi

ant -Djarsdir=$1 -Ddeploycfg=$2