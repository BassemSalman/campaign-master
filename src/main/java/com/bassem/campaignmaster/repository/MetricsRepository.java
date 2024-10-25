package com.bassem.campaignmaster.repository;

import com.bassem.campaignmaster.model.Campaign;
import com.bassem.campaignmaster.model.Metrics;
import jakarta.validation.Valid;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MetricsRepository extends ListCrudRepository<Metrics, Long>{
	public Optional<Metrics> findByMetricsCampaign(Campaign campaign);
	public Metrics save(@Valid Metrics metrics);
}
