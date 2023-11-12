package dao;

import conexao.Conexao;
import conexao.ConexaoServer;
import modelo.Componente;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComponenteDao {
    public void salvar(Componente novoComponente) {
        Conexao conexao = new Conexao();
        ConexaoServer conexaoServer = new ConexaoServer();
        JdbcTemplate con = conexao.getConexaoDoBanco();
        JdbcTemplate conServer = conexaoServer.getConexaoDoBanco();

        String sql = "INSERT INTO Componente (tipo, modelo, fkComputador, numeroSerial) VALUES (?, ?, ?, ?)";

        try {
            // Salvando no banco local
            con.update(sql, novoComponente.getTipo(), novoComponente.getModelo(), novoComponente.getFkComputador(), novoComponente.getNumeroSerial());

            // Salvando no banco do servidor
            conServer.update(sql, novoComponente.getTipo(), novoComponente.getModelo(), novoComponente.getFkComputador(), novoComponente.getNumeroSerial());

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

    public List<Componente> buscarTodosPeloIdComputador(Integer idComputador) {
        Conexao conexao = new Conexao();
        ConexaoServer conexaoServer = new ConexaoServer();
        JdbcTemplate con = conexao.getConexaoDoBanco();
        JdbcTemplate conServer = conexaoServer.getConexaoDoBanco();

        String sql = "SELECT * FROM Componente WHERE fkComputador = ?";

        try {
            // Buscando no banco local
            List<Componente> componentesLocal = con.query(sql, new BeanPropertyRowMapper<>(Componente.class), idComputador);

            // Buscando no banco do servidor
            List<Componente> componentesServer = conServer.query(sql, new BeanPropertyRowMapper<>(Componente.class), idComputador);

            List<Componente> todosComponentes = new ArrayList<>();
            todosComponentes.addAll(componentesLocal);
            todosComponentes.addAll(componentesServer);

            return todosComponentes;

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
                }catch (SQLException e) {
                        e.printStackTrace(); // Trate a exceção de fechamento da conexão do servidor
                    }
                }
            }
        }

        public List<Componente> buscarPeloTipoEIdComputador(String nomeTipo, Integer idComputador) {
            Conexao conexao = new Conexao();
            ConexaoServer conexaoServer = new ConexaoServer();
            JdbcTemplate con = conexao.getConexaoDoBanco();
            JdbcTemplate conServer = conexaoServer.getConexaoDoBanco();

            String sql = "SELECT * FROM Componente WHERE tipo = ? AND fkComputador = ?";

            try {
                // Buscando no banco local
                List<Componente> componentesLocal = con.query(sql, new BeanPropertyRowMapper<>(Componente.class), nomeTipo, idComputador);

                // Buscando no banco do servidor
                List<Componente> componentesServer = conServer.query(sql, new BeanPropertyRowMapper<>(Componente.class), nomeTipo, idComputador);

                List<Componente> todosComponentes = new ArrayList<>();
                todosComponentes.addAll(componentesLocal);
                todosComponentes.addAll(componentesServer);

                return todosComponentes;

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

        public Componente buscarSerialDiscoPeloId(Integer id) {
            Conexao conexao = new Conexao();
            ConexaoServer conexaoServer = new ConexaoServer();
            JdbcTemplate con = conexao.getConexaoDoBanco();
            JdbcTemplate conServer = conexaoServer.getConexaoDoBanco();

            String sql = "SELECT numeroSerial FROM Componente WHERE tipo = 'DISCO' AND idComponente = ?";

            try {
                // Buscando no banco local
                Componente componenteLocal = con.queryForObject(sql, (Componente.class), id);

                // Buscando no banco do servidor
                Componente componenteServer = conServer.queryForObject(sql, (Componente.class), id);

                // Escolha qual componente retornar (pode ser lógica de negócios específica)
                return (componenteLocal != null) ? componenteLocal : componenteServer;

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
