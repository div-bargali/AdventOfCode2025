// Solution for part 2 of Day 1's problem
// Problem link - https://adventofcode.com/2025/day/1
// Brief Description - Count number of times circular dial numbered 0 to 99 crosses zero, when rotated clockwise or anti-clockwise

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

public class Main
{
    public static boolean isInvalid(String number) {
        if (number.length() % 2 != 0) {
            return false;
        }
        int mid = number.length() / 2;
        for (int i = 0; i < mid; i++) {
            if (number.charAt(i) != number.charAt(mid + i)) {
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        long sum = 0;
        
        
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] rangePairs = line.split(",");
                    
                    for (String pair: rangePairs) {
                        String[] bounds = pair.split("-");
                        long start = Long.parseLong(bounds[0]);
                        long end = Long.parseLong(bounds[1]);
                        for (long i = start; i <= end; i++) {
                            String numStr = String.valueOf(i);
                            if (isInvalid(numStr)) {
                                sum += i;
                            }
                        }
                    }
                    
                }
            }
        } catch (IOException e) {
            System.err.println("Error while opening file");
        }
        // 30323879646
        System.out.println("Invalid Sum: " + sum);
        
    }
}
