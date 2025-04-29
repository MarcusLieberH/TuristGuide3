-- Indsæt data i TouristAttraction tabellen
INSERT INTO TouristAttraction (attraction_id, name, description, town, image) VALUES
                                                                                  (1, 'Tivoli Haven', 'Historisk forlystelsespark med traditionel charme', 'København', 'tivoli.png'),
                                                                                  (2, 'Segway Tours Copenhagen', 'Miljøvenlig guidet tur, der giver unikt indblik i København', 'København', NULL),
                                                                                  (3, 'Hop on – Hop off kanalrundfart', 'Fleksibel sejltur, hvor man i eget tempo udforsker København fra vandet', 'København', 'rundfart.png'),
                                                                                  (4, 'Den Lille Havfrue', 'Ikonisk bronzestatue inspireret af H.C. Andersens eventyr', 'København', 'havfrue.png'),
                                                                                  (5, 'Nyhavn', 'Farverigt havneområde med historiske facader, restauranter og barer', 'København', NULL),
                                                                                  (6, 'Fristaden Christiania', 'Alternativ bydel med selvstyre, kreativ kultur og kunstnerisk miljø', 'København', 'christiania.png'),
                                                                                  (7, 'Amalienborg Slot', 'Kongelig residens i rokokostil, centrum for ceremonielle begivenheder', 'København', 'slot.png'),
                                                                                  (8, 'Rosenborg Slot', 'Renæssanceslot og museum, der huser kongelige skatte og kronjuveler', 'København', 'rosenborg.png');

-- Indsæt eksempeldata i AttractionTags tabellen
INSERT INTO AttractionTags (tag_id, attraction_id, tag) VALUES
                                                            (1, 1, 'Forlystelser'),
                                                            (2, 1, 'Historie'),
                                                            (3, 1, 'Familie'),
                                                            (4, 2, 'Guidet tur'),
                                                            (5, 2, 'Oplevelse'),
                                                            (6, 3, 'Sejltur'),
                                                            (7, 3, 'Sightseeing'),
                                                            (8, 4, 'Skulptur'),
                                                            (9, 4, 'Vartegn'),
                                                            (10, 5, 'Restauranter'),
                                                            (11, 5, 'Historisk'),
                                                            (12, 6, 'Alternativ'),
                                                            (13, 6, 'Kunst'),
                                                            (14, 7, 'Kongeligt'),
                                                            (15, 7, 'Arkitektur'),
                                                            (16, 8, 'Museum'),
                                                            (17, 8, 'Historie');
