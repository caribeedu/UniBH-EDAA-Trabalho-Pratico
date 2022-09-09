/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.caribeedu.unibh.edaa.trabalho.pratico;

/**
 *
 * @author Edu
 */
public class TContaBancaria {
    public TContaBancaria(String nroConta, String agencia, String nomeCliente, String cpfCliente) {
        this.NroConta = nroConta;
        this.Agencia = agencia;
        
        this.NomeCliente = nomeCliente;
        this.CpfCliente = cpfCliente;
        
        Saldo = 0;
    }
        
    private final String NomeCliente;
    private final String CpfCliente;
    
    private final String NroConta;
    private final String Agencia;
    private double Saldo;
    
    public Boolean valorEstaDisponivel(double valorDesejado) {
        return valorDesejado < Saldo;
    }
    
    public String obterNomeCliente() {
        return NomeCliente;
    }
    
    public String obterNroConta() {
        return NroConta;
    }
    
    public String obterNroAgencia() {
        return Agencia;
    }
    
    public double obterSaldo() {
        return Saldo;
    }
    
    public TResultadoOperacao depositarValor(double valor) {
        if (valor < 0)
            return new TResultadoOperacao(false, "O valor inserido para depósito é inválido. Tente novamente.");
        
        this.Saldo += valor;
        
        return new TResultadoOperacao(
                true, 
                String.format("Valor depositado com êxito. Seu saldo agora é de R$%.2f.", Saldo)
        );
    }
    
    public TResultadoOperacao retirarValor(double valor) {
        if (valor < 0)
            return new TResultadoOperacao(false, "O valor inserido para retirada é inválido. Tente novamente.");
                
        if (!valorEstaDisponivel(valor))
            return new TResultadoOperacao(false, "Você não tem saldo suficiente.");
        
        this.Saldo -= valor;
        
        return new TResultadoOperacao(
                true, 
                String.format("Valor retirado com êxito. Seu saldo agora é de R$%.2f.", this.Saldo)
        );
    }
    
    public TResultadoOperacao transferirValor(String nroContaDestino, String agenciaContaDestino, double valor) {
        return TGerenciaDeContas.transferir(NroConta, Agencia, nroContaDestino, agenciaContaDestino, valor);
    }
}
