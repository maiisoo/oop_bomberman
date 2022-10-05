package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public abstract class AnimatedEntity extends Entity {
    protected int is_move;        // jump with pixel
    protected int swap;           // swap image
    protected String direction;   // direction of player
    protected int count;          // count step of a jump
    protected int count_to_run;   // run after count frame
    protected boolean isAlive;       // life of enemy

    public AnimatedEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public AnimatedEntity(int xUnit, int yUnit, Image img, int is_move, int swap, String direction, int count, int count_to_run) {
        super(xUnit, yUnit, img);
        this.is_move = is_move;
        this.swap = swap;
        this.direction = direction;
        this.count = count;
        this.count_to_run = count_to_run;
    }

    public int getIs_move() {
        return is_move;
    }

    public int getSwap() {
        return swap;
    }

    public String getDirection() {
        return direction;
    }

    public int getCount() {
        return count;
    }

    public int getCount_to_run() {
        return count_to_run;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setIs_move(int is_move) {
        this.is_move = is_move;
    }

    public void setSwap(int swap) {
        this.swap = swap;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setCount_to_run(int count_to_run) {
        this.count_to_run = count_to_run;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    @Override
    public void update() {

    }
}
