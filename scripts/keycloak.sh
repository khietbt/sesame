#!/usr/bin/env bash

TOP_DIR=$(dirname "$(cd -- "$(dirname "${0}")" >/dev/null 2>&1 && pwd -P)")

source "${TOP_DIR}/scripts/libs.sh"

VENDOR_DIR=${TOP_DIR}/vendors
KEYCLOAK_DIR=${VENDOR_DIR}/keycloak

"${KEYCLOAK_DIR}"/scripts/start.sh