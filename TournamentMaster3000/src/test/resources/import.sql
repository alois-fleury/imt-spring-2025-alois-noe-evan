INSERT INTO users (username, password, enabled) VALUES ('user', '{bcrypt}$2a$10$1SJdbP9DR1k2lhClxM/wye92ova6/bvWO6z3tQDC6og3.eCZE2PwK', 1);
INSERT INTO users (username, password, enabled) VALUES ('admin', '{bcrypt}$2a$10$oxQtvu/geZAPUpbg57rBEupe4obZ.QYsjJGIk1J53er/C.4QILOjW', 1);

INSERT INTO authorities (username, authority) VALUES ('user', 'ROLE_USER');
INSERT INTO authorities (username, authority) VALUES ('admin', 'ROLE_ADMIN');

INSERT INTO joueur (id, nom, prenom, numero) VALUES (1, 'Doe', 'John', 1);
INSERT INTO joueur (id, nom, prenom, numero) VALUES (2, 'Doe', 'Jane', 2);
INSERT INTO joueur (id, nom, prenom, numero) VALUES (3, 'Doe', 'Jack', 3);
INSERT INTO joueur (id, nom, prenom, numero) VALUES (4, 'Doe', 'Jill', 4);
INSERT INTO joueur (id, nom, prenom, numero) VALUES (5, 'Doe', 'Jim', 5);
INSERT INTO joueur (id, nom, prenom, numero) VALUES (6, 'Doe', 'Jenny', 6);

INSERT INTO equipe (id, nom) VALUES (1, 'Equipe 1');
INSERT INTO equipe (id, nom) VALUES (2, 'Equipe 2');

INSERT INTO equipe_joueur (equipe_id, joueur_id) VALUES (1, 1);
INSERT INTO equipe_joueur (equipe_id, joueur_id) VALUES (1, 2);
INSERT INTO equipe_joueur (equipe_id, joueur_id) VALUES (1, 3);
INSERT INTO equipe_joueur (equipe_id, joueur_id) VALUES (2, 4);
INSERT INTO equipe_joueur (equipe_id, joueur_id) VALUES (2, 5);
INSERT INTO equipe_joueur (equipe_id, joueur_id) VALUES (2, 6);

INSERT INTO round (id, scorea, scoreb, round_number) VALUES (1, 21, 14, 1);
INSERT INTO round (id, scorea, scoreb, round_number) VALUES (2, 19, 21, 2);
INSERT INTO round (id, scorea, scoreb, round_number) VALUES (3, 21, 17, 3);
INSERT INTO round (id, scorea, scoreb, round_number) VALUES (4, 2, 1, 4);
INSERT INTO round (id, scorea, scoreb, round_number) VALUES (5, 1, 2, 5);
INSERT INTO round (id, scorea, scoreb, round_number) VALUES (6, 2, 1, 6);

INSERT INTO `match` (id, equipea_id, equipeb_id, status, `date`) VALUES (1, 1, 2, 2, '2024-01-10 14:30:00');
INSERT INTO `match` (id, equipea_id, equipeb_id, status, `date`) VALUES (2, 1, 2, 2, '2024-01-11 16:00:00');

INSERT INTO match_round (match_id, round_id) VALUES (1, 1);
INSERT INTO match_round (match_id, round_id) VALUES (1, 2);
INSERT INTO match_round (match_id, round_id) VALUES (1, 3);
INSERT INTO match_round (match_id, round_id) VALUES (2, 4);
INSERT INTO match_round (match_id, round_id) VALUES (2, 5);
INSERT INTO match_round (match_id, round_id) VALUES (2, 6);

INSERT INTO resultat (id, match_id) VALUES (1, 1);
INSERT INTO resultat (id, match_id) VALUES (2, 2);