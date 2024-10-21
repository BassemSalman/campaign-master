package com.bassem.campaignmaster.repository;

import com.bassem.campaignmaster.exception.UserNotFoundException;
import com.bassem.campaignmaster.model.User;
import com.bassem.campaignmaster.validation.annotation.ValidPhoneNumber;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends ListCrudRepository<User, Long>{
	public Optional<User> findByPhoneNumber(@NotNull @ValidPhoneNumber String phoneNumber);
	public User save(@Valid User user);
	public Optional<User> findByUsername(@NotNull String username);
	public default User find(Long id){
		return this.findById(id).orElseThrow(UserNotFoundException::new);
	}
}
