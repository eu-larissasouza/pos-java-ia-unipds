package mx.florinda.cardapio;

import com.sun.net.httpserver.HttpServer;

import java.io.InputStream;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class LabNetworking {

    public static void main(String[] args) throws Exception {
        // 1) Servidor HTTP pronto do JDK (com.sun.net.httpserver)
        HttpServer servidor = HttpServer.create(new InetSocketAddress(8001), 0);
        servidor.createContext("/ola", exchange -> {
            byte[] corpo = "{\"mensagem\":\"olá do servidor\"}".getBytes(StandardCharsets.UTF_8);
            exchange.getResponseHeaders().add("Content-Type", "application/json; charset=UTF-8");
            exchange.sendResponseHeaders(200, corpo.length);
            try (var os = exchange.getResponseBody()) {   // fechar o body encerra a resposta
                os.write(corpo);
            }
        });
        servidor.start();

        // 2) Cliente HTTP (java.net.http) — AutoCloseable a partir do Java 21
        try (HttpClient cliente = HttpClient.newHttpClient()) {
            var request = HttpRequest.newBuilder(URI.create("http://localhost:8001/ola")).build();
            HttpResponse<String> resposta = cliente.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("HttpClient -> " + resposta.statusCode() + " " + resposta.body());
        }

        // 3) A mesma chamada "na mão", com Socket: HTTP é só texto com linha em branco no fim
        try (Socket socket = new Socket("localhost", 8001)) {
            PrintStream out = new PrintStream(socket.getOutputStream());
            out.print("GET /ola HTTP/1.1\r\nHost: localhost\r\nConnection: close\r\n\r\n");
            out.flush();
            InputStream in = socket.getInputStream();
            System.out.println("\nSocket ->\n" + new String(in.readAllBytes(), StandardCharsets.UTF_8));
        }

        servidor.stop(0);
    }
}
