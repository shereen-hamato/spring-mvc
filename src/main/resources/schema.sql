CREATE TABLE ITEM_CATALOG (
id INTEGER  PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(255) ,
description VARCHAR (255),
image_path VARCHAR (255)
);

CREATE TABLE ITEM (
id int PRIMARY KEY AUTO_INCREMENT,
name VARCHAR (255),
description VARCHAR (255),
price DOUBLE ,
duration VARCHAR (255),
item_catalog_id int,
image_path VARCHAR (500),
foreign key (item_catalog_id) references ITEM_CATALOG(id)
);