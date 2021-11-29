package com.projetinho.apirest.integrationTest;

import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

@CucumberContextConfiguration
public class DataStepDefinitions {
    @Value("${server.port}")
    private String appPort;

    //Listando registros
    @Quando("Roda a API, acessando sua URL {word} os dados aparecem")
    public void rodaAAPIAcessandoSuaURLVDataOsDadosAparecem(String endpoint) {
        when().
        get(endpoint).
                then().
                statusCode(HttpStatus.SC_OK);
    }
    @Entao("Quando é feito uma requisição GET para {word} os registros sao retornados")
    public void quandoEFeitoUmaRequisicaoGETParaVDataOsRegistrosSaoRetornados(String endpoint) {
        when().
        get(endpoint).
                then().
                statusCode(HttpStatus.SC_OK)
                .body("", is(notNullValue()));
    }


    //Busca por registro especifico
    @Quando("Acessando a URL {word} com Id")
    public void acessandoAURLVDataComId(String endpoint) {
        when().
                get(endpoint).
                then().
                statusCode(HttpStatus.SC_OK);
    }

    @Entao("Feito uma requisição GET para {word} retorna o registro do Id")
    public void feitoUmaRequisicaoGETParaVPessoasRetornaORegistroDoId(String endpoint) {
        when().
                get(endpoint). //URL
                then().
                statusCode(HttpStatus.SC_OK)
                .body("cpf", is("500.123.456-18"))
                .body("", is(notNullValue()));
    }

    //Atualiza os registros
    @Quando("Acessando a URL {word} com Id para atualizar")
    public void acessandoAURLVPessoasComIdParaAtualizar(String endpoint, Map<String, String> person) {
        given().log().all()
                .contentType(ContentType.JSON)
                .and()
                .body(person)
                .when()
                .put(endpoint)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Entao("Atualiza o registro solicitado para Id {word}")
    public void atualizaORegistroSolicitadoParaIdVData(String endpoint) {
        when()
                .get(endpoint)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("email", is("amandaFEI@gmail.com"))
                .body("", is(notNullValue()));
    }

    //deleta
    @Quando("Acessando a URL {word} com Id para deletar")
    public void acessandoAURLVDataComIdParaDeletar(String endpoint) {
        when()
                .delete(endpoint)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Entao("Deleta o registro solicitado por Id {word}")
    public void deletaORegistroSolicitadoPorIdVData(String endpoint) {
        when()
                .get(endpoint)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Quando("Roda a API, acessando sua URL {word} os dados sao criados")
    public void rodaAAPIAcessandoSuaURLVDataCreateOsDadosSaoCriados(String endpoint, Map<String, String> person) {
        given().log().all()
                .contentType(ContentType.JSON)
                .and()
                .body(person)
                .when()
                .post(endpoint)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Entao("Quando é feito uma requisição POST para {word} os registros sao retornados")
    public void quandoEFeitoUmaRequisicaoPOSTParaVDataCreateOsRegistrosSaoRetornados(String endpoint) {
        when()
                .get(endpoint)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("[2].id", is(2))
                .body("[2].nome", is("Rodrigo"))
                .body("[2].cpf", is("500.123.456-18"))
                .body("[2].email", is("rodrigoFEI@gmail.com"))
                .body("", is(notNullValue()));
    }
}
