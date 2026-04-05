# 🚀 AI-Powered ATS Resume Analyzer

## Overview

The **AI-Powered ATS Resume Analyzer** is a web application that evaluates resumes based on job descriptions using ATS (Applicant Tracking System) logic.
It helps users understand how well their resume matches job requirements and identifies missing skills.

## Features

* Resume Upload & Parsing
* Job Description Matching
* ATS Score Calculation
* Skill Gap Analysis
* Data Storage using MySQL
* Real-time Processing


## Tech Stack

**Frontend:**

* React.js

**Backend:**

* Java

**Database:**

* MySQL


## Project Structure

```
AI-powered-ATS-Resume-Analyzer/
├── frontend/   # React application
├── backend/    # Java application
```


## ⚙️ Setup Instructions

### 🔹 1. Clone Repository

```
git clone https://github.com/your-username/AI-powered-ATS-Resume-Analyzer.git
cd AI-powered-ATS-Resume-Analyzer
```

### 🔹 2. Backend Setup

- Open the `backend` folder in IntelliJ IDEA  
- Configure database in `application.properties`  
- Run the Spring Boot application using the Run button ▶  


### 🔹 3. Frontend Setup

```
cd frontend
npm install
npm start
```


## Database

* MySQL Database: `ats_db`
* Tables:

  * `user`
  * `ats_results`


## Workflow

1. User uploads resume
2. Frontend sends data to backend
3. Backend processes and analyzes resume
4. Results stored in MySQL
5. Score & feedback returned to user

