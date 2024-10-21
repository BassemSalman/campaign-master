package com.bassem.campaignmaster.model;

import java.time.LocalDateTime;

import com.bassem.campaignmaster.validation.annotation.ValidPhoneNumber;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder

@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "audit_trail")
public class AuditTrail {
	@Id
	@Column(name = "id", nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(name = "campaign_id")
	private Long campaignId;

	@NotNull
	@Column(name = "user_id")
	private Long userId;

	@NotNull
	@ValidPhoneNumber
	@Column(name = "phoneNumber")
	private String phoneNumber;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@CreationTimestamp
	@Column(name = "action_date")
	private LocalDateTime actionDate;
}
