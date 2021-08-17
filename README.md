# Sessao de uma Votacao

API REST para fazer uma sessao de votacao em uma pauta dada.
Votacao de um associado em uma pauta dada, votando SIM ou NAO.
Resultados da sessao de votacao na pauta. Total de eleitores, Total SIM e Total NAO

# Tecnologias
 1- Java 1.8
 
 2- Maven
 
 3- Spring boot 2.5.3
 
 4- JPA
 
 5- MySql

 6- Kafka

 7- URI Versioning

 8- JUnit 5

#Kafka configuração
1- Instalar apache-zookeeper-3.7.0 (Com as configuraçoes por defeto.)

2- Instalar kafka com 2.8.0 (Com as configuraçoes por defeto.)

3- Criar topico resultado-sessao-votacao
(kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic resultados_sessao_votacao)

4- Run zookeeper (zkServer.cmd)

5- Run kafka (kafka-server-start.bat server.properties)

6- Opcional: Se pode instalar o kafka tool para olhar o tópico. 

# Execução da aplicação
1- CREATE SCHEMA `sessao_votacao` DEFAULT CHARACTER SET utf8mb4 ;

2- Cadastrar Pautas (localhost:8080/votacao/v1/pauta/cadastrar_pauta)

Exemplo JSON (

{
"datetime":"2021-08-15 16:38:12",
"local": "Pauta 2"
}
)

3- Cadastrar Associados(localhost:8080/votacao/v1/associado/cadastrar_associado)

Exemplo JSON (

{
"nome":"Karla",
"sobrenome": "Iturriza Alvarez",
"cpf": 23924714878
}
)

4- Executar Schedule
