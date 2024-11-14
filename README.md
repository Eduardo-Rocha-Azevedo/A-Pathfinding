# A* Pathfinding Documentação

Este projeto implementa uma visualização do algoritmo A* (A-Star) para encontrar o caminho mais curto em uma grade. Ele utiliza componentes gráficos do Java Swing para criar uma interface interativa onde o usuário pode definir pontos de início, objetivo e obstáculos. Este README contém uma documentação detalhada de cada classe para facilitar a compreensão e a manutenção do código.

## Índice

- [Introdução](#introdução)
- [Como Usar](#como-usar)
- [Classes](#classes)
  - [Node](#node)
  - [KeyHandler](#keyhandler)
  - [DemoPanel](#demopanel)
  - [Main](#main)

---

## Introdução

O algoritmo A* é amplamente utilizado para encontrar o caminho mais curto em jogos e aplicações de navegação. Ele calcula o caminho mais eficiente com base em custos de movimento entre pontos, permitindo a consideração de obstáculos na rota.

## Como Usar

1. Clone este repositório.
2. Compile e execute o projeto.  
3. Use o mouse para definir o ponto de início, objetivo e obstáculos.
4. Pressione "Enter" para iniciar a busca.

---

## Classes

### Node

A classe `Node` representa uma célula na grade. Cada nó tem um estado e aparência diferentes para indicar se é o ponto inicial, objetivo, um obstáculo ou parte do caminho. Esta classe herda de `JButton`, permitindo a interatividade.

#### Atributos

- **parent**: `Node` - Referência ao nó anterior, usada para reconstruir o caminho.
- **col, row**: `int` - Posições do nó na grade.
- **gCost, hCost, fCost**: `int` - Custos de movimento e heurística para o algoritmo A*.
- **start, goal, solid, open, checked**: `boolean` - Marcam o estado do nó (início, objetivo, obstáculo, aberto, verificado).

#### Métodos

- **Node(int col, int row)**: Construtor que define a posição do nó e configura seu visual inicial.
- **setAsStart()**: Define o nó como ponto de início (cor azul).
- **setAsGoal()**: Define o nó como ponto objetivo (cor amarela).
- **setSolid()**: Marca o nó como obstáculo (cor preta).
- **setAsOpen()**: Marca o nó como "aberto" para exploração.
- **setAsChecked()**: Define o nó como verificado (cor laranja).
- **setAsPath()**: Define o nó como parte do caminho encontrado (cor verde).
- **actionPerformed(ActionEvent e)**: Define o comportamento ao clicar no nó.

---

### KeyHandler

A classe `KeyHandler` gerencia eventos de teclado, como pressionamentos de teclas que iniciam a busca do caminho.

#### Atributos

- **dp**: `DemoPanel` - Referência ao painel de demonstração onde o algoritmo será executado.

#### Métodos

- **KeyHandler(DemoPanel dp)**: Construtor que armazena a referência de `DemoPanel`.
- **keyPressed(KeyEvent e)**: Verifica se a tecla "Enter" foi pressionada e, se sim, chama o método `search()` de `DemoPanel` para iniciar a busca.
- **keyReleased(KeyEvent e)**: Método vazio, mas necessário para implementação da interface.
- **keyTyped(KeyEvent e)**: Método vazio, mas necessário para implementação da interface.

---

### DemoPanel

A classe `DemoPanel` contém a interface visual e lógica principal para a execução do algoritmo A*. Ela gerencia a grade de nós e controla o algoritmo de pathfinding.

#### Atributos

- **node[][]**: `Node` - Matriz bidimensional que representa a grade.
- **startNode, goalNode**: `Node` - Referências para os nós de início e objetivo.
- **pathFound**: `boolean` - Indica se o caminho foi encontrado.

#### Métodos

- **DemoPanel()**: Construtor que configura a interface e inicializa a grade de nós.
- **resetGrid()**: Redefine a grade para seu estado inicial.
- **setStartNode(int col, int row)**: Define o nó inicial na posição especificada.
- **setGoalNode(int col, int row)**: Define o nó objetivo na posição especificada.
- **setSolidNode(int col, int row)**: Define um nó como sólido/obstáculo.
- **search()**: Executa o algoritmo A* para encontrar o caminho do nó inicial ao objetivo.
- **drawPath()**: Desenha o caminho encontrado, alterando a cor dos nós no caminho.

---

### Main

A classe `Main` contém o ponto de entrada do programa. Ela inicializa a janela da aplicação e exibe o painel de demonstração.

#### Métodos

- **main(String[] args)**: Ponto de entrada do programa. Inicializa a interface gráfica e o `DemoPanel`.

---

## Conclusão

Este projeto ilustra o funcionamento do algoritmo A* com uma interface interativa. Ao definir pontos de início, objetivo e obstáculos, você pode ver o algoritmo em ação e entender melhor como ele calcula o caminho mais curto. 

---

Desenvolvido como parte de um estudo sobre algoritmos de pathfinding e interface gráfica em Java.


https://github.com/user-attachments/assets/6102d0c2-a05a-44f2-9e0d-fca115fc6de1

![A_ Pathfinding 13_11_2024 12_04_18](https://github.com/user-attachments/assets/bdc16c30-6685-4eab-ab2a-667181263ae1)

