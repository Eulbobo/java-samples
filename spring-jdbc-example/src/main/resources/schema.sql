CREATE TABLE users (
  id         INTEGER PRIMARY KEY,
  name VARCHAR(30),
  email  VARCHAR(50)
);

CREATE TABLE users_auto (
  id         INTEGER PRIMARY KEY auto_increment,
  name VARCHAR(30),
  mail  VARCHAR(50)
);