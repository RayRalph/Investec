CREATE SCHEMA IF NOT EXISTS assessment_schema AUTHORIZATION sa;
CREATE  TABLE  IF NOT EXISTS  assessment_schema.Client (
  id INT NOT NULL AUTO_INCREMENT,
  FirstName VARCHAR(45) NOT NULL ,
  LastName VARCHAR(45) NOT NULL ,
  MobileNumber VARCHAR(45) NULL ,
  IDNumber VARCHAR(45) NOT NULL ,
  PhysicalAddress VARCHAR(255) NULL ,
  PRIMARY KEY (id) ,
  UNIQUE INDEX MobileNumber_UNIQUE (MobileNumber ASC) ,
  UNIQUE INDEX IDNumber_UNIQUE (IDNumber ASC) );
