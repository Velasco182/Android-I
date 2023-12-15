package com.example.soundvista

import MusicService
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.soundvista.adapters.AdaptadorArchivosMP3
import com.example.soundvista.data.archivoMP3
import android.Manifest
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder

class MusicActivity : AppCompatActivity() {

    // En tu actividad o fragmento
    private val REQUEST_PERMISSION = 1001
    private lateinit var recyclerView: RecyclerView
    private lateinit var archivoAdapter: AdaptadorArchivosMP3

    private var musicService: MusicService? = null
    private var isServiceBound = false

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as MusicService.LocalBinder
            musicService = binder.getService()
            isServiceBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isServiceBound = false
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_music)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        archivoAdapter = AdaptadorArchivosMP3(emptyList()) { archivoMP3: archivoMP3 ->
            playMusic(archivoMP3)
        }
        
        recyclerView.adapter = archivoAdapter

        solicitarPermisos()
        obtenerYMostrarArchivos()
        // Llama a bindMusicService después de solicitar los permisos
        bindMusicService()

        //val serviceIntent = Intent(this, MusicService::class.java)
        //bindService(serviceIntent, connection, Context.BIND_AUTO_CREATE)
        //val archivos = obtenerYMostrarArchivos()
        //archivoAdapter.actualizarArchivos(archivos)
    }

    override fun onResume() {
        super.onResume()
        bindMusicService()
        //obtenerYMostrarArchivos()
    }

    override fun onPause() {
        super.onPause()
        unbindMusicService()
    }

    /*private fun obtenerYMostrarArchivos(): List<archivoMP3> {
        // Aquí debes implementar la lógica para obtener la lista de archivos multimedia
        // Puedes usar el código que usaste para cargar un solo archivo y adaptarlo para cargar múltiples
        // Asegúrate de tener los permisos necesarios y de manejar los casos donde no se pueden cargar los archivos
        //val directorio = Environment.getExternalStorageDirectory()
        // Aquí implementa la lógica para obtener y mostrar archivos del directorio
        val directorioMusica = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)
        val archivosMP3 = mutableListOf<archivoMP3>()

        if (directorioMusica.exists() && directorioMusica.isDirectory) {
            val archivos = directorioMusica.listFiles()

            archivos?.let {
                for (archivo in archivos) {
                    if (archivo.isFile && archivo.name.endsWith(".mp3", true)) {
                        archivosMP3.add(archivoMP3(archivo.name, archivo.absolutePath))
                    }
                }
            }
        }

        return archivosMP3
    }*/


    // Verificar y solicitar permisos en tiempo de ejecución
    private fun solicitarPermisos() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_MEDIA_AUDIO
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_MEDIA_AUDIO),
                REQUEST_PERMISSION
            )
        } else {
            //bindMusicService()
            obtenerYMostrarArchivos() // Llama a la función para cargar archivos si ya tienes el permiso
        }
    }

    private fun obtenerYMostrarArchivos() {
        val archivos = musicService?.obtenerArchivosMultimedia()
        archivos?.let {
            archivoAdapter.actualizarArchivos(archivos)
        }
    }

    private fun bindMusicService() {
        val intent = Intent(this, MusicService::class.java)
        bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

    private fun unbindMusicService() {
        if (isServiceBound) {
            unbindService(connection)
            isServiceBound = false
        }
    }

    private fun playMusic(archivoMP3: archivoMP3) {
        if (isServiceBound) {

            musicService?.playMusic(archivoMP3.filePath, archivoAdapter.archivosMP3.indexOf(archivoMP3))
            // Aquí puedes actualizar tu interfaz de usuario con la información de la canción actual
            // Puedes usar musicService.getCurrentSongInfo() para obtener la información
        } else {
            Toast.makeText(this, "Servicio no enlazado", Toast.LENGTH_SHORT).show()
        }
    }

    // Manejar el resultado de la solicitud de permisos
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                obtenerYMostrarArchivos() // Llama a la función para cargar archivos si el permiso fue concedido
            } else {
                // Manejar el caso en el que el usuario no concede el permiso
                Toast.makeText(this, "Permiso denegado", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindMusicService()
    }

}