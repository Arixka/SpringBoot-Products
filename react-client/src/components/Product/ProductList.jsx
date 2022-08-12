import { useEffect, useState } from 'react'
import NewProduct from './NewProduct'
import ProductItem from './ProductItem'

const ProductList = () => {
	const BASE_URL = 'http://localhost:8080/api/product/all'

	const [products, setProducts] = useState([])
	const [loading, setLoading] = useState(true)


	const getProducts = async () => {
		setLoading(true)
		try {
			const response = await fetch(BASE_URL, {
				method: 'GET',
				headers: {
					'Content-Type': 'application/json',
				},
			})

			const productsRest = await response.json()
			setProducts(productsRest)
		} catch (error) {
			console.log(error)
		}
		setLoading(false)
	}

	useEffect(() => {
		getProducts()
	}, [])

	return (
		<>

			<NewProduct getProducts={getProducts} />

			<div className='container mx-auto my-10'>
				<div className='shadow border-b'>
					<table className='w-full text-sm text-left text-gray-500 dark:text-gray-400 '>
						<thead className='text-gray-700 uppercase bg-gray-100 text-center'>
							<tr>
								<th className='px-6 py-3'>Code</th>
								<th className='px-6 py-3'>Description</th>
								<th className='px-6 py-3'>State</th>
								<th className='px-6 py-3'>Price</th>
								<th className='px-6 py-3'>Creation Date</th>
								<th className='px-6 py-3'>User Creator</th>
								<th className='px-6 py-3'>Actions</th>
							</tr>
						</thead>
						{!loading && (
							<tbody>
								{products?.map((product) => (
									<ProductItem product={product} key={product.itemCode} />
								))}
							</tbody>
						)}
					</table>
				</div>
			</div>
		</>
	)
}

export default ProductList
