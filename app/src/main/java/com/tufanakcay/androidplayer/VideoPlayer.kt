package com.tufanakcay.androidplayer

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.VideoView
import android.widget.MediaController


class VideoPlayer : AppCompatActivity() {
    lateinit var videoView: VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        videoView = findViewById(R.id.videoView)
        val videoController = MediaController(this)
        videoView.setMediaController(videoController)

        // val uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.video)
        val uri = Uri.parse("https://file-examples.com/wp-content/storage/2017/04/file_example_MP4_480_1_5MG.mp4")
        videoView.setVideoURI(uri)
        videoView.start()

    }
}