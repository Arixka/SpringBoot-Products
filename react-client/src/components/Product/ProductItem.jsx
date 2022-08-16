import { useState } from 'react'
import Modal from '../Modal'
import ProductForm from './ProductForm'
import ProductInfo from './ProductInfo'

const BASE_URL = 'http://localhost:8080/api/product/desactive/'

const ProductItem = ({ getProducts, product }) => {
	const { itemCode, description, status, price, createdAt, creatorUser } =
		product
	const [isOpenDeactivate, setIsOpenDeactivate] = useState(false)
	const [isOpenView, setIsOpenView] = useState(false)
	const [isOpenEdit, setIsOpenEdit] = useState(false)
	const [resProduct, setResProduct] = useState({
		reasonDeactivation: '',
	})

	const handleChange = (e) => {
		setResProduct({
			[e.target.name]: e.target.value,
		})
	}

	const onViewProduct = (e) => {
		console.log(e.currentTarget)
		console.log('Ver ', product)
	}
	const onEditProduct = (e) => {
		console.log(e.currentTarget)
		console.log('Editar')
	}
	const onDeactivatedProduct = async () => {
		const response = await fetch(BASE_URL + itemCode, {
			method: 'PUT',
			headers: {
				'Content-Type': 'application/json',
			},
			body: JSON.stringify(resProduct),
		})
		if (!response.ok) {
			throw new Error('Something went wrong')
		}
		const _product = await response.json()
		console.log(_product)
		setIsOpenDeactivate(!isOpenDeactivate)
		getProducts()
	}
	//TODO añadir validacion campo reasonDeactivate obligatorio
	//TODO apatar el ProductForm para editar el producto, no se puede editar el itemcode
	return (
		<>
			{/* <Modal isOpen={isOpen} closeModal={closeModal} title={title}></Modal> */}
			<Modal
				isOpen={isOpenView}
				closeModal={setIsOpenView}
				title={'Product Info'}
			>
				<ProductInfo product={product} />
			</Modal>
			<Modal
				isOpen={isOpenEdit}
				closeModal={setIsOpenEdit}
				title={'Edit Product'}
			>
				Probando vista edit
			</Modal>
			<Modal
				isOpen={isOpenDeactivate}
				closeModal={setIsOpenDeactivate}
				title={'Disable Product'}
			>
				<div className='block p-6 py-2 mt-2'>
					<div className='h-14'>
						<input
							placeholder='Reason Deactivation'
							name='reasonDeactivation'
							type='text'
							onChange={(e) => handleChange(e)}
							className='block w-full px-4 py-2 my-6 text-indigo-700 bg-white border rounded-md focus:border-indigo-400 focus:ring-indigo-300 focus:outline-none focus:ring focus:ring-opacity-40'
						/>
					</div>
					<div className='flex justify-center '>
						<button
							onClick={onDeactivatedProduct}
							type='button'
							className={`py-2 px-4 text-sm font-medium text-red-400 rounded border-2 border-red-400 hover:text-red-600`}
						>
							Deactivate
						</button>
					</div>
				</div>
			</Modal>

			{/* tabla */}
			<tr
				key={product.itemCode}
				className='bg-white border-b text-center font-medium text-gray-600 text-sm whitespace-nowrap'
			>
				<td className='py-4'>{itemCode}</td>
				<td className='py-4'>{description}</td>
				<td className='py-4'>{status}</td>
				<td className='py-4'>{price}</td>
				<td className='py-4'>{createdAt}</td>
				<td className='py-4'>{creatorUser}</td>
				<td className='py-4 flex justify-center'>
					<div className='inline-flex rounded-md shadow-sm  ' role='group'>
						<button
							onClick={() => setIsOpenView(!isOpenView)}
							type='button'
							className='py-2 px-4 text-sm font-medium rounded-l-lg text-amber-400 border-2 border-grey-400  hover:text-amber-600'
						>
							View
						</button>
						<button
							onClick={() => setIsOpenEdit(!isOpenEdit)}
							type='button'
							className={`${
								status === 'DISCONTINUED' && 'rounded-r-lg'
							} py-2 px-4 text-sm font-medium text-blue-400 border-2 border-grey-400  hover:text-blue-600`}
						>
							Edit
						</button>
						<button
							hidden={status === 'DISCONTINUED' ? true : false}
							onClick={() => setIsOpenDeactivate(!isOpenDeactivate)}
							type='button'
							className={` py-2 px-4 text-sm font-medium text-red-400 rounded-r-lg border-2 border-grey-400 hover:text-red-600`}
						>
							Deactivate
						</button>
					</div>
				</td>
			</tr>
		</>
	)
}

export default ProductItem
