const API_BASE = import.meta.env.VITE_API_BASE || 'http://localhost:8080/api'

export async function listProducts() {
  const res = await fetch(`${API_BASE}/products`)
  return await res.json()
}

export async function getProduct(id) {
  const res = await fetch(`${API_BASE}/products/${id}`)
  return await res.json()
}

export async function createProduct(product) {
  const res = await fetch(`${API_BASE}/products`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(product)
  })
  return await res.json()
}

export async function updateProduct(id, product) {
  const res = await fetch(`${API_BASE}/products/${id}`, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(product)
  })
  return await res.json()
}

export async function deleteProduct(id) {
  await fetch(`${API_BASE}/products/${id}`, { method: 'DELETE' })
}
