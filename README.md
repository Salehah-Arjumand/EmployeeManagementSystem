# 🧾 Employee Management & Payroll System

This Java application is designed to assist a payroll clerk in managing employee records, calculating weekly and monthly pays, tracking holiday entitlements, and processing productivity bonuses. It simulates a real-world payroll system for a company employing both full-time and part-time workers.

---

## 📖 Overview

The system categorizes employees into two types:

- **Full-time employees**
  - Receive **monthly salary** (paid every 4th week).
  - Entitled to **holiday leave**: 20 days + 1 extra day per 3 full years of service.
  - Can receive **productivity-based bonuses** (percentage-based).
  - Holiday and bonus requests must be approved by a **manager**.

- **Part-time employees**
  - Paid **weekly wage** based on **days worked**.
  - **No holiday entitlement**.
  - Paid every week.

This application automates weekly payroll generation, employee management, and holiday/bonus tracking using object-oriented principles like **inheritance** and **encapsulation**.

---

## ✅ Features

- 👤 **Create Employee Records**
  - Add new full-time or part-time employees with personal and employment details.
  - Automatically assign employee numbers.
  - Input pay rate and years of service.

- 📅 **Track Holidays**
  - Full-time employees have trackable holiday balances.
  - Holidays must be signed by both employee and manager to be approved.
  - Remaining entitlement is updated weekly.

- 💰 **Process Bonuses**
  - Only for full-time employees.
  - Enter bonus percentage (authorized by manager + head of personnel).
  - Valid for one 4-week pay cycle.

- 📋 **Enter Work Slips**
  - Weekly input of days worked for part-time employees.

- 🧾 **Generate Pay Slips**
  - Weekly payroll run calculates and prints:
    - Employee name
    - Address
    - Employee number
    - Weekly/monthly pay (based on timing and entitlements)
  - Monthly employees are only paid **every 4 weeks** (lunar cycle).
  - Pay is adjusted for holidays and bonuses where applicable.

---

## 🧠 Object-Oriented Design

The application uses:

- 🔄 **Inheritance** to model common behaviors for all employees.
  - `Employee` (base class)
    - `FullTimeEmployee`
    - `PartTimeEmployee`

- 🔒 **Encapsulation** for proper data hiding and management.

- 📦 Utility and helper classes:
  - `PayrollManager`: Handles weekly runs and reporting.
  - `HolidayRequest`, `BonusForm`, and `WorkSlip`: Model real-world forms.
  - `PaySlip`: Models weekly payout information.

---


