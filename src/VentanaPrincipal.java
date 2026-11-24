import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal {
    private JPanel Ventana;
    private JTabbedPane tabbedPane1;
    private JPanel Registro;
    private JTextField txtCodigo;
    private JTextField textNombre;
    private JTextField textEstudio;
    private JComboBox cbxCategoria;
    private JButton btnRegistrar;
    private JTextField textAnioDeLanzamiento;

    public VentanaPrincipal() {
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
