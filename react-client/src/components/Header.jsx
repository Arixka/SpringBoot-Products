import React from 'react'

const Header = () => {
	return (
		<>
			<nav className='relative flex flex-wrap items-center justify-between px-2 py-3 bg-indigo-500 mb-3'>
				<div className='container px-4 mx-auto flex flex-wrap items-center justify-between'>
					<div className='w-full relative flex justify-between lg:w-auto  px-4 lg:static lg:block lg:justify-start'>
						<a className='text-base font-bold leading-relaxed inline-block mr-4 py-2 whitespace-nowrap uppercase text-white'
							href='#title'>
							Simple Erp
						</a>
					</div>
					<div
						className='lg:flex flex-grow items-center'
						id='example-navbar-warning'>

					</div>
				</div>
			</nav>
		</>
	)
}

export default Header
