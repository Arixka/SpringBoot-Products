const ProductItem = ({ product }) => {
	const { itemCode, description, status, price, createdAt, creatorUser } =
		product

	const onButtonView = (e) => {
		console.log(e.currentTarget)
		console.log('Ver ')
	}
	const onButtonEdit = (e) => {
		console.log(e.currentTarget)
		console.log('Editar')
	}
	const onButtonDeactivate = (e) => {
		console.log(e.currentTarget)
		console.log('Desactivar')
	}

	return (
		<>
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
							onClick={onButtonDeactivate}
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
