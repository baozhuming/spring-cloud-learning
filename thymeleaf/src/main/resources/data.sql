insert into sys_user(id,username,password) values (1,'baozm1','baozm1');
insert into sys_user(id,username,password) values (2,'baozm2','baozm2');

insert into sys_role(id,name) values (1,'ROLE_ADMIN');
insert into sys_role(id,name) values (2,'ROLE_USER');

insert into sys_user_roles(sys_user_id,roles_id) values (1,1);
insert into sys_user_roles(sys_user_id,roles_id) values (2,2);