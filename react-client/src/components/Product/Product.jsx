import React from 'react'

const Product = ({product}) => {
	return (
		<>
			<tr key={product.itemCode} className='bg-white border-b text-center font-medium text-gray-600 text-sm whitespace-nowrap'>
				<td className='px-6 py-4'>{product.itemCode}</td>
				<td className='px-6 py-4'>{product.description}</td>
				<td className='px-6 py-4'>{product.status}</td>
				<td className='px-6 py-4'>{product.price}</td>
				<td className='px-6 py-4'>{product.createdAt}</td>
			</tr>
		</>
	)
}

export default Product
