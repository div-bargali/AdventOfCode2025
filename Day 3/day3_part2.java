import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        long sum = 0;
        
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String largestNumber = getLargestKDigitNumber(line, 12);
                    sum += Long.parseLong(largestNumber);
                }
            }
        } catch (IOException e) {
            System.err.println("Error while opening file: " + e.getMessage());
        }
        
        System.out.println("Total Sum: " + sum);
    }
    
    private static String getLargestKDigitNumber(String num, int k) {
        int n = num.length();
        int digitsToRemove = n - k;
        
        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < n; i++) {
            char digit = num.charAt(i);
            
            while (!stack.isEmpty() && 
                   stack.peek() < digit && 
                   digitsToRemove > 0) {
                stack.pop();
                digitsToRemove--;
            }
            
            stack.push(digit);
        }

        while (digitsToRemove > 0 && !stack.isEmpty()) {
            stack.pop();
            digitsToRemove--;
        }
        
        StringBuilder result = new StringBuilder();
        for (char c : stack) {
            result.append(c);
        }
        
        return result.toString();
    }
}
