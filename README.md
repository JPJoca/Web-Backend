# RAF Tourism Guide

## Overview

**RAF Tourism Guide** is a student project developed for the *Web Programming* course. The platform allows users to discover travel destinations through published articles and enables authenticated users to manage those articles and related content through a dedicated CMS system.

The system consists of two main parts:

1. **Content Management System (CMS)** – available only to authenticated users (editors and admins)
2. **Public Platform** – open to all users for reading articles and browsing destinations

---

## Backend – JAX-RS (Jersey)

### Technologies Used
- Java 11
- JAX-RS (Jersey 2.34)
- Servlet API 4.0.1
- JWT authentication (using `java-jwt`)
- MySQL (with `mysql-connector-java`)
- Jackson for JSON serialization
- Bean validation with `jersey-bean-validation`
- Maven (packaged as `.war` for deployment)

### Features
- **JWT-based authentication**
- **Role-based authorization** (admin / content editor)
- **User management** (by admin only):
  - Add, update, activate/deactivate users
- **Destination management**:
  - Create, update, delete destinations (only if no article is linked)
- **Article management**:
  - Add/edit/delete articles
  - Associate articles with destinations and tourist activities
- **Comment management**:
  - Add and list comments per article
- **Tracking article visits**
- **Filtering articles**:
  - By destination
  - By tourist activity
  - Most viewed in the last 30 days
- **Pagination support** for all lists (offset + limit model)

---

## Frontend – Vue.js

### Technologies Used
- Vue 3
- Vue Router
- Vuex 
- BootstrapVue and plain CSS

### CMS Section
Accessible only to authenticated users.

#### Editors:
- Navigation with sections: **Destinations** and **Articles**
- Can:
  - List, add, edit destinations
  - List, add, edit, delete articles
  - Filter articles by destination
- Cannot:
  - Manage users
- Only active editors can log in

#### Admins:
- Have all editor permissions
- Additional **Users** section:
  - Add/edit users
  - Activate/deactivate content editors

---

### Public Platform
Accessible to all users.

- **Home Page:** 10 most recent articles
- **Most Viewed:** 10 most read articles in the last 30 days
- **Destinations:** view articles by selected destination
- **Activities:** view articles by selected tourist activity
- **Article View Page:**
  - Title, content, date, author, tourist activities
  - Comments (sorted by newest)
  - Form to submit a new comment
  - Visit count increases on each open

---

## Error Handling & Validation

- All fields are required (no nulls or empty strings)
- Backend validation for:
  - Duplicate emails
  - Duplicate destination names
  - Empty input fields
- Meaningful error messages for all invalid operations
- Only active users can authenticate into CMS
- Frontend shows validation errors on all forms

---

## Deployment

This project is built and packaged as a `.war` file and can be deployed to any compatible Java EE servlet container (e.g., Apache Tomcat).

---

## Notes

This is a student project for educational purposes. It demonstrates the integration of RESTful APIs using JAX-RS and a modern frontend using Vue.js for managing and consuming structured travel-related content.

