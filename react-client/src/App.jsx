import './App.css'
import Header from './components/Header'
import ProductList from './components/Product/ProductList'

function App() {
	return (
		<div className='App'>
			<Header />
			<main>
				<ProductList />
			</main>
		</div>
	)
}

export default App
