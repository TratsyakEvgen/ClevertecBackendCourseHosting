insert into languages(name) values ('русский');
insert into languages(name) values ('english');

insert into categories(name) values ('наука');
insert into categories(name) values ('развлечения');

insert into users(nickname, name, email) values ('Vasya', 'Василий', 'vasya@gamil.com');
insert into users(nickname, name, email) values ('Sergey', 'Сергей', 'sergey@gamil.com');
insert into users(nickname, name, email) values ('Petya123', 'Петя', 'petya123@gamil.com');
insert into users(nickname, name, email) values ('Tanya', 'Таня', 'tanya@gamil.com');

insert into channels(name, description, user_id, create_date, language_id, category_id, image)
values ('Наука', 'Канал о науке', 1, '2023-11-29', 1, 1, 'https://science.com');
insert into channels(name, description, user_id, create_date, language_id, category_id, image)
values ('Some name', 'Some description', 4, '2022-10-30', 2, 2, 'https://some-image.com');

insert into subscribers values (2,1);
insert into subscribers values (3,1);
insert into subscribers values (4,1);
insert into subscribers values (1,2);
insert into subscribers values (2,2);