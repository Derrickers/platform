create table servers_info
(
	id bigint auto_increment,
	identifer varchar(20) null,
	server_name varchar(50) null,
	abbreviation varchar(50) null,
	type int null,
	credit_code int null,
	location varchar(100) null,
	present varchar(10) null,
	registered_capital int null,
	create_time date null,
	manage_range varchar(255) null,
	manage_begintime date null,
	manage_endtime date null,
	registrar varchar(50) null,
	issue_time date null,
	principal varchar(10) null,
	principal_contact_info varchar(50) null,
	leader varchar(10) null,
	leader_contact_info varchar(50) null,
	secure_leader varchar(10) null,
	secure_leader_contact_info varchar(50) null,
	secure_code varchar(50) null,
	secure_begintime date null,
	secure_endtime date null,
	remarks varchar(255) null,
	gmt_create bigint null,
	gmt_modify bigint null,
	constraint servers_info_pk
		primary key (id)
);

