-- ==========================================
-- 1. SEED USERS
-- ==========================================
INSERT INTO users (id, first_name, last_name, email, password, role) VALUES
                                                                         (1, 'Ahmed', 'Mansouri', 'ahmed@example.com', 'password123', 'CUSTOMER'),
                                                                         (2, 'Sarah', 'Benali', 'sarah@example.com', 'password123', 'CUSTOMER'),
                                                                         (3, 'Admin', 'User', 'admin@rentals.com', 'admin123', 'ADMIN');

-- ==========================================
-- 2. SEED CARS (The Fleet)
-- ==========================================
-- Notice we use the exact Enum string values we defined in CarStatus
INSERT INTO cars (id, make, model, production_year, daily_rate, status, image_url) VALUES
                                                                                       (1, 'Dacia', 'Logan', 2023, 30.00, 'ACTIVE', 'https:/s/logan.jpg/example.com/image'),
                                                                                       (2, 'Renault', 'Clio', 2022, 35.00, 'RENTED', 'https://example.com/images/clio.jpg'),
                                                                                       (3, 'Hyundai', 'Tucson', 2024, 75.00, 'ACTIVE', 'https://example.com/images/tucson.jpg'),
                                                                                       (4, 'Range Rover', 'Evoque', 2021, 150.00, 'MAINTENANCE', 'https://example.com/images/evoque.jpg'),
                                                                                       (5, 'Peugeot', '208', 2019, 25.00, 'RETIRED_SOLD', 'https://example.com/images/208.jpg');

-- ==========================================
-- 3. SEED BOOKINGS
-- ==========================================
-- A past, completed booking (Allowed to leave a review)
INSERT INTO bookings (id, user_id, car_id, start_date, end_date, total_price, status) VALUES
    (1, 1, 1, '2023-12-01', '2023-12-05', 120.00, 'COMPLETED');

-- A currently active booking (Why the Clio is marked as RENTED)
INSERT INTO bookings (id, user_id, car_id, start_date, end_date, total_price, status) VALUES
    (2, 2, 2, CURRENT_DATE - 1, CURRENT_DATE + 3, 140.00, 'CONFIRMED');

-- A future booking
INSERT INTO bookings (id, user_id, car_id, start_date, end_date, total_price, status) VALUES
    (3, 1, 3, CURRENT_DATE + 10, CURRENT_DATE + 15, 375.00, 'PENDING');

-- ==========================================
-- 4. SEED PAYMENTS
-- ==========================================
INSERT INTO payments (id, booking_id, amount, transaction_id, status) VALUES
                                                                          (1, 1, 120.00, 'ch_mock_success_001', 'SUCCESS'),
                                                                          (2, 2, 140.00, 'ch_mock_success_002', 'SUCCESS');

-- ==========================================
-- 5. SEED REVIEWS
-- ==========================================
-- Ahmed is a verified renter of the Dacia Logan (Booking ID 1), so he can leave this review
INSERT INTO reviews (id, car_id, user_id, rating, comment) VALUES
    (1, 1, 1, 5, 'Great car, very fuel-efficient for driving around Casa!');

-- ==========================================
-- 6. RESET POSTGRESQL SEQUENCES (The Pro-Tip)
-- ==========================================
-- Since we hardcoded IDs (1, 2, 3), PostgreSQL's automatic counter is still at 1.
-- If you try to add a new car via the API, Postgres will try to use ID 1 and crash.
-- This forces the database to start counting from 100 for all future API inserts.
ALTER SEQUENCE users_id_seq RESTART WITH 100;
ALTER SEQUENCE cars_id_seq RESTART WITH 100;
ALTER SEQUENCE bookings_id_seq RESTART WITH 100;
ALTER SEQUENCE payments_id_seq RESTART WITH 100;
ALTER SEQUENCE reviews_id_seq RESTART WITH 100;