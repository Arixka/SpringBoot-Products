import { useState } from 'react'
import Modal from '../Modal'
import ProductForm from './ProductForm'

const BASE_URL = 'http://localhost:8080/api/product/'

const NewProduct = ({ getProducts }) => {
	const [isOpen, setIsOpen] = useState(false)

	const openModal = () => {
		setIsOpen(true)
	}
	const closeModal = () => {
		setIsOpen(false)
	}

	const handleNewProduct = async (product) => {
		const response = await fetch(BASE_URL, {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
			},
			body: JSON.stringify(product),
		})
		
		if (!response.ok) {
			throw new Error('Something went wrong')
		}
		const resProduct = await response.json()

		getProducts(resProduct)
	}
	//TODO campos itemcode y description obligatorios, añadir validaciones
	return (
		<>
			<Modal isOpen={isOpen} closeModal={closeModal} title={'Add Product'}>
				<ProductForm
					handleNewProduct={handleNewProduct}
					setIsOpen={setIsOpen}
				/>
			</Modal>

			<button
				onClick={openModal}
				className='py-2 px-6 font-semibold rounded-lg text-green-400 border-2 border-grey-400 hover:text-green-600'
			>
				Add Product
			</button>
		</>
	)
}

export default NewProduct
