# Setup Instructions - Servlet Dependencies

## Problem
The project requires servlet libraries (javax.servlet) which are not currently in your classpath.

## Solution: Install Maven

### Step 1: Download Maven
- Visit: https://maven.apache.org/download.cgi
- Download: **apache-maven-3.8.x-bin.zip**

### Step 2: Extract & Setup
- **Windows**: Extract to `C:\Maven` or `C:\Program Files\Maven`
- **Add to PATH**:
  - Press `Win + X` → System
  - Click "Advanced system settings"
  - Environment Variables → New
  - Variable: `MAVEN_HOME`
  - Value: `C:\Maven` (your Maven path)
  - Edit `Path`, Add: `%MAVEN_HOME%\bin`

### Step 3: Verify Installation
```powershell
mvn --version
```
Should output Maven version info

### Step 4: Build Project
```powershell
cd "d:\6th_Sem_ShiT\OOPSAD\hostetl-complaint-management"
mvn clean install
```

This will:
- Download all dependencies (servlet-api, mysql-connector, etc.)
- Create `target\` folder with compiled classes
- Create `target\hostel-complaint-management.war` for Tomcat deployment

### Step 5: Verify Errors are Gone
After Maven build completes, your Java files will compile without errors.

---

## Alternative: Use IDE

VS Code is great for editing, but for Java projects with dependencies, consider:

### Eclipse IDE (Free)
1. Download: https://www.eclipse.org/downloads/
2. Extract & Open
3. File → Import → Existing Maven Projects
4. Select your project folder
5. Eclipse downloads dependencies automatically ✅

### IntelliJ Community Edition (Free)
1. Download: https://www.jetbrains.com/idea/
2. File → Open → Select project folder
3. IntelliJ detects pom.xml and downloads dependencies ✅

---

## Manual Dependency Download (Not Recommended)

If you can't use Maven or IDE, manually download JARs:

1. **Servlet API** (javax.servlet-api-4.0.1.jar)
   - From: https://mvnrepository.com/artifact/javax.servlet/javax.servlet-api/4.0.1

2. **MySQL Connector** (mysql-connector-java-8.0.28.jar)
   - From: https://dev.mysql.com/downloads/connector/j/

3. **Place in**: `lib/` folder of your project

4. **Add to Classpath**: 
   - VS Code → Extensions → Install "Extension Pack for Java"
   - Configure Java Project settings

---

## Quick Test After Setup

After Maven build, verify with:
```powershell
mvn compile
```

Should show: `BUILD SUCCESS`

---

## Deployment

Once Maven build succeeds:

```powershell
# Copy WAR file to Tomcat
cp target/hostel-complaint-management.war "C:\Program Files\Apache Software Foundation\Tomcat 9.0\webapps\"

# Restart Tomcat
# Access: http://localhost:8080/hostel-complaint-management
```

---

## Still Having Issues?

1. **Maven not found after install?**
   - Restart PS/Terminal
   - Check PATH variable
   - Verify Maven bin folder exists

2. **Build fails?**
   - Delete `src/main/java/com/hostel` folder
   - Re-extract source files
   - Run Maven again

3. **Java version issue?**
   - Ensure Java 11+ is installed
   - Check: `java --version`

---

**Recommended**: Use Maven for easiest setup! 🚀
