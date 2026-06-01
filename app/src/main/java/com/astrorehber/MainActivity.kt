package com.astrorehber

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.astrorehber.ui.AstroNavigation
import com.astrorehber.ui.theme.AstroRehberTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AstroRehberTheme {
                AstroNavigation()
            }
        }
    }
}
