package core.init;

import core.game.Game;
import core.graphics.GraphicsGame;
import core.graphics.GraphicsLayout;
import core.graphics.graphicselements.GameGraphicsElements;

import java.io.IOException;

public class Initialization {

    public static long timeStart;

    private Initialization() {
    }

    public static void initialize(GameGraphicsElements elements) throws IOException {
        Game.initialize();
        GraphicsGame.initialize(elements);
        GraphicsLayout.initialize(elements);
    }
}
