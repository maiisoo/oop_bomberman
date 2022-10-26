package uet.oop.bomberman.entities.items;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;

public class BombItem extends Item {
    public BombItem(int x, int y, Image img){
        super(x,y,img);
    }

    @Override
    public void activateImpactOnPlayer() {
        if(!this.isGotEaten()) {
            BombermanGame.bombStock++;
            this.setImg(null);
            BombermanGame.items.remove(this);
            this.setGotEaten(true);
        }
    }
}
