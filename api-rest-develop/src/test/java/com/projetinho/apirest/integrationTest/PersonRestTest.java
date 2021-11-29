package com.projetinho.apirest.integrationTest;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

public class PersonRestTest {

    @Test
    public void deveRetornarRegistros() {//req GET chamando um parametro no RestAssured
        when().
                get("/project/data"). //URL
                then().
                statusCode(HttpStatus.SC_OK)//Status do código, deve rodar em 200 edar erro em qualquer outro.
                .body("[0].id", is(0))//Testes dos dados da primeira ArrayList da API
                .body("[0].cpf", is("700.618.445-17"))
                .body("[0].nome", is("Joao Carlos"))
                .body("[0].email", is("jocarlos@gmail.com"))
                .body("", is(notNullValue()));//Verificando se minha array tem dados

    }

    @Test
    public void deveRetornarRegistrosPorId() {//req GET chamando um parametro no RestAssured
        when().
                get("/project/data/1"). //URL
                then().
                statusCode(HttpStatus.SC_OK)
                .body("id", is(1))
                .body("cpf", is("700.530.444-18"))
                .body("nome", is("Amanda"))
                .body("email", is("amanda@gmail.com"))
                .body("", is(notNullValue()));//Verificando se minha array tem dados

    }

    @Test
    public void atualizaRegistros() {//Atualiza ps registro e retorna os registros atualizados
        given().log().all()
                .contentType(ContentType.JSON)
                .body("{\"email\": \"amandaFEI@gmail.com\"}")
                .when()
                .put("/project/data/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("email", is("amandaFEI@gmail.com"))
                .body("", is(notNullValue()));
    }

    @Test
    public void deletaRegistro() {//Deletar o Registro e retornar null para o id deletado.
        when().
                delete("/project/data/1").
                then().
                statusCode(HttpStatus.SC_OK);
    }
}
