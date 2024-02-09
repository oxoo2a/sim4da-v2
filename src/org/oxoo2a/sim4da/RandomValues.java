package org.oxoo2a.sim4da;

import java.util.function.Supplier;

public class RandomValues {

    public RandomValues ( double min_value, double max_value, Supplier<Double> distributionFunction ) {
        this.min_value = min_value;
        this.max_value = max_value;
        this.distributionFunction = distributionFunction;
    }

    public double getDouble() {
        double v = distributionFunction.get();
        if (v < 0 || v > 1) {
            System.err.println("Distribution function must return a value between 0 and 1");
            System.exit(-1);
        }
        return min_value + Math.random() * (max_value - min_value);
    }

    public long getLong () {
        return (long) getDouble();
    }

    public static Supplier<Double> getUniformDistribution() {
        return Math::random;
    }
    public static Supplier<Double> getNormalDistribution(double mean, double stdDev) {
        // Using the Box-Muller transform
        return () -> {
            double u, v, s;
            do {
                u = Math.random() * 2 - 1;
                v = Math.random() * 2 - 1;
                s = u * u + v * v;
            } while (s >= 1 || s == 0);
            double mul = Math.sqrt(-2.0 * Math.log(s) / s);
            return mean + stdDev * u * mul;
        };
    }

    private double min_value;
    private double max_value;
    private Supplier<Double> distributionFunction;
}