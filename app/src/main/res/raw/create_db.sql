


CREATE TABLE ration (
	id integer PRIMARY KEY AUTOINCREMENT,
	name varchar,
	date datetime
);

CREATE TABLE dish (
	id integer PRIMARY KEY AUTOINCREMENT,
	name varchar,
	type integer,
	calories integer,
	date datetime
);

CREATE TABLE sport (
	id integer PRIMARY KEY AUTOINCREMENT,
	type integer,
	measure_type varchar,
	measure_value integer,
	date datetime
);

CREATE TABLE dish_type (
	id integer PRIMARY KEY AUTOINCREMENT,
	name varchar
);

CREATE TABLE sport_type (
	id integer PRIMARY KEY AUTOINCREMENT,
	name varchar
);

CREATE TABLE dish_ref (
	id integer PRIMARY KEY AUTOINCREMENT,
	composite_dish_id integer,
	dish_to_consist_of_id integer,
	kg_amount integer
);

CREATE TABLE dish_ration (
	id integer PRIMARY KEY AUTOINCREMENT,
	dish_id integer,
	ration_id integer,
	kg_amount integer
);
