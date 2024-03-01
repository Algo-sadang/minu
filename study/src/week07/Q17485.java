package week07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *  [ 효율성 ]
 *  - 메모리: 108820 KB
 *  - 시간 : 728 ms
 */
public class Q17485 {

    static int n,m;
    static int fuel[][];
    // 0: ↘
    // 1: ↙
    // 2: ↓
    static int[][][] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 받기
        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);  // 행 수
        m = Integer.parseInt(temp[1]);  // 열 수
        fuel = new int[n][m];           // 연료량 저장 배열
        dp = new int[3][n][m];          // DP 테이블

        // 연료량 입력 받기
        for(int i=0; i<n; i++) {
            String[] temp2 = br.readLine().split(" ");
            for(int j=0; j<m; j++) {
                fuel[i][j] = Integer.parseInt(temp2[j]);
            }
        }

        // 초기화
        for(int i=0; i<m; i++){
            dp[0][0][i] = fuel[0][i];  // 첫 번째 행의 ↘ 방향으로의 이동 최소 비용
            dp[1][0][i] = fuel[0][i];  // 첫 번째 행의 ↙ 방향으로의 이동 최소 비용
            dp[2][0][i] = fuel[0][i];  // 첫 번째 행의 ↓ 방향으로의 이동 최소 비용
        }

        // 테두리 값은 갈 수 없는 방향으로 초기화
        for(int i=0; i<n; i++){
            // 왼쪽 끝인데 왼쪽 대각선 방향에서 오는 경우는 없으므로 최대값으로 초기화
            dp[0][i][0] = Integer.MAX_VALUE;
            // 오른쪽 끝인데 오른쪽 대각선 방향에서 오는 경우는 없으므로 최대값으로 초기화
            dp[2][i][m-1] = Integer.MAX_VALUE;
        }

        // DP 계산
        for(int i=1; i<n; i++){
            for(int j=0; j<m; j++){
                // 왼쪽 방향, 오른쪽 방향 모두에서 올 수 있는 경우
                if(isValidPosition(j-1) && isValidPosition(j+1)){
                    dp[0][i][j] = Math.min(dp[1][i-1][j-1], dp[2][i-1][j-1]) + fuel[i][j];  // ↘ 방향에서 올 때 최소 비용
                    dp[1][i][j] = Math.min(dp[0][i-1][j], dp[2][i-1][j]) + fuel[i][j];      // ↙ 방향에서 올 때 최소 비용
                    dp[2][i][j] = Math.min(dp[0][i-1][j+1], dp[1][i-1][j+1]) + fuel[i][j];  // ↓ 방향에서 올 때 최소 비용
                }
                // 오른쪽 끝에 있는 경우
                else if(!isValidPosition(j-1) && isValidPosition(j+1)){
                    dp[1][i][j] = Math.min(dp[0][i-1][j], dp[2][i-1][j]) + fuel[i][j];      // ↙ 방향에서 올 때 최소 비용
                    dp[2][i][j] = Math.min(dp[0][i-1][j+1], dp[1][i-1][j+1]) + fuel[i][j];  // ↓ 방향에서 올 때 최소 비용
                }
                // 왼쪽 끝에 있는 경우
                else if(isValidPosition(j-1) && !isValidPosition(j+1)){
                    dp[0][i][j] = Math.min(dp[1][i-1][j-1], dp[2][i-1][j-1]) + fuel[i][j];  // ↘ 방향에서 올 때 최소 비용
                    dp[1][i][j] = Math.min(dp[0][i-1][j], dp[2][i-1][j]) + fuel[i][j];      // ↙ 방향에서 올 때 최소 비용
                }
            }
        }

        // 마지막 행에서 최소값 찾기
        int min = Integer.MAX_VALUE;
        for(int i=0; i<m; i++){
            for(int j=0; j<3; j++){
                min = min > dp[j][n-1][i] ? dp[j][n-1][i] : min;
            }
        }
        // 결과 출력
        System.out.println(min);
        br.close();
    }

    // 유효한 위치인지 확인하는 메소드
    public static boolean isValidPosition(int y) {
        if(y < 0 || y >= m)
            return false;
        return true;
    }
}
