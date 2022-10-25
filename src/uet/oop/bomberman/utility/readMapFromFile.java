package uet.oop.bomberman.utility;

import uet.oop.bomberman.BombermanGame;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
public class readMapFromFile {
    private static ArrayList<String> res = new ArrayList<>();
    public static ArrayList<String> readMapFromFile() {
        String str = null;
        File file = new File("res/levels/Level1.txt");
        int cnt = 0;
        try {
            Scanner sc = new Scanner(file);
            sc.nextLine();
            while (cnt < BombermanGame.HEIGHT) {
                str = sc.nextLine();
                res.add(str);
                cnt++;
            }
            //file.close()
        } catch (Exception e) {
            e.getMessage(); // lấy lỗi
        }
        return res;
    }

/*    public static void main(String[] args) {
        ArrayList<String> map = new ArrayList<>();
        map = readMapFromFile();
        for (int cnt = 0; cnt < map.size(); cnt++){
            System.out.println(map.get(cnt));
        }
    }*/
}


