package study;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon2606 {

    static int n;
    static int m;

    static int[][] arr;
    static int cnt;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        arr = new int[n+1][n+1];

        for(int i = 0; i<m; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            arr[x][y] = arr[y][x] = 1;
        }
        bw.write(virus() + "");
        bw.flush();
        bw.close();
    }

    static int virus() {

        boolean[] check = new boolean[n+1];
        Queue<Integer> q = new LinkedList<>();
        q.add(1);

        while(!q.isEmpty()) {

            int a = q.poll();
            check[a] = true;

            for(int i = 1; i<arr.length; i++) {
                if(arr[i][a] == 1 && check[i] == false) {
                    check[i] = true;
                    q.add(i);
                    cnt++;
                }
            }
        }
        return cnt;
    }

}
