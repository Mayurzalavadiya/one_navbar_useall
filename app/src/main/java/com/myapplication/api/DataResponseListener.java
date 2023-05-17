package com.myapplication.api;


public interface DataResponseListener {
    public void onData_SuccessfulResponse(String stringResponse);

    void onData_FailureResponse();
}