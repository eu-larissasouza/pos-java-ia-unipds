package main;

import core.ContaBancaria;
import core.ContaEspecial;

public class Main {
    public static void main(String[] args) {
        ContaBancaria c1,c2;
        c1 = new ContaBancaria(123, "Isidro");
        c2 = new ContaEspecial(124, "Jose", 200);

        c1.creditar(100);
        c2.creditar(100);

        System.out.println(c1);
        System.out.println(c2);


        if(c2.debitar(150)){
            System.out.println("Débito efetuado");
            System.out.println(c2);
        }else {
            System.out.println("Saldo insuficiente para conta " +c2.getNumero());
        }
    }
}