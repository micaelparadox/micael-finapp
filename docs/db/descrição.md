# Documentação do Banco de Dados Financeiro

## Entidades

### Categoria
Representa as diferentes categorias financeiras sob as quais as despesas e receitas podem ser classificadas.

- **Id** (integer): Chave primária, identificador único da categoria.
- **Descricao** (varchar): Descrição textual da categoria financeira.

### Pagamento
Detalhes dos pagamentos realizados ou pendentes.

- **Id** (integer): Chave primária, identificador único do pagamento.
- **DataVencimento** (date): Data de vencimento para o pagamento.
- **ValorPrevisto** (decimal): Valor previsto a ser pago.
- **ValorRealizado** (decimal): Valor efetivamente pago.
- **CodigoBarraReferencia** (varchar): Código de barras ou referência para o pagamento.
- **Status** (varchar): Status do pagamento (ex: 'Pago', 'Pendente').

### Receita
Registra as receitas recebidas.

- **Id** (integer): Chave primária, identificador único da receita.
- **DataPagamento** (date): Data em que a receita foi recebida.
- **ValorRecebido** (decimal): Valor da receita recebida.

### Despesa
Detalhes das despesas previstas e realizadas.

- **Id** (integer): Chave primária, identificador único da despesa.
- **CategoriaId** (integer): Chave estrangeira que liga à categoria da despesa.
- **DataPrevista** (date): Data prevista para a ocorrência da despesa.
- **ValorPrevisto** (decimal): Valor previsto da despesa.
- **ValorRealizado** (decimal): Valor efetivamente realizado da despesa.
- **Status** (varchar): Status da despesa (ex: 'Pago', 'Pendente').

### RegistroMensal
Registro de todas as despesas e receitas para cada mês.

- **Id** (integer): Chave primária, identificador único do registro mensal.
- **Mes** (varchar): Mês do registro.
- **Ano** (integer): Ano do registro.
- **TotalReceitas** (decimal): Total de receitas no mês.
- **TotalDespesas** (decimal): Total de despesas no mês.
- **SaldoFinal** (decimal): Saldo final no mês, calculado como TotalReceitas - TotalDespesas.

## Relacionamentos

- Uma **Categoria** pode estar associada a várias **Despesas**.
- Um **Pagamento** pode estar associado a várias **Despesas**.
- Várias **Receitas** contribuem para um **RegistroMensal**.
- Várias **Despesas** contribuem para um **RegistroMensal**.

## Diagrama

Um diagrama UML pode ser encontrado no arquivo `UML.svg` para uma representação visual das entidades e relacionamentos.

