
#  User Management API Documentation

## Description
This project is a user management application that interacts with a backend through defined endpoints. It allows creating, deleting, and listing users via HTTP requests.

---

## Endpoints

### **1. Create User**

#### **Description:**
This endpoint allows the creation of a new user in the system.

#### **URL:**
POST /createUser


#### **HTTP Method:**
`POST`

#### **Body:**
The following fields must be sent in JSON format in the request body:

| Parameter  | Type   | Required | Description                      |
|------------|--------|----------|----------------------------------|
| firstName  | String | Yes      | The user's first name            |
| lastName   | String | Yes      | The user's last name             |
| email      | String | Yes      | The user's email                 |
| phone      | String | No       | The user's phone number          |
| password   | String | Yes      | The user's password              |
| role       | String | Yes      | The role assigned to the user (e.g., `ADMIN`,`VETERINARY_OWNER`,`PET_OWNER`) |

#### **Example Body (JSON):**
```json
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "johndoe@example.com",
  "phone": "1234567890",
  "password": "password123",
  "role": "ADMIN"
}
