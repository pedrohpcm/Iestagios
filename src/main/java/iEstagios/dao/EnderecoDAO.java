package iEstagios.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import iEstagios.conexao.Conexao;
import iEstagios.modelo.Endereco;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDAO {
    public static int cadastrarEndereco(Endereco endereco){		
	try {
            Connection conexao = Conexao.abrirConexao();
            String cadastrarEndereco = "INSERT INTO endereco(rua,numero,bairro,cidade,estado,cep) VALUES (?,?,?,?,?,?)";
            PreparedStatement statement = conexao.prepareStatement(cadastrarEndereco, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, endereco.getRua());
            statement.setInt(2, endereco.getNumero());
            statement.setString(3, endereco.getBairro());
            statement.setString(4, endereco.getCidade());			
            statement.setString(5, endereco.getEstado());
            statement.setString(6, endereco.getCep());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);
	} catch (SQLException e) {
            e.printStackTrace();
        }
            return -1;
    }
        
    // MÉTODO LISTAR ENDEREÇO QUE DEVE RETORNAR UMA LISTA DOS ENDEREÇOS
    // CADASTRADOS NO BANCO
    public static List<Endereco> listarEnderecos(){
        try {
            ArrayList<Endereco> enderecos = new ArrayList<>();
            //PREPARANDO CONSULTA
            Connection conexao = Conexao.abrirConexao();
            String listarEnderecos = "SELECT * FROM Endereco";
            PreparedStatement statement = conexao.prepareStatement(listarEnderecos);            
            /*É PRECISO CRIAR UM 'STATEMENT' QUE IRÁ RECEBER O RESULTADO DA CONSULTA
            ELE IRÁ ARMAZENAR TODOS OS DADOS QUE VIEREM DO BANCO DE ACORDO COM A CONSULTA*/
            ResultSet rs = statement.executeQuery();
            //CRIAMOS UM LAÇO PARA PERCORRER O RESULTSET ENQUANTO ELE TIVER UM 'PROXIMO'
            while(rs.next()){
                //PARA CADA RESULTADO, CRIAMOS UM OBJETO DO TIPO ENDERECO
                Endereco endereco = new Endereco();
                /*SETAMOS OS CAMPOS DO OBJETO COM OS DADOS DO RESULTSET.
                PARA PEGAR O DADO DO RESULTSET É PRECISO COLOCAR O NOME DELE,  
                O TIPO DE DADO QUE VC IRÁ PEGAR, E O INDICE DA COLUNA.
                A LINHA ABAIXO PEGA UM INT QUE ESTA NA PRIMEIRA COLUNA*/
                endereco.setId(rs.getInt(1));                
                /*EXECUTAR O MESMO PROCEDIMENTO PARA O RESTO DOS DADOS
                É NECESSÁRIO SEGUIR A ORDEM DAS COLUNAS NO BANCO*/
                endereco.setRua(rs.getString(2));
                endereco.setNumero(rs.getInt(3));
                endereco.setBairro(rs.getString(4));
                endereco.setCidade(rs.getString(5));
                endereco.setEstado(rs.getString(6));
                endereco.setCep(rs.getString(7));
                //POR FIM, ADICIONAMOS O OBJECTO NA LISTA DE OBJETOS
                enderecos.add(endereco);
            }
            //AGORA RETORNAMOS A NOSSA LISTA
            return enderecos;
        } catch (SQLException e) {
            e.printStackTrace();
	} 
        return null;
    }
    
    public static Endereco pesquisarEnderecoPorId(int id){
        try {
            Connection conexao = Conexao.abrirConexao();
            String pesquisarEndereco = "SELECT * FROM Endereco WHERE idEndereco ="+id;
            PreparedStatement statement = conexao.prepareStatement(pesquisarEndereco);          
            ResultSet rs = statement.executeQuery();
            Endereco endereco = new Endereco();
            if(rs.next()){                 
                endereco.setId(rs.getInt(1));
                endereco.setRua(rs.getString(2));
                endereco.setNumero(rs.getInt(3));
                endereco.setBairro(rs.getString(4));
                endereco.setCidade(rs.getString(5));
                endereco.setEstado(rs.getString(6));
                endereco.setCep(rs.getString(7));
            }            
            return endereco;
        } catch (SQLException e) {
            e.printStackTrace();
	} 
        return null;
    }
}
