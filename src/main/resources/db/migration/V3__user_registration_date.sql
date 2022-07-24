ALTER TABLE user
    ADD registration_date DATETIME;

UPDATE user
SET registration_date = NOW()
WHERE registration_date is null;