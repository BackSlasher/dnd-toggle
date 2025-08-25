package net.backslasher.dndtoggle

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.content.ComponentName
import android.service.quicksettings.TileService
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var notificationManager: NotificationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val permissionButton = findViewById<Button>(R.id.permission_button)
        val permissionStatusText = findViewById<TextView>(R.id.permission_status_text)

        permissionButton.setOnClickListener {
            val intent = Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS)
            startActivity(intent)
        }

        updateUi()
    }

    override fun onResume() {
        super.onResume()
        updateUi()
        TileService.requestListeningState(this, ComponentName(this, DnDTileService::class.java))
    }

    private fun updateUi() {
        val permissionStatusText = findViewById<TextView>(R.id.permission_status_text)
        if (notificationManager.isNotificationPolicyAccessGranted) {
            permissionStatusText.text = "DND Permissions: Granted ✅"
        } else {
            permissionStatusText.text = "DND Permissions: Not Granted ❌"
        }
    }
}
