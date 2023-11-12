package dao;

import conexao.Conexao;
import conexao.ConexaoServer;
import modelo.Monitoramento;
import modelo.Usuario;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;

public class UsuarioDao {
    public Integer contarPeloEmailESenha(String email, String senha) {
        Conexao conexao = new Conexao();
        ConexaoServer conexaoServer = new ConexaoServer();
        JdbcTemplate con = conexao.getConexaoDoBanco();
        JdbcTemplate conServer = conexaoServer.getConexaoDoBanco();

        String sql = "SELECT COUNT(*) FROM Usuario WHERE email = ? AND senha = ?";

        try {
            // Contando no banco local
            Integer countLocal = con.queryForObject(sql, Integer.class, email, senha);

            // Contando no banco do servidor
            Integer countServer = conServer.queryForObject(sql, Integer.class, email, senha);

            // Somando os resultados
            return countLocal + countServer;

        } catch (Exception e) {
            // Trate exceções (log, relatório de erro, etc.)
            e.printStackTrace();
            return 0;

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

    public Usuario buscarPeloEmailESenha(String email, String senha) {
        Conexao conexao = new Conexao();
        ConexaoServer conexaoServer = new ConexaoServer();
        JdbcTemplate con = conexao.getConexaoDoBanco();
        JdbcTemplate conServer = conexaoServer.getConexaoDoBanco();

        String sql = "SELECT * FROM Usuario WHERE email = ? AND senha = ?";

        try {
            // Buscando no banco local
            Usuario usuarioLocal = con.queryForObject(sql, new BeanPropertyRowMapper<>(Usuario.class), email, senha);

            // Buscando no banco do servidor
            Usuario usuarioServer = conServer.queryForObject(sql, new BeanPropertyRowMapper<>(Usuario.class), email, senha);

            // Escolha qual usuário retornar (pode ser lógica de negócios específica)
            return (usuarioLocal != null) ? usuarioLocal : usuarioServer;

        } catch (Exception e) {
            // Trate exceções (log, relatório de erro, etc.)
            e.printStackTrace();
            return null;

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
