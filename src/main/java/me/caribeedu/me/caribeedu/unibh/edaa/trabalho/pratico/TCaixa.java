/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package me.caribeedu.me.caribeedu.unibh.edaa.trabalho.pratico;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Edu
 */
public class TCaixa {
    public TCaixa() {
        ContaAtiva = null;
    }
    
    private static TContaBancaria ContaAtiva;
    private static final Scanner Input = new Scanner(System.in);
    
    public static void main(String[] args) {
        while(true) {
            try {
                Utilitarios.LimparTela();
            } catch (IOException | InterruptedException ex) {
                Logger.getLogger(TCaixa.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            imprimirCabecalho();
            
            if (existeClienteAtivo())
                selecionarOperacao();
            else
                selecionarConta();
            
            System.out.println("Pressione ENTER para voltar ao menu inicial.");
            Input.next();
        }
    }
    
    private static void selecionarOperacao() {
    }
    
    private static void selecionarConta() {
    }
    
    private static void imprimirCabecalho() {
        Boolean clienteAtivo = existeClienteAtivo();
        
        System.out.println("===============CAIXA===============");
        
        if (clienteAtivo)
            System.out.println("Selecione a conta que deseja utilizar");
        else 
            System.out.println(String.format("Seja bem-vindo, %s", ContaAtiva.NomeCliente));
        
        System.out.println("===================================");
        
        if (clienteAtivo)
            System.out.println("Contas cadastradas:");
        else 
            System.out.println("Selecione uma operação:");
    }
    
    private static Boolean existeClienteAtivo() {
        return ContaAtiva != null;
    }
}
