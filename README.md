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

Usage

-**Jobs Screen**: Upon opening the app, navigate to the "Jobs" section to explore available job listings. Scroll down to load more jobs.
-**Job Details**: Click on a job card to view more detailed information.
-**Bookmarking** Jobs: Tap the bookmark icon on a job card to save it. The job will appear in the "Bookmarks" section.
-**Offline Access**: Access bookmarked jobs offline from the "Bookmarks" section.
