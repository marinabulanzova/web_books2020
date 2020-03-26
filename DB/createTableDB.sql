set DATESTYLE to "DMY";

-- "Авторы" -- 
CREATE TABLE IF NOT EXISTS Authors  (
  id_author serial primary key,
  name varchar(60) not null
);

-- "Книги" --
CREATE TABLE IF NOT EXISTS Books (
  id_book serial primary key, -- номер книги
  genre varchar(30) not null, -- жанр
  title varchar(50) not null, -- название
  publishing_house varchar(30) not null, -- издательство
  publication_year int not null, -- год публикации
  page_count int not null, -- количество страниц
  count_book int not null, -- количество экземпляров
  cover varchar(20) not null, -- вид обложки
  price numeric(6,2) not null -- цена
);

-- "Авторы книг" -- 
CREATE TABLE IF NOT EXISTS Books_authors (
  id serial primary key,
  id_book int not null, -- книга
  id_author int not null -- автор
);

-- "Клиенты" --
CREATE TABLE IF NOT EXISTS Users (
  id_user serial primary key, -- номер клиента
  surname varchar(20), -- фамилия
  first_name varchar(20) not null,-- имя
  patronymic varchar(20), -- отчество 
  address varchar(100), -- адрес
  phone_number varchar(11) unique not null, -- номер телефона
  e_mail varchar(30) unique not null, -- адрес электронной почты
  password_hash varchar(50) not null, -- хэш пароля
  admin boolean default false
);

-- "Корзина клиента" -- 
CREATE TABLE IF NOT EXISTS Basket_customer (
  id serial primary key,
  id_book int not null, -- книга
  id_customer int not null, -- клиент
  count_book int not null -- количество экземпляров
);

-- "Заказы" --
CREATE TABLE IF NOT EXISTS Orders (
  id_order serial primary key, -- номер заказа
  id_customer int not null, -- номер клиента
  delivery_address varchar(100), -- адрес доставки
  payment_card boolean default false, -- оплата картой
  order_date timestamp not null, -- дата заказа
  delivery_date date not null, -- дата доставки
  status varchar(15) not null, -- текущий статус
  delivery_price numeric(6,2) default 0 -- стоимость доставки
);

-- "Составляющие заказа" --
CREATE TABLE IF NOT EXISTS Order_basket (
  id serial primary key,
  id_order int not null, -- номер заказа
  id_book int not null, -- номер книги
  count_book int not null, -- количество книг
  price numeric(6,2) not null -- цена на момент заказа
);

ALTER TABLE Orders ADD
	foreign key (id_customer)
	references Users(id_user) on delete set null;

ALTER TABLE Order_basket ADD 
	foreign key (id_book)
	references Books(id_book) on delete set null,
    ADD
	foreign key (id_order) 
	references Orders(id_order) on delete cascade ;

ALTER TABLE Books_authors ADD 
	foreign key (id_book)
	references Books(id_book) on delete cascade,
    ADD
	foreign key (id_author) 
	references Authors(id_author) on delete cascade;

ALTER TABLE Basket_customer ADD 
	foreign key (id_book)
	references Books(id_book) on delete cascade,
    ADD
	foreign key (id_customer) 
	references Users(id_user) on delete cascade;


