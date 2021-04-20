create table admins(
    id bigserial not null constraint admins_pkey primary key,
    email varchar(255) not null,
    password varchar(255) not null,
    last_login_date timestamp
);

insert into admins(email, password, last_login_date)
values ('anton@test.com', 'pass', now());

insert into admins(email, password, last_login_date)
values ('artem@test.com', 'pass', now());

insert into admins(email, password, last_login_date)
values ('vitaliy@test.com', 'pass', now());

insert into admins(email, password, last_login_date)
values ('lisa@test.com', 'pass', now());

insert into admins(email, password, last_login_date)
values ('xenia@test.com', 'pass', now());