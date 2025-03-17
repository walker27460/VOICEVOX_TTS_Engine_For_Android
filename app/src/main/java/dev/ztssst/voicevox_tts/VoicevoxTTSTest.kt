package dev.ztssst.voicevox_tts

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.ztssst.voicevox_tts.ui.theme.Voicevox_ttsTheme

@Composable
fun VoicevoxTTSTest(){
    Text("Test TTS Message", modifier = Modifier.fillMaxSize())
}

@Preview
@Composable
private fun TextTestPreview() {
    Voicevox_ttsTheme {
        VoicevoxTTSTest()
    }
}