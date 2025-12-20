import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        long sum = 0;
        ArrayList<ArrayList<String>> num = new ArrayList<ArrayList<String>>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            
            while ((line = reader.readLine()) != null) {
                ArrayList<String> row = new ArrayList<>(Arrays.asList(line.split("")));
                num.add(row);
            }

            int n = num.size();
            int m = num.get(0).size();
            
            // Fill in missing operators (propagate from right to left)
            String leftOp = " ";
            for (int j = 0; j < m; j++) {
                if (!num.get(n-1).get(j).equals("*") && !num.get(n-1).get(j).equals("+")) {
                    num.get(n-1).set(j, leftOp);
                } else {
                    leftOp = num.get(n-1).get(j);
                }
            }
            
            // Process right-to-left
            Long colOp = null;
            
            for (int j = m - 1; j >= 0; j--) {
                String op = num.get(n-1).get(j);
                
                // Extract number from column
                StringBuilder numStr = new StringBuilder();
                for (int i = 0; i < n-1; i++) {
                    if (!num.get(i).get(j).equals(" ")) {
                        numStr.append(num.get(i).get(j));
                    }
                }
                
                // If empty column (separator between problems)
                if (numStr.length() == 0) {
                    if (colOp != null) {
                        // System.out.println(colOp);
                        sum += colOp;
                        colOp = null;
                    }
                } else {
                    long number = Long.parseLong(numStr.toString());
                    // System.out.println(number);
                    
                    if (colOp == null) {
                        // First number in this problem
                        colOp = number;
                    } else {
                        // Apply the operator
                        if (op.equals("+")) {
                            colOp += number;
                        } else if (op.equals("*")) {
                            colOp *= number;
                        }
                    }
                }
            }
            
            // // Add the last problem result
            if (colOp != null) {
                sum += colOp;
            }
            
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        System.out.println("Total Sum: " + sum);
    }
}
