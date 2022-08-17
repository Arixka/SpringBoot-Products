import { useEffect, useState } from 'react'
import NewProduct from './NewProduct'
import ProductItem from './ProductItem'
import Search from '../Search'

const ProductList = () => {
	const BASE_URL = 'http://localhost:8080/api/product/all'

	const [products, setProducts] = useState([])
	const [loading, setLoading] = useState(true)
	const [listSearch, setListSearch] = useState([])

	const handleSearch = (e) => {
		filterTable(e.target.value)
	}

	const filterTable = (search) => {
		if (search === '') return getProducts()
		const filteredResult = listSearch.filter((product) => {
			const status = product.status.toLowerCase()
			const itemCode = product.itemCode.toLowerCase()
			const _search = search.toString().toLowerCase().trim()

			if (status.includes(_search) || itemCode.includes(_search)) {
				return product
			}
		})
		setProducts(filteredResult)
	}

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
			setListSearch(productsRest)
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
			<div className='container mx-auto my-8'>
				<div className='h-12 flex justify-between'>
					<Search handleSearch={handleSearch} />
					<NewProduct getProducts={getProducts} />
				</div>
			</div>
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
								<th className='px-6 py-3 '>
									<span className='flex space-x-4'>Actions</span>
								</th>
							</tr>
						</thead>
						{/* TODO error  Warning: validateDOMNesting(...): <div> cannot appear as a child of <tbody>. al abrir modales*/}
						{!loading && (
							<tbody>
								{products?.map((product) => (
									<ProductItem
										getProducts={getProducts}
										product={product}
										key={product.itemCode}
									/>
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
