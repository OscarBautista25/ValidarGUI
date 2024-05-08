import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login311 extends JFrame {
    private JPanel panelLog;
    private JTextField usuarioText;
    private JPasswordField passText;
    private JButton agregarAdministradorButton;
    private JButton validarDatosButton;
    Connection conexion;
    PreparedStatement ps;
    Statement st;
    ResultSet r;

    public Login311() {
        validarDatosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validar();
            }
        });
    }

    void conectar(){
        try{
            conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/usuarios311","root","1234");
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    void validar(){
        conectar();
        int validacion = 0;
        String usuario = usuarioText.getText();
        String pass = String.valueOf(passText.getText());
        try{
            st = conexion.createStatement();
            r = st.executeQuery("select * from administrador where usuario = '"+usuario+ "'and contrasena = '"+pass+"'");
            if(r.next()){
                validacion=1;
                if(validacion==1){
                    JOptionPane.showMessageDialog(null, "Validación Exitosa");
                }
            }else{
                JOptionPane.showMessageDialog(null, "Usuario sin permisos de administración");
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error"+e.getMessage());
        }

    }

    public static void main(String[] args) {
        Login311 login1 = new Login311();
        login1.setContentPane(new Login311().panelLog);
        login1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        login1.setVisible(true);
        login1.pack();
}
}

//conectar
ps = conexion.preapareStatement("Insert int administradores(usuario,contrasena) values (?,?,?)");