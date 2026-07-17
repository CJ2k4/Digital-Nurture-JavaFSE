# PL/SQL Solutions

Solutions for `PL SQL programming/PLSQL_Exercises.docx`.

## Setup

Run scripts in order against Oracle Database (SQL*Plus, SQL Developer, etc.):

```sql
@schema.sql
@02_error_handling.sql
@03_stored_procedures.sql
@04_functions.sql
@05_triggers.sql
@07_packages.sql
@01_control_structures.sql   -- run after schema; may modify data
@06_cursors.sql
```

Enable output for `DBMS_OUTPUT`:

```sql
SET SERVEROUTPUT ON
```

## Files

| File | Exercises |
|------|-----------|
| `schema.sql` | Tables, `AuditLog`, `ErrorLog`, sample data |
| `01_control_structures.sql` | Senior discount, VIP flag, loan reminders |
| `02_error_handling.sql` | `SafeTransferFunds`, `UpdateSalary`, `AddNewCustomer` |
| `03_stored_procedures.sql` | Monthly interest, employee bonus, `TransferFunds` |
| `04_functions.sql` | `CalculateAge`, `CalculateMonthlyInstallment`, `HasSufficientBalance` |
| `05_triggers.sql` | Last-modified, audit log, transaction rules |
| `06_cursors.sql` | Monthly statements, annual fee, loan rate updates |
| `07_packages.sql` | `CustomerManagement`, `EmployeeManagement`, `AccountOperations` |

## Example calls

```sql
EXEC ProcessMonthlyInterest;
EXEC UpdateEmployeeBonus('IT', 10);
SELECT CalculateAge(TO_DATE('1985-05-15','YYYY-MM-DD')) FROM DUAL;
SELECT HasSufficientBalance(1, 500) FROM DUAL;
SELECT CustomerManagement.GetCustomerBalance(1) FROM DUAL;
```
