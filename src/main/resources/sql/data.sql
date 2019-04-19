INSERT INTO cinema.USER_ROLE VALUES (1,'ADMIN'), (2,'USER');

INSERT INTO cinema.MOVIE (NAME, DESCRIPTION, YEAR, DURATION) VALUES ('Avengers: Infinity War', 'The Avengers and their allies must be willing to sacrifice all in an attempt to defeat the powerful Thanos before his blitz of devastation and ruin puts an endTime to the universe', 2018, 149);
INSERT INTO cinema.MOVIE (NAME, DESCRIPTION, YEAR, DURATION) VALUES ('Aquaman', 'Arthur Curry learns that he is the heir to the underwater kingdom of Atlantis, and must step forward to lead his people and be a hero to the world.', 2018, 143);
INSERT INTO cinema.MOVIE (NAME, DESCRIPTION, YEAR, DURATION) VALUES ('Solo: A Star Wars Story', 'During an adventure into the criminal underworld, Han Solo meets his future co-pilot Chewbacca and encounters Lando Calrissian years before joining the Rebellion.', 2018, 135);
INSERT INTO cinema.MOVIE (NAME, DESCRIPTION, YEAR, DURATION) VALUES ('Fantastic Beasts: The Crimes of Grindelwald', 'The second installment of the "Fantastic Beasts" series featuring the adventures of Magizoologist Newt Scamander', 2018, 134);
INSERT INTO cinema.MOVIE (NAME, DESCRIPTION, YEAR, DURATION) VALUES ('Black Panther', 'T''Challa, heir to the hidden but advanced kingdom of Wakanda, must step forward to lead his people into a new future and must confront a challenger from his country''s past.', 2018, 134);

INSERT INTO cinema.SEAT (NUMBER) VALUES (1), (2), (3), (4), (5), (6), (7), (8), (9), (10);
INSERT INTO cinema.SEAT (NUMBER) VALUES (11), (12), (13), (14), (15), (16), (17), (18), (19), (20);

INSERT INTO cinema.TIME_SLOT (START_TIME, END_TIME) VALUES ('09:00','12:00'), ('12:00','15:00'), ('15:00','19:00');
INSERT INTO cinema.TIME_SLOT (START_TIME, END_TIME) VALUES ('19:00','22:00'), ('22:00','01:00');

