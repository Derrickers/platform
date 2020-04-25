create table accessory_type
(
	id int auto_increment,
	device_type varchar(20) null,
	accessory_type_name varchar(20) null,
	accessory_type_index varchar(20) null,
	constraint accessory_type_pk
		primary key (id)
);

