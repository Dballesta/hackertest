package com.hotelbeds.supplierintegrations.hackertest.detector.helper.parser;

import com.hotelbeds.supplierintegrations.hackertest.detector.model.LogSignInLine;
import org.springframework.stereotype.Component;
import sun.net.util.IPAddressUtil;

import java.time.Instant;

@Component
public class LogSignInLineParserImpl implements LogSignInLineParser {

    @Override
    public LogSignInLine parse(String line) {
        String[] splitLines = validateSigninLine(line);

        String ip = splitLines[0];
        String epoch = splitLines[1];
        String signInStatus = splitLines[2];
        String name = splitLines[3];

        return new LogSignInLine(validateIpAddress(ip), validateEpochTime(epoch), validateSignInStatus(signInStatus), name);
    }

    private String[] validateSigninLine(String line) {
        if (line == null)
            throw new LogSignInLineParserException("Log Signin Line is null");

        String[] splitLines = line.split(",");

        if (splitLines.length != 4)
            throw new LogSignInLineParserException("Malformed Log Signing Line");

        return splitLines;
    }


    private String validateIpAddress(String ip) {
        if (IPAddressUtil.isIPv4LiteralAddress(ip)) {
            return ip;
        } else {
            throw new LogSignInLineParserException("Ip Address not valid: " + ip);
        }
    }

    private long validateEpochTime(String epochStr) {
        try {
            long epoch = Long.parseLong(epochStr);

            if (Instant.ofEpochSecond(epoch).isAfter(Instant.now()))
                throw new LogSignInLineParserException("Epoch is after Current Time");
            else
                return epoch;

        } catch (NumberFormatException e) {
            throw new LogSignInLineParserException("Wrong Format Epoch", e);
        }
    }

    private LogSignInLine.SignInStatus validateSignInStatus(String signInStatus) {
        if (signInStatus.equals(LogSignInLine.SignInStatus.SIGNIN_SUCCESS.name())) {
            return LogSignInLine.SignInStatus.SIGNIN_SUCCESS;
        } else if (signInStatus.equals(LogSignInLine.SignInStatus.SIGNIN_FAILURE.name())) {
            return LogSignInLine.SignInStatus.SIGNIN_FAILURE;
        } else {
            throw new LogSignInLineParserException("Invalid Status");
        }
    }
}
