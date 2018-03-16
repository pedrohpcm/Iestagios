package iEstagios.testes;

import static com.jayway.restassured.RestAssured.given;
import iEstagios.dao.ContaDAO;
import iEstagios.modelo.Telefone;
import iEstagios.modelo.Conta;
import iEstagios.modelo.Vaga;
import java.util.List;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Test;

/**
 *
 * @author pc
 */
//CRIAR TESTE PARA A BUSCA DE VAGAS
//CRIAR TESTE PARA O CADASTRO DO CURRÍCULO
public class RecursosDoUsuarioTeste extends FunctionalTest {
    
    @Test
    public void listarVagas() {
        List<Vaga> vagas = (List<Vaga>) given().contentType("application/json").when().post("usuario/listarVagas");
        for (Vaga vaga : vagas) {
            System.out.println(vaga.getNomeDaVaga());
        }
    }
    /*
    @Test
    public void cadastrarConcedenteTeste2() {
        String login = "APITest";
        String senha = "agiven().contentType(\"application/json\").body(login).body(senha).when().post(\"usuario/cadCon2\")pi123";
        given().contentType("application/json").body(login).body(senha).when().post("usuario/cadCon2").then().statusCode(200);
        //given().when().get("pessoa/oi").then().statusCode(200);
    }

     
    @Test
    public void cadastrarConcedenteTeste() {
        Usuario u  = new Usuario();
        u.setLogin("APITest");
        u.setSenha("api123");
        given().contentType("application/json").body(u).when().post("usuario/cadCon").then().statusCode(200);
        //given().when().get("pessoa/oi").then().statusCode(200);
    }
    @Test
    public void basicPingTest() {
        Telefone t = new Telefone();
        t.setNumero("(84)98899-2898");
        given().contentType("application/json").body(t).when().post("usuario/teste").then().statusCode(200);
        //given().when().get("pessoa/oi").then().statusCode(200);
    }
    
    @Test
    public void invalidNameShouldReturnBadRequest(){
        given().when().get("pessoa/buscar/Gerardo").then().statusCode(400);
    }
    
    @Test
    public void verifyName(){
        given().when().get("pessoa/buscar/Teste1").then().body(containsString("Teste1"));
        given().when().get("pessoa/buscar/Teste1").then().body("nome", equalTo("Teste1"));  
    }
    
    @Test
    public void verifySave(){
        Pessoa p = new Pessoa("Gerardo");
        given().contentType("application/json").body(p).when().post("pessoa/salvar").then().statusCode(200);
    }*/
}
