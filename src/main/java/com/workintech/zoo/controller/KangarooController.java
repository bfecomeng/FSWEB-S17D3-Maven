package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/kangaroos")
public class KangarooController {

    public Map<Integer, Kangaroo> kangaroos;

    @PostConstruct
    public void init() {
        this.kangaroos = new HashMap<>();
    }

    // [GET] /workintech/kangaroos
    @GetMapping
    public List<Kangaroo> getAll() {
        return new ArrayList<>(kangaroos.values());
    }

    // [GET] /workintech/kangaroos/{id}
    @GetMapping("/{id}")
    public Kangaroo getById(@PathVariable Integer id) {
        if (id == null || id <= 0) {
            throw new ZooException("Id must be greater than zero: " + id, HttpStatus.BAD_REQUEST);
        }
        if (!kangaroos.containsKey(id)) {
            throw new ZooException("Kangaroo with given id is not exist: " + id, HttpStatus.NOT_FOUND);
        }
        return kangaroos.get(id);
    }

    // [POST] /workintech/kangaroos
    @PostMapping
    public Kangaroo create(@RequestBody Kangaroo kangaroo) {
        if (kangaroo == null || kangaroo.getId() == null || kangaroo.getName() == null) {
            throw new ZooException("Kangaroo credentials are not valid", HttpStatus.BAD_REQUEST);
        }
        kangaroos.put(kangaroo.getId(), kangaroo);
        return kangaroo;
    }

    // [PUT] /workintech/kangaroos/{id}
    @PutMapping("/{id}")
    public Kangaroo update(@PathVariable Integer id, @RequestBody Kangaroo kangaroo) {
        if (id == null || id <= 0) {
            throw new ZooException("Id must be greater than zero: " + id, HttpStatus.BAD_REQUEST);
        }
        if (kangaroo == null) {
            throw new ZooException("Kangaroo body cannot be null", HttpStatus.BAD_REQUEST);
        }
        kangaroo.setId(id);
        kangaroos.put(id, kangaroo);
        return kangaroo;
    }

    // [DELETE] /workintech/kangaroos/{id}
    @DeleteMapping("/{id}")
    public Kangaroo delete(@PathVariable Integer id) {
        if (id == null || id <= 0) {
            throw new ZooException("Id must be greater than zero: " + id, HttpStatus.BAD_REQUEST);
        }
        if (!kangaroos.containsKey(id)) {
            throw new ZooException("Kangaroo with given id is not exist: " + id, HttpStatus.NOT_FOUND);
        }
        return kangaroos.remove(id);
    }
}