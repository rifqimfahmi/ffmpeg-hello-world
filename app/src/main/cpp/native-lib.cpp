#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_rifqimfahmi_helloworldffmpeeg_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++17";
    return env->NewStringUTF(hello.c_str());
}
