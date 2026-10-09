package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/koalas")
public class KoalaController {

    public Map<Integer, Koala> koalas;

    @PostConstruct
    public void init() {
        this.koalas = new HashMap<>();
    }

    // [GET] /workintech/koalas
    @GetMapping
    public List<Koala> getAll() {
        return new ArrayList<>(koalas.values());
    }

    // [GET] /workintech/koalas/{id}
    @GetMapping("/{id}")
    public Koala getById(@PathVariable Integer id) {
        if (id == null || id <= 0) {
            throw new ZooException("Id must be greater than zero: " + id, HttpStatus.BAD_REQUEST);
        }
        if (!koalas.containsKey(id)) {
            throw new ZooException("Koala with given id is not exist: " + id, HttpStatus.NOT_FOUND);
        }
        return koalas.get(id);
    }

    // [POST] /workintech/koalas
    @PostMapping
    public Koala create(@RequestBody Koala koala) {
        if (koala == null || koala.getId() <= 0 || koala.getName() == null) {
            throw new ZooException("Koala credentials are not valid", HttpStatus.BAD_REQUEST);
        }
        koalas.put(koala.getId(), koala);
        return koala;
    }

    // [PUT] /workintech/koalas/{id}
    @PutMapping("/{id}")
    public Koala update(@PathVariable Integer id, @RequestBody Koala koala) {
        if (id == null || id <= 0) {
            throw new ZooException("Id must be greater than zero: " + id, HttpStatus.BAD_REQUEST);
        }
        if (koala == null) {
            throw new ZooException("Koala body cannot be null", HttpStatus.BAD_REQUEST);
        }
        koala.setId(id);
        koalas.put(id, koala);
        return koala;
    }

    // [DELETE] /workintech/koalas/{id}
    @DeleteMapping("/{id}")
    public Koala delete(@PathVariable Integer id) {
        if (id == null || id <= 0) {
            throw new ZooException("Id must be greater than zero: " + id, HttpStatus.BAD_REQUEST);
        }
        if (!koalas.containsKey(id)) {
            throw new ZooException("Koala with given id is not exist: " + id, HttpStatus.NOT_FOUND);
        }
        return koalas.remove(id);
    }
}