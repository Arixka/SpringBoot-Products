import './App.css'
import AuthProvider from './auth/context/AuthProvider'
import Header from './components/Header'

import AppRouter from './router/AppRouter'

function App() {
	return (
		<div className='App'>
			<main>
				<AuthProvider>
					<Header />
					<AppRouter />
				</AuthProvider>
			</main>
		</div>
	)
}

export default App
