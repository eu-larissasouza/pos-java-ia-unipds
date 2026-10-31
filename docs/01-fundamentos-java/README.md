# Módulo: Fundamentos Avançados de Java

## 1. A Evolução e Modernidade do Java (Java 21+)
* **O que resolve:** Quebrar o mito de que Java é uma linguagem antiga e engessada, mostrando sua adaptabilidade para alta performance e ecossistema moderno.


* **A sacada (O que muda na prática):** 
  * O Java possui cadência de lançamentos a cada 6 meses, além de ser linguagem, plataforma e especificação.
  * A comunidade OpenJDK utiliza iniciativas como o **Project Amber** para simplificar a sintaxe e trazer recursos modernos (como recursos em modo preview).

* **Snippet útil (Executando recursos modernos via terminal):**
  ```bash
  javac --enable-preview --release 21 Hello.java
  java --enable-preview Hello

*(Permite criar classes e métodos mais enxutos, como métodos `main` simplificados sem a assinatura padrão verbosa).*

## 2. Pilares da Orientação a Objetos na Prática

* **O que resolve:** Modelar o domínio do negócio de forma segura, reutilizável e com regras claras de integridade.

### **Resumo estratégico dos 4 pilares:**

* **Abstração:** Capacidade de criar novos Tipos Abstratos de Dados (TADs) que representam o domínio da aplicação para reutilização sistêmica.


* **Encapsulamento:** Blinda a estrutura interna contra distorções (ex: impedir preços negativos). O uso de construtores e validações nos métodos modificadores garante a consistência.
  * **Gotcha / Imutabilidade:** Uma classe contendo apenas construtor e getters (sem setters) torna-se **imutável** durante todo o seu ciclo de vida. O uso de `record` nativamente encapsula esse comportamento gerando classes imutáveis de forma concisa.


* **Herança (`extends`):** Permite que classes derivadas (filhas) ampliem a estrutura e o comportamento de uma classe base (pai). O modificador `protected` gerencia o nível restrito de visibilidade nessa hierarquia.


* **Polimorfismo:** Depende diretamente da herança e da reescrita de comportamentos (ex: o método `debitar` adaptado em uma conta especial com limite). Permite referenciar tipos de forma genérica enquanto a JVM resolve dinamicamente em tempo de execução qual implementação específica executar com base na instância real.
