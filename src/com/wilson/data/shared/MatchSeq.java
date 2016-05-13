package com.wilson.data.shared;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "match_seq")
@JsonIgnoreProperties(ignoreUnknown = true)

public class MatchSeq {


	private Long matchSeqId;
	private Long matchId;
	private Timestamp timeUpdated; 
	
	public MatchSeq(){
	}
	
	@Id
	@Column(name = "seq_id")
	public Long getMatchSeqId() {
		return matchSeqId;
	}
	@Column(name = "seq_id")
	public void setMatchSeqId(Long matchSeqId) {
		this.matchSeqId = matchSeqId;
	}
	@Column(name = "match_id")
	public Long getMatchId() {
		return matchId;
	}
	@Column(name = "match_id")
	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}
	@Column(name = "time_updated")
	public Timestamp getTimeUpdated() {
		return timeUpdated;
	}
	@Column(name = "time_updated")
	public void setTimeUpdated(Timestamp timeUpdated) {
		this.timeUpdated = timeUpdated;
	}
	
}
