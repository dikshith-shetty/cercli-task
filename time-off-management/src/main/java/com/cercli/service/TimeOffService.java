package com.cercli.service;


import com.cercli.controller.request.TimeOffRequest;
import com.cercli.entity.TimeOff;
import com.cercli.enums.RequestCategory;
import com.cercli.repository.TimeOffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeOffService {

    @Autowired
    TimeOffRepository timeOffRepo;

    private boolean isRequestValid(TimeOffRequest newRequest) {
        // Fetch existing requests for this employee within the time range
        List<TimeOff> existingRequests = timeOffRepo.findOverlappingRequests(
                newRequest.getEmployeeId(), newRequest.getStartDate(), newRequest.getEndDate()
        );

        for (TimeOff existingRequest : existingRequests) {
            boolean isAnnualLeaveWithWorkRemoteOverlap = isAnnualLeaveWithWorkRemoteOverlap(newRequest, existingRequest);

            if (!isAnnualLeaveWithWorkRemoteOverlap) {
                return false; // Invalid overlap detected
            }
        }
        return true; // All checks passed; the request is valid
    }

    private static boolean isAnnualLeaveWithWorkRemoteOverlap(TimeOffRequest newRequest, TimeOff existingRequest) {
        RequestCategory existingCategory = existingRequest.getRequestCategory();
        RequestCategory newCategory = newRequest.getRequestCategory();

        // Allow overlap only if one is "annual leave" and the other is "work remotely"
        return (existingCategory.equals(RequestCategory.WORK_REMOTELY) && newCategory.equals(RequestCategory.ANNUAL_LEAVE)) ||
                (existingCategory.equals(RequestCategory.ANNUAL_LEAVE) && newCategory.equals(RequestCategory.WORK_REMOTELY));
    }

    public TimeOff save(TimeOffRequest timeOffRequest) {
        if(!isRequestValid(timeOffRequest)) {
            throw new RuntimeException();
        }

        TimeOff timeOff = new TimeOff();

        timeOff.setEmployeeId(timeOffRequest.getEmployeeId());
        timeOff.setRequestCategory(timeOffRequest.getRequestCategory());
        timeOff.setStartDate(timeOffRequest.getStartDate());
        timeOff.setEndDate(timeOffRequest.getEndDate());
        return timeOffRepo.save(timeOff);
    }

    public List<TimeOff> getTimeOffFor(Long employeeId) {
        return timeOffRepo.findByEmployeeId(employeeId);
    }
}
