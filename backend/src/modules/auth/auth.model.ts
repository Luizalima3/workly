import { t, type UnwrapSchema } from 'elysia'

export const AuthModel = {
	signUpBody: t.Object({
		username: t.String(),
		password: t.String(),
	}),
	signUpResponse: t.Object({
		token: t.String(),
	}),
} as const

export type AuthModel = {
	[k in keyof typeof AuthModel]: UnwrapSchema<typeof AuthModel[k]>
}