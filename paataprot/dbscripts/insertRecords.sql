insert into userprofiles(id,display_name,password,user_name) values(1,'ArunKrish','123','ramlabs');
insert into userprofiles(id,display_name,password,user_name) values(2,'Ravikant','1234','ravikant');
insert into roles(id,role_name) values(1,'moderator');
insert into user_role(role_id,user_id) values(1,1);
insert into roles(id,role_name) values(2,'admin');
insert into user_role(role_id,user_id) values(2,2);