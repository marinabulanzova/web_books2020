INSERT INTO Authors /*(name)*/ VALUES 
	(default , 'Лев Толстой'),
	(default, 'Стивен Кинг'),
	(default, 'Николай Чернышевский'),
	(default, 'Дарья Донцова'),
	(default, 'Александр Блок'),
	(default, 'Касюн Таками'),
	(default, 'Анна Орлова'),
	(default, 'Юрий Венигоров'),
	(default, 'Светлана Егорова');


INSERT INTO Books /*(genre, title, publishing_house, publication_year, page_count, count_book, cover, price)*/ VALUES
	(default, 'классика', 'Война и мир', 'Просвещение', 2012, 1560, 12, 'Твёрдая', 1700.0),
	(default, 'ужасы', 'Оно', 'АСТ', 2015, 1242, 5, 'Мягкая', 950.0),
	(default, 'классика', 'Что делать?', 'Азбука', 2012, 426, 1, 'Твёрдая', 400.0),
	(default, 'фантастика', 'Наука и проклятия', 'Миф', 2019, 456, 2, 'Твёрдая_тканевая', 560.0),
	(default, 'детектив', 'Вынос дела', 'Эксмо', 2016, 245, 10, 'Кожаная', 360.0),
	(default, 'триллер', 'Королевская битва', 'Амфора', 2016, 629, 7, 'Твёрдая', 730.0),
	(default, 'поэзия', 'Лирика', 'Стрекоза', 2018, 288, 8, 'Твёрдая_тканевая', 270.0),
	(default, 'научная литература', 'Элементы математики в физике', 'Просвещение', 2015, 345, 15, 'Твёрдая', 630.0);

INSERT INTO Books_authors VALUES 
	(default, 1, 1),
	(default, 2, 2),
	(default, 3, 3),
	(default, 4, 7),
	(default, 5, 4),
	(default, 6, 6),
	(default, 7, 5),
	(default, 8, 8),
	(default, 8, 9);

INSERT INTO Users /*(surname, first_name, patronymic, address, phone_number, e_mail, password_hash, user_right)*/ VALUES 
	(default, 'Матвеев', 'Даниил', 'Олегович', 'г.Москва, Лермонтовский проспект, д.13, к.56', '89507899067', 'matveev_d@mail.ru', 'drgdrh', true),
	(default, 'Митин', 'Виктор', 'Владимирович', 'г.Москва, Ломоносовский проспект, д.27, к.11', '89779347105', 'mitin_v@mail.ru', 'dgdrgaetes', default),
	(default, 'Кометова', 'Дарья', 'Максимовна', 'г.Москва, ул.Ленина, д.4, к.128', '89507895634','kometova_d@mail.ru', 'drgyhruw', default),
	(default, 'Денин', 'Борис', 'Ильич', 'г.Москва, ул.Вавилова, д.103, к.26', '89003456704', 'denin_b@mail.ru', 'sgtrs', default),
	(default, 'Огородникова', 'Валерия', 'Станиславовна', 'г.Москва, ул.Гагарина, д.56, к.17', '89022345676', 'ogorodnikova_a@mail.ru', 'dsrgedsha', default),
	(default, 'Межукова', 'Валерия', 'Игоревна', 'г.Москва, Ломоносовский проспект, д.103, к.1', '89087887005', 'mezhukova_v@mail.ru', 'esgrshfc', default),
	(default, 'Костина', 'Екатерина', 'Андреевна', 'г.Москва, ул.Новый Арбат, д.48, к.56', '89082405686', 'kostina_e@mail.ru', 'ergesdh', default);

INSERT INTO Orders /*(id_customer, delivery_address, payment_card, order_date, delivery_date, status, delivery_price)*/ VALUES
	(default, 2, 'г.Москва, Ломоносовский проспект, д.27, к.11', true, '23/01/2020 10:30:06', '26/01/2020', 'доставлен', 0),
	(default, 2, 'г.Москва, ул.Вавилова, д.43, к.107 ', true, '18/02/2020 11:30:06', '27/03/2020', 'в обработке', 0),
	(default, 1, 'г.Москва, Лермонтовский проспект, д.13, к.56', true, '19/12/2020 12:30:06', '26/03/2020', 'собран', 0),
	(default, 6, 'г.Москва, Мичуринский проспект, д.103, к.1',  false, '20/02/2020 13:30:06', '27/03/2020', 'в пути', 0),
	(default, 3, 'г.Москва, ул.Ленина, д.4, к.128', false, '29/01/2020 14:30:06', '01/02/2020', 'доставлен', 0),
	(default, 5, 'г.Тула, ул.Гагарина, д.56, к.17', false, '19/02/2020 15:30:06', '27/03/2020', 'в обработке', 100),
	(default, 4, 'г.Москва, ул.Вавилова, д.103, к.26', true, '30/01/2020 16:30:06', '05/03/2020', 'доставлен', 0);

INSERT INTO Order_basket VALUES 
	(default, 1, 2, 950, 3),
	(default, 1, 3, 400, 1),
	(default, 3, 1, 1600, 1),
	(default, 4, 5, 360, 2),
	(default, 5, 7, 270, 1),
	(default, 2, 5, 360, 1),
	(default, 6, 7, 250, 2),
	(default, 3, 2, 950, 1),
	(default, 7, 4, 560, 1);

INSERT INTO Basket_customer VALUES 
	(default, 1, 3, 2),
	(default, 2, 3, 1),
	(default, 2, 1, 1);


