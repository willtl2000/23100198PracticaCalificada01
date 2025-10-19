package dev.wticona.a23100198practicacalificada01.presentation.auth

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.wticona.a23100198practicacalificada01.firebase.FirebaseAuthManager
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavController){

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Column (
        modifier = Modifier.padding(16.dp)
    ){
        Text("Iniciar Sesión", style = MaterialTheme.typography.titleLarge)

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo Electrónico") },
            modifier = Modifier.padding(vertical = 8.dp).fillMaxWidth()
        )
        //OutlinedTextField for password
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            modifier = Modifier.padding(vertical = 8.dp).fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )
        //spacer
        Spacer(modifier = Modifier.height(16.dp))
        //Button for login
        Button(
            onClick = {
                if (email.isNotBlank() && password.isNotBlank()) {
                    scope.launch {
                        val result = FirebaseAuthManager().loginUser(email, password)
                        if(result.isSuccess)
                            navController.navigate("home")
                        else {
                            val error = result.exceptionOrNull()?.message ?: "Error desconocido"
                            Toast.makeText(
                                context, error, Toast.LENGTH_SHORT
                            ).show()

                        }
                    }
                }
            },
            modifier = Modifier.padding(vertical = 8.dp).fillMaxWidth()
        ) {
            Text("Iniciar Sesión")
        }

    }
}