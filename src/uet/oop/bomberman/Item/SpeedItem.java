package uet.oop.bomberman.Item;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.StaticEntity.Bomb;

public class SpeedItem extends Item{
    public SpeedItem(int x, int y, Image img){
        super(x,y,img);
    }

    public void activateImpactOnPlayer(){
        if(!this.isGotEaten()){
            BombermanGame.bomberman.speed++;
            this.setGotEaten(true);
            this.setImg(null);
            BombermanGame.items.remove(this);
        }
    }
}
