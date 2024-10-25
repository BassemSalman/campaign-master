package com.bassem.campaignmaster.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static java.time.LocalDateTime.now;


@Getter
@Setter

@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(
		name="campaign",
		uniqueConstraints = @UniqueConstraint(columnNames = {"name"})
)

public class Campaign {
	@Id
	@Column(name = "id", nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(name = "name", unique = true)
	private String name;

	@NotBlank
	@URL
	@Column(name = "url")
	private String url;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "activation_date")
	private LocalDateTime activationDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "expiry_date")
	private LocalDateTime expiryDate;

	@OneToMany(mappedBy = "campaign", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Engagement> engagements = new HashSet<>();

	@JsonManagedReference
	@PrimaryKeyJoinColumn
	@OneToOne(mappedBy = "metricsCampaign", cascade = CascadeType.ALL)
	private Metrics metrics;

	public void activate(Duration duration) {
		this.activationDate = now();
		this.expiryDate = duration.computeExpiry();
	}

	public void deactivate(){
		this.expiryDate = now();
		engagements.parallelStream().forEach(
				engagement -> engagement.setPhoneToken(null)
		);

	}

	public boolean isActive(){
		return this.activationDate != null && now().isBefore(this.getExpiryDate());
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Campaign)
			return ((Campaign)obj).getId().equals(id);
		return super.equals(obj);
	}

	@Override
	public int hashCode(){
		return Long.hashCode(id);
	}
}
