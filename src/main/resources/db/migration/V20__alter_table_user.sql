alter table user change username account varchar(20) null;

alter table user drop column mg_state;

alter table user change role_name username varchar(20) null;

