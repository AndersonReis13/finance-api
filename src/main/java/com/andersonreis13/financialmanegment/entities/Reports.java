package com.andersonreis13.financialmanegment.entities;

import com.andersonreis13.financialmanegment.utils.JpaConvertJson;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Map;

@Entity()
@Table(name = "reports")
public class Reports {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;
    private String type;

    @Column(columnDefinition = "jsonb")
    @Convert(converter = JpaConvertJson.class)
    private Map<String, Object> data;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Reports(Long id, User userId, String type, Map<String, Object> data, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.data = data;
        this.createdAt = createdAt;
    }

    public Reports() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
