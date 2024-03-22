package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Q21967 {

    static int[] Num = new int[11];
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 첫 번째 입력값은 N을 받음
        Deque<Integer> Q = new ArrayDeque<>(); // Deque 이용한 큐 선언

        int result = 0; // 결과값을 저장할 변수 초기화
        st = new StringTokenizer(br.readLine()); // 새로운 StringTokenizer를 생성하여 다음 줄을 처리
        for (int i = 0; i < N; i++) { // N번 반복
            int M = Integer.parseInt(st.nextToken()); // 다음 숫자 입력 받음
            Num[M]++; // 해당 숫자의 개수를 증가시킴
            Q.push(M); // 큐에 해당 숫자를 삽입

            while (check() > 2 && !Q.isEmpty()) { // 차이가 2보다 크면서 큐가 비어있지 않을 때까지 반복
                int b = Q.peek(); // 큐의 맨 앞 숫자를 가져옴
                Q.pop(); // 큐에서 해당 숫자 제거
                Num[b]--; // 해당 숫자의 개수를 감소시킴
            }
            result = Math.max(result, Q.size()); // 현재 결과값과 큐의 크기 중 큰 값을 결과값으로 갱신
        }
        System.out.println(result); // 결과값 출력
    }

    // 배열을 순회하며 최소값과 최대값의 차이를 반환하는 함수
    public static int check() {
        int a = 0;
        for (int i = 1; i <= 10; i++) {
            if (Num[i] > 0) { // 해당 숫자가 0보다 크면
                a = i; // 최소값 갱신
                break;
            }
        }
        int b = 0;
        for (int i = 10; i >= 1; i--) {
            if (Num[i] > 0) { // 해당 숫자가 0보다 크면
                b = i; // 최대값 갱신
                break;
            }
        }
        return b - a; // 최대값과 최소값의 차이 반환
    }
}
