#!/usr/bin/env bash

TOP_DIR=$(dirname "$(cd -- "$(dirname "${0}")" >/dev/null 2>&1 && pwd -P)")

export TOP_DIR

cd "${TOP_DIR}"/conf && docker-compose up -d