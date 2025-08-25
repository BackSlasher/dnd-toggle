package com.example.dndtoggle

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var notificationManager: NotificationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val permissionButton = findViewById<Button>(R.id.permission_button)

        if (notificationManager.isNotificationPolicyAccessGranted) {
            permissionButton.visibility = View.GONE
        } else {
            permissionButton.visibility = View.VISIBLE
            permissionButton.setOnClickListener {
                val intent = Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS)
                startActivity(intent)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val permissionButton = findViewById<Button>(R.id.permission_button)
        if (notificationManager.isNotificationPolicyAccessGranted) {
            permissionButton.visibility = View.GONE
        } else {
            permissionButton.visibility = View.VISIBLE
        }
    }
}
