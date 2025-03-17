package dev.ztssst.voicevox_tts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import dev.ztssst.voicevox_tts.ui.theme.Voicevox_ttsTheme

class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Voicevox_ttsTheme {
                Surface {
                    Text("TEST!")
                }
            }
        }
    }
}