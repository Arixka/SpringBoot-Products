const Search = ({ handleSearch }) => {

	const handleChange = (e) => {
		handleSearch(e)
	}

	return (
		<>
		
		<div className='relative'>
			<svg
				xmlns='http://www.w3.org/2000/svg'
				className='absolute top-0 bottom-0 w-6 h-6 my-auto text-indigo-400 left-3'
				fill='none'
				viewBox='0 0 24 24'
				stroke='currentColor'
			>
				<path
					strokeLinecap='round'
					strokeLinejoin='round'
					strokeWidth={2}
					d='M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z'
				/>
			</svg>
			<input
				type='text'
				placeholder='Status or ItemCode '
				onChange={(e) => handleChange(e)}
				className='w-full py-3 pl-12 pr-4 text-indigo-500 border rounded-md outline-none focus:bg-white focus:border-indigo-600'
			/>
		</div>
		</>
	)
}

export default Search
