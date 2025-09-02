DO $$
DECLARE

pessoa_id_1 uuid := gen_random_uuid();
    pessoa_id_2 uuid := gen_random_uuid();
    pessoa_id_3 uuid := gen_random_uuid();
    pessoa_id_4 uuid := gen_random_uuid();
    pessoa_id_5 uuid := gen_random_uuid();

    medico_id_1 uuid := gen_random_uuid();
    medico_id_2 uuid := gen_random_uuid();
    medico_id_3 uuid := gen_random_uuid();
    medico_id_4 uuid := gen_random_uuid();
    medico_id_5 uuid := gen_random_uuid();

    cardiologia_id uuid;
    dermatologia_id uuid;
    ortopedia_id uuid;
    pediatria_id uuid;
    ginecologia_id uuid;

BEGIN

SELECT codigo_especialidade INTO cardiologia_id FROM especialidade WHERE nome = 'Cardiologia';
SELECT codigo_especialidade INTO dermatologia_id FROM especialidade WHERE nome = 'Dermatologia';
SELECT codigo_especialidade INTO ortopedia_id FROM especialidade WHERE nome = 'Ortopedia';
SELECT codigo_especialidade INTO pediatria_id FROM especialidade WHERE nome = 'Pediatria';
SELECT codigo_especialidade INTO ginecologia_id FROM especialidade WHERE nome = 'Ginecologia';


INSERT INTO pessoa (codigo_pessoa, nome, email, telefone, senha_criptografada, tipo_pessoa, data_criacao, data_atualizacao)
VALUES (pessoa_id_1, 'Dr. Carlos Andrade', 'carlos.andrade@email.com', '65912345678', 'senha_forte_123', 'MEDICO', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO medico (codigo_medico, codigo_pessoa, codigo_especialidade, crm, data_criacao, data_atualizacao)
VALUES (medico_id_1, pessoa_id_1, cardiologia_id, 'CRM/MT 12345', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO expediente (codigo_medico, dia_semana, hora_inicio, hora_fim)
VALUES
    (medico_id_1, 2, '08:00:00', '12:00:00'),
    (medico_id_1, 4, '14:00:00', '18:00:00');


INSERT INTO pessoa (codigo_pessoa, nome, email, telefone, senha_criptografada, tipo_pessoa, data_criacao, data_atualizacao)
VALUES (pessoa_id_2, 'Dra. Ana Beatriz', 'ana.beatriz@email.com', '65987654321', 'senha_forte_123', 'MEDICO', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO medico (codigo_medico, codigo_pessoa, codigo_especialidade, crm, data_criacao, data_atualizacao)
VALUES (medico_id_2, pessoa_id_2, dermatologia_id, 'CRM/MT 54321', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO expediente (codigo_medico, dia_semana, hora_inicio, hora_fim)
VALUES
    (medico_id_2, 3, '09:00:00', '17:00:00'),
    (medico_id_2, 5, '09:00:00', '17:00:00');


INSERT INTO pessoa (codigo_pessoa, nome, email, telefone, senha_criptografada, tipo_pessoa, data_criacao, data_atualizacao)
VALUES (pessoa_id_3, 'Dr. Fernando Guimarães', 'fernando.guimaraes@email.com', '65911223344', 'senha_forte_123', 'MEDICO', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO medico (codigo_medico, codigo_pessoa, codigo_especialidade, crm, data_criacao, data_atualizacao)
VALUES (medico_id_3, pessoa_id_3, ortopedia_id, 'CRM/MT 67890', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO expediente (codigo_medico, dia_semana, hora_inicio, hora_fim)
VALUES
    (medico_id_3, 6, '07:00:00', '13:00:00');


INSERT INTO pessoa (codigo_pessoa, nome, email, telefone, senha_criptografada, tipo_pessoa, data_criacao, data_atualizacao)
VALUES (pessoa_id_4, 'Dra. Juliana Costa', 'juliana.costa@email.com', '65955667788', 'senha_forte_123', 'MEDICO', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO medico (codigo_medico, codigo_pessoa, codigo_especialidade, crm, data_criacao, data_atualizacao)
VALUES (medico_id_4, pessoa_id_4, pediatria_id, 'CRM/MT 13579', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO expediente (codigo_medico, dia_semana, hora_inicio, hora_fim)
VALUES
    (medico_id_4, 2, '13:00:00', '19:00:00'),
    (medico_id_4, 3, '13:00:00', '19:00:00'),
    (medico_id_4, 4, '13:00:00', '19:00:00');


INSERT INTO pessoa (codigo_pessoa, nome, email, telefone, senha_criptografada, tipo_pessoa, data_criacao, data_atualizacao)
VALUES (pessoa_id_5, 'Dra. Patrícia Lima', 'patricia.lima@email.com', '65924681357', 'senha_forte_123', 'MEDICO', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO medico (codigo_medico, codigo_pessoa, codigo_especialidade, crm, data_criacao, data_atualizacao)
VALUES (medico_id_5, pessoa_id_5, ginecologia_id, 'CRM/MT 24680', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO expediente (codigo_medico, dia_semana, hora_inicio, hora_fim)
VALUES
    (medico_id_5, 5, '08:00:00', '12:00:00'),
    (medico_id_5, 6, '08:00:00', '12:00:00');

END $$;