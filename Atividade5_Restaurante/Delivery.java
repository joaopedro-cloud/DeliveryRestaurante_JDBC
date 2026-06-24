package JDBC_1pratica.Atividade5_Restaurante;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Delivery {

    private static final String URL = "jdbc:mysql://localhost:3306/restaurante_delivery";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.println("=== SISTEMA DE COMPRAS ONLINE ===");

        System.out.print("Digite o nome do cliente: ");
        String cliente = teclado.nextLine();

        System.out.print("Digite a escolha do cardápio: ");
        String produto = teclado.nextLine();

        System.out.print("Digite o valor unitário: ");
        double valor = teclado.nextDouble();

        System.out.print("Digite a quantidade comprada: ");
        int quantidade = teclado.nextInt();

        Connection conn = null;
        Statement stmt = null;

        try {

            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            stmt = conn.createStatement();

            String sql = "INSERT INTO pedidos (clientes, pedido, valor, quantidade) VALUES ("
                    + "'" + cliente + "', "
                    + "'" + produto + "', "
                    + valor + ", "
                    + quantidade + ")";


            int linhasAfetadas = stmt.executeUpdate(sql);

            if (linhasAfetadas > 0) {
                System.out.println("\n Sucesso! O pedido do cliente '" + cliente + "' foi registrado!");
            }

        } catch (SQLException e) {
            System.err.println("\n Erro no banco de dados: " + e.getMessage());
        } finally {

            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar conexões: " + e.getMessage());
            }
            teclado.close();
        }
    }
}