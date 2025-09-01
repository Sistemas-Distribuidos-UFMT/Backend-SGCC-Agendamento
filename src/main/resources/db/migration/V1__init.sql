DO $$ BEGIN
    PERFORM 'public.tipo_pessoa'::regtype;
EXCEPTION
    WHEN undefined_object THEN
create type tipo_pessoa as enum ('CLIENTE', 'MEDICO', 'ATENDENTE', 'GESTOR');
END $$;

DO $$ BEGIN
    PERFORM 'public.situacao'::regtype;
EXCEPTION
    WHEN undefined_object THEN
create type situacao as enum ('AGENDADA', 'CANCELADA', 'REALIZADA');
END $$;

create table pessoa(
    codigo_pessoa uuid primary key,
    nome varchar(80) not null,
    email varchar(80) unique not null,
    telefone varchar(15),
    senha_criptografada varchar(256) not null,
    tipo_pessoa tipo_pessoa not null,
    data_atualizacao timestamp,
    data_criacao timestamp default current_timestamp
);

create table especialidade(
    codigo_especialidade uuid primary key,
    nome varchar(80) unique not null,
    descricao text,
    data_atualizacao timestamp,
    data_criacao timestamp default current_timestamp
);

create table medico (
    codigo_medico uuid primary key,
    codigo_pessoa uuid references pessoa(codigo_pessoa) unique not null,
    codigo_especialidade uuid references especialidade(codigo_especialidade) not null,
    crm varchar(50) unique not null,
    data_atualizacao timestamp,
    data_criacao timestamp default current_timestamp
);

create table expediente (
    codigo_expediente uuid primary key default gen_random_uuid(),
    codigo_medico uuid references medico(codigo_medico) not null,
    dia_semana smallint not null,
    hora_inicio time not null,
    hora_fim time not null,
    data_atualizacao timestamp,
    data_criacao timestamp default current_timestamp
);

create table consulta (
    codigo_consulta uuid primary key default gen_random_uuid(),
    codigo_pessoa uuid references pessoa(codigo_pessoa) not null,
    codigo_medico uuid references medico(codigo_medico) not null,
    data timestamp not null,
    situacao situacao,
    data_atualizacao timestamp,
    data_criacao timestamp default current_timestamp,
    CONSTRAINT medico_horario_unico UNIQUE (codigo_medico, data)
);