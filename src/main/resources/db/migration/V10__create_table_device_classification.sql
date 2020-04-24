create table device_classification
(
	id int auto_increment,
	classification varchar(30) null,
	classification_code varchar(20) null,
	level int null,
	constraint device_classification_pk
		primary key (id)
);

