package uet.oop.bomberman.entities.items;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.list_kill;
import static uet.oop.bomberman.BombermanGame.obj_matrix;

public abstract class Item extends Entity {
    private boolean gotEaten = false;

    private boolean gotRevealed = false;
    public Item(int xUnit, int yUnit, Image img){
        super(xUnit, yUnit, Sprite.brick.getFxImage());
    }

    public Item(int xUnit, int yUnit, Image img, boolean gotEaten){
        super(xUnit, yUnit, Sprite.brick.getFxImage());
        this.gotEaten = gotEaten;
    }

    public boolean isGotEaten() {
        return gotEaten;
    }

    public void setGotEaten(boolean gotEaten) {
        this.gotEaten = gotEaten;
    }

    public void update(){
        if(!gotRevealed) {
            if (gotBlownUp()) {
                obj_matrix[this.x / 32][this.y / 32] = 1;
                changePic();
                gotRevealed = true;
            }
        }
        else {
            if (checkCollision()) {
                activateImpactOnPlayer();
            }
        }
    }

    private boolean gotBlownUp(){
        return(list_kill[this.x/32][this.y/32] == 4);
    }
    private boolean checkCollision(){
        int ax = BombermanGame.bomberman.getX();
        int ay = BombermanGame.bomberman.getY();
        return (ax == this.x && ay == this.y);
    }

    public abstract void activateImpactOnPlayer();
    abstract void changePic();
}
