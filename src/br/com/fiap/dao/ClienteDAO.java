package br.com.fiap.dao;

import br.com.fiap.dto.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private Connection con;

    public ClienteDAO(Connection con) {
        this.con = con;
    }

    public Connection getCon() {
        return con;
    }

    public String inserir(Cliente cliente) {
        String SQL = "INSERT INTO ddd_cliente(id_cliente, nome_cliente, placa) VALUES(?,?,?)";

        try (PreparedStatement ps = getCon().prepareStatement(SQL)) {
            ps.setInt(1, cliente.getIdCliente());
            ps.setString(2, cliente.getNomeCliente());
            ps.setString(3, cliente.getPlaca());

            if(ps.executeUpdate() > 0) return "Inserido com sucesso.";

            return "Erro ao inserir";
        } catch (SQLException e) {
            return "ERRO SQL:" + e.getMessage();
        }
    }

    public String alterar(Cliente cliente) {
        String SQL = "UPDATE ddd_cliente SET nome_cliente = ?, placa = ? WHERE id_cliente = ?";

        try(PreparedStatement ps = getCon().prepareStatement(SQL)) {
            ps.setString(1, cliente.getNomeCliente());
            ps.setString(2, cliente.getPlaca());
            ps.setInt(3, cliente.getIdCliente());

            if(ps.executeUpdate() > 0) return "Atualizado com sucesso";

            return "Erro ao atualizar";
        } catch (SQLException e) {
            return "ERRO SQL: " + e.getMessage();
        }
    }

    public String excluir(Cliente cliente) {
        String SQL = "DELETE FROM ddd_cliente WHERE id_cliente = ?";

        try(PreparedStatement ps = getCon().prepareStatement(SQL)) {
            ps.setInt(1, cliente.getIdCliente());

            if(ps.executeUpdate() > 0) return "Excluido com sucesso";

            return "Erro ao excluir";
        }catch (SQLException e) {
            return "ERRO SQL: " + e.getMessage();
        }
    }

    public ArrayList<Cliente> listarTodos() {
        String SQL = "SELECT * FROM ddd_cliente";

        try(PreparedStatement ps = getCon().prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            ArrayList<Cliente> clientes = new ArrayList<>();

            if(rs == null) return clientes;

            while(rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt(1));
                cliente.setNomeCliente(rs.getString(2));
                cliente.setPlaca(rs.getString(3));

                clientes.add(cliente);
            }

            return clientes;

        } catch (SQLException e) {
            System.out.println("ERRO SQL:" + e.getMessage());
            return null;
        }
    }
}
