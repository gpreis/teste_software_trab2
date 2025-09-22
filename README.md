## Trabalho 2 - Técnicas de Teste de Software

Segundo trabalho desenvolvido para a disciplina de Teste de Software da Universidade de Caxias do Sul.

Utilizado como base um sistema bancário simples passado pelo professor, e realizamos as seguintes melhorias:

- Reestruturação total da arquitetura do projeto para uma arquitetura MVC.
- Adição de interface gráfica para utilização em vez do prompt de comando.
- Adição de persistência de dados com PostgreSQL.
- Implementação das funcionalidades de transferência de valores entre diferentes contas.
- Criação de testes unitários e de integração utilizando JUnit e Mockito.
- Realização de testes manuais de todas as funcionalidades.

## Requerimentos

Nessa atividade, iremos trabalhar com o software do Sistema Bancario utilizado anteriormente. Cada grupo deverá:

- Separar o código-fonte em cliente/servidor (frontend/backend). A separação pode ser feita pela criação de objetos ou usando web services ou Rest API. Dessa forma, as chamadas da parte cliente ficam separadas da camada de negócio.
- Implementar o uso das funcionalidades não usadas até o momento.
- Implementar uma nova funcionalidade a escolha.
- Planejar os testes a serem feitos, obrigatoriamente, ao menos aplicar as seis técnicas de teste de software escolhidas pelo seu grupo na tarefa anterior.
- Fazer o uso das ferramentas listadas necessárias para implementar os testes.
- Deve-se fazer o teste do fluxo principal das funcionalidades do Sistema Bancário na forma de um caso de teste / teste de cenário.

As funcionalidades do Sistema Bancário podem ser reimplementadas em C#, Python ou Node.JS se o grupo preferir.

#### Entregar

Deve-se organizar o código-fonte no github, bem como a explicação do que foi feito nesse projeto.

#### Apresentar

A Tarefa deverá ser apresentada na aula do dia 22 de setembro para o professor.

#### Inteligência Artificial

O trabalho deve ser de autoria dos alunos de cada grupo. O uso de Inteligência Artificial deve ser declarado qual ferramenta foi usada e com qual propósito.

## Observações

Para rodar os testes com Mockito, é necessário os seguintes argumentos na JVM:

```
	--add-opens java.base/java.lang=ALL-UNNAMED
```

## Desenvolvido por

Trabalho desenvolvido por:
- Gabriel Pedot Reis
- Klaus Bastian
- Vitor Voltolini Da Silva

## Uso de Inteligência Artificial

Para o desenvolvimento dos testes do trabalho, houve auxílio de _autocomplete_ do Copilot e do agente ChatGPT-4.1.
