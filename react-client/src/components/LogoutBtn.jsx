import { useContext } from 'react'
import { useNavigate } from 'react-router-dom'
import { AuthContext } from '../auth/context/AuthContext'

const LogoutBtn = () => {
	const { user, logout } = useContext(AuthContext)
	const navigate = useNavigate()
	const logoutUser = () => {
		logout()
		navigate('/', { replace: true })
	}
	return (
		<>
			{user && (
				<div className='text-center'>
					<button
						className='rounded-md  uppercase border border-grey-600  px-8 py-3 text-sm font-bold text-white  hover:text-black  focus:outline-none focus-visible:ring-2 focus-visible:ring-indigo-500 focus-visible:ring-offset-2 ease-linear transition-all duration-150'
						style={{ transition: 'all .15s ease' }}
						onClick={logoutUser}
					>
						Logout
					</button>
				</div>
			)}
		</>
	)
}

export default LogoutBtn
