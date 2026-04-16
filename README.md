# TopCar — Sistema de Peças Automotivas com Comunicação entre Processos

> Trabalho 1 da disciplina **QXD0043 - Sistemas Distribuídos**  
> Universidade Federal do Ceará – Campus de Quixadá  
> Professor: Rafael Braga

---

## 📋 Descrição

O **TopCar** é um sistema de gerenciamento de peças automotivas desenvolvido em Java, utilizado como base para implementar comunicação entre processos via **Sockets e Streams**, **Serialização** e **Representação Externa de Dados** (Capítulo 4 da disciplina).

O sistema modela um catálogo de peças de veículos (amortecedores, baterias, faróis, motores e pneus), pedidos e clientes, servindo como domínio de aplicação para os exercícios de comunicação distribuída.

---

## 🗂️ Estrutura do Projeto

```
src/
└── br/
    └── com/
        └── topcar/
            ├── model/
            │   ├── Peca.java                  # Classe base (POJO) para todas as peças
            │   ├── Pedido.java                # Representa um pedido de compra
            │   ├── ItemPedido.java            # Item individual de um pedido
            │   ├── storage/
            │   │   ├── Amortecedor.java       # Subclasse de Peca
            │   │   ├── Bateria.java           # Subclasse de Peca
            │   │   ├── Farol.java             # Subclasse de Peca
            │   │   ├── Motor.java             # Subclasse de Peca
            │   │   └── Pneu.java              # Subclasse de Peca
            │   ├── user/
            │   │   └── Cliente.java           # Representa um cliente com pedidos
            │   └── utils/
            │       └── Car.java               # Representa o veículo associado à peça
            └── CargaDeDadosStorage.java       # Dados de teste (5 clientes, pedidos e peças)
```

---

## 🧱 Modelo de Domínio

### `Peca` (classe base)
Representa uma peça automotiva genérica com os atributos:

| Atributo          | Tipo         | Descrição                        |
|-------------------|--------------|----------------------------------|
| `id`              | `int`        | Identificador único da peça      |
| `nome`            | `String`     | Nome descritivo                  |
| `valor`           | `BigDecimal` | Preço unitário                   |
| `data_fabricacao` | `LocalDate`  | Data de fabricação               |
| `carro`           | `Car`        | Veículo compatível com a peça    |

### Subclasses de `Peca`

| Classe         | Atributos extras                          |
|----------------|-------------------------------------------|
| `Amortecedor`  | `tipo` (hidráulico, a gás...), `posicao` (dianteiro, traseiro) |
| `Bateria`      | `capacidade` (Ah), `tecnologia` (chumbo-ácido, AGM, EFB)      |
| `Farol`        | `tipo` (alto, baixo, milha), `led` (boolean)                   |
| `Motor`        | `potencia` (cv), `torque` (kgfm)                               |
| `Pneu`         | `medida` (ex: 205/55R16), `classificacao` (A, B, C)            |

### `Pedido` e `ItemPedido`
Um `Pedido` agrupa uma lista de `ItemPedido`, onde cada item referencia uma `Peca`, a quantidade e o desconto aplicado.

### `Cliente`
Possui CPF, nome, idade e uma lista de `Pedido`s associados.

---

## ⚙️ Requisitos do Trabalho

### Exercício 2 — OutputStream customizado
Implementação de `PecaOutputStream` (subclasse de `OutputStream`) que serializa manualmente um array de objetos `Peca`, enviando pelo menos **3 atributos** como bytes para:
- `System.out` (saída padrão)
- `FileOutputStream` (arquivo local)
- Socket TCP (servidor remoto)

### Exercício 3 — InputStream customizado
Implementação de `PecaInputStream` (subclasse de `InputStream`) que lê e reconstrói os objetos gerados pelo exercício anterior a partir de:
- `System.in` (entrada padrão)
- `FileInputStream` (arquivo local)
- Socket TCP (servidor remoto)

### Exercício 4 — Serialização via Sockets
Comunicação cliente-servidor com empacotamento e desempacotamento de mensagens:
- Cliente serializa a requisição antes de enviar
- Servidor desempacota, processa e empacota a resposta
- Comunicação via **TCP** ou **UDP**

### Exercício 5 — Requisição de Peças (adaptado ao TopCar)
Aplicação distribuída de requisição de peças com:
- Login de clientes via **unicast TCP**
- Envio do catálogo de peças disponíveis via **unicast TCP**
- Cliente envia um objeto `Peca` como requisição ao servidor
- Notificações dos administradores (ex: novas peças, promoções) via **multicast UDP**
- Representação externa via **Protocol Buffers**, XML ou JSON
- Servidor **multi-threaded**

---

## 🚀 Como Executar

### Pré-requisitos
- Java 11+
- Maven ou compilação manual via `javac`

### Compilar
```bash
javac -d out -sourcepath src src/br/com/topcar/**/*.java
```

### Executar (exemplo — servidor TCP)
```bash
# Terminal 1 — Servidor
java -cp out br.com.topcar.server.PecaServer

# Terminal 2 — Cliente
java -cp out br.com.topcar.client.PecaClient
```

---

## 📦 Dados de Teste

A classe `CargaDeDadosStorage` fornece dados pré-carregados para testes:

- **8 veículos**: CrossFox, Gol, Corolla, Hilux, Civic, Mobi, Ranger, Sandero
- **5 amortecedores**, **5 baterias**, **5 faróis**, **5 motores**, **5 pneus**
- **15 itens de pedido** distribuídos em **5 pedidos**
- **5 clientes**: Matias Souza, Fernanda Lima, Ricardo Alves, Ana Paula Costa, Bruno Mendes

---

## 🏛️ Arquitetura de Comunicação

```
┌──────────────┐        TCP / UDP        ┌──────────────┐
│    Cliente   │ ──── request (serialized) ───► │   Servidor   │
│              │ ◄─── reply (serialized) ──── │ (multi-thread)│
└──────────────┘                         └──────────────┘
                           │
                    Multicast UDP
                    (notas admin)
                           │
                  ┌────────┴────────┐
                  │    Clientes     │
                  └─────────────────┘
```

---

## 📝 Observações

- A classe `Peca` e suas subclasses devem implementar `Serializable` para os exercícios de serialização via ObjectStream.
- Para o exercício de streams manuais (ex. 2 e 3), a serialização é feita byte a byte, sem uso de `ObjectOutputStream`.
- O campo `valorPedido` em `Pedido` está comentado e pode ser reativado para cálculo automático do total do pedido.

---

## 👨‍💻 Autores

Desenvolvido como parte da disciplina de **Sistemas Distribuídos** — UFC Quixadá.
