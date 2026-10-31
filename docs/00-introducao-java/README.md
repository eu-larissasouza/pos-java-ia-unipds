# Módulo: Introdução ao Java

## 1. O Conceito Tríplice do Java: Linguagem, Plataforma e Especificação

* **O que resolve:** Entender que o Java não é apenas uma sintaxe de programação, mas um ecossistema padronizado regido por especificações formais.


* **A sacada (O que muda na prática):**
    * É uma **Linguagem** (sintaxe, orientação a objetos).
    * É uma **Plataforma** (a JVM e as APIs padrão que rodam em cima dela).
    * É uma **Especificação** (gerenciada por processos formais via **JSRs** - Java Specification Requests e **JEPs** - JDK Enhancement Proposals). 

> Empresas diferentes (como Oracle, Azul, Eclipse Temurin) implementam essa especificação, e ferramentas como o [WhichJDK.com](https://whichjdk.com/) ajudam a escolher a distribuição ideal para produção.


## 2. Arquitetura de Execução: Javac, JVM, JRE e JDK

* **O que resolve:** Diferenciar claramente o papel de cada componente para saber o que instalar no ambiente de desenvolvimento vs. servidor de produção.


* **Diferença rápida:**
    * **`javac`:** Compila o código fonte (`.java`) em bytecode (`.class`). O famoso *“Write once, run anywhere”* acontece porque o bytecode roda em qualquer máquina que tenha uma JVM.

    * **JRE (Java Runtime Environment):** Focado em **executar** aplicações. Traz a JVM e as bibliotecas padrão.
  
    * **JDK (Java Development Kit):** Focado em **desenvolver e operar**. Inclui o JRE + o compilador (`javac`) + ferramentas essenciais de diagnóstico/empacotamento:
      
        * `javap`: Desmontador de bytecode (ótimo para entender o que o compilador gerou por baixo dos panos).
        * `javadoc`: Geração de documentação.
        * Gerenciadores de pacotes/arquivos (`jar`).


* **Gotcha / Trade-off em Produção:** 


Em ambientes de microsserviços modernos em containers (Docker), instalar uma JDK completa em vez de uma JRE (ou usar imagens otimizadas apenas com o runtime necessário) pode inflar desnecessariamente o tamanho da imagem e a superfície de segurança.