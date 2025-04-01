/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProdutosDAO {
    Connection conn;
    
    public void cadastrarProduto(ProdutosDTO produto) {
        conn = new conectaDAO().connectDB();
        
        try {
            String sql = "insert into produtos (nome, valor, status) values (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getValor());
            stmt.setString(3, produto.getStatus());
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar: " + sqle.getMessage());
        }
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        return listagem;
    }
}