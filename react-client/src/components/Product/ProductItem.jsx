const ProductItem = ({ product }) => {
	const onRowClickHandler = (e) => {
		const item = e.target.getAttribute('key')
		console.log(e.currentTarget)
		console.log('Producto seleccionado: ', item)
	}

	return (
		<>
			<tr
				key={product.itemCode}
				onClick={onRowClickHandler}
				className='bg-white border-b text-center font-medium text-gray-600 text-sm whitespace-nowrap'
			>
				<td className='py-4'>{product.itemCode}</td>
				<td className='py-4'>{product.description}</td>
				<td className='py-4'>{product.status}</td>
				<td className='py-4'>{product.price}</td>
				<td className='py-4'>{product.createdAt}</td>
				<td className='py-4 flex justify-center'>
					<div className='inline-flex rounded-md shadow-sm  ' role='group'>
						<button
							type='button'
							class='py-2 px-4 text-sm font-medium rounded-l-lg text-green-400 border-2 border-grey-400'
						>
							View
						</button>
						<button
							type='button'
							class='py-2 px-4 text-sm font-medium text-blue-400 border-2 border-grey-400'
						>
							Edit
						</button>
						<button
							type='button'
							class='py-2 px-4 text-sm font-medium text-red-400 rounded-r-lg border-2 border-grey-400'
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
