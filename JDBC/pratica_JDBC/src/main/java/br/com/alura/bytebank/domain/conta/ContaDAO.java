package br.com.alura.bytebank.domain.conta;

import br.com.alura.bytebank.domain.cliente.Cliente;
import br.com.alura.bytebank.domain.cliente.DadosCadastroCliente;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class ContaDAO {

    private Connection conn;

    ContaDAO(Connection connection) {
        this.conn = connection;
    }

    public void salvar(DadosAberturaConta dadosDaConta) {

        //o cliente em teoria iria digitar os dados em console, onde seriam
        //armazenados no DTO "dadosDaConta", que é o que nós passamos como
        //parâmetro para instanciar o cliente (e a conta)
        var cliente = new Cliente(dadosDaConta.dadosCliente());
        var conta = new Conta(dadosDaConta.numero(), BigDecimal.ZERO, cliente);

        String sql = "insert into conta (numero, saldo, cliente_nome, cliente_cpf, cliente_email)" +
                "values (?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setInt(1, conta.getNumero());
            preparedStatement.setBigDecimal(2, BigDecimal.ZERO);
            preparedStatement.setString(3, dadosDaConta.dadosCliente().nome());
            preparedStatement.setString(4, dadosDaConta.dadosCliente().cpf());
            preparedStatement.setString(5, dadosDaConta.dadosCliente().email());

            preparedStatement.execute();

            preparedStatement.close();
            conn.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public Set<Conta> listar() {

        ResultSet resultSet;
        PreparedStatement ps;

        Set<Conta> contas = new HashSet<>();

        String sql = "select * from conta";

        try {
            //a variável "conn" é o construtor desta classe, ela recebe como paramêtro a conexão
            //instanciada em "ConnectionFactory", e nossa conexão prepara uma preparedStatement,
            //nesse caso, com a query "select * from conta"
            ps = conn.prepareStatement(sql);


            //"ResultSet" é usado quando nós temos um resultado no banco de dados,
            //ele executa a query e retorna para a gente o resultado do select
            resultSet = ps.executeQuery();

            //em quanto o nosso resultado possuir uma próxima linha
            //execute o while
            while(resultSet.next()) {
                Integer numero = resultSet.getInt(1);
                BigDecimal saldo = resultSet.getBigDecimal(2);

                String nome = resultSet.getString(3);
                String cpf = resultSet.getString(4);
                String email = resultSet.getString(5);


                DadosCadastroCliente dadosCadastroCliente = new DadosCadastroCliente(nome, cpf, email);
                Cliente cliente = new Cliente(dadosCadastroCliente);

                contas.add(new Conta(numero, saldo, cliente));


            }

            resultSet.close();
            ps.close();
            conn.close();
        }
        catch (SQLException e) {
            System.out.println(e);
        }
        return contas;

    }


    public Conta listarPorNumero(Integer numero) {
        ResultSet resultSet;
        PreparedStatement preparedStatement;

        String sql = "select * from conta where numero = ?";
        Conta conta = null;

        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, numero);
            resultSet = preparedStatement.executeQuery();


            while(resultSet.next()) {

                Integer numeroRecuperado = resultSet.getInt(1);
                BigDecimal saldo = resultSet.getBigDecimal(2);

                String nome = resultSet.getString(3);
                String cpf = resultSet.getString(4);
                String email = resultSet.getString(5);

                DadosCadastroCliente dadosCadastroCliente = new DadosCadastroCliente(nome, cpf, email);

                Cliente cliente = new Cliente(dadosCadastroCliente);

                conta = new Conta(numeroRecuperado, saldo, cliente);

            }


            resultSet.close();
            preparedStatement.close();
            conn.close();
        }
        catch (SQLException e) {
            System.out.println(e);
        }

        return conta;
    }


    public void alterar(Integer numero, BigDecimal valor) {
        PreparedStatement ps;
        String sql = "update conta set saldo = ? where numero = ?";


        try {

            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);

            ps.setBigDecimal(1, valor);
            ps.setInt(2, numero);

            ps.execute();
            conn.commit();
            ps.close();
            conn.close();
        }
        catch (SQLException e) {
            try {
                conn.rollback();
            }
            catch(SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }












}
