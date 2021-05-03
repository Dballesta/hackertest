package com.hotelbeds.supplierintegrations.hackertest.detector.model;

public class LogSignInLine {

    public enum SignInStatus {
        SIGNIN_SUCCESS,
        SIGNIN_FAILURE
    }

    private final String ip;
    private final long epoch;
    private final SignInStatus signInStatus;
    private final String name;

    public LogSignInLine(String ip, long epoch, SignInStatus signInStatus, String name) {
        this.ip = ip;
        this.epoch = epoch;
        this.signInStatus = signInStatus;
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public long getEpoch() {
        return epoch;
    }

    public SignInStatus getSignInStatus() {
        return signInStatus;
    }

    public String getName() {
        return name;
    }
}
