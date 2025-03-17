package dev.ztssst.voicevox_tts

import android.util.Log
import jp.hiroshiba.voicevoxcore.blocking.OpenJtalk
import jp.hiroshiba.voicevoxcore.blocking.Synthesizer
import jp.hiroshiba.voicevoxcore.blocking.Onnxruntime
import jp.hiroshiba.voicevoxcore.blocking.VoiceModelFile

class VoicevoxTTSEngine(voiceModelPath: String, openJtalkDictPath: String){
    private val synthesizer: Synthesizer
    @Suppress("PrivatePropertyName")
    private val TAG = "VoicevoxTTSService"

    init {
        val model = VoiceModelFile(voiceModelPath)
        val jtalk = OpenJtalk(openJtalkDictPath)
        val onnxruntime = Onnxruntime.loadOnce().perform()
        val synthesizer = Synthesizer.builder(onnxruntime, jtalk).build()
        synthesizer.loadVoiceModel(model)
        this.synthesizer = synthesizer
        Log.d(TAG, "VoicevoxTTSService Initialized")
    }

    fun synthesis(text: String, speakerId: Int = 14): ByteArray{ // 冥鳴ひまりでやるので、defaultのstyleIdは14
        Log.d("${TAG}->Synthesizer", "Synthesis started (isGPUMode = ${synthesizer.isGpuMode})")
        val synthStartTime = System.currentTimeMillis()
        val data = synthesizer.tts(text, speakerId).perform()
        Log.d("${TAG}->Synthesizer", "Synthesis finished: data.size = ${data.size}")
        Log.d("${TAG}->Synthesizer", "Synthesis elapsed: ${System.currentTimeMillis() - synthStartTime}ms")
        return data
    }
}