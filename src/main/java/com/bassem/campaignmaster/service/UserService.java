package com.bassem.campaignmaster.service;

import com.bassem.campaignmaster.dto.UserDto;
import com.bassem.campaignmaster.exception.UserAlreadyExistsException;
import com.bassem.campaignmaster.exception.UserNotFoundException;
import com.bassem.campaignmaster.mapper.UserMapper;
import com.bassem.campaignmaster.model.User;
import com.bassem.campaignmaster.repository.EngagementRepository;
import com.bassem.campaignmaster.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

	public List<UserDto> findAllDtos() {
		return mapper.toResponseDtos(repo.findAll());
	}
	
	public User findById(long id) {
		return repo.findById(id).orElseThrow(UserNotFoundException::new);
	}

	public UserDto findDtoById(long id) {
		return mapper.toResponseDto(this.findById(id));
	}

	public UserDto findDtoByPhoneNumber(String phoneNumber) {
		return mapper.toResponseDto(repo.findByPhoneNumber(phoneNumber).orElseThrow(UserNotFoundException::new));
	}

	public UserDto create(@Valid UserDto createDto) {
		if(repo.findByUsername(createDto.getUsername()).isPresent() || 
				repo.findByPhoneNumber(createDto.getPhoneNumber()).isPresent())
			throw new UserAlreadyExistsException();
		log.debug("Creating user with username {}", createDto.getUsername());
		return mapper.toResponseDto(repo.save(mapper.fromCreateDto(createDto)));
	}

	public UserDto deleteById(long id) {
		User user = this.findById(id);
		log.debug("Deleting user with username {}", user.getUsername());
		repo.delete(user);
		return mapper.toResponseDto(user);
	}
}
