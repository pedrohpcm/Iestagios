package iEstagios.bean;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
@ViewScoped
public class mBeanPDF implements Serializable {

    private Document doc = null;
    private OutputStream os = null;
    private StreamedContent file;

    @PostConstruct
    public void init() {
        InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/pdf/documento.pdf");
        file = new DefaultStreamedContent(stream, "document/pdf", "pdf.pdf");
    }

    public StreamedContent getFile() {
        System.out.println("FILE - " + file.getName());
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }

    public void gerarPDFTermoCompromissoEstagio() {
        try {
            //ABRINDO DOCUMENTO E DEFININDO O ESTILO E AS MARGENS
            doc = new Document(PageSize.A4, 30, 20, 72, 72);
            //PEGANDO CAMINHO DO DOCUMENTO PDF JÁ CRIADO
            String caminho = FacesContext.getCurrentInstance().getExternalContext().getRealPath("") + "/resources/pdf/documento.pdf";
            //PEGANDO CAMINHO DA IMAGEM QUE SERÁ ADICIONADA AO PDF
            String caminho2 = FacesContext.getCurrentInstance().getExternalContext().getRealPath("") + "/resources/img/if.png";
            os = new FileOutputStream(caminho);
            PdfWriter.getInstance(doc, os);
            doc.open();

            /* 
             //ESCREVENDO NO PDF
             //ADICIONANDO LOGO DO IF
             Image img = Image.getInstance(caminho2);
             img.setAbsolutePosition(10f, 700f);            
             img.setAlignment(Element.ALIGN_LEFT);
             doc.add(img);

             //TEXTO
             //CRIANDO UM PARÁGRAFO. 'TIMES_BOLD' É REFERENTE AO TIPO DA FONTE E '22' É O TAMANHO DA FONTE
             Paragraph p = new Paragraph("Termo de compromisso do estágio",FontFactory.getFont(FontFactory.TIMES_BOLD, 22));
             //DEFININDO ALINHAMENTO DO PARÁGRAFO
             p.setAlignment(Element.ALIGN_CENTER);
             //ADICIONANDO PARÁGRAFO AO DOCUMENTO
             doc.add(p);
           
             //CRIANDO FRASE
             String nome = "José Gerardo";
             String endereco = "Endereco";
             Phrase phrase1 = new Phrase(nome,FontFactory.getFont(FontFactory.TIMES_BOLD, 10));
             p = new Paragraph("\n\n\nDADOS DA INSTITUIÇÃO DE ENSINO",FontFactory.getFont(FontFactory.TIMES_BOLD, 12));
             p.setAlignment(Element.ALIGN_LEFT);
             doc.add(p);
            
             p = new Paragraph(
             "\nNome: "+nome
             + "\nEndereço: "+endereco
             + "\nBairro: "
             + "\tCidade: "
             + "\tEstado: "
                   
             + "\tCep: "
             + "\nCNPJ: "
             + "\tTelefone: "
             + "\nRepresentante: "
             + "\tCargo: "
             + "\nProfessor orientador: "
             + "\tTelefone: "
             + "\tEmail: "
                   
             ,FontFactory.getFont(FontFactory.TIMES_ROMAN, 10));
             doc.add(p);
             */
            Paragraph p = new Paragraph("Termo de compromisso do estágio", FontFactory.getFont(FontFactory.TIMES_BOLD, 22));
            Phrase phrase1 = new Phrase("Gerardo", FontFactory.getFont(FontFactory.TIMES_BOLD, 10));
            p = new Paragraph(
                    "TERMO DE COMPROMISSO E PLANO DE ATIVIDADES DE ESTÁGIO SEM VINCULAÇÃO EMPREGATÍCIA NOS TERMOS DA LEI Nº 11.788 DE 25 DE SETEMBRO DE 2008.\n"
                    + "\n"
                    + "INSTITUIÇÃO DE ENSINO\n"
                    + "NOME:\n"
                    + "ENDEREÇO:      \n"
                    + "BAIRRO:      CIDADE:      ESTADO:      CEP:      \n"
                    + "CNPJ:      TELEFONE:      \n"
                    + "REPRESENTANTE:      CARGO:      \n"
                    + "PROFº ORIENTADOR:      FONE:      E-MAIL PROFº ORIENTADOR:      \n"
                    + "\n"
                    + "CONCEDENTE (ÓRGÃO OU EMPRESA)\n"
                    + "RAZÃO SOCIAL:      \n"
                    + "NOME FANTASIA:      CNPJ:                                   \n"
                    + "ENDEREÇO:      \n"
                    + "BAIRRO:      CIDADE:      ESTADO:      CEP:      \n"
                    + "TELEFONE:      \n"
                    + "REPRESENTANTE:      CARGO:      \n"
                    + "SUPERVISOR:           CARGO:            E-MAIL DO SUPERVISOR:      \n"
                    + "RAMO DE ATIVIDADE:      \n"
                    + "\n"
                    + "ESTAGIÁRIO\n"
                    + "NOME:           \n"
                    + "CPF:      RG:      ÓRGÃO EXPEDIDOR:      \n"
                    + "ENDEREÇO:      \n"
                    + "BAIRRO:      \n"
                    + "CIDADE:               ESTADO:      CEP:      \n"
                    + "DATA NASCIMENTO:      TELEFONE:      EMAIL:      \n"
                    + "CURSO:      PERÍODO/ANO:        NÍVEL:           \n"
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
            Logger.getLogger(mBeanPDF.class.getName()).log(Level.SEVERE, null, ex);
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

    public void gerarPDFApolice() {
        try {
            //ABRINDO DOCUMENTO E DEFININDO O ESTILO E AS MARGENS
            doc = new Document(PageSize.A4, 30, 20, 72, 72);
            //PEGANDO CAMINHO DO DOCUMENTO PDF JÁ CRIADO
            String caminho = FacesContext.getCurrentInstance().getExternalContext().getRealPath("") + "/resources/pdf/documento.pdf";
            //PEGANDO CAMINHO DA IMAGEM QUE SERÁ ADICIONADA AO PDF
            String caminho2 = FacesContext.getCurrentInstance().getExternalContext().getRealPath("") + "/resources/img/if.png";
            os = new FileOutputStream(caminho);
            PdfWriter.getInstance(doc, os);
            doc.open();

            /* 
             //ESCREVENDO NO PDF
             //ADICIONANDO LOGO DO IF
             Image img = Image.getInstance(caminho2);
             img.setAbsolutePosition(10f, 700f);            
             img.setAlignment(Element.ALIGN_LEFT);
             doc.add(img);

             //TEXTO
             //CRIANDO UM PARÁGRAFO. 'TIMES_BOLD' É REFERENTE AO TIPO DA FONTE E '22' É O TAMANHO DA FONTE
             Paragraph p = new Paragraph("Termo de compromisso do estágio",FontFactory.getFont(FontFactory.TIMES_BOLD, 22));
             //DEFININDO ALINHAMENTO DO PARÁGRAFO
             p.setAlignment(Element.ALIGN_CENTER);
             //ADICIONANDO PARÁGRAFO AO DOCUMENTO
             doc.add(p);
           
             //CRIANDO FRASE
             String nome = "José Gerardo";
             String endereco = "Endereco";
             Phrase phrase1 = new Phrase(nome,FontFactory.getFont(FontFactory.TIMES_BOLD, 10));
             p = new Paragraph("\n\n\nDADOS DA INSTITUIÇÃO DE ENSINO",FontFactory.getFont(FontFactory.TIMES_BOLD, 12));
             p.setAlignment(Element.ALIGN_LEFT);
             doc.add(p);
            
             p = new Paragraph(
             "\nNome: "+nome
             + "\nEndereço: "+endereco
             + "\nBairro: "
             + "\tCidade: "
             + "\tEstado: "
                   
             + "\tCep: "
             + "\nCNPJ: "
             + "\tTelefone: "
             + "\nRepresentante: "
             + "\tCargo: "
             + "\nProfessor orientador: "
             + "\tTelefone: "
             + "\tEmail: "
                   
             ,FontFactory.getFont(FontFactory.TIMES_ROMAN, 10));
             doc.add(p);
             */
            Paragraph p = new Paragraph("Termo de compromisso do estágio", FontFactory.getFont(FontFactory.TIMES_BOLD, 22));

            p = new Paragraph(
                    "APOLICE DO SEGURO\n", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10));
            p.setAlignment(Element.ALIGN_JUSTIFIED);
            doc.add(p);

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(mBeanPDF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(mBeanPDF.class.getName()).log(Level.SEVERE, null, ex);
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

    public void gerarPDFRelatorioOrientador() {
        try {
            //ABRINDO DOCUMENTO E DEFININDO O ESTILO E AS MARGENS
            doc = new Document(PageSize.A4, 30, 20, 72, 72);
            //PEGANDO CAMINHO DO DOCUMENTO PDF JÁ CRIADO
            String caminho = FacesContext.getCurrentInstance().getExternalContext().getRealPath("") + "/resources/pdf/documento.pdf";
            //PEGANDO CAMINHO DA IMAGEM QUE SERÁ ADICIONADA AO PDF
            String caminho2 = FacesContext.getCurrentInstance().getExternalContext().getRealPath("") + "/resources/img/if.png";
            os = new FileOutputStream(caminho);
            PdfWriter.getInstance(doc, os);
            doc.open();

            /* 
             //ESCREVENDO NO PDF
             //ADICIONANDO LOGO DO IF
             Image img = Image.getInstance(caminho2);
             img.setAbsolutePosition(10f, 700f);            
             img.setAlignment(Element.ALIGN_LEFT);
             doc.add(img);

             //TEXTO
             //CRIANDO UM PARÁGRAFO. 'TIMES_BOLD' É REFERENTE AO TIPO DA FONTE E '22' É O TAMANHO DA FONTE
             Paragraph p = new Paragraph("Termo de compromisso do estágio",FontFactory.getFont(FontFactory.TIMES_BOLD, 22));
             //DEFININDO ALINHAMENTO DO PARÁGRAFO
             p.setAlignment(Element.ALIGN_CENTER);
             //ADICIONANDO PARÁGRAFO AO DOCUMENTO
             doc.add(p);
           
             //CRIANDO FRASE
             String nome = "José Gerardo";
             String endereco = "Endereco";
             Phrase phrase1 = new Phrase(nome,FontFactory.getFont(FontFactory.TIMES_BOLD, 10));
             p = new Paragraph("\n\n\nDADOS DA INSTITUIÇÃO DE ENSINO",FontFactory.getFont(FontFactory.TIMES_BOLD, 12));
             p.setAlignment(Element.ALIGN_LEFT);
             doc.add(p);
            
             p = new Paragraph(
             "\nNome: "+nome
             + "\nEndereço: "+endereco
             + "\nBairro: "
             + "\tCidade: "
             + "\tEstado: "
                   
             + "\tCep: "
             + "\nCNPJ: "
             + "\tTelefone: "
             + "\nRepresentante: "
             + "\tCargo: "
             + "\nProfessor orientador: "
             + "\tTelefone: "
             + "\tEmail: "
                   
             ,FontFactory.getFont(FontFactory.TIMES_ROMAN, 10));
             doc.add(p);
             */
            Paragraph p = new Paragraph("Termo de compromisso do estágio", FontFactory.getFont(FontFactory.TIMES_BOLD, 22));

            p = new Paragraph(
                    "RELATÓRIO ORIENTADOR", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10));
            p.setAlignment(Element.ALIGN_JUSTIFIED);
            doc.add(p);

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(mBeanPDF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(mBeanPDF.class.getName()).log(Level.SEVERE, null, ex);
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

    public void gerarPDFRelatorioEstagiario() {
        try {
            //ABRINDO DOCUMENTO E DEFININDO O ESTILO E AS MARGENS
            doc = new Document(PageSize.A4, 30, 20, 72, 72);
            //PEGANDO CAMINHO DO DOCUMENTO PDF JÁ CRIADO
            String caminho = FacesContext.getCurrentInstance().getExternalContext().getRealPath("") + "/resources/pdf/documento.pdf";
            //PEGANDO CAMINHO DA IMAGEM QUE SERÁ ADICIONADA AO PDF
            String caminho2 = FacesContext.getCurrentInstance().getExternalContext().getRealPath("") + "/resources/img/if.png";
            os = new FileOutputStream(caminho);
            PdfWriter.getInstance(doc, os);
            doc.open();

            /* 
             //ESCREVENDO NO PDF
             //ADICIONANDO LOGO DO IF
             Image img = Image.getInstance(caminho2);
             img.setAbsolutePosition(10f, 700f);            
             img.setAlignment(Element.ALIGN_LEFT);
             doc.add(img);

             //TEXTO
             //CRIANDO UM PARÁGRAFO. 'TIMES_BOLD' É REFERENTE AO TIPO DA FONTE E '22' É O TAMANHO DA FONTE
             Paragraph p = new Paragraph("Termo de compromisso do estágio",FontFactory.getFont(FontFactory.TIMES_BOLD, 22));
             //DEFININDO ALINHAMENTO DO PARÁGRAFO
             p.setAlignment(Element.ALIGN_CENTER);
             //ADICIONANDO PARÁGRAFO AO DOCUMENTO
             doc.add(p);
           
             //CRIANDO FRASE
             String nome = "José Gerardo";
             String endereco = "Endereco";
             Phrase phrase1 = new Phrase(nome,FontFactory.getFont(FontFactory.TIMES_BOLD, 10));
             p = new Paragraph("\n\n\nDADOS DA INSTITUIÇÃO DE ENSINO",FontFactory.getFont(FontFactory.TIMES_BOLD, 12));
             p.setAlignment(Element.ALIGN_LEFT);
             doc.add(p);
            
             p = new Paragraph(
             "\nNome: "+nome
             + "\nEndereço: "+endereco
             + "\nBairro: "
             + "\tCidade: "
             + "\tEstado: "
                   
             + "\tCep: "
             + "\nCNPJ: "
             + "\tTelefone: "
             + "\nRepresentante: "
             + "\tCargo: "
             + "\nProfessor orientador: "
             + "\tTelefone: "
             + "\tEmail: "
                   
             ,FontFactory.getFont(FontFactory.TIMES_ROMAN, 10));
             doc.add(p);
             */
            Paragraph p = new Paragraph("Termo de compromisso do estágio", FontFactory.getFont(FontFactory.TIMES_BOLD, 22));

            p = new Paragraph(
                    "RELATÓRIO ESTAGIÁRIO", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10));
            p.setAlignment(Element.ALIGN_JUSTIFIED);
            doc.add(p);

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(mBeanPDF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(mBeanPDF.class.getName()).log(Level.SEVERE, null, ex);
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

    public void gerarPDFRelatorioSupervisor() {
        try {
            //ABRINDO DOCUMENTO E DEFININDO O ESTILO E AS MARGENS
            doc = new Document(PageSize.A4, 30, 20, 72, 72);
            //PEGANDO CAMINHO DO DOCUMENTO PDF JÁ CRIADO
            String caminho = FacesContext.getCurrentInstance().getExternalContext().getRealPath("") + "/resources/pdf/documento.pdf";
            //PEGANDO CAMINHO DA IMAGEM QUE SERÁ ADICIONADA AO PDF
            String caminho2 = FacesContext.getCurrentInstance().getExternalContext().getRealPath("") + "/resources/img/if.png";
            os = new FileOutputStream(caminho);
            PdfWriter.getInstance(doc, os);
            doc.open();

            /* 
             //ESCREVENDO NO PDF
             //ADICIONANDO LOGO DO IF
             Image img = Image.getInstance(caminho2);
             img.setAbsolutePosition(10f, 700f);            
             img.setAlignment(Element.ALIGN_LEFT);
             doc.add(img);

             //TEXTO
             //CRIANDO UM PARÁGRAFO. 'TIMES_BOLD' É REFERENTE AO TIPO DA FONTE E '22' É O TAMANHO DA FONTE
             Paragraph p = new Paragraph("Termo de compromisso do estágio",FontFactory.getFont(FontFactory.TIMES_BOLD, 22));
             //DEFININDO ALINHAMENTO DO PARÁGRAFO
             p.setAlignment(Element.ALIGN_CENTER);
             //ADICIONANDO PARÁGRAFO AO DOCUMENTO
             doc.add(p);
           
             //CRIANDO FRASE
             String nome = "José Gerardo";
             String endereco = "Endereco";
             Phrase phrase1 = new Phrase(nome,FontFactory.getFont(FontFactory.TIMES_BOLD, 10));
             p = new Paragraph("\n\n\nDADOS DA INSTITUIÇÃO DE ENSINO",FontFactory.getFont(FontFactory.TIMES_BOLD, 12));
             p.setAlignment(Element.ALIGN_LEFT);
             doc.add(p);
            
             p = new Paragraph(
             "\nNome: "+nome
             + "\nEndereço: "+endereco
             + "\nBairro: "
             + "\tCidade: "
             + "\tEstado: "
                   
             + "\tCep: "
             + "\nCNPJ: "
             + "\tTelefone: "
             + "\nRepresentante: "
             + "\tCargo: "
             + "\nProfessor orientador: "
             + "\tTelefone: "
             + "\tEmail: "
                   
             ,FontFactory.getFont(FontFactory.TIMES_ROMAN, 10));
             doc.add(p);
             */
            Paragraph p = new Paragraph("Termo de compromisso do estágio", FontFactory.getFont(FontFactory.TIMES_BOLD, 22));

            p = new Paragraph(
                    "O presente relatório, entregue em periodicidade não superior a 6 (seis) meses,"
                    + " tem por objetivo avaliar o desenvolvimento do Plano de Atividades que integra "
                    + "o Termo de Compromisso de Estágio. A entrega deste relatório ao setor responsável "
                    + "por estágios do Campus do IFRN em que o estagiário estuda, devidamente preenchido"
                    + " e assinado pelo Estagiário, pelo Supervisor do Estágio e pelo Professor Orientador, "
                    + "cumpre as exigências do inciso VII do art. 9º da Lei nº 11.788/2008. \n"
                    + "\n"
                    + "Período deste relatório: _____/_____/_____ à _____/_____/_____ ( ___ meses)\n"
                    + "\nESTAGIÁRIO\n"
                    + "Nome do Estagiário:\n"
                    + "Matrícula:\n"
                    + "Curso:\n"
                    + "Telefone/Celular:\n"
                    + "E-mail:\n"
                    + "\n"
                    + "CONCEDENTE DE ESTÁGIO\n"
                    + "Razão Social ou Profissional Liberal:\n"
                    + "Nome do Supervisor do Estágio:\n"
                    + "Telefone/Celular do Supervisor:\n"
                    + "Cargo do supervisor:\n"
                    + "E-mail do Supervisor:\n"
                    + "\n"
                    + "1.	Plano de Atividades: \n"
                    + "Atividades Previstas no Plano realizadas: \n\n"
                    + "Atividades não realizadas (justifique o motivo):\n"
                    + "Identifique os motivos conforme legenda: (1) tempo insuficiente; "
                    + "(2) conhecimento insuficiente; (3) Mudança no Plano de Atividades; "
                    + "(4) Substituição por outra atividade; (5) outro motivo. Qual?\n"
                    + "\n"
                    + "1.1	- Comentários sobre o desenvolvimento das atividades:\n"
                    + "1.2	- Realizou atividades não previstas no Plano de Atividades?  (    ) Sim   (    ) Não\n"
                    + "Outras Atividades não previstas no plano: \n"
                    + "1.3	- Em caso afirmativo, justifique: \n"
                    + "1.4	– Avaliando o desempenho do estagiário como um todo, dê uma nota de 0 a 10: ______.\n"
                    + "\n"
                    + "Observação: O desenvolvimento de uma atividade de estágio implica em um "
                    + "constante processo de aprendizagem e desenvolvimento de competências. "
                    + "O plano de atividades do estagiário, à medida que for avaliado, progressivamente,"
                    + " o desempenho do estudante deve ser atualizado e incorporado ao termo de "
                    + "compromisso de estágio por meio de aditivos. "
                    + "(Parágrafo único, art. 7º, Lei nº 11.788/08).\n"
                    + "\n"
                    + "                                                         (  ),   de       de 20  .\n"
                    + "\n"
                    + "\n"
                    + "                                                         Supervisor do Estágio                                    Estagiário\n", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10));
            p.setAlignment(Element.ALIGN_JUSTIFIED);
            doc.add(p);

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(mBeanPDF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(mBeanPDF.class.getName()).log(Level.SEVERE, null, ex);
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

    public void gerarPDFTermoAditivo() {
        try {
            //ABRINDO DOCUMENTO E DEFININDO O ESTILO E AS MARGENS
            doc = new Document(PageSize.A4, 50, 50, 60, 60);
            //PEGANDO CAMINHO DO DOCUMENTO PDF JÁ CRIADO
            String caminho = FacesContext.getCurrentInstance().getExternalContext().getRealPath("") + "/resources/pdf/documento.pdf";
            //PEGANDO CAMINHO DA IMAGEM QUE SERÁ ADICIONADA AO PDF
            String caminho2 = FacesContext.getCurrentInstance().getExternalContext().getRealPath("") + "/resources/img/if.png";
            os = new FileOutputStream(caminho);
            PdfWriter.getInstance(doc, os);
            doc.open();

            /* 
             //ESCREVENDO NO PDF
             //ADICIONANDO LOGO DO IF
             Image img = Image.getInstance(caminho2);
             img.setAbsolutePosition(10f, 700f);            
             img.setAlignment(Element.ALIGN_LEFT);
             doc.add(img);

             //TEXTO
             //CRIANDO UM PARÁGRAFO. 'TIMES_BOLD' É REFERENTE AO TIPO DA FONTE E '22' É O TAMANHO DA FONTE
             Paragraph p = new Paragraph("Termo de compromisso do estágio",FontFactory.getFont(FontFactory.TIMES_BOLD, 22));
             //DEFININDO ALINHAMENTO DO PARÁGRAFO
             p.setAlignment(Element.ALIGN_CENTER);
             //ADICIONANDO PARÁGRAFO AO DOCUMENTO
             doc.add(p);
           
             //CRIANDO FRASE
             String nome = "José Gerardo";
             String endereco = "Endereco";
             Phrase phrase1 = new Phrase(nome,FontFactory.getFont(FontFactory.TIMES_BOLD, 10));
             p = new Paragraph("\n\n\nDADOS DA INSTITUIÇÃO DE ENSINO",FontFactory.getFont(FontFactory.TIMES_BOLD, 12));
             p.setAlignment(Element.ALIGN_LEFT);
             doc.add(p);
            
             p = new Paragraph(
             "\nNome: "+nome
             + "\nEndereço: "+endereco
             + "\nBairro: "
             + "\tCidade: "
             + "\tEstado: "
                   
             + "\tCep: "
             + "\nCNPJ: "
             + "\tTelefone: "
             + "\nRepresentante: "
             + "\tCargo: "
             + "\nProfessor orientador: "
             + "\tTelefone: "
             + "\tEmail: "
                   
             ,FontFactory.getFont(FontFactory.TIMES_ROMAN, 10));
             doc.add(p);
             */
            Paragraph p = new Paragraph("Termo de compromisso do estágio - Aditivo", FontFactory.getFont(FontFactory.TIMES_BOLD, 22));

            p = new Paragraph(
                    "TERMO ADITIVO\n", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD));
            p.setAlignment(Element.ALIGN_CENTER);
            doc.add(p);

            p = new Paragraph(
                    "\nAditamos ao TERMO DE COMPROMISSO DE ESTÁGIO, firmado entre a CONCEDENTE:"
                    + " e o(a) ESTAGIÁRIO(A):"
                    + ", aluno(a) regularmente matriculado(a) no(a): "
                    + ", no curso: "
                    + ", nivel: "
                    + ", já qualificados(as) no Termo de Compromisso de Estágio – TCE.\n"
                    + "\n"
                    + "Cláusula Primeira - De acordo com Termo de Compromisso de Estágio, o(a) ESTAGIÁRIO(a) acima citado(a):\n"
                    + "1.	Está recebendo da referida CONCEDENTE, a título de bolsa, a importância de R$ 00,00 (valor por extenso), bem como auxílio transporte no valor de R$ 00,00 (valor por extenso).\n"
                    + "2.	Está cumprindo Estágio no período de ___/___/___ a ___/___/___ na referida CONCEDENTE, das ___horas às ___horas.\n"
                    + "3.	Está sendo orientado pelo (a) professor(a) (nome do orientador), (cargo do orientador), informações de contato: (telefone do orientador) e (email do orientador);\n"
                    + "4.	Está sob supervisão na CONCEDENTE por (nome do supervisor), (cargo do supervisor), informações de contato: (telefone do supervisor) e (email do supervisor) \n"
                    + "5.	E está executando as seguintes atividades: (descrever atividades listadas no plano de atividades)\n"
                    + "Cláusula Segunda - Este Termo Aditivo atualiza, a partir desta data, apenas o(s) item(ns) (enumerar os itens que sofrerão alteração, Ex: 1, 4 e 5, informando os novos dados, excluir itens que não serão alterados) estabelecido(s) no referido TCE/Plano de atividades, celebrado com a interveniência e assinatura da INSTITUIÇÃO DE ENSINO e da CONCEDENTE, passando a vigorar nos seguintes termos: \n"
                    + "1.	Receberá da referida CONCEDENTE, a título de bolsa, a importância de R$ 00,00 (valor por extenso), bem como auxílio transporte no valor de R$ 00,00 (valor por extenso).\n"
                    + "2.	Cumprirá o Estágio no período de ___/___/___ a ___/___/___ na referida CONCEDENTE, das ___horas às ___horas.\n"
                    + "3.	Será orientado pelo (a) professor(a) (nome do orientador), (cargo do orientador), informações de contato: (telefone do orientador) e (email do orientador);\n"
                    + "4.	Estará sob supervisão na CONCEDENTE por (nome do supervisor), (cargo do supervisor), informações de contato: (telefone do supervisor) e (email do supervisor) \n"
                    + "5.	Executará as seguintes atividades: \n"
                    + "Cláusula Terceira - Permanecem inalteradas todas as demais disposições do TCE, do qual este Termo Aditivo passa a ser parte integrante.\n"
                    + "E por estarem de inteiro e comum acordo, as partes assinam o presente instrumento, em três vias de igual teor, forma e validade.\n"
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
            Logger.getLogger(mBeanPDF.class.getName()).log(Level.SEVERE, null, ex);
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

    public void gerarPDFTermoRealizacao() {
        try {
            //ABRINDO DOCUMENTO E DEFININDO O ESTILO E AS MARGENS
            doc = new Document(PageSize.A4, 50, 50, 60, 60);
            //PEGANDO CAMINHO DO DOCUMENTO PDF JÁ CRIADO
            String caminho = FacesContext.getCurrentInstance().getExternalContext().getRealPath("") + "/resources/pdf/documento.pdf";
            //PEGANDO CAMINHO DA IMAGEM QUE SERÁ ADICIONADA AO PDF
            String caminho2 = FacesContext.getCurrentInstance().getExternalContext().getRealPath("") + "/resources/img/if.png";
            os = new FileOutputStream(caminho);
            PdfWriter.getInstance(doc, os);
            doc.open();

            Paragraph p = new Paragraph("Termo de compromisso do estágio", FontFactory.getFont(FontFactory.TIMES_BOLD, 22));
            p = new Paragraph(
                    "TERMO DE REALIZAÇÃO DE ESTÁGIO\n"
                    + "Lei nº 11.788, de 25 de setembro de 2008\n\n", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD));
            p.setAlignment(Element.ALIGN_CENTER);
            doc.add(p);

            p = new Paragraph(
                    "\nTermo deve ser emitido em 2 duas vias, uma para o estagiário e uma para a Instituição de Ensino. "
                    + "\nDocumento cumpre a exigência do inciso V do art. 9º da Lei 1.788/2008.\n"
                    + "\nNome da Concedente:      \n"
                    + "CNPJ:   .   .   /    -  \n"
                    + "Endereço:      \n"
                    + "Telefone: (    )     -    \n"
                    + "Supervisor de Estágio:      \n"
                    + "\n"
                    + "Nome do Estagiário:      \n"
                    + "Curso:      \n"
                    + "Matrícula:      \n"
                    + "\n"
                    + "Tarefas realizadas pelo estagiário:\n" + ""
                    + "\n"
                    + "Avaliação de desempenho do estagiário:\n"
                    + "(    ) Excelente \t"
                    + "(    ) Muito bom\t"
                    + "(    ) Bom\t"
                    + "(    ) Regular\t"
                    + "(    ) Insuficiente\t"
                    + "\nComentários:      \n"
                    + "\n"
                    + "Motivo do desligamento/encerramento:\n"
                    + "(   ) Por término do período previsto no Termo de Compromisso\n"
                    + "(   ) Contratação do estagiário pela concedente           \n"
                    + "(   ) Rescisão por iniciativa da concedente                 \n"
                    + "(   ) Rescisão por iniciativa do estagiário     \n"
                    + "(   ) Rescisão por iniciativa da instituição de ensino\n"
                    + "\nPeríodo de estágio:   /  /       a     /  /        Carga Horária Semanal:       horas\n"
                    + "Carga Horária Total do Estágio:       horas\n"
                    + "\n"
                    + "Declaração do Supervisor\n"
                    + "Declaro, para os fins que se fizerem necessários junto a(o) Instituto Federal de Educação , que o aluno acima indicado realizou seu estágio sob minha responsabilidade e que o desligamento está sendo realizado em conformidade com a lei 11.788/2008, inexistindo créditos e direitos a serem recebidos pelo estagiário.\n"
                    + "\nSUPERVISOR DE ESTÁGIO"
                    + "\n(Assinatura)\n", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10));
            p.setAlignment(Element.ALIGN_JUSTIFIED);
            doc.add(p);

            p = new Paragraph(
                    "\n(Município) (RN),    de       de 20  \n", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10));
            p.setAlignment(Element.ALIGN_CENTER);
            doc.add(p);

            p = new Paragraph(
                    "\n\n                                                 ESTAGIÁRIO (A)"
                    + "                                                   CONCEDENTE"
                    + "\n                                                      Assinatura"
                    + "                                                               Assinatura", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10));
            p.setAlignment(Element.ALIGN_JUSTIFIED);
            doc.add(p);

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(mBeanPDF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(mBeanPDF.class.getName()).log(Level.SEVERE, null, ex);
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

}
