package iEstagios.webservice;

import com.google.gson.Gson;
import iEstagios.dao.ContaDAO;
import iEstagios.dao.VagaDAO;
import iEstagios.email.Email;
import iEstagios.modelo.Telefone;
import iEstagios.modelo.Conta;
import iEstagios.modelo.Vaga;
import iEstagios.modeloJSON.VagasJSON;
import static java.util.Arrays.asList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ObjectNotFoundException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.primefaces.json.JSONArray;

/**
 *
 * @author pc
 */
@Path("/usuario")
public class RecursosDoUsuario {

    /*private List<Pessoa> pessoas;
    private static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    {
        pessoas = asList(new Pessoa("Teste1"), new Pessoa("Teste2"), new Pessoa("Teste3"));
    }*/
    //@GET
    //@Path("oi")
    @POST
    @Path("/teste")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response oi(Telefone telefone) {
        System.out.println(telefone.getNumero());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(RecursosDoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.OK).entity("Deu certo, zé!").type(MediaType.APPLICATION_JSON_TYPE).build();
    }

    @GET
    @Path("/t")
    @Produces("application/json")
    public Telefone ola() {
        System.out.println("Right!");
        Telefone t = new Telefone();
        t.setNumero("(84)98899-2898)");
        return t;
    }
    
    @GET
    @Path("/testeAndroid")
    @Produces("application/json")
    public String testeAndroid() {
        return "Olá, Android!";
    }
    
    @GET
    @Path("/listarVagas")
    @Produces("application/json")
    public VagasJSON listarVagas() {
        VagasJSON vagas = new VagasJSON();
        vagas.setVagas(VagaDAO.buscarVagas());
        return vagas;
    }
    
    //CRIAR MÉTODO PARA A BUSCA DE VAGAS
    //CRIAR MÉTODO PARA O CADASTRO DO CURRÍCULO
    //CRIAR DOCUMENTAÇÃO COM EXEMPLOS
    @POST
    @Path("/cadCon")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarConcedente(Conta usuario) {
        usuario.setTipo("Concedente");
        ContaDAO.cadastrar(usuario);
        System.out.println("<< " + usuario.getLogin() + " >> cadastrado com sucesso!");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(RecursosDoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.OK).entity("Deu certo, zé!").type(MediaType.APPLICATION_JSON_TYPE).build();
    }

    @POST
    @Path("/cadCon2")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarConcedente2(@PathParam("login") String login, @PathParam("senha") String senha) {
        Conta u = new Conta();
        u.setLogin(login);
        u.setSenha(senha);
        u.setTipo("Concedente");
        System.out.println(u);
        //System.out.println("<< "+usuario.getLogin()+" >> cadastrado com sucesso!");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(RecursosDoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.OK).entity("Deu certo, zé!").type(MediaType.APPLICATION_JSON_TYPE).build();
    }



    /*@GET
    @Path("oiA")
    @Produces("application/json")
    public void oiAssincrono(@Suspended AsyncResponse asyncResponse) {
        System.out.println(Thread.currentThread().getName());
        /*executorService.execute(new Runnable() {
            @Override
            public void run() {
                Response res = Response.status(Response.Status.OK).entity(Thread.currentThread().getName()).type(MediaType.APPLICATION_JSON_TYPE).build();
                asyncResponse.resume(res);
            }
        });
        executorService.execute(() -> {
            Response res = Response.status(Response.Status.OK).entity(Thread.currentThread().getName()).type(MediaType.APPLICATION_JSON_TYPE).build();
            asyncResponse.resume(res);
        });
    }

    @GET
    @Path("listar")
    @Produces("application/json")
    public Response listar() {
        return Response.ok(new Gson().toJson(pessoas)).build();
    }

    @GET
    @Path("buscar/{nome}")
    @Produces("application/json")
    public Response buscar(@PathParam("nome") String nome) throws ObjectNotFoundException {
        int index = pessoas.indexOf(new Pessoa(nome));
        if (index < 0) {
            throw new ObjectNotFoundException();
        }
        return Response.ok(new Gson().toJson(pessoas.get(index))).build();
    }

    @POST
    @Path("salvar")
    @Consumes("application/json")
    @Produces("application/json")
    public Response salvar(Pessoa pessoa) {
        System.out.println(pessoa.getNome());
        return Response.ok(new Gson().toJson("Salvo com sucesso!")).build();
    }*/
}
