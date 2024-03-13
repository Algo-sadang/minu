package week09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *  [ 효율성 ]
 *  - 메모리: 127312 KB
 *  - 시간 : 1360 ms
 */
public class Q16401 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // M: 스넥을 받을 수 있는 사람의 수, N: 스넥의 개수
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        // 각 스넥의 길이를 저장하는 배열 초기화
        int[] snack = new int[N];

        // 스넥의 길이를 입력받아 배열에 저장
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            snack[i] = Integer.parseInt(st.nextToken());
        }

        // 스넥의 길이를 오름차순으로 정렬
        Arrays.sort(snack);

        // 이분 탐색을 위한 left와 right 설정
        int left = 1;
        int right = snack[N - 1];

        // 이분 탐색 시작
        while(left <= right){
            int mid = (left + right) / 2; // 중간값 계산

            int cnt = 0; // 스넥을 나눠줄 수 있는 사람의 수를 세는 변수

            // 각 스넥의 길이를 순회하며 mid보다 크거나 같은 스넥의 개수를 구함
            for(int i = 0; i < N; i++){
                if(snack[i] >= mid){
                    cnt += snack[i] / mid; // 현재 스넥에서 mid 길이의 스넥이 몇 개 만들어지는지 더함
                }
            }

            // 만들어진 스넥의 수가 M보다 크거나 같으면 left를 늘려 스넥의 길이를 더 늘릴 수 있도록 함
            if(cnt >= M){
                left = mid + 1;
            }
            else{
                // 그렇지 않으면 right를 줄여 스넥의 길이를 줄임
                right = mid - 1;
            }
        }

        // 최대 길이를 출력
        System.out.println(right);
    }


}
