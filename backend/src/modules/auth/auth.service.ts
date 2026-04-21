import { status } from 'elysia'

import type { AuthModel } from './auth.model'

export abstract class Auth {
	public static async signUp({ username, password }: AuthModel["signUpBody"]) {
		//TODO
	}
}