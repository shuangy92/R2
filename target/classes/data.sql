
--
-- TOC entry 2771 (class 0 OID 68291)
-- Dependencies: 411
-- Data for Name: person; Type: TABLE DATA; Schema: ss1604c200_rd2; Owner: ss1604c200
--
INSERT INTO department (location, name) VALUES ('shanghai', 'Dept1');
INSERT INTO department (location, name) VALUES ('beijing', 'Dept2');
INSERT INTO person (active,email,name,password_hash,role,status,department_id) VALUES (true, 'admin@gmail.com', 'admin', '$2a$04$/A7Lyo5KnF7uyhk6QPMiPe3TRwYdbv9bqWZnbn7lejYI2dBWRBeTm', 'ADMIN', 'NORMAL', 1);
INSERT INTO person (active,email,name,password_hash,role,status,department_id) VALUES (true, 'manager@gmail.com', 'manager', '$2a$04$/A7Lyo5KnF7uyhk6QPMiPe3TRwYdbv9bqWZnbn7lejYI2dBWRBeTm', 'MANAGER', 'NORMAL', 1);
INSERT INTO person (active,email,name,password_hash,role,status,department_id) VALUES (true, 'manager2@gmail.com', 'manager', '$2a$04$/A7Lyo5KnF7uyhk6QPMiPe3TRwYdbv9bqWZnbn7lejYI2dBWRBeTm', 'MANAGER', 'NORMAL', 2);

INSERT INTO job_category (name) VALUES ('Sale');
INSERT INTO job_category (name) VALUES ('HR');
INSERT INTO job_category (name) VALUES ('IT');
INSERT INTO job_category (name) VALUES ('Custom Service');
INSERT INTO job_category (name) VALUES ('Marketing');

/*INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'est.congue@ante.ca', 'Gail Dixon', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'CASHER', 'TRAINEE');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'dictum@scelerisquesedsapien.co.uk', 'Dieter Jackson', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'CASHER', 'TRAINER');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'lorem@sedhendrerita.ca', 'Cyrus Stafford', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'COOK', 'TRAINER');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'imperdiet.non@sit.co.uk', 'Robert Moon', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'COOK', 'NORMAL');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'lobortis.Class@etcommodoat.co.uk', 'Maxine Burch', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'CASHER', 'TRAINEE');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'Donec.tempus.lorem@adipiscing.edu', 'Robin Long', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'COOK', 'TRAINEE');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'Pellentesque.ut.ipsum@magnisdis.edu', 'Upton Lott', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'CASHER', 'TRAINEE');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'senectus.et@commodoauctorvelit.ca', 'Orla William', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'CASHER', 'TRAINER');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'In.scelerisque.scelerisque@aliquam.edu', 'Galena Leonard', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'COOK', 'TRAINEE');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'tellus@nullaInteger.com', 'Guy Stevenson', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'COOK', 'NORMAL');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'erat@atlacusQuisque.edu', 'Glenna Crawford', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'WAITER', 'TRAINEE');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'augue@erateget.edu', 'Ray Santos', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'CASHER', 'NORMAL');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'lacus.Etiam.bibendum@odiosagittis.org', 'Odysseus Berger', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'COOK', 'TRAINEE');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'eu.ultrices.sit@Aliquamgravidamauris.edu', 'Jessica Sosa', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'CASHER', 'TRAINEE');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'elit@QuisquevariusNam.co.uk', 'Echo Justice', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'CASHER', 'TRAINEE');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'pede.Cras@Curabitursedtortor.ca', 'Merrill Avila', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'WAITER', 'TRAINER');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'habitant@interdumSedauctor.org', 'Lillith Holden', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'COOK', 'NORMAL');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'elit.fermentum@Sedauctorodio.ca', 'Kane Wolf', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'WAITER', 'NORMAL');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'habitant@volutpat.net', 'Victor Jensen', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'WAITER', 'NORMAL');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'et@nullaIn.edu', 'Rosalyn Lamb', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'COOK', 'NORMAL');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'eget.massa@elitpedemalesuada.com', 'Nayda Bentley', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'WAITER', 'NORMAL');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'lacus.Cras@feugiat.net', 'Chantale Lawrence', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'CASHER', 'TRAINER');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'Lorem.ipsum@eget.org', 'Lester Sawyer', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'COOK', 'TRAINER');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'vitae@ipsum.co.uk', 'Castor Justice', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'COOK', 'TRAINEE');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'gravida.sagittis.Duis@justofaucibus.ca', 'Jasmine Ware', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'WAITER', 'NORMAL');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'ante.Maecenas@risus.co.uk', 'Wanda Riley', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'WAITER', 'NORMAL');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'dictum@bibendum.org', 'Alexander Miller', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'COOK', 'NORMAL');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'elementum@lacus.ca', 'Eleanor Stevenson', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'WAITER', 'TRAINEE');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'tincidunt.dui.augue@egestas.edu', 'Shelley Lynch', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'CASHER', 'TRAINER');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'orci.Ut.semper@quispedePraesent.com', 'Sarah Ford', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'CASHER', 'NORMAL');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'dui@magnaNam.ca', 'Kennedy Poole', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'WAITER', 'TRAINEE');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'egestas.rhoncus.Proin@enim.ca', 'Christopher Pennington', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'WAITER', 'TRAINEE');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'ac.eleifend@eget.org', 'Jakeem Duffy', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'CASHER', 'TRAINER');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'felis.eget@ornaretortorat.co.uk', 'Rebecca Lyons', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'CASHER', 'TRAINER');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'Proin@adipiscing.net', 'Samson Holloway', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'COOK', 'NORMAL');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'nulla@sitametmetus.org', 'Kirestin Brady', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'WAITER', 'TRAINER');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'eu@afeugiat.org', 'Ria Cote', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'CASHER', 'NORMAL');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'taciti@est.edu', 'Adena Blackburn', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'WAITER', 'NORMAL');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'ac@ultricesmauris.com', 'Inez Reed', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'CASHER', 'TRAINEE');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'interdum@Crasdolor.edu', 'Caldwell Osborn', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'CASHER', 'NORMAL');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'Suspendisse.sed@Quisque.org', 'Kadeem Rasmussen', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'COOK', 'TRAINEE');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'In.mi@tristique.ca', 'Mannix Hensley', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'COOK', 'TRAINEE');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'aliquet.lobortis.nisi@metusVivamuseuismod.org', 'Aurora Emerson', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'CASHER', 'TRAINEE');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'neque@lectusCum.co.uk', 'Kathleen Griffith', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'WAITER', 'NORMAL');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'magnis.dis@neceuismod.ca', 'Nina Bray', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'COOK', 'TRAINER');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'arcu.ac.orci@nec.com', 'Neil White', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'COOK', 'TRAINEE');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'mauris.Suspendisse.aliquet@euismodestarcu.edu', 'TaShya Peterson', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'CASHER', 'NORMAL');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'Aliquam.auctor@magnaPhasellusdolor.co.uk', 'Nora Schroeder', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'WAITER', 'NORMAL');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'Nulla.aliquet.Proin@aarcuSed.org', 'Eric Baker', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'COOK', 'TRAINER');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'ullamcorper@loremsitamet.org', 'Emery Barrett', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'WAITER', 'TRAINEE');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'egestas.urna.justo@ultricesiaculis.org', 'Jackson Hansen', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'WAITER', 'TRAINER');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'in@iaculis.co.uk', 'Ferris English', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'COOK', 'NORMAL');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'faucibus.orci@ornarelectus.co.uk', 'Suki Knight', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'WAITER', 'TRAINER');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'mauris@Donecfeugiatmetus.co.uk', 'Karina Lopez', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'WAITER', 'TRAINER');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'tristique.neque.venenatis@dui.co.uk', 'Sage Wilder', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'COOK', 'NORMAL');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'erat@Incondimentum.org', 'Gray Barnett', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'WAITER', 'TRAINEE');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'arcu.Sed.et@acturpisegestas.co.uk', 'Ray Cook', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'CASHER', 'TRAINER');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'cursus.diam.at@Vivamusnisi.ca', 'Ronan Morgan', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'WAITER', 'TRAINER');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'eu@blanditcongueIn.co.uk', 'Donovan Perkins', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'COOK', 'NORMAL');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'In.faucibus.Morbi@nonsollicitudina.net', 'Lars Vang', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'WAITER', 'TRAINEE');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'vitae.diam@lacus.edu', 'Charlotte Rowe', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'CASHER', 'TRAINER');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'dapibus.ligula.Aliquam@nislarcu.edu', 'Leilani Weber', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'COOK', 'NORMAL');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'morbi.tristique.senectus@Vivamusmolestie.org', 'Laurel Jennings', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'COOK', 'NORMAL');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'libero@ipsum.co.uk', 'Barbara Cunningham', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'WAITER', 'TRAINER');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'Proin@egestasFuscealiquet.ca', 'Lesley Lester', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'WAITER', 'NORMAL');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'sodales@massanonante.co.uk', 'Carly Chang', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'CASHER', 'NORMAL');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'orci.Ut@eget.net', 'Ursa Mccarthy', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'WAITER', 'TRAINEE');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'Donec.dignissim.magna@dolorquamelementum.edu', 'Mara Sullivan', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'CASHER', 'NORMAL');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'tincidunt.orci.quis@ullamcorpermagna.edu', 'Tate Dorsey', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'CASHER', 'TRAINEE');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'felis.Donec@ametornare.net', 'Germane Colon', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'WAITER', 'TRAINEE');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'Sed.nulla.ante@ultricesDuis.ca', 'Alyssa Gilmore', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'WAITER', 'TRAINER');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'feugiat.placerat@quamafelis.edu', 'Paula Shaffer', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'COOK', 'NORMAL');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'auctor.velit.eget@est.edu', 'Abra Sandoval', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'COOK', 'NORMAL');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'aliquet.metus.urna@sapien.org', 'Bryar Winters', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'WAITER', 'TRAINER');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'amet@augue.edu', 'Kibo Wynn', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'COOK', 'TRAINER');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'nisi.a.odio@augueSed.ca', 'Colleen Wall', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'WAITER', 'TRAINER');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'rutrum.Fusce@idrisusquis.co.uk', 'Carly Gill', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'CASHER', 'TRAINER');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'risus.at.fringilla@nondui.edu', 'Candice Hunter', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'WAITER', 'TRAINER');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'risus.quis@Vivamussitamet.org', 'Wyatt Morales', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'CASHER', 'TRAINEE');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'quis.turpis@orciinconsequat.co.uk', 'Garth Wilkins', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'COOK', 'NORMAL');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'Donec.at.arcu@atortor.net', 'Oleg Arnold', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'COOK', 'TRAINER');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'ac.ipsum@erat.edu', 'Castor Glenn', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'COOK', 'TRAINEE');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'augue.ut@Sed.net', 'Lucian Witt', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'COOK', 'TRAINER');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'Maecenas.libero.est@sit.com', 'Connor Snider', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'CASHER', 'NORMAL');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'Donec.elementum@ac.com', 'Irma Barnes', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'WAITER', 'NORMAL');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'Nullam.lobortis@pedeCum.com', 'Kadeem Hicks', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'WAITER', 'TRAINEE');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'molestie.tellus@semNulla.co.uk', 'Orla Williamson', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'CASHER', 'NORMAL');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'a@id.co.uk', 'Dakota Garrett', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'COOK', 'NORMAL');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'at.auctor.ullamcorper@nasceturridiculusmus.co.uk', 'Gabriel Garrison', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'CASHER', 'TRAINEE');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'dignissim@elit.co.uk', 'Giacomo Stone', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'CASHER', 'TRAINEE');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'convallis.ante.lectus@imperdiet.co.uk', 'Josiah Giles', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'CASHER', 'TRAINEE');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'ornare.placerat.orci@amet.ca', 'Brock Hamilton', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'CASHER', 'TRAINER');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'Duis@Quisqueimperdieterat.net', 'Risa Acevedo', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'COOK', 'TRAINER');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'pede.ultrices.a@nonhendreritid.com', 'Rafael Mckenzie', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'COOK', 'TRAINER');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'blandit@lectuspede.org', 'Sopoline Richardson', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'CASHER', 'TRAINER');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'et@acurna.co.uk', 'Tamekah Campos', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'WAITER', 'TRAINEE');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'urna@risusDonec.net', 'Garrett Alston', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'CASHER', 'NORMAL');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'nec.cursus@ipsumDonec.edu', 'Leonard Copeland', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'WAITER', 'TRAINER');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'amet@sociisnatoque.edu', 'Dolan Ramirez', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'WAITER', 'NORMAL');
INSERT INTO person (active,email,name,password_hash,role,status) VALUES (true, 'consequat.enim.diam@leo.com', 'Giacomo Summers', '$2a$04$JgdrdJIpz7Q3snhHT68HKuVQ.T06bmV3y3tLqZehyKPXnr1Nq3MMq', 'COOK', 'TRAINEE');


--
-- TOC entry 2773 (class 0 OID 68302)
-- Dependencies: 413
-- Data for Name: request; Type: TABLE DATA; Schema: ss1604c200_rd2; Owner: ss1604c200
--

INSERT INTO request (replier_message,reply_date,send_date,sender_message,status,title,type,replier_id,sender_id) VALUES (NULL, NULL, '2016-04-20 15:58:49.318', 'are you happy?', 'PENDING', 'hey man', 'OTHER', NULL, 65);
INSERT INTO request (replier_message,reply_date,send_date,sender_message,status,title,type,replier_id,sender_id) VALUES (NULL, NULL, '2016-04-20 15:59:10.556', 'ahhhh', 'PENDING', 'let me go', 'LEAVE', NULL, 65);
INSERT INTO request (replier_message,reply_date,send_date,sender_message,status,title,type,replier_id,sender_id) VALUES (NULL, NULL, '2016-04-20 15:59:34.396', 'Closes the connection, if any, and sets the readyState attribute to CLOSED. If the connection is already closed, the method does nothing.', 'PENDING', 'it''s a good day', 'OTHER', NULL, 65);
INSERT INTO request (replier_message,reply_date,send_date,sender_message,status,title,type,replier_id,sender_id) VALUES (NULL, NULL, '2016-04-20 16:03:15.618', 'today and tomorrow', 'PENDING', 'leave for two days', 'LEAVE', NULL, 89);
INSERT INTO request (replier_message,reply_date,send_date,sender_message,status,title,type,replier_id,sender_id) VALUES (NULL, NULL, '2016-04-20 16:04:28.157', 'ha ha u r funny', 'PENDING', 'hi baby', 'OTHER', NULL, 4);
INSERT INTO request (replier_message,reply_date,send_date,sender_message,status,title,type,replier_id,sender_id) VALUES (NULL, NULL, '2016-04-20 16:05:27.501', 'can''t come next week', 'PENDING', 'i feel sick', 'LEAVE', NULL, 4);
INSERT INTO request (replier_message,reply_date,send_date,sender_message,status,title,type,replier_id,sender_id) VALUES ('no', '2016-04-20 16:20:11.094', '2016-04-20 16:03:39.852', 'do you want to be a waiter forever?', 'PENDING', 'i''m done with it', 'RESIGNATION', 1, 89);
INSERT INTO request (replier_message,reply_date,send_date,sender_message,status,title,type,replier_id,sender_id) VALUES ('sure', '2016-04-20 16:22:52.25', '2016-04-20 16:12:00.436', 'enough', 'PENDING', 'enough', 'RESIGNATION', 1, 21);
INSERT INTO request (replier_message,reply_date,send_date,sender_message,status,title,type,replier_id,sender_id) VALUES ('say english', '2016-04-20 16:30:53.716', '2016-04-20 16:00:47.413', '
Navigate commands enable you to quickly jump to the desired classes, files, or symbols specified by names. IntelliJ IDEA suggests ', 'DECLINED', 'this is a request ', 'LEAVE', 1, 89);
INSERT INTO request (replier_message,reply_date,send_date,sender_message,status,title,type,replier_id,sender_id) VALUES ('you are so interesting', '2016-04-20 16:31:39.358', '2016-04-20 16:05:04.716', 'i''m so smart', 'DECLINED', '1+1=2', 'OTHER', 1, 4);
INSERT INTO request (replier_message,reply_date,send_date,sender_message,status,title,type,replier_id,sender_id) VALUES ('ok', '2016-04-20 16:31:59.232', '2016-04-20 16:02:37.125', 'The EventSource interface is used to receive server-sent events. It connects to a server over HTTP and receives events in text/event-stream format without closing the connection.', 'APPROVED', 'The EventSource interface', 'LEAVE', 1, 89);
INSERT INTO request (replier_message,reply_date,send_date,sender_message,status,title,type,replier_id,sender_id) VALUES ('i don''t know', '2016-04-20 16:10:08.644', '2016-04-20 15:56:50.773', 'you know', 'PENDING', 'i''m tired', 'RESIGNATION', 1, 65);
INSERT INTO request (replier_message,reply_date,send_date,sender_message,status,title,type,replier_id,sender_id) VALUES ('good', '2016-04-20 16:28:51.682', '2016-04-20 16:04:45.499', 'how do u think?', 'PENDING', 'no more me', 'RESIGNATION', 1, 4);
INSERT INTO request (replier_message,reply_date,send_date,sender_message,status,title,type,replier_id,sender_id) VALUES ('ok', '2016-04-20 16:28:26.29', '2016-04-20 16:11:30.885', 'aaaaa', 'PENDING', 'aaaaa', 'RESIGNATION', 1, 11);



--
-- TOC entry 2774 (class 0 OID 68311)
-- Dependencies: 414
-- Data for Name: roster; Type: TABLE DATA; Schema: ss1604c200_rd2; Owner: ss1604c200
--

INSERT INTO roster VALUES (1460537043424, NULL, 20160410, 'Asia/Shanghai', 1);


--
-- TOC entry 2775 (class 0 OID 68319)
-- Dependencies: 415
-- Data for Name: schedule; Type: TABLE DATA; Schema: ss1604c200_rd2; Owner: ss1604c200
--

INSERT INTO schedule VALUES (1460533845404, 20160410, 'Asia/Shanghai', 8);
INSERT INTO schedule VALUES (1460534047852, 20160417, 'Asia/Shanghai', 8);
INSERT INTO schedule VALUES (1460534125880, 20160410, 'Asia/Shanghai', 9);
INSERT INTO schedule VALUES (1460534178321, 20160417, 'Asia/Shanghai', 9);
INSERT INTO schedule VALUES (1460534238952, 20160410, 'Asia/Shanghai', 10);
INSERT INTO schedule VALUES (1460534283647, 20160417, 'Asia/Shanghai', 10);
INSERT INTO schedule VALUES (1460537189022, 20160410, 'Asia/Shanghai', 4);



--
-- TOC entry 2777 (class 0 OID 68332)
-- Dependencies: 417
-- Data for Name: time_slot; Type: TABLE DATA; Schema: ss1604c200_rd2; Owner: ss1604c200
--

INSERT INTO time_slot VALUES (1460533846517, 20160410, '12:00', false, '10:00', 1460533845404, 8);
INSERT INTO time_slot VALUES (1460533848228, 20160411, '12:00', false, '00:00', 1460533845404, 8);
INSERT INTO time_slot VALUES (1460533848660, 20160412, '10:00', false, '08:00', 1460533845404, 8);
INSERT INTO time_slot VALUES (1460533849404, 20160413, '19:00', false, '07:00', 1460533845404, 8);
INSERT INTO time_slot VALUES (1460533849852, 20160414, '17:00', false, '11:00', 1460533845404, 8);
INSERT INTO time_slot VALUES (1460533850301, 20160415, '20:30', false, '15:30', 1460533845404, 8);
INSERT INTO time_slot VALUES (1460533850893, 20160416, '17:00', false, '10:00', 1460533845404, 8);
INSERT INTO time_slot VALUES (1460534035531, 20160411, '09:00', false, '02:00', 1460533845404, 8);
INSERT INTO time_slot VALUES (1460534050139, 20160417, '10:00', false, '08:00', 1460534047852, 8);
INSERT INTO time_slot VALUES (1460534056395, 20160418, '18:30', false, '14:30', 1460534047852, 8);
INSERT INTO time_slot VALUES (1460534061355, 20160419, '17:30', false, '11:00', 1460534047852, 8);
INSERT INTO time_slot VALUES (1460534066771, 20160420, '17:00', false, '13:00', 1460534047852, 8);
INSERT INTO time_slot VALUES (1460534082596, 20160417, '18:30', false, '12:30', 1460534047852, 8);
INSERT INTO time_slot VALUES (1460534128196, 20160410, '12:00', false, '10:00', 1460534125880, 9);
INSERT INTO time_slot VALUES (1460534145292, 20160414, '12:00', false, '10:00', 1460534125880, 9);
INSERT INTO time_slot VALUES (1460534146067, 20160414, '21:00', false, '18:00', 1460534125880, 9);
INSERT INTO time_slot VALUES (1460534169996, 20160416, '12:00', false, '10:00', 1460534125880, 9);
INSERT INTO time_slot VALUES (1460534180573, 20160418, '12:00', false, '10:00', 1460534178321, 9);
INSERT INTO time_slot VALUES (1460534179508, 20160419, '13:30', false, '10:00', 1460534178321, 9);
INSERT INTO time_slot VALUES (1460534203051, 20160420, '19:00', false, '17:00', 1460534178321, 9);
INSERT INTO time_slot VALUES (1460534240915, 20160416, '21:00', false, '15:30', 1460534238952, 10);
INSERT INTO time_slot VALUES (1460534241834, 20160415, '17:00', false, '11:00', 1460534238952, 10);
INSERT INTO time_slot VALUES (1460534242218, 20160414, '20:30', false, '14:30', 1460534238952, 10);
INSERT INTO time_slot VALUES (1460534262179, 20160411, '15:00', false, '08:30', 1460534238952, 10);
INSERT INTO time_slot VALUES (1460534266298, 20160412, '15:00', false, '10:30', 1460534238952, 10);
INSERT INTO time_slot VALUES (1460534270410, 20160412, '21:30', false, '19:00', 1460534238952, 10);
INSERT INTO time_slot VALUES (1460534285179, 20160417, '19:30', false, '10:00', 1460534283647, 10);
INSERT INTO time_slot VALUES (1460534291235, 20160418, '19:00', false, '16:00', 1460534283647, 10);
INSERT INTO time_slot VALUES (1460534295675, 20160419, '17:00', false, '12:00', 1460534283647, 10);
INSERT INTO time_slot VALUES (1460534301475, 20160420, '19:00', false, '12:30', 1460534283647, 10);
INSERT INTO time_slot VALUES (1460534308709, 20160421, '17:30', false, '08:30', 1460534283647, 10);
INSERT INTO time_slot VALUES (1460534315403, 20160422, '14:00', false, '12:00', 1460534283647, 10);
INSERT INTO time_slot VALUES (1460534323946, 20160422, '20:00', false, '16:00', 1460534283647, 10);
INSERT INTO time_slot VALUES (1460537193522, 20160412, '15:30', false, '06:00', 1460537189022, 4);
INSERT INTO time_slot VALUES (1460537201337, 20160412, '23:00', false, '19:00', 1460537189022, 4);
INSERT INTO time_slot VALUES (1460537206729, 20160413, '12:30', false, '07:00', 1460537189022, 4);
INSERT INTO time_slot VALUES (1460537211690, 20160413, '18:30', false, '16:30', 1460537189022, 4);
INSERT INTO time_slot VALUES (1460537217977, 20160415, '17:00', false, '09:00', 1460537189022, 4);
*/