package dev.ztssst.voicevox_tts

import android.app.Activity
import android.os.Bundle
import android.util.Log

class GetSampleText: Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("GetSampleText", "onCreate called with intents: $intent extras: ${intent.extras}")
        finish()
    }
}