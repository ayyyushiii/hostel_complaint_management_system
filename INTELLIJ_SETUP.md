# IntelliJ IDEA Setup Guide - Hostel Complaint Management System

## ✅ Getting Started with IntelliJ

### Step 1: Open Project in IntelliJ
1. Launch IntelliJ IDEA
2. File → Open
3. Navigate to: `d:\6th_Sem_ShiT\OOPSAD\hostetl-complaint-management`
4. Click Open
5. When prompted, select **"Open as Project"**

### Step 2: Load Maven Configuration
IntelliJ will automatically detect `pom.xml`:
- A popup should appear asking to load Maven project
- Click **"Load Maven Changes"** or **"Configure"**
- IntelliJ starts downloading dependencies (this may take 1-3 minutes)

If NO popup appears:
1. View → Tool Windows → Maven
2. Click refresh icon (⟳) to reload maven
3. Or: File → Settings → Maven → Check "Always update snapshots"

### Step 3: Configure Project SDK
If you see "Project SDK not set":
1. File → Project Structure
2. Project Settings → Project
3. SDK dropdown → Add SDK → JDK
4. Select Java 11 or higher
5. Click OK

### Step 4: Mark Source Folders
1. Right-click `src/main/java` → Mark Directory as → Sources Root
2. Right-click `src/main/webapp` → Mark Directory as → Web Resource Root

### Step 5: Build & Compile
1. Build → Build Project
2. Or press: **Ctrl + F9**
3. Watch for "Build completed successfully" message

### Step 6: Verify No Errors
1. View → Tool Windows → Problems
2. Should show: **0 errors** (may show some warnings, that's OK)
3. To fix imports: Code → Optimize Imports (Ctrl + Alt + O)

---

## 🔍 What IntelliJ Will Download

From the pom.xml, IntelliJ automatically downloads:

✅ **javax.servlet-api-4.0.1.jar** - Servlet library
✅ **mysql-connector-java-8.0.28.jar** - MySQL driver  
✅ **javax.servlet.jsp-api-2.3.3.jar** - JSP support
✅ **jstl-1.2.jar** - JSP Tag Library
✅ Other dependencies...

All these go into: `~\.m2\repository\` (Maven cache)

---

## 🚀 Deploy to Tomcat from IntelliJ

### Option A: Embedded Tomcat
1. Run → Edit Configurations
2. Click **+** → Tomcat Server → Local
3. Set Application server (download if needed)
4. Set Deployment tab → Add artifact → Select WAR
5. Click OK
6. Run → Run (or press Shift + F10)

### Option B: External Tomcat Server
1. Download Tomcat 9+: https://tomcat.apache.org/
2. Run → Edit Configurations → + → Tomcat Server → Local
3. Configure Server → Application server → Configure...
4. Select Tomcat installation folder
5. Set Deployment: Add artifact
6. Run deployment

---

## 📁 Project Structure (What You'll See)

```
hostel-complaint-management/
├── .idea/                          ← IntelliJ config (auto-created)
├── .m2/                            ← Maven dependencies (auto-downloaded)
├── src/
│   └── main/
│       ├── java/                   ← Java source code
│       │   └── com/hostel/
│       │       ├── controller/
│       │       ├── dao/
│       │       ├── model/
│       │       ├── filter/
│       │       └── util/
│       └── webapp/
│           ├── WEB-INF/
│           │   └── web.xml
│           ├── views/
│           └── *.jsp
├── target/                         ← Compiled output (auto-created)
├── pom.xml                         ← Maven configuration
└── *.md                           ← Documentation
```

---

## ✅ Checklist - Before Running

- [ ] Project opens without errors in IntelliJ
- [ ] Maven dependencies downloaded (no red squiggly lines)
- [ ] Build → Build Project succeeds
- [ ] No compilation errors in Problems panel
- [ ] Java SDK configured (Java 11+)
- [ ] Source folders marked correctly

---

## 🐛 Troubleshooting

### Issue: "Cannot resolve symbol 'javax.servlet'"
**Solution:**
1. File → Invalidate Caches → Invalidate and Restart
2. Or: Build → Rebuild Project
3. Check that Maven is properly loading

### Issue: ".m2 repository not found"
**Solution:**
- IntelliJ will auto-create it
- Force Maven reload: View → Tool Windows → Maven → Reload (⟳)

### Issue: "No SDK set"
**Solution:**
- File → Project Structure → Project
- Select JDK 11+ from SDK dropdown
- If none available: Add SDK → Click download → Select version

### Issue: "Cannot find MySQLDatabase"
**Solution:**
- The MySQL connector JAR gets downloaded by Maven
- Ensure MySQL server is running
- Check DBConnection.java has correct credentials

---

## 📝 Next Steps

1. **Run the Application:**
   - Set up Tomcat configuration
   - Click Run (Shift + F10)
   - Access: http://localhost:8080/hostel-complaint-management

2. **Test with Sample Data:**
   - Database already has sample users (see hostel_complaint_db.sql)
   - Login credentials:
     - Admin: admin@hostel.com / admin@123
     - Student: alice@student.com / alice@123
     - Staff: john@hostel.com / john@123

3. **Debug (if needed):**
   - Set breakpoints (click line number)
   - Run → Debug (Shift + F9)
   - Step through code with F10 (step over) / F11 (step into)

---

## 🎉 You're All Set!

IntelliJ will:
✅ Manage dependencies automatically
✅ Provide code completion & hints
✅ Build & compile everything
✅ Help deploy to Tomcat
✅ Debug like a pro

**Start building!** 🚀
