package com.projetinho.apirest.utiltest;

import com.projetinho.apirest.util.RandomPersonGenerator;
import com.projetinho.apirest.vo.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RandomPersonGeneratorTest {
    RandomPersonGenerator randomPersonGenerator = new RandomPersonGenerator();
    private Person person;

    @Test
    public void createValidAndReturnPerson() {
        Person person = new Person();
        person.setId(0);
        person.setCpf("500.248.345-10");
        person.setNome("Pedro");
        person.setEmail("pedro@gmail.com");
        assertEquals("Pedro", person.getNome());
    }

    @Test
    public void applyValidIdAndReturnPerson() {
        int id = 0; //id da pessoa listada
        Person person = randomPersonGenerator.findPerson(id);//faz a busca do Id
        assertEquals("Joao Carlos", person.getNome());//retorna o nome do Id pedido
    }

    @Test
    public void notValidIdPersonReturnNull() {
        int id = 9; // quando o id é inexistente
        Person person = this.randomPersonGenerator.findPerson(id);//faz a busca do Id
        assertNull(person);//retorna null se for null
    }

    @Test
    public void updateValidIdReturnPerson() {
        int id = 1;
        Person person = this.randomPersonGenerator.findPerson(id);//faz a busca do Id
        person.setEmail("amandaFEI@gmail.com");//atualiza o email
        assertEquals("amandaFEI@gmail.com", person.getEmail());//compara o email esperado com o email atualizado
    }

    @Test
    public void deleteValidIdReturnPerson() {
        int id = 0;
        String person1 = RandomPersonGenerator.deletePerson(id);
        assertEquals("Registro removido com sucesso", person1);
    }
}
