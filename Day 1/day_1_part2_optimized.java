// Used maths to count number of factors of 100 in the range [pos+1, pos+n] or [pos-1-n, pos-1]
// n = number of steps in left or right direction

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main
{
    public static void main(String[] args) {
        int position = 50;
        int passZeroCount = 0;
        
        
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    char dir = line.charAt(0);
                    int steps = Integer.parseInt(line.substring(1));
                    
                    if (dir == 'L') {
                        // floor division because of negative numbers
                        passZeroCount += Math.floorDiv(position-1, 100) - Math.floorDiv(position-1-steps, 100);
                        position -= steps;
                        position = (position % 100 + 100) % 100;
                    } else if (dir == 'R') {
                        passZeroCount += Math.floorDiv(position+steps, 100) - Math.floorDiv(position, 100);
                        position += steps;
                        position %= 100;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error while opening file");
        }
        
        System.out.println(passZeroCount + " times passed/at zero");
        
    }
}
