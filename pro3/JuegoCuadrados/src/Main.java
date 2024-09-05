

import model.GameModel;
import view.GameView;
import controller.GameController;

public class Main {
    public static void main(String[] args) {
        GameModel model = new GameModel();
        GameView view = new GameView();
        GameController controller = new GameController(model, view);

        view.setVisible(true);  // Mostrar la ventana del juego
    }
}
