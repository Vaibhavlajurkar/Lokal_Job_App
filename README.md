## Download

You can download the APK and view the demo video from the links below:

- **APK**: [LokalJobApp.apk](releases/app-debug.apk)
- **Demo Video**: [Watch Demo Video](https://www.loom.com/share/1d6f48e64f8d4e128dd25cdf7c022c66?sid=8c6efdf6-cbb0-407d-8fa2-6e04744f7113)


# LokalJobApp

## Overview

LokalJobApp is an Android application designed to help users find and bookmark job opportunities. The app features a clean and intuitive interface with two primary sections: "Jobs" and "Bookmarks." Users can explore job listings, view detailed information, and bookmark jobs for offline access.

## Features

- **Bottom Navigation UI**: The app features a bottom navigation bar with two sections:
  - **Jobs**: Browse job listings fetched from a remote API.
  - **Bookmarks**: View and manage bookmarked jobs stored locally for offline access.
  
- **Jobs Screen**: 
  - Fetches job data from the API using an infinite scroll approach.
  - Displays key job details, including title, location, salary, and contact information.
  
- **Job Details Screen**:
  - Users can click on a job card to view more detailed information about the job.
  
- **Bookmarking**:
  - Users can bookmark jobs, which will then appear in the "Bookmarks" section.
  - Bookmarked jobs are stored in a local SQLite database for offline viewing.
  
- **State Management**:
  - Handles different app states, including loading, error, and empty views, ensuring a smooth user experience.

## Technical Stack

- **Language**: Kotlin
- **UI Development**: XML for layout design
- **Networking**: Volley for API requests
- **Local Storage**: SQLite for storing bookmarked jobs offline

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/VaibhavLajurkar/LokalJobApp.git
2.Open the project in Android Studio.
3.Build the project to download dependencies.
4.Run the app on an Android device or emulator.

## Usage

1. **Jobs Screen**: 
   - Upon opening the app, navigate to the "Jobs" section to explore available job listings.
   - Scroll down to load more jobs as they are fetched from the API.
   
2. **Job Details**: 
   - Click on a job card to view more detailed information about the job, such as title, location, salary, and phone details.
   
3. **Bookmarking Jobs**: 
   - Tap the bookmark icon on a job card to save it for later.
   - The bookmarked job will appear in the "Bookmarks" section for easy access.
   
4. **Offline Access**: 
   - Access your bookmarked jobs offline from the "Bookmarks" section, even when you don't have an internet connection.


## Project Structure

```plaintext
LokalJobApp/
├── app/
│   ├── sampledata/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/lokaljobapp/
│   │   │   │   ├── Bookmark/
│   │   │   │   ├── DatabaseHelper/
│   │   │   │   ├── Job/
│   │   │   │   ├── JobAdapter.kt
│   │   │   │   ├── JobDetailActivity/
│   │   │   │   ├── MainActivity/
│   │   │   │   ├── MyApp/
│   │   │   ├── res/
│   │   │   │   ├── drawable/
│   │   │   │   │   ├── background.xml
│   │   │   │   │   ├── bookmark.xml
│   │   │   │   │   ├── bookmark_blackcolour.xml
│   │   │   │   │   ├── bookmark_selector.xml
│   │   │   │   │   ├── bookmarks_simple_duotone_svgrepo_com.xml
│   │   │   │   │   ├── cloud_jobs_api_svgrepo_com.xml
│   │   │   │   │   ├── ic_launcher_background.xml
│   │   │   │   │   ├── ic_launcher_foreground.xml
│   │   │   │   ├── layout/
│   │   │   │   │   ├── activity_job_detail.xml
│   │   │   │   │   ├── activity_main.xml
│   │   │   │   │   ├── fragment_bookmark.xml
│   │   │   │   │   ├── fragment_job.xml
│   │   │   │   │   ├── item_job.xml
│   │   │   │   ├── menu/
│   │   │   │   │   ├── bottom_nav.xml
│   │   │   │   ├── mipmap-hdpi/
│   │   │   │   ├── mipmap-mdpi/
│   │   │   │   ├── mipmap-xhdpi/
│   │   │   │   ├── mipmap-anydpi/
│   │   │   │   ├── mipmap-xxhdpi/
│   │   │   │   ├── mipmap-xxxhdpi/
│   │   │   │   │   ├── ic_launcher.xml
│   │   │   │   │   ├── ic_launcher.webp
│   │   │   │   │   ├── ic_launcher_round.webp
│   │   │   │   │   ├── ic_launcher_round.xml
│   │   │   │   ├── values/
│   │   │   │   │   ├── colors.xml
│   │   │   │   │   ├── strings.xml
│   │   │   │   │   ├── styles.xml
│   │   │   │   │   ├── themes.xml
│   │   │   │   │   ├── values-night/
│   │   │   │   ├── xml/
│   │   │   │   │   ├── backup_rules.xml
│   │   │   │   │   ├── data_extraction_rules.xml
│   │   │   ├── AndroidManifest.xml
│   ├── test/
│   │   ├── java/com/example/lokaljobapp/
│   │   │   ├── ExampleInstrumentedTest/
│   │   │   ├── ExampleUnitTest/
├── build.gradle.kts
├── proguard-rules.pro
├── gradle.properties
├── gradle-wrapper.properties
├── libs.versions.toml
├── local.properties
└── settings.gradle.kts
