package com.ey.dto.response;

public class TheatreSummaryResponse {

    private Long theatreId;
    private String name;
    private String location;

    public TheatreSummaryResponse(Long theatreId, String name, String location) {
        this.theatreId = theatreId;
        this.name = name;
        this.location = location;
    }

    public Long getTheatreId() {
        return theatreId;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }
}
