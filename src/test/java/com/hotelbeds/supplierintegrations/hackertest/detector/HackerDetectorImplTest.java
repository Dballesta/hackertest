package com.hotelbeds.supplierintegrations.hackertest.detector;

import com.hotelbeds.supplierintegrations.hackertest.detector.helper.parser.LogSignInLineParserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HackerDetectorImplTest {

    // Example 80.238.9.179,133612947,SIGNIN_SUCCESS / SIGNIN_FAILURE ,Will.Smith

    private HackerDetector hackerDetector;

    @BeforeEach
    void setUp() {
        hackerDetector = new HackerDetectorImpl();
    }

    @Test
    void parseLineWithNullLineShouldThrowException() {

        assertThrows(LogSignInLineParserException.class, () -> hackerDetector.parseLine(null));
    }

    @Test
    void parseLineWithEmptyLineShouldThrowException() {

        assertThrows(LogSignInLineParserException.class, () -> hackerDetector.parseLine(""));
    }

    @Test
    void parseLineWithWrongFormatLineShouldThrowException() {

        assertThrows(LogSignInLineParserException.class, () -> hackerDetector.parseLine("80.238.9.179,133612947,SIGNIN_FAILURE,Will.Smith,Troy.Mcclure"));
    }

    @Test
    void parseLineWrongEpochLineShouldThrowException() {

        assertThrows(LogSignInLineParserException.class, () -> hackerDetector.parseLine("80.238.9.179,WRONG_EPOCH,SIGNIN_FAILURE,Will.Smith"));
    }

    @Test
    void parseLineWithWrongStatusCodeShouldThrowException() {

        assertThrows(LogSignInLineParserException.class, () -> hackerDetector.parseLine("80.238.9.179,133612947,WRONG_STATUS,Will.Smith"));
    }

    @Test
    void parseLineWithOneSignInFailureCodeShouldReturnNull() {

        assertNull(hackerDetector.parseLine("80.238.9.179,133612947,SIGNIN_FAILURE,Will.Smith"));
    }

    @Test
    void parseLineWithSignInSuccessCodeShouldReturnNull() {

        assertNull(hackerDetector.parseLine("80.238.9.179,133612947,SIGNIN_SUCCESS,Will.Smith"));
    }

    @Test
    void parseLineWithLessThanFiveSignInFailureCodeShouldReturnIp() {

        //WHEN
        String result1 = hackerDetector.parseLine("80.238.9.179,133612947,SIGNIN_FAILURE,Will.Smith");
        String result2 = hackerDetector.parseLine("80.238.9.179,133612948,SIGNIN_FAILURE,Will.Smith");
        String result3 = hackerDetector.parseLine("80.238.9.179,133612949,SIGNIN_FAILURE,Will.Smith");
        String result4 = hackerDetector.parseLine("80.238.9.179,133612950,SIGNIN_FAILURE,Will.Smith");
        String result5 = hackerDetector.parseLine("80.238.9.179,133612951,SIGNIN_SUCCESS,Will.Smith");

        //THEN
        assertNull(result1);
        assertNull(result2);
        assertNull(result3);
        assertNull(result4);
        assertNull(result5);
    }

    @Test
    void parseLineWithFiveSignInFailureInLessThanFiveMinutesCodeShouldReturnIp() {

        //WHEN
        String result1 = hackerDetector.parseLine("80.238.9.179,133612947,SIGNIN_FAILURE,Will.Smith");
        String result2 = hackerDetector.parseLine("80.238.9.179,133612948,SIGNIN_FAILURE,Will.Smith");
        String result3 = hackerDetector.parseLine("80.238.9.179,133612949,SIGNIN_FAILURE,Will.Smith");
        String result4 = hackerDetector.parseLine("80.238.9.179,133612950,SIGNIN_FAILURE,Will.Smith");
        String result5 = hackerDetector.parseLine("80.238.9.179,"+Long.toString(133612947+60*5-1)+",SIGNIN_FAILURE,Will.Smith");

        //THEN
        assertNull(result1);
        assertNull(result2);
        assertNull(result3);
        assertNull(result4);
        assertEquals("80.238.9.179", result5);
    }

    @Test
    void parseLineWithFiveSignInFailureWithMoreThanFiveSignInFailureInFiveShouldReturnNull() {

        //WHEN
        String result1 = hackerDetector.parseLine("80.238.9.179,133612947,SIGNIN_FAILURE,Will.Smith");
        String result2 = hackerDetector.parseLine("80.238.9.179,133612948,SIGNIN_FAILURE,Will.Smith");
        String result3 = hackerDetector.parseLine("80.238.9.179,133612949,SIGNIN_FAILURE,Will.Smith");
        String result4 = hackerDetector.parseLine("80.238.9.179,133612950,SIGNIN_FAILURE,Will.Smith");
        String result5 = hackerDetector.parseLine("80.238.9.179,"+Long.toString(133612947+60*5+1)+",SIGNIN_FAILURE,Will.Smith");

        //THEN
        assertNull(result1);
        assertNull(result2);
        assertNull(result3);
        assertNull(result4);
        assertEquals("80.238.9.179", result5);
    }

    @Test
    void parseLineWithFiveSignInFailureWithMoreThanFiveMinutesDiffCodeShouldReturnNull() {

        //WHEN
        String result1 = hackerDetector.parseLine("80.238.9.179,133612947,SIGNIN_FAILURE,Will.Smith");
        String result2 = hackerDetector.parseLine("80.238.9.179,133612948,SIGNIN_FAILURE,Will.Smith");
        String result3 = hackerDetector.parseLine("80.238.9.179,133612949,SIGNIN_FAILURE,Will.Smith");
        String result4 = hackerDetector.parseLine("80.238.9.179,133612950,SIGNIN_FAILURE,Will.Smith");
        String result5 = hackerDetector.parseLine("80.238.9.179,"+Long.toString(133612947+60*5+1)+",SIGNIN_FAILURE,Will.Smith");

        //THEN
        assertNull(result1);
        assertNull(result2);
        assertNull(result3);
        assertNull(result4);
        assertEquals("80.238.9.179", result5);
    }
}