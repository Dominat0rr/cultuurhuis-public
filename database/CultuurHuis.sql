set names utf8mb4;
set charset utf8mb4;
drop database if exists cultuurhuis;
create database cultuurhuis charset utf8mb4;
USE cultuurhuis;

create table genres (
  id int unsigned not null auto_increment primary key,
  naam varchar(50) not null unique
) auto_increment=11;

insert into genres(id,naam) values
 (10,'Circustheater'),
 (6,'Dans'),
 (5,'Familievoorstelling'),
 (1,'Humor'),
 (8,'Modern klassiek'),
 (7,'Multimedia'),
 (3,'Muziek'),
 (9,'Muziektheater'),
 (2,'Theater'),
 (4,'Wereldmuziek');

create table klanten (
  id int unsigned not null auto_increment primary key,
  voornaam varchar(50) not null,
  familienaam varchar(50) not null,
  straat varchar(50) not null,
  huisnr varchar(50) not null,
  postcode varchar(50) not null,
  gemeente varchar(50) not null,
  gebruikersnaam varchar(50) not null unique,
  paswoord varchar(100) not null
);


insert into klanten(voornaam,familienaam,straat,huisnr,postcode,gemeente,gebruikersnaam,paswoord) values
 ('Hans','Desmet','Keizerslaan','7','1000','Brussel','hans','{bcrypt}$2a$10$yXxcEKwK9eiRggZ1L5hHduIrUTW4fMyZnrTN6jmQdvX4gYHJKdyNC'),
 ('Alexandra','Blondeel','Anspachlaan','65','1000','Brussel','alexandra','{bcrypt}$2a$10$/rUxgaFVmYCKTfD..cSQOOBnbO95RyE8zl6TZ2PQE.q22oTC/2MjK');

create table voorstellingen (
  id int unsigned not null auto_increment primary key,
  titel varchar(50) not null,
  uitvoerders varchar(50) not null,
  datum datetime not null,
  genreid int unsigned not null,
  prijs decimal(10,2) not null,
  vrijeplaatsen int unsigned not null,
  versie int unsigned not null default 0,
  -- CONSTRAINT voorstellingenGenres FOREIGN KEY (genreid) references genres(id)
	constraint fk_voorstellingen_genres FOREIGN KEY (genreid) REFERENCES genres(id)
);

insert into voorstellingen(titel,uitvoerders,datum,genreid,prijs,vrijeplaatsen) values
 ('Rechtstreeks & integraal/Ka-Boom!','Neveneffecten / Alex Agnew',adddate(timestamp(curdate(), '20:00'), 31),1,5,0),
 ('De leeuw van Vlaanderen','Union Suspecte / Publiekstheater',adddate(timestamp(curdate(), '20:00'), 32),2,7,141),
 ('Ano Neko','Dobet Gnahoré',adddate(timestamp(curdate(), '20:00'), 33),4,6,200),
 ('Professor Bernhardi','de Roovers',adddate(timestamp(curdate(), '20:00'), 34),2,7.5,180),
 ('Ich bin wie du','het Toneelhuis',adddate(timestamp(curdate(), '20:00'), 35),2,7,150),
 ('Randschade','fABULEUS',adddate(timestamp(curdate(), '20:00'), 36),5,6,200),
 ('Koning Lear','Tonic',adddate(timestamp(curdate(), '20:00'), 37),2,7,170),
 ('Van alle landen thuis','Els Helewaut,D.Van Esbroeck & co',adddate(timestamp(curdate(), '20:00'), 38),3,5,200),
 ('Ma - Earth','Akram Khan',adddate(timestamp(curdate(), '20:00'), 39),6,8,180),
 ('Jerusalem','Berlin',adddate(timestamp(curdate(), '20:00'), 40),7,7.5,198),
 ('De fijnste dagen van het jaar','Het Paleis/Jenny',adddate(timestamp(curdate(), '20:00'), 41),5,4,168),
 ('Cancion de un amorio','Bodicxhel',adddate(timestamp(curdate(), '20:00'), 42),4,6,200),
 ('Moest ik van u zijn','Wouter Deprez  ',adddate(timestamp(curdate(), '20:00'), 43),1,6,198),
 ('Poézique cabaret','La compagnie du chien qui tousse',adddate(timestamp(curdate(), '20:00'), 44),1,6.5,200),
 ('Eekhoornbrood','Lampe',adddate(timestamp(curdate(), '20:00'), 45),2,5.5,180),
 ('Liederen v. Baert, Debussy, Chausson, Weill','Bernard Baert & Anna Pardon',adddate(timestamp(curdate(), '20:00'), 46),8,8,198),
 ('L\"Hafa','Union Suspecte',adddate(timestamp(curdate(), '20:00'), 47),2,7,200),
 ('Achter \'t eten','Ceremonia/Het muziek Lod/Theater Zuidpool',adddate(timestamp(curdate(), '20:00'), 48),3,6,180),
 ('Poulenc / Stravinsky','Prometheus Ensemble',adddate(timestamp(curdate(), '20:00'), 49),8,8,200),
 ('Lied der rusteloosheid','Eva De Roovere, Pedro Moutinho & G. de Mol',adddate(timestamp(curdate(), '20:00'), 50),4,7,190),
 ('Wilde dingen','Kopergietery    ',adddate(timestamp(curdate(), '20:00'), 51),5,5,196),
 ('Licht','Bos',adddate(timestamp(curdate(), '20:00'), 52),7,6,94),
 ('Een hond in de nacht','Speeltheater Holland',adddate(timestamp(curdate(), '20:00'), 53),5,6,0),
 ('Gloed','theater Malpertuis',adddate(timestamp(curdate(), '20:00'), 54),2,7,196),
 ('To speak or not to speak','Spectra Ensemble',adddate(timestamp(curdate(), '20:00'), 55),8,8,200),
 ('Tres cultures por la paz','Rosa Zaragoza',adddate(timestamp(curdate(), '20:00'), 56),4,7.25,190),
 ('Zouff!','Les Argonautes',adddate(timestamp(curdate(), '20:00'), 57),10,5,200),
 ('La cucina dell\"arte ','David & Danny Ronaldo',adddate(timestamp(curdate(), '20:00'), 58),10,6,190),
 ('Speelt Rzewski','Frederic Rzewski',adddate(timestamp(curdate(), '20:00'), 59),8,8,160),
 ('Tv-tunes K.N.T.','Wim Opbrouck & Maandacht',adddate(timestamp(curdate(), '20:00'), 60),3,7,200),
 ('Schone woorden klinken zo...','Warre Borgmans & Jokke Schreurs',adddate(timestamp(curdate(), '20:00'), 61),3,6,180),
 ('White Light White Heat - The Velvet Undergr.','Bea Van der Maat & Dr Kloot Per W',adddate(timestamp(curdate(), '20:00'), 62),3,5.5,200),
 ('Het gaat toch rap','Raf Coppens',adddate(timestamp(curdate(), '20:00'), 63),1,6,170),
 ('Emilia Galotti','Tijd',adddate(timestamp(curdate(), '20:00'), 64),2,7,198),
 ('Iets over de liefde','theater Malpertuis',adddate(timestamp(curdate(), '20:00'), 66),2,6,160),
 ('Hendrickx, Xenakis & Tan Dun','Spiegel Strijkkwartet',adddate(timestamp(curdate(), '20:00'), 67),8,7,180),
 ('Cry like a man, part 2','J. Blaute, Paul Michiels & Roland',adddate(timestamp(curdate(), '20:00'), 68),3,6,8),
 ('De Kreutzersonates','Het Net',adddate(timestamp(curdate(), '20:00'), 69),2,'7.00',100);

create table reservaties (
  id int unsigned not null auto_increment primary key,
  klantid int unsigned not null,
  voorstellingid int unsigned not null,
  plaatsen int unsigned not null,
  constraint fk_reservaties_klanten foreign key (klantid) references klanten(id),
  constraint fk_reservaties_voorstellingen foreign key (voorstellingid) references voorstellingen(id)
--   CONSTRAINT reservatiesKlanten FOREIGN KEY (klantid) REFERENCES klanten(id),
--   CONSTRAINT reservatiesVoorstellingen FOREIGN KEY (voorstellingid) REFERENCES voorstellingen(id)
);

create user if not exists application identified by 'application';
grant select on genres to application;
grant select,insert on klanten to application;
grant insert on reservaties to application;
grant select,update on voorstellingen to application;