package com.googlemusic.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.googlemusic.api.entities.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {

}