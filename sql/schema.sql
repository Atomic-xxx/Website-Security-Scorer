CREATE DATABASE security_scanner;
USE security_scanner;
CREATE TABLE results (
    sno INT AUTO_INCREMENT PRIMARY KEY,
    url VARCHAR(255) NOT NULL,
    https_score INT DEFAULT 0 CHECK (https_score >= 0),
    ssl_score INT DEFAULT 0 CHECK (ssl_score >= 0),
    domain_score INT DEFAULT 0 CHECK (domain_score >= 0),
    ip_score INT DEFAULT 0 CHECK (ip_score >= 0),
    network_score INT DEFAULT 0 CHECK (network_score >= 0),
    total_score INT DEFAULT 0 CHECK (total_score >= 0),
    scan_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
SELECT * FROM results;
DROP DATABASE security_scanner;