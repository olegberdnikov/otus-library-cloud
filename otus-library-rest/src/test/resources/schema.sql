create table author(
author_id bigint not null,
name varchar(255) not null,
surname varchar(255) not null,
name2 varchar(255),
constraint author_id_key primary key (author_id)
);

create table category(
category_id bigint not null,
name varchar(255) not null,
constraint category_id_key primary key (category_id)
);

create table book (
book_id bigint not null,
name varchar(255) not null,
category_id int not null,
constraint book_id_key primary key (book_id),
foreign key (category_id) references category (category_id)
);

create table comment (
comment_id bigint not null,
text varchar(255) not null,
created datetime not null,
book_id bigint not null,
constraint comment_id_key primary key (comment_id),
foreign key (book_id) references book (book_id)
);

create table book_author(
book_id bigint not null,
author_id bigint not null,
primary key(book_id ,author_id),
foreign key (book_id) references book (book_id),
foreign key (author_id) references author (author_id)
);

create sequence book_seq start with 100;
create sequence author_seq start with 100;
create sequence comment_seq start with 100;
create sequence category_seq start with 100;