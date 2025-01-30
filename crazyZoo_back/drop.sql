
    alter table carrelli 
       drop 
       foreign key FKfoplh747w9xrdk0dkvcufdlwd;

    alter table prodotti 
       drop 
       foreign key FKrkjgi92odmn662nhgdbbtybfv;

    alter table prodotti 
       drop 
       foreign key FKi8oxr29c5gmxa5q775w4oujt2;

    alter table recensioni 
       drop 
       foreign key FK5wtirys8opmifrpgxxe3lla6q;

    drop table if exists animali;

    drop table if exists carrelli;

    drop table if exists immagini;

    drop table if exists marche;

    drop table if exists ordini;

    drop table if exists prodotti;

    drop table if exists recensioni;

    drop table if exists utenti;
