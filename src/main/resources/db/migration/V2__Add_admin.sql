insert into country(cname, population)
values ('Canada', 38516518),
       ('USA', 332403650),
       ('India', 1411792127),
       ('Kazakhstan', 19301735),
       ('Israel', 8972002),
       ('UK', 68719355 ),
       ('Germany', 84406260),
       ('Japan', 125572230),
       ('South Korea', 51372263),
       ('China', 1452326258),
       ('Sweden', 10246435);
insert into users (email,cname,name,password,phone,salary,surname)
values ('a1@gmail.com','Kazakhstan','admin','$2a$10$HU8ZHn.Mi13JFzsmqS5Iqu6QHNFPMK99mVM4euKGhDyictts5zmsW','77777777777',1,'admin');
insert into roles (id,email,role) values (1,'a1@gmail.com','ADMIN');