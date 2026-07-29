package com.googlemusic.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.googlemusic.api.entities.Instrumento;

@Repository
public interface InstrumentoRepository extends JpaRepository <Instrumento, Long>{

}
