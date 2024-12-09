# Leaderboard API

## Overview

The Leaderboard API is a RESTful service that enables CRUD operations for managing user registrations in a competitive leaderboard system. The system allows users to be registered, updated, retrieved, and deregistered, with scores and badges reflecting their progress.

---

## Features

- **User Registration**:
  - Each user has a unique ID, username, score, and badges.
  - Score defaults to `0`, and badges are empty upon registration.

- **Score and Badge System**:
  - Users can only update their scores via a `PUT` request.
  - Badges are dynamically awarded based on the following rules:
    - `1 <= Score < 30`: **Code Ninja**
    - `30 <= Score < 60`: **Code Champ**
    - `60 <= Score <= 100`: **Code Master**
  - Users can hold up to three unique badges.

- **Sorting**:
  - Users are retrieved in descending order of their scores.
  - Sorting is optimized with a time complexity of **O(nlogn)**.

- **Validation and Error Handling**:
  - Scores must be between `0` and `100`.
  - Proper HTTP status codes are returned for errors (e.g., `404 User Not Found`).

---

## Endpoints

### 1. **GET /users**
Retrieve a list of all registered users, sorted by score in descending order.

**Response Example**:
```json
[
  {
    "userId": "u001",
    "username": "Sakshi Mendhe",
    "score": 90,
    "badges": ["Code Master"]
  }
  
]

## Postman Collection

To test the API endpoints, use the public Postman collection link below:

[Leaderboard API - Postman Collection](https://drive.google.com/file/d/147Z_UYwsAVnLLzu_RwgKRM-wTnsq6oWV/view?usp=drive_link)

### How to Use
1. Open Postman.
2. Click **Import**.
3. Paste the link above into the input field.
4. Click **Continue** and then **Import** to add the collection to Postman.

