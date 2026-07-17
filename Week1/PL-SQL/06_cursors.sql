-- Exercise 6: Cursors

-- Scenario 1: Generate monthly statements
DECLARE
    CURSOR GenerateMonthlyStatements IS
        SELECT c.Name, t.TransactionID, t.TransactionDate, t.Amount, t.TransactionType
        FROM Transactions t
        JOIN Accounts a ON t.AccountID = a.AccountID
        JOIN Customers c ON a.CustomerID = c.CustomerID
        WHERE TRUNC(t.TransactionDate, 'MM') = TRUNC(SYSDATE, 'MM')
        ORDER BY c.Name, t.TransactionDate;

    v_current_customer Customers.Name%TYPE := NULL;
BEGIN
    FOR rec IN GenerateMonthlyStatements LOOP
        IF v_current_customer IS NULL OR v_current_customer <> rec.Name THEN
            v_current_customer := rec.Name;
            DBMS_OUTPUT.PUT_LINE('--- Statement for ' || rec.Name || ' ---');
        END IF;
        DBMS_OUTPUT.PUT_LINE(
            TO_CHAR(rec.TransactionDate, 'DD-MON') || ' | ' ||
            rec.TransactionType || ' | ' || rec.Amount
        );
    END LOOP;
END;
/

-- Scenario 2: Apply annual fee to all accounts
DECLARE
    c_annual_fee CONSTANT NUMBER := 50;
    CURSOR ApplyAnnualFee IS
        SELECT AccountID, Balance FROM Accounts;
BEGIN
    FOR rec IN ApplyAnnualFee LOOP
        UPDATE Accounts
        SET Balance = Balance - c_annual_fee,
            LastModified = SYSDATE
        WHERE AccountID = rec.AccountID;
    END LOOP;
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Annual fee of ' || c_annual_fee || ' applied to all accounts');
END;
/

-- Scenario 3: Update loan interest rates based on new policy
DECLARE
    CURSOR UpdateLoanInterestRates IS
        SELECT LoanID, InterestRate, LoanAmount FROM Loans;
    v_new_rate NUMBER;
BEGIN
    FOR rec IN UpdateLoanInterestRates LOOP
        IF rec.LoanAmount > 8000 THEN
            v_new_rate := rec.InterestRate - 0.5;
        ELSE
            v_new_rate := rec.InterestRate + 0.25;
        END IF;

        UPDATE Loans SET InterestRate = v_new_rate WHERE LoanID = rec.LoanID;
        DBMS_OUTPUT.PUT_LINE('Loan ' || rec.LoanID || ' rate updated to ' || v_new_rate);
    END LOOP;
    COMMIT;
END;
/
