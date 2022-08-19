import { Navigate, Routes, Route } from 'react-router-dom'
import Login from '../pages/Login'
import Product from '../pages/Product'
import PrivateRoute from './PrivateRoute'

const AppRouter = () => {
	return (
		<>
			<Routes>
				<Route path='/*' element={<Navigate to='/login' />} />
				<Route path='login' element={<Login />} />

				<Route
					path='product'
					element={
						<PrivateRoute>
							<Product />
						</PrivateRoute>
					}
				/>
				{/* <Route path='product' element={<Product />} /> */}
			</Routes>
		</>
	)
}

export default AppRouter
