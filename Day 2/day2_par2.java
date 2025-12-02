// Corrected code with all errors fixed
// Finds numbers with repeating digit patterns and sums them

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main
{
    public static boolean isRepeating(String curr, String number) {
        int winLen = curr.length();
        if (winLen == 0) {
            return false;
        }
        
        for (int j = 0; j < number.length();) {
            for (int k = 0; k < winLen; k++) {
                // Check if we've gone past the end of number
                if (j + k >= number.length()) {
                    return false;
                }
                
                if (curr.charAt(k) != number.charAt(j + k)) {
                    return false;
                }
            }
            j = j + winLen;
        }
        return true;
    } 
    
    public static boolean isInvalid(String number) {
        int len = number.length();
        for (int i = 0; i < len; i++) {
            String curr = number.substring(0, i+1);
            String rem = number.substring(i+1);
            
            if (!curr.equals(number) && !rem.isEmpty() && isRepeating(curr, rem)) {
                // System.out.println("isRepeating: " + curr + " in: " + number + " rem: " + rem);
                return true;
            }
        }
        return false;
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
            System.err.println("Error while opening file: " + e.getMessage());
        }
        
        System.out.println("Invalid Sum: " + sum);
    }
}
