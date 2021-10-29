set foreign_key_checks=0;

truncate  table  learning_party;
truncate  table  authority;
truncate  table  instructor;

insert into learning_party( `id`, `email`, `pass_word`, `enabled`)
values(123, 'tomi@gmail.com', '1234pass$', false),
    (124, 'timi@gmail.com', '1234pass$', false),
    (125, 'yom@gmail.com', '1234pass$', false),
   (126, 'toni@gmail.com', '1234pass$', false);


set foreign_key_checks=1;