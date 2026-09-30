#!/bin/bash
###
 # @Descripttion:
 # @version:
 # @Author: wesley
 # @Date: 2024-06-13 15:52:12
 # @LastEditors: wesley
 # @LastEditTime: 2024-06-18 11:38:45
###

TOOLCHAIN_PREFIX=aarch64-linux-gnu
PLATFORM_ABI=aarch64

mkdir -p build-aarch64 && cd build-aarch64
cmake -G "Unix Makefiles" .. \
      -DPLATFORM_ABI="${PLATFORM_ABI}" \
      -DCMAKE_SYSTEM_NAME=Linux \
      -DCMAKE_SYSTEM_VERSION=1 \
      -DCMAKE_SYSTEM_PROCESSOR=aarch64 \
      -DCMAKE_LINKER=${TOOLCHAIN_PREFIX}-ld \
      -DCMAKE_C_COMPILER=${TOOLCHAIN_PREFIX}-gcc \
      -DCMAKE_CXX_COMPILER=${TOOLCHAIN_PREFIX}-g++ \
      -DBUILD_TEST=ON
make -j4

cd ..

./bin/cpp100
