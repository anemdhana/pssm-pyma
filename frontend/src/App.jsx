import React, { useState, useEffect } from 'react'
import ProductList from './components/ProductList'
import ProductForm from './components/ProductForm'

export default function App() {
  const [editing, setEditing] = useState(null)
  const [refreshKey, setRefreshKey] = useState(0)

  useEffect(() => {}, [refreshKey])

  return (
    <div style={{ padding: 20, fontFamily: 'Arial, sans-serif' }}>
      <h1>Products</h1>
      <ProductForm
        editing={editing}
        onSaved={() => {
          setEditing(null)
          setRefreshKey(k => k + 1)
        }}
        onCancel={() => setEditing(null)}
      />
      <ProductList
        key={refreshKey}
        onEdit={(p) => setEditing(p)}
      />
    </div>
  )
}
