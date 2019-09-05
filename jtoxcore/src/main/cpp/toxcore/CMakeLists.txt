cmake_minimum_required(VERSION 3.6)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++14")
INCLUDE_DIRECTORIES(${CMAKE_CURRENT_SOURCE_DIR}/../libsodium/include)
INCLUDE_DIRECTORIES(${CMAKE_CURRENT_SOURCE_DIR}/tox)
add_library( # Sets the name of the library.
        jtoxcore
        # Sets the library as a shared library.
        SHARED
        # Provides a relative path to your source file(s).
        tox/ccompat.h
        tox/crypto_core.c
        tox/crypto_core.h
        tox/crypto_core_mem.c
        tox/logger.c
        tox/logger.h
        tox/mono_time.c
        tox/mono_time.h
        tox/network.c
        tox/network.h
        tox/state.c
        tox/state.h
        tox/util.c
        tox/util.h
        tox/DHT.c
        tox/DHT.h
        tox/LAN_discovery.c
        tox/LAN_discovery.h
        tox/ping.c
        tox/ping.h
        tox/ping_array.c
        tox/ping_array.h
        tox/TCP_client.c
        tox/TCP_client.h
        tox/TCP_connection.c
        tox/TCP_connection.h
        tox/TCP_server.c
        tox/TCP_server.h
        tox/list.c
        tox/list.h
        tox/net_crypto.c
        tox/net_crypto.h
        tox/onion.c
        tox/onion.h
        tox/onion_announce.c
        tox/onion_announce.h
        tox/onion_client.c
        tox/onion_client.h
        tox/friend_connection.c
        tox/friend_connection.h
        tox/friend_requests.c
        tox/friend_requests.h
        tox/Messenger.c
        tox/Messenger.h
        tox/group.c
        tox/group.h
        tox/tox_api.c
        tox/tox.c
        tox/tox.h
        toxencryptsave/toxencryptsave.c
        toxencryptsave/toxencryptsave.h
        toxjni/im_tox_jtoxcore_JTox.cpp
        toxjni/ArrayFromJava.cpp
        toxjni/ArrayToJava.cpp)

find_library( # Sets the name of the path variable.
                log-lib
                # Specifies the name of the NDK library that
                # you want CMake to locate.
                log )

find_library( # Sets the name of the path variable.
        sodium

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        ${CMAKE_CURRENT_SOURCE_DIR}/../../../../build/intermediates/cmake/debug/obj/${ANDROID_ABI}/ )

target_link_libraries( # Specifies the target library.
                jtoxcore
              # Links the target library to the log library
              # included in the NDK.
        sodium )
