-- Exercise 1: Control Structures

-- Scenario 1: 1% discount on loan interest for customers above 60
DECLARE
    CURSOR c_customers IS
        SELECT c.CustomerID, c.Name, c.DOB, l.LoanID, l.InterestRate
        FROM Customers c
        JOIN Loans l ON c.CustomerID = l.CustomerID;
    v_age NUMBER;
BEGIN
    FOR rec IN c_customers LOOP
        v_age := TRUNC(MONTHS_BETWEEN(SYSDATE, rec.DOB) / 12);
        IF v_age > 60 THEN
            UPDATE Loans
            SET InterestRate = InterestRate - 1
            WHERE LoanID = rec.LoanID;
            DBMS_OUTPUT.PUT_LINE('Discount applied for ' || rec.Name || ' (age ' || v_age || ')');
        END IF;
    END LOOP;
    COMMIT;
END;
/

-- Scenario 2: Promote customers with balance over $10,000 to VIP
DECLARE
    CURSOR c_customers IS SELECT CustomerID, Name, Balance FROM Customers;
BEGIN
    FOR rec IN c_customers LOOP
        IF rec.Balance > 10000 THEN
            UPDATE Customers SET IsVIP = 'Y' WHERE CustomerID = rec.CustomerID;
            DBMS_OUTPUT.PUT_LINE(rec.Name || ' promoted to VIP');
        ELSE
            UPDATE Customers SET IsVIP = 'N' WHERE CustomerID = rec.CustomerID;
        END IF;
    END LOOP;
    COMMIT;
END;
/

-- Scenario 3: Remind customers whose loans are due within 30 days
DECLARE
    CURSOR c_due_loans IS
        SELECT c.Name, l.LoanID, l.EndDate
        FROM Loans l
        JOIN Customers c ON l.CustomerID = c.CustomerID
        WHERE l.EndDate BETWEEN SYSDATE AND SYSDATE + 30;
BEGIN
    FOR rec IN c_due_loans LOOP
        DBMS_OUTPUT.PUT_LINE(
            'REMINDER: Dear ' || rec.Name ||
            ', your loan #' || rec.LoanID ||
            ' is due on ' || TO_CHAR(rec.EndDate, 'DD-MON-YYYY')
        );
    END LOOP;
END;
/
