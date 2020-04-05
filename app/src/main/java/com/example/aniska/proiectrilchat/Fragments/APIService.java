package com.example.aniska.proiectrilchat.Fragments;

import com.example.aniska.proiectrilchat.Notifications.MyReponse;
import com.example.aniska.proiectrilchat.Notifications.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAAtW1Fx7M:APA91bFQLV29ed_XgYY8pL-H--ZoTLQTvjPpf3nqBFgmE4cDUXOGSpfRVpAjHXy2UGNAtvPFrRELrd755P1g1UIV_SRY5XjgqDcrQAbQPDrTXmuJPKcVVW-sAuhBI-eIFjo30TVuw92x"
            }
    )

    @POST("fcm/send")
    Call<MyReponse> sendNotification(@Body Sender body);
}