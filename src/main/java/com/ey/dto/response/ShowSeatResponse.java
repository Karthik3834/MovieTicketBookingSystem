package com.ey.dto.response;

public class ShowSeatResponse {
	
	private Long showSeatId;
    private String seatTag;
    private String rowLabel;
    private Integer seatNumber;
    private String status;
	public Long getShowSeatId() {
		return showSeatId;
	}
	public void setShowSeatId(Long showSeatId) {
		this.showSeatId = showSeatId;
	}
	public String getSeatTag() {
		return seatTag;
	}
	public void setSeatTag(String seatTag) {
		this.seatTag = seatTag;
	}
	public String getRowLabel() {
		return rowLabel;
	}
	public void setRowLabel(String rowLabel) {
		this.rowLabel = rowLabel;
	}
	public Integer getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(Integer seatNumber) {
		this.seatNumber = seatNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
    
    

}
