package com.andersonreis13.financialmanegment.repositories;

import com.andersonreis13.financialmanegment.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
