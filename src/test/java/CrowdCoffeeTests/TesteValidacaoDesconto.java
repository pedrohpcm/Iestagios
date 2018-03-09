package CrowdCoffeeTests;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import iEstagios.modelo.Vaga;
import iEstagios.modeloJSON.VagasJSON;

/**
 *
 * @author Gerardo
 */
public class TesteValidacaoDesconto {

    public static void main(String[] args) {
        try {
            Client client = Client.create();
            WebResource webResource = client
                    .resource("http://localhost:8080/webresources/order/teste");

            ClientResponse response = webResource.get(ClientResponse.class);

            System.out.println("Output from Server .... \n");
            String mensagem = response.getEntity(String.class);
            System.out.println(mensagem);
            
            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }   
}
