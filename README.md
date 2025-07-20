
# 🛡️ Secure Journal App

A secure, RESTful journal application that allows users to safely create and manage personal journal entries with full user authentication and role-based access control.

## 🚀 Features

- 🔐 User Registration and Login
- 🔑 Password Encryption with BCrypt
- 🧾 Create, Read, Update, Delete (CRUD) Journal Entries
- 🛡️ Role-Based Access Control (USER/ADMIN)
- 🔒 JWT-based Authentication
- 🧩 RESTful API Design
- 💾 MongoDB for Persistent Storage

## 💻 Tech Stack

- **Backend**: Java, Spring Boot, Spring Security
- **Database**: MongoDB
- **Authentication**: JWT (JSON Web Token)
- **Build Tool**: Maven



## 📂 Project Structure

src/  
├── main/  
│   ├── java/  
│   │   └── com/example/journal  
│   │       ├── controller  
│   │       ├── model  
│   │       ├── repository  
│   │       ├── security  
│   │       └── service  
│   └── resources/  
│       └── application.properties  


## 🧪 How to Run

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/secure-journal-app.git
   cd secure-journal-app
2. **Configure MongoDB**
    Make sure MongoDB is installed and running. Update application.properties with your database details.

🔒 Security Highlights
Passwords are hashed using BCrypt before storing in the database.
JWT is used for stateless and secure communication between client and server.
Only authenticated users can access their own entries.

## 🛠️ API Endpoints

| Method | Endpoint               | Description             |
|--------|------------------------|-------------------------|
| POST   | `/api/auth/register`   | Register new user       |
| POST   | `/api/auth/login`      | Login and get JWT token |
| GET    | `/api/journal/entries` | Fetch journal entries   |
| POST   | `/api/journal/entry`   | Create a journal entry  |
| PUT    | `/api/journal/entry`   | Update a journal entry  |
| DELETE | `/api/journal/entry`   | Delete a journal entry  |


📌 Future Enhancements
- Add frontend using React or Thymeleaf
- Implement OAuth2 login (Google, GitHub)
- Enable journal entry search/filter by tags or dates
- Add email-based password recovery

