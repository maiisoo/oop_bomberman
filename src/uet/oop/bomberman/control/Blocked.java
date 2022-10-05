package uet.oop.bomberman.control;

import uet.oop.bomberman.entities.Entity;

import static uet.oop.bomberman.BombermanGame.obj_matrix;

public class Blocked {
    public static boolean block_down(Entity entity) {   //Create a blocked that prevent all mob go down through the object
        return obj_matrix[entity.getX() / 32][entity.getY() / 32 + 1] == 1;
    }

    public static boolean block_up(Entity entity) {     //Create a blocked that prevent all mob go up through the object
        return obj_matrix[entity.getX() / 32][entity.getY() / 32 - 1] == 1;
    }

    public static boolean block_left(Entity entity) {   //Create a blocked that prevent all mob go left through the object
        return obj_matrix[entity.getX() / 32 - 1][entity.getY() / 32] == 1;
    }

    public static boolean block_right(Entity entity) {   //Create a blocked that prevent all mob go right through the object
        return obj_matrix[entity.getX() / 32 + 1][entity.getY() / 32] == 1;
    }
}
