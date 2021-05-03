package com.hotelbeds.supplierintegrations.hackertest.detector;

import com.hotelbeds.supplierintegrations.hackertest.detector.helper.parser.LogSignInLineParser;
import com.hotelbeds.supplierintegrations.hackertest.detector.helper.parser.LogSignInLineParserImpl;
import com.hotelbeds.supplierintegrations.hackertest.detector.model.LogSignInLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HackerDetectorImpl implements HackerDetector {

    private LogSignInLineParser logSignInLineParser;

    @Override
    public String parseLine(String line) {

        logSignInLineParser = new LogSignInLineParserImpl();

        LogSignInLine logSignInLine = logSignInLineParser.parse(line);

        if (logSignInLine.getSignInStatus().equals(LogSignInLine.SignInStatus.SIGNIN_SUCCESS)) {
            return null;
        } else {
            //TODO: Add Counters With static Map
            return "TODO";
        }

    }

}
