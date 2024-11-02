package com.cercli.controller;


import com.cercli.controller.request.TimeOffRequest;
import com.cercli.entity.TimeOff;
import com.cercli.service.TimeOffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/time-off")
public class TimeOffController {

    @Autowired
    TimeOffService timeOffService;

    @PostMapping
    public TimeOff createTimeOff(TimeOffRequest timeOffRequest) {
        return timeOffService.save(timeOffRequest);
    }

    @GetMapping("/{employeeId}")
    public List<TimeOff> getTimeOff(@PathVariable Long employeeId) {
        return timeOffService.getTimeOffFor(employeeId);
    }

}
