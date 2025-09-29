import { useState, useEffect } from 'react';
import ProductList from './ProductList';
import './App.css';
import CategoryFilter from './CategoryFilter';

function App() {
  const [products, setProduct] = useState([]);
  const [categories, setCategories] = useState([]);
  const [selectedCategory, setSelectedCategory] = useState(null);
  const [searchTerm, setSearchTerm] = useState();
  const [sortOrder, setSortOrder] = useState("asc");

  useEffect(() => {
    fetch("http://localhost:8081/api/products")
      .then(response => response.json())
      .then(data => setProduct(data))
      .catch(error => console.error("Error fetching products:", error));

    fetch("http://localhost:8081/api/categories")
      .then(response => response.json())
      .then(data => setCategories(data))
      .catch(error => console.error("Error fetching products:", error));
  }, []);

  const handleSearchChange = (event) =>{
    setSearchTerm(event.target.value);
  }
  
  const handleSortChange = (event) =>{
    setSortOrder(event.target.value);
  }

  const handleCategorieSelect = (categoryId) =>{
    setSelectedCategory(categoryId ? Number(categoryId) : null);
  }
  
  const filteredProducts = products.filter(products =>{
    return(
      (selectedCategory ? products.category.id === selectedCategory : true)
    )
  })
  return (
    <div className="container">
    <h1 className='my-4'>Product Catalog</h1>
      <div className='row align-items-center mb-2'>
        <div className='col-md-3 col-sm-12 mb-12'>
          {/* <p>Category  Filter</p> */}
          <CategoryFilter categories={categories} onSelect={handleCategorieSelect}/>
        </div>

        <div className='col-md-5 col-sm-12 mb-12'>
          <input type='text' className='form-control' placeholder='Search for products' 
          onChange={handleSearchChange}
          />
        </div>
        <div className='col-md-2 col-sm-12 mb-2' onChange={handleSortChange}>
        <select className='form-control'>
          <option value="asc">Price: Low to High</option>
          <option value="desc">Price: High to Low</option>
        </select>
        </div>
      <div>
        {filteredProducts.length ? (
        <ProductList products={products} />
      ) : (
        <p>Product Not Found</p>
      )}
      </div>
    </div>
  </div>
  );
}

export default App;
