create table detect_order
(
	id int auto_increment,
	affiliation varchar(50) null,
	order_index varchar(30) null,
	detect_date date null,
	server varchar(50) null,
	detect_member varchar(255) null,
	relate_order varchar(30) null,
	remark varchar(255) null,
	constraint detect_orders_pk
		primary key (id)
);

