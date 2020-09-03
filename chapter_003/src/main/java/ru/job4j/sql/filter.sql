--1. �������� ������ ��������� ���� ��������� � ����� "���"
select p."name", p.expired_date, p.price, t."name" from product p
left join "type" t on p.type_id = t.id 
where t."name" = '���';

--2. �������� ������ ��������� ���� ���������, � ���� � ����� ���� ����� "����������"
select "name", expired_date, price from product
where "name" like '%����������%';

--3. �������� ������, ������� ������� ��� ��������, ���� �������� ������� ������������� � ��������� ������.
select "name", expired_date, price from product p 
where expired_date between now() and now() + interval '1 month';

--4. �������� ������, ������� ������� ����� ������� �������.
select * from product p
where p.price = (select max(price) from product p2);

--5. �������� ������, ������� ������� ���������� ���� ��������� ������������� ����.
select count(p.id) from product p 
left join "type" t on p.type_id = t.id 
where t."name" = '���';

--6. �������� ������ ��������� ���� ��������� � ����� "���" � "������"
select p.* from product p 
left join "type" t on p.type_id = t.id 
where t."name" in ('���', '������');

--7. �������� ������, ������� ������� ��� ���������, ������� �������� ������ 10 ����.  
select * from (select t."name", count(p.id) from product p 
left join "type" t on p.type_id = t.id
group by t."name") as c
where c.count < 10;

--8. ������� ��� �������� � �� ���.
select p.id, p.name, t."name", p.expired_date, p.price from product p 
left join "type" t on p.type_id = t.id;