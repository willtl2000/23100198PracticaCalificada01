package dev.wticona.a23100198practicacalificada01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dev.wticona.a23100198practicacalificada01.navigation.AppNavGraph
import dev.wticona.a23100198practicacalificada01.ui.theme._23100198PracticaCalificada01Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _23100198PracticaCalificada01Theme {
                AppNavGraph()
            }
        }
    }
}
