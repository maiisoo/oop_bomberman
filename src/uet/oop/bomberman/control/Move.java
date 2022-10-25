package uet.oop.bomberman.control;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Enemy.*;

import uet.oop.bomberman.entities.StaticEntity.Bomb;
import uet.oop.bomberman.graphics.*;

public class Move {
    public static void checkRun(AnimatedEntity character) {    //Check if all your mob move or not
        if (character instanceof Bomber && character.getCount() > 0) {
            setDirection(character.getDirection(), character, 4 * BombermanGame.bomberman.getSpeed());
            character.setCount(character.getCount() - 1);
        }
        if ((character instanceof Balloom || character instanceof Oneal || character instanceof Doll
                || character instanceof Kondoria) && character.getCount() > 0) {
            setDirection(character.getDirection(), character, 4);
            character.setCount(character.getCount() - 1);
        }
    }

    private static void setDirection(String direction, AnimatedEntity character, int isMove) {     //Show the direction of all mob
        switch (direction) {
            case "down":
                down_step(character);
                character.setY(character.getY() + isMove);
                break;
            case "up":
                up_step(character);
                character.setY(character.getY() - isMove);
                break;
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

    public static void down(AnimatedEntity character) {        //Control all mob to go down
            if (character instanceof Bomber && Blocked.block_down(character)) {
                if (character.getY() % 32 == 0 && character.getX() % 32 == 0) {//Block???
                    character.setDirection("down");
                    character.setCount(8 / ((Bomber) character).getSpeed());
                    checkRun(character);
                }
            }
            if ((character instanceof Balloom || character instanceof Oneal || character instanceof Doll
                    || character instanceof Kondoria ) && Blocked.block_down(character)) {
                if (character.getY() % 32 == 0 && character.getX() % 32 == 0) {
                    character.setDirection("down");
                    character.setCount(8);
                    checkRun(character);
            }
        }
    }

    private static void down_step(AnimatedEntity character) {      //Show the animation of all mob that go down
            if (character instanceof Bomber && character.getY() % 8 == 0) {
                if (character.getSwap() == 1) {
                    character.setImg(Sprite.player_down.getFxImage());
                    character.setSwap(2);
                } else if (character.getSwap() == 2) {
                    character.setImg(Sprite.player_down_1.getFxImage());
                    character.setSwap(3);
                } else if (character.getSwap() == 3) {
                    character.setImg(Sprite.player_down.getFxImage());
                    character.setSwap(4);
                } else {
                    character.setImg(Sprite.player_down_2.getFxImage());
                    character.setSwap(1);
                }
            }
        if (character instanceof Balloom && character.getY() % 8 == 0) {
            if (character.getSwap() == 1) {
                character.setImg(Sprite.balloom_right1.getFxImage());
                character.setSwap(2);
            } else if (character.getSwap() == 2) {
                character.setImg(Sprite.balloom_right2.getFxImage());
                character.setSwap(3);
            } else if (character.getSwap() == 3) {
                character.setImg(Sprite.balloom_right3.getFxImage());
                character.setSwap(4);
            } else {
                character.setImg(Sprite.balloom_right2.getFxImage());
                character.setSwap(1);
            }
        }
        if (character instanceof Oneal && character.getY() % 8 == 0) {
            if (character.getSwap() == 1) {
                character.setImg(Sprite.oneal_right1.getFxImage());
                character.setSwap(2);
            } else if (character.getSwap() == 2) {
                character.setImg(Sprite.oneal_right2.getFxImage());
                character.setSwap(3);
            } else if (character.getSwap() == 3) {
                character.setImg(Sprite.oneal_right3.getFxImage());
                character.setSwap(4);
            } else {
                character.setImg(Sprite.oneal_right2.getFxImage());
                character.setSwap(1);
            }
        }
        if (character instanceof Doll && character.getY() % 8 == 0) {
            if (character.getSwap() == 1) {
                character.setImg(Sprite.doll_left1.getFxImage());
                character.setSwap(2);
            } else if (character.getSwap() == 2) {
                character.setImg(Sprite.doll_left2.getFxImage());
                character.setSwap(3);
            } else if (character.getSwap() == 3) {
                character.setImg(Sprite.doll_left3.getFxImage());
                character.setSwap(4);
            } else {
                character.setImg(Sprite.doll_left2.getFxImage());
                character.setSwap(1);
            }
        }
    }


    public static void up(AnimatedEntity character) {      //Control all mob to go up
            if (character instanceof Bomber && Blocked.block_up(character)) {
                if (character.getY() % 32 == 0 && character.getX() % 32 == 0) {
                    character.setDirection("up");
                    character.setCount(8 / ((Bomber) character).getSpeed());
                    checkRun(character);
                }
            }
            if ((character instanceof Balloom || character instanceof Oneal || character instanceof Doll
                    || character instanceof Kondoria) && Blocked.block_up(character)) {
                if (character.getY() % 32 == 0 && character.getX() % 32 == 0) {
                    character.setDirection("up");
                    character.setCount(8);
                    checkRun(character);
            }
        }
    }

    private static void up_step(AnimatedEntity character) {        //Show the animation of all mob that go down
        if (character instanceof Bomber && character.getY() % 8 == 0) {
            if (character.getSwap() == 1) {
                character.setImg(Sprite.player_up.getFxImage());
                character.setSwap(2);
            } else if (character.getSwap() == 2) {
                character.setImg(Sprite.player_up_1.getFxImage());
                character.setSwap(3);
            } else if (character.getSwap() == 3) {
                character.setImg(Sprite.player_up.getFxImage());
                character.setSwap(4);
            } else {
                character.setImg(Sprite.player_up_2.getFxImage());
                character.setSwap(1);
            }
        }
        if (character instanceof Balloom && character.getY() % 8 == 0) {
            if (character.getSwap() == 1) {
                character.setImg(Sprite.balloom_left1.getFxImage());
                character.setSwap(2);
            } else if (character.getSwap() == 2) {
                character.setImg(Sprite.balloom_left2.getFxImage());
                character.setSwap(3);
            } else if (character.getSwap() == 3) {
                character.setImg(Sprite.balloom_left3.getFxImage());
                character.setSwap(4);
            } else {
                character.setImg(Sprite.balloom_left2.getFxImage());
                character.setSwap(1);
            }
        }
        if (character instanceof Oneal && character.getY() % 8 == 0) {
            if (character.getSwap() == 1) {
                character.setImg(Sprite.oneal_left1.getFxImage());
                character.setSwap(2);
            } else if (character.getSwap() == 2) {
                character.setImg(Sprite.oneal_left2.getFxImage());
                character.setSwap(3);
            } else if (character.getSwap() == 3) {
                character.setImg(Sprite.oneal_left3.getFxImage());
                character.setSwap(4);
            } else {
                character.setImg(Sprite.oneal_left2.getFxImage());
                character.setSwap(1);
            }
        }
        if (character instanceof Doll && character.getY() % 8 == 0) {
            if (character.getSwap() == 1) {
                character.setImg(Sprite.doll_right1.getFxImage());
                character.setSwap(2);
            } else if (character.getSwap() == 2) {
                character.setImg(Sprite.doll_right2.getFxImage());
                character.setSwap(3);
            } else if (character.getSwap() == 3) {
                character.setImg(Sprite.doll_right3.getFxImage());
                character.setSwap(4);
            } else {
                character.setImg(Sprite.doll_right2.getFxImage());
                character.setSwap(1);
            }
        }
    }

    public static void left(AnimatedEntity character) {        //Control all mob to go left
            if (character instanceof Bomber && Blocked.block_left(character)) {
                if(character.getX() % 32 == 0 && character.getY() % 32 == 0) {
                    character.setDirection("left");
                    character.setCount(8 / ((Bomber) character).getSpeed());
                    checkRun(character);
                }
            }
            if ((character instanceof Balloom || character instanceof Oneal || character instanceof Doll
                    || character instanceof Kondoria) && Blocked.block_left(character)) {
                if(character.getX() % 32 == 0 && character.getY() % 32 == 0) {
                    character.setDirection("left");
                    character.setCount(8);
                    checkRun(character);
            }
        }
    }

    private static void left_step(AnimatedEntity character) {      //Show the animation of all mob that go left
        if (character instanceof Bomber && character.getX() % 8 == 0) {
            if (character.getSwap() == 1) {
                character.setImg(Sprite.player_left.getFxImage());
                character.setSwap(2);
            } else if (character.getSwap() == 2) {
                character.setImg(Sprite.player_left_1.getFxImage());
                character.setSwap(3);
            } else if (character.getSwap() == 3) {
                character.setImg(Sprite.player_left.getFxImage());
                character.setSwap(4);
            } else {
                character.setImg(Sprite.player_left_2.getFxImage());
                character.setSwap(1);
            }
        }
        if (character instanceof Balloom && character.getY() % 8 == 0) {
            if (character.getSwap() == 1) {
                character.setImg(Sprite.balloom_right1.getFxImage());
                character.setSwap(2);
            } else if (character.getSwap() == 2) {
                character.setImg(Sprite.balloom_right2.getFxImage());
                character.setSwap(3);
            } else if (character.getSwap() == 3) {
                character.setImg(Sprite.balloom_right3.getFxImage());
                character.setSwap(4);
            } else {
                character.setImg(Sprite.balloom_right2.getFxImage());
                character.setSwap(1);
            }
        }
        if (character instanceof Oneal && character.getY() % 8 == 0) {
            if (character.getSwap() == 1) {
                character.setImg(Sprite.oneal_right1.getFxImage());
                character.setSwap(2);
            } else if (character.getSwap() == 2) {
                character.setImg(Sprite.oneal_right2.getFxImage());
                character.setSwap(3);
            } else if (character.getSwap() == 3) {
                character.setImg(Sprite.oneal_right3.getFxImage());
                character.setSwap(4);
            } else {
                character.setImg(Sprite.oneal_right2.getFxImage());
                character.setSwap(1);
            }
        }
        if (character instanceof Doll && character.getY() % 8 == 0) {
            if (character.getSwap() == 1) {
                character.setImg(Sprite.doll_left1.getFxImage());
                character.setSwap(2);
            } else if (character.getSwap() == 2) {
                character.setImg(Sprite.doll_left2.getFxImage());
                character.setSwap(3);
            } else if (character.getSwap() == 3) {
                character.setImg(Sprite.doll_left3.getFxImage());
                character.setSwap(4);
            } else {
                character.setImg(Sprite.doll_left2.getFxImage());
                character.setSwap(1);
            }
        }
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
            if (character instanceof Bomber && Blocked.block_right(character)) {
                if (character.getY() % 32 == 0 && character.getX() % 32 == 0) {
                    character.setDirection("right");
                    character.setCount(8 / ((Bomber) character).getSpeed());
                    checkRun(character);
                }
            }
            if ((character instanceof Balloom || character instanceof Oneal || character instanceof Doll
                    || character instanceof Kondoria) && Blocked.block_right(character)) {
                if (character.getX() % 32 == 0 && character.getY() % 32 == 0) {
                    character.setDirection("right");
                    character.setCount(8);
                    checkRun(character);
            }
        }
    }

    public static void right_step(AnimatedEntity character) {      //Show the animation of all mob that go right
        if (character instanceof Bomber && character.getX() % 8 == 0) {
            if (character.getSwap() == 1) {
                character.setImg(Sprite.player_right.getFxImage());
                character.setSwap(2);
            } else if (character.getSwap() == 2) {
                character.setImg(Sprite.player_right_1.getFxImage());
                character.setSwap(3);
            } else if (character.getSwap() == 3) {
                character.setImg(Sprite.player_right.getFxImage());
                character.setSwap(4);
            } else {
                character.setImg(Sprite.player_right_2.getFxImage());
                character.setSwap(1);
            }
        }

        if (character instanceof Balloom && character.getY() % 8 == 0) {
            if (character.getSwap() == 1) {
                character.setImg(Sprite.balloom_left1.getFxImage());
                character.setSwap(2);
            } else if (character.getSwap() == 2) {
                character.setImg(Sprite.balloom_left2.getFxImage());
                character.setSwap(3);
            } else if (character.getSwap() == 3) {
                character.setImg(Sprite.balloom_left3.getFxImage());
                character.setSwap(4);
            } else {
                character.setImg(Sprite.balloom_left2.getFxImage());
                character.setSwap(1);
            }
        }
        if (character instanceof Oneal && character.getY() % 8 == 0) {
            if (character.getSwap() == 1) {
                character.setImg(Sprite.oneal_left1.getFxImage());
                character.setSwap(2);
            } else if (character.getSwap() == 2) {
                character.setImg(Sprite.oneal_left2.getFxImage());
                character.setSwap(3);
            } else if (character.getSwap() == 3) {
                character.setImg(Sprite.oneal_left3.getFxImage());
                character.setSwap(4);
            } else {
                character.setImg(Sprite.oneal_left2.getFxImage());
                character.setSwap(1);
            }
        }
        if (character instanceof Doll && character.getY() % 8 == 0) {
            if (character.getSwap() == 1) {
                character.setImg(Sprite.doll_right1.getFxImage());
                character.setSwap(2);
            } else if (character.getSwap() == 2) {
                character.setImg(Sprite.doll_right2.getFxImage());
                character.setSwap(3);
            } else if (character.getSwap() == 3) {
                character.setImg(Sprite.doll_right3.getFxImage());
                character.setSwap(4);
            } else {
                character.setImg(Sprite.doll_right2.getFxImage());
                character.setSwap(1);
            }
        }
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