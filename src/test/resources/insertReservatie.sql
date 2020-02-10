insert into reservaties (klantid, voorstellingid, plaatsen)
values ((select id from klanten where familienaam = 'test'),
        (select id from voorstellingen where titel = 'test1'
            and genreid = (select id from genres where naam = 'test1')), 1);