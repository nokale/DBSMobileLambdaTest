package com.qa.Utils;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ExcelModel {
    private String testCaseId;
    private String testName;
    private String mobileNumber;
    private String pin;
    private String dob;
    private String user;
    private String password;

    public String getTestCaseId() {
        return testCaseId;
    }
    public void setTestCaseId(String testCaseId) {
        this.testCaseId =testCaseId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    public String getPin() {
        return pin;
    }
    public void setPin(String pin) {
        this.pin = pin;
    }
    public String getDob() {
        return dob;
    }
    public void setDob(String dob) {
        this.dob = dob;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    public ExcelModel(String testCaseId, String testName, String mobileNumber, String pin, String dob, String user, String password) {
        this.testCaseId = testCaseId;
        this.testName = testName;
        this.mobileNumber = mobileNumber;
        this.pin = pin;
        this.dob = dob;
        this.user = user;
        this.password = password;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(ExcelModel.class).
                append("TestCaseId", testCaseId).
                append("TestName", testName).
                append("MobileNumber", mobileNumber).
                append("PIN", pin).
                append("DOB", dob).
                append("User", user).
                append("Password", password).
                toString();
    }

}
