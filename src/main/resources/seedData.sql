INSERT INTO cba_sem3project.role (name) VALUES ('User');
INSERT INTO cba_sem3project.role (name) VALUES ('Admin');
INSERT INTO cba_sem3project.user (username, password, salt) VALUES ('Adam', '498686f70928c6f3dfd4e5288bf38b7b0ca789a5e933f6893c5398fbca8452e4', '320927329287754881270722666944861037179');
INSERT INTO cba_sem3project.user_role (user_id, role_id) VALUES (1, 1);