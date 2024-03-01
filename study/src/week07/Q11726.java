package week07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *  [ 효율성 ]
 *  - 메모리: 14412 KB
 *  - 시간 : 124 ms
 */
public class Q11726 {

    static int[] dp; // DP 테이블

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // DP 배열 초기화
        dp = new int[N + 1];
        dp[0] = dp[1] = 1; // 초기값 설정

        // 타일링 수 계산 및 출력
        System.out.println(tileCount(N));
    }

    // 타일링 수를 구하는 재귀 메소드
    private static int tileCount(int n) {
        // 이미 계산한 값이 있다면 그 값을 반환
        if (dp[n] > 0) return dp[n];

        // 이전 두 값의 합을 구하고 10007로 나눈 나머지를 저장
        dp[n] = (tileCount(n - 1) + tileCount(n - 2)) % 10007;

        // 구한 값을 반환
        return dp[n];
    }
}
