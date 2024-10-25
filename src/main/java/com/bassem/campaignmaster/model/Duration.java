package com.bassem.campaignmaster.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Getter
@Setter

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Duration {
    @Min(0)
    @NotNull
    private Long value;

    @NotNull
    private DurationType type;

    private enum DurationType {
        HOUR,
        DAY,
        WEEK,
        MONTH;

        @JsonCreator
        public static DurationType fromString(String value) {
            try {
                return DurationType.valueOf(value);
            } catch (IllegalArgumentException e) {
                return null; // Set to null if the value is invalid
            }
        }
    }

    public LocalDateTime computeExpiry(){
        if(type.equals(DurationType.HOUR))
            return (now().plusHours(value));
        if(type.equals(DurationType.DAY))
            return (now().plusDays(value));
        else if(type.equals(DurationType.WEEK))
            return (now().plusWeeks(value));
        else if(type.equals(DurationType.MONTH))
            return (now().plusMonths(value));
        return null;
    }
}
