# language: pt
Funcionalidade: API que faz a manipulação de dados.
  API Rest, na qual podera retornar todos os dados
  ou buscar, atualizar e deletar por qualquer dado por ID.

#1
  Cenario: Cria registros, com a requisição POST.
    Quando Roda a API, acessando sua URL /project/data/create os dados sao criados
      | id    | 2                    |
      | nome  | Rodrigo              |
      | cpf   | 500.123.456-18       |
      | email | rodrigoFEI@gmail.com |
    Entao Quando é feito uma requisição POST para /project/data os registros sao retornados

#2
  Cenario: Retorna todos os registros, com a requisição GET.
    Quando Roda a API, acessando sua URL /project/data os dados aparecem
    Entao Quando é feito uma requisição GET para /project/data os registros sao retornados

#3
  Cenario: Retorna todos os registros, com a requisição GET.
    Quando Acessando a URL /project/data/2 com Id
    Entao  Feito uma requisição GET para /v1/pessoas/2 retorna o registro do Id

#4
  Cenario: Retorna o registro atualizado, com a requisição PUT.
    Quando Acessando a URL /project/data/1 com Id para atualizar
      | email | amandaFEI@gmail.com |
    Entao  Atualiza o registro solicitado para Id /project/data/1

#5
  Cenario: Deletando registro com a requisição DELETE.
    Quando Acessando a URL /project/data/0 com Id para deletar
    Entao  Deleta o registro solicitado por Id /project/data/0


