/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iEstagios.dao;

import iEstagios.conexao.Conexao;
import iEstagios.modelo.Documento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gerardo
 */
public class DocumentoDAO {

    public static List<Documento> listarDocumentosEstagio(int idEstagio) {
        try {
            Connection conexao = Conexao.abrirConexao();
            String pesquisarDocumentos = "SELECT * FROM documentos WHERE idEstagio =" + idEstagio;
            PreparedStatement statement = conexao.prepareStatement(pesquisarDocumentos);
            ResultSet rs = statement.executeQuery();
            List<Documento> documentos = new ArrayList<>();
            while (rs.next()) {
                Documento documento = new Documento();
                documento.setId(rs.getInt(1));
                documento.setDescricao(rs.getString(2));
                documento.setUrl(rs.getString(3));
                documento.setDataCriacao(rs.getString(4));
                documentos.add(documento);
            }
            return documentos;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void cadastrarDocumento(Documento documento) {
        try {
            Connection conexao = Conexao.abrirConexao();
            String cadastrarDocumento = "INSERT INTO documentos(descricao,url,dataCriacao,idEstagio) VALUES(?,?,?,?)";
            PreparedStatement statement = conexao.prepareStatement(cadastrarDocumento);
            statement.setString(1, documento.getDescricao());
            statement.setString(2, documento.getUrl());
            SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy");
            String result = out.format((new Date()));
            statement.setString(3, result);
            statement.setInt(4, documento.getEstagio().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
