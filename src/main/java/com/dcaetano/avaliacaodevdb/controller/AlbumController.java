package com.dcaetano.avaliacaodevdb.controller;

import com.dcaetano.avaliacaodevdb.entity.Album;
import com.dcaetano.avaliacaodevdb.entity.Band;
import com.dcaetano.avaliacaodevdb.repository.AlbumRepository;
import com.dcaetano.avaliacaodevdb.repository.BandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/apirest/albums")
public class AlbumController {
    private final AlbumRepository albumRepository;
    private final BandRepository bandRepository;

    @Autowired
    public AlbumController(AlbumRepository albumRepository, BandRepository bandRepository){
        this.albumRepository = albumRepository;
        this.bandRepository = bandRepository;
    }

    @Transactional
    @PostMapping("/create")
    public ResponseEntity<Album> create(@RequestBody @Valid Album album) {
        Optional<Band> optBand = bandRepository.findById(album.getBand().getId());
        if (!optBand.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        album.setBand(optBand.get());

        Album createdAlbum = albumRepository.save(album);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdAlbum.getId()).toUri();

        return ResponseEntity.created(location).body(createdAlbum);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<Album> update(@RequestBody @Valid Album album, @PathVariable Long id) {
        Optional<Band> optBand = bandRepository.findById(album.getBand().getId());
        if (!optBand.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Album> optAlbum = albumRepository.findById(id);
        if (!optAlbum.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        album.setBand(optBand.get());
        album.setId(optAlbum.get().getId());
        albumRepository.save(album);

        return ResponseEntity.noContent().build();
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Album> delete(@PathVariable Long id) {
        Optional<Album> optAlbum = albumRepository.findById(id);
        if (!optAlbum.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        albumRepository.delete(optAlbum.get());

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<Album>> getAll(Pageable pageable) {
        return ResponseEntity.ok(albumRepository.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Album> getById(@PathVariable Long id) {
        Optional<Album> optAlbum = albumRepository.findById(id);
        if (!optAlbum.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(optAlbum.get());
    }
}
