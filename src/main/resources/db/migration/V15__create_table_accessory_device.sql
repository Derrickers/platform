create table accessory_device
(
	id int auto_increment,
	affiliation varchar(50) null,
	affiliate_device varchar(20) null,
	accessory_name varchar(20) null,
	accessory_type varchar(20) null,
	constraint accessory_device_pk
		primary key (id)
);

