package uet.oop.bomberman.AI;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import uet.oop.bomberman.utility.readMapFromFile;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.control.Move;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Enemy.Enemy;
import uet.oop.bomberman.entities.StaticEntity.Bomb;

public class AIBFS extends AI{
    private Bomber bomber;
    private Enemy enemy;

    public static ArrayList<String> map = readMapFromFile.readMapFromFile();

    public AIBFS(Bomber bomber, Enemy enemy) {
        this.bomber = bomber;
        this.enemy = enemy;
    }

    public ArrayList<Integer> shortestPath = new ArrayList();

    public int w = BombermanGame.WIDTH;
    public int h = BombermanGame.HEIGHT;
    public int countNode = w * h; // tổng số đỉnh

    /**
     * node: ma trận đỉnh kề
     * mỗi đỉnh có tối đa 4 cạnh kề
     * 0 là ko kề với đỉnh nào, node lưu đỉnh kề
     */

    public int[][] node = new int[countNode][4];
    public int[][] nodeMatrix = new int[1000][1000];

    public ArrayList<Integer> getShortestPath() {
        return shortestPath;
    }

    /**
     * đọc map từ file
     */
    /*public void readMapFromFile() {
        String str = null;
        File file = new File(this.filePath);
        int cnt = 0;

        try {
            Scanner sc = new Scanner(file);
            sc.nextLine();
            while (cnt < h) {
                str = sc.nextLine();
                map.add(str);
                cnt++;

            }

        } catch (Exception e) {
            e.getMessage(); // lấy lỗi
        }

        *//*for (cnt = 0; cnt < map.size(); cnt++){
            System.out.println(map.get(cnt));
        }*//*

    }*/

    /**
     * # -> 0: không đi được
     * * or x -> negative vertex: có thể đi đươc trong tương lai
     * còn lại là 1: đi được tương ứng với các đỉnh
     */

    public void convertToMatrix() {
        int vertex = 1;
        int i;
        for (i = 0; i < this.map.size(); i++) {
            for (int j = 0; j < this.map.get(i).length(); j++)

                if (this.map.get(i).charAt(j) == '#') {
                    this.nodeMatrix[i][j] = 0;

                } else if (this.map.get(i).charAt(j) == '*' || this.map.get(i).charAt(j) == 'x'
                        /*|| this.map.get(i).charAt(j) == 'b' || this.map.get(i).charAt(j) == '3'*/ ) {

                    this.nodeMatrix[i][j] = vertex * (-1);
                    vertex++;

                } else {
                    this.nodeMatrix[i][j] = vertex;
                    vertex++;
                }
        }

        //System.out.println(nodeMatrix[1][10]);
    }

    /**
     * từ ma trận đỉnh chuyển sang ma trân cạnh kề
     * 0: trai
     * 1: phai
     * 2: tren
     * 3: duoi
     */
    public void convertToNodeMatrix() {
        for (int i = 1; i < this.h-1; i++) {
            for (int j = 1; j < this.w-1; j++) {
                if (this.nodeMatrix[i][j] > 0) {
                    if (this.nodeMatrix[i][j - 1] > 0) {
                        this.node[this.nodeMatrix[i][j]][0] = this.nodeMatrix[i][j - 1];
                    } else {
                        this.node[this.nodeMatrix[i][j]][0] = 0; // không có đỉnh kể
                    }
                    //bên phải
                    if (this.nodeMatrix[i][j + 1] > 0) {
                        this.node[this.nodeMatrix[i][j]][1] = this.nodeMatrix[i][j + 1];
                    } else {
                        this.node[this.nodeMatrix[i][j]][1] = 0;
                    }

                    //cùng cột
                    //bên trên

                    if (this.nodeMatrix[i - 1][j] > 0) {
                        this.node[this.nodeMatrix[i][j]][2] = this.nodeMatrix[i - 1][j];
                    } else {
                        this.node[this.nodeMatrix[i][j]][2] = 0;
                    }

                    //bên dưới
                    if (this.nodeMatrix[i + 1][j] > 0) {
                        this.node[this.nodeMatrix[i][j]][3] = this.nodeMatrix[i + 1][j];
                    } else {
                        this.node[this.nodeMatrix[i][j]][3] = 0;
                    }

                }

            }
        }
        /*System.out.print("ke trai: "+node[nodeMatrix[1][9]][0]+" "+node[nodeMatrix[1][9]][1]+" "
                +node[nodeMatrix[1][9]][2]+" "+node[nodeMatrix[1][9]][3]);
        System.out.println();*/

    }

    public void updateDestroy_Brick(){
        //https://github.com/17021084/BomberMan_Base_NES/blob/master/src/uet/oop/bomberman/entities/character/enemy/ai/AIAdvance.java
    }

    public void updateMatrix() {
        int r = 1; // ban kinh vu no
        int enemyX = this.enemy.getX();
        int enemyY = this.enemy.getY();
        int bomberX = this.bomber.getX();
        int bomberY = this.bomber.getY();
        for (int i = 0; i < this.bomber.getBombs().size(); i++) {
            // tọa độ quả bom dang đc đặt
            int xt = this.bomber.getBombs().get(this.bomber.getBombs().size() - 1).getX();
            int yt = this.bomber.getBombs().get(this.bomber.getBombs().size() - 1).getY();
            // làm tạm mất đỉnh mà quả bom đang ở
            this.nodeMatrix[yt][xt] *= -1;
            // làm tạm mất các đỉnh trong vù ảnh hưởng của bom ( âm nó đi)
            // xét ngang
                //phải    && yt!=yb && xt+j != xb
            for (int j = 1; j <= r; j++) {
                if (this.nodeMatrix[yt][xt + j] > 0 && yt != enemyY && xt + j != enemyX) {
                    // tức là sẽ dùng việc bôi đen lại nếu enemy trong vùng ảnh hương
                    // dừng việc bôi đen khi găp người
                    this.nodeMatrix[yt][xt + j] *= -1;
                } else {
                    break;
                }
            }
            //trai
            for (int j = 1; j <= r; j++) {
                if (this.nodeMatrix[yt][xt - j] > 0 && yt != enemyY && xt - j != enemyX) {
                    // tức là sẽ dùng việc bôi đen lại nếu enemy trong vùng ảnh hương
                    // dừng việc bôi đen khi găp người
                    this.nodeMatrix[yt][xt - j] *= -1;
                } else {
                    break;
                }
            }
            // xet dọc
            //dưới
            for (int j = 1; j <= r; j++) {
                if (this.nodeMatrix[yt + j][xt] > 0 && yt + j != enemyY && xt != enemyX) {
                    // tức là sẽ dùng việc bôi đen lại nếu enemy trong vùng ảnh hương
                    // dừng việc bôi đen khi găp người

                    this.nodeMatrix[yt + j][xt] *= -1;
                } else {
                    break;
                }
            }
            //trên
            for (int j = 1; j <= r; j++) {
                if (this.nodeMatrix[yt - j][xt] > 0 && yt - j != enemyY && xt != enemyX) {
                    // tức là sẽ dùng việc bôi đen lại nếu enemy trong vùng ảnh hương
                    // dừng việc bôi đen khi găp người

                    this.nodeMatrix[yt - j][xt] *= -1;
                } else {
                    break;
                }
            }

        }
    }

    public int nextDirection(int start, int end) throws IllegalStateException { // exception khi queue full
        Queue<Integer> qNode = new LinkedList<Integer>();

        int[] parent = new int[countNode + 1];
        boolean[] visited = new boolean[countNode + 1];

        if ( start < 0 ) start *=-1;
        if ( end < 0 ) end *=-1;

        visited[start] = false;
        parent[start] = -1;
        parent[end] = -1;

        qNode.add(start);

        while (!qNode.isEmpty()) {
            int currentNode = qNode.poll(); // dequeue
            // duyệt toàn bộ đỉnh kể với current , nếu chưa visit thì  visited<-true
            for (int i = 0; i < 4; i++) {
                if (!visited[node[currentNode][i]] && node[currentNode][i] != 0) {
                    // dán nhãn đã thăm
                    visited[node[currentNode][i]] = true;
                    // gán đỉnh cha
                    parent[node[currentNode][i]] = currentNode;
                    // cho vào queue
                    qNode.add(node[currentNode][i]);

                }

            }
        }

        int p_end = parent[end];
        if (p_end != -1) {
            shortestPath.add(end);
            shortestPath.add(p_end);
            while (p_end != start) { //chưa đi đến gốc
                p_end = parent[p_end];
                shortestPath.add(p_end);
            }
            System.out.print("Duong di: ");
            for ( int i =0 ; i < shortestPath.size() ; i++ ){
                System.out.print(shortestPath.get(i)+ " ");
            }
            return shortestPath.get(shortestPath.size()-2);
        }
        return -1;
    }

    @Override
    public void nextMove(){
        this.convertToMatrix();
        this.updateMatrix();
        this.convertToNodeMatrix();
        System.out.println("enemy:"+ enemy.getY()/32+" "+enemy.getX()/32+" bomber:"+ bomber.getY()/32+" "+bomber.getX()/32);


        //System.out.println(nodeMatrix[3][22]);
        //System.out.println("Node cua bomber: "+nodeMatrix[this.bomber.getY()/32][this.bomber.getX()/32]);
        //int start = nodeMatrix[this.enemy.getY()/32][this.enemy.getX()/32];
        int start = nodeMatrix[this.enemy.getY()/32][this.enemy.getY()/32];
        int end = nodeMatrix[this.bomber.getY()/32][this.bomber.getX()/32];
        //System.out.println(start+" "+end);
        int result = this.nextDirection(start, end);
        //System.out.println("dg di:");
        //System.out.println(start+" "+result);
        if (result - start == 1) Move.right(enemy);
        if (start - result == 1) Move.left(enemy);
        if (start > result) Move.up(enemy);
        if (start < result) Move.down(enemy);

    }

}