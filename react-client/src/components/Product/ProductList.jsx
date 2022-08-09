import { useEffect, useState } from 'react'
import Product from './Product'

const ProductList = () => {
  const BASE_URL = 'http://localhost:8080/api/product/all'

  const [products, setProducts] = useState(null)
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    const getProducts = async () => {
      setLoading(true)
      try {
        const response = await fetch(BASE_URL, {
          method: 'Get',
          headers: {
            'Content-Type': 'application/json',
          },
        })

        const productsRest = await response.json()
        setProducts(productsRest)
        console.log(products, loading)
      } catch (error) {
        console.log(error)
      }
      setLoading(false)
    }
    getProducts()
  }, [])

  return (
    <div className='container mx-auto my-10'>
      <div className='shadow border-b'>
        <table className='w-full text-sm text-left text-gray-500 dark:text-gray-400'>
          <thead className='text-gray-700 uppercase bg-gray-50 text-center'>
            <tr>
              <th className='px-6 py-3'>Code</th>
              <th className='px-6 py-3'>Description</th>
              <th className='px-6 py-3'>State</th>
              <th className='px-6 py-3'>Price</th>
              <th className='px-6 py-3'>Creation Date</th>
            </tr>
          </thead>

          <tbody>
            {products?.map((product) => {
              <Product product={product} key={product.itemCode} />
            })}
          </tbody>
        </table>
      </div>
    </div>
  )
}

export default ProductList
