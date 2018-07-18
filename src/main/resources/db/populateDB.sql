DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);
INSERT INTO meals(date_time, description, calories, user_id) VALUES
  ('2018-05-30 09:00', 'User breakfast', 200, 100000),
  ('2018-05-30 12:00:01', 'User dinner', 700, 100000),
  ('2018-05-31 19:30', 'User supper', 1500, 100000),
  ('2018-05-31 22:00', 'User lite supper', 700, 100000),
  ('2018-05-31 10:20', 'User heavy lunch', 1700, 100000),



  ('2018-05-30 09:00', 'Admin breakfast', 100, 100001),
  ('2018-05-30 11:45', 'Admin lunch', 200, 100001),
  ('2018-05-30 17:34', 'Admin dinner', 1501, 100001),
  ('2018-05-30 21:17:02', 'Admin late dinner', 200, 100001),
  ('2018-05-31 10:56', 'Admin late breakfast', 170, 100001),
  ('2018-05-31 14:18', 'Admin  late lunch', 216, 100001),
  ('2018-05-31 19:51', 'Admin lite supper', 17, 100001),
  ('2018-05-31 22:03', 'Admin supper', 190, 100001) ;

