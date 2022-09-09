/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.caribeedu.unibh.edaa.trabalho.pratico;

/**
 *
 * @author Edu
 */
public class TResultadoOperacao {
    public TResultadoOperacao(Boolean sucesso, String retorno) {
        this.Sucesso = sucesso;
        this.Retorno = retorno;
    }
    
    private final Boolean Sucesso;
    private final String Retorno;
    
    public String obterMensagem()  {
        if (this.Sucesso)
            return String.format("Operação realizada com sucesso. %s", Retorno);
        else
            return String.format("Não foi possível completar a operação. %s", Retorno);
    }
}
