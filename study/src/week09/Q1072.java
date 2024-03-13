package week09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *  [ 효율성 ]
 *  - 메모리: 17736 KB
 *  - 시간 : 208 ms
 */
public class Q1072 {

    static long X, Y, Z;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // X: 전체 게임 횟수, Y: 이긴 게임 횟수
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        // 현재 승률 계산
        Z = Y * 100 / X;

        // 승률이 99% 이상이면 더 이상 변화할 수 없으므로 -1 출력
        if (Z >= 99) {
            System.out.println(-1);
        }
        else {
            // 이분 탐색 시작
            binarySearch(1, X);
        }

    }

    private static void binarySearch(long start, long end) {
        long mid = 0, ratio = 0; // 중간값과 승률을 저장할 변수
        while (start <= end) {
            mid = (start + end) / 2; // 중간값 계산
            ratio = (Y + mid) * 100 / (X + mid); // mid만큼 게임을 더 플레이했을 때의 승률 계산

            // 승률이 기존 승률보다 높으면 end를 줄여 mid를 줄여 승률을 낮춤
            if (ratio > Z) {
                end = mid - 1;
            } else { // 그렇지 않으면 start를 늘려 mid를 늘려 승률을 높임
                start = mid + 1;
            }
        }
        // 최소 게임 횟수 출력
        System.out.println(start);
    }


}
