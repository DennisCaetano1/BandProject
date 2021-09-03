package com.dcaetano.avaliacaodevdb.repository;

import com.dcaetano.avaliacaodevdb.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    @Query(
            value = "select a.name, a.genre, b.name from album a inner join band b on a.band_id = b.id",
            nativeQuery = true)
    Album findAllAlbumsAndBands();

    @Query(
            value = "select a.name, a.genre, b.name from album a inner join band b on a.band_id = b.id where a.genre = :genre and b.name = :bandName",
            nativeQuery = true)
    Album findByGenreAndBandName(@Param("genre") String genre, @Param("bandName") String bandName);

    @Override
    List<Album> findAll();

    List<Album> findAllByGenre(@Param("genre") String genre);

}
