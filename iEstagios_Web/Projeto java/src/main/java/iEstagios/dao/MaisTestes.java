package iEstagios.dao;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.io.FileOutputStream;
import java.io.IOException;
//import api iText
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

public class MaisTestes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // criação do objeto documento
        /*try {
            String caminho = "C:/Users/pc/Documents/NetBeansProjects/iEstagios_Maven/target/iEstagios_Maven-1.0-SNAPSHOT/resources/pdf/documento.pdf";
            InputStream stream = new FileInputStream(caminho);
            StreamedContent file = new DefaultStreamedContent(stream, "document/pdf", "documento.pdf");
            processFileUpload(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MaisTestes.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
        System.out.println("CAMINHO 1 C:/Users/pc/Documents/NetBeansProjects/iEstagios_Maven/target/iEstagios_Maven-1.0-SNAPSHOT/resources/pdf/documento.pdf");
        System.out.println(FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/pdf/documento.pdf"));
        
    }
    private static String url;

    public static void processFileUpload(StreamedContent file) {
        try {

            byte[] foto = IOUtils.toByteArray(file.getStream());

            Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                    "cloud_name", "gerardoneto",
                    "api_key", "997166293489434",
                    "api_secret", "iH6gkuuUOTFFKEj_2JHXdUEGM20"));

            Map result = cloudinary.uploader().upload(foto, ObjectUtils.emptyMap());

            url = (String) result.get("secure_url");

        } catch (Exception ex) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage facesMassage = new FacesMessage("Erro no Upload :" + ex.getMessage());
            facesMassage.setSeverity(FacesMessage.SEVERITY_ERROR);
            facesContext.addMessage(null, facesMassage);
            ex.printStackTrace();
        }
    }
}
