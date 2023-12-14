// MusicService.kt

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.Build
import android.os.Environment
import android.os.IBinder
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.example.soundvista.MusicActivity
import com.example.soundvista.R
import com.example.soundvista.data.archivoMP3

class MusicService : Service() {
    private val binder = LocalBinder()
    private var mediaPlayer: MediaPlayer? = null

    private val notificationId = 1
    private val channelId = "MusicPlayerChannel"

    private var currentSongIndex: Int = -1
    private var archivosMP3: List<archivoMP3> = emptyList()

    inner class LocalBinder : Binder() {
        fun getService(): MusicService = this@MusicService
    }

    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }

    override fun onCreate() {
        super.onCreate()
        archivosMP3 = obtenerArchivosMultimedia()
    }

    fun playMusic(filePath: String, position: Int) {
        stopMusic()

        mediaPlayer = MediaPlayer().apply {
            setDataSource(filePath)
            prepare()
            start()
            setOnCompletionListener {
                // Aquí puedes manejar la lógica cuando una canción termina de reproducirse
            }
        }

        currentSongIndex = position
        updateNotification()
    }

    fun stopMusic() {
        mediaPlayer?.apply {
            if (isPlaying) {
                stop()
            }
            release()
        }
        mediaPlayer = null
        currentSongIndex = -1
        stopForeground(true)
    }

    private fun updateNotification() {
        val notificationLayout = createNotificationLayout()
        val notification = createNotification(notificationLayout)

        val notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager.notify(notificationId, notification)
    }

    private fun obtenerArchivosMultimedia(): List<archivoMP3> {
        // Implementa la lógica para obtener la lista de archivos multimedia
        // Puedes usar el código que usaste para cargar un solo archivo y adaptarlo para cargar múltiples
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
    }

    private fun createNotificationLayout(): RemoteViews {
        val notificationLayout = RemoteViews(packageName, R.layout.notification_layout)

        val currentSong = getCurrentSongInfo()
        notificationLayout.setTextViewText(R.id.notificationTitle, "Now Playing")
        notificationLayout.setTextViewText(R.id.notificationSubtitle, currentSong)

        return notificationLayout
    }

    private fun createNotification(notificationLayout: RemoteViews): Notification {
        val notificationIntent = Intent(this, MusicActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        return NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.image)
            .setContentIntent(pendingIntent)
            .setCustomContentView(notificationLayout)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setOngoing(true) // Hace que la notificación sea persistente
            .build()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Music Player",
                NotificationManager.IMPORTANCE_DEFAULT
            )

            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun getCurrentSongInfo(): String {
        return if (currentSongIndex != -1 && currentSongIndex < archivosMP3.size) {
            val currentSong = archivosMP3[currentSongIndex]
            "${currentSong.nombre} - ${currentSong.filePath}"
        } else {
            "No hay canción en reproducción"
        }
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createNotificationChannel()

        val notificationLayout = createNotificationLayout()
        val notification = createNotification(notificationLayout)

        startForeground(notificationId, notification)

        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        stopMusic()
    }

}
