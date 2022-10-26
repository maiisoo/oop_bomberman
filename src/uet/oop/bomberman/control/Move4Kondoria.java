package uet.oop.bomberman.control;

import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.entities.Enemy.Kondoria;
import uet.oop.bomberman.graphics.Sprite;

public class Move4Kondoria extends Move{
    public static void checkRun(AnimatedEntity character) {    //Check if all your mob move or not
        if (character instanceof Kondoria && character.getCount() > 0) {
            setDirection(character.getDirection(), character, 4);
            character.setCount(character.getCount() - 1);
        }
    }

    private static void setDirection(String direction, AnimatedEntity character, int isMove) {     //Show the direction of all mob
        switch (direction) {
            case "left":
                left_step(character);
                character.setX(character.getX() - isMove);
                break;
            case "right":
                right_step(character);
                character.setX(character.getX() + isMove);
                break;
        }
    }

    public static void left(AnimatedEntity character) {        //Control all mob to go left
        if (character instanceof Kondoria) {
            if (character.getX() % 32 == 0 && character.getY() % 32 == 0) {
                character.setDirection("left");
                character.setCount(8);
                checkRun(character);
            }
        }
    }

    private static void left_step(AnimatedEntity character) {      //Show the animation of all mob that go left
        if (character instanceof Kondoria && character.getY() % 8 == 0) {
            if (character.getSwap() == 1) {
                character.setImg(Sprite.kondoria_left1.getFxImage());
                character.setSwap(2);
            } else if (character.getSwap() == 2) {
                character.setImg(Sprite.kondoria_left2.getFxImage());
                character.setSwap(3);
            } else if (character.getSwap() == 3) {
                character.setImg(Sprite.kondoria_left3.getFxImage());
                character.setSwap(4);
            } else {
                character.setImg(Sprite.kondoria_left2.getFxImage());
                character.setSwap(1);
            }
        }
    }

    public static void right(AnimatedEntity character) {       //Control all mob to go right
        if (character instanceof Kondoria) {
            if (character.getX() % 32 == 0 && character.getY() % 32 == 0) {
                character.setDirection("right");
                character.setCount(8);
                checkRun(character);
            }
        }
    }

    public static void right_step(AnimatedEntity character) {      //Show the animation of all mob that go right
        if (character instanceof Kondoria && character.getY() % 8 == 0) {
            if (character.getSwap() == 1) {
                character.setImg(Sprite.kondoria_right1.getFxImage());
                character.setSwap(2);
            } else if (character.getSwap() == 2) {
                character.setImg(Sprite.kondoria_right2.getFxImage());
                character.setSwap(3);
            } else if (character.getSwap() == 3) {
                character.setImg(Sprite.kondoria_right3.getFxImage());
                character.setSwap(4);
            } else {
                character.setImg(Sprite.kondoria_right2.getFxImage());
                character.setSwap(1);
            }
        }
    }
}