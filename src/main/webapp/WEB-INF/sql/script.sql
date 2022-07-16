drop database internet_provider_base;
create database internet_provider_base;
use internet_provider_base;



create table users
(
    id       int(10) auto_increment not null,
    phone    varchar(16)  not null unique,
    password varchar(100) not null,
    isActive boolean   default true,
    role     varchar(10),
    created  timestamp default now(),
    updated  timestamp default now() on update NOW(),
    primary key (id)
);



create table customers
(
    id       int(10) auto_increment not null,
    name     varchar(25) not null,
    surname  varchar(25) not null,
    phone    varchar(13) not null unique,
    email    varchar(25) not null unique,
    isActive boolean   default true,
    created  timestamp default now(),
    updated  timestamp default now() on update NOW(),
    balance  int(10) default null,
    primary key (id)
);


create table tariffs
(
    id            int(10) auto_increment not null,
    name          varchar(25) not null unique,
    service_type  enum ('TV','INTERNET','IPTV','MOBILE') not null,
    price_per_day int(10) not null ,
    isActive      boolean   default true,
    created       timestamp default now(),
    updated       timestamp default now() on update NOW(),
    primary key (id)
);



create table subscriptions
(
    id           int(10) auto_increment not null,
    name         varchar(25)            not null unique,
    isActive     boolean   default true,
    created      timestamp default now(),
    updated      timestamp default now() on update NOW(),
    primary key (id),
    customers_id int(10),
    foreign key (customers_id) references customers (id)
);



create table subscriptions_tariffs
(
    subscription_id int(10) not null,
    tariff_id int(10) not null,
    primary key (subscription_id, tariff_id),
    foreign key (subscription_id) references subscriptions (id),
    foreign key (tariff_id) references tariffs (id)
);

create table customers_tariffs
(
    customers_id int(10) not null,
    tariffs_id   int(10) not null,
    primary key (customers_id, tariffs_id),
    foreign key (customers_id) references customers (id),
    foreign key (tariffs_id) references tariffs (id)
);

-- CREATE USERS

insert into users (phone,password,isActive,role,created,updated)values('+380955992668','17121983',true,'ADMIN',now(),now());
insert into customers(name,surname, phone,email,balance) values ('Eduard','Vitko','+380955992668','eduardvitko@gmail.com',0);

insert into users (phone,password,isActive,role,created,updated)values('+380667475521','21101984',true,'CLIENT',now(),now());
insert into customers(name,surname, phone,email,balance) values ('Anna','Kutsinina','+380667475521','annakut@gmail.com',0);

insert into users (phone,password,isActive,role,created,updated)values('+380632220085','28101962',true,'CLIENT',now(),now());
insert into customers(name,surname, phone,email,balance) values ('Antonina','Sivash','+380632220085','sivash@gmail.com',0);

insert into users (phone,password,isActive,role,created,updated)values('+380447521430','01011981',true,'CLIENT',now(),now());
insert into customers(name,surname, phone,email,balance) values ('Yuriy','Mischenko','+380447521430','yumich@gmail.com',0);

insert into users (phone,password,isActive,role,created,updated)values('+380664956273','08071963',true,'CLIENT',now(),now());
insert into customers(name,surname, phone,email,balance) values ('Oleg','Chubko','+380664956273','chub@gmail.com',0);

-- CREATE SERVISES

insert into subscriptions(name,isActive,created, updated) values ('INTERNET',true,now(),now());
insert into subscriptions(name,isActive,created, updated) values ('MOBILE',true,now(),now());
insert into subscriptions(name,isActive,created, updated) values ('IPTV',true,now(),now());
insert into subscriptions(name,isActive,created, updated) values ('TV',true,now(),now());

-- CREATE TARIFFS

insert into tariffs(name,service_type,price_per_day,isActive) values ('NO LIMITS','INTERNET',450,true);
insert into tariffs(name,service_type,price_per_day,isActive) values ('NIGHT','INTERNET',299,true);
insert into tariffs(name,service_type,price_per_day,isActive) values ('STUDENTS','INTERNET',99,true);
insert into tariffs(name,service_type,price_per_day,isActive) values ('DAY','INTERNET',349,true);
insert into tariffs(name,service_type,price_per_day,isActive) values ('LIGHT','INTERNET',199,true);

insert into tariffs(name,service_type,price_per_day,isActive) values ('BASE','MOBILE',119,true);
insert into tariffs(name,service_type,price_per_day,isActive) values ('CORPORATION','MOBILE',200,true);
insert into tariffs(name,service_type,price_per_day,isActive) values ('ROAMING','MOBILE',145,true);
insert into tariffs(name,service_type,price_per_day,isActive) values ('STUDY','MOBILE',102,true);
insert into tariffs(name,service_type,price_per_day,isActive) values ('WORK','MOBILE',179,true);

insert into tariffs(name,service_type,price_per_day,isActive) values ('WORLD','IPTV',79,true);
insert into tariffs(name,service_type,price_per_day,isActive) values ('HOUSE','IPTV',100,true);
insert into tariffs(name,service_type,price_per_day,isActive) values ('COMEDY','IPTV',115,true);
insert into tariffs(name,service_type,price_per_day,isActive) values ('ADVENTURES','IPTV',105,true);

insert into tariffs(name,service_type,price_per_day,isActive) values ('STANDARD','TV',65,true);
insert into tariffs(name,service_type,price_per_day,isActive) values ('FOOTBALL+','TV',230,true);
insert into tariffs(name,service_type,price_per_day,isActive) values ('SPORTS+','TV',119,true);
insert into tariffs(name,service_type,price_per_day,isActive) values ('CINEMA+','TV',159,true);
insert into tariffs(name,service_type,price_per_day,isActive) values ('ANIMALS+','TV',89,true);

insert into subscriptions_tariffs(subscription_id,tariff_id)values (1, 1);
insert into subscriptions_tariffs(subscription_id,tariff_id)values (1, 2);
insert into subscriptions_tariffs(subscription_id,tariff_id)values (1, 3);
insert into subscriptions_tariffs(subscription_id,tariff_id)values (1, 4);
insert into subscriptions_tariffs(subscription_id,tariff_id)values (1, 5);
insert into subscriptions_tariffs(subscription_id,tariff_id)values (2, 6);
insert into subscriptions_tariffs(subscription_id,tariff_id)values (2, 7);
insert into subscriptions_tariffs(subscription_id,tariff_id)values (2, 8);
insert into subscriptions_tariffs(subscription_id,tariff_id)values (2, 9);
insert into subscriptions_tariffs(subscription_id,tariff_id)values (2, 10);
insert into subscriptions_tariffs(subscription_id,tariff_id)values (3, 11);
insert into subscriptions_tariffs(subscription_id,tariff_id)values (3, 12);
insert into subscriptions_tariffs(subscription_id,tariff_id)values (3, 13);
insert into subscriptions_tariffs(subscription_id,tariff_id)values (3, 14);
insert into subscriptions_tariffs(subscription_id,tariff_id)values (4, 15);
insert into subscriptions_tariffs(subscription_id,tariff_id)values (4, 16);
insert into subscriptions_tariffs(subscription_id,tariff_id)values (4, 17);
insert into subscriptions_tariffs(subscription_id,tariff_id)values (4, 18);
insert into subscriptions_tariffs(subscription_id,tariff_id)values (4, 19);
