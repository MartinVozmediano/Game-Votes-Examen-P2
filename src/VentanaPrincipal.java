import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal {
    private JPanel Ventana;
    private JTabbedPane panelBusqueda;
    private JPanel Registro;
    private JTextField txtCodigo;
    private JTextField textNombre;
    private JTextField textEstudio;
    private JComboBox cbxCategoria;
    private JButton btnRegistrar;
    private JTextField textAnioDeLanzamiento;
    private JTextField txtBuscarTexto;
    private JButton btnBuscarTexto;
    private JTextArea txtResultados;
    private JTextField txtBuscarCodigo;
    private JButton btnBuscarCodigo;
    private JTextField txtEditNombre;
    private JTextField txtEditEstudio;
    private JComboBox cbxEditCategoria;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JLabel lblVotosActuales;
    private JSpinner spnVotos;
    private JButton btnRestarVotos;
    private JTextField txtEditAnio;
    private JComboBox comboBox1;

    public VentanaPrincipal() {
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
