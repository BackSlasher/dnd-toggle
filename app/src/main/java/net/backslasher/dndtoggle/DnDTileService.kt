package net.backslasher.dndtoggle

import android.app.NotificationManager
import android.app.PendingIntent

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Icon
import android.provider.Settings
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService

class DnDTileService : TileService() {

    private lateinit var notificationManager: NotificationManager

    override fun onCreate() {
        super.onCreate()
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    override fun onStartListening() {
        super.onStartListening()
        updateTile()
    }

    override fun onClick() {
        super.onClick()
        if (!notificationManager.isNotificationPolicyAccessGranted) {
            // Do nothing on short click if permission is not granted
            return
        }

        if (notificationManager.currentInterruptionFilter == NotificationManager.INTERRUPTION_FILTER_ALL) {
            notificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_PRIORITY)
        } else {
            notificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_ALL)
        }
        updateTile()
    }

    

    

    private fun updateTile() {
        val tile = qsTile
        if (!notificationManager.isNotificationPolicyAccessGranted) {
            tile.state = Tile.STATE_UNAVAILABLE
            tile.label = "dnd: permission"
            tile.icon = Icon.createWithResource(this, R.drawable.ic_dnd_off)
            tile.updateTile()
            return
        }

        when (notificationManager.currentInterruptionFilter) {
            NotificationManager.INTERRUPTION_FILTER_ALL -> {
                tile.state = Tile.STATE_INACTIVE
                tile.label = "dnd: off"
                tile.icon = Icon.createWithResource(this, R.drawable.ic_dnd_off)
            }
            NotificationManager.INTERRUPTION_FILTER_PRIORITY -> {
                tile.state = Tile.STATE_ACTIVE
                tile.label = "dnd: priority"
                tile.icon = Icon.createWithResource(this, R.drawable.ic_dnd_on)
            }
            NotificationManager.INTERRUPTION_FILTER_ALARMS -> {
                tile.state = Tile.STATE_ACTIVE
                tile.label = "dnd: alarms"
                tile.icon = Icon.createWithResource(this, R.drawable.ic_dnd_on)
            }
            NotificationManager.INTERRUPTION_FILTER_NONE -> {
                tile.state = Tile.STATE_ACTIVE
                tile.label = "dnd: none"
                tile.icon = Icon.createWithResource(this, R.drawable.ic_dnd_on)
            }
            NotificationManager.INTERRUPTION_FILTER_UNKNOWN -> {
                tile.state = Tile.STATE_UNAVAILABLE
                tile.label = "dnd: unknown"
                tile.icon = Icon.createWithResource(this, R.drawable.ic_dnd_off)
            }
        }
        tile.updateTile()
    }
}
