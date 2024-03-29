/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
#include <stdlib.h>
#include <arpa/inet.h>
#include <sodium.h>
#include <string>
/* Header for class im_tox_jtoxcore_JTox */
#include "tox.h"
#include "ArrayFromJava.h"
#ifndef _Included_im_tox_jtoxcore_JTox
#define _Included_im_tox_jtoxcore_JTox
#ifdef __cplusplus
extern "C" {
#endif

#define ATTACH_THREAD(ptr,env) (ptr->jvm)->AttachCurrentThread(&env, 0)
#define UNUSED(x) (void)(x)

#include <android/log.h>
#define  LOG_TAG "Jtox"
#define  LOGI(...)   __android_log_print(android_LogPriority::ANDROID_LOG_INFO,LOG_TAG,__VA_ARGS__)


typedef struct {
    Tox *tox;
    JavaVM *jvm;
    jobject handler;
    jobject jtox;
    char* savedatapath;
} tox_jni_globals_t;


/// callback
static void callback_connectionstatus(Tox *tox, TOX_CONNECTION connection_status, void *user_data)
{
    tox_jni_globals_t *ptr = (tox_jni_globals_t *) user_data;
    JNIEnv *env;
    ATTACH_THREAD(ptr, env);
    jclass  handlerclass = env->GetObjectClass( ptr->handler);
    jmethodID  handlermeth = env->GetMethodID(handlerclass, "onSelfConnectionStatus", "(I)V");
    env->CallVoidMethod(ptr->handler, handlermeth, (int)connection_status);
    UNUSED(tox);
}
void callback_friendname(Tox *tox, uint32_t friend_number, const uint8_t *name, size_t length, void *user_data){
    tox_jni_globals_t *ptr = (tox_jni_globals_t *) user_data;
    JNIEnv *env;
    ATTACH_THREAD(ptr, env);
    jclass  handlerclass = env->GetObjectClass( ptr->handler);
    jmethodID  handlermeth = env->GetMethodID(handlerclass, "onFriendNameChange", "(ILjava/lang/String;)V");
    std::string_view str((const char*)name,length);
    jstring jstring = env->NewStringUTF(str.data());
    env->CallVoidMethod(ptr->handler, handlermeth, friend_number,jstring);
    UNUSED(tox);
}

void callback_friend_status_message(Tox *tox, uint32_t friend_number, const uint8_t *message, size_t length,
                                  void *user_data){
    tox_jni_globals_t *ptr = (tox_jni_globals_t *) user_data;
    JNIEnv *env;
    ATTACH_THREAD(ptr, env);
    jclass  handlerclass = env->GetObjectClass( ptr->handler);
    jmethodID  handlermeth = env->GetMethodID(handlerclass, "onFriendStatusMessageChange", "(ILjava/lang/String;)V");
    std::string_view str((const char*)message,length);
    jstring jstring = env->NewStringUTF(str.data());
    env->CallVoidMethod(ptr->handler, handlermeth, friend_number,jstring);
    UNUSED(tox);
}

void callback_friend_status(Tox *tox, uint32_t friend_number, TOX_USER_STATUS status, void *user_data){
    tox_jni_globals_t *ptr = (tox_jni_globals_t *) user_data;
    JNIEnv *env;
    ATTACH_THREAD(ptr, env);
    jclass  handlerclass = env->GetObjectClass( ptr->handler);
    jmethodID  handlermeth = env->GetMethodID(handlerclass, "onFriendStatusChange", "(II)V");
    env->CallVoidMethod(ptr->handler, handlermeth, friend_number,int(status));
    UNUSED(tox);
}

void callback_friend_connection_status(Tox *tox, uint32_t friend_number, TOX_CONNECTION connection_status,
                                     void *user_data){
    tox_jni_globals_t *ptr = (tox_jni_globals_t *) user_data;
    JNIEnv *env;
    ATTACH_THREAD(ptr, env);
    jclass  handlerclass = env->GetObjectClass( ptr->handler);
    jmethodID  handlermeth = env->GetMethodID(handlerclass, "onFriendConnectionStatusChange", "(II)V");
    env->CallVoidMethod(ptr->handler, handlermeth, friend_number,int(connection_status));
    UNUSED(tox);
}

void callback_friend_typing(Tox *tox, uint32_t friend_number, bool is_typing, void *user_data){
    tox_jni_globals_t *ptr = (tox_jni_globals_t *) user_data;
    JNIEnv *env;
    ATTACH_THREAD(ptr, env);
    jclass  handlerclass = env->GetObjectClass( ptr->handler);
    jmethodID  handlermeth = env->GetMethodID(handlerclass, "onFriendTypingChange", "(IZ)V");
    env->CallVoidMethod(ptr->handler, handlermeth, friend_number,is_typing);
    UNUSED(tox);
}

void callback_friend_read_receipt(Tox *tox, uint32_t friend_number, uint32_t message_id, void *user_data){
    tox_jni_globals_t *ptr = (tox_jni_globals_t *) user_data;
    JNIEnv *env;
    ATTACH_THREAD(ptr, env);
    jclass  handlerclass = env->GetObjectClass( ptr->handler);
    jmethodID  handlermeth = env->GetMethodID(handlerclass, "onFriendReadReceipt", "(II)V");
    env->CallVoidMethod(ptr->handler, handlermeth, friend_number,message_id);
    UNUSED(tox);
}

void callback_friend_request(Tox *tox, const uint8_t *public_key, const uint8_t *message, size_t length,
                           void *user_data){
    tox_jni_globals_t *ptr = (tox_jni_globals_t *) user_data;
    JNIEnv *env;
    ATTACH_THREAD(ptr, env);
    jclass  handlerclass = env->GetObjectClass( ptr->handler);
    jmethodID  handlermeth = env->GetMethodID(handlerclass, "onFriendReadRequest", "([BLjava/lang/String;)V");

    jbyte *by = (jbyte*)public_key;
    jbyteArray jarray = env->NewByteArray(TOX_PUBLIC_KEY_SIZE);
    env->SetByteArrayRegion(jarray, 0, TOX_PUBLIC_KEY_SIZE, by);
    std::string_view str((const char*)message,length);
    jstring jstring = env->NewStringUTF(str.data());
    env->CallVoidMethod(ptr->handler, handlermeth, jarray,jstring);
    UNUSED(tox);
}

void callback_friend_message(Tox *tox, uint32_t friend_number, TOX_MESSAGE_TYPE type, const uint8_t *message,
                           size_t length, void *user_data){
    tox_jni_globals_t *ptr = (tox_jni_globals_t *) user_data;
    JNIEnv *env;
    ATTACH_THREAD(ptr, env);
    jclass  handlerclass = env->GetObjectClass( ptr->handler);
    jmethodID  handlermeth = env->GetMethodID(handlerclass, "onFriendMessage", "(IILjava/lang/String;)V");
    std::string_view str((const char*)message,length);
    jstring jstring = env->NewStringUTF(str.data());
    env->CallVoidMethod(ptr->handler, handlermeth, friend_number,(int)type,jstring);
    UNUSED(tox);
}

void callback_friend_lossy_packet(Tox *tox, uint32_t friend_number, const uint8_t *data, size_t length,
                                void *user_data){
    tox_jni_globals_t *ptr = (tox_jni_globals_t *) user_data;
    JNIEnv *env;
    ATTACH_THREAD(ptr, env);
    jclass  handlerclass = env->GetObjectClass( ptr->handler);
    jmethodID  handlermeth = env->GetMethodID(handlerclass, "onFriendLossyPacket", "(ILjava/lang/String;)V");
    std::string_view str((const char*)data,length);
    jstring jstring = env->NewStringUTF(str.data());
    env->CallVoidMethod(ptr->handler, handlermeth, friend_number,jstring);
    UNUSED(tox);
}

void callback_friend_lossless_packet(Tox *tox, uint32_t friend_number, const uint8_t *data, size_t length,
                                   void *user_data){
    tox_jni_globals_t *ptr = (tox_jni_globals_t *) user_data;
    JNIEnv *env;
    ATTACH_THREAD(ptr, env);
    jclass  handlerclass = env->GetObjectClass( ptr->handler);
    jmethodID  handlermeth = env->GetMethodID(handlerclass, "onFriendLossLessPacket", "(ILjava/lang/String;)V");
    std::string_view str((const char*)data,length);
    jstring jstring = env->NewStringUTF(str.data());
    env->CallVoidMethod(ptr->handler, handlermeth, friend_number,jstring);
    UNUSED(tox);
}


JNIEXPORT jlong JNICALL Java_im_tox_jtoxcore_JTox_toxNew(JNIEnv *env, jobject jobj,jstring savedatafile)
{
    tox_jni_globals_t *globals = (tox_jni_globals_t *)malloc(sizeof(tox_jni_globals_t));
    JavaVM *jvm;
    jclass clazz = env->GetObjectClass(jobj);
    jfieldID id = env->GetFieldID( clazz, "handler", "Lim/tox/jtoxcore/callbacks/CallbackHandler;");
    jobject handler = env->GetObjectField( jobj, id);
    jobject handlerRef = env->NewGlobalRef( handler);
    jobject jtoxRef = env->NewGlobalRef(jobj);
    Tox *tox=NULL;
    struct Tox_Options options;

    tox_options_default(&options);
    if(savedatafile!=NULL) {
        const char *path = env->GetStringUTFChars(savedatafile, NULL);
        if (path != NULL) {
            FILE *f = fopen(path, "rb");
            if (f) {
                fseek(f, 0, SEEK_END);
                long fsize = ftell(f);
                fseek(f, 0, SEEK_SET);

                char *savedata = (char*)malloc(fsize);

                fread(savedata, fsize, 1, f);
                fclose(f);

                options.savedata_type = TOX_SAVEDATA_TYPE_TOX_SAVE;
                options.savedata_data = (const unsigned char *) savedata;
                options.savedata_length = fsize;

                tox = tox_new(&options, NULL);

                free(savedata);

            }
            int len =strlen(path);
            globals->savedatapath= (char*)malloc(len+1);
            strcpy(globals->savedatapath,path);
            env->ReleaseStringUTFChars(savedatafile, path);
        }
    }
    if(tox==NULL){
        tox = tox_new(&options, NULL);
    }


    env->GetJavaVM(&jvm);
    globals->tox = tox;
    globals->jvm = jvm;
    globals->handler = handlerRef;
    globals->jtox = jtoxRef;
    jlong  ll = ((jlong) ((intptr_t) globals));
    return ll;
}

JNIEXPORT void JNICALL Java_im_tox_jtoxcore_JTox_toxUpdateSaveData(JNIEnv *env, jobject jobj,jlong instanceNumber)
{
    Tox *tox = ((tox_jni_globals_t *) ((intptr_t) instanceNumber))->tox;
    size_t size = tox_get_savedata_size(tox);
    char *savedata = (char*)malloc(size);
    tox_get_savedata(tox, (uint8_t*)savedata);
    char * savedata_filename =((tox_jni_globals_t *) ((intptr_t) instanceNumber))->savedatapath;
    char *tmpname = (char *) malloc(strlen(savedata_filename) +5);
    sprintf(tmpname, "%s.tmp", savedata_filename);

    FILE *f = fopen(tmpname, "wb+");
    fwrite(savedata, size, 1, f);
    fclose(f);
    rename(tmpname, savedata_filename);
    free(savedata);
    free(tmpname);
}


/*
 * Class:     im_tox_jtoxcore_JTox
 * Method:    toxKill
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_im_tox_jtoxcore_JTox_toxKill
        (JNIEnv *env, jobject jobj, jlong instanceNumber) {
     tox_kill(((tox_jni_globals_t *) ((intptr_t) instanceNumber))->tox);
}

/*
 * Class:     im_tox_jtoxcore_JTox
 * Method:    toxFinalize
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_im_tox_jtoxcore_JTox_toxFinalize
        (JNIEnv *env, jobject jobj, jlong instanceNumber) {
    tox_jni_globals_t * globals= (tox_jni_globals_t *) ((intptr_t) instanceNumber);
    if(globals->savedatapath!= nullptr){
        free(globals->savedatapath);
    }
    env->DeleteGlobalRef(globals->handler);
}
/*
 * Class:     im_tox_jtoxcore_JTox
 * Method:    toxGetSavedata
 * Signature: (I)[B
 */
JNIEXPORT jbyteArray JNICALL Java_im_tox_jtoxcore_JTox_toxGetSavedata
        (JNIEnv *env, jobject jobj, jlong instanceNumber) {
    UNUSED(jobj);
    Tox *tox = ((tox_jni_globals_t *) ((intptr_t) instanceNumber))->tox;
    uint32_t size = tox_get_savedata_size(tox);
    uint8_t *data = (uint8_t*)malloc(size);
    jbyteArray bytes = env->NewByteArray(size);
    tox_get_savedata(tox, data);
    env->SetByteArrayRegion(bytes, 0, size, (jbyte *) data);
    free(data);
    return bytes;
}

/*
 * Class:     im_tox_jtoxcore_JTox
 * Method:    toxBootstrap
 * Signature: (ILjava/lang/String;I[B)V
 */
JNIEXPORT jboolean JNICALL Java_im_tox_jtoxcore_JTox_toxBootstrap
        (JNIEnv *env, jobject jobj, jlong instanceNumber, jstring address, jint port,
         jbyteArray publicKey) {
    const char *_address = (const char *) env->GetStringUTFChars(address, 0);

   // auto _publicKey = fromJavaArray(env, publicKey).data();

    int len = env->GetArrayLength(publicKey);
    jbyte *jbarray = (jbyte *)malloc(len * sizeof(jbyte));

    env->GetByteArrayRegion(publicKey,0,len,jbarray);

    jboolean result = tox_bootstrap(((tox_jni_globals_t *) ((intptr_t) instanceNumber))->tox,
                                    _address, port,
                                    (uint8_t *) jbarray, NULL);

    env->ReleaseStringUTFChars(address, _address);
    free(jbarray);
    return result;
}

/*
 * Class:     im_tox_jtoxcore_JTox
 * Method:    toxAddTcpRelay
 * Signature: (ILjava/lang/String;I[B)V
 */
JNIEXPORT void JNICALL Java_im_tox_jtoxcore_JTox_toxAddTcpRelay
        (JNIEnv *env, jobject jobj, jlong instanceNumber, jstring address, jint port, jbyteArray publicKey) {
    Tox *tox = ((tox_jni_globals_t *) ((intptr_t) instanceNumber))->tox;
    const char *_address = (const char *) env->GetStringUTFChars(address, 0);

    auto _publicKey = fromJavaArray(env, publicKey).data();
    tox_add_tcp_relay(tox,_address,port,_publicKey,NULL);
    env->ReleaseStringUTFChars(address, _address);
}

/*
 * Class:     im_tox_jtoxcore_JTox
 * Method:    toxSelfGetUdpPort
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_im_tox_jtoxcore_JTox_toxSelfGetUdpPort
        (JNIEnv *env, jobject jobj, jlong instanceNumber) {
    Tox *tox = ((tox_jni_globals_t *) ((intptr_t) instanceNumber))->tox;
    return tox_self_get_udp_port(tox,NULL);
}

/*
 * Class:     im_tox_jtoxcore_JTox
 * Method:    toxSelfGetTcpPort
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_im_tox_jtoxcore_JTox_toxSelfGetTcpPort
        (JNIEnv *env, jobject jobj, jlong instanceNumber) {
    Tox *tox = ((tox_jni_globals_t *) ((intptr_t) instanceNumber))->tox;
    return tox_self_get_tcp_port(tox,NULL);
}

/*
 * Class:     im_tox_jtoxcore_JTox
 * Method:    toxSelfGetDhtId
 * Signature: (I)[B
 */
JNIEXPORT jbyteArray JNICALL Java_im_tox_jtoxcore_JTox_toxSelfGetDhtId
        (JNIEnv *env, jobject jobj, jlong instanceNumber) {
    Tox *tox = ((tox_jni_globals_t *) ((intptr_t) instanceNumber))->tox;
    uint8_t  data[TOX_PUBLIC_KEY_SIZE];
    tox_self_get_dht_id(tox,data);
    jbyteArray bytes = env->NewByteArray(TOX_PUBLIC_KEY_SIZE);
    env->SetByteArrayRegion(bytes, 0, TOX_PUBLIC_KEY_SIZE, (jbyte *) data);
    return  bytes;
}

/*
 * Class:     im_tox_jtoxcore_JTox
 * Method:    toxIterationInterval
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_im_tox_jtoxcore_JTox_toxIterationInterval
        (JNIEnv *env, jobject jobj, jlong instanceNumber) {
    Tox *tox = ((tox_jni_globals_t *) ((intptr_t) instanceNumber))->tox;
    jint t= tox_iteration_interval(tox);
    return t;
}

/*
 * Class:     im_tox_jtoxcore_JTox
 * Method:    toxIterate
 * Signature: (I)[B
 */
JNIEXPORT void JNICALL Java_im_tox_jtoxcore_JTox_toxIterate
        (JNIEnv *env, jobject jobj, jlong instanceNumber) {
    Tox *tox = ((tox_jni_globals_t *) ((intptr_t) instanceNumber))->tox;
    tox_iterate(tox,  (tox_jni_globals_t *)  instanceNumber);
   // char buffer[100]={0};
    //sprintf(buffer,"toxIterate%d",instanceNumber);
   // LOGI("toxIterate%d",instanceNumber);
}

/*
 * Class:     im_tox_jtoxcore_JTox
 * Method:    toxSelfGetPublicKey
 * Signature: (I)[B
 */
JNIEXPORT jbyteArray JNICALL Java_im_tox_jtoxcore_JTox_toxSelfGetPublicKey
        (JNIEnv *env, jobject jobj, jlong instanceNumber) {
    Tox *tox = ((tox_jni_globals_t *) ((intptr_t) instanceNumber))->tox;
    uint8_t  data[TOX_PUBLIC_KEY_SIZE];
    tox_self_get_public_key(tox,data);
    jbyteArray bytes = env->NewByteArray(TOX_PUBLIC_KEY_SIZE);
    env->SetByteArrayRegion(bytes, 0, TOX_PUBLIC_KEY_SIZE, (jbyte *) data);
    return  bytes;
}

/*
 * Class:     im_tox_jtoxcore_JTox
 * Method:    toxSelfGetSecretKey
 * Signature: (I)[B
 */
JNIEXPORT jbyteArray JNICALL Java_im_tox_jtoxcore_JTox_toxSelfGetSecretKey
        (JNIEnv *env, jobject jobj, jlong instanceNumber) {
    Tox *tox = ((tox_jni_globals_t *) ((intptr_t) instanceNumber))->tox;
    uint8_t  data[TOX_SECRET_KEY_SIZE];
    tox_self_get_secret_key(tox,data);
    jbyteArray bytes = env->NewByteArray(TOX_SECRET_KEY_SIZE);
    env->SetByteArrayRegion(bytes, 0, TOX_SECRET_KEY_SIZE, (jbyte *) data);
    return  bytes;
}

/*
 * Class:     im_tox_jtoxcore_JTox
 * Method:    toxSelfSetNospam
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_im_tox_jtoxcore_JTox_toxSelfSetNospam
        (JNIEnv *env, jobject jobj, jlong instanceNumber, jint nospam) {
    Tox *tox = ((tox_jni_globals_t *) ((intptr_t) instanceNumber))->tox;
    tox_self_set_nospam(tox,nospam);
}

/*
 * Class:     im_tox_jtoxcore_JTox
 * Method:    toxSelfGetNospam
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_im_tox_jtoxcore_JTox_toxSelfGetNospam
        (JNIEnv *env, jobject jobj, jlong instanceNumber) {
    Tox *tox = ((tox_jni_globals_t *) ((intptr_t) instanceNumber))->tox;
    return  tox_self_get_nospam(tox);
}

/*
 * Class:     im_tox_jtoxcore_JTox
 * Method:    toxSelfGetAddress
 * Signature: (I)[B
 */
JNIEXPORT jbyteArray JNICALL Java_im_tox_jtoxcore_JTox_toxSelfGetAddress
        (JNIEnv *env, jobject jobj, jlong instanceNumber) {
    Tox *tox = ((tox_jni_globals_t *) ((intptr_t) instanceNumber))->tox;
    uint8_t  data[TOX_ADDRESS_SIZE];
    tox_self_get_address(tox,data);
    jbyteArray bytes = env->NewByteArray(TOX_ADDRESS_SIZE);
    env->SetByteArrayRegion(bytes, 0, TOX_ADDRESS_SIZE, (jbyte *) data);
    return  bytes;
}

/*
 * Class:     im_tox_jtoxcore_JTox
 * Method:    toxSelfSetName
 * Signature: (I[B)V
 */
JNIEXPORT void JNICALL Java_im_tox_jtoxcore_JTox_toxSelfSetName
        (JNIEnv *env, jobject jobj, jlong instanceNumber, jbyteArray name) {
    Tox *tox = ((tox_jni_globals_t *) ((intptr_t) instanceNumber))->tox;
    auto name_array = fromJavaArray (env, name);
    tox_self_set_name(tox,name_array.data(),name_array.size(),NULL);
}

/*
 * Class:     im_tox_jtoxcore_JTox
 * Method:    toxSelfGetName
 * Signature: (I)[B
 */
JNIEXPORT jbyteArray JNICALL Java_im_tox_jtoxcore_JTox_toxSelfGetName
        (JNIEnv *env, jobject jobj, jlong instanceNumber) {
    Tox *tox = ((tox_jni_globals_t *) ((intptr_t) instanceNumber))->tox;
    size_t size=tox_self_get_name_size(tox);
    uint8_t *data = (uint8_t*)malloc(size);
    jbyteArray bytes = env->NewByteArray(size);
    tox_self_get_name(tox,data);
    env->SetByteArrayRegion(bytes, 0, size, (jbyte *) data);
    free(data);
    return bytes;
}

/*
 * Class:     im_tox_jtoxcore_JTox
 * Method:    toxSelfSetStatusMessage
 * Signature: (I[B)V
 */
JNIEXPORT void JNICALL Java_im_tox_jtoxcore_JTox_toxSelfSetStatusMessage
        (JNIEnv *env, jobject jobj, jlong instanceNumber, jbyteArray statusMessage) {
    Tox *tox = ((tox_jni_globals_t *) ((intptr_t) instanceNumber))->tox;
    auto statusMessage_array = fromJavaArray (env, statusMessage);
    tox_self_set_status_message(tox,statusMessage_array.data(),statusMessage_array.size(),NULL);
}

/*
 * Class:     im_tox_jtoxcore_JTox
 * Method:    toxSelfGetStatusMessage
 * Signature: (I)[B
 */
JNIEXPORT jbyteArray JNICALL Java_im_tox_jtoxcore_JTox_toxSelfGetStatusMessage
        (JNIEnv *env, jobject jobj, jlong instanceNumber) {
    Tox *tox = ((tox_jni_globals_t *) ((intptr_t) instanceNumber))->tox;
    size_t size=tox_self_get_status_message_size(tox);
    uint8_t *data = (uint8_t*)malloc(size);
    jbyteArray bytes = env->NewByteArray(size);
    tox_self_get_status_message(tox,data);
    env->SetByteArrayRegion(bytes, 0, size, (jbyte *) data);
    free(data);
    return  bytes;
}

/*
 * Class:     im_tox_jtoxcore_JTox
 * Method:    toxSelfSetStatus
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_im_tox_jtoxcore_JTox_toxSelfSetStatus
        (JNIEnv *env, jobject jobj, jlong instanceNumber, jint status) {
    Tox *tox = ((tox_jni_globals_t *) ((intptr_t) instanceNumber))->tox;
    tox_self_set_status(tox,(TOX_USER_STATUS)status);
}

/*
 * Class:     im_tox_jtoxcore_JTox
 * Method:    toxSelfGetStatus
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_im_tox_jtoxcore_JTox_toxSelfGetStatus
        (JNIEnv *env, jobject jobj, jlong instanceNumber) {
    Tox *tox = ((tox_jni_globals_t *) ((intptr_t) instanceNumber))->tox;
    return tox_self_get_status(tox);
}

/*
 * Class:     im_tox_jtoxcore_JTox
 * Method:    toxFriendAdd
 * Signature: (I[B[B)I
 */
JNIEXPORT jint JNICALL Java_im_tox_jtoxcore_JTox_toxFriendAdd
        (JNIEnv *env, jobject jobj, jlong instanceNumber, jbyteArray address, jbyteArray message) {
    Tox *tox = ((tox_jni_globals_t *) ((intptr_t) instanceNumber))->tox;
    //auto address_array = fromJavaArray (env, address);
    //auto message_array = fromJavaArray (env, message);

    int len = env->GetArrayLength(address);
    jbyte *address_array = (jbyte *)malloc(len * sizeof(jbyte));
    env->GetByteArrayRegion(address,0,len,address_array);

    int len2 = env->GetArrayLength(message);
    jbyte *message_array = (jbyte *)malloc(len2 * sizeof(jbyte));
    env->GetByteArrayRegion(message,0,len2,message_array);

    jint ret = tox_friend_add(tox,(const uint8_t *)address_array,(const uint8_t *)message_array,len2,NULL);
    free(address_array);
    free(message_array);
    return ret;
}

/*
 * Class:     im_tox_jtoxcore_JTox
 * Method:    toxFriendAddNorequest
 * Signature: (I[B)I
 */
JNIEXPORT jint JNICALL Java_im_tox_jtoxcore_JTox_toxFriendAddNorequest
        (JNIEnv *env, jobject jobj, jlong instanceNumber, jbyteArray publickey) {
    Tox *tox = ((tox_jni_globals_t *) ((intptr_t) instanceNumber))->tox;
    auto publickey_array = fromJavaArray (env, publickey);
    return tox_friend_add_norequest(tox,publickey_array.data(),NULL);
}

/*
 * Class:     im_tox_jtoxcore_JTox
 * Method:    toxFriendDelete
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_im_tox_jtoxcore_JTox_toxFriendDelete
        (JNIEnv *env, jobject jobj, jlong instanceNumber, jint friendnumber) {
    Tox *tox = ((tox_jni_globals_t *) ((intptr_t) instanceNumber))->tox;
    tox_friend_delete(tox,friendnumber,NULL);
}

/*
 * Class:     im_tox_jtoxcore_JTox
 * Method:    toxFriendByPublicKey
 * Signature: (I[B)I
 */
JNIEXPORT jint JNICALL Java_im_tox_jtoxcore_JTox_toxFriendByPublicKey
        (JNIEnv *env, jobject jobj, jlong instanceNumber, jbyteArray publickey) {
    Tox *tox = ((tox_jni_globals_t *) ((intptr_t) instanceNumber))->tox;
    auto publickey_array = fromJavaArray (env, publickey);
    int ret =tox_friend_by_public_key(tox,publickey_array.data(),NULL);
    if(ret==UINT32_MAX){
        return -1;
    }
    return ret;
}

/*
 * Class:     im_tox_jtoxcore_JTox
 * Method:    toxFriendGetPublicKey
 * Signature: (II)[B
 */
JNIEXPORT jbyteArray JNICALL Java_im_tox_jtoxcore_JTox_toxFriendGetPublicKey
        (JNIEnv *env, jobject jobj, jlong instanceNumber, jint friendnumber) {
    Tox *tox = ((tox_jni_globals_t *) ((intptr_t) instanceNumber))->tox;
    uint8_t  data[TOX_PUBLIC_KEY_SIZE];
    tox_friend_get_public_key(tox,friendnumber,data,NULL);
    jbyteArray bytes = env->NewByteArray(TOX_PUBLIC_KEY_SIZE);
    env->SetByteArrayRegion(bytes, 0, TOX_PUBLIC_KEY_SIZE, (jbyte *) data);
    return  bytes;
}

/*
 * Class:     im_tox_jtoxcore_JTox
 * Method:    toxFriendExists
 * Signature: (II)Z
 */
JNIEXPORT jboolean JNICALL Java_im_tox_jtoxcore_JTox_toxFriendExists
        (JNIEnv *env, jobject jobj, jlong instanceNumber, jint friendnumber) {
    Tox *tox = ((tox_jni_globals_t *) ((intptr_t) instanceNumber))->tox;
    return tox_friend_exists(tox,friendnumber);
}

/*
 * Class:     im_tox_jtoxcore_JTox
 * Method:    toxSelfGetFriendList
 * Signature: (I)[I
 */
JNIEXPORT jintArray JNICALL Java_im_tox_jtoxcore_JTox_toxSelfGetFriendList
        (JNIEnv *env, jobject jobj, jlong instanceNumber) {
    Tox *tox = ((tox_jni_globals_t *) ((intptr_t) instanceNumber))->tox;
    size_t  size=tox_self_get_friend_list_size(tox);
    uint32_t *data = (uint32_t*)malloc(size*sizeof(uint32_t));
    tox_self_get_friend_list(tox,data);
    jintArray bytes = env->NewIntArray(size);
    env->SetIntArrayRegion(bytes, 0, size, (jint *) data);
    free(data);
    return  bytes;
}

/*
 * Class:     im_tox_jtoxcore_JTox
 * Method:    toxSelfSetTyping
 * Signature: (IIZ)V
 */
JNIEXPORT void JNICALL Java_im_tox_jtoxcore_JTox_toxSelfSetTyping
        (JNIEnv *env, jobject jobj, jlong instanceNumber, jint friendnumber, jboolean typing) {
    Tox *tox = ((tox_jni_globals_t *) ((intptr_t) instanceNumber))->tox;
    tox_self_set_typing(tox,friendnumber,typing,NULL);
}

/*
 * Class:     im_tox_jtoxcore_JTox
 * Method:    toxFriendSendMessage
 * Signature: (IIII[B)I
 */
JNIEXPORT jint JNICALL Java_im_tox_jtoxcore_JTox_toxFriendSendMessage
        (JNIEnv *env, jobject jobj, jlong instanceNumber, jint friendnumber, jint type,jbyteArray message) {
    Tox *tox = ((tox_jni_globals_t *) ((intptr_t) instanceNumber))->tox;
    auto message_array = fromJavaArray (env, message);
    return  tox_friend_send_message(tox,friendnumber,(TOX_MESSAGE_TYPE)type,message_array.data(),message_array.size(),NULL);
}

/*
 * Class:     im_tox_jtoxcore_JTox
 * Method:    toxFileControl
 * Signature: (IIII)V
 */
JNIEXPORT void JNICALL Java_im_tox_jtoxcore_JTox_toxFileControl
        (JNIEnv *env, jobject jobj, jlong instanceNumber, jint, jint, jint) {
    Tox *tox = ((tox_jni_globals_t *) ((intptr_t) instanceNumber))->tox;
}

/*
 * Class:     im_tox_jtoxcore_JTox
 * Method:    toxFileSeek
 * Signature: (IIIJ)V
 */
JNIEXPORT void JNICALL Java_im_tox_jtoxcore_JTox_toxFileSeek
        (JNIEnv *env, jobject jobj, jlong instanceNumber, jint, jint, jlong) {}

/*
 * Class:     im_tox_jtoxcore_JTox
 * Method:    toxFileSend
 * Signature: (IIIJ[B[B)I
 */
JNIEXPORT jint JNICALL Java_im_tox_jtoxcore_JTox_toxFileSend
        (JNIEnv *env, jobject jobj, jlong instanceNumber, jint, jint, jlong, jbyteArray,
         jbyteArray) {}

/*
 * Class:     im_tox_jtoxcore_JTox
 * Method:    toxFileSendChunk
 * Signature: (IIIJ[B)V
 */
JNIEXPORT void JNICALL Java_im_tox_jtoxcore_JTox_toxFileSendChunk
        (JNIEnv *env, jobject jobj, jlong instanceNumber, jint, jint, jlong, jbyteArray) {}

/*
 * Class:     im_tox_jtoxcore_JTox
 * Method:    toxFileGetFileId
 * Signature: (III)[B
 */
JNIEXPORT jbyteArray JNICALL Java_im_tox_jtoxcore_JTox_toxFileGetFileId
        (JNIEnv *env, jobject jobj, jlong instanceNumber, jint, jint) {}

/*
 * Class:     im_tox_jtoxcore_JTox
 * Method:    toxFriendSendLossyPacket
 * Signature: (II[B)V
 */
JNIEXPORT void JNICALL Java_im_tox_jtoxcore_JTox_toxFriendSendLossyPacket
        (JNIEnv *env, jobject jobj, jlong instanceNumber, jint, jbyteArray) {}

/*
 * Class:     im_tox_jtoxcore_JTox
 * Method:    toxFriendSendLosslessPacket
 * Signature: (II[B)V
 */
JNIEXPORT void JNICALL Java_im_tox_jtoxcore_JTox_toxFriendSendLosslessPacket
        (JNIEnv *env, jobject jobj, jlong instanceNumber, jint, jbyteArray) {}

/*
 * Class:     im_tox_jtoxcore_JTox
 * Method:    invokeSelfConnectionStatus
 * Signature: (IZ)V
 */
JNIEXPORT void JNICALL Java_im_tox_jtoxcore_JTox_registeSelfConnectionStatusCallBack
        (JNIEnv *env, jobject jobj, jlong instanceNumber, jboolean b) {
    Tox *tox = ((tox_jni_globals_t *) ((intptr_t) instanceNumber))->tox;
    if(b){
        tox_callback_self_connection_status(tox,callback_connectionstatus);
    }else{
        tox_callback_self_connection_status(tox, nullptr);
    }
}


/*
 * Class:     im_tox_jtoxcore_JTox
 * Method:    registeFriendnameCallback
 * Signature: (IZ)V
 */
JNIEXPORT void JNICALL Java_im_tox_jtoxcore_JTox_registeFriendNameCallBack
        (JNIEnv *env, jobject jobj, jlong instanceNumber, jboolean b) {
    Tox *tox = ((tox_jni_globals_t *) ((intptr_t) instanceNumber))->tox;
    if(b){
        tox_callback_friend_name (tox,callback_friendname);
    }else{
        tox_callback_friend_name(tox, nullptr);
    }
}


JNIEXPORT void JNICALL Java_im_tox_jtoxcore_JTox_registeFriendStatusMessageCallBack
        (JNIEnv *env, jobject jobj, jlong instanceNumber, jboolean b) {
    Tox *tox = ((tox_jni_globals_t *) ((intptr_t) instanceNumber))->tox;
    if(b){
        tox_callback_friend_status_message(tox,callback_friend_status_message);
        tox_callback_friend_name (tox,callback_friendname);
    }else{
        tox_callback_friend_status_message(tox, nullptr);
    }
}


JNIEXPORT void JNICALL Java_im_tox_jtoxcore_JTox_registeFriendStatusCallBack
        (JNIEnv *env, jobject jobj, jlong instanceNumber, jboolean b) {
    Tox *tox = ((tox_jni_globals_t *) ((intptr_t) instanceNumber))->tox;
    if(b){
        tox_callback_friend_status (tox,callback_friend_status);
    }else{
        tox_callback_friend_status(tox, nullptr);
    }
}


JNIEXPORT void JNICALL Java_im_tox_jtoxcore_JTox_registeFriendConnectionStatusCallBack
        (JNIEnv *env, jobject jobj, jlong instanceNumber, jboolean b) {
    Tox *tox = ((tox_jni_globals_t *) ((intptr_t) instanceNumber))->tox;
    if(b){
        tox_callback_friend_connection_status (tox,callback_friend_connection_status);
    }else{
        tox_callback_friend_connection_status(tox, nullptr);
    }
}



JNIEXPORT void JNICALL Java_im_tox_jtoxcore_JTox_registeFriendTypingCallBack
        (JNIEnv *env, jobject jobj, jlong instanceNumber, jboolean b) {
    Tox *tox = ((tox_jni_globals_t *) ((intptr_t) instanceNumber))->tox;
    if(b){
        tox_callback_friend_typing (tox,callback_friend_typing);
    }else{
        tox_callback_friend_typing(tox, nullptr);
    }
}


JNIEXPORT void JNICALL Java_im_tox_jtoxcore_JTox_registeFriendNReadReceiptCallBack
        (JNIEnv *env, jobject jobj, jlong instanceNumber, jboolean b) {
    Tox *tox = ((tox_jni_globals_t *) ((intptr_t) instanceNumber))->tox;
    if(b){
        tox_callback_friend_read_receipt (tox,callback_friend_read_receipt);
    }else{
        tox_callback_friend_read_receipt(tox, nullptr);
    }
}


JNIEXPORT void JNICALL Java_im_tox_jtoxcore_JTox_registeFriendRequestCallBack
        (JNIEnv *env, jobject jobj, jlong instanceNumber, jboolean b) {
    Tox *tox = ((tox_jni_globals_t *) ((intptr_t) instanceNumber))->tox;
    if(b){
        tox_callback_friend_request (tox,callback_friend_request);
    }else{
        tox_callback_friend_request(tox, nullptr);
    }
}



JNIEXPORT void JNICALL Java_im_tox_jtoxcore_JTox_registeFriendMessageCallBack
        (JNIEnv *env, jobject jobj, jlong instanceNumber, jboolean b) {
    Tox *tox = ((tox_jni_globals_t *) ((intptr_t) instanceNumber))->tox;
    if(b){
        tox_callback_friend_message (tox,callback_friend_message);
    }else{
        tox_callback_friend_message(tox, nullptr);
    }
}


JNIEXPORT void JNICALL Java_im_tox_jtoxcore_JTox_registeFriendLossyPacketCallBack
        (JNIEnv *env, jobject jobj, jlong instanceNumber, jboolean b) {
    Tox *tox = ((tox_jni_globals_t *) ((intptr_t) instanceNumber))->tox;
    if(b){
        tox_callback_friend_lossy_packet (tox,callback_friend_lossy_packet);
    }else{
        tox_callback_friend_lossy_packet(tox, nullptr);
    }
}


JNIEXPORT void JNICALL Java_im_tox_jtoxcore_JTox_registeFriendLossLessPacketCallBack
        (JNIEnv *env, jobject jobj, jlong instanceNumber, jboolean b) {
    Tox *tox = ((tox_jni_globals_t *) ((intptr_t) instanceNumber))->tox;
    if(b){
        tox_callback_friend_lossless_packet (tox,callback_friend_lossless_packet);
    }else{
        tox_callback_friend_lossless_packet(tox, nullptr);
    }
}

#ifdef __cplusplus
}
#endif
#endif