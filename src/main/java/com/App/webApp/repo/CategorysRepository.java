package com.App.webApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.App.webApp.entities.Categorys;

public interface CategorysRepository extends JpaRepository<Categorys, Integer> {
	public Categorys findByCatergoryName(String categoryName);
	
}
