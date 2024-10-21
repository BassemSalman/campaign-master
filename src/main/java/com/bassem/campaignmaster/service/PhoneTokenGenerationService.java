package com.bassem.campaignmaster.service;

import java.security.MessageDigest;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.bassem.campaignmaster.exception.CampaignInactiveException;
import com.bassem.campaignmaster.model.Campaign;
import com.bassem.campaignmaster.model.Engagement;
import com.bassem.campaignmaster.repository.EngagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PhoneTokenGenerationService {
	@Autowired
	private EngagementRepository engagementRepo;
	@Autowired
	private MessageDigest messageDigest;

	public Set<Engagement> bulkGeneratePhoneTokens(Campaign campaign) {
		if(campaign.isActive())
			return campaign.getEngagements();
		Set<Engagement> campaignEngagements = campaign.getEngagements();
		List<Engagement> existingEngagements = engagementRepo.findAll();
		Set<String> existingTokens = existingEngagements.parallelStream()
				.map(Engagement::getPhoneToken)
				.collect(Collectors.toSet());
		String phoneToken;

		for (Engagement engagement : campaignEngagements) {
			if(engagement.getPhoneToken() != null)
				continue;
			phoneToken = this.generatePhoneToken(engagement, existingEngagements);
			existingTokens.add(phoneToken);
			engagement.setPhoneToken(phoneToken);
		}
		return campaignEngagements;
	}

	private String generatePhoneToken(Engagement engagement, List<Engagement> existingEngagements) {
		List<String> existingTokens = existingEngagements.parallelStream().map(Engagement::getPhoneToken).toList();
		String phoneNumber = engagement.getUser().getPhoneNumber();
		String hashed = this.hashPhoneNumber(phoneNumber);
		String phoneToken = hashed.substring(0,7);

		int i = 0;
		while(existingTokens.contains(phoneToken) && i+7 < hashed.length()) {
			phoneToken = hashed.substring(i, i+7); // shift right as long as it exists
			i++;
		}
		return phoneToken;
	}

    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b & 0xff)); 
        }
        return sb.toString();
    }

	private String hashPhoneNumber(String phoneNumber){
		messageDigest.update(phoneNumber.getBytes());
		return bytesToHex(messageDigest.digest());
	}
}
