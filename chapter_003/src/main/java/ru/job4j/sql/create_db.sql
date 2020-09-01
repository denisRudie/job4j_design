create database users;

--create tables

create table roles (
	id serial primary key,
	name varchar(100)	
);

create table rules (
	id serial primary key,
	name varchar(100)	
);

create table roles_rules (
	id serial primary key,
	role_id bigint references roles(id),
	rule_id bigint references rules(id)
);

create table users(
	id serial primary key,
	first_name varchar(100),
	last_name varchar(100),
	role_id bigint references roles(id)
);

create table category (
	id serial primary key,
	name varchar(100)	
);

create table state (
	id serial primary key,
	name varchar(100)	
);

create table item (
	id serial primary key,
	name varchar(100),
	user_id bigint references users(id),
	category_id bigint references category(id),
	state_id bigint references state(id)
);

create table comments (
	id serial primary key,
	text varchar(10000),
	item_id bigint references item(id)
);

create table attachs (
	id serial primary key,
	name varchar(100),
	item_id bigint references item(id)
);

--insert data

insert into roles (name) values ('admin');

insert into rules (name) values 
	('create'),
	('delete'),
	('update');

insert into roles_rules (role_id, rule_id) values
	(1, 1),
	(1, 2),
	(1, 3);

insert into users (first_name, last_name, role_id) values 
	('denis', 'rudie', 1),
	('admin', 1);

insert into category (name) values ('issue');

insert into state (name) values ('new');

insert into item (name, user_id, category_id, state_id) values 
	('issue1', 1, 1, 1),
	('issue2', 2, 1, 1),
	('issue3', 1, 1, 1),
	('issue4', 2, 1, 1);

insert into "comments" (text, item_id) values
	('comm1', 3),
	('comm2', 3),
	('comm1', 4),
	('comm2', 4),
	('comm1', 5),
	('comm2', 5),
	('comm1', 6),
	('comm2', 6);

insert into attachs (name, item_id) values
	('attch1', 3),
	('attch2', 3),
	('attch1', 4),
	('attch2', 4),
	('attch1', 5),
	('attch2', 5),
	('attch1', 6),
	('attch2', 6);