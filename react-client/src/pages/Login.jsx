import React, { useState, useEffect, useContext } from 'react'
import { AiOutlineEyeInvisible, AiOutlineEye } from 'react-icons/ai'
import { AuthContext } from '../auth/context/AuthContext'

const BASE_URL = 'http://localhost:8080/api/auth/login'
const Login = () => {
	const [loginForm, setLoginForm] = useState({ username: '', password: '' })
	const [formErrors, setFormErrors] = useState({})
	const [isSubmitting, setIsSubmitting] = useState(false)
	const [open, setOpen] = useState(false)
	const { login } = useContext(AuthContext)


	const handleChange = (e) => {
		setLoginForm({
			...loginForm,
			[e.target.name]: e.target.value,
		})
	}
	const handleSubmit = (e) => {
		e.preventDefault()
		setFormErrors(validateInputs(loginForm))
		setIsSubmitting(true)
	}
	const toggle = () => {
		setOpen(!open)
	}
	const loginUser = async () => {
		login(loginForm)
	}

	const validateInputs = (values) => {
		let errors = {}

		if (!values.username) {
			errors.username = 'This field Username is required'
		}
		if (!values.password) {
			errors.password = 'This field Password is required'
		}
		return errors
	}

	useEffect(() => {
		if (Object.keys(formErrors).length === 0 && isSubmitting) {
			loginUser()
		}
	}, [formErrors])

	return (
		<>
			<div className='container mx-auto px-4 pt-6 h-full'>
				<div className='flex content-center items-center justify-center h-full'>
					<div className='w-full md:w-8/12 lg:w-6/12 px-4'>
						<div className='relative flex flex-col min-w-0 break-words w-full mb-6 shadow-lg rounded-lg 0 border-0'>
							<div className='rounded-t mb-0 px-6 py-6'>
								<div className='text-center mb-3'>
									<h6 className='text-3xl font-medium text-gray-700'>Login</h6>
								</div>
								<hr className='mt-6 border-b-1 border-gray-400' />
							</div>
							<div className='flex-auto px-4 lg:px-10 py-10 pt-0'>
								<div className='text-gray-500 text-center mb-3 font-bold'>
									<small>Sign in with credentials</small>
								</div>
								<form>
									<div className='relative w-full mb-3 pt-4'>
										<label
											className='block uppercase text-gray-700 text-xs font-bold mb-2'
											htmlFor='grid-password'
										>
											Username
										</label>
										<input
											placeholder='Username'
											name='username'
											type='text'
											value={loginForm.username}
											onChange={(e) => handleChange(e)}
											style={{ transition: 'all .15s ease' }}
											className='block w-full px-4 py-2 mt-2 text-indigo-700 bg-white border rounded-md focus:border-indigo-400 focus:ring-indigo-300 focus:outline-none focus:ring focus:ring-opacity-40'
										/>
									</div>

									<div className='relative w-full mb-3'>
										<label
											className='block uppercase text-gray-700 text-xs font-bold mb-2'
											htmlFor='grid-password'
										>
											Password
										</label>

										<input
											placeholder='Password'
											name='password'
											type={open === false ? 'password' : 'text'}
											value={loginForm.password}
											onChange={(e) => handleChange(e)}
											style={{ transition: 'all .15s ease' }}
											className='block w-full px-4 py-2 mt-2 text-indigo-700 bg-white border rounded-md focus:border-indigo-400 focus:ring-indigo-300 focus:outline-none focus:ring focus:ring-opacity-40'
										/>
										<div className='text-2xl absolute top-8 right-5'>
											{open === false ? (
												<AiOutlineEye onClick={toggle} />
											) : (
												<AiOutlineEyeInvisible onClick={toggle} />
											)}
										</div>
									</div>

									<div className='text-center mt-6'>
										<button
											className='rounded-md  uppercase border border-transparent bg-indigo-100 px-8 py-3 text-sm font-bold text-indigo-900 hover:bg-indigo-200 focus:outline-none focus-visible:ring-2 focus-visible:ring-indigo-500 focus-visible:ring-offset-2 ease-linear transition-all duration-150'
											onClick={handleSubmit}
											style={{ transition: 'all .15s ease' }}
										>
											Login
										</button>
									</div>
								</form>

								<div className='flex flex-wrap mt-6'>
									<div className='w-1/2'></div>
									<div className='w-1/2 text-right'></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</>
	)
}

export default Login
