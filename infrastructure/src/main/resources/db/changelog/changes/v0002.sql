create table "employee"
(
    id            bigserial                   not null,
    name          varchar(255)                not null,
    age           numeric                     not null,
    joined_date   timestamp without time zone not null,
    created       timestamp without time zone not null,
    department_id int,
    primary key (id),
    constraint fk_department
        foreign key (department_id)
            references department (id)

);