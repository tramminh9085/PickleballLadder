# 🏓 Pickleball Ladder League - Quick Start Guide

## 🎯 Tôi đã tạo gì?

Một hệ thống quản lý bảng xếp hạng Pickleball đầy đủ với:

### ✅ Backend (Java Spring Boot)
- RESTful API cho Players, Leagues, và Matches
- Database schema hoàn chỉnh với JPA entities
- Business logic services
- CORS-enabled for frontend communication

### ✅ Frontend (React + HTML/CSS)
- Responsive design (desktop, tablet, mobile)
- Player ladder rankings
- Search & filter functionality
- Statistics dashboard
- Modern UI giống với Global Pickleball Network

### ✅ Mock Data
- 10 sample players từ các thành phố Việt Nam
- 1 sample league (DK 2026-09-09 - Hà Nội)
- Dữ liệu đã được pre-load tự động

---

## 🚀 Cách sử dụng

### **Option 1: Xem Demo Ngay Lập Tức** ⚡ (Khuyến nghị)

1. Mở file `DEMO.html` trong trình duyệt
   ```bash
   open DEMO.html
   # Hoặc double-click file DEMO.html
   ```

2. Không cần cài đặt gì, demo chạy ngay trong browser!

### **Option 2: Chạy Full Stack Application** 🔧

#### Prerequisites:
- Java 17+
- Maven 3.6+

#### Steps:

1. **Cài đặt Maven** (nếu chưa có):
   ```bash
   # macOS
   brew install maven
   
   # Ubuntu/Debian
   sudo apt-get install maven
   ```

2. **Build project**:
   ```bash
   cd /Users/duyminh/IdeaProjects/PickleballLadder
   mvn clean install
   ```

3. **Run application**:
   ```bash
   mvn spring-boot:run
   ```

4. **Access tại**: `http://localhost:8080`

5. **View H2 Database Console**: `http://localhost:8080/h2-console`

---

## 📁 Project Structure

```
PickleballLadder/
├── DEMO.html                 # 👈 Quick demo (không cần setup)
├── run.sh                    # Helper script để chạy app
├── README.md                 # Detailed documentation
├── pom.xml                   # Maven configuration
├── .gitignore
│
├── src/main/java/com/pickleball/
│   ├── model/               # JPA Entities
│   │   ├── Player.java
│   │   ├── League.java
│   │   └── Match.java
│   ├── repository/          # Data Access
│   │   ├── PlayerRepository.java
│   │   ├── LeagueRepository.java
│   │   └── MatchRepository.java
│   ├── service/             # Business Logic
│   │   └── PlayerService.java
│   ├── controller/          # REST APIs
│   │   ├── PlayerController.java
│   │   └── LeagueController.java
│   ├── dto/                 # Data Transfer Objects
│   │   └── PlayerDTO.java
│   └── PickleballLadderApplication.java
│
└── src/main/resources/
    ├── application.properties
    ├── data.sql             # Sample data
    └── static/
        ├── index.html       # Main page (React)
        ├── css/style.css    # Styling
        └── js/app.js        # React components
```

---

## 🎨 Features

### Player Rankings
- ✅ Sorted by rating (ELO-like system)
- ✅ Show wins, losses, win percentage
- ✅ Display skill level
- ✅ Show location

### Search & Filter
- ✅ Search by player name
- ✅ Filter by location
- ✅ Sort by rating, wins, or alphabetically

### Statistics
- ✅ Total players count
- ✅ Total matches played
- ✅ Top rating
- ✅ Average rating

### UI/UX
- ✅ Modern gradient design
- ✅ Responsive mobile-friendly
- ✅ Smooth animations & transitions
- ✅ Professional color scheme (primary: #94a105 - Pickleball green)

---

## 📊 Sample Data

**Players (10 total):**
1. Nguyễn Văn A - Rating: 1850 (15W-3L)
2. Trần Thị B - Rating: 1780 (14W-5L)
3. Lê Văn C - Rating: 1720 (12W-6L)
... và 7 players khác

**League:**
- Name: DK 2026-09-09
- Location: Hà Nội
- Type: LADDER
- Status: ACTIVE

---

## 🔌 API Endpoints

### Players
```
GET    /api/players              # Get all players (sorted by rating)
GET    /api/players/{id}         # Get player by ID
POST   /api/players              # Create new player
PUT    /api/players/{id}         # Update player
DELETE /api/players/{id}         # Delete player
```

### Leagues
```
GET    /api/leagues              # Get all leagues
GET    /api/leagues/{id}         # Get league by ID
GET    /api/leagues/location/{location}  # Get leagues by location
POST   /api/leagues              # Create new league
PUT    /api/leagues/{id}         # Update league
DELETE /api/leagues/{id}         # Delete league
```

---

## 🎯 Next Steps (Future Enhancements)

- [ ] User authentication & authorization
- [ ] Match history & scheduling
- [ ] Real ELO rating algorithm
- [ ] Email notifications
- [ ] Advanced statistics & charts (Charts.js)
- [ ] Player profiles with avatars
- [ ] Team leagues support
- [ ] Tournament management
- [ ] PostgreSQL database integration
- [ ] Docker containerization
- [ ] Deployment to cloud (AWS, Heroku, etc.)

---

## 🐛 Troubleshooting

### Port 8080 already in use
```bash
mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=8081"
```

### Build fails with Maven not found
```bash
# Check Maven installation
mvn --version

# If not installed, install Maven first
brew install maven
```

### Can't see sample data
```bash
# Clear H2 database and restart
# Delete target/ folder and rebuild
rm -rf target/
mvn clean install
```

---

## 📝 File Descriptions

| File | Purpose |
|------|---------|
| DEMO.html | Standalone HTML demo - **RUN THIS FIRST** ⭐ |
| run.sh | Shell script to build and run the app |
| pom.xml | Maven dependencies and build config |
| Player.java | JPA entity for players |
| PlayerRepository.java | Database queries for players |
| PlayerService.java | Business logic for player operations |
| PlayerController.java | REST API endpoints for players |
| app.js | React components and UI logic |
| style.css | CSS styling for the application |
| data.sql | SQL script to populate sample data |

---

## 💡 Tips

1. **Start with DEMO.html** - It's the fastest way to see the UI
2. **Customize colors** - Edit `--primary-color` in CSS to change the theme
3. **Add more players** - Modify `generateMockPlayers()` in app.js
4. **Connect real database** - Switch from H2 to PostgreSQL in application.properties
5. **Deploy easily** - The WAR/JAR can be deployed to any cloud platform

---

## 📞 Support

For issues or questions:
1. Check README.md for detailed documentation
2. Review code comments in Java files
3. Check browser console for JavaScript errors (F12)
4. Check server logs: `tail -f nohup.out`

---

**Tạo bởi**: GitHub Copilot  
**Ngày tạo**: 2026-09-16  
**Version**: 1.0.0  

**Happy Coding! 🎉**
