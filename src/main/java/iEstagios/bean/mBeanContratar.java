package iEstagios.bean;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import iEstagios.modelo.AlunoDAO;
import iEstagios.dao.ConcedenteDAO;
import iEstagios.dao.EstagioDAO;
import iEstagios.dao.VagaDAO;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import iEstagios.dao.ContaDAO;
import iEstagios.modelo.Aluno;
import iEstagios.modelo.Concedente;
import iEstagios.modelo.Endereco;
import iEstagios.modelo.Estagio;
import iEstagios.modelo.Telefone;
import iEstagios.modelo.Vaga;
import iEstagios.email.Email;
import iEstagios.modelo.Conta;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@SessionScoped
public class mBeanContratar implements Serializable {

    private Document doc = null;
    private OutputStream os = null;
    private StreamedContent file;
    private boolean rendererVaga = false;
    private boolean rendererEstudante = false;
    private boolean rendererConcedente = false;
    private boolean rendererInstituicao = false;
    private String idVaga;
    private String cpf;
    private String cnpjConcedente;
    private String cnpjInstituicao;
    private Aluno aluno;
    private ArrayList<Aluno> alunos = new ArrayList<>();
    private ArrayList<String> cpfDosAlunos = new ArrayList<>();
    private Concedente concedente;
    private ArrayList<Concedente> concedentes = new ArrayList<>();
    private ArrayList<String> cnpjConcedentes = new ArrayList<>();
    private Concedente instituicao;
    private ArrayList<Concedente> instituicoes = new ArrayList<>();
    private ArrayList<String> cnpjInstituicoes = new ArrayList<>();
    private Vaga vaga;
    private ArrayList<Vaga> vagas = new ArrayList<>();
    private ArrayList<String> nomeVagas = new ArrayList<>();
    private Estagio estagio;
    private boolean estudanteCadastrado;
    private boolean concedenteCadastrada;
    private boolean instituicaoCadastrada;
    private boolean vagaCadastrada;

    @PostConstruct
    public void init() {
        aluno = new Aluno();
        aluno.setEndereco(new Endereco());
        aluno.setTelefone(new Telefone());
        aluno.setConta(new Conta());
        instituicao = new Concedente();
        instituicao.setEndereco(new Endereco());
        instituicao.setTelefone(new Telefone());
        instituicao.setConta(new Conta());
        estagio = new Estagio();

        //BUSCANDO CONCEDENTE 
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext ec = facesContext.getExternalContext();
        Map attrMap = ec.getSessionMap();
        Conta conta = (Conta) attrMap.get("Conta");
        concedente = ConcedenteDAO.pesquisarPorIdConta(conta.getId());
        concedente.setConta(conta);

        vaga = new Vaga();
        vaga.setConcedente(concedente);
        InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/pdf/documento.pdf");
        file = new DefaultStreamedContent(stream, "document/pdf", "TermoDeCompromisso.pdf");
    }

    public void setFile(StreamedContent file) {
        gerarPDFTermoCompromissoEstagio("");
        this.file = file;
    }

    //AÇÃO DO BOTÃO CONTRATAR
    public StreamedContent getFile() {
        /*if (!estudanteCadastrado) {
            Usuario usuario = new Usuario();
            usuario.setDado(aluno.getNome());
            usuario.setLogin(aluno.getEmail());
            usuario.setTipo("Estudante");
            UsuarioDAO.cadastrar(usuario);
            Email.enviarSenha(usuario);
        }
        if (!vagaCadastrada) {
            vaga.setIdVaga(VagaDAO.cadastrarVaga(vaga));
        }
        estagio.setAluno(aluno);
        estagio.setConcedente(concedente);
        estagio.setVaga(vaga);
        EstagioDAO.cadastrarEstagio(estagio);*/
        estagio.setAluno(aluno);
        estagio.setConcedente(concedente);
        estagio.setVaga(vaga);
        System.out.println("Aqui Estágio : " + estagio);
        estagio.setId(EstagioDAO.cadastrarEstagio(estagio));
        String nome = "Termo_Estagio_" + estagio.getId() + ".pdf";
        gerarPDFTermoCompromissoEstagio(nome);
        InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/pdf/" + nome);
        file = new DefaultStreamedContent(stream, "document/pdf", "TermoDeCompromisso.pdf");
        processFileUpload(file);
        stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/pdf/" + nome);
        file = new DefaultStreamedContent(stream, "document/pdf", "TermoDeCompromisso.pdf");
        return file;
    }

    public void cadastrarEstagio() {

    }

    private String fileName;
    private String url;
    private UploadedFile fileUploaded;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void processFileUpload(StreamedContent file) {
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

    public void setIdVaga(String idVaga) {
        this.idVaga = idVaga;
        boolean cadastrado = false;
        if (!idVaga.equals("")) {
            rendererVaga = true;
        }
        for (Vaga vaga : vagas) {
            if (vaga.getNomeDaVaga().equals(idVaga)) {
                this.vaga = vaga;
                cadastrado = true;
            }
        }
        if (cadastrado) {
            vagaCadastrada = true;
        } else {
            vagaCadastrada = false;
        }
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
        boolean cadastrado = false;
        if (!cpf.equals("")) {
            rendererEstudante = true;
        }
        for (Aluno aluno : alunos) {
            if (aluno.getCpf().equals(cpf)) {
                this.aluno = aluno;
                cadastrado = true;
            }
        }
        if (cadastrado) {
            estudanteCadastrado = true;
        } else {
            estudanteCadastrado = false;
        }
    }

    public void setCnpjConcedente(String cnpjConcedente) {
        this.cnpjConcedente = cnpjConcedente;
        boolean cadastrado = false;
        if (!cnpjConcedente.equals("")) {
            rendererConcedente = true;
        }
        for (Concedente concedente : concedentes) {
            if (concedente.getCnpj().equals(cnpjConcedente)) {
                this.concedente = concedente;
                cadastrado = true;
            }
        }
        if (cadastrado) {
            concedenteCadastrada = true;
        } else {
            concedenteCadastrada = false;
        }
    }

    public void setCnpjInstituicao(String cnpjInstituicao) {
        this.cnpjInstituicao = cnpjInstituicao;
        boolean cadastrado = false;
        if (!cnpjInstituicao.equals("")) {
            rendererInstituicao = true;
        }
        for (Concedente instituicao : instituicoes) {
            if (instituicao.getCnpj().equals(cnpjInstituicao)) {
                this.instituicao = instituicao;
                cadastrado = true;
            }
        }
        if (cadastrado) {
            instituicaoCadastrada = true;
        } else {
            instituicaoCadastrada = false;
        }
    }

    public ArrayList<String> completeTextAluno(String query) {
        pesquisarAlunos(query);
        return cpfDosAlunos;
    }

    public void pesquisarAlunos(String cpf) {
        cpfDosAlunos = new ArrayList<>();
        alunos = AlunoDAO.pesquisarPorCPF(cpf);
        if (!alunos.isEmpty()) {
            for (Aluno aluno : alunos) {
                cpfDosAlunos.add(aluno.getCpf());
            }
        }
    }

    public ArrayList<String> TextConcedente(String query) {
        pesquisarConcedentes(query);
        return cnpjConcedentes;
    }

    public void pesquisarConcedentes(String cnpj) {
        cnpjConcedentes = new ArrayList<>();
        concedentes = ConcedenteDAO.pesquisarPorCNPJ(cnpj);
        if (!concedentes.isEmpty()) {
            for (Concedente concedente : concedentes) {
                cnpjConcedentes.add(concedente.getCnpj());
            }
        }
    }

    public ArrayList<String> completeTextInstituicao(String query) {
        pesquisarInstituicoes(query);
        return cnpjInstituicoes;
    }

    public void pesquisarInstituicoes(String cnpj) {
        cnpjInstituicoes = new ArrayList<>();
        instituicoes = ConcedenteDAO.pesquisarInstituicao(cnpj);
        if (!instituicoes.isEmpty()) {
            for (Concedente concedente : instituicoes) {
                cnpjInstituicoes.add(concedente.getCnpj());
            }
        }
    }

    public ArrayList<String> completeTextVagas(String query) {
        pesquisarVagas(query);
        return nomeVagas;
    }

    public void pesquisarVagas(String nomeDaVaga) {
        nomeVagas = new ArrayList<>();
        vagas = VagaDAO.buscarVagas();
        if (!vagas.isEmpty()) {
            for (Vaga vaga : vagas) {
                nomeVagas.add(vaga.getNomeDaVaga());
            }
        }
    }

    public void onVagaSelect(SelectEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Vaga selecionada", event.getObject().toString()));
        vagaCadastrada = true;
    }

    public void onEstudanteSelect(SelectEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Estudante selecionado", event.getObject().toString()));
        estudanteCadastrado = true;
    }

    public void onInstituicaoSelect(SelectEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Instituição selecionada", event.getObject().toString()));
        instituicaoCadastrada = true;
    }

    public void onConcedenteSelect(SelectEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Concedente selecionada", event.getObject().toString()));
        concedenteCadastrada = true;
    }

    public void gerarPDFTermoCompromissoEstagio(String nome) {
        try {
            //ABRINDO DOCUMENTO E DEFININDO O ESTILO E AS MARGENS
            doc = new Document(PageSize.A4, 30, 20, 72, 72);
            //PEGANDO CAMINHO DO DOCUMENTO PDF JÁ CRIADO
            String caminho = FacesContext.getCurrentInstance().getExternalContext().getRealPath("") + "/resources/pdf/" + nome;

            //PEGANDO CAMINHO DA IMAGEM QUE SERÁ ADICIONADA AO PDF
            String caminho2 = FacesContext.getCurrentInstance().getExternalContext().getRealPath("") + "/resources/img/if.png";
            os = new FileOutputStream(caminho);
            PdfWriter.getInstance(doc, os);
            doc.open();

            Paragraph p = new Paragraph("Termo de compromisso do estágio", FontFactory.getFont(FontFactory.TIMES_BOLD, 22));
            p = new Paragraph(
                    "3 TERMO DE COMPROMISSO E PLANO DE ATIVIDADES DE ESTÁGIO SEM VINCULAÇÃO EMPREGATÍCIA NOS TERMOS DA LEI Nº 11.788 DE 25 DE SETEMBRO DE 2008.\n"
                    + "\n"
                    + "INSTITUIÇÃO DE ENSINO\n"
                    + "NOME: " + instituicao.getNome() + "\n"
                    + "ENDEREÇO: " + instituicao.getEndereco().getRua() + "," + instituicao.getEndereco().getNumero() + "\n"
                    + "BAIRRO: " + instituicao.getEndereco().getBairro() + "   "
                    + "CIDADE: " + instituicao.getEndereco().getCidade()
                    + " ESTADO: " + instituicao.getEndereco().getEstado()
                    + "  CEP: " + instituicao.getEndereco().getCep() + "\n"
                    + "CNPJ: " + instituicao.getCnpj() + "    "
                    + "TELEFONE: " + instituicao.getTelefone().getNumero() + "\n"
                    + "REPRESENTANTE: " + instituicao.getRepresentante()
                    + "    CARGO: " + instituicao.getCargo() + "\n"
                    + "PROFº ORIENTADOR: " + estagio.getOrientador()
                    + "    FONE:"
                    + "    E-MAIL PROFº ORIENTADOR: " + estagio.getEmailOrientador() + "\n"
                    + "\n"
                    + "CONCEDENTE (ÓRGÃO OU EMPRESA)\n"
                    + "RAZÃO SOCIAL: " + concedente.getRazaoSocial() + "\n"
                    + "NOME FANTASIA: " + concedente.getNome()
                    + "    CNPJ: " + concedente.getCnpj() + "\n"
                    + "ENDEREÇO: " + concedente.getEndereco().getRua() + "," + concedente.getEndereco().getNumero() + "\n"
                    + "BAIRRO: " + concedente.getEndereco().getBairro()
                    + "   CIDADE:" + concedente.getEndereco().getCidade() + "\n"
                    + "ESTADO: " + concedente.getEndereco().getEstado() + "\n"
                    + "CEP: " + concedente.getEndereco().getCep() + "\n"
                    + "TELEFONE: " + concedente.getTelefone().getNumero() + "\n"
                    + "REPRESENTANTE: " + concedente.getRepresentante()
                    + "CARGO:  " + concedente.getCargo() + "\n"
                    + "SUPERVISOR: " + estagio.getSupervisor()
                    + "CARGO:  " + concedente.getCargo()
                    + "E-MAIL DO SUPERVISOR: " + estagio.getEmailSupervisor() + "\n"
                    + "RAMO DE ATIVIDADE: " + concedente.getRamoDeAtividade() + "\n"
                    + "\n"
                    + "ESTAGIÁRIO\n"
                    + "NOME: " + aluno.getNome() + "\n"
                    + "CPF: " + aluno.getCpf()
                    + "RG: " + aluno.getRg()
                    + "ÓRGÃO EXPEDIDOR: " + aluno.getOrgaoExpeditor() + "\n"
                    + "ENDEREÇO: " + aluno.getEndereco().getRua() + "," + aluno.getEndereco().getNumero() + "\n"
                    + "BAIRRO: " + aluno.getEndereco().getBairro() + "\n"
                    + "CIDADE: " + aluno.getEndereco().getCidade()
                    + "ESTADO: " + aluno.getEndereco().getEstado() + " CEP:      \n"
                    + "DATA NASCIMENTO: " + aluno.getDataDeNascimento()
                    + " TELEFONE: " + aluno.getTelefone().getNumero()
                    + "       EMAIL: " + aluno.getConta().getLogin() + "\n"
                    + "CURSO: " + aluno.getCurso()
                    + "        PERÍODO/ANO: " + aluno.getAno()
                    + "        NÍVEL: " + aluno.getPeriodo() + "\n"
                    + " POSSUI NECESSIDADE ESPECIAL: S (  ) N (  )\n"
                    + "\n"
                    + "CLÁUSULA PRIMEIRA – DO OBJETO – Este instrumento tem como objeto o compromisso da CONCEDENTE em receber o estagiário acima qualificado, observando as cláusulas do convênio firmado entre a Instituição de Ensino e a CONCEDENTE, bem como legislação vigente.\n"
                    + "\n"
                    + "CLÁUSULA SEGUNDA – DA CARACTERIZAÇÃO DO ESTÁGIO – O estágio como ato educativo escolar supervisionado, obrigatório ou não obrigatório, desenvolvido no ambiente de trabalho, visa à preparação para o trabalho produtivo do educando e para a vida cidadã, fazendo parte do projeto pedagógico do curso. Constitui-se um instituto de integração entre a Instituição de Ensino e a concedente, capaz de proporcionar o aprendizado de competências próprias da atividade profissional e a contextualização curricular, não acarretando qualquer vínculo de caráter empregatício.\n"
                    + "§ PRIMEIRO – A manutenção de estagiários em desconformidade com a lei nº 11.788 de 25 de setembro de 2008, caracteriza vínculo empregatício do estudante com a CONCEDENTE para todos os fins da legislação trabalhista e previdenciária.\n"
                    + "§ SEGUNDO – A reincidência na irregularidade descrita no parágrafo primeiro, impedirá a CONCEDENTE de receber estagiários por 02 (dois) anos, contados da data da decisão definitiva do processo administrativo correspondente. Esta penalidade limita-se a filial ou agência em que for cometida a irregularidade.\n"
                    + "§ TERCEIRO – A eventual concessão de benefícios relacionados a transporte, alimentação e saúde, entre outros, não caracteriza vínculo empregatício\n"
                    + "\n"
                    + "CLÁUSULA TERCEIRA – DAS CONDIÇÕES DO ESTÁGIO:\n"
                    + "I – O estágio terá a duração de       meses e       dias, com início em      /      /        e término em      /      /      ;\n"
                    + "II – A jornada de estágio será de       horas diárias e       horas semanais. Horário do estágio:       .\n"
                    + "III – O estagiário desenvolverá as atividades discriminadas no Plano de Atividades do Estágio, conforme cláusula nona desse documento.\n"
                    + "§ PRIMEIRO  – A CONCEDENTE pagará ao estagiário mensalmente a importância de R$         (       ) a título de bolsa, bem como auxílio transporte no valor de R$       (     ), além do benefício:      .\n"
                    + "§ SEGUNDO – Durante a vigência deste TERMO DE COMPROMISSO DE ESTÁGIO, o estagiário estará segurado contra acidentes pessoais, conforme apólice Nº        da       - cujo CNPJ é      .\n"
                    + "§ TERCEIRO – É assegurado ao estagiário, período de recesso a ser gozado, preferencialmente, durante suas férias escolares, na forma do artigo 13 e seus parágrafos da lei 11.788/08\n"
                    + "§ QUARTO – A carga horária do estágio, deverá ser reduzida pelo menos à metade, para garantir o bom desempenho do estudante na hipótese da Instituição de Ensino adotar verificações de aprendizagem periódicas ou finais, no período de avaliação.\n"
                    + "§ QUINTO – O estágio poderá ser prorrogado mediante Termo Aditivo, não podendo, entretanto, ultrapassar o limite de 02 (dois) anos, na mesma CONCEDENTE.\n"
                    + "CLÁUSULA QUARTA - DAS OBRIGAÇÕES DAS INSTITUIÇÕES DE ENSINO – Caberá a Instituição de Ensino:\n"
                    + "a) Elencar, na cláusula nona deste documento o plano de atividades, em acordo com a CONCEDENTE e o estagiário, nele devem constar as atividades que serão desenvolvidas pelo estagiário. Este plano de atividades poderá ser aditivado à medida que for avaliado, progressivamente, o desempenho do estudante;\n"
                    + "b) Analisar as instalações da parte CONCEDENTE do estágio, reconhecendo sua adequação à formação social, cultural e profissional do estudante;\n"
                    + "c) Designar professor orientador, da área a ser desenvolvida no estágio, como responsável pelo acompanhamento e avaliação das atividades do estagiário;\n"
                    + "d) Exigir do estagiário a apresentação periódica, em prazo não superior a 06 (seis) meses, do Relatório de Atividades;\n"
                    + "e) Elaborar normas complementares e instrumentos de avaliação dos estágios de seus educandos;\n"
                    + "f) Comunicar à parte CONCEDENTE do estágio, no início do período letivo, as datas de realização de avaliações escolares ou acadêmicas, bem como manter a CONCEDENTE atualizada a cerca de qualquer alteração que estas datas venham a sofrer;\n"
                    + "g) Comunicar a CONCEDENTE a cada 06 (seis) meses a regularidade da matrícula dos estudantes bem como numa periodicidade de 06 (seis) meses, a freqüência do educando, nos termos do artigo 3º inciso I da lei 11.788/08\n"
                    + "h) Averiguar os casos de possíveis distorções ou incompatibilidade das atividades de estágio em andamento, em relação ao que foi definido neste Termo de Compromisso do Estágio.\n"
                    + "i) Emitir declaração de matrícula e freqüência efetiva às aulas, dos alunos interessados em fazer estágio, sempre que o estudante ou a CONCEDENTE solicitar.\n"
                    + "\n"
                    + "CLÁUSULA QUINTA - DAS OBRIGAÇÕES DA CONCEDENTE – Caberá a Concedente:\n"
                    + "a) Apresentar instalações que tenham condições de proporcionar ao estagiário a realização das atividades propostas;\n"
                    + "b) Designar funcionário do seu quadro de pessoal com formação ou experiência profissional na área de conhecimento desenvolvida no curso de estagiário, para orientá-lo e supervisioná-lo, observado o limite de até 10 (dez) estagiários simultaneamente para cada orientador/supervisor;\n"
                    + "c) Elaborar o Plano de Atividades em acordo com a Instituição de Ensino e o estudante;\n"
                    + "d) Propiciar à Instituição de Ensino, subsídios que possibilitem o acompanhamento, a supervisão e a avaliação do estágio;\n"
                    + "e) Encaminhar à Instituição de Ensino, com periodicidade mínima de 06 (seis) meses, o Relatório de Atividades, com vista obrigatória ao estagiário;\n"
                    + "f) Entregar o Termo de Realização de Estágio, contendo o resumo das atividades desenvolvidas, dos períodos e da avaliação de desempenho, por ocasião do desligamento do estagiário;\n"
                    + "g) Pagar bolsa conforme previsto no Parágrafo 1º da Cláusula 3ª deste Instrumento, bem como outros benefícios previamente acordados com o estagiário;\n"
                    + "h) Fornecer o Auxílio Transporte, conforme previsto no parágrafo 1º da cláusula 3ª deste Instrumento;\n"
                    + "i) Contratar em favor do estagiário, seguros contra acidentes pessoais, com apólice compatível com valores de mercado;\n"
                    + "j) Aplicar ao estagiário, a legislação de segurança e saúde no trabalho, como determina o Artigo 14 da lei 11.788/08;, especificamente no tocante ao enquadramento no Programa de Prevenção de Riscos Ambientais – PPRA e ao Programa de Controle Médico de Saúde Ocupacional- PCMSO;\n"
                    + " k) Reduzir à metade a carga horária do estágio nos períodos de avaliação, quando a Instituição de Ensino adotar verificações de aprendizagem periódicas ou finais;\n"
                    + "l) Garantir ao estagiário o gozo de recesso, preferencialmente durante suas férias escolares, na forma do artigo 13 e seus parágrafos da lei 11.788/08.\n"
                    + "\n"
                    + "CLÁUSULA SEXTA – DAS OBRIGAÇÕES DO ESTAGIÁRIO – O estagiário se compromete a:\n"
                    + "a) Executar com empenho o Plano de Atividades elaborado de comum acordo com a CONCEDENTE e a INSTITUIÇÃO DE ENSINO;\n"
                    + "b) Conhecer e cumprir as normas e recomendações internas da CONCEDENTE, em especial as que resguardam o sigilo de informações técnicas e tecnológicas;\n"
                    + "c) Elaborar relatório de estágio na forma, prazo e padrões estabelecidos pela instituição de ensino e/ou pela CONCEDENTE;\n"
                    + "d) Informar a CONCEDENTE qualquer alteração na regularidade de sua matrícula bem como na freqüência escolar, que possam de alguma forma alterar os requisitos exigidos pela lei para caracterização do presente estágio (artigo 3º, inciso I da lei 11.788/08);\n"
                    + "e) Informar a Instituição de Ensino, quando suas atividades de estágio estiverem em desacordo com as descritas no Plano de Atividades, neste instrumento ou em seu curso de formação.\n"
                    + "\n"
                    + "CLÁUSULA SÉTIMA -  DA VALIDADE – O presente TERMO DE COMPROMISSO DE ESTÁGIO – TCE terá validade a partir da data indicada no inciso I, da cláusula Terceira.\n"
                    + "\n"
                    + "CLÁUSULA OITAVA – DA RESCISÃO: O presente TERMO DE COMPROMISSO DE ESTÁGIO será rescindido nos seguintes casos:\n"
                    + "a) automaticamente, ao término do estágio;\n"
                    + "b) Por livre e unilateral deliberação da CONCEDENTE ou do estagiário;\n"
                    + "c) Quando comprovado rendimento não satisfatório do estagiário;\n"
                    + "d) Por conclusão, abandono, trancamento de matrícula ou mudança do curso realizado pelo estagiário;\n"
                    + "e) Por não cumprimento das cláusulas, normas e instruções convencionadas no presente TERMO DE COMPROMISSO DE ESTÁGIO, bem como nos convênios com a Instituição de Ensino e no contrato com a CONCEDENTE, dos quais decorre este documento legal;\n"
                    + "§ ÚNICO: A CONCEDENTE fica responsável pela emissão do Termo de Rescisão em 3(três) vias encaminhando uma via a cada uma das partes envolvidas no processo, caso haja o desligamento do estagiário antes do período previsto no presente TERMO DE COMPROMISSO DE ESTÁGIO;\n"
                    + "E, por estarem acordes, firmam o presente Termo em 3(três) vias de igual teor e forma, na presença das duas testemunhas abaixo subscritas.\n"
                    + "\n"
                    + "CLÁUSULA NONA – DO PLANO DE ATIVIDADES:\n"
                    + "E por estarem de acordo, assinam este Termo de Compromisso e Plano de Atividades os representantes das partes em três vias de igual teor e forma. \n"
                    + "As atividades acima descritas ESTÃO ADEQUADAS ao curso, conforme exigência da Lei do Estágio nº 11.788 de 25/09/2008\n"
                    + "\n"
                    + "                                                                _________/RN, ____de ___________de 20___.\n"
                    + "\n"
                    + "\nESTAGIÁRIO:\n" + "\n"
                    + "\nINSTITUIÇÃO DE ENSINO:                                                        PROFESSOR ORIENTADOR:\n"
                    + "     \n"
                    + "\nCONCEDENTE:                                                                            SUPERVISOR:\n"
                    + "\n\nTESTEMUNHA:                                                                             TESTEMUNHA:", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10));
            p.setAlignment(Element.ALIGN_JUSTIFIED);
            doc.add(p);

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(mBeanPDF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(mBeanContratar.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (doc != null) {
                doc.close();
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public Estagio getEstagio() {
        return estagio;
    }

    public void setEstagio(Estagio estagio) {
        this.estagio = estagio;
    }

    public boolean isRendererVaga() {
        return rendererVaga;
    }

    public void setRendererVaga(boolean rendererVaga) {
        this.rendererVaga = rendererVaga;
    }

    public boolean isRendererEstudante() {
        return rendererEstudante;
    }

    public void setRendererEstudante(boolean rendererEstudante) {
        this.rendererEstudante = rendererEstudante;
    }

    public boolean isRendererConcedente() {
        return rendererConcedente;
    }

    public void setRendererConcedente(boolean rendererConcedente) {
        this.rendererConcedente = rendererConcedente;
    }

    public boolean isRendererInstituicao() {
        return rendererInstituicao;
    }

    public void setRendererInstituicao(boolean rendererInstituicao) {
        this.rendererInstituicao = rendererInstituicao;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }

    public Concedente getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Concedente instituicao) {
        this.instituicao = instituicao;
    }

    public String getCnpjInstituicao() {
        return cnpjInstituicao;
    }

    public ArrayList<Concedente> getConcedentes() {
        return concedentes;
    }

    public void setConcedentes(ArrayList<Concedente> concedentes) {
        this.concedentes = concedentes;
    }

    public String getCnpjConcedente() {
        return cnpjConcedente;
    }

    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(ArrayList<Aluno> alunos) {
        this.alunos = alunos;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Concedente getConcedente() {
        return concedente;
    }

    public void setConcedente(Concedente concedente) {
        this.concedente = concedente;
    }

    public String getCpf() {
        return cpf;
    }

    public String getIdVaga() {
        return idVaga;
    }
}
