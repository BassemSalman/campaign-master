package com.bassem.campaignmaster.repository;

import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.bassem.campaignmaster.model.Campaign;
import com.bassem.campaignmaster.model.Metrics;

@Repository

public interface MetricsRepository extends ListCrudRepository<Metrics, Long>{
	public Optional<Metrics> findByMetricsCampaign(Campaign campaign);
	public Metrics save(@Valid Metrics metrics);
}
