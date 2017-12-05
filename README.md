# JuniorDesignGroup7150

## Release Notes
### CULTIVATE v1.0
Cultivate has been released for the first time!
### Feature list:
Profiles
Groups
Events
Attendance list

### Bug fixes since last release:
N/A (first release!)

### Known Bugs:
Users cannot remove themselves from groups
Emails show up with "\_com" instead of  ".com" (due to all "." being replaced with "\_" in the database)

## Install Guide
Prerequisites: In order to run Cultivate, one must have an Android device or have access to an Android emulator and a PC.
### Step 1: Download Android Studio
- See Android’s [Install Options](https://developer.android.com/studio/index.html) 

### Step 2:  Once you have Android Studio installed, you must configure it to work with the Firebase Database and Authentication packages.
- First, you have to make sure that your Android Studio environment will work nicely with Firebase. Go to *Tools > Android > SDK Manager*, then to the tab that reads *SDK Tools* and make sure the *Google Play Services* box is checked. Apply these changes.

### Step 3: Download the GitHub Cultivate Repository
- Found [here](https://github.com/ALLComplex/Cultivate)

### Step 4: Open the project with Android Studio
- Open Android Studio
- Select *Open An Exsisting Android Studio project*
- Find the location of the GitHub Cultivate Repository on your screen and select *Ok*

### Step 5: Set-up emulator or real device for download
For emulator:
- In the *Select Deployment Target* window, click *Create New Virtual Device*.
- In the *Select Hardware* screen, select “*Nexus 5X*” and then click *Next*.
- In the *System Image* screen, select *API level 26* and click *Next*.
    - If you don't have that version installed, a Download link is shown, so click that and complete the download, then click *Next*.
- On the Android Virtual Device (AVD) screen, leave all the settings alone and click *Finish*.
For real device:
- If you're developing on Windows, you might need to [install the appropriate USB driver](https://developer.android.com/studio/run/oem-usb.html) for your device.
- Enable USB debugging in the Developer options.

### Step 6: Run the app on the device
- In Android Studio, click the app module in the Project window and then select *Run > Run*.
- In the *Select Deployment Target* window, select your device, and click *OK*.

### Step 7: Use the application
- On either an emulator or a real device, Cultivate should immediately open after the build has completed.
- You can now begin to use Cultivate.  

### Troubleshooting:
- If the app crashes when there is data being read or written from the database, check the section titled “*dependencies*” in the file *Gradle Scripts > build.gradle (Module: app)*. Cultivate currently runs on Firebase Android SDK version 11.4.0 and is used in the dependencies “*com.google.firebase:firebase-database*” and “*com.google.firebase:firebase-auth*”. This could become out of sync, if Firebase releases a new version that is not backwards compatible. Simply replace the version number listed in the Gradle file with the most recent version. See the [Firebase Android Studio](https://firebase.google.com/docs/android/setup) release notes for the current versions.
- For additional notes on running the app once the project is up in Android Studio, see these [helpful tips](https://developer.android.com/training/basics/firstapp/running-app.html).
