package com.hotelbeds.supplierintegrations.hackertest.detector.helper.parser;

import com.hotelbeds.supplierintegrations.hackertest.detector.model.LogSignInLine;

public interface LogSignInLineParser {
    LogSignInLine parse(String line);
}
