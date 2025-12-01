// Solution for part 2 of Day 1's problem
// Problem link - https://adventofcode.com/2025/day/1
// Brief Description - Count number of times circular dial numbered 0 to 99 crosses zero, when rotated clockwise or anti-clockwise

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main
{
    public static void main(String[] args) {
        int newPosition = 50;
        int oldPosition = 50;
        int passZeroCount = 0;
        
        
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    char dir = line.charAt(0);
                    int steps = Integer.parseInt(line.substring(1));
                    
                    passZeroCount += (steps / 100);
                    steps %= 100;
                    
                    if (steps > 0) {
                        if (dir == 'L') {
                            newPosition -= steps;
                        } else if (dir == 'R') {
                            newPosition += steps;
                        }

                        // handle negative positions and normalize to range [0-99]
                        newPosition = (newPosition % 100 + 100) % 100;
                        
                        if (newPosition == 0 && oldPosition != 0) {
                            passZeroCount++;
                        } else if (oldPosition != 0 && newPosition > oldPosition && dir == 'L') {
                            passZeroCount++;
                        } else if (oldPosition != 0 && newPosition < oldPosition && dir == 'R') {
                            passZeroCount++;
                        }
                    }
                    oldPosition = newPosition;
                }
            }
        } catch (IOException e) {
            System.err.println("Error while opening file");
        }
        
        System.out.println(passZeroCount + " times passed/at zero");
        
    }
}
