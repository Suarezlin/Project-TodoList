create table if not exists todo (
    id varchar(255) not null primary key,
    content text not null,
    date date not null,
    status tinyint not null
);