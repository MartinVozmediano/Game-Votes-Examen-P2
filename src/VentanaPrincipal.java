import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
    private JPanel panelRanking;
    private JLabel lblTituloRanking;
    private JList lstRanking;
    private JButton btnActualizarRanking;
    private JScrollPane scrollRanking;

    private int codigoSeleccionado = -1;
    private String ultimaBusquedaTexto = null;
    private String ultimoCriterio = "";

    private GestionVideojuegos gestion = new GestionVideojuegos();

    public VentanaPrincipal() {
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarVideojuego();
            }
        });

        spnVotos.setModel(new SpinnerNumberModel(1, 1, 3, 1));
        txtResultados.setEditable(false);
        limpiarSeleccion();

        btnBuscarTexto.addActionListener(e -> {
            String texto = txtBuscarTexto.getText();
            Object criterioElegido = comboBox1.getSelectedItem();
            String criterio = (criterioElegido == null) ? "" : criterioElegido.toString();
            try {
                ArrayList<Videojuego> resultado = new ArrayList<>();
                if (criterioElegido.equals("Nombre")) {
                    resultado = gestion.buscarNombre(texto);
                }
                if (criterioElegido.equals("Estudio")) {
                    resultado = gestion.buscarEstudio(texto);
                }
                ultimaBusquedaTexto = texto;
                ultimoCriterio = criterio;
                mostrarResultados(resultado);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(Ventana, ex.getMessage());
            }
        });

        btnBuscarCodigo.addActionListener(e -> {
            limpiarSeleccion();
            int codigo;
            try {
                codigo = Integer.parseInt(txtBuscarCodigo.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(Ventana, "Ingrese un código numérico");
                return;
            }
            if (codigo <= 0) {
                JOptionPane.showMessageDialog(Ventana, "El código debe ser positivo");
                return;
            }
            int indice = gestion.busquedaBinaria(codigo);
            if (indice == -1) {
                JOptionPane.showMessageDialog(Ventana, "No existe un videojuego con ese código");
                return;
            }
            codigoSeleccionado = codigo;
            Videojuego v = gestion.obtener(indice);
            txtEditNombre.setText(v.getNombre());
            txtEditEstudio.setText(v.getEstudio());
            cbxEditCategoria.setSelectedItem(v.getCategoria());
            txtEditAnio.setText(String.valueOf(v.getAnioLan()));
            lblVotosActuales.setText(String.valueOf(v.getVotos()));
            btnActualizar.setEnabled(true);
            btnEliminar.setEnabled(true);
            btnRestarVotos.setEnabled(true);
        });

        btnActualizar.addActionListener(e -> {
            int indice = gestion.busquedaBinaria(codigoSeleccionado);
            try {
                gestion.editar(indice, txtEditNombre.getText(), txtEditEstudio.getText(),
                        (String) cbxEditCategoria.getSelectedItem(), txtEditAnio.getText());
                JOptionPane.showMessageDialog(Ventana, "Videojuego actualizado");
                refrescarVistas();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(Ventana, ex.getMessage());
            }
        });


        btnEliminar.addActionListener(e -> {
            int indice = gestion.busquedaBinaria(codigoSeleccionado);
            int opcion = JOptionPane.showConfirmDialog(Ventana,
                    "¿Eliminar el videojuego?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (opcion != JOptionPane.YES_OPTION) {
                return;
            }
            try {
                gestion.eliminar(indice);
                limpiarSeleccion();
                refrescarVistas();
                JOptionPane.showMessageDialog(Ventana, "Videojuego eliminado");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(Ventana, ex.getMessage());
            }
        });

        btnRestarVotos.addActionListener(e -> {
            int indice = gestion.busquedaBinaria(codigoSeleccionado);
            int cantidad = (Integer) spnVotos.getValue();
            try {
                gestion.restarVotos(indice, cantidad);
                lblVotosActuales.setText(String.valueOf(gestion.obtener(indice).getVotos()));
                refrescarVistas();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(Ventana, ex.getMessage());
            }
        });

    }

    private void limpiarSeleccion() {
        codigoSeleccionado = -1;
        txtEditNombre.setText("");
        txtEditEstudio.setText("");
        txtEditAnio.setText("");
        lblVotosActuales.setText("0");
        btnActualizar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnRestarVotos.setEnabled(false);
    }

    private void mostrarResultados(ArrayList<Videojuego> lista) {
        txtResultados.setText("");
        if (lista.isEmpty()) {
            txtResultados.setText("Sin coincidencias");
            return;
        }
        for (Videojuego v : lista) {
            txtResultados.append(v.toString() + "\n");
        }
    }

    private void registrarVideojuego() {
        String codigoStr = txtCodigo.getText().trim();
        String nombre = textNombre.getText().trim();
        String estudio = textEstudio.getText().trim();
        String anioStr = textAnioDeLanzamiento.getText().trim();
        String categoria = (cbxCategoria.getSelectedItem() != null)
                ? cbxCategoria.getSelectedItem().toString()
                : "";

        if (codigoStr.isEmpty() || nombre.isEmpty() || estudio.isEmpty() || anioStr.isEmpty() || categoria.isEmpty()) {
            JOptionPane.showMessageDialog(Ventana, "Por favor, completa todos los campos antes de registrar.", "Campos incompletos",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int codigo = Integer.parseInt(codigoStr);
            int anio = Integer.parseInt(anioStr);

            Videojuego nuevoJuego = new Videojuego(codigo, nombre, estudio, categoria, anio);

            gestion.agregar(nuevoJuego);

            JOptionPane.showMessageDialog(Ventana, "Videojuego registrado correctamente.", "Registro exitoso",
                    JOptionPane.INFORMATION_MESSAGE);

            limpiarCamposRegistro();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(Ventana, "El Código ID y el Año de lanzamiento deben ser números enteros.", "Error de formato",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(Ventana,
                    ex.getMessage(), "Error al registrar",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCamposRegistro() {
        txtCodigo.setText("");
        textNombre.setText("");
        textEstudio.setText("");
        textAnioDeLanzamiento.setText("");
        if (cbxCategoria.getItemCount() > 0) {
            cbxCategoria.setSelectedIndex(0);
        }
        txtCodigo.requestFocus();
    }

    private void refrescarVistas() {

        if (ultimaBusquedaTexto != null) {
            mostrarResultados(gestion.buscarTexto(ultimaBusquedaTexto, ultimoCriterio));
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Gestión de Videojuegos");
        frame.setContentPane(new VentanaPrincipal().Ventana);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


}