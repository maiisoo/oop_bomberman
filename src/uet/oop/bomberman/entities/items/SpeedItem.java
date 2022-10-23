package uet.oop.bomberman.entities.items;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;

public class SpeedItem extends Item {
    public SpeedItem(int x, int y, Image img){
        super(x,y,img);
    }
    @Override
    public void update() {}

    @Override
    public void activateImpactOnPlayer() {
        if(!this.isGotEaten()){
            BombermanGame.bomberman.speed++;
            this.setGotEaten(true);
            this.setImg(null);
            BombermanGame.items.remove(this);
        }
    }
}
