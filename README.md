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

# Execução da aplicação
1- Cadastrar Pautas (localhost:8080/votacao/v1/pauta/cadastrar_pauta)

Exemplo JSON (

{
"datetime":"2021-08-15 16:38:12",
"local": "Pauta 2"
}
)

2- Cadastrar Associados(localhost:8080/votacao/v1/associado/cadastrar_associado)

Exemplo JSON (

{
"nome":"Karla",
"sobrenome": "Iturriza Alvarez",
"cpf": 23924714878
}
)

3- Executar Schedule
