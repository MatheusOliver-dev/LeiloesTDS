package DAO;

import DTO.ProdutosDTO;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutosDAO {

    Connection conn;

    public void cadastrarProduto(ProdutosDTO produto) {
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";

        try {
            conn = new conectaDAO().connectDB();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getValor());
            stmt.setString(3, produto.getStatus());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao cadastrar o produto.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao adicionar produto: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();
        String sql = "SELECT * FROM produtos";

        try {
            conn = new conectaDAO().connectDB();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getDouble("valor"));
                produto.setStatus(rs.getString("status"));

                listagem.add(produto);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return listagem;

    }

    public void venderProduto(int idProduto) {
        String sql = "UPDATE produtos SET status = ? WHERE id = ?";

        try {
            conn = new conectaDAO().connectDB();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "Vendido");
            stmt.setInt(2, idProduto);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao vender o produto. Produto não encontrado.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao vender produto: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public ArrayList<ProdutosDTO> listarProdutosVendidos() {
        ArrayList<ProdutosDTO> listagemVendidos = new ArrayList<>();
        String sql = "SELECT * FROM produtos WHERE status = ?";

        try {
            conn = new conectaDAO().connectDB();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "Vendido");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getDouble("valor"));
                produto.setStatus(rs.getString("status"));

                listagemVendidos.add(produto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listagemVendidos;
    }
}
