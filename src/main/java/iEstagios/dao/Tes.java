/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iEstagios.dao;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Gerardo
 */
public class Tes {

    public static void main(String[] args) {
        File arquivo = new File("arquivo.extensao");
        String caminho = arquivo.getAbsolutePath();
        String[] parts = caminho.split("arquivo.");
        String nome = "docteste";
        caminho = parts[0] + "src/main/webapp/resources/pdf/"+nome+".pdf";
        //arquivo = new File(caminho + "docteste");

        //System.out.println(new File(this.getClass().getResource("./src").toURI().getPath()));
        // criação do objeto documento
        Document document = new Document();
        Document doc = null;
        FileOutputStream os = null;
        try {
           
            // Criando objeto para escrever em pdf
            //PdfWriter.getInstance(document, new FileOutputStream("C://Teste_PDF.pdf"));

            doc = new Document(PageSize.A4, 30, 20, 72, 72);
            os = new FileOutputStream(caminho);
            PdfWriter.getInstance(doc, os);
            doc.open();
// adicionando um parágrafo ao documento
            //doc.add(new Paragraph("Exemplo Geração de Arquivo PDF via iText -   Java"));
            Paragraph p = new Paragraph("Termo de compromisso do estágio", FontFactory.getFont(FontFactory.TIMES_BOLD, 22));
            p = new Paragraph(
                    "TERMO DE COMPROMISSO E PLANO DE ATIVIDADES DE ESTÁGIO SEM VINCULAÇÃO EMPREGATÍCIA NOS TERMOS DA LEI Nº 11.788 DE 25 DE SETEMBRO DE 2008.\n"
                    + "\n"
                    + "INSTITUIÇÃO DE ENSINO\n"
                    + "NOME: " + "\n"
                    + "ENDEREÇO: " + "," + "\n"
                    + "BAIRRO: "
                    + "CIDADE: "
                    + " ESTADO: "
                    + "  CEP: " + "\n"
                    + "CNPJ: "
                    + "TELEFONE: "
                    + "REPRESENTANTE: "
                    + "    CARGO: "
                    + "PROFº ORIENTADOR: "
                    + "    FONE:"
                    + "    E-MAIL PROFº ORIENTADOR: "
                    + "\n"
                    + "CONCEDENTE (ÓRGÃO OU EMPRESA)\n"
                    + "RAZÃO SOCIAL: "
                    + "    CNPJ: "
                    + "ENDEREÇO: "
                    + "BAIRRO: "
                    + "   CIDADE:"
                    + "ESTADO: "
                    + "CEP: "
                    + "TELEFONE: "
                    + "REPRESENTANTE: "
                    + "CARGO:  "
                    + "SUPERVISOR: "
                    + "CARGO:  "
                    + "E-MAIL DO SUPERVISOR: "
                    + "RAMO DE ATIVIDADE: "
                    + "\n"
                    + "ESTAGIÁRIO\n"
                    + "NOME: "
                    + "CPF: "
                    + "RG: "
                    + "ÓRGÃO EXPEDIDOR: "
                    + "ENDEREÇO: "
                    + "BAIRRO: "
                    + "CIDADE: "
                    + "ESTADO: "
                    + " TELEFONE: "
                    + "       EMAIL: "
                    + "CURSO: "
                    + "        PERÍODO/ANO: "
                    + "        NÍVEL: "
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
        } catch (DocumentException de) {
            System.err.println(de.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
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

