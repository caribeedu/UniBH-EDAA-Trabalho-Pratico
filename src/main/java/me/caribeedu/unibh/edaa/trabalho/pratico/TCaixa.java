/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package me.caribeedu.unibh.edaa.trabalho.pratico;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Edu
 */
public class TCaixa {
    private static TContaBancaria ContaAtiva;
    private static final Scanner Input = new Scanner(System.in);
    
    public static void main(String[] args) {
        try {
            while(true) {
                imprimirCabecalho();
                selecionarOperacao();

                System.out.println();
                System.out.println("Pressione ENTER para voltar ao menu inicial.");
                System.in.read();
            }
        } catch (IOException ex) {
            Logger.getLogger(TCaixa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void selecionarOperacao() {
        final String operacaoSair = "0 - Sair da Conta";
        
        final String operacaoNovaConta = "1 - Iniciar nova conta";
        final String operacaoSelecionarConta = "2 - Selecionar conta";
        
        final String operacaoDepositar = "3 - Depositar valor";
        final String operacaoRetirar = "4 - Retirar valor";
        final String operacaoSaldo = "5 - Exibir saldo";
        final String operacaoTransferir = "6 - Tranferir valor";
        
        if (existeClienteAtivo()) {
            System.out.println(
                    String.format(
                            "%s\n%s\n%s\n%s\n%s\n",
                            operacaoDepositar,
                            operacaoRetirar,
                            operacaoSaldo,
                            operacaoTransferir,
                            operacaoSair
                    )
            );
        }
        else {
            System.out.println(
                    String.format(
                            "%s\n%s\n",
                            operacaoNovaConta,
                            operacaoSelecionarConta
                    )
            );
        }
                
        System.out.print("Escolha uma das opera????es e pressione ENTER: ");
        
        int operacaoSelecionada = Input.nextInt();
        
        System.out.println();
        
        if (existeClienteAtivo()) {
            switch(operacaoSelecionada) {
                case 0 -> sairConta();
                case 3 -> depositarValorConta();
                case 4 -> retirarValorConta();
                case 5 -> exibirSaldoConta();
                case 6 -> transferirValorContas();
                default -> System.out.println("Opera????o inserida inv??lida.");
            }        
        }
        else {
            switch(operacaoSelecionada) {
                case 1 -> cadastrarCliente();
                case 2 -> selecionarConta();
                default -> System.out.println("Opera????o inserida inv??lida.");             
            }        
        }        
    }
    
    private static void cadastrarCliente() {        
        System.out.println("==============CADASTRO=============");
        System.out.println("Informe os dados abaixo");        
        System.out.println("===================================");
        
        System.out.println("Informe o nome completo:");
        String nome = Input.next();
        System.out.println("Informe o CPF:");
        String cpf = Input.next();
                
        System.out.println();
        TContaBancaria contaAberta = TGerenciaDeContas.novoCliente(nome, cpf);

        System.out.println(
                String.format(
                        "A conta foi criada com ??xito.\nNro. da Conta: %s\nAg??ncia: %s",
                        contaAberta.obterNroConta(),
                        contaAberta.obterNroAgencia()
                )
        );
    }
    
    private static void depositarValorConta() {        
        if (!existeClienteAtivo()) {
            System.out.println("Erro no sistema. Nenhum cliente selecionado.");
            return;
        }
        
        System.out.println("==============DEP??SITO=============");
        System.out.println("Informe o valor de dep??sito desejado");        
        System.out.println("===================================");
        System.out.print("Valor desejado:\nR$");
        
        double valor = Input.nextDouble();
        
        TResultadoOperacao resultado = ContaAtiva.depositarValor(valor);
        
        System.out.println();
        System.out.println(resultado.obterMensagem());
    }
    
    private static void retirarValorConta() {        
        if (!existeClienteAtivo()) {
            System.out.println("Erro no sistema. Nenhum cliente selecionado.");
            return;
        }
        
        System.out.println("===============SAQUE==============");
        System.out.println("Informe o valor de saque desejado");        
        System.out.println("===================================");
        System.out.print("Valor desejado:\nR$");
        
        double valor = Input.nextDouble();
        
        TResultadoOperacao resultado = ContaAtiva.retirarValor(valor);
        
        System.out.println();
        System.out.println(resultado.obterMensagem());
    }
    
    private static void exibirSaldoConta() {                
        if (!existeClienteAtivo()) {
            System.out.println("Erro no sistema. Nenhum cliente selecionado.");
            return;
        }
        
        System.out.println("================SALDO==============");
        System.out.println("Confira o seu saldo abaixo");        
        System.out.println("===================================");
        System.out.println(
                String.format(
                    "Saldo em conta: R$%s",
                    String.format("%.2f", ContaAtiva.obterSaldo())
                )
        );
    }
    
    private static void transferirValorContas() {        
        if (!existeClienteAtivo()) {
            System.out.println("Erro no sistema. Nenhum cliente selecionado.");
            return;
        }
        
        System.out.println("===========TRANSFER??NCIA===========");
        
        if (TGerenciaDeContas.obterQuantidadeContas() < 2) {
            System.out.println("N??o existem outros clientes na carteira banc??ria. Cadastre um novo cliente antes de continuar.");
            return;
        }
        
        System.out.println("Informe a conta de destino e o valo\nr de transfer??ncia desejado");        
        System.out.println("===================================");
                
        System.out.println("Contas dispon??veis para transfer??ncia:");
        System.out.println(TGerenciaDeContas.obterContasTransferencia(ContaAtiva.obterNroConta(), ContaAtiva.obterNroAgencia()));
        
        System.out.println("N??mero da conta de destino:");        
        String nroContaDestino = Input.next();
        
        System.out.println("Ag??ncia da conta de destino:");        
        String nroAgenciaDestino = Input.next();
        
        System.out.print("Valor desejado:\nR$");        
        double valor = Input.nextDouble();
          
        TResultadoOperacao resultado = ContaAtiva.transferirValor(nroContaDestino, nroAgenciaDestino, valor);
        
        System.out.println();
        System.out.println(resultado.obterMensagem());
    }
    
    private static void selecionarConta() {
        System.out.println("===============ENTRAR===============");
        if (TGerenciaDeContas.obterQuantidadeContas() == 0) {
            System.out.println("N??o existem clientes na carteira banc??ria. Cadastre um novo cliente antes de continuar.");
            return;
        }
        
        System.out.println(TGerenciaDeContas.obterContasDisponiveis());
        
        System.out.println("Insira o N??mero da Conta:");
        String nroConta = Input.next();
        
        System.out.println("Insira a Ag??ncia:");        
        String nroAgencia = Input.next();
        
        TContaBancaria conta = TGerenciaDeContas.obterContaCliente(nroConta, nroAgencia);
        
        if (conta == null) {
            System.out.println("Conta n??o encontrada. N??o foi poss??vel prosseguir.");
            return;
        }
        
        ContaAtiva = conta;
        
        System.out.println();
        System.out.println("Voc?? entrou na conta com ??xito.");
    }
        
    private static void sairConta() {
        System.out.println("===============SAIR===============");
        System.out.println("Voc?? saiu da conta com ??xito.");
        
        ContaAtiva = null;
    }
    
    private static void imprimirCabecalho() {
        Boolean existeClienteSelecionado = existeClienteAtivo();
        
        System.out.println("===============CAIXA===============");
        
        if (existeClienteSelecionado) {
            System.out.println(String.format("Seja bem-vindo, %s", ContaAtiva.obterNomeCliente()));
            System.out.println(String.format("Nro. Conta: %s - Ag??ncia: %s", ContaAtiva.obterNroConta(), ContaAtiva.obterNroAgencia()));
        }
        else 
            System.out.println("Ol??, seja bem-vindo");
        
        System.out.println("===================================");
        System.out.println("Selecione uma opera????o:");
    }
    
    private static Boolean existeClienteAtivo() {
        return ContaAtiva != null;
    }
}
