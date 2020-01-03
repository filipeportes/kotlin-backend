CREATE TABLE Contact (
   id bigint auto_increment primary key,
   name VARCHAR(255) NOT NULL,
   email VARCHAR(255),
   phone VARCHAR(64),
   creation_date DATE default now()
);