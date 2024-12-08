# Attendance-Buddy

### Team Members:
* Viplav Billa
* Bhargav Varma Sagi
* Priyanka Bolem -  [Github Link](https://github.com/priyankabolem)
* Piyush Jain

## Application Information
### Overview
Attendance Buddy is an Android application that manages faculty and student data for attendance purposes. This application is to make the attendance process easy and manage attendance easily.

### Features
- Clean UI
- Predict Attendance
- Backup and Restore support
- Daily notifications to mark attendance
- Time Table view to edit you time table

### APK Information
- **Supported Devices:** Android devices running version Android 8.0 (Oreo) or higher.

## Sequence Information

### Start Screen
- **File:** `MainActivity.java`
- **Layout:** `activity_main.xml`
- **Description:** The start button navigates the user to the Login screen.

### Handler Screen
- **File:** `Class.java`
- **Description:** Handles the core class details such as ID and class name.

### Login Screen
- **File:** `jdbc:mysql://192.168.2.199/admin`
- **Description:** 
  - Validates hardcoded admin credentials:
    - Username: `admin`
    - Password: `7ObKupK87kLFsZWK`
  - Successful login navigates to the Index screen.

### Session Management:
- **File:** `sessionEditor.java`
- **Layout:** `activity_session_Editor.xml`
- **Description:** 
  - Create new attendance sessions with customizable details.
  - Edit existing sessions or delete them as needed.
  - View session attendance, differentiating between present and absent students.

### View Students Screen
- **File:** `ShowSessions.java`
- **Layout:** `activity_show_sessions.xml`
- **Description:** 
  - Fetching sessions from a database.
  - Displaying sessions in a ListView
  - Navigating to session creation or editing activities.

### Database
- **File:** `Database.java`
- **Description:** 
  - Fetching/Update database.
  - Persistent storage of classes, sessions, and attendance data using a custom database.
  - CRUD (Create, Read, Update, Delete) operations for classes and sessions.

### View Students Screen
- **File:** `Student.java`
- **Layout:** `activity_student.xml`
- **Description:** 
  - Reads student details from the file `students.txt`.
  - Displays the student data on the screen.

## Future Scope
Adding location-based attendance tracking. GAT uses User's GPS to retrieve user's current location and based on the distance between the institution and user's current location, it decides whether the user is in class or not and marks the attendance accordingly.

### Development Setup
1. Clone the repository:
   ```bash
   git clone (https://github.com/bhargavvarmasagi/Attendance-Buddy.git)
