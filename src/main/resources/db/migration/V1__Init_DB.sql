create sequence hibernate_sequence start 2 increment 1;
create table country
(
    cname      varchar(50) not null,
    population int8        not null,
    primary key (cname)
);
create table discover
(
    cname          varchar(255) not null,
    disease_code   varchar(255) not null,
    date           varchar(255),
    first_enc_date date         not null,
    primary key (cname, disease_code)
);
create table disease
(
    id                 int4         not null,
    accredited         boolean,
    author_email       varchar(255),
    description        varchar(140) not null,
    disease_code       varchar(50)  not null,
    last_modified_by   varchar(255),
    pathogen           varchar(20)  not null,
    reviewer_email_one varchar(255),
    reviewer_email_two varchar(255),
    primary key (id)
);
create table doctor
(
    email  varchar(255) not null,
    degree varchar(20)  not null,
    primary key (email)
);
create table publicservant
(
    email      varchar(255) not null,
    department varchar(50)  not null,
    primary key (email)
);
create table record
(
    cname          varchar(255) not null,
    disease_code   varchar(255) not null,
    email          varchar(255) not null,
    total_deaths   int4         not null,
    total_patients int4         not null,
    primary key (cname, disease_code, email)
);
create table reviewing
(
    id                 int4         not null,
    accredited         boolean,
    action             varchar(255),
    author_email       varchar(255),
    description        varchar(140) not null,
    disease_code       varchar(50)  not null,
    original_id        int4,
    pathogen           varchar(20)  not null,
    reviewer_email_one varchar(255),
    reviewer_email_two varchar(255),
    primary key (id)
);
create table roles
(
    id    int4 not null,
    role  varchar(255),
    email varchar(255),
    primary key (id)
);
create table users
(
    email    varchar(60) not null,
    cname    varchar(255),
    name     varchar(30) not null,
    password varchar(255),
    phone    varchar(20) not null,
    salary   int4        not null,
    surname  varchar(40) not null,
    primary key (email)
);
