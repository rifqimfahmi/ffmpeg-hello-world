package com.rifqimfahmi.helloworldffmpeeg.video

class VideoFileConfig {

    external fun getCodecName(fileDescriptor: Int): String

    companion object {

        init {
            listOf("avutil", "avcodec", "avformat", "swscale", "swresample", "native-lib").forEach {
                System.loadLibrary(it)
            }
        }
    }
}