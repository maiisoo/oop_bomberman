package uet.oop.bomberman.entities.items;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class BombItem extends Item {
    public BombItem(int x, int y, Image img){
        super(x,y,img);
    }

    @Override
    public void activateImpactOnPlayer() {
        if(!this.isGotEaten()) {
            BombermanGame.bombStock++;
            this.setImg(Sprite.grass.getFxImage());
            BombermanGame.items.remove(this);
            this.setGotEaten(true);
        }
    }

    void changePic(){
        this.setImg(Sprite.powerup_bombs.getFxImage());
    }
}
