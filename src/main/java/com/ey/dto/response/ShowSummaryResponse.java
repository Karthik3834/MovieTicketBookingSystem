package com.ey.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;

public class ShowSummaryResponse {

    private Long showId;
    private LocalDate showDate;
    private LocalTime startTime;
    private String screenName;

    public ShowSummaryResponse(
            Long showId,
            LocalDate showDate,
            LocalTime startTime,
            String screenName) {

        this.showId = showId;
        this.showDate = showDate;
        this.startTime = startTime;
        this.screenName = screenName;
    }

    public Long getShowId() {
        return showId;
    }

    public LocalDate getShowDate() {
        return showDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public String getScreenName() {
        return screenName;
    }
}
