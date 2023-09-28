# Welcome to GMart Convenience Store

## This is a convenience store built with:

- JAVA (For the Backend)
- Flutter (For Mobile Application)
- ReactJs (For Web Application)

---

**Instructions for Store**
1. There should be a staff model that holds:
   - Manager
   - Cashier
2. There should be these personalities/entities:
   - Manager
   - Cashier
   - Customer
   - Product
   - Receipt
3. A manager should have these things:
   - Staff Model
4. A cashier should have these things:
   - Staff Model
5. A receipt should have these things:
   - Receipt ID
   - Customer Model
   - Receipt Issued Date
   - Total amount of product
6. A customer should have these things:
   - Customer ID
   - List of Products
7. A product model should have these things:
   - Product name
   - Product ID
   - Product price
   - Product quantity

---

**Functions/Methods to reproduce**
1. A manager can perform the following manager:
   - Hire a cashier
   - Fire a cashier
   - Payout salary to cashier
2. A cashier can perform the following manager:
   - Verify product/s
   - Calculate product/s total
   - Checkout product/s
   - Dispense receipts
3. A customer can perform the following manager:
    - Buy and pay for product/s (In quantity)
    - Pay with cash or ATM
    - Lay complaint
    - Give tips

---

**Steps to reproduce**
- [ ] Create Manager Model
- [ ] Create Cashier Model
- [ ] Create Customer Model
- [ ] Create Product Model
- [ ] Create Receipt Model
- [ ] Create Functions

---

**Folder Structure**
- src
  - g.store
    - Main
  - Models
    - Entities
      - Manager
      - Cashier
      - Staff
      - Customer
    - Transactions
      - Product
      - Cart
      - PaymentResult
      - ProductFamily
      - ProductParent
    - Receipt
  - Manager
    - CurrencyManager
    - IDGenerators
  - Types
    - GenderType
    - PaymentMethod
    - PaymentResult
    - StaffAuthority
    - StaffPosition
- test

---

**Steps reproduced**
- [ x ] Created models for required entities.
- 
