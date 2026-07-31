package com.googlemusic.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.googlemusic.api.entities.Instrumento;

@Repository
public interface InstrumentoRepository extends JpaRepository<Instrumento, Long> {

    // Método correto para buscar por ID do Luthier (já que em Luthier o campo é 'id')
    List<Instrumento> findByLuthierId(Long idLuthier);

    // Método para buscar por ID da Marca (já que em Marca o campo é 'idMarca')
    List<Instrumento> findByMarcaIdMarca(Long idMarca);

    // Método para buscar por ID da Família (já que em Familia o campo é 'idFamilia')
    List<Instrumento> findByFamiliaIdFamilia(Long idFamilia);
}