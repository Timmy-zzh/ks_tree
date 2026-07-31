cmake_minimum_required(VERSION 3.15)
project(test_zeromap)

# 设置C++标准
set(CMAKE_CXX_STANDARD 11)

# 手动指定 libzmq 的安装路径
set(ZeroMQ_ROOT "/home/zhuzhonghua/zzh/github/ks_tree/zmq/cmake_libzmq")

# 添加头文件搜索路径
include_directories(${ZeroMQ_ROOT}/include
                    ${PROJECT_SOURCE_DIR}/include
)

# 添加库文件搜索路径
link_directories(${ZeroMQ_ROOT}/lib)

# # 测试程序
# add_executable(test_zeromap main.cpp)
# # 链接 libzmq 库
# target_link_libraries(test_zeromap zmq)

# 服务端
add_executable(service service.cpp)
target_link_libraries(service zmq)

# 客户端
add_executable(client client.cpp)
target_link_libraries(client zmq)