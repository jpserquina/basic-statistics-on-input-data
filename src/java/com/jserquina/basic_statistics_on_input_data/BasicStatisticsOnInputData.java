package com.jserquina.basic_statistics_on_input_data;

import java.io.*;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  class BasicStatisticsOnInputData
 */
public class BasicStatisticsOnInputData {

    /**
     * Main method
     *
     */
    public static void main(String[] args) {
        ArrayList<String> numbers;
        ArrayList<BigInteger> validatedNumbers;
        boolean noErrors;

        // initialize and validate numbers from stdin
        numbers = getNumbersFromStdin();
        noErrors = getNumbersErrors(numbers) == 0;
        validatedNumbers = getValidatedNumbers(numbers);

        // massage string outputs from validated number array
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.FLOOR);
        String numbersCount = (getNumbersCount(validatedNumbers) != 0) ? Integer.toString(getNumbersCount(validatedNumbers)) : "n/a";
        // String numbersSum = noErrors ? getNumbersSum(validatedNumbers).toString() : "n/a";
        String numbersMinimum = noErrors ? getNumbersMinimum(validatedNumbers).toString() : "n/a";
        String numbersMaximum = (noErrors && !getNumbersMaximum(validatedNumbers).equals(new BigInteger("0"))) ? getNumbersMaximum(validatedNumbers).toString() : "n/a";
        String numbersMean = (noErrors && getNumbersMean(validatedNumbers) != 0) ? String.valueOf(df.format(getNumbersMean(validatedNumbers))) : "n/a";
        String numbersMedian = (noErrors && getNumbersMedian(validatedNumbers) > 0) ? String.valueOf(df.format(getNumbersMedian(validatedNumbers))) : "n/a";
        String numbersErrors = noErrors ? Integer.toString(getNumbersErrors(numbers)) : "n/a";

        // stdout
        // System.out.println(formatInputNumbers(validatedNumbers));
        System.out.println("Count : " + numbersCount);
        // System.out.println("Sum : " + numbersSum);
        System.out.println("Minimum : " + numbersMinimum);
        System.out.println("Maximum : " + numbersMaximum);
        System.out.println("Mean : " + numbersMean);
        System.out.println("Median : " + numbersMedian);
        System.out.println("Errors : " + numbersErrors);
    }

    /**
     *  Get array of strings from stdin
     *
     */
    private static ArrayList<String> getNumbersFromStdin() {
        boolean finishedInput = false;
        String stringInput;
        ArrayList<String> result = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // grab input until finished
        while (!finishedInput) {
            try {
                stringInput = br.readLine();
                // stop input when input is null (inputting nothing, then pressing ENTER)
                if ("".equals(stringInput.trim())) {
                    finishedInput = true;
                } else {
                    // else add value to the result array
                    result.add(stringInput.trim());
                }
            } catch (IOException ioException) {
                // on error, don't do anything, fail over quietly
            }
        }

        // return result array
        return result;
    }

    /**
     * Format an array of numbers into a comma-separated string
     *
     */
    public static String formatInputNumbers(ArrayList<BigInteger> numbers) {
        StringBuilder result = new StringBuilder();

        // iterate through number array, append value and a comma to string
        numbers.forEach((number) -> result.append(number).append(", "));

        // remove trailing comma and space, then return value
        return result.substring(0, result.length() - 2);
    }

    /**
     * Return count of non-number inputs
     *
     */
    public static int getNumbersErrors(ArrayList<String> numbers) {
        AtomicInteger errors = new AtomicInteger();

        numbers.forEach((number) -> {
            try {
                // test value
                BigInteger testValue = new BigInteger(number.trim());
                if (testValue.compareTo(new BigInteger("0")) < 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException numberFormatException) {
                // if the value is anything other than a positive BigInteger, bean-count
                errors.addAndGet(1);
            }
        });

        return errors.get();
    }

    /**
     * Return validated array of numbers
     *
     */
    public static ArrayList<BigInteger> getValidatedNumbers(ArrayList<String> numbers) {
        ArrayList<BigInteger> result = new ArrayList<>();

        numbers.forEach((number) -> {
            // try to build a number array
            try {
                result.add(new BigInteger(number.trim()));
            } catch (NumberFormatException numberFormatException) {
                // on error, don't do anything, fail over quietly
            }
        });

        Collections.sort(result);
        return result;
    }

    /**
     * Return count of validated numbers
     *
     */
    public static int getNumbersCount(ArrayList<BigInteger> numbers) {
        return numbers.size();
    }

    /**
     * Return count of validated numbers
     *
     */
    public static BigInteger getNumbersSum(ArrayList<BigInteger> numbers) {
        BigInteger result = new BigInteger("0");

        // we need to unroll the lambda or inline function, for the
        // result variable to receive the BigInteger.add() result
        for (BigInteger number : numbers) {
            result = result.add(number);
        }

        return result;
    }

    /**
     * Return minimum of validated numbers
     *
     */
    public static BigInteger getNumbersMinimum(ArrayList<BigInteger> numbers) {
        return numbers.stream().min(BigInteger::compareTo).orElse(null);
    }

    /**
     * Return maximum of validated numbers
     *
     */
    public static BigInteger getNumbersMaximum(ArrayList<BigInteger> numbers) {
        return numbers.stream().max(BigInteger::compareTo).orElse(null);
    }

    /**
     * Return mean of validated numbers
     *
     */
    public static float getNumbersMean(ArrayList<BigInteger> numbers) {
        return getNumbersSum(numbers).floatValue() / numbers.size();
    }

    /**
     * Return median of validated numbers
     *
     */
    public static double getNumbersMedian(ArrayList<BigInteger> numbers) {
        float result;
        int count = numbers.size();

        if (count % 2 == 1) {
            result = numbers.get(count / 2).floatValue();
        } else {
            result = (numbers.get(count / 2).floatValue() + numbers.get(count / 2 + 1).floatValue()) / 2;
        }

        return result;
    }
}
