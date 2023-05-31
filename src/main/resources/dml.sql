insert into roles ("role_id", "name") values ('1', 'USER');
insert into roles ("role_id", "name") values ('2', 'USER');
-- password is pas5word
insert into ecommerce.users ("id", "username" , "password", "role_id") values ('96c65d26-fa88-42ab-aeaa-f832706039f5', 'qwertyui', '$2a$04$nqsNDmO6L9OlqLImNC.HVe6GnMWLYoTfCiZJce1VhSaJjPtMd.OpK', '1');