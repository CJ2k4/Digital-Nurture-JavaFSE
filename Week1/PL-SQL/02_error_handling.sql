-- Exercise 2: Error Handling

CREATE OR REPLACE PROCEDURE SafeTransferFunds(
    p_from_account IN NUMBER,
    p_to_account   IN NUMBER,
    p_amount       IN NUMBER
) IS
    v_from_balance Accounts.Balance%TYPE;
BEGIN
    SELECT Balance INTO v_from_balance
    FROM Accounts
    WHERE AccountID = p_from_account
    FOR UPDATE;

    IF v_from_balance < p_amount THEN
        RAISE_APPLICATION_ERROR(-20001, 'Insufficient funds in account ' || p_from_account);
    END IF;

    UPDATE Accounts SET Balance = Balance - p_amount WHERE AccountID = p_from_account;
    UPDATE Accounts SET Balance = Balance + p_amount WHERE AccountID = p_to_account;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        INSERT INTO ErrorLog (Source, Message)
        VALUES ('SafeTransferFunds', SQLERRM);
        COMMIT;
        RAISE;
END SafeTransferFunds;
/

CREATE OR REPLACE PROCEDURE UpdateSalary(
    p_employee_id IN NUMBER,
    p_percent     IN NUMBER
) IS
    v_count NUMBER;
BEGIN
    SELECT COUNT(*) INTO v_count FROM Employees WHERE EmployeeID = p_employee_id;
    IF v_count = 0 THEN
        RAISE_APPLICATION_ERROR(-20002, 'Employee ID ' || p_employee_id || ' does not exist');
    END IF;

    UPDATE Employees
    SET Salary = Salary * (1 + p_percent / 100)
    WHERE EmployeeID = p_employee_id;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        INSERT INTO ErrorLog (Source, Message)
        VALUES ('UpdateSalary', SQLERRM);
        COMMIT;
        RAISE;
END UpdateSalary;
/

CREATE OR REPLACE PROCEDURE AddNewCustomer(
    p_customer_id IN NUMBER,
    p_name        IN VARCHAR2,
    p_dob         IN DATE,
    p_balance     IN NUMBER
) IS
BEGIN
    INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
    VALUES (p_customer_id, p_name, p_dob, p_balance, SYSDATE);
    COMMIT;
EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
        ROLLBACK;
        INSERT INTO ErrorLog (Source, Message)
        VALUES ('AddNewCustomer', 'Customer ID ' || p_customer_id || ' already exists');
        COMMIT;
        RAISE_APPLICATION_ERROR(-20003, 'Customer already exists');
    WHEN OTHERS THEN
        ROLLBACK;
        INSERT INTO ErrorLog (Source, Message)
        VALUES ('AddNewCustomer', SQLERRM);
        COMMIT;
        RAISE;
END AddNewCustomer;
/
