-- Exercise 4: Functions

CREATE OR REPLACE FUNCTION CalculateAge(p_dob IN DATE) RETURN NUMBER IS
BEGIN
    RETURN TRUNC(MONTHS_BETWEEN(SYSDATE, p_dob) / 12);
END CalculateAge;
/

CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment(
    p_loan_amount  IN NUMBER,
    p_interest_rate IN NUMBER,
    p_years        IN NUMBER
) RETURN NUMBER IS
    v_monthly_rate NUMBER;
    v_months       NUMBER;
    v_emi          NUMBER;
BEGIN
    v_monthly_rate := p_interest_rate / 100 / 12;
    v_months := p_years * 12;

    IF v_monthly_rate = 0 THEN
        RETURN p_loan_amount / v_months;
    END IF;

    v_emi := p_loan_amount * v_monthly_rate * POWER(1 + v_monthly_rate, v_months)
             / (POWER(1 + v_monthly_rate, v_months) - 1);
    RETURN ROUND(v_emi, 2);
END CalculateMonthlyInstallment;
/

CREATE OR REPLACE FUNCTION HasSufficientBalance(
    p_account_id IN NUMBER,
    p_amount     IN NUMBER
) RETURN NUMBER IS
    v_balance NUMBER;
BEGIN
    SELECT Balance INTO v_balance FROM Accounts WHERE AccountID = p_account_id;
    IF v_balance >= p_amount THEN
        RETURN 1;
    END IF;
    RETURN 0;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN 0;
END HasSufficientBalance;
/
