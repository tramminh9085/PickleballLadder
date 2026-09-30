-- Insert sample leagues
INSERT INTO leagues (name, location, description, created_date, status, league_type)
VALUES ('DK 2026-09-09', 'Hà Nội', 'A pickleball league in Hanoi Vietnam', '2026-01-15', 'ACTIVE', 'LADDER');

-- Insert sample players
INSERT INTO players (name, email, skill_level, wins, losses, rating, rank, location, phone_number, created_at)
VALUES
    ('Nguyễn Văn A', 'nguyena@email.com', 4.5, 15, 3, 1850.0, 1, 'Hà Nội', '0901234567', '2026-01-15'),
    ('Trần Thị B', 'tranb@email.com', 4.2, 14, 5, 1780.0, 2, 'Hà Nội', '0901234568', '2026-01-15'),
    ('Lê Văn C', 'levanc@email.com', 4.0, 12, 6, 1720.0, 3, 'Hà Nội', '0901234569', '2026-01-15'),
    ('Phạm Thị D', 'phamd@email.com', 3.8, 11, 7, 1650.0, 4, 'TP HCM', '0901234570', '2026-01-15'),
    ('Hoàng Văn E', 'hoange@email.com', 3.7, 10, 8, 1600.0, 5, 'Đà Nẵng', '0901234571', '2026-01-15'),
    ('Võ Thị F', 'vof@email.com', 3.5, 9, 9, 1550.0, 6, 'Hà Nội', '0901234572', '2026-01-15'),
    ('Trương Văn G', 'truong@email.com', 3.3, 8, 10, 1500.0, 7, 'TP HCM', '0901234573', '2026-01-15'),
    ('Bùi Thị H', 'buih@email.com', 3.2, 7, 11, 1450.0, 8, 'Hà Nội', '0901234574', '2026-01-15'),
    ('Đinh Văn I', 'dinhi@email.com', 3.0, 6, 12, 1400.0, 9, 'Cần Thơ', '0901234575', '2026-01-15'),
    ('Ngô Thị K', 'ngok@email.com', 2.8, 5, 13, 1350.0, 10, 'Hà Nội', '0901234576', '2026-01-15');

-- Insert a default play day with all sample players
INSERT INTO play_days (name, play_date)
VALUES ('Ngày chơi 09/09/2026', '2026-09-09');

INSERT INTO play_day_players (play_day_id, player_id)
SELECT 1, id FROM players;
