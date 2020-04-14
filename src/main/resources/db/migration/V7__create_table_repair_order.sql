create table repair_order
(
	id int auto_increment,
	affiliation varchar(50) null,
	order_index varchar(30) null,
	type varchar(10) null,
	device_name varchar(20) null,
	description varchar(255) null,
	server varchar(50) null,
	status varchar(10) null,
	repair_member varchar(255) null,
	value_added int null,
	constraint repair_orders_pk
		primary key (id)
);

