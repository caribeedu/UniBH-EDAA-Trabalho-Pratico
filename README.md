# Trabalho Prático de Estrutura de Dados e Análise de Algoritmos - Abstração de Contas Bancárias

## Introdução 
Este projeto tem como finalidade explorar o conceito de TADs (Tipos Abstratos de Dados). Neste, é utilizado o fluxo de um caixa eletrônico para realizar as operações nas abstrações de contas bancárias. 

## Execução

### Compilação
Esta aplicação foi desenvolvida através da IDE NetBeans, por este motivo é recomendado que a utilize para a execução devida do projeto (utilizando o método de compilação de projeto padrão).

### Uso
Uma vez com o projeto em execução, o programa irá disponibilizar o menu inicial do caixa eletrônico. 

#### Caixa Eletrônico

O caixa eletrônico é responsável por gerenciar as operações escolhidas e o cliente ativo (caso exista). É interessante notar que existem duas possibilidades de estado de funcionamento do caixa eletrônico:

* **Sem Cliente Ativo**
    * Neste primeiro caso de uso, é necessário cadastrar um novo cliente ou selecionar um cliente já cadastrado. Neste contexto não é possível realizar nenhuma operação bancária.

* **Com Cliente Ativo**
    * Neste segundo caso de uso, um cliente já foi selecionado previamente e é possível realizar as operações bancárias disponibilizadas em tela.

**Importante:** Ao início do programa, não existirá nenhuma conta bancária cadastrada e por este motivo é necessário realizar o cadastro de pelo menos:

* Uma conta bancária (para as operações de depósito, saque e exibição de saldo)
* Duas contas bancárias (para a operação de transferência)

#### Cadastro de Contas Bancárias

O cadastro de uma conta bancária pode ser realizado através do menu inicial, sem cliente ativo, utilizando a operação ***1 - Iniciar nova conta***.

Ao cadastrar uma conta bancária, serão gerados automaticamente um número de conta e um número de agência, que serão informados pelo programa ao término do cadastro.

Por padrão, toda nova conta aberta terá saldo zero.

#### Operações Bancárias

Uma vez com um cliente ativo no caixa (utilizar operação ***2 - Selecionar conta***), é possível realizar as operações bancárias:

* **3 - Depósito**
* **4 - Saque**
* **5 - Exibir Saldo**
* **6 - Transferência**

Uma vez com a operação selecionada, você será requisitado à informar os dados para cada operação, e então a gerência de contas e o próprio caixa realizarão as devidas validações necessárias e por fim, a execução da operação.

Caso deseje terminar a sessão do cliente ativo e voltar ao menu inicial, basta utilizar a operação ***0 - Sair da Conta***.

## Desenvolvimento

O trabalho foi dividido em quatro abstrações, estas em conjunto se compõe para o correto funcionamento do programa. Abaixo há o detalhamento de cada uma delas e seus fins:

### Caixa Eletrônico
#### Classe Abstraída: TCaixa

O caixa eletrônico, representado pela *classe abstraída **TCaixa***, tem as seguintes finalidades:

1. Controlar a existência de cliente ativo no caixa para validar as operações disponíveis para uso
2. Requisitar e processar os valores inseridos em cada operação
3. Tratar o fluxo do programa em relação à construção dos menus de operações
4. Executar as operações escolhidas, baseando-se no cliente ativo (caso exista), através da conta bancária do cliente

### Gerência de Contas
#### Classe Abstraída: TGerenciaDeContas

O setor de gerência de contas, representado pela *classe abstraída **TGerenciaDeContas***, tem as seguintes finalidades:

1. Obter contas de clientes através do número da conta e número da agência
2. Disponibilizar clientes para uso em operações
3. Validar a existência de clientes através do número da conta e número da agência
4. Armazenar e administrar todas as contas bancárias de clientes
5. Cadastrar novas contas bancárias
6. Realizar a transferência de valores entre clientes

### Conta Bancária
#### Classe Abstraída: TContaBancaria

A conta bancária, representada pela *classe abstraída **TContaBancaria***, tem as seguintes finalidades:

1. Realizar operações de depósito e saque
2. Exibir o saldo disponível em conta
3. Verificar se determinado valor está disponível em conta
4. Armazenar dados pessoais e bancários do cliente

### Resultado de Operação
#### Classe Abstraída: TResultadoOperacao

O resultado de cada operação realizada, representado pela *classe abstraída **TResultadoOperacao***, tem as seguintes finalidades:

1. Armazenar o status final de cada operação (sendo possível sucesso ou falha)
2. Armazenar e disponibilizar a mensagem final de resultado de cada operação

## Conclusão

Este projeto teve sua finalização de maneira pragmática utilizando o mínimo de abstrações possíveis e também levando em consideração um nível coerente de segregação de cada uma das abstrações.

Este, ainda, poderia ser melhorado com pouco esforço nos seguintes aspectos:

* Uma melhor organização do fluxo do caixa eletrônico, tanto no quesito funcional como técnico
* Adição de validações nos valores inseridos pelo usuário do sistema

Tendo em vista a finalidade do trabalho, onde a intenção é a implementação devida das abstrações e menor uso possível de métodos de alto nível, foi buscado utilizar apenas tipos primitivos, com laços de repetição simples, recursão e *arrays*. Deste modo, este atende seu fim nos quesitos de planejamento, orquestração e execução.
