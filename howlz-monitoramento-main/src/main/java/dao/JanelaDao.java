package dao;

import conexao.Conexao;
import conexao.ConexaoServer;
import modelo.Janela;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;

public class JanelaDao {
    public void salvar(Janela janela) {
        Conexao conexao = new Conexao();
        ConexaoServer conexaoServer = new ConexaoServer();
        JdbcTemplate con = conexao.getConexaoDoBanco();
        JdbcTemplate conServer = conexaoServer.getConexaoDoBanco();

        String sql = "INSERT INTO Janela (pid, idLocalJanela, comando, titulo, posicao, visibilidade, fkComputador, fkProcesso, dataHora) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            // Salvando no banco local
            con.update(sql, janela.getPid(), janela.getIdLocalJanela(), janela.getComando(), janela.getTitulo(), janela.getPosicao(), janela.getVisibilidade(), janela.getFkComputador(), janela.getFkProcesso(), janela.getDataHora());

            // Salvando no banco do servidor
            conServer.update(sql, janela.getPid(), janela.getIdLocalJanela(), janela.getComando(), janela.getTitulo(), janela.getPosicao(), janela.getVisibilidade(), janela.getFkComputador(), janela.getFkProcesso(), janela.getDataHora());

        } catch (Exception e) {
            // Trate exceções (log, relatório de erro, etc.)
            e.printStackTrace();

        } finally {
            // Certifique-se de fechar as conexões, mesmo se ocorrer uma exceção
            if (con != null) {
                try {
                    con.getDataSource().getConnection().close();
                } catch (SQLException e) {
                    e.printStackTrace(); // Trate a exceção de fechamento da conexão local
                }
            }

            if (conServer != null) {
                try {
                    conServer.getDataSource().getConnection().close();
                } catch (SQLException e) {
                    e.printStackTrace(); // Trate a exceção de fechamento da conexão do servidor
                }
            }
        }
    }

}
