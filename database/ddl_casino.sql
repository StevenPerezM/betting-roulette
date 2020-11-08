/*==============================================================*/
/* DBMS name:      PostgreSQL 8                                 */
/* Created on:     5/11/2020 10:24:54 p. m.                     */
/*==============================================================*/


/*==============================================================*/
/* Table: bets                                                  */
/*==============================================================*/
create table bets (
   id_bet               serial               not null,
   id_roulette          int4                 not null,
   id_user              int4                 not null,
   number               smallint             null,
   color                char(1)              null,
   money_bet            float8               not null,
   constraint pk_bets primary key (id_bet)
);

/*==============================================================*/
/* Table: roulettes                                             */
/*==============================================================*/
create table roulettes (
   id_roulette          serial               not null,
   open                 bool                 not null,
   constraint pk_roulettes primary key (id_roulette)
);

/*==============================================================*/
/* Table: users                                                 */
/*==============================================================*/
create table users (
   id_user              serial               not null,
   money                float8               not null,
   constraint pk_users primary key (id_user)
);

alter table bets
   add constraint fk_bets_reference_roulette foreign key (id_roulette)
      references roulettes (id_roulette)
      on delete restrict on update restrict;

alter table bets
   add constraint fk_bets_reference_users foreign key (id_user)
      references users (id_user)
      on delete restrict on update restrict;

