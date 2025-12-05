import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class Main
{
    public static void main(String[] args) {
        int sum = 0;
        
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    Stack<String> s = new Stack<>();
                    int maxVolt = Integer.MIN_VALUE;
                    for (int i = 0; i < line.length(); i++) {
                        String num = String.valueOf(line.charAt(i));
                        if (s.empty()) {
                            s.push(num);
                        } else {
                            String cur = s.peek();
                            if (Integer.parseInt(num) > Integer.parseInt(cur)) {
                                s.pop(); 
                                s.push(num);
                                int volt = Integer.parseInt(cur + num);
                                maxVolt = Math.max(maxVolt, volt);
                            } else {
                                int volt = Integer.parseInt(cur + num);
                                maxVolt = Math.max(maxVolt, volt);
                            }
                        }
                    }
                    sum += maxVolt;
                }
            }
        } catch (IOException e) {
            System.err.println("Error while opening file: " + e.getMessage());
        }
        
        System.out.println("Total Sum: " + sum);
    }
}
