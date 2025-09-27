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

User Interface Layer - The project is created by InTelliJ IDEA with Thymeleaf template engine, for developing a clean and responsive design

Business Logic Layer - Involving Java Service Classes for dealing with validation, managing calculations, organizing workflows

Persistance Layer- Involving Java Persistance API using an Object-Relational Mapping called Hibernate to connect MySQL database

Integration Layer - Includes REST endpoints and JSON endpoints for permiting compatibility with other systems

## Storyboard (Screen Mockups)


## Functional Requirements 

## Class Diagram
class CreateBudget {
    - MonthlyGoal : Decimal
    - IncomeSource : String
    - IncomeAmount : Decimal
    - ExpenseCategory : String
    - ExpenseAmount : Decimal
}

class FinancialGoals {
    - GoalName : String
    - TargetAmount : Decimal
    - Timeframe : Date
    - InitialSavings : Decimal
    - MonthlyContribution : Decimal
    - InterestRate : Decimal
    - CompoundFrequency : Date
}

class DebtManagement {
    - CurrentDebt : Decimal
    - DebtGoal : String
    - DebtTimeframe : Date
    - DebtInterest : Decimal
}

class InvestmentTools {
    - CurrentBalance : Decimal
    - StockChooser : String
    - InvestmentGoal : Decimal
    - InvestmentTimeframe : Date
}

class TaxPreparation {
    - TaxRate : Decimal
    - CurrentDues : Decimal
    - TaxDate : Date
    - TaxPaymentGoal : String
    - TaxTimeframe : Date
}            
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

**Future Milestones (#2, 3) will be defined during sprint planning.**

## Weekly Stand-up
- **Time:** Thursdays, 8:00 PM
- **Platform:** Microsoft Teams
- **Link:** Will be shared via email to team members

[Group Meeting at 8 PM Thursdays Eastern on Teams](https://teams.microsoft.com/l/meetup-join/19%3ameeting_N2Q3NDRmOTUtMGY5ZS00ODc2LThhMzAtMmFlMWRmNGI1YzBk%40thread.v2/0?context=%7b%22Tid%22%3a%22f5222e6c-5fc6-48eb-8f03-73db18203b63%22%2c%22Oid%22%3a%224c08cbbc-a21f-4686-b074-927bde5f94c0%22%7d)
