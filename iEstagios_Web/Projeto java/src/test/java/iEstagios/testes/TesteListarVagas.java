package iEstagios.testes;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import iEstagios.modelo.Vaga;
import iEstagios.modeloJSON.VagasJSON;

/**
 *
 * @author Gerardo
 */
public class TesteListarVagas {

    public static void main(String[] args) {
        try {
            Client client = Client.create();
            WebResource webResource = client
                    .resource("http://localhost:44536/service/usuario/listarVagas");

            ClientResponse response = webResource.get(ClientResponse.class);

            System.out.println("Output from Server .... \n");
            VagasJSON vagas = response.getEntity(VagasJSON.class);
            for (Vaga vaga : vagas.getVagas()) {
                System.out.println(vaga.getNomeDaVaga());
            }

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }   
}
