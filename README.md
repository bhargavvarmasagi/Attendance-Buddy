# Attendance-Buddy

### Team Members:
* Viplav Billa
* Bhargav Varma Sagi
* Priyanka Bolem
* Piyush Jain

## Application Information
### Overview
Attendance Buddy is an Android application that manages faculty and student data for attendance purposes. This application is to make the attendance process easy and manage attendance easily.

### Test Credentials
- **Username:** `admin`
- **Password:** `password`

### APK Information
- **Supported Devices:** Android devices running version Android 8.0 (Oreo) or higher.

## Sequence Information

### Start Screen
- **File:** `MainActivity.java`
- **Layout:** `activity_main.xml`
- **Description:** The start button navigates the user to the Login screen.

### Login Screen
- **File:** `login.java`
- **Layout:** `activity_login.xml`
- **Description:** 
  - Validates hardcoded admin credentials:
    - Username: `admin`
    - Password: `password`
  - Successful login navigates to the Index screen.

### Index Screen
- **File:** `index.java`
- **Layout:** `activity_index.xml`
- **Description:** 
  - Provides navigation buttons to the following screens:
    - Add Faculty
    - Add Students (not implemented)
    - View Students
    - View Faculty (not implemented)
    - Logout

### Add Faculty Screen
- **File:** `addFaculty.java`
- **Layout:** `activity_add_faculty.xml`
- **Description:** 
  - Collects faculty details including first name, last name, mobile number, username, and password.
  - Saves the data locally in `faculty.txt`.

### View Students Screen
- **File:** `viewStudent.java`
- **Layout:** `activity_view_student.xml`
- **Description:** 
  - Reads student details from the file `students.txt`.
  - Displays the student data on the screen.


### Development Setup
1. Clone the repository:
   ```bash
   git clone <repository_url>
