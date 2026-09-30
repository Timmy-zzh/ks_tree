cmake_minimum_required(VERSION 3.5)
project(cpp100)

# Set the C++ standard to C++11
set(CMAKE_CXX_STANDARD 11) 
# Ensure that the C++ standard is required
set(CMAKE_CXX_STANDARD_REQUIRED ON)

# 将所有要编译的源文件添加到变量中
file(GLOB_RECURSE ALL_SRC_FILES
    "src/*.cpp" "src/*.h"
)

add_executable(${PROJECT_NAME}
    main.cpp
    ${ALL_SRC_FILES}
)

# 设置产物输出路径
set(EXECUTABLE_OUTPUT_PATH ${PROJECT_SOURCE_DIR}/bin)