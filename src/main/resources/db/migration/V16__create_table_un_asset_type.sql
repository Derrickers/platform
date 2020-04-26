create table un_asset_type
(
	id int auto_increment,
	device_type varchar(20) null,
	un_asset_type_index varchar(20) null,
	un_asset_type_name varchar(20) null,
	constraint un_asset_type_pk
		primary key (id)
);

