package DAO;


import DAO.Conexao;
import DTO.Login;
import java.awt.Toolkit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import net.proteanit.sql.*;

/**
 *
 * @author luizo
 */
public class ControllerLogin {

    Icon erro = new ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icones/alert-octagon.png"))));
    Icon ok = new ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icones/certo.png"))));

    public boolean cadastra(Login login) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO login(usuario,senha,cpf,id) VALUES(?,?,?,?)";
        PreparedStatement ps;
        ps = Conexao.getConexao().prepareStatement(sql);
        try {
            ps.setString(1, login.getUsuario());
            ps.setString(2, login.getSenha());
            ps.setInt(3, login.getCpf());
            ps.execute();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ControllerLogin.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro, cadastro não realizado! Verifique os dados informados.\n\n" + "Erro SQL:\n" + ex, "Erro - Cadastro de Clientes", JOptionPane.ERROR_MESSAGE, erro);
            return false;
        }

    }

    public boolean edita(Login login) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE login SET cpf = ?,usuario = ?,senha = ? where id = ?";
        PreparedStatement ps;
        ps = Conexao.getConexao().prepareStatement(sql);
        try {
            ps.setInt(1, login.getCpf());
            ps.setString(2, login.getUsuario());
            ps.setString(3, login.getSenha());

            ps.execute();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ControllerLogin.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro, edição do cadastro não realizada! Verifique os dados informados.\n\n" + "Erro SQL:\n" + ex, "Erro - Cadastro de Clientes", JOptionPane.ERROR_MESSAGE, erro);
            return false;
        }

    }

    public boolean exclui(Login login) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM login where cpf=? or id=?";
        PreparedStatement ps;
        ps = Conexao.getConexao().prepareStatement(sql);
        try {
            ps.setInt(1, login.getCpf());
            ps.setInt(1, login.getId());
            ps.execute();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ControllerLogin.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro, exclusão do cadastro não realizada! Verifique os dados informados\n\n" + "Erro SQL:\n" + ex, "Erro - Cadastro de Clientes", JOptionPane.ERROR_MESSAGE, erro);
            return false;
        }
    }

    public ResultSet listar() {
        String sql = "Select * from login";
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            rs = ps.executeQuery();
            return rs;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Erro", JOptionPane.ERROR_MESSAGE, erro);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex, "Erro", JOptionPane.ERROR_MESSAGE, erro);

        }
        return null;
    }


  /*  public void pesquisa_cli() {
        String sql = "Select * from cliente where nome like ?";
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, pesq_cli.getText() + "%");
            rs = ps.executeQuery();
            tab_cli.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error, "Erro", JOptionPane.ERROR_MESSAGE, erro);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frmLoja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/

   
}
