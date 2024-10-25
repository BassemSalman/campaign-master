package com.bassem.campaignmaster.repository;

import com.bassem.campaignmaster.model.User;
import com.bassem.campaignmaster.validation.annotation.ValidPhoneNumber;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends ListCrudRepository<User, Long> {
	public Optional<User> findByPhoneNumber(@ValidPhoneNumber String phoneNumber);
	public User save(@Valid User user);
	public Optional<User> findByUsername(@NotBlank String username);
}

