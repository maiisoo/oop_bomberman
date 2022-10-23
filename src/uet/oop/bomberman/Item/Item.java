package uet.oop.bomberman.Item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.BombermanGame;

public abstract class Item extends Entity {
    private boolean gotEaten = false;

    public Item(int xUnit, int yUnit, Image img){
        super(xUnit, yUnit, img);
    }

    public Item(int xUnit, int yUnit, Image img, boolean gotEaten){
        super(xUnit, yUnit, img);
        this.gotEaten = gotEaten;
    }

    public boolean isGotEaten() {
        return gotEaten;
    }

    public void setGotEaten(boolean gotEaten) {
        this.gotEaten = gotEaten;
    }

    public void update(){
        if(checkCollision()){
            System.out.println("Touched");
            activateImpactOnPlayer();
        }
    }

    private boolean checkCollision(){
        int ax = BombermanGame.bomberman.getX();
        int ay = BombermanGame.bomberman.getY();
        return (ax == this.x && ay == this.y);
    }

    public abstract void activateImpactOnPlayer();
}
