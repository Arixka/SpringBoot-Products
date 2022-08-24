const ProductInfo = ({ product }) => {
	return (
		<>
			<div className='p-4'>
				<h5 className='text-4xl font-medium text-gray-700 text-center mt-6'>
					{product.itemCode}
				</h5>
				<h5 className='text-3lg font-medium text-gray-700 text-center mt-6'>
					{product.status}
				</h5>
				<div className='grid grid-flow-row-dense grid-cols-3 grid-rows-3 pt-4'>
					<div className='col-span-2'>
						<h5 className='w-full mt-4 text-sm font-medium text-gray-900'>
							Description:
						</h5>
						<p className='text-lg font-light text-gray-500'>
							{product.description}
						</p>
					</div>
					<div className='col-span-1'>
						<h5 className='w-full mt-4 text-sm font-medium text-gray-900'>
							Price:
						</h5>
						<p className='text-lg font-light text-gray-500'>{`${product.price} €`}</p>
					</div>
					<div className='col-span-2'>
						<h5 className='w-full mt-4 text-sm font-medium text-gray-900'>
							Creator User:
						</h5>
						<p className='text-lg font-light text-gray-500'>
							{product.creatorUser}
						</p>
					</div>
					<div>
						<h5 className='w-full mt-4 text-sm font-medium text-gray-900'>
							Creation Date:
						</h5>
						<p className='text-lg font-light text-gray-500'>
							{product.createdAt}
						</p>
					</div>
					<div className='col-span-3'>
						<h5 className='my-2 text-sm font-medium text-gray-900 col-span-2'>
							Suppliers:
						</h5>
						<ul className='w-full text-lg font-light text-gray-500 mx-12'>
							{product.suppliers?.map((supplier, index) => (
								<li key={index}>
									<div className='grid grid-cols-2'>
										<span className='inline text-sm font-medium text-gray-900'>Name</span>
										<p>{supplier.name}</p>
										<span className='inline text-sm font-medium text-gray-900'>Country</span>
										<p>{supplier.country}</p>
									</div>
								</li>
							))}
						</ul>
					</div>
					<div className='col-span-3'>
						<h5 className='my-2 text-sm font-medium text-gray-900'>
							Price Reductions:
						</h5>
						<ul className='w-full text-lg font-light text-gray-500 mx-12'>
							{product.pricesReductions?.map((price, index) => (
								<li key={index}>
									<div className='grid grid-cols-2 '>
										<span className='inline text-sm font-medium text-gray-900'>Price Reduced</span>
										<p>{`${price.reducedPrice} €`}</p>
										<span className='inline text-sm font-medium text-gray-900'>Start Date</span>
										<p>{price.startDate}</p>
										<span className='inline text-sm font-medium text-gray-900'>End Date</span>
										<p>{price.endDate}</p>
									</div>
								</li>
							))}
						</ul>
					</div>
				</div>
			</div>
		</>
	)
}

export default ProductInfo
