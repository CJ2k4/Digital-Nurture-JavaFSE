-- Exercise 5: Triggers

CREATE OR REPLACE TRIGGER UpdateCustomerLastModified
BEFORE UPDATE ON Customers
FOR EACH ROW
BEGIN
    :NEW.LastModified := SYSDATE;
END;
/

CREATE OR REPLACE TRIGGER LogTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
    INSERT INTO AuditLog (TransactionID, AccountID, Action)
    VALUES (:NEW.TransactionID, :NEW.AccountID, 'INSERT');
END;
/

CREATE OR REPLACE TRIGGER CheckTransactionRules
BEFORE INSERT ON Transactions
FOR EACH ROW
DECLARE
    v_balance NUMBER;
BEGIN
    IF :NEW.TransactionType = 'Deposit' AND :NEW.Amount <= 0 THEN
        RAISE_APPLICATION_ERROR(-20020, 'Deposit amount must be positive');
    END IF;

    IF :NEW.TransactionType = 'Withdrawal' THEN
        SELECT Balance INTO v_balance FROM Accounts WHERE AccountID = :NEW.AccountID;
        IF :NEW.Amount <= 0 THEN
            RAISE_APPLICATION_ERROR(-20021, 'Withdrawal amount must be positive');
        END IF;
        IF v_balance < :NEW.Amount THEN
            RAISE_APPLICATION_ERROR(-20022, 'Withdrawal exceeds account balance');
        END IF;
    END IF;
END;
/
