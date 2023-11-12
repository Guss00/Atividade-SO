package dao;

import conexao.Conexao;
import conexao.ConexaoServer;
import modelo.Computador;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComputadorDao {
    public void salvar(Computador novoComputador) {
        Conexao conexao = new Conexao();
        ConexaoServer conexaoServer = new ConexaoServer();
        JdbcTemplate con = conexao.getConexaoDoBanco();
        JdbcTemplate conServer = conexaoServer.getConexaoDoBanco();

        String sql = "INSERT INTO Computador (nome, sistemaOperacional, numeroSerial, codigo, stts, fkEmpresa) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            // Salvando no banco local
            con.update(sql, novoComputador.getNome(), novoComputador.getSistemaOperacional(), novoComputador.getNumeroSerial(), novoComputador.getCodigo(), novoComputador.getStatus(), novoComputador.getFkEmpresa());

            // Salvando no banco do servidor
            conServer.update(sql, novoComputador.getNome(), novoComputador.getSistemaOperacional(), novoComputador.getNumeroSerial(), novoComputador.getCodigo(), novoComputador.getStatus(), novoComputador.getFkEmpresa());

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

    public List<Computador> buscarTodosPeloIdEmpresa(Integer idEmpresa) {
        Conexao conexao = new Conexao();
        ConexaoServer conexaoServer = new ConexaoServer();
        JdbcTemplate con = conexao.getConexaoDoBanco();
        JdbcTemplate conServer = conexaoServer.getConexaoDoBanco();

        String sql = "SELECT * FROM Computador WHERE fkEmpresa = ?";

        try {
            // Buscando no banco local
            List<Computador> computadoresLocal = con.query(sql, new BeanPropertyRowMapper<>(Computador.class), idEmpresa);

            // Buscando no banco do servidor
            List<Computador> computadoresServer = conServer.query(sql, new BeanPropertyRowMapper<>(Computador.class), idEmpresa);

            List<Computador> todosComputadores = new ArrayList<>();
            todosComputadores.addAll(computadoresLocal);
            todosComputadores.addAll(computadoresServer);

            return todosComputadores;

        } catch (Exception e) {
            // Trate exceções (log, relatório de erro, etc.)
            e.printStackTrace();
            return Collections.emptyList();

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

    public List<Computador> buscarPeloId(Integer idComputador) {
        Conexao conexao = new Conexao();
        ConexaoServer conexaoServer = new ConexaoServer();
        JdbcTemplate con = conexao.getConexaoDoBanco();
        JdbcTemplate conServer = conexaoServer.getConexaoDoBanco();

        String sql = "SELECT * FROM Computador WHERE idComputador = ?";

        try {
            // Buscando no banco local
            List<Computador> computadoresLocal = con.query(sql, new BeanPropertyRowMapper<>(Computador.class), idComputador);

            // Buscando no banco do servidor
            List<Computador> computadoresServer = conServer.query(sql, new BeanPropertyRowMapper<>(Computador.class), idComputador);

            List<Computador> todosComputadores = new ArrayList<>();
            todosComputadores.addAll(computadoresLocal);
            todosComputadores.addAll(computadoresServer);

            return todosComputadores;

        } catch (Exception e) {
            // Trate exceções (log, relatório de erro, etc.)
            e.printStackTrace();
            return Collections.emptyList();

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

    public Computador buscarPeloSerial(String numeroSerial) {
        Conexao conexao = new Conexao();
        ConexaoServer conexaoServer = new ConexaoServer();
        JdbcTemplate con = conexao.getConexaoDoBanco();
        JdbcTemplate conServer = conexaoServer.getConexaoDoBanco();

        String sql = "SELECT * FROM Computador WHERE numeroSerial = ?";

        try {
            // Buscando no banco local
            Computador computadorLocal = con.queryForObject(sql, new BeanPropertyRowMapper<>(Computador.class), numeroSerial);

            // Buscando no banco do servidor
            Computador computadorServer = conServer.queryForObject(sql, new BeanPropertyRowMapper<>(Computador.class), numeroSerial);

            // Escolha qual computador retornar (pode ser lógica de negócios específica)
            return (computadorLocal != null) ? computadorLocal : computadorServer;

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

    public Integer contarPeloSerial(String numeroSerial) {
        Conexao conexao = new Conexao();
        ConexaoServer conexaoServer = new ConexaoServer();
        JdbcTemplate con = conexao.getConexaoDoBanco();
        JdbcTemplate conServer = conexaoServer.getConexaoDoBanco();

        String sql = "SELECT COUNT(*) FROM Computador WHERE numeroSerial = ?";

        try {
            // Contando no banco local
            Integer countLocal = con.queryForObject(sql, Integer.class, numeroSerial);

            // Contando no banco do servidor
            Integer countServer = conServer.queryForObject(sql, Integer.class, numeroSerial);

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


}
