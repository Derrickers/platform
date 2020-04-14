create table device_error_code
(
	id int auto_increment,
	classification varchar(20) null,
	error_code varchar(20) null,
	description varchar(50) null,
	create_time date null,
	constraint device_error_code_pk
		primary key (id)
);

