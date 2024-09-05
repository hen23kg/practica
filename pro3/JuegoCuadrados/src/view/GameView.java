package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameView extends JFrame {
    private JPanel cuadradoAzul;
    private JPanel cuadradoRojo;
    private JPanel destinoAzul;
    private JPanel destinoRojo;
    private JButton nuevoJuegoButton;
    private JButton scoresButton;
    private JButton salirButton;
    private ColocacionListener colocacionListener;

    public GameView() {
        // Configuración básica de la ventana
        setTitle("Juego Cuadrados");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);  // Deshabilitar el layout automático

        // Inicialización de los cuadrados
        cuadradoAzul = new JPanel();
        cuadradoAzul.setBackground(Color.BLUE);
        cuadradoAzul.setBounds(90, 50, 50, 50);
        add(cuadradoAzul);

        cuadradoRojo = new JPanel();
        cuadradoRojo.setBackground(Color.RED);
        cuadradoRojo.setBounds(90, 150, 50, 50);
        add(cuadradoRojo);

        // Inicialización de los destinos
        destinoAzul = new JPanel();
        destinoAzul.setBackground(Color.LIGHT_GRAY);
        destinoAzul.setBounds(350, 50, 50, 50);
        add(destinoAzul);

        destinoRojo = new JPanel();
        destinoRojo.setBackground(Color.LIGHT_GRAY);
        destinoRojo.setBounds(350, 150, 50, 50);
        add(destinoRojo);

        // Botón "Nuevo Juego"
        nuevoJuegoButton = new JButton("Nuevo Juego");
        nuevoJuegoButton.setBounds(50, 400, 120, 30);
        add(nuevoJuegoButton);

        // Botón "Scores"
        scoresButton = new JButton("Scores");
        scoresButton.setBounds(180, 400, 120, 30);
        add(scoresButton);

        // Botón "Salir"
        salirButton = new JButton("Salir");
        salirButton.setBounds(310, 400, 120, 30);
        add(salirButton);

        // Listener para el cuadrado azul
        cuadradoAzul.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Inicia el movimiento del cuadrado azul
                if (colocacionListener != null) {
                    colocacionListener.iniciarTiempo();
                }
            }
        });

        cuadradoAzul.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                cuadradoAzul.setLocation(e.getXOnScreen(), e.getYOnScreen());
                verificarColocacion(cuadradoAzul, "azul");
            }
        });

        // Listener para el cuadrado rojo
        cuadradoRojo.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Inicia el movimiento del cuadrado rojo
                if (colocacionListener != null) {
                    colocacionListener.iniciarTiempo();
                }
            }
        });

        cuadradoRojo.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                cuadradoRojo.setLocation(e.getXOnScreen(), e.getYOnScreen());
                verificarColocacion(cuadradoRojo, "rojo");
            }
        });
    }

    public void setColocacionListener(ColocacionListener listener) {
        this.colocacionListener = listener;
    }

    private void verificarColocacion(JPanel cuadrado, String color) {
        Rectangle boundsCuadrado = cuadrado.getBounds();
        Rectangle boundsDestino;

        if (cuadrado == cuadradoAzul) {
            boundsDestino = destinoAzul.getBounds();
            if (boundsCuadrado.intersects(boundsDestino)) {
                cuadrado.setLocation(destinoAzul.getLocation());
                if (colocacionListener != null) {
                    colocacionListener.cuadradoColocado(color);
                }
            }
        } else if (cuadrado == cuadradoRojo) {
            boundsDestino = destinoRojo.getBounds();
            if (boundsCuadrado.intersects(boundsDestino)) {
                cuadrado.setLocation(destinoRojo.getLocation());
                if (colocacionListener != null) {
                    colocacionListener.cuadradoColocado(color);
                }
            }
        }
    }

    // Método para mostrar un mensaje
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    // Getters para los botones
    public JButton getNuevoJuegoButton() {
        return nuevoJuegoButton;
    }

    public JButton getScoresButton() {
        return scoresButton;
    }

    public JButton getSalirButton() {
        return salirButton;
    }
}
