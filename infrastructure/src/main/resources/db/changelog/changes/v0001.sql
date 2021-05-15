create table "department"
(
    id       bigserial,
    name     varchar(255)                not null,
    category varchar(255)                not null,
    created  timestamp without time zone not null,
    primary key (id)
);