package com.example.leo.activitystudy.Network;

public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
