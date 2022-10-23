package uet.oop.bomberman.AI;

import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Enemy.Enemy;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class avoidBomb {
    private Bomber bomber;
    private Enemy enemy;

    private int radius = 1; //Game.getBombRadius();

    /**
     * x1  x2  x3
     * y1  0   1   2
     * y2  3   e   4
     * y3  5   6   7
     *
     * @param bombX toa do x cua bomb
     * @param bombY toa do y cua bomb
     * @return vị trí của bom trong khu vực dò của enemy
     * @return -1 nếu không thuộc phạm vi dò
     */
    public int detectBomb(int bombX, int bombY) {
        int enemyX = this.enemy.getX();
        int enemyY = this.enemy.getY();
        if (bombY == enemyY) {
            if (abs(bombX - enemyX) <= radius) {
                if (bombX - enemyX > 0) {
                    return 4; //right
                } else {
                    return 3; //left
                }
            }
        }
        if (bombX == enemyX) {
            if (abs(bombY - enemyY) <= radius) {
                if (bombY - enemyY > 0) {
                    return 6; //bottom
                } else {
                    return 1; //top
                }
            }
        }

        if ((enemyY - bombY > 0) && (enemyY - bombY <= radius)) {
            if ((bombX - enemyX) > 0 && (bombX - enemyX) <= radius) {
                return 2;//top-right
            }
            if ((enemyX - bombX) > 0 && (enemyX - bombX) <= radius) {
                return 0; // top-left
            }
        }
        if ((bombY - enemyY > 0) && (bombY - enemyY <= radius)) {
            if ((bombX - enemyX) > 0 && (bombX - enemyX) <= radius) {
                return 7;//bot-right
            }
            if ((enemyX - bombX) > 0 && (enemyX - bombX) <= radius) {
                return 5; //bot-left
            }
        }
        return -1; //if no bomb found
    }
    /*public int calculateDirection() {
        int Xe = this.enemy.getX();
        int Ye = this.enemy.getY();

        // top left
               *//*
                    tl      tm      tr

                     l      m       r

                     bl     bm      br


               *//*
        // canGo  -1 tương ứng với cango[4]
        //0     1      2    3   4 (-1)
        boolean[] canGo = { true,true,true,true,true};

        ArrayList<Integer> way = new ArrayList<Integer>();

        int thread =0;

        // duyet toan bo list bom cua bang
        for ( int i = 0 ; i < this._board.getBombs().size() ; i++){
            // phat hiện bom
            int Xb = this._board.getBombs().get(i).getXTile();
            int Yb = this._board.getBombs().get(i).getYTile();

            // xét những quả bom trong miền sét
            if ( this.detectBombinRanger(Xb, Yb)!=-1 ){
                thread++;
                // tùy trường hợp thì mình sẽ sét cách  hướng KHÔNG THỂ ĐI
                //  chú ý 4 thay cho -1;
                switch (this.detectBombinRanger(Xb, Yb) ){
                    case 0:
                        if ( Xb - Xe == this.radius ){
                            canGo[4]=canGo[1]=false;
                        }else{
                            canGo[4]=canGo[1]=canGo[3]= false;
                        }

                        break;
                    case 1:
                        canGo[1]=canGo[0]= false;
                        break;
                    case 2:
                        if ( Ye - Yb == this.radius ){
                            canGo[4]=canGo[0]=false;
                        }else{
                            canGo[4]=canGo[0]=canGo[2]= false;

                        }
                        break;
                    case 3:
                        canGo[3]=canGo[0]= false;
                        break;
                    case 4:
                        if ( Xe - Xb == this.radius ){
                            canGo[4]=canGo[3]=false;
                        }else{
                            canGo[4]=canGo[1]=canGo[3]= false;
                        }
                        break;
                    case 5:
                        canGo[2]=canGo[3]= false;
                        break;
                    case 6:
                        if ( Yb - Ye == this.radius ){
                            canGo[4]=canGo[2]=false;
                        }else{
                            canGo[4]=canGo[0]=canGo[2]= false;
                        }
                        break;
                    case 7:
                        canGo[1]=canGo[2]= false;
                        break;
                }
            }

        }
        for ( int k =0  ; k < canGo.length ; k++){
            if ( canGo[k]==true ) {
                if ( k == 4 ){
                    way.add(-1); // chuyển 4 là -1
                }
                else{
                    way.add(k);
                }

            }
        }
        // nếu ko có nguy  hiểm
        if ( thread == 0 ){
            //  return -1;

            int vertical = random.nextInt(2);
            if(vertical == 1) {
                int v = calculateRowDirection();
                if(v != -1)
                    return v;
                else
                    return calculateColDirection();

            } else {
                int h = calculateColDirection();

                if(h != -1)
                    return h;
                else
                    return calculateRowDirection();
            }
        }

        // trường hợp không có đường đi hợp lý
        // thì sẽ cho ramdo bừa
        if ( way.size() == 0 ){
            return random.nextInt(4);
        }
        // tồn tạ đường duy nhất
        if ( way.size() == 1){
            //  System.out.println("di theo huong " + way.get(0) );
            return way.get(0);
        }


        return way.get(random.nextInt(way.size()));
    }*/
}