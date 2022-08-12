import { useState } from 'react'
import Modal from './Modal'

const BASE_URL = 'http://localhost:8080/api/product/desactive/'
const title = 'Titulo del modal'
const ProductItem = ({ product }) => {
	const { itemCode, description, status, price, createdAt, creatorUser } =
		product

	const [isOpen, setIsOpen] = useState(false)

	const openModal = () => {
		setIsOpen(true)
	}
	const closeModal = () => {
		setIsOpen(false)
	}
	const onButtonView = (e) => {
		console.log(e.currentTarget)
		console.log('Ver ')
	}
	const onButtonEdit = (e) => {
		console.log(e.currentTarget)
		console.log('Editar')
	}
	const onButtonDeactivate = (e) => {
		e.preventDefault()
		// const response = await fetch(BASE_URL+itemCode, {
		// 	method: 'PUT',
		// 	headers: {
		// 		'Content-Type': 'application/json',
		// 	},
		// 	body: JSON.stringify(product),
		// })
		// if (!response.ok) {
		// 	throw new Error('Something went wrong')
		// }
		// const resProduct = await response.json()
		// console.log(resProduct)
	}

	return (
		<>
			<Modal isOpen={isOpen} closeModal={closeModal} title={title} >
				METER AQUI EL CONTENIDO DEL MODAL
			</Modal>

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
							onClick={onButtonView}
							type='button'
							className='py-2 px-4 text-sm font-medium rounded-l-lg text-amber-400 border-2 border-grey-400  hover:text-amber-600'
						>
							View
						</button>
						<button
							onClick={onButtonEdit}
							type='button'
							className={`${
								status === 'DISCONTINUED' && 'rounded-r-lg'
							} py-2 px-4 text-sm font-medium text-blue-400 border-2 border-grey-400  hover:text-blue-600`}
						>
							Edit
						</button>
						<button
							hidden={status === 'DISCONTINUED' ? true : false}
							onClick={openModal}
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
