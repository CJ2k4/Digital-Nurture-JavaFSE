package com.digitalnurture.week1.forecasting;

public class FinancialForecastingTest {

    public static void main(String[] args) {
        FinancialForecaster forecaster = new FinancialForecaster();

        double presentValue = 10000.0;
        double growthRate = 0.08;

        System.out.println("=== Recursive forecast: 10000.00 at 8% growth ===");
        for (int years = 1; years <= 5; years++) {
            double value = forecaster.futureValue(presentValue, growthRate, years);
            System.out.printf("Year %d: %.2f  (%d recursive calls)%n", years, value, forecaster.getCallCount());
        }

        System.out.println("\n=== Recursive vs iterative (must agree) ===");
        for (int years : new int[]{0, 1, 10, 25}) {
            double recursive = forecaster.futureValue(presentValue, growthRate, years);
            double iterative = forecaster.futureValueIterative(presentValue, growthRate, years);
            boolean agree = Math.abs(recursive - iterative) < 0.000001;
            System.out.printf("Years %-3d recursive=%.2f iterative=%.2f agree=%b%n", years, recursive, iterative, agree);
        }

        System.out.println("\n=== Call count grows linearly with n -> O(n) ===");
        for (int years : new int[]{5, 10, 20, 40}) {
            forecaster.futureValue(presentValue, growthRate, years);
            System.out.printf("years=%-3d calls=%d%n", years, forecaster.getCallCount());
        }

        System.out.println("\n=== Varying growth rates (memoised) ===");
        double[] rates = {0.05, 0.07, 0.03, 0.10, 0.06};
        for (int year = 1; year <= rates.length; year++) {
            double value = forecaster.futureValueVaryingRates(presentValue, rates, year);
            System.out.printf("Year %d (rate %.0f%%): %.2f%n", year, rates[year - 1] * 100, value);
        }

        System.out.println("\n=== Edge case: 0 years returns the present value ===");
        System.out.printf("futureValue(10000, 0.08, 0) = %.2f%n", forecaster.futureValue(presentValue, growthRate, 0));

        try {
            forecaster.futureValue(presentValue, growthRate, -1);
            System.out.println("FAIL: negative years should have thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("PASS: negative years rejected -> " + e.getMessage());
        }
    }
}
