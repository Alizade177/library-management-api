package com.farid.libraryapi.service;

public interface NotificationService {

    void sendOrderNotification(
            String email,
            Long orderId
    );
}