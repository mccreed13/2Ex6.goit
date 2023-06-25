CREATE TABLE if not exists worker
(
    ID INT auto_increment,
    primary key(id),
    NAME VARCHAR(1000) not null,
    CONSTRAINT check_name check (length(name)>2),
    birthday date,
    level varchar(7) not null,
    CONSTRAINT check_level check (level in ('Trainee', 'Junior','Middle', 'Senior')),
    salary int,
    constraint check_salary check (salary>100 and salary<100000),
    constraint check_birthday check (birthday > '1900-01-01')
    );

create table if not exists client(
    id int auto_increment,
    primary key(id),
    name varchar(1000) not null,
    CONSTRAINT check_name_client check (length(name)>2)
);

create table if not exists project(
    id int auto_increment,
    primary key(id),
    client_id int,
    foreign key (client_id) references client(id),
    start_date date,
    finish_date date
);

create table if not exists project_worker(
    project_id int,
    foreign key (project_id) references project(id),
    worker_id int,
    foreign key (worker_id) references worker(id),
    primary key (project_id, worker_id)
);

