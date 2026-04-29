import { Elysia, t } from 'elysia'

let usuarios = [
  {
    id: 1,
    nome: 'Maria',
    email: 'maria@email.com',
    telefone: '83999999999',
    endereco: 'João Pessoa'
  }
]

export const userController = new Elysia().group('/user', (app) =>
  app
    .get('/', () => usuarios)
    .get('/:id', ({ params }) => {
      const usuario = usuarios.find((u) => u.id === Number(params.id))
      return usuario ?? { message: 'Usuário não encontrado' }
    })
    .post(
      '/',
      ({ body }) => {
        const novoUsuario = {
          id: usuarios.length + 1,
          ...body
        }

        usuarios.push(novoUsuario)
        return novoUsuario
      },
      {
        body: t.Object({
          nome: t.String(),
          email: t.String(),
          telefone: t.String(),
          endereco: t.String()
        })
      }
    )
)