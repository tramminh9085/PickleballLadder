# Pickleball Ladder League Management System

A modern web application for managing pickleball ladder leagues, built with **Spring Boot** backend and **React** frontend.

## Features

- 📊 **Ladder Rankings** - View player rankings with ratings and statistics
- 🏆 **Player Management** - Track wins, losses, and skill levels
- 🎯 **League Management** - Create and manage multiple leagues
- 📈 **Performance Tracking** - Monitor win rates and progress
- 🔍 **Search & Filter** - Find players by name or location
- 📱 **Responsive Design** - Works on desktop, tablet, and mobile

## Tech Stack

### Backend
- **Java 17**
- **Spring Boot 3.1.5**
- **Spring Data JPA**
- **H2 Database** (development)
- **Maven**

### Frontend
- **React 18**
- **Bootstrap 5**
- **Font Awesome Icons**
- **Vanilla JavaScript**

## Project Structure

```
PickleballLadder/
├── src/
│   ├── main/
│   │   ├── java/com/pickleball/
│   │   │   ├── model/           # JPA Entity classes
│   │   │   ├── repository/      # Spring Data repositories
│   │   │   ├── service/         # Business logic
│   │   │   ├── controller/      # REST API endpoints
│   │   │   ├── dto/             # Data Transfer Objects
│   │   │   └── PickleballLadderApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── data.sql         # Sample data
│   │       └── static/
│   │           ├── index.html
│   │           ├── css/style.css
│   │           └── js/app.js
├── pom.xml
└── README.md
```

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher

### Installation & Running

1. **Clone/Navigate to project:**
   ```bash
   cd /Users/duyminh/IdeaProjects/PickleballLadder
   ```

2. **Build the project:**
   ```bash
   mvn clean install
   ```

3. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the application:**
   - Open browser and navigate to: `http://localhost:8080`
   - The application will automatically load the React frontend

## API Endpoints

### Players
- `GET /api/players` - Get all players (sorted by rating)
- `GET /api/players/{id}` - Get player by ID
- `POST /api/players` - Create new player
- `PUT /api/players/{id}` - Update player
- `DELETE /api/players/{id}` - Delete player

### Leagues
- `GET /api/leagues` - Get all leagues
- `GET /api/leagues/{id}` - Get league by ID
- `GET /api/leagues/location/{location}` - Get leagues by location
- `POST /api/leagues` - Create new league
- `PUT /api/leagues/{id}` - Update league
- `DELETE /api/leagues/{id}` - Delete league

## Sample Data

The application comes with pre-loaded sample data:
- 10 sample players from different Vietnamese cities
- 1 sample league (DK 2026-09-09 - Hà Nội)

Players are automatically ranked by their rating when loaded.

## Screenshots

### Ladder Rankings View
- **Header** - Navigation and authentication
- **Sidebar** - League selection and filters
- **Stats Cards** - Total players, matches, top rating, average rating
- **Ladder Table** - Player rankings with ratings, records, and win percentages

## Features Explained

### Ranking System
Players are ranked based on their rating (ELO-like system). Higher rating = better position in ladder.

### Player Statistics
- **Rating**: Overall performance score (0-2000+)
- **Wins/Losses**: Match record
- **Win %**: Win rate percentage
- **Skill Level**: 1-5 rating scale

### Search & Filter
- Search by player name or location
- Sort by rating (default), wins, or alphabetically
- Filter results in real-time

## Customization

### Styling
Modify `/src/main/resources/static/css/style.css` to customize colors and layout.

### Mock Data
Update `/src/main/resources/static/js/app.js` function `generateMockPlayers()` to add more sample players.

### Database
Switch to PostgreSQL or MySQL by updating `pom.xml` and `application.properties`.

## Future Enhancements

- [ ] Database persistence with PostgreSQL
- [ ] User authentication and authorization
- [ ] Match history and match scheduling
- [ ] ELO rating algorithm implementation
- [ ] Email notifications
- [ ] Mobile app (React Native)
- [ ] Real-time updates (WebSocket)
- [ ] Advanced statistics and charts
- [ ] Player profiles and avatars

## Database Configuration

### Current (Development)
- **Database**: H2 (in-memory)
- **DDL**: `create-drop` (recreates on startup)

### Production (Future)
To use PostgreSQL:
1. Add dependency in `pom.xml`:
   ```xml
   <dependency>
       <groupId>org.postgresql</groupId>
       <artifactId>postgresql</artifactId>
       <version>42.6.0</version>
   </dependency>
   ```

2. Update `application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/pickleball
   spring.datasource.username=postgres
   spring.datasource.password=your_password
   spring.jpa.database-platform=org.hibernate.dialect.PostgreSQL10Dialect
   spring.jpa.hibernate.ddl-auto=validate
   ```

## Troubleshooting

### Port 8080 already in use
```bash
mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=8081"
```

### Clean build
```bash
mvn clean install -U
```

### View H2 Console (Development)
Navigate to: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: (leave blank)

## License

MIT License - feel free to use for personal or commercial projects.

## Support

For issues or questions, please contact the development team.

---

**Created**: 2026
**Version**: 1.0.0
