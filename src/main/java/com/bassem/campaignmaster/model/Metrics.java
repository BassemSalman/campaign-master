package com.bassem.campaignmaster.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder

@AllArgsConstructor

@Entity
@Table(name = "metrics")
public class Metrics {
	@Id
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@JsonBackReference
	@OneToOne
	@MapsId
	@JoinColumn(name = "campaign_id")
	private Campaign metricsCampaign;

	@Min(0)
	@Column(name = "successful_clicks")
	private Integer successfulClicks;

	@Min(0)
	@Column(name = "total_clicks")
	private Integer totalClicks;

	public Metrics(){
		totalClicks = 0;
		successfulClicks = 0;
	}

	public void incrementTotalClicks(){
		totalClicks++;
	}
	public void incrementSuccessfulClicks(){
		successfulClicks++;
	}
}