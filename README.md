# Group6-Ent-app-dev

# Financial Management System 

Design Document

## Introduction

The Financial Management System is a system created to assist users with monitoring income, savings, expenses, budgets, and reaching financial goals in a one secure web application. 

It provides:
- Real-time insights into finances
- Visualizations of spending habits(charts, graphs)
- A REST API so that other systems can expend financial data

Our application will be developed into with three layers:

- **User Interface (UI):**
       - Responsive, user-friendly front end  
- **Businees Logic & Persistance:**
      - Core services and data storage 
- **REST API:**
      - JSON-based endpoints for integration

The system will be based on this structured architecture

User Interface Layer - Spring Boot with the Thymeleaf template engine. for developing a clean and responsive design. Also, the JavaScript library React could be included when working with the User Interface layer.

Business Logic Layer - Involving Java Service Classes for dealing with validation, managing calculations, organizing workflows

Persistance Layer- Involving Java Persistance API using an Object-Relational Mapping called Hibernate to connect MySQL database

Integration Layer - Includes REST endpoints and JSON endpoints for permiting compatibility with other systems

## Storyboard (Screen Mockups)

[<img width="1536" height="1024" alt="image" src="https://github.com/user-attachments/assets/4cc27817-2151-4115-8221-abf0221827ae" />
](https://imgur.com/a/mvoUUc3)

## Functional Requirements

As a Budgeting User 

 I want to set a monthly budget and category allocations 
 
 So that I can track remaining amounts per category and overall 

1.Scenarios 

**Given** a new month with no expenses and a total budget of $2,000 split as: Food $500, Housing $1,000, Transport $200, Misc $300 

**When** I view the dashboard 
 
**Then** overall remaining = $2,000 and each category shows its initial remaining amount 

**Given** an existing month with the same budget and recorded expenses: Food $120, Transport $50 

**When** I view the dashboard 
 
 **Then** overall remaining = $2,000 − $170 = $1,830 and Food remaining = $380, Transport remaining = $150 


**Given** I attempt to set category allocations that sum to more than the total (e.g., total $2,000 but allocations sum to $2,400) 

 **When** I save the budget 
 
 **Then** the system rejects the save and shows a validation error: “Category allocations exceed total budget” 

**Given** I reduce a category allocation below the already-spent amount (e.g., Food spent $200, I set Food budget to $150) 

**When** I save the budget 
 
 **Then** the system allows the save only if an override flag is set; otherwise it blocks the change with: “Allocation cannot be less than already spent” 
 
 2.Scenarios 
 
**Given** a valid category “Food” and today’s date 

 **When** I submit an expense for $45.00 with note “Groceries” 
 
 **Then** the expense is saved, the Food category total increases by $45.00, and the dashboard updates remaining amounts 

**Given** an amount of $0 or negative (e.g., −$10) 

**When** I submit the expense 

 **Then** the system rejects it with: “Amount must be greater than 0” 

**Given** a category that does not exist (e.g., “TravelLuxury”) 

 **When** I submit the expense 
 
 **Then** the system rejects it with: “Unknown category” and suggests closest matches if any 

**Given** multiple expenses submitted rapidly (e.g., 3 items in < 1s) 

 **When** I save them 
 
 **Then** the system persists all without duplicates (idempotent client-generated keys) and totals reflect the sum of all three 

**Given** an expense with a date in a closed month (e.g., month already locked for reporting) 

 **When** I submit the expense 
 
 **Then** the system rejects it with: “Month is closed; post to next open period or reopen the month”  

 3.Scenarios 
 
**Given** recorded expenses across categories for September 2025 and income entries totaling $5,000 

 **When** I generate the September 2025 report 
 
 **Then** the report shows: Income $5,000, Total Expenses (sum of all transactions), Net (Income − Expenses), and a breakdown by category 

**Given** no transactions for October 2025 

 **When** I generate the October 2025 report 
 
 **Then** the report displays zeros for expenses, the configured income (or 0 if none), and clearly labels “No transactions found” 

**Given** multiple transactions with the same merchant and category 

 **When** I generate the report with “Group by Merchant” enabled 
 
 **Then** the report aggregates those lines under the merchant, preserving category totals and listing count of transactions 

**Given** I request export as PDF and CSV 

 **When** the report is generated
 
 **Then** both files are produced with identical totals and consistent rounding (two decimal places) and include a generation timestamp ## Class Diagram

## Class Diagram
[<img width="1536" height="1024" alt="image" src="https://i.imgur.com/PEoN0v0.png" />](https://imgur.com/a/mvoUUc3)
(https://i.imgur.com/PEoN0v0.png)            
## JSON Schema

Budget Schema
>{
>  "type": "object",
>  "properties": {
>    "monthlyGoal": { "type": "integer" },
>    "items": {
>      "type": "array",
>      "items": {
>        "type": "object",
>        "properties": {
>          "type": { "type": "string" },
>          "name": { "type": "string" },
>          "amount": { "type": "integer" }
>        }
>      }
>    }
>  }
>}

Goal Schema
>{
>  "type": "object",
>  "properties": {
>    "name": { "type": "string" },
>    "targetAmount": { "type": "integer" },
>    "timeframeMonths": { "type": "integer" },
>    "initialSavings": { "type": "integer" },
>    "monthlyContribution": { "type": "integer" },
>    "interestRatePercent": { "type": "number" },
>    "frequency": { "type": "string" }
>  }
>}

## Scrum Roles
- **Dalton Towe** - Product Owner,  GitHub Admin, Flex
    - Provides cross-role support
    - Helps with integration and documentation
    - Ensures timely commits & merges
  
- **Tyler Kemp** - User Interface (UI) Developer
    - Build responsive front-end (login, dashboard, forms)
    - Consumes backend JSON API
  
- **India Hardin** - Business Logic & Persistance Specialist
    - Designs interfaces, stubs, and actual implementation
    - Builds services and DAOs
    - Handles database integration

- **Melissa Manzon** - Scrum Master and DevOps
    - Manages backlog & GitHub project board
    - Faciltates sprint planning & standup
    - Ensures consistency among layers
  
- **All Team Members** - Quality Assurance (QA)
    - Write unit tests using Given/When/Then
    - Perform code reviews
    - Run functional testing before merging

## GitHub Project Link
https://github.com/LittleMan360/Group6-Ent-app-dev

https://github.com/users/LittleMan360/projects/3

## Scrum/Kanban Board
- **Milestone #1: Define Service Endpoints, Basic UI, Write Unit Tests**
    - Set up project skeleton
    - Login functionality
    - Expense entry prototype

- **Tasks In GitHub Projects**
   - Create ReactUI skeleton
   - SetUp Spring Boot Backend
   - Define DTOs and JSON schema
   - Configure GitHub Actions for Continuous Integration/Continuous Deployment 

**Future Milestones (#2, 3) will be defined during sprint planning.**

## Weekly Stand-up
- **Time:** Thursdays, 8:00 PM
- **Platform:** Microsoft Teams
- **Link:** Will be shared via email to team members

[Group Meeting at 8 PM Thursdays Eastern on Teams](https://teams.microsoft.com/l/meetup-join/19%3ameeting_N2Q3NDRmOTUtMGY5ZS00ODc2LThhMzAtMmFlMWRmNGI1YzBk%40thread.v2/0?context=%7b%22Tid%22%3a%22f5222e6c-5fc6-48eb-8f03-73db18203b63%22%2c%22Oid%22%3a%224c08cbbc-a21f-4686-b074-927bde5f94c0%22%7d)
