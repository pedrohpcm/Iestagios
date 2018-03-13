/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iEstagios.testes;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import iEstagios.modelo.Telefone;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import iEstagios.modelo.Vaga;
import java.util.List;

/**
 *
 * @author Gerardo
 */
public class TesteCliente {

    public static void main(String[] args) {
        try {
            /*ClientConfig clientConfig = new DefaultClientConfig();
            clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
            Client client = Client.create(clientConfig);*/
            Client client = Client.create();
            WebResource webResource = client
                    .resource("http://localhost:44536/service/usuario/testeAndroid");
            //ClientResponse response = webResource.accept("application/json")
            //        .get(ClientResponse.class);

            ClientResponse response = webResource.get(ClientResponse.class);

            System.out.println("Output from Server .... \n");
            Telefone t = response.getEntity(Telefone.class);
            System.out.println(t.getNumero());

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*  try {
            try {
                URL url = new URL("http://localhost:44536/service/usuario/t");
                URLConnection connection = url.openConnection();
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                //Telefone t = (Telefone) connection.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                while (in.readLine() != null) {
                }
                System.out.println("\nCrunchify REST Service Invoked Successfully..");
                in.close();
            } catch (Exception e) {
                System.out.println("\nError while calling Crunchify REST Service");
                System.out.println(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
