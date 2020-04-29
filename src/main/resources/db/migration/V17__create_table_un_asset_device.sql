create table un_asset_device
(
	id int auto_increment,
	affiliation varchar(20) null,
	un_asset_type varchar(20) null,
	count int null,
	unit varchar(10) null,
	specification varchar(50) null,
	constraint un_asset_device_pk
		primary key (id)
);

