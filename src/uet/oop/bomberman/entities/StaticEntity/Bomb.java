package uet.oop.bomberman.entities.StaticEntity;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.BombItem;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.BombermanGame.*;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends Entity {

    private int bombPlaceTime = 0;
    private static int swap_active = 1;

    public Bomb(int x, int y, Image img) {
        super(x, y, img);
    }

    public static void placeBomb() {
        int x = BombermanGame.bomberman.getX() / 32;
        int y = BombermanGame.bomberman.getY() / 32;
        Bomb bomb = new Bomb(x, y, Sprite.bomb.getFxImage());
        BombermanGame.obj_matrix[x][y] = 1;
        BombermanGame.stillObjects.add(bomb);
    }

    private void bombStillNotExplode() {
        if (1 <= swap_active && swap_active <= 4) {
            this.setImg(Sprite.bomb_1.getFxImage());
            swap_active++;
        } else if (5 <= swap_active && swap_active <= 8) {
            this.setImg(Sprite.bomb_2.getFxImage());
            swap_active++;
        } else if (9 <= swap_active && swap_active <= 12) {
            this.setImg(Sprite.bomb_1.getFxImage());
            swap_active++;
        } else if (swap_active > 12) {
            this.setImg(Sprite.bomb.getFxImage());
            swap_active = 1;
        }
    }

    //Check if the bomb has come to the time to explode
    private boolean checkBombThere() {
        return (bombPlaceTime < 100);
    }

    public void update() {
        if (checkBombThere()) {
            this.bombStillNotExplode();
            bombPlaceTime++;
        }
        else{
            BombermanGame.obj_matrix[x][y] = 1;
        }
    }


}