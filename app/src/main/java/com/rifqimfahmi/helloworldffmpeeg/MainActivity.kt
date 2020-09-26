package com.rifqimfahmi.helloworldffmpeeg

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.rifqimfahmi.helloworldffmpeeg.video.VideoFileConfig


class MainActivity : AppCompatActivity() {

    private val videoPickerBtn: ImageButton? by lazy(LazyThreadSafetyMode.NONE) {
        findViewById<ImageButton?>(R.id.pick_video)
    }
    private val sampleText: TextView? by lazy(LazyThreadSafetyMode.NONE) {
        findViewById<TextView?>(R.id.sample_text)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initVideoPickerBtn()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PICK_FILE_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            processImage(data)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun processImage(data: Intent) {
        val fd = applicationContext.contentResolver.openFileDescriptor(data.data!!, "r")?.fd ?: return
        val videoConfig = VideoFileConfig()
        sampleText?.text = videoConfig.getCodecName(fd)
    }

    private fun initVideoPickerBtn() {
        videoPickerBtn?.setOnClickListener {
            pickFile()
        }
    }

    private fun pickFile() {
        val chooseFile = Intent(Intent.ACTION_GET_CONTENT)
        chooseFile.addCategory(Intent.CATEGORY_OPENABLE)
        chooseFile.type = "video/*"
        startActivityForResult(
            Intent.createChooser(chooseFile, "Choose a file"),
            PICK_FILE_REQUEST_CODE
        )
    }

    companion object {
        const val PICK_FILE_REQUEST_CODE = 1
    }
}
