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
            
            // Read ranges
            while ((line = reader.readLine()) != null) {
                line = line.stripLeading();
                ArrayList<String> row = new ArrayList<>(Arrays.asList(line.split("\\s+")));
                num.add(row);
            }

            
            int n = num.size();
            int m = num.get(0).size();
            
            // for (int i = 0; i < n; i++) {
            //     for (int j = 0; j < m; j++) {
            //         System.out.print(num.get(i).get(j) + " ");
            //     }
            //     System.out.println();
            // }
            
            
            for (int j = 0; j < m; j++) {
                long colOp = 0;
                if (num.get(n-1).get(j).equals("+")) {
                    for (int i = 0; i < n-1; i++) {
                        colOp += Long.parseLong(num.get(i).get(j));
                    }
                } else if (num.get(n-1).get(j).equals("*")) {
                    colOp = 1;
                    for (int i = 0; i < n-1; i++) {
                        colOp *= Long.parseLong(num.get(i).get(j));
                    }
                }
                sum += colOp;
            }
            
            
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        System.out.println("Total Sum: " + sum);
    }
}
