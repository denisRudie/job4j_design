--Список всех машин с наименованиями комплектующих
select c."name", t."name", cb."name", e."name" from car c
inner join transmission t on t.id = c.transmission_id
inner join car_body cb ON cb.id = c.car_body_id
inner join engine e ON e.id = c.engine_id;

--Список коробок передач, которые на используются в машинах
select t."name" from car c
right outer join transmission t on t.id = c.transmission_id
where c.id is null;

--Список кузовов, которые на используются в машинах
select cb."name" from car c
right outer join car_body cb ON cb.id = c.car_body_id
where c.id is null;

--Список двигателей, которые на используются в машинах
select e."name" from car c
right outer join engine e ON e.id = c.engine_id
where c.id is null;