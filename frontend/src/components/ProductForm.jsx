import React, { useEffect, useState } from 'react'
import { createProduct, updateProduct } from '../api/productService'

export default function ProductForm({ editing, onSaved, onCancel }) {
  const [form, setForm] = useState({ name: '', description: '', price: '' })

  useEffect(() => {
    if (editing) {
      setForm({
        name: editing.name || '',
        description: editing.description || '',
        price: editing.price || ''
      })
    }
  }, [editing])

  async function handleSubmit(e) {
    e.preventDefault()
    const payload = { ...form, price: Number(form.price) }
    if (editing && editing.id) {
      await updateProduct(editing.id, payload)
    } else {
      await createProduct(payload)
    }
    setForm({ name: '', description: '', price: '' })
    onSaved && onSaved()
  }

  return (
    <form onSubmit={handleSubmit} style={{ marginBottom: 20 }}>
      <div style={{ marginBottom: 8 }}>
        <input placeholder="Name" value={form.name} onChange={e => setForm({...form, name: e.target.value})} required />
      </div>
      <div style={{ marginBottom: 8 }}>
        <input placeholder="Description" value={form.description} onChange={e => setForm({...form, description: e.target.value})} />
      </div>
      <div style={{ marginBottom: 8 }}>
        <input placeholder="Price" type="number" step="0.01" value={form.price} onChange={e => setForm({...form, price: e.target.value})} required />
      </div>
      <div>
        <button type="submit">{editing ? 'Update' : 'Create'}</button>
        {editing && <button type="button" onClick={onCancel} style={{ marginLeft: 8 }}>Cancel</button>}
      </div>
    </form>
  )
}
