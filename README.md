
#  User Management API Documentation

## Description
This project is a user management application that interacts with a backend through defined endpoints. It allows creating, deleting, and listing users via HTTP requests.

---

## Endpoints

### **1. Create User**
#### *Description:*
This endpoint allows the creation of a new user in the system.

#### *URL:*
``` 
https://api-spike-indol.vercel.app/createUser
```


#### *HTTP Method:*
`POST`  /createUser

#### *Body:*
The following fields must be sent in JSON format in the request body:

| Parameter  | Type   | Required | Description                      |
|------------|--------|----------|----------------------------------|
| firstName  | String | Yes      | The user's first name            |
| lastName   | String | Yes      | The user's last name             |
| email      | String | Yes      | The user's email                 |
| phone      | String | No       | The user's phone number          |
| password   | String | Yes      | The user's password              |
| role       | String | Yes      | The role assigned to the user (e.g., `ADMIN`,`VETERINARY_OWNER`,`PET_OWNER`) |

#### *Example Body (JSON):*
```json
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "johndoe@example.com",
  "phone": "1234567890",
  "password": "password123",
  "role": "ADMIN"
}
```
#### *Successful Response:*

- HTTP Code: 201 Created
- Description: User created successfully
```json
{
  "message": "User created successfully",
}
```
### **2. Delete User**
#### *Description:*
This endpoint allows the deletion of a user by their userId.
#### *HTTP Method:*
`DELETE`   /deleteUser/{userId}
#### *Path Parameters:*
| Parameter  | Type   | Required | Description                      |
|------------|--------|----------|----------------------------------|
| userId  | String | Yes      | The unique identifier of the user to be deleted           |

#### *URL:*
```
 https://api-spike-indol.vercel.app/deleteUser/:{userId}

```
#### *Successful Response:*
- HTTP Code: 200 OK
- **Description:** User deleted successfully.
#### *Example Response (JSON)::*
```json
{
  "message": "User deleted successfully",
}
```
### **3. List Users**
#### *Description:*
This endpoint returns a list of all users registered in the system.
#### *HTTPMethod:*
`GET` /getUsers
#### *URL:*
```
 https://api-spike-indol.vercel.app/getUsers

```
####  *Successful Response:*
- HTTP Code: 200 OK
- Description: List of users retrieved successfully.
```json
[
  {
    "id": "abc123",
    "firstName": "John",
    "lastName": "Doe"
  },
  {
    "id": "xyz456",
    "firstName": "Jane",
    "lastName": "Smith"
  }
]
```
## Authors

- [@JDAA4](https://github.com/JDAA4)
