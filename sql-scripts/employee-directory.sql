CREATE DATABASE  IF NOT EXISTS `employee_directory`;
USE `employee_directory`;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Data for table `employee`
--

INSERT INTO `employee` VALUES 
	(1,'Leslie','Andrews','leslie@luv2code.com'),
	(2,'Emma','Baumgarten','emma@luv2code.com'),
	(3,'Avani','Gupta','avani@luv2code.com'),
	(4,'Yuri','Petrov','yuri@luv2code.com'),
	(5,'Juan','Vega','juan@luv2code.com');
    


--
-- Table structure for table `users`
--
    
    
DROP TABLE IF EXISTS `authorities`;
DROP TABLE IF EXISTS `users`;

CREATE TABLE `users`(
	`username` varchar(50) NOT NULL,
    `password` varchar(68) NOT NULL,
    `enabled` tinyint NOT NULL,
    PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Data for table `users`
--

 INSERT INTO `users`
 VALUES 
 ('john' , '{bcrypt}$2a$10$/S//WtL0VWd5QlWdxJWzm.nXRaeCB4rhqed9CuhHhmcU2PHQ3AOh6' , 1 ),
 ('mary' , '{bcrypt}$2a$10$/S//WtL0VWd5QlWdxJWzm.nXRaeCB4rhqed9CuhHhmcU2PHQ3AOh6' , 1 ),
 ('susan' , '{bcrypt}$2a$10$/S//WtL0VWd5QlWdxJWzm.nXRaeCB4rhqed9CuhHhmcU2PHQ3AOh6' , 1 );
 
 --
 -- Table structure for table `authorities`
 --
 
 

CREATE TABLE `authorities`(
	`username` varchar(50) NOT NULL,
    `authority` varchar(50) NOT NULL,
    
    unique KEY `authorities_idx_1` ( `username` , `authority` ),
    
    CONSTRAINT `authorities_ibfk_1`
    FOREIGN KEY ( `username` )
    REFERENCES `users` ( `username` )
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Data for table `authorities`
--

 INSERT INTO `authorities`
 VALUES 
 ('john' , 'ROLE_EMPLOYEE' ),
 ('mary' , 'ROLE_EMPLOYEE' ),
 ('mary' , 'ROLE_MANAGER' ),
  ('susan' , 'ROLE_EMPLOYEE' ),
 ('susan' , 'ROLE_MANAGER' ),
 ('susan' , 'ROLE_ADMIN' );
 
 
 

