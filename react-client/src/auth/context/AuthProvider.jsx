import { useReducer } from 'react'
import { AuthContext } from './AuthContext'
import { authReducer } from './authReducer'
import { useNavigate } from 'react-router-dom'
import { types } from '../types/types'

const BASE_URL = 'http://localhost:8080/api/auth/login'

const init = () => {
	const token = localStorage.getItem('token')
	return {
		logged: !!token,
		token,
	}
}
const AuthProvider = ({ children }) => {
	const [authState, dispatch] = useReducer(authReducer, {}, init)
	const navigate = useNavigate()

	const login = async (userLogin) => {
		const response = await fetch(BASE_URL, {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
			},
			body: JSON.stringify(userLogin),
		})
		if (!response.ok) {
			throw new Error('Something went wrong')
		}
		const res = await response.json()
		console.log(res)
		const action = {
			type: types.login,
			payload: userLogin.username,
			token: res.jwtToken,
		}
		localStorage.setItem('token', JSON.stringify(res.jwtToken))
		dispatch(action)
		navigate('/product', { replace: true })
	}

	const logout = () => {
		localStorage.removeItem('token')
		const action = { type: types.logout }
		dispatch(action)
	}

	return (
		<AuthContext.Provider
			value={{ ...authState, login: login, logout: logout }}
		>
			{children}
		</AuthContext.Provider>
	)
}

export default AuthProvider
