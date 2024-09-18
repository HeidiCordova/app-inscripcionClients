package com.cursosant.myapplication2

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var nombreEditText: EditText
    private lateinit var apellidosEditText: EditText
    private lateinit var correoEditText: EditText
    private lateinit var telefonoEditText: EditText
    private lateinit var grupoSanguineoEditText: EditText
    private lateinit var registrarButton: Button
    private lateinit var leerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Referencia a las vistas
        nombreEditText = findViewById(R.id.editTextText)
        apellidosEditText = findViewById(R.id.editTextText2)
        correoEditText = findViewById(R.id.editTextText3)
        telefonoEditText = findViewById(R.id.editTextText4)
        grupoSanguineoEditText = findViewById(R.id.editTextText5)
        registrarButton = findViewById(R.id.button)
        leerButton = findViewById(R.id.button2)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        registrarButton.setOnClickListener {
            registrarDatos()
        }

        leerButton.setOnClickListener {
            leerDatos()
        }
    }

    private fun registrarDatos() {
        val nombre = nombreEditText.text.toString()
        val apellidos = apellidosEditText.text.toString()
        val correo = correoEditText.text.toString()
        val telefono = telefonoEditText.text.toString()
        val grupoSanguineo = grupoSanguineoEditText.text.toString()

        // Crear cadena de texto con los datos
        val datos = "Nombre: $nombre\nApellidos: $apellidos\nCorreo: $correo\nTeléfono: $telefono\nGrupo Sanguíneo: $grupoSanguineo\n"

        // Guardar en archivo de texto
        val fileName = "datos.txt"
        try {
            val fileOutputStream: FileOutputStream = openFileOutput(fileName, MODE_PRIVATE)
            fileOutputStream.write(datos.toByteArray())
            fileOutputStream.close()
            Log.d("MainActivity", "Datos registrados en el archivo $fileName")
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun leerDatos() {
        val fileName = "datos.txt"
        try {
            val fileInputStream: FileInputStream = openFileInput(fileName)
            val inputStreamReader = fileInputStream.bufferedReader()
            val data = inputStreamReader.use { it.readText() }
            Log.d("MainActivity", "Datos leídos:\n$data")
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
