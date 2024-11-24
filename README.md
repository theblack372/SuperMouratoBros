[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/rUa5vdmg)

# Relatório do Projeto - t05g04

## Título do Projeto:
Super Mourato & Bros.

## Descrição do Projeto:
Este projeto implementa um jogo em terminal utilizando a biblioteca Lanterna, uma ferramenta
para criação de interfaces gráficas baseadas em texto. O objetivo é fornecer uma experiência
interativa onde o jogador pode controlar personagens, realizar ações no mapa e interagir com
elementos dinâmicos, como o personagem Koopa que se move automaticamente no mapa.
O sistema é desenvolvido em Java e organiza-se seguindo princípios de design de software
como MVC (Model-View-Controller). A interface gráfica é baseada em caracteres e construída
para funcionar de forma fluida e responsiva em terminais.


## UML do projeto
![superMourato drawio (3)](https://github.com/user-attachments/assets/5fa1019c-7396-4247-a882-eef05b0e0a63)

## Featues Principais:
### 1. Mapa interativo:
- Um mapa 2D representado por caracteres no terminal.
- Dimensões configuráveis para permitir mapas personalizados.
- Capacidade de representar diferentes tipos de entidades, como jogadores e inimigos.
### 2. Controle de Jogador:
- Uso de comandos no teclado para controlar o personagem principal (arrow-keys).
- Suporte para ações básicas como movimentação e saída do jogo (q para sair).
### 3. Loop do Jogo:
- Um loop principal que atualiza o estado do jogo, processa entradas do jogador e
  redesenha a interface em intervalos regulares.
- Controle do tempo de execução do loop para garantir uma experiência suave (15fps
  para já).
### 4. Movimento do Mourato:
- Implementamos uma thread para o salto suave do Mourato, este pode partir os blocos
  todos até ao momento, mas no futuro apenas com o power-up isso será possivel.
- Um dos problemas neste momento é conseguir com que ele caia com um movimento
  suave, vai ser implementado no futuro.
### 5. Movimento do Koopa:
- Implementamos uma thread para o movimento do Koopa, este move-se de um lado para
  o outro do mapa, e quando chega a um dos limites, muda de direção.
### 6. Ganhar o Jogo:
- Para ganhar o jogo, o utilizador tem de evitar cair nos buracos do mapa e alcançar a
flag no fim do mapa representada por ‘|'.
- Se tal acontecer, neste momento, o gameLoop fecha e o programa termina. No futuro,
  vamos ter um menu inicial e final que vai ajudar com a experiência de jogo.

## Mockups:
Alguns mockups para este jogo foram feitos para conseguir representar algumas
features atuais ou futuras:
- Conseguir apanhar questionBlocks
![image](https://github.com/user-attachments/assets/016cba31-d1c5-447e-ada5-810e13ee19cc)
- Conseguir partir alguns blocos (aqui já com o powerup, ainda não implementado)
![image](https://github.com/user-attachments/assets/a7445fb8-bcdf-4e71-9ab2-89c214bbae99)
- Conseguir apanhar a flag no fim do mapa e acabar o jogo
![image](https://github.com/user-attachments/assets/e62f2f41-dc3d-40fb-9f91-dc90dec5efed)
- Morrer para o Koopa
![image](https://github.com/user-attachments/assets/27764c9b-a8ed-4272-943b-5db0d3d9a055)


## Futura Implementação:
- Queremos no futuro ter uma package de controller melhor organizada.
- Implementar power-ups para o mourato
- Mapas diferentes
- Terminal reativo ao movimento do mourato
- Menu para escolha de mapas
- Uma queda mais suave do mourato
- Game Overlay melhorada
