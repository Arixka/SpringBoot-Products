import './App.css'
import Header from './components/Header'
import NewProduct from './components/Product/NewProduct'
import ProductList from './components/Product/ProductList'

function App() {
	return (
		<div className='App'>
			<Header />
			<main>
				<NewProduct />
				<ProductList />
			</main>
		</div>
	)
}

export default App
