package com.projetinho.apirest.util;

import com.projetinho.apirest.vo.Person;

import java.util.ArrayList;

public class RandomPersonGenerator {

    //Lista na memoria
    public static ArrayList<Person> getRandomPerson() {
        Person person = new Person();
        person.setId(0);
        person.setCpf("700.618.445-17");
        person.setNome("Joao Carlos");
        person.setEmail("jocarlos@gmail.com");

        Person person1 = new Person();
        person1.setId(1);
        person1.setCpf("700.530.444-18");
        person1.setNome("Amanda");
        person1.setEmail("amanda@gmail.com");

        ArrayList<Person> records = new ArrayList<>();
        records.add(person);
        records.add(person1);

        return records;
    }

    //Transformando a ArrayList em uma variavel "global" para todas as req terem acesso sem precisar reiniciar a array
    private static final ArrayList<Person> randomPersons = getRandomPerson();

    //Retorna todos os registros
    public static ArrayList<Person> findAll() {
        return randomPersons;
    }

    //Create
    public static ArrayList<Person> createPerson(Person person) {
        randomPersons.add(person);
        return randomPersons;
    }

    //Ready
    public Person findPerson(int id) {
        Person personFind = new Person();
        personFind.setId(id);
        int index = randomPersons.indexOf(personFind);
        if (index < 0) {
            return null;
        }

        return randomPersons.get(index);
    }

    //Update
    public Person updatePerson(int id, Person person) {
        Person personFind = new Person();
        personFind.setId(id);
        int index = randomPersons.indexOf(personFind);
        if (index < 0) {
            return null;
        }
        randomPersons.get(id).setEmail(person.getEmail());

        return randomPersons.get(index);
    }

    //Delete//
    public static String deletePerson(int id) {
        randomPersons.removeIf(person -> person.getId() == id);
        return "Registro removido com sucesso";
    }


}

