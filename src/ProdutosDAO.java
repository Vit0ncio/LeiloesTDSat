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
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.util.ArrayList;

public class ProdutosDAO {
    Connection conn;
    PreparedStatement stmt;
    ResultSet rs;
    
    public void cadastrarProduto(ProdutosDTO produto) {
        conectaDAO conectaDao = new conectaDAO();
        conn = conectaDao.connectDB();
        
        try {
            String sql = "insert into produtos (nome, valor, status) values (?, ?, ?)";
            stmt = conn.prepareStatement(sql);
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
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();
        conn = new conectaDAO().connectDB();
        
        try {
            String sql = "select * from produtos";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getLong("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));
                listagem.add(produto);
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + sqle.getMessage());
        }
        return listagem;
    }
    
    public void venderProduto(long id) {
        conectaDAO conectaDao = new conectaDAO();
        conn = conectaDao.connectDB();
        
        try {
            String sql = "update produtos set status = ? where id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "Vendido");
            stmt.setLong(2, id);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao vender produto: " + sqle.getMessage());
        }
    }
    
    public ArrayList<ProdutosDTO> listarProdutosVendidos() {
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();
        conectaDAO conectaDao = new conectaDAO();
        conn = conectaDao.connectDB();
        
        try {
            String sql = "select * from produtos where status = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "Vendido");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getLong("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));
                listagem.add(produto);
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos vendidos: " + sqle.getMessage());
        }
        return listagem;
    }
}