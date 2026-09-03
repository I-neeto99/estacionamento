package br.com.fiap.main;

import br.com.fiap.dao.CarroDAO;
import br.com.fiap.dao.ConnectionFactory;
import br.com.fiap.dto.Carro;

import java.sql.Connection;

public class TesteUpdate {
    static void main() {
        Connection con = ConnectionFactory.abrirConexao();

        Carro carro = new Carro();
        carro.setPlaca("RTI8317");
        carro.setCor("vermelho");
        carro.setDescricao("strada");

        CarroDAO carroDAO = new CarroDAO(con);
        System.out.println(carroDAO.alterar(carro));

        ConnectionFactory.fecharConexao(con);
    }
}
