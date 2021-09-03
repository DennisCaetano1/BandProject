package com.dcaetano.avaliacaodevdb.repository;

import com.dcaetano.avaliacaodevdb.entity.Band;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface BandRepository extends JpaRepository<Band, Long> {
    @Transactional
    Long deleteByName(@Param("name") String name);

    Long findByName(@Param("name") String name);
}
