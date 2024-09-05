package controller;

import model.GameModel;
import view.GameView;
import view.ColocacionListener;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameController {
    private GameModel model;
    private GameView view;
    private boolean azulColocado = false;
    private boolean rojoColocado = false;
    private long startTime;
    private List<Long> scores;

    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
        this.scores = new ArrayList<>();

        view.setColocacionListener(new ColocacionListener() {
            @Override
            public void cuadradoColocado(String color) {
                if (color.equals("azul")) {
                    azulColocado = true;
                } else if (color.equals("rojo")) {
                    rojoColocado = true;
                }

                // Si ambos cuadrados están colocados, termina el juego
                if (azulColocado && rojoColocado) {
                    long endTime = System.currentTimeMillis();
                    long totalTime = endTime - startTime;
                    scores.add(totalTime);

                    model.endGame();
                    view.showMessage("¡Juego completado en " + totalTime + " milisegundos!");

                    // Ordenar los scores en orden ascendente
                    Collections.sort(scores);
                }
            }

            @Override
            public void iniciarTiempo() {
                // Solo iniciar el tiempo la primera vez que se mueve un cuadrado
                if (startTime == 0) {
                    startTime = System.currentTimeMillis();
                }
            }
        });

        // Manejo de botones
        view.getNuevoJuegoButton().addActionListener(e -> reiniciarJuego());
        view.getScoresButton().addActionListener(e -> mostrarScores());
        view.getSalirButton().addActionListener(e -> System.exit(0));
    }

    private void reiniciarJuego() {
        // Reinicia el estado del juego
        azulColocado = false;
        rojoColocado = false;
        startTime = 0;

        // Reposicionar los cuadrados
        view.getContentPane().getComponent(0).setLocation(90, 50);  // Cuadrado azul
        view.getContentPane().getComponent(1).setLocation(90, 150);  // Cuadrado rojo
    }

    private void mostrarScores() {
        StringBuilder scoreText = new StringBuilder("Scores:\n");
        for (Long score : scores) {
            scoreText.append(score).append(" ms\n");
        }
        JOptionPane.showMessageDialog(view, scoreText.toString());
    }
}
