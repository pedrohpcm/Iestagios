package iEstagios.bean;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import iEstagios.dao.DocumentoDAO;
import iEstagios.dao.ConcedenteDAO;
import iEstagios.dao.CurriculoDAO;
import iEstagios.dao.EstagioDAO;
import iEstagios.dao.VagaDAO;
import iEstagios.modelo.Aluno;
import iEstagios.modelo.AlunoDAO;
import iEstagios.modelo.Concedente;
import iEstagios.modelo.Curriculo;
import iEstagios.modelo.Endereco;
import iEstagios.modelo.Telefone;
import iEstagios.modelo.Conta;
import iEstagios.modelo.Documento;
import iEstagios.modelo.Estagio;
import iEstagios.modelo.Vaga;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@SessionScoped
public class mBeanConcedente implements Serializable {

    private Concedente concedente;
    private List<Vaga> vagas;
    private Vaga vaga;
    private List<Aluno> estudantes;
    private Curriculo curriculo;
    private Aluno estudante;
    private Conta conta;
    private List<Estagio> estagios;
    private Estagio estagioSelecionado;
    private String descricaoDocumento;
    private List<Documento> documentosDoEstagioSelecionado;

    @PostConstruct
    public void init() {
        estagioSelecionado = new Estagio();
        vaga = new Vaga();

        //Inicializando objeto Concedente com o usuário que foi colocado na sessão no bean "mBeanLogin"
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext ec = facesContext.getExternalContext();
        Map attrMap = ec.getSessionMap();
        conta = (Conta) attrMap.get("Conta");

        concedente = ConcedenteDAO.pesquisarPorIdConta(conta.getId());
        if (concedente == null) {
            concedente = new Concedente();
            concedente.setEndereco(new Endereco());
            concedente.setTelefone(new Telefone());
        }
        concedente.setConta(conta);

        //Buscando vagas da concedente
        vagas = VagaDAO.buscarPorId(concedente.getId());
        estagios = EstagioDAO.listarEstagiosDaConcedente(concedente.getId());
    }

    public void cadastrarConcedente() {
        ConcedenteDAO.cadastrarConcedente(concedente);
    }

    public void teste(FileUploadEvent uploadEvent) {
        System.out.println("AQUI " + descricaoDocumento);
    }

    public void cadastrarVaga() {
        vaga.setConcedente(concedente);
        VagaDAO.cadastrarVaga(vaga);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("indexConcedente.xhtml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void redirectContratar() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("contratarEstagiario.xhtml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void redirectDocumentos() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/iEstagios/Teste.xhtml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private UploadedFile file;
    private String fileName;
    private String url;

    public void processFileUpload(FileUploadEvent uploadEvent) {

        try {
            // byte[] arquivo = uploadEvent.getFile().getContents();

            file = uploadEvent.getFile();

            fileName = file.getFileName();

            byte[] foto = IOUtils.toByteArray(file.getInputstream());

            Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                    "cloud_name", "gerardoneto",
                    "api_key", "997166293489434",
                    "api_secret", "iH6gkuuUOTFFKEj_2JHXdUEGM20"));

            Map result = cloudinary.uploader().upload(foto, ObjectUtils.emptyMap());

            url = (String) result.get("secure_url");

            System.out.println("AQUI " + descricaoDocumento);
            Documento documento = new Documento();
            documento.setUrl(url);
            documento.setDescricao(descricaoDocumento);
            documento.setEstagio(estagioSelecionado);
            DocumentoDAO.cadastrarDocumento(documento);

            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage facesMassage = new FacesMessage("Upload do Comprovante realizado com sucesso!");
            facesMassage.setSeverity(FacesMessage.SEVERITY_INFO);
            facesContext.addMessage(null, facesMassage);

        } catch (Exception ex) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage facesMassage = new FacesMessage("Erro no Upload do Comprovante:" + ex.getMessage());
            facesMassage.setSeverity(FacesMessage.SEVERITY_ERROR);
            facesContext.addMessage(null, facesMassage);
            ex.printStackTrace();
        }
    }

    public String getDescricaoDocumento() {
        return descricaoDocumento;
    }

    public void setDescricaoDocumento(String descricaoDocumento) {
        this.descricaoDocumento = descricaoDocumento;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Concedente getConcedente() {
        return concedente;
    }

    public void setConcedente(Concedente concedente) {
        this.concedente = concedente;
    }

    public List<Vaga> getVagas() {
        return vagas;
    }

    public void setVagas(List<Vaga> vagas) {
        this.vagas = vagas;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }

    public List<Aluno> getEstudantes() {
        estudantes = AlunoDAO.pesquisarParaCurriculo(vaga.getIdVaga());
        return estudantes;
    }

    public Curriculo getCurriculo() {
        curriculo = CurriculoDAO.pesquisar(estudante.getId());
        return curriculo;
    }

    public Aluno getEstudante() {
        return estudante;
    }

    public void setEstudante(Aluno estudante) {
        this.estudante = estudante;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public List<Estagio> getEstagios() {
        return estagios;
    }

    public void setEstagios(List<Estagio> estagios) {
        this.estagios = estagios;
    }

    public Estagio getEstagioSelecionado() {
        return estagioSelecionado;
    }

    public void setEstagioSelecionado(Estagio estagioSelecionado) {
        this.estagioSelecionado = estagioSelecionado;
    }

    public List<Documento> getDocumentosDoEstagioSelecionado() {
        documentosDoEstagioSelecionado = DocumentoDAO.listarDocumentosEstagio(estagioSelecionado.getId());
        if (!documentosDoEstagioSelecionado.isEmpty()) {
        }
        return documentosDoEstagioSelecionado;
    }

    public void setDocumentosDoEstagioSelecionado(List<Documento> documentosDoEstagioSelecionado) {
        this.documentosDoEstagioSelecionado = documentosDoEstagioSelecionado;
    }

}
