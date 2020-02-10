insert into voorstellingen (titel, uitvoerders, datum, genreid, prijs, vrijeplaatsen, versie)
values ('test1', 'test1', '2019-09-10 10:00:00', (select id from genres where naam = 'test1'), 5.50, 100, 1);
insert into voorstellingen (titel, uitvoerders, datum, genreid, prijs, vrijeplaatsen, versie)
values ('test2', 'test2', '2029-09-10 15:00:00', (select id from genres where naam = 'test2'), 10, 200, 1);