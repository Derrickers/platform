create table user
(
	id int auto_increment unique primary key,
	rid int null,
	user_name varchar(20) null,
	mobile varchar(11) null,
	email varchar(50) null,
	gmt_create bigint null,
	gmt_modify bigint null
);

