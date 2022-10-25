package uet.oop.bomberman.entities.StaticEntity;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.list_kill;
import static uet.oop.bomberman.BombermanGame.stillObjects;

public class Brick extends Entity {
    public Brick(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        checkUnderBrick();
    }

    public void checkUnderBrick() { //Check Brick's Visibility
        for (Entity entity : stillObjects) {
            if (entity instanceof Brick)
                if (list_kill[entity.getX() / 32][entity.getY() / 32] == 4) {    // At the element of the 2-dimensional listKill array with the value 4, Brick and Grass will appear
                    entity.setImg(Sprite.grass.getFxImage());
                }
        }
    }
}
