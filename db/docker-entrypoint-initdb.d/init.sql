CREATE DATABASE qc_test;
CREATE USER qc_user WITH PASSWORD 'qc_secret';
CREATE DATABASE qc_test;
GRANT ALL PRIVILEGES ON DATABASE qc_test TO qc_user;