import { useState } from 'react'
import Modal from './Modal'
import ProductForm from './ProductForm'

const title = 'Add Product'

const NewProduct = ({ getProducts }) => {
	const [isOpen, setIsOpen] = useState(false)

	const openModal = () => {
		setIsOpen(true)
	}
	const closeModal = () => {
		setIsOpen(false)
	}
//TODO campos itemcode y description obligatorios, añadir validaciones 
	return (
		<>
			<Modal isOpen={isOpen} closeModal={closeModal} title={title}>
				<ProductForm getProducts={getProducts} setIsOpen={setIsOpen} />
			</Modal>

			<div className='container mx-auto my-8'>
				<div className='h-12 flex justify-end'>
					<button
						onClick={openModal}
						className='py-2 px-6 font-semibold rounded-lg text-green-400 border-2 border-grey-400 hover:text-green-600'
					>
						Add Product
					</button>
				</div>
			</div>
		</>
	)
}

export default NewProduct
