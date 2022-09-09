/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.caribeedu.unibh.edaa.trabalho.pratico;

/**
 *
 * @author Edu
 */
public class TGerenciaDeContas {
    public TGerenciaDeContas() {
        Contas = new TContaBancaria[0];
    }
    
    private static TContaBancaria[] Contas;
    
    private static Boolean contaExiste(String nroConta) {
        for (TContaBancaria contaExistente : Contas) {
            if (contaExistente.obterNroConta().equals(nroConta))
                return true;
        }
        
        return false;
    }
    
    private static String gerarNovoNroConta() {
        final int menorNumero = 1;
        final int maiorNumero = 999;
        
        final int numeroPossivel = (int)(Math.random()*(maiorNumero - menorNumero + 1) + menorNumero);
        final String novoNroContaGerado = String.format("%03d", numeroPossivel);
        
        if (TGerenciaDeContas.contaExiste(novoNroContaGerado))
            return gerarNovoNroConta();
        else
            return novoNroContaGerado;
    }
    
    public static void novoCliente(String nome, String cpf) {
        int tamanhoListaContas = TGerenciaDeContas.Contas.length;
                
        var listaContasAtualizada = new TContaBancaria[tamanhoListaContas + 1];
        
        // Move as contas já cadastradas para uma nova lista maior
        System.arraycopy(TGerenciaDeContas.Contas, 0, listaContasAtualizada, 0, tamanhoListaContas);
                
        TContaBancaria novaConta = new TContaBancaria(TGerenciaDeContas.gerarNovoNroConta(), nome, cpf);
        
        listaContasAtualizada[tamanhoListaContas] = novaConta;
    }
    
    public static TResultadoOperacao transferir(String nroContaOrigem, String nroContaDestino, double valor) {
        if (valor < 0)
            return new TResultadoOperacao(false, "O valor inserido para transferência é inválido. Tente novamente.");
        
        TContaBancaria contaOrigem = TGerenciaDeContas.obterContaCliente(nroContaOrigem);
        
        if (!contaOrigem.valorEstaDisponivel(valor))
            return new TResultadoOperacao(false, "Você não tem saldo suficiente.");
                
        TContaBancaria contaDestino = TGerenciaDeContas.obterContaCliente(nroContaDestino);
        
        if (contaDestino == null)
            return new TResultadoOperacao(false, "A conta de destino da transferência não existe. Tente novamente.");
        
        contaOrigem.retirarValor(valor);
        contaDestino.depositarValor(valor);
        
        return new TResultadoOperacao(
                true, 
                String.format("Valor transferido com êxito. Seu saldo agora é de R$%.2f.", contaOrigem.obterSaldo())
        );
    }
    
    private static TContaBancaria obterContaCliente(String nroConta) {        
        for (TContaBancaria conta : Contas) {
            if (conta.obterNroConta().equals(nroConta))
                return conta;
        }
        
        return null;
    }
}
