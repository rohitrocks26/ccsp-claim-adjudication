package com.ccsp.commons.persistence.event;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Snapshot implements Serializable {

	private static final long serialVersionUID = 7113009835605310272L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(nullable = false)
	private String emitterId;
	

	@Column
	private String lastEvent;

	@Column
	private Date currentTime;
	
	@Column
	private Long sequenceNo;

	//deliveryAttempts: Vector[DeliveryAttempt] = Vector.empty,

	//persistOnEventRequests: Vector[PersistOnEventRequest] = Vector.empty)

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmitterId() {
		return emitterId;
	}

	public void setEmitterId(String emitterId) {
		this.emitterId = emitterId;
	}

	public String getLastEvent() {
		return lastEvent;
	}

	public void setLastEvent(String lastEvent) {
		this.lastEvent = lastEvent;
	}

	public Date getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(Date currentTime) {
		this.currentTime = currentTime;
	}

	public Long getSequenceNo() {
		return sequenceNo;
	}

	public void setSequenceNo(Long sequenceNo) {
		this.sequenceNo = sequenceNo;
	}
}