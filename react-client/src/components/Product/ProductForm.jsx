import { useState } from 'react'

const BASE_URL = 'http://localhost:8080/api/product/'

const _product = {
	itemCode: '',
	description: '',
	price: 0,
	creatorUser: '',
	reasonDeactivation: '',
	suppliers: [
		{
			country: '',
			name: '',
		},
	],
	pricesReductions: [
		{
			reducedPrice: 0,
		},
	],
}
const ProductForm = ({ getProducts, setIsOpen }) => {

	const [product, setProduct] = useState(_product)

	//TODO *** ERROR al añadir la fecha de las reducciones ****
	const handleChange = (e) => {
		setProduct({
			...product,
			[e.target.name]: e.target.value,
		})
		console.log(e.target.name, e.target.value)
	}

	const saveProduct = async (e) => {
		e.preventDefault()

		const response = await fetch(BASE_URL, {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
			},
			body: JSON.stringify(product),
		})
		console.log('product: ', product)
		if (!response.ok) {
			throw new Error('Something went wrong')
		}
		const resProduct = await response.json()

		getProducts(resProduct)
		setIsOpen(false)
	}

	return (
		<>
			<div className='block p-6 py-2 '>
				<div className='flex space-x-4'>
					<div className='h-14 my-2 '>
						<label className='block text-sm font-semibold text-gray-600'>
							Item Code
						</label>
						<input
							name='itemCode'
							type='text'
							onChange={(e) => handleChange(e)}
							className='block w-full px-4 py-2 mt-2 text-indigo-700 bg-white border rounded-md focus:border-indigo-400 focus:ring-indigo-300 focus:outline-none focus:ring focus:ring-opacity-40'
						/>
					</div>
					<div className='h-14 my-2'>
						<label className=' block text-sm font-semibold text-gray-600'>
							User Creator
						</label>
						<input
							name='creatorUser'
							type='text'
							onChange={(e) => handleChange(e)}
							className='block w-full px-4 py-2 mt-2 text-indigo-700 bg-white border rounded-md focus:border-indigo-400 focus:ring-indigo-300 focus:outline-none focus:ring focus:ring-opacity-40'
						/>
					</div>
				</div>
			</div>
			<div className='block p-6 py-2'>
				<div className='h-14'>
					<label className='pt-2 block text-sm font-semibold text-gray-600'>
						Description
					</label>
					<input
						name='description'
						type='text'
						onChange={(e) => handleChange(e)}
						className='block w-full px-4 py-2 mt-2 text-indigo-700 bg-white border rounded-md focus:border-indigo-400 focus:ring-indigo-300 focus:outline-none focus:ring focus:ring-opacity-40'
					/>
				</div>
				<div className='h-14 my-6'>
					<label className='pt-2 block text-sm font-semibold text-gray-600'>
						Price
					</label>
					<input
						name='price'
						type='number'
						onChange={(e) => handleChange(e)}
						className='block w-full px-4 py-2 mt-2 text-indigo-700 bg-white border rounded-md focus:border-indigo-400 focus:ring-indigo-300 focus:outline-none focus:ring focus:ring-opacity-40'
					/>
				</div>
				<div className='h-14 my-6'>
					<label className='pt-2 block text-sm font-semibold text-gray-600'>
						Supplier
					</label>
					<input
						name='suppliers.name'
						type='text'
						onChange={(e) => handleChange(e)}
						className='block w-full px-4 py-2 mt-2 text-indigo-700 bg-white border rounded-md focus:border-indigo-400 focus:ring-indigo-300 focus:outline-none focus:ring focus:ring-opacity-40'
					/>
				</div>
			</div>

			<div className='mt-4 flex justify-center space-x-4'>
				<button
					className='rounded-md  uppercase border border-transparent bg-indigo-100 px-8 py-3 text-sm font-bold text-indigo-900 hover:bg-indigo-200 focus:outline-none focus-visible:ring-2 focus-visible:ring-indigo-500 focus-visible:ring-offset-2 ease-linear transition-all duration-150'
					onClick={saveProduct}
				>
					Save
				</button>
			</div>
		</>
	)
}

export default ProductForm
