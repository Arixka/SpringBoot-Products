const NewModal = ({ isOpen, setIsOpen, title }) => {


	const closeModal = () => {
		setIsOpen(false)
	}

	return (
		<>
			{state && (
				<Transition appear show={isOpen} as={Fragment}>
					<Dialog as='div' className='relative z-10' onClose={closeModal}>
						<Transition.Child
							as={Fragment}
							enter='ease-out duration-300'
							enterFrom='opacity-0'
							enterTo='opacity-100'
							leave='ease-in duration-200'
							leaveFrom='opacity-100'
							leaveTo='opacity-0'
						>
							<div className='fixed inset-0 bg-black bg-opacity-25' />
						</Transition.Child>

						<div className='fixed inset-0 overflow-y-auto'>
							<div className='flex min-h-full items-center justify-center p-4 text-center'>
								<Transition.Child
									as={Fragment}
									enter='ease-out duration-300'
									enterFrom='opacity-0 scale-95'
									enterTo='opacity-100 scale-100'
									leave='ease-in duration-200'
									leaveFrom='opacity-100 scale-100'
									leaveTo='opacity-0 scale-95'
								>
									<Dialog.Panel className='w-full max-w-lg transform overflow-hidden rounded-2xl bg-white p-6 text-left align-middle shadow-xl transition-all'>
										<Dialog.Title
											as='h3'
											className='text-lg font-medium leading-6 text-indigo-900 text-center'
										>
											{title}
										</Dialog.Title>

										{children}

										<div className='mt-4'>
											<button
												type='button'
												className='absolute top-0 right-0 h-16 w-16'
												onClick={() => closeModal()}
											>
												<svg
													aria-hidden='true'
													className='w-5 h-5'
													fill='currentColor'
													viewBox='0 0 20 20'
													xmlns='http://www.w3.org/2000/svg'
												>
													<path d='M4.293 4.293a1 1 0 011.414 0L10 8.586l4.293-4.293a1 1 0 111.414 1.414L11.414 10l4.293 4.293a1 1 0 01-1.414 1.414L10 11.414l-4.293 4.293a1 1 0 01-1.414-1.414L8.586 10 4.293 5.707a1 1 0 010-1.414z'></path>
												</svg>
												<span className='sr-only'>Close modal</span>
											</button>
										</div>
									</Dialog.Panel>
								</Transition.Child>
							</div>
						</div>
					</Dialog>
				</Transition>
			)}
		</>
	)
}

export default NewModal
