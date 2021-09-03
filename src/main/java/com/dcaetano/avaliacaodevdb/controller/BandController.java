package com.dcaetano.avaliacaodevdb.controller;

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
@RequestMapping("/apirest/bands")
public class BandController {
    private final BandRepository bandRepository;
    private final AlbumRepository albumRepository;

    @Autowired
    public BandController(BandRepository bandRepository, AlbumRepository albumRepository){
        this.bandRepository = bandRepository;
        this.albumRepository = albumRepository;
    }

    @Transactional
    @PostMapping("/create")
    public ResponseEntity<Band> create(@Valid @RequestBody Band band) {
        Band createdBand = bandRepository.save(band);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdBand.getId()).toUri();

        return ResponseEntity.created(location).body(createdBand);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<Band> update(@PathVariable Long id, @Valid @RequestBody Band band) {
        Optional<Band> optBand = bandRepository.findById(id);
        if (!optBand.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        band.setId(optBand.get().getId());
        bandRepository.save(band);

        return ResponseEntity.noContent().build();
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Band> delete(@PathVariable Long id) {
        Optional<Band> optBand = bandRepository.findById(id);
        if (!optBand.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        bandRepository.delete(optBand.get());

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Band> getById(@PathVariable Long id) {
        Optional<Band> optBand = bandRepository.findById(id);
        if (!optBand.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(optBand.get());
    }

    @GetMapping
    public ResponseEntity<Page<Band>> getAll(Pageable pageable) {
        return ResponseEntity.ok(bandRepository.findAll(pageable));
    }

}
