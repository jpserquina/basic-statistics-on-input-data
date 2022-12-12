package com.jserquina.basic_statistics_on_input_data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * class testBasicStatisticsOnInputData
 */

public class BasicStatisticsOnInputDataTest {
    // test numbers array
    static ArrayList<String> testNumbersArray = new ArrayList<>() {{
        add("9223372036854775807");         // bigint, maximum long number
        add("123443243142332423421132");    // bigint value
        add("12342");                       // bigint value
        add("cumulonimbus");                // !!string
        add("43564cumulonimbus6577");       // !!string
        add(" 42");                         // !!bigint value
        add("52 ");                         // !!bigint value
        add("52");                          // bigint value
    }};

    // validated numbers string
    String outputNumbersString = "42, 52, 52, 12342, 9223372036854775807, 123443243142332423421132";

    // validated sorted numbers array
    ArrayList<BigInteger> validatedNumbersArray = new ArrayList<>() {{
        add(new BigInteger("42"));                          // bigint value
        add(new BigInteger("52"));                          // bigint value
        add(new BigInteger("52"));                          // bigint value
        add(new BigInteger("12342"));                       // bigint value
        add(new BigInteger("9223372036854775807"));         // bigint, maximum long number
        add(new BigInteger("123443243142332423421132"));    // bigint value
    }};

    // values for comparison
    int getNumbersCountOutputInt = 6;
    BigInteger getNumbersSumOutputInt = new BigInteger("123452466514369278209427");
    BigInteger getNumbersMinimumOutputInt = new BigInteger("42");
    BigInteger getNumbersMaximumOutputInt = new BigInteger("123443243142332423421132");
    double getNumbersMeanOutputInt = 2.0575410969982863E22;
    double getNumbersMedianOutputInt = 4.6116860184273879E18;
    int getNumbersErrorOutputInt = 2;

    // tested validated numbers array
    static ArrayList<BigInteger> testedValidatedNumbersArray;

    @BeforeEach
    public void setup() {
        testedValidatedNumbersArray = BasicStatisticsOnInputData.getValidatedNumbers(testNumbersArray);
    }

    @Test
    public void testGetValidatedNumbers() {
        assertNotNull(testedValidatedNumbersArray);
        assertEquals(validatedNumbersArray, testedValidatedNumbersArray);
    }

    @Test
    public void testShowNumbersMethod() {
        assertEquals(outputNumbersString, BasicStatisticsOnInputData.formatInputNumbers(testedValidatedNumbersArray));
    }

    @Test
    public void testGetNumbersCountOutput() {
        assertEquals(getNumbersCountOutputInt, BasicStatisticsOnInputData.getNumbersCount(testedValidatedNumbersArray));
    }

    @Test
    public void testGetNumbersSumOutput() {
        assertEquals(getNumbersSumOutputInt, BasicStatisticsOnInputData.getNumbersSum(testedValidatedNumbersArray));
    }

    @Test
    public void testGetNumbersMinimumOutput() {
        assertEquals(getNumbersMinimumOutputInt, BasicStatisticsOnInputData.getNumbersMinimum(testedValidatedNumbersArray));
    }

    @Test
    public void testGetNumbersMaximumOutput() {
        assertEquals(getNumbersMaximumOutputInt, BasicStatisticsOnInputData.getNumbersMaximum(testedValidatedNumbersArray));
    }

    @Test
    public void testGetNumbersMeanOutput() {
        assertEquals(getNumbersMeanOutputInt, BasicStatisticsOnInputData.getNumbersMean(testedValidatedNumbersArray));
    }

    @Test
    public void testGetNumbersMedianOutput() {
        assertEquals(getNumbersMedianOutputInt, BasicStatisticsOnInputData.getNumbersMedian(testedValidatedNumbersArray));
    }

    @Test
    public void testGetNumbersErrorsOutput() {
        assertEquals(getNumbersErrorOutputInt, BasicStatisticsOnInputData.getNumbersErrors(testNumbersArray));
    }
}
