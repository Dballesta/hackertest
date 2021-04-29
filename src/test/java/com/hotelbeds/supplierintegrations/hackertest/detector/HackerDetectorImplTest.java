package com.hotelbeds.supplierintegrations.hackertest.detector;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class HackerDetectorImplTest {


    @BeforeEach
    void setUp() {
        HackerDetector hackerDetector = new HackerDetectorImpl();
    }

    // Example 80.238.9.179,133612947,SIGNIN_SUCCESS / SIGNIN_FAILURE ,Will.Smith


    @Test
    void parseLine() {
    }
}