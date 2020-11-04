import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    // 1大于，0等于，-1小于，2为空值，3位结束
    public static int[][] priority = {
            {1, -1, -1, 1, -1, 1, 2},
            {1, 1, -1, 1, -1, 1, 2},
            {-1, -1, -1, 0, -1, 1, 2},
            {1, 1, 2, 1, 2, 1, 2},
            {1, 1, 2, 1, 2, 1, 2},
            {-1, -1, -1, -1, -1, 3, 2}
    };

    public static Stack<Character> N = new Stack<Character>();
    public static Stack<Character> T = new Stack<Character>();

    public static int getIndex(char in) {
        switch (in) {
            case '+':
                return 0;
            case '*':
                return 1;
            case '(':
                return 2;
            case ')':
                return 3;
            case 'i':
                return 4;
            case '#':
                return 5;
            default:
                return 6;
        }
    }

    public static boolean getPriority(char in, char out) throws Exception {
        int i = getIndex(in);
        int j = getIndex(out);
        if (priority[i][j] <= 0)
            return true; // 允许入栈
        else if (priority[i][j] == 1)
            return false; // 开始规约分析
        else
            throw new Exception("E");
    }

    public static void reduce() throws Exception {
        char peek = T.pop();
        if (peek == 'i')
            N.push('N');
        else if (peek == '+' || peek == '*') {
            if (N.size() >= 2) {
                N.pop();
            } else
                throw new Exception("RE");
        } else if (peek == ')') {
            if (T.peek() == '(') {
                T.pop();
                N.pop();
            }
        }
    }

    public static void main(String[] args) throws IOException {
//        Scanner scan = new Scanner(System.in);
//        String input = scan.next();
        File filename = new File(args[0]);
        InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
        BufferedReader br = new BufferedReader(reader);
        String input = br.readLine();
        input = input + '#';
        T.push('#');
        for (int i = 0; i < input.length(); i++) {
            // 记得特判#号结束
            char cur = input.charAt(i);
            char peek = T.peek();
            if (cur == '#' && peek == '#')
                break;
            try {
                if (getPriority(peek, cur)) {
                    T.push(cur);
                    System.out.println("I" + cur);
                } else {
                    reduce();
                    System.out.println('R');
                    i--;
                }
            } catch (Exception e) {
                if (e.getMessage().equals("RE"))
                    System.out.println("RE");
                else
                    System.out.println("E");
                break;
            }

        }
    }
}
