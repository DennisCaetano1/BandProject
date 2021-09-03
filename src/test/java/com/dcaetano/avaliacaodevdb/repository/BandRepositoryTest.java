package com.dcaetano.avaliacaodevdb.repository;

import com.dcaetano.avaliacaodevdb.entity.Band;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class BandRepositoryTest {

    @Autowired
    public BandRepository repository = new BandRepository() {
        @Override
        public Long deleteByName(String name) {
            return null;
        }

        @Override
        public Long findByName(String name) {
            return null;
        }

        @Override
        public List<Band> findAll() {
            return null;
        }

        @Override
        public List<Band> findAll(Sort sort) {
            return null;
        }

        @Override
        public List<Band> findAllById(Iterable<Long> longs) {
            return null;
        }

        @Override
        public <S extends Band> List<S> saveAll(Iterable<S> entities) {
            return null;
        }

        @Override
        public void flush() {

        }

        @Override
        public <S extends Band> S saveAndFlush(S entity) {
            return null;
        }

        @Override
        public <S extends Band> List<S> saveAllAndFlush(Iterable<S> entities) {
            return null;
        }

        @Override
        public void deleteAllInBatch(Iterable<Band> entities) {

        }

        @Override
        public void deleteAllByIdInBatch(Iterable<Long> longs) {

        }

        @Override
        public void deleteAllInBatch() {

        }

        @Override
        public Band getOne(Long aLong) {
            return null;
        }

        @Override
        public Band getById(Long aLong) {
            return null;
        }

        @Override
        public <S extends Band> List<S> findAll(Example<S> example) {
            return null;
        }

        @Override
        public <S extends Band> List<S> findAll(Example<S> example, Sort sort) {
            return null;
        }

        @Override
        public Page<Band> findAll(Pageable pageable) {
            return null;
        }

        @Override
        public <S extends Band> S save(S entity) {
            return null;
        }

        @Override
        public Optional<Band> findById(Long aLong) {
            return Optional.empty();
        }

        @Override
        public boolean existsById(Long aLong) {
            return false;
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void deleteById(Long aLong) {

        }

        @Override
        public void delete(Band entity) {

        }

        @Override
        public void deleteAllById(Iterable<? extends Long> longs) {

        }

        @Override
        public void deleteAll(Iterable<? extends Band> entities) {

        }

        @Override
        public void deleteAll() {

        }

        @Override
        public <S extends Band> Optional<S> findOne(Example<S> example) {
            return Optional.empty();
        }

        @Override
        public <S extends Band> Page<S> findAll(Example<S> example, Pageable pageable) {
            return null;
        }

        @Override
        public <S extends Band> long count(Example<S> example) {
            return 0;
        }

        @Override
        public <S extends Band> boolean exists(Example<S> example) {
            return false;
        }
    };

    @Test
    void createTest() {
        Band band = new Band();
        band.setName("teste");

        assertThat(repository.save(band));
        assertThat(repository.findAll()).isNotNull();
    }

    @Test
    void deleteTest() {
        assertThat(repository.deleteByName("teste"));
        assertThat(repository.findByName("teste")).isNull();
    }

}