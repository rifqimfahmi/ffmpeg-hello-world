#include <jni.h>
#include <string>
extern "C" {
    #include <libavformat/avformat.h>
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_rifqimfahmi_helloworldffmpeeg_video_VideoFileConfig_getCodecName(JNIEnv *env,
                                                                          jobject thiz,
                                                                          jint descriptor) {
    char pipe[32] {};
    sprintf(pipe, "pipe:%d", descriptor);
    AVFormatContext *avFormatContext {nullptr};

    if (avformat_open_input(&avFormatContext, pipe, nullptr, nullptr)) {
        const char *hello = "Error reading file header";
        return env->NewStringUTF(hello);
    }

    std::string codecName = avFormatContext->iformat->long_name;
    return env->NewStringUTF(codecName.c_str());
}