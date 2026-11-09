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
Laboratório prático: ```/modulo01-fundamentos/pilaresPoo```

* **O que resolve:** Modelar o domínio do negócio de forma segura, reutilizável e com regras claras de integridade.

### **Resumo estratégico dos 4 pilares:**

* **Abstração:** Capacidade de criar novos Tipos Abstratos de Dados (TADs) que representam o domínio da aplicação para reutilização sistêmica.


* **Encapsulamento:** Blinda a estrutura interna contra distorções (ex: impedir preços negativos). O uso de construtores e validações nos métodos modificadores garante a consistência.
  * **Gotcha / Imutabilidade:** Uma classe contendo apenas construtor e getters (sem setters) torna-se **imutável** durante todo o seu ciclo de vida. O uso de `record` nativamente encapsula esse comportamento gerando classes imutáveis de forma concisa.


* **Herança (`extends`):** Permite que classes derivadas (filhas) ampliem a estrutura e o comportamento de uma classe base (pai). O modificador `protected` gerencia o nível restrito de visibilidade nessa hierarquia.


* **Polimorfismo:** Depende diretamente da herança e da reescrita de comportamentos (ex: o método `debitar` adaptado em uma conta especial com limite). Permite referenciar tipos de forma genérica enquanto a JVM resolve dinamicamente em tempo de execução qual implementação específica executar com base na instância real.


## 3. Estruturas de Dados e Collections Framework (List, Set e Map)
Laboratório prático: ```/modulo01-fundamentos/estruturasDados```

* **O que resolve:** Escolher a estrutura de dados correta com base no comportamento de unicidade, ordenação e, principalmente, na complexidade de tempo de busca (Big O) para evitar gargalos de performance.

### A. Interface `List` (Listas)
* **Comportamento:** Permite elementos duplicados e mantém a ordem de inserção (respeita a ordem dos elementos). Permite inserção/remoção em qualquer parte, mas **não valida a presença prévia** de elementos.
* **Principais Implementações:**
  * `ArrayList`: A mais utilizada para acesso indexado rápido.
  * `LinkedList`: Alocação de memória sob demanda.
  * `Vector`: Semelhante ao `ArrayList`, porém possui **sincronicidade nativa** gerenciada pela JVM para operações multithread (garante que apenas uma thread manipule os dados por vez de forma segura, embora traga custo de performance).

### B. Interface `Set` (Conjuntos)
* **Comportamento:** Baseado no conceito matemático de conjuntos. **Não permite duplicatas**.
* **O Requisito Crítico:** Para garantir unicidade e posicionamento correto, os objetos inseridos devem obrigatoriamente implementar:
  * `equals()`: Essencial para garantir que não existam objetos duplicados.
  * `hashCode()`: Utilizado para definir o espalhamento e a posição na estrutura.

### C. Interface `Map` (Dicionários / Chave-Valor)
* **Comportamento:** Organizado em pares de Chave-Valor. A implementação mais famosa é o `HashMap`. Permite valores duplicados, **desde que as chaves sejam exclusivas**.

---

### 📊 O Poder da Escolha: Benchmark de Busca (List vs. Map)

* **O Problema (Complexidade Assintótica / Big O):** O Big O representa o **pior caso** (ex: o elemento buscado é o último ou não existe).
  * A busca em uma `List` possui complexidade linear de ordem **$O(N)$**: quanto maior a lista, maior o tempo de percurso.
  * A busca em um `Map` (hash map) calcula a posição do elemento de forma indexada, mantendo o tempo praticamente constante, independentemente do volume.


* **Resultados do Benchmark Prático:**

| Quantidade de Elementos | Tempo de Busca na `List` | Tempo de Busca no `Map` |
| :--- | :--- | :--- |
| **1.000** | 27 ms | 5 ms |
| **10.000** | 95 ms | 3 ms |
| **100.000** | 1.394 ms | 3 ms |


* **Gotcha / Trade-off arquitetural:**
  * O `HashMap` utiliza uma estrutura de espalhamento (*hashing*).
  * Embora o `Map` seja imbatível para cenários com muitas buscas diretas por chave, **nem sempre ele faz sentido**. Se o seu cenário exige percorrer sequencialmente ou se o volume de dados é pequeno e não justifica o overhead de cálculo de hash, uma `List` simples ainda pode atender perfeitamente. A decisão técnica deve ponderar o volume de dados e o tipo de operação predominante (escrita vs. leitura/busca).



## 4. APIs Essenciais da Plataforma Java

* **O que resolve:** Aproveitar o ecossistema nativo da linguagem para lidar com nulos de forma segura, manipulação moderna de datas, introspecção de código (Reflection), metadados (Annotations), processamento de texto e criptografia sem depender de bibliotecas externas excessivas.

### 1. Optional (Java 8)
* **O que resolve:** Evitar o clássico e indesejado `NullPointerException` (NPE) de forma declarativa.
* **A sacada (O que muda na prática):** Funciona como um invólucro (*wrapper*) que indica a presença ou ausência de um valor. Permite encadear regras de negócio e tratamentos na mesma linha, eliminando a poluição de múltiplos blocos `if (objeto != null)`.

### 2. Date and Time API (Java 8)
* **O que resolve:** Substituição definitiva do legado `java.util.Date`.
* **A sacada:** O uso de `LocalDate`, `LocalTime` e classes associadas traz **segurança tipada** e suporte nativo e robusto para manipulação de datas, horas e fusos horários.
* 🔗 *Referência:* [Documentação Oficial DateTimeFormatter](https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html)[cite: 1]

### 3. Reflection API (Java 1.1)
* **O que resolve:** Inspecionar dinamicamente classes, métodos, atributos e tipos em tempo de execução.
* **A sacada:** Conhecida carinhosamente como a API para "hackear classes", é a base utilizada exaustivamente por frameworks corporativos (como Spring e Hibernate) para mapeamento, injeção de dependências e serialização. Permite extrair metadados, invocar métodos e acessar atributos diretamente.
* 🔗 *Referência:* [Guia de Java Reflection da Oracle](https://www.oracle.com/technical-resources/articles/java/javareflection.html)[cite: 1]

### 4. Annotation Processing (Criação de Anotações Customizadas)
* **O que resolve:** Transformar metadados em código executável ou em regras ativas, indo além de simples comentários visuais.
* **Conceitos-chave essenciais:**
  * **`@Target`:** Define onde a anotação pode ser aplicada (classes, métodos, campos, etc.).
  * **`@Retention` (Política de Retenção):** Define até quando a anotação estará disponível:
    * `SOURCE`: Descartada após a compilação.
    * `CLASS`: Armazenada no bytecode, mas ignorada pela JVM em execução.
    * `RUNTIME`: Disponível em tempo de execução para ser lida via *Reflection*.

### 5. RegEx (Expressões Regulares)
* **O que resolve:** Poder de processamento e extração avançada de padrões em strings.
* **Exemplo de Aplicação Prática:** Em um cenário de *Web Crawler* (programa automatizado que navega pela internet para mapear e indexar conteúdos de páginas web), o uso de RegEx permite varrer o código bruto coletado e extrair cirurgicamente apenas as tags ou conteúdos desejados de forma filtrada.

### 6. Security (Criptografia com `Cipher`)
* **O que resolve:** Proteção de dados sensíveis diretamente com recursos nativos do Java.
* **A sacada:** A biblioteca `Cipher` possibilita implementar rotinas de criptografia e descriptografia de campos ou dados confidenciais com alta confiabilidade.



## 5. Manipulação de Arquivos e Entrada/Saída (I/O, NIO e NIO.2)

* **O que resolve:** Escolher a API de I/O correta com base no volume de dados, garantindo performance de leitura, segurança na manipulação de caminhos e controle de concorrência.

### As Três Gerações de I/O em Java

#### 1. Java I/O (Desde o Java 1.0)
* **Abordagem:** Baseada em *streams* (fluxos) orientados a byte (`InputStream`/`OutputStream`) ou caractere (`Reader`/`Writer`).
* **Característica:** É **bloqueante (blocking I/O)** — uma thread fica estritamente aguardando a operação de I/O concluir.
* **Uso:** Ideal para operações simples e arquivos pequenos.

#### 2. Java NIO (Desde o Java 1.4)
* **Abordagem:** Baseada em Buffers e Canais (`Channels`) em vez de fluxos contínuos.

* **Característica:** Suporta I/O não bloqueante e multiplexação com `Selectors` (excelente para servidores de alta concorrência).

* **Uso:** Extremamente eficiente para leitura e escrita de grandes volumes de dados ou fluxos de rede.

#### 3. Java NIO.2 (Desde o Java 7)
* **Abordagem:** Focada na produtividade do desenvolvedor, introduzindo a interface `java.nio.file.Path` (substituindo o antigo `File`).

* **Recursos:** Suporte a sistemas de arquivos virtuais (como ZIPs), monitoramento de diretórios em tempo real (`WatchService`), operações atômicas e manipulação robusta de diretórios.

---

### 📊 Benchmark Prático: Leitura de Arquivo de 200 MB

* **O Problema:** Diferentes abordagens geram impactos drásticos de performance e consumo de memória dependendo do tamanho do arquivo.

| Abordagem | Tempo de Execução (200 MB) |
| :--- | :--- |
| **Java I/O clássico** (`BufferedReader`) | 687 ms |
| **Java NIO** (`FileChannel` + `ByteBuffer`) | **192 ms (O mais rápido)** |
| **Java NIO.2** (`Files.readAllLines`) | 691 ms |

**Gotchas e Trade-offs Arquiteturais:**

* **O perigo do `Files.readAllLines`:** Ele carrega **todo o arquivo de uma só vez para a memória**, o que pode estourar a memória (`OutOfMemoryError`) se aplicado a arquivos massivos. Contudo, para arquivos pequenos, sua sintaxe é a mais prática do mercado.


* **O poder do `FileChannel` com Buffer:** O ganho absurdo de performance do NIO se dá porque trabalhamos com canais diretos e blocos de memória definidos por um buffer (ex: alocar `8192` bytes). Quanto melhor dimensionado o buffer, mais blocos lemos de forma otimizada com poucas iterações de sistema.



## 6. Tratamento de Erros e Exceções

* **O que resolve:** Controlar desvios de fluxo causados por comportamentos anormais, garantindo a resiliência da aplicação, o fechamento seguro de recursos e a criação de regras de negócio customizadas.

### A. Erros (`Error`) vs. Exceções (`Exception`)
* **`Error`:** Problemas críticos da Máquina Virtual (JVM) ou do ambiente (ex: `OutOfMemoryError`, corrupção de hardware). São anormais e geralmente **fatais**, impossibilitando recuperação pela aplicação.
* **`Exception`:** Comportamentos anormais que **podem ser tratados e recuperados** pela lógica do sistema. Dividem-se em:
  * **Checked (Verificadas):** Obrigam o tratamento ou a declaração explícita na assinatura do método (`throws`). Indicam cenários recuperáveis externos (ex: arquivo não encontrado).
  * **Unchecked (Não verificadas / `RuntimeException`):** Não exigem tratamento forçado em tempo de compilação. Geralmente refletem falhas de programação ou mau uso pelo usuário (ex: `NullPointerException`, `ArithmeticException`).

### B. Boas Práticas e Recursos Modernos de Tratamento

* **Ordem dos `catch`:** Sempre trate as exceções **da mais específica para a mais genérica** (evitando que uma exceção genérica como `Exception` capture tudo antes de chegar nas específicas).
* **Multi-catch:** Permite capturar múltiplas exceções em um único bloco de tratamento utilizando o operador pipe (`|`), reduzindo a verbosidade.
* **`finally` vs. `try-with-resources` (Java 7+):**
  * O bloco `finally` garante a execução de rotinas de limpeza (como fechar conexões ou arquivos), independentemente do fluxo.
  * O **`try-with-resources`** dispensa a chamada manual do `.close()`. Qualquer classe que implemente `AutoCloseable` (como `Scanner`, sockets e arquivos) é fechada automaticamente pela JVM de forma elegante e segura.
* **Lançamento e Customização (`throw` e `throws`):**
  * `throw`: Utilizado para instanciar e disparar uma exceção de forma ativa (ideal para criar exceções de negócio customizadas, que podem ser extendidas de `RuntimeException` para maior flexibilidade ou de `Exception` para fluxos validados).
  * `throws`: Declaração na assinatura do método informando quais exceções verificadas ele pode propagar.



## 7. Streams API (Java 8)

* **O que resolve:** Manipular coleções de dados de forma declarativa, fluida, concisa e com pouca verbosidade, permitindo encadear operações de filtragem, mapeamento, ordenação e reduções em uma única instrução.

### A. Benefícios e Conceitos-Chave

* **Abordagem Declarativa:** Em vez de focar no *como* fazer (loops imperativos como `for` e `if` aninhados), você descreve *o que* quer fazer com os dados.
* **Encadeamento de Operações:** Permite combinar filtros (`filter`), transformações (`map`), ordenações (`sorted`) e agregações de forma limpa.
* **Method References (`::`):** Introduzido no Java 8, o operador de dois pontos duplos serve como uma forma sintática mais curta e limpa de escrever expressões Lambda para chamar métodos que já existem.

### B. O Detalhe Crítico: `.toList()` vs. `.collect(Collectors.toList())`
* **`.toList()` (Java 16+):** Retorna uma lista **imutável** (tamanho fixo, não permite adição ou remoção de elementos após a criação, embora os atributos dos objetos internos possam ser alterados).
* **`.collect(Collectors.toList())`:** Retorna uma lista **mutável**, permitindo que você adicione ou remova elementos posteriormente se o seu fluxo de negócio exigir.



## 8. Configuração de Ambiente
* **Foco:** Configuração e padronização da IDE (IntelliJ IDEA / Eclipse), JDKs e variáveis de ambiente para desenvolvimento professional Java.

---

## 9. Maven para Gestão de Dependências

* **O que resolve:** Automatizar o ciclo de vida da aplicação (build, compilação, testes, empacotamento em JAR/WAR) e resolver centralizadamente o download e a gestão de dependências externas sem necessidade de gerenciamento manual de bibliotecas.

### A. Contexto Histórico e Funcionamento

* **O Problema Pré-2004:** O ecossistema Java possuía uma vasta oferta de bibliotecas de terceiros, mas a inclusão e o controle de versões em projetos eram feitos manualmente (baixando arquivos `.jar` e configurando caminhos de build), o que gerava incompatibilidades e complexidade.
* **A Solução do Maven:** A comunidade padronizou o gerenciamento criando o **Repositório Central (Maven Central)**. Através de um arquivo declarativo em XML (`pom.xml`), a ferramenta identifica, baixa automaticamente na versão correta e gerencia a árvore de dependências transitivas da aplicação.
* 🔗 *Referência:* [Maven Central Repository](https://mvnrepository.com/repos/central)

### B. O Arquivo `pom.xml` (Project Object Model)

* **`groupId`:** Identificador da organização ou domínio reverso (ex: `br.com.enterprise`).
* **`artifactId`:** Nome do projeto ou módulo específico (ex: `projectmaven`).
* **`version`:** Versão atual do artefato (ex: `1.0.0-SNAPSHOT`).
* **`dependencies`:** Bloco declarativo onde são listadas as bibliotecas de terceiros (como o Google Gson).


