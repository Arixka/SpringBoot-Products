import { types } from '../types/types'

export const authReducer = (state = {}, action) => {
	switch (action.type) {
		case types.login:
			return {
				...state,
				logged: true,
				user: action.payload,
				token: action.token
			}

		case types.logout:
			return {
				logged: false,
			}

		default:
			return state
	}
}
