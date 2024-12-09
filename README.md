# Attendance-Buddy

### Team Members:
* Viplav Billa
* Bhargav Varma Sagi
* Priyanka Bolem 
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

### Login Page
- **File:** `LoginActivity.java`
- **Layout:** `activity_login.xml`
- **Description:** The login page of Attendance Buddy provides secure access to the app's features.
- **Username:** androidFaculty
- **Password:** android2024dec
### Attendance Records Maintenance
- **File:** `MainActivity.java`
- **Layout:** `activity_main.xml`
- **Description:** It allows us to create classes to add student into it.

### Handler Screen
- **File:** `Class.java`
- **Description:** Handles the core class details such as ID and class name.
  
### Class Management
- **File:** `ClassEditor.java`
- **Layout** `activity_class_editor.xml`
- **Description:** Creates and manages classes to organize students and sessions effectively.  

### Session Management:
- **File:** `sessionEditor.java`
- **Layout:** `activity_session_Editor.xml`
- **Description:** 
  - Create new attendance sessions with customizable details.
  - Edit existing sessions or delete them as needed.
  - View session attendance, differentiating between present and absent students.

### View Students Screen
- **File:** `Student.java`
- **Layout:** `activity_student.xml`
- **Description:** 
  - Reads student details from the students activity.
  - Displays the student data on the screen.

### Student Management
- **File:** `ClassEditor.java`
- **Layout** `activity_class_editor.xml`
- **Description:** Add, edit and remove student records within each class, ensuring accurate and up-to-date information for every student.

### Attendance Tracking
- **File:** `ViewSession.java`, `ShowSessions.java`
- **Layout** `activity_view_session.xml`, `activity_show_sessions.xml`
- **Description:** 
  - Fetching sessions from a database.
  - Displaying sessions in a ListView
  - Navigating to session creation or editing activities.
  - Check attendance status for each student in the session.
 
### Database
- **File:** `Database.java`
- **Description:** 
  - Fetching/Update database.
  - Persistent storage of classes, sessions, and attendance data using a custom database.
  - CRUD (Create, Read, Update, Delete) operations for classes and sessions.
### Database Details
- **File:** `jdbc:mysql://192.168.2.199/admin`
- **Description:** 
  - Validates hardcoded admin credentials:
    - Username: `admin`
    - Password: `7ObKupK87kLFsZWK`
  
## Future Scope
Adding location-based attendance tracking. GAT uses User's GPS to retrieve user's current location and based on the distance between the institution and user's current location, it decides whether the user is in class or not and marks the attendance accordingly.

## Running the app
When you launch the Attendance Buddy app, you begin at the Welcome Page, where you can create classes. Once a class is saved, it appears in the class list. By clicking on a class name, you can view the list of students in that class, with an option to add more students. After adding students, you can proceed to the Session Editor to create attendance sessions. Finally, the Sessions List provides an overview of all sessions for a class, showing details of students marked as Present or Absent for each session.

### Development Setup
1. Clone the repository:
   ```bash
   git clone (https://github.com/bhargavvarmasagi/Attendance-Buddy.git)
