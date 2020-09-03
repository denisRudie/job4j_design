--1. Написать запрос получение всех продуктов с типом "СЫР"
select p."name", p.expired_date, p.price, t."name" from product p
left join "type" t on p.type_id = t.id 
where t."name" = 'СЫР';

--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
select "name", expired_date, price from product
where "name" like '%мороженное%';

--3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
select "name", expired_date, price from product p 
where expired_date between now() and now() + interval '1 month';

--4. Написать запрос, который выводит самый дорогой продукт.
select * from product p
where p.price = (select max(price) from product p2);

--5. Написать запрос, который выводит количество всех продуктов определенного типа.
select count(p.id) from product p 
left join "type" t on p.type_id = t.id 
where t."name" = 'СЫР';

--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select p.* from product p 
left join "type" t on p.type_id = t.id 
where t."name" in ('СЫР', 'МОЛОКО');

--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.  
select * from (select t."name", count(p.id) from product p 
left join "type" t on p.type_id = t.id
group by t."name") as c
where c.count < 10;

--8. Вывести все продукты и их тип.
select p.id, p.name, t."name", p.expired_date, p.price from product p 
left join "type" t on p.type_id = t.id;