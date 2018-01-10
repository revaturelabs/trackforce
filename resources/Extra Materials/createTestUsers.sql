INSERT INTO admin.tf_role(tf_role_id, tf_role_name) VALUES(1, 'Admin');
INSERT INTO admin.tf_role(tf_role_id, tf_role_name) VALUES(2, 'Manager');
INSERT INTO admin.tf_role(tf_role_id, tf_role_name) VALUES(3, 'Vice President');

INSERT INTO admin.tf_user(tf_user_id, tf_username, tf_hashpassword, tf_role_id)
VALUES(1,   'TestAdmin',   'sha1:64000:18:zBfcx3rxxYev6SuYjw/EoTzwwhDW0+5I:TE/5QDShUo2DpVtwM1wfpnmD', 1);

INSERT INTO admin.tf_user(tf_user_id, tf_username, tf_hashpassword, tf_role_id)
VALUES(2,   'TestManager', 'sha1:64000:18:/fW6R/5plhg/bmBGJHwZ706zkWS3+gu2:G1DIcP3u0KXQnzXBsL/6zdjj', 2);

INSERT INTO admin.tf_user(tf_user_id, tf_username, tf_hashpassword, tf_role_id)
VALUES(3,   'TestVicePresident',   'sha1:64000:18:BId387PD/2CaEcCaISnf6GNHcfdSFM9B:nkKS54PqDi5hSoJa+6sjxW85', 3);

COMMIT;
