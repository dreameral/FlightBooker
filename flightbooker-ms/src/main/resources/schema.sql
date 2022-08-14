create table if not exists users (
    id serial primary key,
    username varchar (50) unique not null ,
    password varchar (200) not null,
    user_role varchar (10) not null
    );
create table if not exists segments (
    id serial primary key,
    departure varchar (10) not null,
    destination varchar (10) not null,
    airline varchar (20) not null
    );
create table if not exists flight_requests (
    id serial primary key,
    departure varchar (10) not null ,
    destination varchar (10) not null,
    departure_time timestamp not null,
    status varchar (10) not null,
    requested_by_id int not null,
    route varchar not null,
    foreign key (requested_by_id) references users (id)
    );
create table if not exists notifications (
    id serial primary key,
    content varchar (100) not null,
    is_read boolean not null,
    user_id int not null,
    foreign key (user_id) references users (id)
    );