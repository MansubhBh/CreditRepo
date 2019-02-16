create database if not exists creditscore;

use creditscore;

create table if not exists offer(
    id INT primary key auto_increment,
    bank text,
    interest decimal(9,2),
    `type` text,
    `limit` decimal(9,2),
     product_name text,
     rewards text,
     balance_transfer decimal(9,2),
     yearly_fee decimal(9,2)
);


create table if not exists login(
    username varchar(100) primary key,
    password varchar(100)
);

insert into login(username, password) values('admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918');
insert into login(username, password) values('user', '04f8996da763b7a969b1028ee3007569eaf3a635486ddab211d512c85b9df8fb');


