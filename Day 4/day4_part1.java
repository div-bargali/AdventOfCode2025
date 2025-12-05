import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;

public class Main {
    static int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1}; 
    static int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};
    
    public static void main(String[] args) {
        int sum = 0, curAns = 0;
        ArrayList<ArrayList<String>> arr = new ArrayList<ArrayList<String>>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    ArrayList<String> ar = new ArrayList<>(Arrays.asList(line.split("")));
                    arr.add(ar);
                }
            }
            while (true) {
                curAns = findPaperRollsTwo(arr);
                sum += curAns;
                if (curAns == 0) {
                    break;
                }
            }
            
        } catch (IOException e) {
            System.err.println("Error while opening file: " + e.getMessage());
        }
        
        System.out.println("Total Sum: " + sum);
        // System.out.println("Rows: " + arr.size());
    }
    
    public static int findPaperRolls(ArrayList<ArrayList<String>> arr) {
        int n = arr.size();
        int m = arr.get(0).size();
        int ans = 0;
        
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++) {
                if (arr.get(i).get(j).equals("@")) {
                    int surround = 0;
                for (int k = 0; k < 8; k++) {
                    int x = i + dx[k];
                    int y = j + dy[k];
                    if (x >= 0 && x < n && y >= 0 && y < m) {
                        if (arr.get(x).get(y).equals("@")) {
                            surround++;
                        }
                    }
                }
                if (surround < 4) {
                    ans++;
                }
                }
                
            }
        }
        return ans;
    }
    
    public static int findPaperRollsTwo(ArrayList<ArrayList<String>> arr) {
        int n = arr.size();
        int m = arr.get(0).size();
        int ans = 0;
        
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++) {
                if (arr.get(i).get(j).equals("@")) {
                    int surround = 0;
                for (int k = 0; k < 8; k++) {
                    int x = i + dx[k];
                    int y = j + dy[k];
                    if (x >= 0 && x < n && y >= 0 && y < m) {
                        if (arr.get(x).get(y).equals("@")) {
                            surround++;
                        }
                    }
                }
                if (surround < 4) {
                    ans++;
                    arr.get(i).set(j, ".");
                }
                }
                
            }
        }
        
        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < m; j++) {
        //         System.out.print(arr.get(i).get(j));
        //     }
        //     System.out.println();
        // }
        
        return ans;
    }
}
