package uet.oop.bomberman.entities.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.AI.AILow;
import uet.oop.bomberman.control.Move;
import uet.oop.bomberman.entities.Bomber;

import java.util.Random;

import static uet.oop.bomberman.BombermanGame.bomberman;

public class Oneal extends Enemy {
    public Oneal(int x, int y, Image img) {
        super(x, y, img);
    }

    public Oneal(int xUnit, int yUnit, Image img, int is_move, int swap, String direction, int count, int count_to_run) {
        super(xUnit, yUnit, img);
        this.is_move = 4;
        this.swap = 1;
        this.direction = "up";
        this.count = 0;
        this.count_to_run = 0;
    }

    @Override
    public void update() {
        AILow ai = new AILow(bomberman, this);
        ai.nextMove();
    }

}
