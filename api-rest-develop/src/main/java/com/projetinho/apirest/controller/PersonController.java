package com.projetinho.apirest.controller;

import com.projetinho.apirest.util.RandomPersonGenerator;
import com.projetinho.apirest.vo.Person;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/project/data")
@Api(value = "API REST")
@CrossOrigin(origins = "*")


public class PersonController {

    //Get listando os registros
    @GetMapping
    @ApiOperation(value = "Retorna todos os registros")
    public HttpEntity<? extends Object> findAll() {
        return ResponseEntity.ok(RandomPersonGenerator.findAll());
    }

    //Criar Registros
    @PostMapping(path = "/create")
    @ApiOperation(value = "Cria registros na lista")
    public HttpEntity<? extends Object> createOne(@RequestBody final Person person) {
        return ResponseEntity.ok(RandomPersonGenerator.createPerson(person));
    }

    //listar filtrando
    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Retorna os registros filtrados por ID")
    public HttpEntity<? extends Object> findOne(@PathVariable("id") int id) {
        System.out.println(id);
        Person person = new RandomPersonGenerator().findPerson(id);
        if (person == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(person);
    }

    //Atualizando a lista
    @PutMapping(path = "/{id}")
    @ApiOperation(value = "Atualiza os registros por ID")
    public HttpEntity<? extends Object> updateOne(@PathVariable("id") int id, @RequestBody final Person person) {
        System.out.println(id);

        Person verificar = new RandomPersonGenerator().findPerson(id);
        if (verificar == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(new RandomPersonGenerator().updatePerson(id, person));
    }

    //Delete removendo registro por Id
    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Deleta os registros por ID")
    public HttpEntity<? extends Object> deleteOne(@PathVariable("id") int id) {
        System.out.println(id);
        Person verificar = new RandomPersonGenerator().findPerson(id);
        if (verificar == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(RandomPersonGenerator.deletePerson(id));
    }
}
