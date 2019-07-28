DROP TABLE IF EXISTS customer_profile;
 
CREATE TABLE customer_profile (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  user_id VARCHAR(50) NOT NULL
);
 
INSERT INTO customer_profile (id, first_name, last_name, user_id) VALUES
  (100, 'Aliko', 'Hank', 'ahank'),
  (101, 'Bill', 'Gates', 'bgates');
  
  
