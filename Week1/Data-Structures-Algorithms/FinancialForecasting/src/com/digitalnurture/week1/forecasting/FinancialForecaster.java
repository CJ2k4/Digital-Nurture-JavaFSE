package com.digitalnurture.week1.forecasting;

import java.util.HashMap;
import java.util.Map;

public class FinancialForecaster {

    private int callCount;

    public double futureValue(double presentValue, double growthRate, int years) {
        if (years < 0) {
            throw new IllegalArgumentException("Years cannot be negative");
        }
        callCount = 0;
        return recurse(presentValue, growthRate, years);
    }

    private double recurse(double presentValue, double growthRate, int years) {
        callCount++;
        if (years == 0) {
            return presentValue;
        }
        return recurse(presentValue, growthRate, years - 1) * (1 + growthRate);
    }

    public double futureValueIterative(double presentValue, double growthRate, int years) {
        double value = presentValue;
        for (int year = 0; year < years; year++) {
            value *= (1 + growthRate);
        }
        return value;
    }

    public double futureValueVaryingRates(double presentValue, double[] growthRates, int year) {
        return recurseVarying(presentValue, growthRates, year, new HashMap<>());
    }

    private double recurseVarying(double presentValue, double[] growthRates, int year, Map<Integer, Double> memo) {
        if (year == 0) {
            return presentValue;
        }
        Double cached = memo.get(year);
        if (cached != null) {
            return cached;
        }
        double value = recurseVarying(presentValue, growthRates, year - 1, memo) * (1 + growthRates[year - 1]);
        memo.put(year, value);
        return value;
    }

    public int getCallCount() {
        return callCount;
    }
}
