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