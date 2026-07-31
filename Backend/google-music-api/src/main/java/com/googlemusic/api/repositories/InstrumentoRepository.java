package com.googlemusic.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.googlemusic.api.entities.Instrumento;

@Repository
public interface InstrumentoRepository extends JpaRepository <Instrumento, Long>{
	
	// Buscar instrumentos por família
    List<Instrumento> findByFamiliaIdFamilia(Long idFamilia);

    // Buscar instrumentos por marca
    List<Instrumento> findByMarcaIdMarca(Long idMarca);

    // Buscar instrumentos por luthier
    List<Instrumento> findByLuthierIdLuthier(Long idLuthier);

}
