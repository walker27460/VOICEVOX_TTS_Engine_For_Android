package dev.ztssst.voicevox_tts

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log

class CheckVoiceData: Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("CheckVoiceData", "onCreate called with intent: $intent, extras: ${intent.extras}")
        super.onCreate(savedInstanceState)
        val result = TextToSpeech.Engine.CHECK_VOICE_DATA_PASS
        val returnData = Intent()

        val available: ArrayList<String> = arrayListOf("jpn-JPN")
        val unavailable: ArrayList<String> = arrayListOf()

        returnData.putStringArrayListExtra(TextToSpeech.Engine.EXTRA_AVAILABLE_VOICES, available)
        returnData.putStringArrayListExtra(
            TextToSpeech.Engine.EXTRA_UNAVAILABLE_VOICES,
            unavailable
        )
        setResult(result, returnData)
        finish()
    }
}