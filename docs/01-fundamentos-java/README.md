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