alter table user
	add mg_state bool default true null;

alter table user
	add role_name varchar(10) null;
