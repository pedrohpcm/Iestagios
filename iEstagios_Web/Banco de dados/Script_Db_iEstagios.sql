select * from Vaga;
select * from c;
select * from Estagio;
select * from conta;
select * from aluno;
select * from curriculo;
select * from processoseletivo;
select * from concedentes;
select * from conta;
select * from telefone;
select * from documentos;


DELETE FROM Estagio WHERE idEstagio >4;

SELECT * FROM documentos WHERE idEstagio = 3;

INSERT INTO documentos(descricao,url,dataCriacao,idEstagio) VALUES('Termo de compromisso','http://res.cloudinary.com/gerardoneto/image/upload/v1512506919/kjpca0gmgimlz9ponyra.pdf','06-12-2017',3);

SELECT * FROM Telefone WHERE idTelefone =2;

SELECT * FROM Curriculo WHERE idCurriculo = 4;

ALTER Table documentos MODIFY url VARCHAR(100);

SELECT * FROM conta WHERE email = 'e@h.com' AND senha = '1234' and tipo = 'Concedente';

DELETE FROM processoseletivo WHERE idVaga >0;
DELETE FROM vaga WHERE idVaga >0;


SELECT * FROM Curriculo WHERE idCurriculo = 4;

UPDATE curriculo SET formacao = 'TADS', cursos = 'Marketing digital', habilidades = 'Fotografia', sobre = 'Sou estudante e empresário' WHERE idCurriculo = 4;


DROP TABLE concedente;
CREATE TABLE concedentes (id int not null PRIMARY KEY);
ALTER TABLE concedente ADD COLUMN id int not null PRIMARY KEY ;

INSERT INTO concedentes(razaoSocial,nome,cnpj,ramoDeAtividade,tipoConcedente,representante,cargo,idConta,idEndereco,idTelefone) 
VALUES ('Escola Alegria do Saber','Alegria do Saber LTDA','82837359000182','Ensino','Instituicao','Alvaro','Diretor',3,3,3);


INSERT INTO aluno(nome,cpf,rg,orgaoexpeditor,datanascimento,curso,periodo,ano,idendereco,idconta,idtelefone,idcurriculo)
VALUES('José Silva','228.531.234-54','003.309.139','ITEP','08-03-1996','TADS','3°','2018',2,2,2,4);

INSERT INTO processoseletivo(idVaga,idAluno,dataInicio) VALUES(8,1,'01-11-2017');


SELECT * FROM conta;







USE automall;

SELECT * FROM carro;


ALTER TABLE carro MODIFY idimagem VARCHAR (30);

ALTER TABLE carro ADD COLUMN idimagem VARCHAR (30);


INSERT INTO carro(marca,modelo,ano,url) VALUES ('FIAT','PALIO','2006','https://res.cloudinary.com/gerardoneto/image/upload/v1519596296/occth6kbypnxzzoghewk.jpg')


