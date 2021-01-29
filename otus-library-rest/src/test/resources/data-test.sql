insert into author (author_id, name,surname) values (1, 'Питер','Гамильтон');
insert into author (author_id, name,surname) values (2, 'Адриан','Чайковски');
insert into author (author_id, name,surname,name2) values (3, 'Пурышева','Наталья','Сергеевная');
insert into author (author_id, name,surname,name2) values (4, 'Важеевская','Наталья','Евгеньевна');
insert into category (category_id, name) values (1, 'Научная фантастика');
insert into category (category_id, name) values (2, 'Учебная литература');
insert into book (book_id, name, category_id) values (1, 'Звезда Пандоры',1);
insert into book (book_id, name, category_id) values (2, 'Звёздная дорога',2);
insert into book (book_id, name, category_id) values (3, 'Дети времени',1);
insert into book (book_id, name, category_id) values (4, 'Иуда освобожденный',2);
insert into book_author (book_id, author_id) values (1, 1);
insert into book_author (book_id, author_id) values (2, 1);
insert into book_author (book_id, author_id) values (4, 1);
insert into book_author (book_id, author_id) values (3, 2);
insert into comment (comment_id, text, created,book_id) values (1, 'Комментарий 1 Звезда Пандоры','2019-11-11',1);
insert into comment (comment_id, text, created,book_id) values (2, 'Комментарий 2 Звезда Пандоры','2019-11-11',2);
insert into comment (comment_id, text, created,book_id) values (3, 'Комментарий 1 Звёздная дорога','2019-11-11',3);
insert into comment (comment_id, text, created,book_id) values (4, 'Комментарий 2 Звёздная дорога','2019-11-11',4);