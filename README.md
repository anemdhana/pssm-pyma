# Product App (Spring Boot + React)

This workspace contains a Spring Boot backend and a Vite + React frontend.

Backend:
- Location: `backend`
- Java 21, Spring Boot, H2 in-memory DB
- Runs on port `8080`

Frontend:
- Location: `frontend`
- Vite + React (port `5173` by default)

Running locally (PowerShell):

1) Start backend

```powershell
cd backend
mvn spring-boot:run
```

2) Start frontend

```powershell
cd frontend
npm install
npm run dev
```

API endpoints (backend):
- `GET /api/products` - list products
- `GET /api/products/{id}` - get product
- `POST /api/products` - create product
- `PUT /api/products/{id}` - update product
- `DELETE /api/products/{id}` - delete product

H2 console: `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:productdb`)
