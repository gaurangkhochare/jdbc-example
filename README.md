# Student Record Management System

A Java Swing desktop application for managing student records in MySQL. The application uses JDBC and provides controls for adding, searching, viewing, and deleting student records. Made for a college assignment.

## Features

- Add a student record
- Search for a student by roll number
- View all student records
- Delete a student by roll number
- Clear the form and close the application

The **Update** button is present in the interface but is not implemented yet.

## Requirements

- [Apache NetBeans](https://netbeans.apache.org/front/main/download/) with Java SE support
- JDK 26 or a compatible JDK configured in NetBeans
- [MySQL Server](https://www.mysql.com/downloads/) 8.0 or later
- MySQL Workbench/Command Line Client
- [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/)

## MySQL Setup

1. Start the MySQL Server.
2. Open MySQL Workbench, the MySQL command-line client, or another SQL client.
3. Create the database and table by running:

```sql
CREATE DATABASE college;
USE college;

CREATE TABLE student_details (
	rollno VARCHAR(20) NOT NULL PRIMARY KEY,
	student_name VARCHAR(100) NOT NULL,
	department VARCHAR(100) NOT NULL,
	semester INT NOT NULL,
	mobileno VARCHAR(20) NOT NULL,
	course VARCHAR(20) NOT NULL
);
```

4. Optionally insert a test record:

```sql
INSERT INTO student_details
	(rollno, student_name, department, semester, mobileno, course)
VALUES
	('26101A001', 'Test Student', 'Information Technology', 3, '9876543210', 'INFT');
```

## Configure the Database Connection

The connection is currently defined in `src/StudentDatabase.java`:

```java
String url = "jdbc:mysql://localhost:3306/college";
String username = "root";
String password = "gaurang";
```

Before running the application, change `username` and `password` to match the MySQL account on your computer. If MySQL is running on another host or port, update the JDBC URL as well.

For a real application, do not commit database passwords to source control. Use environment variables or a configuration file excluded from version control.

## Open and Configure in Apache NetBeans

1. Install and open Apache NetBeans.
2. Select **File > Open Project**.
3. Select the project folder containing `nbproject`, `src`, and `build.xml`.
4. If NetBeans asks for a Java platform, select a compatible installed JDK. The project is currently configured for Java source and target level 26.
5. Download the current MySQL Connector/J `.jar` file from the official MySQL website.
6. In the **Projects** window, expand the project, right-click **Libraries**, and select **Add JAR/Folder**.
7. Select the downloaded MySQL Connector/J jar and confirm.

The existing NetBeans project properties contain a machine-specific Connector/J path. If NetBeans reports that the referenced jar cannot be found, remove that broken library reference and add the downloaded jar using the steps above.

## Build and Run

1. Make sure MySQL Server is running and the `college.student_details` table exists.
2. In NetBeans, right-click the project and select **Clean and Build**.
3. Right-click the project and select **Run**.
4. The main class is `StudentManagementApp`.

You can also run the Ant targets from the project directory:

```text
ant clean
ant jar
ant run
```

## Using the Application

- Enter the roll number, name, mobile number, department, semester, and course, then select **Add**.
- Enter a roll number and select **Search** to display one record.
- Select **View All** to display every record.
- Enter a roll number and select **Delete** to remove that record.
- Select **Clear** to reset the form.
- Select **Exit** to close the application and its database connection.

## Troubleshooting

### JDBC driver not found

Confirm that the MySQL Connector/J jar is listed under the project's **Libraries** in NetBeans and that it is available on the runtime classpath.

### Access denied for user

Verify the username and password in `StudentDatabase.java`. Also confirm that the MySQL account has access to the `college` database.

### Unknown database or table

Run the SQL in the [MySQL Setup](#mysql-setup) section and confirm that the database is named `college` and the table is named `student_details`.

### Communications link failure

Check that MySQL Server is running and that the JDBC URL uses the correct host and port. The default URL expects MySQL on `localhost:3306`.
