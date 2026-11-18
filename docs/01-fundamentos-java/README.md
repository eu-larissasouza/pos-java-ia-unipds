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


---


## 2. Pilares da Orientação a Objetos na Prática
Laboratório prático: ```/modulo01-fundamentos/pilaresPoo```

* **O que resolve:** Modelar o domínio do negócio de forma segura, reutilizável e com regras claras de integridade.

### **Resumo estratégico dos 4 pilares:**

* **Abstração:** Capacidade de criar novos Tipos Abstratos de Dados (TADs) que representam o domínio da aplicação para reutilização sistêmica.


* **Encapsulamento:** Blinda a estrutura interna contra distorções (ex: impedir preços negativos). O uso de construtores e validações nos métodos modificadores garante a consistência.
  * **Gotcha / Imutabilidade:** Uma classe contendo apenas construtor e getters (sem setters) torna-se **imutável** durante todo o seu ciclo de vida. O uso de `record` nativamente encapsula esse comportamento gerando classes imutáveis de forma concisa.


* **Herança (`extends`):** Permite que classes derivadas (filhas) ampliem a estrutura e o comportamento de uma classe base (pai). O modificador `protected` gerencia o nível restrito de visibilidade nessa hierarquia.


* **Polimorfismo:** Depende diretamente da herança e da reescrita de comportamentos (ex: o método `debitar` adaptado em uma conta especial com limite). Permite referenciar tipos de forma genérica enquanto a JVM resolve dinamicamente em tempo de execução qual implementação específica executar com base na instância real.


---


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



---


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



---


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


---


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


---

## 7. Streams API (Java 8)

* **O que resolve:** Manipular coleções de dados de forma declarativa, fluida, concisa e com pouca verbosidade, permitindo encadear operações de filtragem, mapeamento, ordenação e reduções em uma única instrução.

### A. Benefícios e Conceitos-Chave

* **Abordagem Declarativa:** Em vez de focar no *como* fazer (loops imperativos como `for` e `if` aninhados), você descreve *o que* quer fazer com os dados.
* **Encadeamento de Operações:** Permite combinar filtros (`filter`), transformações (`map`), ordenações (`sorted`) e agregações de forma limpa.
* **Method References (`::`):** Introduzido no Java 8, o operador de dois pontos duplos serve como uma forma sintática mais curta e limpa de escrever expressões Lambda para chamar métodos que já existem.

### B. O Detalhe Crítico: `.toList()` vs. `.collect(Collectors.toList())`
* **`.toList()` (Java 16+):** Retorna uma lista **imutável** (tamanho fixo, não permite adição ou remoção de elementos após a criação, embora os atributos dos objetos internos possam ser alterados).
* **`.collect(Collectors.toList())`:** Retorna uma lista **mutável**, permitindo que você adicione ou remova elementos posteriormente se o seu fluxo de negócio exigir.


---

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


---

## 10. Introdução ao Gradle (Gerenciamento Moderno de Build)

* **O que resolve:** Superar a verbosidade do Maven e da estruturação em XML para gerenciar dependências e automatizar fluxos de build complexos utilizando uma abordagem declarativa e programática.

### A. O Problema do XML e a Evolução para o Gradle
* **A limitação do Maven:** Embora seja o mais popular, o Maven utiliza arquivos `pom.xml`. Como o XML **não é programável**, definir lógicas personalizadas e complexas de build exigia a criação de classes *Mojo* para escrever plugins externos — um processo altamente verboso.
* **Curiosidade histórica:** O próprio criador do *Ant* (precursor do Maven) comentou que transformar arquivos de configuração em uma quase-linguagem de script baseada em XML pode não ter sido a melhor escolha.
* **A solução do Gradle:** Uma ferramenta moderna que mantém a simplicidade declarativa na gestão de dependências (reutilizando o próprio repositório central do Maven), mas que é **totalmente programável**, permitindo escrever scripts e criar tasks customizadas utilizando **Groovy** ou **Kotlin**.

### B. O Gradle Wrapper e Comandos Essenciais
* **Gradle Wrapper (`gradlew`):** Garante a padronização do ambiente ao embutir uma versão específica do Gradle no próprio projeto, evitando conflitos de versão entre diferentes máquinas de desenvolvimento ou servidores de CI/CD.

* **Comandos úteis no dia a dia:**
  * `./gradlew build`: Compila, executa os testes e empacota a aplicação.
  * `./gradlew dependencies`: Exibe a árvore completa de dependências do projeto.
  * `./gradlew dependencies --configuration rC`: Lista e audita as configurações de dependências específicas do projeto.

---

## 11, 12 e 13. Aprofundando em Coleções

Laboratório prático: `aula2-aprofundando-em-colecoes`

- **O que resolve:** Entender por baixo do capô as estruturas de dados e escolher a variação certa para cada cenário (ordem, memória, auditoria, ciclo de vida).


- **A sacada (O que muda na prática):**
  - **`ArrayList` x `LinkedList`:** array contíguo (rápido por índice, custoso no meio) x nós encadeados (bom nas pontas, `get(i)` percorre a lista).
  - **Sets:** `HashSet` (sem ordem), `LinkedHashSet` (ordem de inserção), `TreeSet` (ordenado por árvore rubro-negra), `EnumSet` (vetor de bits, só para Enums).
  - **Maps:** mesma lógica dos Sets (`HashMap`, `LinkedHashMap`, `TreeMap`) + `EnumMap`, `WeakHashMap` e `IdentityHashMap`.
  - **No projeto:** a auditoria de preços usa `IdentityHashMap`. Como `ItemCardapio` é `record`, depois de alterar o preço 2.99 → 3.99 → 2.99 temos `==` **false**, mas `equals()` e `hashCode()` **true**. Num `HashMap`, as duas instâncias virariam uma chave só.


- **Gotcha / Trade-off arquitetural:**
  - O `WeakHashMap` só descarta a entrada se **nenhuma outra referência** segurar a chave, inclusive outros mapas. No projeto, o item antigo continua preso como chave na auditoria.
  - `TreeSet`/`TreeMap` decidem igualdade por `compareTo`/`Comparator`, não por `equals`.
  - `System.gc()` é só uma sugestão: demos de `WeakHashMap` não são determinísticas.

---

## 14, 15 e 16. Networking

Laboratório prático: `aula3-networking`

- **O que resolve:** Comunicar com outros sistemas pela rede usando só o JDK e entender o que existe por baixo do HTTP.


- **A sacada (O que muda na prática):**
  - **`HttpClient` (Java 11):** cliente HTTP moderno, `AutoCloseable` no Java 21. Substitui `URL`/`HttpURLConnection` (`new URL(String)` está obsoleto; use `URI.create`).
  - **`HttpServer` (`com.sun.net.httpserver`):** servidor HTTP pronto do JDK.
  - **`Socket` / `ServerSocket`:** HTTP "na mão". É texto: linha da requisição + cabeçalhos + **linha em branco** + corpo.
- **Snippet útil (cliente HTTP):**

  ```java
  try (HttpClient cliente = HttpClient.newHttpClient()) {
      var request = HttpRequest.newBuilder(URI.create("http://localhost:8000/itensCardapio.json")).build();
      HttpResponse<String> resposta = cliente.send(request, HttpResponse.BodyHandlers.ofString());
  }
  ```
- **Gotcha / Trade-off arquitetural:**
  - O servidor com `Socket` lê a requisição com `read()` + `available()`, uma heurística frágil. Servidores reais usam o `Content-Length`.
  - HTTP exige `\r\n` nas linhas; `println` usa o separador do sistema.
  - Esse servidor atende **um cliente por vez**: é o problema que a próxima aula resolve.

---

## 17, 18, 19 e 20. Concurrency

Laboratório prático: `aula4-concurrency`

- **O que resolve:** Atender vários clientes ao mesmo tempo sem travar o servidor e proteger dados compartilhados entre threads.


- **A sacada (O que muda na prática):** a evolução do servidor de Socket em quatro passos:
  1. **Sem threads:** um cliente por vez.
  2. **`new Thread(...)` por conexão:** o `accept()` só aceita e delega.
  3. **`Executors.newFixedThreadPool(50)`:** reaproveita threads; o excedente espera na fila.
  4. **`Database` thread-safe:** `HashMap` → `ConcurrentSkipListMap` (concorrente e ordenado por id).

### 📊 Benchmark do repositório (JMeter, `Thread.sleep(250)` simulando I/O)

| Cenário | Amostras | Tempo médio | Throughput |
| :-- | :-- | :-- | :-- |
| Sem threads | 333 | 21.242 ms | 3,6 req/s |
| Uma thread por request | 10.807 | 511 ms | 178,6 req/s |
| Pool fixo de 50 | 5.729 | 968 ms | 94 req/s |

- **Leitura:** thread por request é a mais rápida aqui, mas **sem limite**; o pool dá um teto previsível trocando um pouco de latência por segurança. As execuções foram feitas em momentos diferentes do código: compare a ordem de grandeza.


- **Snippet útil:**

  ```java
  try (ExecutorService pool = Executors.newFixedThreadPool(5)) { // close() espera as tarefas (Java 19+)
      pool.execute(() -> atendeCliente());
  }
  ```
- **Gotcha / Trade-off arquitetural:**
  - Coleção concorrente **não torna a classe thread-safe**: o `IdentityHashMap` da auditoria continua sem proteção.
  - `get` + `put` continuam não atômicos; use `merge`, `compute` ou `putIfAbsent`.
  - No `POST` do projeto, o `400 Bad Request` não tem `return` e o código segue para `requestChunks[1]` → `ArrayIndexOutOfBoundsException`.

---

## 21 e 22. JDBC

Laboratório prático: `aula5-jdbc`

- **O que resolve:** Persistir dados em banco relacional e trocar o armazenamento sem reescrever o servidor.


- **A sacada (O que muda na prática):**
  - **Interface `Database`** com duas implementações: `InMemoryDatabase` (o antigo `Map`) e `SQLDatabase` (MySQL). O servidor só conhece a interface.
  - **Fluxo:** `DriverManager.getConnection(...)` → `PreparedStatement` → `ResultSet`. O `try-with-resources` fecha os três.
  - **Mapeamento:** `DECIMAL` ↔ `BigDecimal`, `ENUM` ↔ `enum.valueOf(String)`, `NULL` ↔ `null`.
  - `executeQuery()` para SELECT; `execute()`/`executeUpdate()` para INSERT/UPDATE/DELETE.


- **Snippet útil (parâmetro, nunca concatenação):**

  ```java
  String sql = "SELECT nome, preco FROM item_cardapio WHERE id = ?";
  try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
       PreparedStatement ps = conn.prepareStatement(sql)) {
      ps.setLong(1, id);
      try (ResultSet rs = ps.executeQuery()) { /* ... */ }
  }
  ```
  

- **Gotcha / Trade-off arquitetural:**
  - O `script.sql` usa `use cardapio;`, mas o `docker-compose.yml` não cria o banco: crie antes (ou adicione `MYSQL_DATABASE`).
  - Uma conexão nova por chamada é cara; em produção usa-se **pool** (ex.: HikariCP). Usuário e senha no código: use variáveis de ambiente.
  - O `Main` insere o `id` 10: rodar duas vezes dá chave duplicada.
  - Três métodos do `SQLDatabase` estão como `UnsupportedOperationException("TODO")`.

---

## 23. Logging

Laboratório prático: `aula6-logs`

- **O que resolve:** Trocar `System.out.println` por registros com nível, destino e formato configuráveis sem mexer no código.


- **A sacada (O que muda na prática):**
  - **`java.util.logging`** já vem no JDK. Um `Logger` por classe: `Logger.getLogger(Classe.class.getName())`.
  - **Níveis:** `SEVERE` > `WARNING` > `INFO` > `CONFIG` > `FINE` > `FINER` > `FINEST`.
  - **Handlers** definem o destino (`ConsoleHandler`, `FileHandler`), cada um com seu nível. No projeto: console em `INFO`, arquivo em `FINE`.


- **Snippet útil:**

  ```java
  logger.finer(() -> "Method: " + method);                 // Supplier: só monta a String se FINER estiver ativo
  logger.log(Level.SEVERE, "Erro fatal no servidor", ex);  // passa a exceção: sai o stack trace
  ```
  
- **Gotcha / Trade-off arquitetural:**
  - O `logging.properties` em `resources` **não é carregado sozinho**: precisa de `-Djava.util.logging.config.file=...` (ou `LogManager.readConfiguration`).
  - `logger.fine("x" + y)` concatena mesmo com o nível desligado: use `Supplier`.
  - O `cardapio.0.log` está commitado no repo; log deve ir no `.gitignore`.
  - Em projetos reais é comum SLF4J + Logback; o JUL é a base nativa do JDK.

---

## 24. Internacionalização (i18n)

Laboratório prático: `aula7-i18n`

- **O que resolve:** Servir a mesma aplicação em vários idiomas e países: textos, moeda e datas.


- **A sacada (O que muda na prática):**
  - **`Locale`:** idioma + país (`pt_BR`, `en_US`). `Locale.of(...)` vale do Java 19 em diante.
  - **`ResourceBundle`:** textos em `mensagens_pt_BR.properties`; o arquivo base é `mensagens.properties`.
  - **`NumberFormat.getCurrencyInstance(locale)`** para moeda e **`DateTimeFormatter...withLocale(locale)`** para datas.
  - No projeto, `/` serve a página em pt-BR e `/en` em en-US.

| Item | pt-BR | en-US |
| :-- | :-- | :-- |
| Moeda (1234,50) | R$ 1.234,50 | $1,234.50 |
| Mês/ano | novembro/2026 | November/2026 |
| Categoria | Bebidas | Beverages |


- **Gotcha / Trade-off arquitetural:**
  - **Fallback do `ResourceBundle`:** com a JVM em `pt_BR`, pedir `Locale.US` devolveu "Bebidas", porque o Java tenta o locale padrão antes do arquivo base. Solução simples: criar `mensagens_en.properties`.
  - `NumberFormat` muda o formato e o símbolo, **não converte câmbio**.
  - O HTML concatena `item.nome()` sem escapar (risco de XSS), e os textos fixos da página seguem em português.

---

## 25. Serialização

Laboratório prático: `aula8-serialization`

- **O que resolve:** Transformar objetos em bytes (arquivo ou rede) e reconstruí-los depois.

- **A sacada (O que muda na prática):**
  - A classe implementa **`Serializable`**; `ObjectOutputStream.writeObject` grava e `ObjectInputStream.readObject` lê.
  - **`serialVersionUID`:** versão do "contrato" dos bytes. Se mudar e não bater com o do arquivo, a leitura falha com `InvalidClassException`.
  - **`transient`:** o campo fica fora dos bytes (volta `null`).
  - No projeto, `GET /itens-cardapio` com `Accept: application/x-java-serialized-object` devolve a lista serializada; sem ele, devolve JSON.

- **Snippet útil (leitura com filtro):**

  ```java
  try (var ois = new ObjectInputStream(new ByteArrayInputStream(bytes))) {
      ois.setObjectInputFilter(ObjectInputFilter.Config.createFilter("mx.florinda.cardapio.*;java.util.*;java.lang.*;java.math.*;!*"));
      var lida = ois.readObject();
  }
  ```
  
- **Gotcha / Trade-off arquitetural:**
  - **Segurança:** desserializar bytes não confiáveis pode executar código malicioso; use `ObjectInputFilter`. O cliente do projeto faz cast direto, sem filtro.
  - Formato só de Java, frágil entre versões e grande (1 item gerou 777 bytes, bem mais que o JSON). Para APIs, JSON é o padrão.
  - Todos os objetos aninhados precisam ser `Serializable`.
  - No servidor, `header.contains("Accept")` também casa `Accept-Language` e `Accept: */*`.

---
