import React from 'react'

const Header = () => {
	return (
		<>
			<nav className='relative flex flex-wrap items-center justify-between px-2 py-3 bg-indigo-500 mb-3'>
				<div className='container px-4 mx-auto flex flex-wrap items-center justify-between'>
					<div className='w-full relative flex justify-between lg:w-auto  px-4 lg:static lg:block lg:justify-start'>
						<a
							className='text-base font-bold leading-relaxed inline-block mr-4 py-2 whitespace-nowrap uppercase text-white'
							href='#title'
						>
							Simple Erp
						</a>
					</div>
					<div
						className='lg:flex flex-grow items-center'
						id='example-navbar-warning'
					>
						<div className='relative flex w-full sm:w-7/12 md:w-5/12 px-4 flex-wrap items-stretch lg:ml-auto'>
							<div className='flex'>
								<span className='font-normal leading-snug flex text-center white-space-no-wrap border border-solid border-indigo-600 rounded-full text-sm bg-indigo-100 items-center rounded-r-none pl-2 py-1 text-indigo-800 border-r-0 placeholder-indigo-300'>
									<i className='fas fa-search'></i>
								</span>
							</div>
							<input
								type='text'
								className='px-2 py-1 h-8 border border-solid  border-indigo-600 rounded-full text-sm leading-snug text-indigo-700 bg-indigo-100 shadow-none outline-none focus:outline-none w-full font-normal rounded-l-none flex-1 border-l-0 placeholder-indigo-300'
								placeholder='SearchItems'
							/>
						</div>
					</div>
				</div>
			</nav>
		</>
	)
}

export default Header
