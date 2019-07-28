DROP TABLE IF EXISTS customer_profile;
 
CREATE TABLE customer_profile (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  user_id VARCHAR(50) NOT NULL,
  phone_number VARCHAR(20) NOT NULL
);
 
INSERT INTO customer_profile (id, first_name, last_name, user_id, phone_number) VALUES
  (100, 'Aliko', 'Hank', 'ahank', '43145634567'),
  (101, 'Bill', 'Gates', 'bgates', '7916786789');
  
  
  
  
