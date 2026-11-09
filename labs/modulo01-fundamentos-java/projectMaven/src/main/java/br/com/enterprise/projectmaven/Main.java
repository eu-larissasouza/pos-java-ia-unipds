package br.com.enterprise.projectmaven;

import com.google.gson.Gson;

public class Main {

    public static void main(String[] args) {
        Produto produto = new Produto(12345, "Computador", 1500.0, 5);

        // =========================================================================
        // USANDO DEPENDÊNCIA EXTERNA VIA MAVEN (Google Gson)
        // A biblioteca 'com.google.gson' foi declarada no pom.xml e baixada
        // automaticamente pelo Maven a partir do repositório central.
        // =========================================================================
        Gson gson = new Gson();

        // Serialização: converte um objeto Java em formato JSON String
        String jsonOutput = gson.toJson(produto);

        System.out.println("JSON Serializado:");
        System.out.println(jsonOutput);
    }
}