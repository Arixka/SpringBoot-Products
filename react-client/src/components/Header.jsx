import LogoutBtn from './LogoutBtn'

const Header = () => {
	return (
		<>
			<nav className='relative flex flex-wrap items-center justify-between px-2 py-3 bg-indigo-500 mb-3'>
				<div className='container px-4 mx-auto flex flex-wrap items-center justify-between'>
					<div className='w-full relative flex justify-between lg:w-auto  px-4 lg:static lg:block lg:justify-start'>
						<a
							className='text-base font-bold leading-relaxed inline-block mr-4 whitespace-nowrap uppercase text-white'
							href='#title'
						>
							Simple Erp
						</a>
					</div>
					<div className=' w-full md:block md:w-auto' id='navbar-default'>
						<div className='flex justify-between rounded-lg bordermd:flex-row md:space-x-8 md:mt-0 md:text-sm md:font-medium md:border-0'>
							<LogoutBtn />
						</div>
						
					</div>
				</div>
			</nav>
		</>
	)
}

export default Header
