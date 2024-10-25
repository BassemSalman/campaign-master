package com.bassem.campaignmaster.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Builder

@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(
		name="engagement",
		uniqueConstraints = @UniqueConstraint(columnNames = {"id", "phone_number_token"})
)

public class Engagement {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@NotNull
	@ManyToOne
	@JoinColumn(name="campaign_id")
	private Campaign campaign;

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id")
	private User user;

//	To activate campaign, this should be generated for all users
	@Column(name="phone_token")
	private String phoneToken;

	@Min(0)
	@NotNull
	@Column(name="clicks")
	private Long clicks;

	public void incrementClicks(){
		if(clicks == null)
			clicks = 0L;
		clicks++;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Engagement)
			return ((Engagement)obj).getId().equals(id);
		return super.equals(obj);
	}
	@Override
	public int hashCode(){
		return Long.hashCode(id);
	}
}
