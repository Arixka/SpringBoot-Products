import { Navigate, Routes, Route } from 'react-router-dom'
import Login from '../pages/Login'
import Product from '../pages/Product'

const AppRouter = () => {
	return (
		<>
			<Routes>
				<Route path='/*' element={<Navigate to='/login'/>} />
				<Route path='login' element={<Login />} />
				<Route path='product' element={<Product />} />
			</Routes>
		</>
	)
}

export default AppRouter
