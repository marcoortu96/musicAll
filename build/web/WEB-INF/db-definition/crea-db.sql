/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  marcoortu
 * Created: 8-mag-2018
 */
/*I drop sono utlizzati per testare e ripristinare il db*/
drop table if exists categoria;
drop table if exists commenti;
drop table if exists notizia;
drop table if exists utente;

/*Creare una tabella nel db del modello User*/
create table utente(
    id_utente serial primary key, /*prima colonna, primary key deve essere unico*/
    nome varchar(100),
    cognome varchar(100),
    email varchar(150),
    username varchar(100) not null,
    password varchar(100) not null,
    url_prof_img varchar(1000),
    tipo_utente enum('AUTORE', 'LETTORE') not null
);

/*Creare una tabella  nel db del modello News*/
create table notizia(
    id_notizia serial primary key,
    titolo varchar(200),
    contenuto varchar(10000),
    data_notizia date,
    descr_img varchar(100),
    url_img varchar(1000),
    alt_img varchar(100),
    categoria set('Rap', 'Trap', 'Rock', 'Pop', 'Electronic', 'Indie', 'Jazz', 'Classica'),
    id_utente BIGINT UNSIGNED not null,                                     /*Key secondario che collega notizia*/
    foreign key (id_utente) references utente(id_utente) on update cascade  /*alla tabella utene in base all'autore*/  
);

/*Creare una tabella  nel db del modello Comment*/
create table commenti(
    id_commento serial primary key,
    contenuto varchar(6500),
    id_utente BIGINT UNSIGNED not null,                                     /*Key secondario che collega notizia*/
    foreign key (id_utente) references utente(id_utente) on update cascade,
    id_notizia BIGINT UNSIGNED not null,
    foreign key (id_notizia) references notizia(id_notizia) on update cascade
);

create table categoria(
    id_categoria serial primary key,
    nome_categoria varchar(20) 
);

/*Inserimento utenti*/
insert into utente(nome, cognome, email, username, password, url_prof_img, tipo_utente)
values('Mario', 'Boni', 'marioboni@gmail.com', 'marioboni', 'mabo', 'https://assets.pokemon.com/assets/cms2/img/pokedex/full/007.png', 'LETTORE');

insert into utente(nome, cognome, email, username, password, url_prof_img, tipo_utente)
values('Marco', 'Ortu', 'sora@hotmail.it', 'sora5', 'olio', 'https://media.pokemoncentral.it/wiki/thumb/9/9c/Artwork004.png/200px-Artwork004.png', 'AUTORE');

insert into utente(nome, cognome, email, username, password, url_prof_img, tipo_utente)
values('Henry', 'Porcu', 'henryporcu@virgilio.it', 'henryporc', 'cocacola', 'https://assets.pokemon.com/assets/cms2/img/pokedex/full/001.png', 'AUTORE');

/*Inserimento notizie*/
insert into notizia(titolo, contenuto, data_notizia, descr_img, url_img, alt_img, categoria, id_utente)
values('Clasho Myiagi semina il panico', 'Clasho Myiagi nasce ad Iglesias, Sardegna, il 22 gennaio 1998. Appassionato del genere rap già da giovene età, inizia a fare i suoi primi lavori a 12 anni. La sua passione lo porta a rendere pubblica la sua musica, evolvendosi di continuofino a diventare l attuale Clasho Myiagi. Si legge chiaramente nel volto del rapper la voglia di emergere e sfondare nella scena italiana, pur sapendo quanto sia difficile, ma lui afferma di non arrendersi spinto dalla voglia di diventare famoso e come lui ha detto: voglio farcela per dare una bella casa ai miei genitori, dare una mano alle persone care e comprare una macchina al suo amico non che collega NastyFebo. A inizio 2018 ha fatto uscire il suo ultimo lavoro Workin Mixtape un album di dieci tracce, ma Clasho non si limita solo all uscita di un mixtape, infatti con l aiuto di un team  di video-maker, i 2Mprod, ha dato vita a più video musicali tra cui il più famoso, che ormai ha quasi raggiunto 15 mila visualizzazioni su YouTube, che si chiama "Senza Testimoni".', 
       '2018-04-29','Concerto di Clasho nel Sulcis', 'http://localhost:8080/Milestone1/M1/img/clasho.jpg', 'Foto di Clasho Miyagi', ('Trap,Rap'), (select id_utente from utente where nome = 'Marco' and username = 'sora5'));

insert into notizia(titolo, contenuto, data_notizia, descr_img, url_img, alt_img, categoria, id_utente)
values('Il Malammore di Luchè', 'Luca Imprudente, 35 anni, in arte Luchè, è chi mostra la situazione della sua Napoli e in particolare della zona tra Marianella e Scampia, resa celebre dal libro Roberto Saviano, dal film e dalla fiction. Lo ha fatto prima nel duo Co Sang, lo fa da quattro anni come solista. Dopo i dischi L1 e L2, ora ecco Malammore, tanto per cambiare un po’ con i titoli", anticipato dal singolo O’ primmo ammore, incluso proprio nella serie tv Gomorra. Non cambia la sostanza: un gangsta rap che in Italia è un genere non troppo praticato, e chi potrebbe praticarlo se non chi viene dalla mia terra?, dice un po’ scherzando e un po’ con amarezza.', 
       '2017-03-20','Copertina del disco Malammore', 'https://images-na.ssl-images-amazon.com/images/I/51Vro9DC0GL.jpg', 'Copertina del disco Malammore', ('Trap'), (select id_utente from utente where nome = 'Marco' and username = 'sora5'));

insert into notizia(titolo, contenuto, data_notizia, descr_img, url_img, alt_img, categoria, id_utente)
values('Tomorrowland per la prima volta in Italia', 'L Italia è per la prima volta tra le magnifiche sette nazioni che sabato 28 luglio ospiteranno Tomorrowland, il più grande festival della musica dance ed elettronica. A far da cornice all evento sarà il Parco di Monza, che l’estate scorsa ha ospitato gli I-Days: sul palco si alterneranno diversi dj di fama internazionale e numerosi collegamenti in diretta streaming con l’esibizione principale in Belgio, dalle 16 fino alle 3 di notte.', 
       '2018-02-15','Foto del Tomorrowland', 'https://www.si24.it/wp-content/uploads/2018/02/Tomorrowland_Belgium_en_2016-800x600.jpg', 'Foto del Tomorrowland', ('Electronic'), (select id_utente from utente where nome = 'Henry' and username = 'henryporc'));

/*Inserimento commenti*/
insert into commenti(contenuto, id_utente, id_notizia)
values('commento interessante',(select id_utente from utente where nome = 'Mario' and username = 'marioboni'), 1);


insert into commenti(contenuto, id_utente, id_notizia)
values('commento molto interessante',(select id_utente from utente where nome = 'Henry' and username = 'henryporc'), 1);

insert into commenti(contenuto, id_utente, id_notizia)
values('commento incredibilmente interessante',(select id_utente from utente where nome = 'Marco' and username = 'sora5'), 3);

/*Inserimento categorie*/
insert into categoria(nome_categoria) values('Rap');
insert into categoria(nome_categoria) values('Trap');
insert into categoria(nome_categoria) values('Rock');
insert into categoria(nome_categoria) values('Pop');
insert into categoria(nome_categoria) values('Electronic');
insert into categoria(nome_categoria) values('Indie');
insert into categoria(nome_categoria) values('Jazz');
insert into categoria(nome_categoria) values('Classica');


