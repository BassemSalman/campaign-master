package com.bassem.campaignmaster.service;

import com.bassem.campaignmaster.dto.UserDTO;
import com.bassem.campaignmaster.exception.UserAlreadyExistsException;
import com.bassem.campaignmaster.exception.UserNotFoundException;
import com.bassem.campaignmaster.mapper.UserMapper;
import com.bassem.campaignmaster.model.Campaign;
import com.bassem.campaignmaster.model.Engagement;
import com.bassem.campaignmaster.model.User;
import com.bassem.campaignmaster.repository.EngagementRepository;
import com.bassem.campaignmaster.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {
	@Autowired
	private UserRepository repo;
	@Autowired
	private UserMapper mapper;
	@Autowired
	private EngagementRepository engagementRepo;

	public List<UserDTO> findAll() {
		return mapper.toResponseDTOs(repo.findAll());
	}

	public UserDTO findById(long id) {
		return mapper.toResponseDTO(repo.find(id));
	}

	public UserDTO findByPhoneNumber(String phoneNumber) {
		return mapper.toResponseDTO(repo.findByPhoneNumber(phoneNumber).orElseThrow(() -> new UserNotFoundException()));
	}

	public UserDTO create(@Valid UserDTO createDTO) {
		if(repo.findByUsername(createDTO.getUsername()).isPresent() || repo.findByPhoneNumber(createDTO.getPhoneNumber()).isPresent())
			throw new UserAlreadyExistsException();
		log.debug("Creating user with username {}", createDTO.getUsername());
		return mapper.toResponseDTO(repo.save(mapper.fromCreateDTO(createDTO)));
	}

	public UserDTO deleteById(long id) {
		User user = repo.find(id);
		log.debug("Deleting user with username {}", user.getUsername());
		repo.delete(user);
		return mapper.toResponseDTO(user);
	}
}
