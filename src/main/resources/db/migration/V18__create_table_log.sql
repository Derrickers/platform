create table log
(
	id int auto_increment,
	account varchar(50) null,
	username varchar(50) null,
	description varchar(50) null,
	ip varchar(20) null,
	gmt_create varchar(30) null,
	constraint log_pk
		primary key (id)
);

