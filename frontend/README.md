# E-Banking Frontend Application

An Angular-based frontend for the e-banking platform, enabling customer management, bank account operations (debit, credit, and transfers), and paginated transaction history. It communicates with the Spring Boot backend via REST APIs.

---

## Authors & Supervision
- **Author:** Hamza Benbrahim
- **Supervised by:** Professeur Mohamed YOUSSFI

---

## Technical Stack
- **Frontend Framework:** Angular 21 (Standalone Components)
- **Language:** TypeScript
- **Styling:** Bootstrap 5
- **State Management:** Angular Signals
- **HTTP Client:** Angular `HttpClient`
- **Forms:** Angular Reactive Forms (`FormGroup` / `FormControl`)
- **Dev Server:** `http://localhost:4200`
- **Backend:** Spring Boot REST API at `http://localhost:8085`

---

## Running the Application

```bash
ng serve
```

Then open your browser and navigate to `http://localhost:4200/`.

> **Note:** Make sure the backend server is running at `http://localhost:8085` with CORS enabled before launching the frontend.

---

## Project Structure

```
src/app/
├── components/
│   ├── navbar/          # Navigation bar (RouterLink, RouterLinkActive)
│   ├── customers/       # Customer directory & search
│   ├── new-customer/    # Add new customer form
│   └── accounts/        # Account search, history & operations
├── model/
│   ├── customer.model.ts   # Customer interface
│   └── account.model.ts    # AccountDetails, AccountOperation, BankAccount
└── services/
    ├── customer.service.ts  # Customer CRUD REST calls
    └── accounts.service.ts  # Account operations REST calls
```

---

## Application Pages & Features

### 1. Navigation Bar (`/`)

The top navigation bar provides access to all sections of the application:
- **Home** — landing page
- **Accounts** — navigate to the accounts page
- **Customers** dropdown — links to *Search Customers* and *New Customer*

![Customers Directory – Full List](FrontScreenshots/Screenshot%202026-07-19%20120605.png)

---

### 2. Customer Management

#### Customers Directory (`/customers`)

Displays all customers loaded from `GET /customers/search`. The search bar filters results live by submitting a keyword to the backend. Each row includes an **ID**, **Name**, **Email**, and a **Delete** button.

![Customers Directory – Full List](FrontScreenshots/Screenshot%202026-07-19%20120710.png)

#### Search Customers by Keyword

Typing a name in the search bar and clicking **Search** filters the list via `GET /customers/search?keyword=...`.

![Customers Search – Filtered by "Hassan"](FrontScreenshots/Screenshot%202026-07-19%20120618.png)

---

### 3. New Customer (`/new-customer`)

A reactive form to create a new customer via `POST /customers`. Fields include:
- **Name** — required, minimum 4 characters
- **Email** — required, valid email format

Clicking **Save Customer** sends the data to the backend. A browser alert confirms success, and the user is redirected to `/customers`.

![New Customer Form](FrontScreenshots/Screenshot%202026-07-19%20120639.png)

![Customer Saved Successfully](FrontScreenshots/Screenshot%202026-07-19%20120704.png)

---

### 4. Account Operations (`/accounts`)

#### Account Search & History

Enter a bank account UUID in the search bar and click **Search** to load the account's paginated operation history via `GET /accounts/{accountId}/pageOperations?page=0&size=5`.

The results show:
- **Account ID** and current **Balance**
- A table of operations: ID, Date, Type (color-coded **DEBIT** / **CREDIT** badge), Amount, and Description
- **Pagination** controls to navigate between pages of 5 operations each

![Account Search & Operations History](FrontScreenshots/Screenshot%202026-07-19%20120217.png)

---

#### Debit Operation (`POST /accounts/debit`)

Select **Debit** from the *Operation Type* dropdown, enter the amount and description, then click **Execute Operation**. A browser dialog confirms the operation and the table refreshes automatically.

![Debit Operation – Success Alert](FrontScreenshots/Screenshot%202026-07-19%20120501.png)

---

#### Credit Operation (`POST /accounts/credit`)

Select **Credit** from the *Operation Type* dropdown, enter the amount and description, then click **Execute Operation**. A browser dialog confirms success and the table refreshes.

![Credit Operation – Success Alert](FrontScreenshots/Screenshot%202026-07-19%20120416.png)

---

#### Transfer Between Accounts (`POST /accounts/transfer`)

Select **Transfer** from the *Operation Type* dropdown. An additional **Destination Account ID** field appears. Fill in the amount, destination, and description, then execute. A browser alert confirms the transfer.

![Transfer Operation – Success Alert](FrontScreenshots/Screenshot%202026-07-19%20120345.png)
