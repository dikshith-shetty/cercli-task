package com.cercli.controller.request;

import com.cercli.enums.RequestCategory;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class TimeOffRequest {
    private RequestCategory requestCategory;
    private Long employeeId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
