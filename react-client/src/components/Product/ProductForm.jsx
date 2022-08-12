import { useState } from 'react'

const BASE_URL = 'http://localhost:8080/api/product/'

const _product = {
	itemCode: '',
	description: '',
	price: 0,
	creatorUser: '',
	supplierName: '',
	supplierCountry: '',
	priceReduction: 0,
	reductionStartDate: '',
	reductionEndDate: '',
}
const ProductForm = ({ getProducts, setIsOpen }) => {
	const [product, setProduct] = useState(_product)

	//TODO *** ERROR al añadir la fecha de las reducciones ****
	const handleChange = (e) => {
		setProduct({
			...product,
			[e.target.name]: e.target.value,
		})
	}

	const saveProduct = async (e) => {
		e.preventDefault()
		console.log(product)

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
	//TODO itemCode y Description obligatorios
	return (
		<>
			<div className='block p-6 py-2 mt-2'>
				<label className='block text-sm font-semibold text-gray-600'>
					Item Code
				</label>
				<div className='flex space-x-4'>
					<div className='h-14'>
						<input
							placeholder='Item Code'
							name='itemCode'
							type='text'
							onChange={(e) => handleChange(e)}
							className='block w-full px-4 py-2 mt-2 text-indigo-700 bg-white border rounded-md focus:border-indigo-400 focus:ring-indigo-300 focus:outline-none focus:ring focus:ring-opacity-40'
						/>
					</div>
					<div className='h-14'>
						<input
							placeholder='User Creator'
							name='creatorUser'
							type='text'
							onChange={(e) => handleChange(e)}
							className='block w-full px-4 py-2 mt-2 text-indigo-700 bg-white border rounded-md focus:border-indigo-400 focus:ring-indigo-300 focus:outline-none focus:ring focus:ring-opacity-40'
						/>
					</div>
				</div>
			</div>
			<div className='block p-6 py-2 '>
				<div className='h-14'>
					<input
						placeholder='Description'
						name='description'
						type='text'
						onChange={(e) => handleChange(e)}
						className='block w-full px-4 py-2  text-indigo-700 bg-white border rounded-md focus:border-indigo-400 focus:ring-indigo-300 focus:outline-none focus:ring focus:ring-opacity-40'
					/>
				</div>
				<div className='h-14'>
					<input
						placeholder='Price'
						name='price'
						type='number'
						onChange={(e) => handleChange(e)}
						className='block w-full px-4 py-2  text-indigo-700 bg-white border rounded-md focus:border-indigo-400 focus:ring-indigo-300 focus:outline-none focus:ring focus:ring-opacity-40'
					/>
				</div>

				<div className='flex space-x-4'>
					<div className='relative z-0 mb-6 w-full group'>
						<label className='pt-2 block text-sm font-semibold text-gray-600'>
							Supplier
						</label>
						<input
							placeholder='Name'
							name='supplierName'
							type='text'
							onChange={(e) => handleChange(e)}
							className='block w-full px-4 py-2 mt-2 text-indigo-700 bg-white border rounded-md focus:border-indigo-400 focus:ring-indigo-300 focus:outline-none focus:ring focus:ring-opacity-40'
						/>
						<input
							placeholder='Country'
							name='supplierName'
							type='text'
							onChange={(e) => handleChange(e)}
							className='block w-full px-4 py-2 mt-2 text-indigo-700 bg-white border rounded-md focus:border-indigo-400 focus:ring-indigo-300 focus:outline-none focus:ring focus:ring-opacity-40'
						/>
					</div>
				</div>
				<div className='flex space-x-4'>
					<div className='relative z-0 mb-6 w-full group'>
						<label className=' block text-sm font-semibold text-gray-600'>
							Price Reduction
						</label>
						<input
							placeholder='Price to be reduced'
							name='priceReduction'
							type='number'
							onChange={(e) => handleChange(e)}
							className='block w-full px-4 py-2 mt-2 text-indigo-700 bg-white border rounded-md focus:border-indigo-400 focus:ring-indigo-300 focus:outline-none focus:ring focus:ring-opacity-40'
						/>
						<input
							placeholder='Select a Start Date'
							name='reductionStartDate'
							type='text'
							onChange={(e) => handleChange(e)}
							className='block w-full px-4 py-2 mt-2 text-indigo-700 bg-white border rounded-md focus:border-indigo-400 focus:ring-indigo-300 focus:outline-none focus:ring focus:ring-opacity-40'
						/>
						<input
							placeholder='Select a End Date'
							name='reductionEndDate'
							type='text'
							onChange={(e) => handleChange(e)}
							className='block w-full px-4 py-2 mt-2 text-indigo-700 bg-white border rounded-md focus:border-indigo-400 focus:ring-indigo-300 focus:outline-none focus:ring focus:ring-opacity-40'
						/>
					</div>
				</div>
			</div>

			<div className='flex justify-center space-x-4'>
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
