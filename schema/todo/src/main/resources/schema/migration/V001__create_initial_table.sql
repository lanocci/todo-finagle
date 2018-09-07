CREATE TABLE users(
  id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  account VARCHAR(255),
  email VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
CREATE TABLE todos(
  id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  title VARCHAR(255),
  completed BOOLEAN,
  user_id INT not null ,
  CONSTRAINT `fk_todo_user_id` FOREIGN KEY(user_id) references users(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;