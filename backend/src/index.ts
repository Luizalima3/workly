import { Elysia } from 'elysia'
import { userController } from './modules/user/user.controller'

const app = new Elysia()
  .use(userController)
  .get('/', () => 'API Workly rodando')
  .listen(3000)

console.log(`🦊 Elysia rodando em http://localhost:${app.server?.port}`)