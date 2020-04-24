create table device_asset_goods
(
	id int auto_increment,
	affiliation varchar(50) null,
	device_name varchar(50) null,
	asset_code varchar(20) null,
	classification varchar(50) null,
	count int null,
	manufacture varchar(50) null,
	specification varchar(50) null,
	product_date date null,
	use_date date null,
	service_life int null,
	warranty_date date null,
	storage_location varchar(20) null,
	storage_charge varchar(10) null,
	status varchar(10) null,
	constraint device_asset_goods_pk
		primary key (id)
);

