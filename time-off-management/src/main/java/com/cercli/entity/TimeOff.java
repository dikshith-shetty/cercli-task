package com.cercli.entity;

import com.cercli.enums.RequestCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@Setter
@Getter
@ToString
public class TimeOff {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RequestCategory requestCategory;
    @Column(nullable = false)
    private Long employeeId;
    @Column(nullable = false)
    private LocalDateTime startDate;
    @Column(nullable = false)
    private LocalDateTime endDate;
    @Column(nullable = false, updatable = false)
    @ColumnDefault("CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
    @Column(nullable = false)
    @ColumnDefault("CURRENT_TIMESTAMP")
    private LocalDateTime modifiedAt;

    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now(ZoneId.of("UTC"));
        }
        this.modifiedAt = LocalDateTime.now(ZoneId.of("UTC"));
    }

    @PreUpdate
    protected void onUpdate() {
        this.modifiedAt = LocalDateTime.now(ZoneId.of("UTC"));
    }
}
