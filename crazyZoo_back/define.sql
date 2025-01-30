
    create table animali (
        id integer not null auto_increment,
        nome_animale varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    create table carrelli (
        id integer not null auto_increment,
        id_utente integer,
        primary key (id)
    ) engine=InnoDB;

    create table immagini (
        id integer not null auto_increment,
        primary key (id)
    ) engine=InnoDB;

    create table marche (
        id integer not null auto_increment,
        nome_marca varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    create table ordini (
        id integer not null auto_increment,
        primary key (id)
    ) engine=InnoDB;

    create table prodotti (
        id integer not null auto_increment,
        id_animale integer,
        id_marca integer,
        prezzo float(53),
        quantita integer,
        titolo varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table recensioni (
        id integer not null auto_increment,
        id_prodotto integer,
        valutazione integer,
        descrizione varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table utenti (
        id integer not null auto_increment,
        cellulare varchar(255) not null,
        cognome varchar(255) not null,
        email varchar(255) not null,
        nome varchar(255) not null,
        password varchar(255) not null,
        username varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    alter table carrelli 
       add constraint UKb76ur59r6gp0hfcl450nh8ua1 unique (id_utente);

    alter table utenti 
       add constraint UKosp6ju78vcf9vn2tblmb1ljgv unique (cellulare);

    alter table utenti 
       add constraint UK9b90mk1nolf3ou90p42a93tjo unique (email);

    alter table utenti 
       add constraint UKtn8mwk6h2wn28yyj7fco65gls unique (username);

    alter table carrelli 
       add constraint FKfoplh747w9xrdk0dkvcufdlwd 
       foreign key (id_utente) 
       references utenti (id);

    alter table prodotti 
       add constraint FKrkjgi92odmn662nhgdbbtybfv 
       foreign key (id_animale) 
       references animali (id);

    alter table prodotti 
       add constraint FKi8oxr29c5gmxa5q775w4oujt2 
       foreign key (id_marca) 
       references marche (id);

    alter table recensioni 
       add constraint FK5wtirys8opmifrpgxxe3lla6q 
       foreign key (id_prodotto) 
       references prodotti (id);
