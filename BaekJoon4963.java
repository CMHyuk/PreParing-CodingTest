package study;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BaekJoon4963 {

    static int width;
    static int height;
    static int nowX;
    static int nowY;
    static int[][] square;
    static int dirX[] = {0, 0, -1 ,1, -1, 1, -1, 1}; // 상 하 좌 우 대각선 상좌, 상우, 하좌, 하우
    static int dirY[] = {-1, 1, 0, 0, 1, 1, -1, -1}; // 상 하 좌 우 대각선 상좌, 상우, 하좌, 하우
    static List<Integer> answers = new ArrayList<>();
    static boolean[][] check;
    static int cnt;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        width = sc.nextInt();
        height = sc.nextInt();

        //입력(너비 높이 모두 0이면 종료)
        while (width != 0 && height != 0) {

            square = new int[height][width];
            check = new boolean[height][width];

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    square[i][j] = sc.nextInt();
                }
            }

            //섬의 개수 계산
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (square[i][j] == 1 && !check[i][j]) {
                        cnt++;
                        countIsland(i, j);
                    }
                }
            }
            addAndClear();

            //하나의 정사각형 계산 끝나면 다시 입력
            width = sc.nextInt();
            height = sc.nextInt();
        }

        //출력
        for (int answer : answers) {
            System.out.println(answer);
        }
    }

    private static void addAndClear() {
        answers.add(cnt);
        cnt = 0;
    }

    static void countIsland(int a, int b) {

        check[a][b] = true;

        for(int i = 0; i < 8; i++) {
            nowX = dirX[i] + a;
            nowY = dirY[i] + b;

            if(range_check() && !check[nowX][nowY] && square[nowX][nowY] == 1) {
                countIsland(nowX, nowY);
            }
        }
    }

    static boolean range_check() {
        return (nowX >= 0 && nowY >= 0 && nowX < height && nowY < width);
    }
}
