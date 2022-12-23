package com.App.webApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.App.webApp.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	public User findByLoginIdAndPassword(String loginId,String password);
	public User findByLoginId(String loginId);
}
