package com.ey.mapper;

import com.ey.dto.response.ShowSeatResponse;
import com.ey.entity.ShowSeat;

public class ShowSeatMapper {

    public static ShowSeatResponse toResponse(ShowSeat showSeat) {

        ShowSeatResponse response = new ShowSeatResponse();
        response.setShowSeatId(showSeat.getShowSeatId());
        response.setStatus(showSeat.getStatus());

        response.setSeatTag(showSeat.getSeat().getSeatTag());
        response.setRowLabel(showSeat.getSeat().getRowLabel());
        response.setSeatNumber(showSeat.getSeat().getSeatNumber());

        return response;
    }
}
