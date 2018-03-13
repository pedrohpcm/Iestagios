package iEstagios.bean;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import iEstagios.dao.CurriculoDAO;
import iEstagios.dao.DocumentoDAO;
import iEstagios.dao.EstagioDAO;
import iEstagios.dao.ProcessoSeletivoDAO;
import iEstagios.dao.VagaDAO;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import iEstagios.modelo.AlunoDAO;
import iEstagios.modelo.Aluno;
import iEstagios.modelo.Endereco;
import iEstagios.modelo.Telefone;
import iEstagios.modelo.Conta;
import iEstagios.modelo.Curriculo;
import iEstagios.modelo.Documento;
import iEstagios.modelo.Estagio;
import iEstagios.modelo.Vaga;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@ViewScoped
public class mBeanAluno implements Serializable {

    private List<Vaga> vagas;
    private Vaga vaga;
    private Aluno aluno;
    private Conta conta;
    private List<Estagio> estagios;
    private String descricaoDocumento;
    private String filename;

    @PostConstruct
    public void init() {
        vaga = new Vaga();

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext ec = facesContext.getExternalContext();
        Map attrMap = ec.getSessionMap();
        conta = (Conta) attrMap.get("Conta");

        aluno = AlunoDAO.pesquisarAluno(conta.getId());

        vagas = VagaDAO.buscarVagas();
        if (aluno != null) {
            estagios = EstagioDAO.listarEstagiosDoEstudante(aluno.getId());
        } else {
            aluno = new Aluno();
            aluno.setEndereco(new Endereco());
            aluno.setTelefone(new Telefone());
            aluno.setCurriculo(new Curriculo());
        }
    }

    public void enviarCurriculoParaEmpresa() {
        ProcessoSeletivoDAO.cadastrarProcesso(vaga.getIdVaga(), aluno.getId());
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Currículo enviado com sucesso!", ""));
    }

    public void atualizarCurriculo() {
        CurriculoDAO.atualizar(aluno.getCurriculo());
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Currículo salvo com sucesso!", ""));
    }

    public void completarCadastro() {
        AlunoDAO.finalizarCadastro(aluno, conta);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/iEstagios/Estudante/indexEstudante.xhtml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    private UploadedFile file;

    public void processFileUpload(FileUploadEvent uploadEvent) {

        try {
            // byte[] arquivo = uploadEvent.getFile().getContents();

            file = uploadEvent.getFile();

            filename = file.getFileName();

            byte[] foto = IOUtils.toByteArray(file.getInputstream());

            Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                    "cloud_name", "gerardoneto",
                    "api_key", "997166293489434",
                    "api_secret", "iH6gkuuUOTFFKEj_2JHXdUEGM20"));

            Map result = cloudinary.uploader().upload(foto, ObjectUtils.emptyMap());

            url = (String) result.get("secure_url");

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
    private List<Documento> documentosDoEstagioSelecionado;

    public List<Documento> getDocumentosDoEstagioSelecionado() {
        documentosDoEstagioSelecionado = DocumentoDAO.listarDocumentosEstagio(estagioSelecionado.getId());
        if (!documentosDoEstagioSelecionado.isEmpty()) {
        }
        return documentosDoEstagioSelecionado;
    }

    public void redirectDocumentos() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/iEstagios/Estudante/Teste.xhtml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
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

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    private boolean skip;
    private String url;
    private Estagio estagioSelecionado;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Estagio getEstagioSelecionado() {
        return estagioSelecionado;
    }

    public void setEstagioSelecionado(Estagio estagioSelecionado) {
        this.estagioSelecionado = estagioSelecionado;
    }

    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false;
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public List<Estagio> getEstagios() {
        return estagios;
    }

    public void setEstagios(List<Estagio> estagios) {
        this.estagios = estagios;
    }

    public String getDescricaoDocumento() {
        return descricaoDocumento;
    }

    public void setDescricaoDocumento(String descricaoDocumento) {
        this.descricaoDocumento = descricaoDocumento;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

}
