Create a bare-bones kotlin app that exposes a quick settings tile.
It is set according to the current do not disturb status:
1. If no DND is on, it's "inactive" with "dnd: off" on it. Clicking it sets DND to `INTERRUPTION_FILTER_PRIORITY`.
2. If and DND is on, it's "active" and with "dnd: STATUS" with STATUS being the DND status. Clicking it sets DND to `INTERRUPTION_FILTER_ALL`.

Subscribe to DND changes from the notification manager, so that when DND changes from outside the app, it updates accordingly.
