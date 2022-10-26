package uet.oop.bomberman.entities.items;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.items.*;
import uet.oop.bomberman.entities.Entity;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.StaticEntity.Bomb;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.bomberman;

public class SpeedItem extends Item {
    public SpeedItem(int x, int y, Image img){
        super(x,y,img);
    }

    public SpeedItem(int x, int y, Image img, boolean gotEaten){
        super(x,y,img, gotEaten);
    }

    public void activateImpactOnPlayer(){
        if(!this.isGotEaten()){
            bomberman.speed++;
            this.setImg(Sprite.grass.getFxImage());
            this.setGotEaten(true);
            bomberman.setSpeed(2);
            BombermanGame.items.remove(this);
        }
    }

    void changePic(){
        this.setImg(Sprite.powerup_speed.getFxImage());
    }
}

