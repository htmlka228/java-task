create table users(
    id bigserial not null constraint users_pkey primary key,
    email varchar(255) not null,
    password varchar(255) not null,
    active boolean
);

insert into users(email, password, active)
    values ('drmitriy@test.com', 'pass', true);

insert into users(email, password, active)
values ('sergey@test.com', 'pass', true);

insert into users(email, password, active)
values ('danila@test.com', 'pass', true);

insert into users(email, password, active)
values ('kirill@test.com', 'pass', true);

insert into users(email, password, active)
values ('roman@test.com', 'pass', true);

