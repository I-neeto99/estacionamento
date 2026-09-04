package br.com.fiap.main;

import br.com.fiap.dao.CarroDAO;
import br.com.fiap.dao.ClienteDAO;
import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dto.Carro;
import br.com.fiap.dto.Cliente;

import java.sql.Connection;

public class TesteClienteCRUD {

    private static CarroDAO carroDAO = null;
    private static ClienteDAO clienteDAO = null;

    static void main() {
        Connection connection = ConnectionFactory.abrirConexao();
        carroDAO = new CarroDAO(connection);
        clienteDAO = new ClienteDAO(connection);

        list();

    }

    static void list() {
        System.out.println("Carros:");
        carroDAO.listarTodos().forEach(carro -> {
            System.out.println();
            System.out.println("Descricao: " + carro.getDescricao());
            System.out.println("Planca: " + carro.getPlaca());
            System.out.println("Cor: " + carro.getCor());
            System.out.println();

        });

        clienteDAO.listarTodos().forEach(carro -> {
            System.out.println(carro.toString());
        });
    }

    static void create() {
        Carro carro = new Carro();
        carro.setPlaca("1234567");
        carro.setCor("Prata");
        carro.setDescricao("strada");

        System.out.println(carroDAO.inserir(carro));

        Cliente cliente = new Cliente();
        cliente.setIdCliente(1);
        cliente.setNomeCliente("Italo");
        cliente.setPlaca(carro.getPlaca());

        System.out.println(clienteDAO.inserir(cliente));
    }
}
