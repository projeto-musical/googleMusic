package com.googlemusic.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.googlemusic.api.entities.Luthier;

@Repository
public interface LuthierRepository extends JpaRepository<Luthier, Long>{

	
	
}
