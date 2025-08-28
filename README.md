# DND Toggle

Adds a "Quick Settings Tile" that toggles Do Not Disturb.

Created because an Android version upgrade made it 2 clicks to toggle DND (since they have several modes now).  
Vibe-coded with Gemini, written with Kotlin.  

## Usage
Install APK as usual.  
Add the new Quick Settings tile to your tile collection.  

When DND is off, the tile is "inactive":  
![inactive](docs/off.png)  
Clicking it will turn DND on to "priority" mode.  

When DND is on, the tile is "active":  
![active](docs/on.png)  
Clicking it will turn DND off.  

## Permissions
Apps usually can't access DND. If the app doesn't have access, it'll be "grayed out".  
![unavailable](docs/invalid.png)  
To allow this app to access it, long click on the tile to access the app screen.  
Then click the button to go to system settings  
![](docs/grant1.png)  
There, select our app  
![](docs/grant2.png)  
DND access will be off  
![](docs/grant3.jpg)  
Set it to on  
![](docs/grant4.jpg)  
The app should now work

## Contributing
PRs and issues welcome

## Changelog

### Version 1.5
- Some linting.

### Version 1.4
- Migrated project to API level 35.
- Updated Gradle to version 8.4.
- Updated Android Gradle Plugin to version 8.6.0.
