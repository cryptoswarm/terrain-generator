#!/usr/bin/env bash

export ARGS=$(echo "$@")

mvn -q -X exec:java -Dexec.args="$ARGS"
