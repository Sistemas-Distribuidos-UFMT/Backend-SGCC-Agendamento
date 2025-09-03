DO $$
DECLARE

medico_carlos_id uuid;
    medico_ana_id uuid;
    medico_fernando_id uuid;
    medico_juliana_id uuid;
    medico_patricia_id uuid;

    paciente_id_1 uuid := gen_random_uuid();
    paciente_id_2 uuid := gen_random_uuid();
    paciente_id_3 uuid := gen_random_uuid();
    paciente_id_4 uuid := gen_random_uuid();
    paciente_id_5 uuid := gen_random_uuid();

    senha_texto_plano text := 'senha_forte_123';

BEGIN

SELECT m.codigo_medico INTO medico_carlos_id FROM medico m JOIN pessoa p ON m.codigo_pessoa = p.codigo_pessoa WHERE p.nome = 'Dr. Carlos Andrade';
SELECT m.codigo_medico INTO medico_ana_id FROM medico m JOIN pessoa p ON m.codigo_pessoa = p.codigo_pessoa WHERE p.nome = 'Dra. Ana Beatriz';
SELECT m.codigo_medico INTO medico_fernando_id FROM medico m JOIN pessoa p ON m.codigo_pessoa = p.codigo_pessoa WHERE p.nome = 'Dr. Fernando Guimarães';
SELECT m.codigo_medico INTO medico_juliana_id FROM medico m JOIN pessoa p ON m.codigo_pessoa = p.codigo_pessoa WHERE p.nome = 'Dra. Juliana Costa';
SELECT m.codigo_medico INTO medico_patricia_id FROM medico m JOIN pessoa p ON m.codigo_pessoa = p.codigo_pessoa WHERE p.nome = 'Dra. Patrícia Lima';


INSERT INTO pessoa (codigo_pessoa, nome, email, telefone, senha_criptografada, tipo_pessoa, data_criacao, data_atualizacao)
VALUES (gen_random_uuid(), 'Gestor Silva', 'gestor@sgcc.com', '65999990001', senha_texto_plano, 'GESTOR', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO pessoa (codigo_pessoa, nome, email, telefone, senha_criptografada, tipo_pessoa, data_criacao, data_atualizacao)
VALUES (gen_random_uuid(), 'Atendente Souza', 'atendente@sgcc.com', '65999990002', senha_texto_plano, 'ATENDENTE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO pessoa (codigo_pessoa, nome, email, telefone, senha_criptografada, tipo_pessoa, data_criacao, data_atualizacao)
VALUES
    (paciente_id_1, 'Mariana Oliveira', 'mariana.o@email.com', '65988881111', senha_texto_plano, 'CLIENTE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (paciente_id_2, 'Rafael Pereira', 'rafael.p@email.com', '65988882222', senha_texto_plano, 'CLIENTE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (paciente_id_3, 'Beatriz Almeida', 'beatriz.a@email.com', '65988883333', senha_texto_plano, 'CLIENTE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (paciente_id_4, 'Lucas Martins', 'lucas.m@email.com', '65988884444', senha_texto_plano, 'CLIENTE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (paciente_id_5, 'Isabela Santos', 'isabela.s@email.com', '65988885555', senha_texto_plano, 'CLIENTE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO consulta (codigo_pessoa, codigo_medico, data, situacao) VALUES
    (paciente_id_1, medico_carlos_id, '2025-10-06 09:00:00', 'AGENDADA'), -- Segunda
    (paciente_id_1, medico_carlos_id, '2025-10-08 15:00:00', 'AGENDADA'), -- Quarta
    (paciente_id_2, medico_carlos_id, '2025-10-13 10:00:00', 'AGENDADA'), -- Segunda
    (paciente_id_2, medico_carlos_id, '2025-10-15 16:00:00', 'AGENDADA'), -- Quarta
    (paciente_id_3, medico_carlos_id, '2025-10-20 09:30:00', 'AGENDADA'), -- Segunda
    (paciente_id_3, medico_carlos_id, '2025-10-22 14:30:00', 'AGENDADA'), -- Quarta
    (paciente_id_4, medico_carlos_id, '2025-10-27 11:00:00', 'AGENDADA'), -- Segunda
    (paciente_id_4, medico_carlos_id, '2025-10-29 17:00:00', 'AGENDADA'), -- Quarta
    (paciente_id_5, medico_carlos_id, '2025-11-03 08:30:00', 'AGENDADA'), -- Segunda
    (paciente_id_5, medico_carlos_id, '2025-11-05 15:30:00', 'AGENDADA'); -- Quarta

INSERT INTO consulta (codigo_pessoa, codigo_medico, data, situacao) VALUES
    (paciente_id_1, medico_ana_id, '2025-10-07 10:00:00', 'AGENDADA'), -- Terça
    (paciente_id_1, medico_ana_id, '2025-10-09 11:00:00', 'AGENDADA'), -- Quinta
    (paciente_id_2, medico_ana_id, '2025-10-14 14:00:00', 'AGENDADA'), -- Terça
    (paciente_id_2, medico_ana_id, '2025-10-16 15:00:00', 'AGENDADA'), -- Quinta
    (paciente_id_3, medico_ana_id, '2025-10-21 09:30:00', 'AGENDADA'), -- Terça
    (paciente_id_3, medico_ana_id, '2025-10-23 16:00:00', 'AGENDADA'), -- Quinta
    (paciente_id_4, medico_ana_id, '2025-10-28 10:30:00', 'AGENDADA'), -- Terça
    (paciente_id_4, medico_ana_id, '2025-10-30 11:30:00', 'AGENDADA'), -- Quinta
    (paciente_id_5, medico_ana_id, '2025-11-04 14:30:00', 'AGENDADA'), -- Terça
    (paciente_id_5, medico_ana_id, '2025-11-06 15:30:00', 'AGENDADA'); -- Quinta

INSERT INTO consulta (codigo_pessoa, codigo_medico, data, situacao) VALUES
    (paciente_id_1, medico_fernando_id, '2025-10-10 07:30:00', 'AGENDADA'), -- Sexta
    (paciente_id_2, medico_fernando_id, '2025-10-17 08:00:00', 'AGENDADA'), -- Sexta
    (paciente_id_3, medico_fernando_id, '2025-10-24 09:00:00', 'AGENDADA'), -- Sexta
    (paciente_id_4, medico_fernando_id, '2025-10-31 10:00:00', 'AGENDADA'), -- Sexta
    (paciente_id_5, medico_fernando_id, '2025-11-07 11:00:00', 'AGENDADA'), -- Sexta
    (paciente_id_1, medico_fernando_id, '2025-11-14 12:00:00', 'AGENDADA'), -- Sexta
    (paciente_id_2, medico_fernando_id, '2025-11-21 08:30:00', 'AGENDADA'), -- Sexta
    (paciente_id_3, medico_fernando_id, '2025-11-28 09:30:00', 'AGENDADA'), -- Sexta
    (paciente_id_4, medico_fernando_id, '2025-12-05 10:30:00', 'AGENDADA'), -- Sexta
    (paciente_id_5, medico_fernando_id, '2025-12-12 11:30:00', 'AGENDADA'); -- Sexta

INSERT INTO consulta (codigo_pessoa, codigo_medico, data, situacao) VALUES
    (paciente_id_1, medico_juliana_id, '2025-10-13 14:00:00', 'AGENDADA'), -- Segunda
    (paciente_id_2, medico_juliana_id, '2025-10-14 15:00:00', 'AGENDADA'), -- Terça
    (paciente_id_3, medico_juliana_id, '2025-10-15 16:00:00', 'AGENDADA'), -- Quarta
    (paciente_id_4, medico_juliana_id, '2025-10-20 17:00:00', 'AGENDADA'), -- Segunda
    (paciente_id_5, medico_juliana_id, '2025-10-21 18:00:00', 'AGENDADA'), -- Terça
    (paciente_id_1, medico_juliana_id, '2025-10-22 14:30:00', 'AGENDADA'), -- Quarta
    (paciente_id_2, medico_juliana_id, '2025-10-27 15:30:00', 'AGENDADA'), -- Segunda
    (paciente_id_3, medico_juliana_id, '2025-10-28 16:30:00', 'AGENDADA'), -- Terça
    (paciente_id_4, medico_juliana_id, '2025-10-29 17:30:00', 'AGENDADA'), -- Quarta
    (paciente_id_5, medico_juliana_id, '2025-11-03 13:30:00', 'AGENDADA'); -- Segunda

INSERT INTO consulta (codigo_pessoa, codigo_medico, data, situacao) VALUES
    (paciente_id_1, medico_patricia_id, '2025-10-16 08:30:00', 'AGENDADA'), -- Quinta
    (paciente_id_2, medico_patricia_id, '2025-10-17 09:30:00', 'AGENDADA'), -- Sexta
    (paciente_id_3, medico_patricia_id, '2025-10-23 10:00:00', 'AGENDADA'), -- Quinta
    (paciente_id_4, medico_patricia_id, '2025-10-24 11:00:00', 'AGENDADA'), -- Sexta
    (paciente_id_5, medico_patricia_id, '2025-10-30 09:00:00', 'AGENDADA'), -- Quinta
    (paciente_id_1, medico_patricia_id, '2025-10-31 10:30:00', 'AGENDADA'), -- Sexta
    (paciente_id_2, medico_patricia_id, '2025-11-06 11:30:00', 'AGENDADA'), -- Quinta
    (paciente_id_3, medico_patricia_id, '2025-11-07 08:00:00', 'AGENDADA'), -- Sexta
    (paciente_id_4, medico_patricia_id, '2025-11-13 09:00:00', 'AGENDADA'), -- Quinta
    (paciente_id_5, medico_patricia_id, '2025-11-14 10:00:00', 'AGENDADA'); -- Sexta

END $$;