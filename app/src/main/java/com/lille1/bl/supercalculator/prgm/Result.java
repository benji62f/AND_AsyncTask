package com.lille1.bl.supercalculator.prgm;

/**
 * Created by Benjamin on 17/10/2017.
 */

public class Result {
    private String functionName;
    private double[] params;
    private double result;

    public Result(String functionName, double[] params, double result) {
        this.functionName = functionName;
        this.params = params;
        this.result = result;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public double[] getParams() {
        return params;
    }

    public void setParams(double[] params) {
        this.params = params;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }
}
