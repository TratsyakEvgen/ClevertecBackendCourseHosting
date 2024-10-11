create table users
(
    id       bigserial primary key,
    nickname varchar(255) not null,
    name     varchar(255) not null,
    email    varchar(255) not null
);

create table categories
(
    id   bigserial primary key,
    name varchar(255) not null
);

create table languages
(
    id   bigserial primary key,
    name varchar(255) not null
);

create table channels
(
    id          bigserial primary key,
    name        varchar(255) not null,
    description varchar(255) not null,
    user_id     bigint
        constraint fk_user_id references users (id),
    create_date date         not null,
    language_id bigint       not null
        constraint fk_language_id references languages (id),
    category_id bigint       not null
        constraint fk_category_id references categories (id),
    image       varchar(255) not null
);

create table subscribers
(
    user_id    bigint
        constraint fk_user_id references users (id),
    channel_id bigint
        constraint fk_channel_id references channels (id),
    primary key (user_id, channel_id)
)
