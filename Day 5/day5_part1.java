import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Range {
    long start, end;
    Range(long s, long e) { start = s; end = e; }
    
    boolean contains(long num) {
        return num >= start && num <= end;
    }
}

public class Main {
    public static void main(String[] args) {
        long sum = 0;
        List<Range> ranges = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    break;
                }
                if (line.contains("-")) {
                    String[] parts = line.split("-");
                    long l = Long.parseLong(parts[0].trim());
                    long r = Long.parseLong(parts[1].trim());
                    ranges.add(new Range(l, r));
                } else {
                    break;
                }
            }
            
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    long num = Long.parseLong(line.trim());
                    System.out.println(num);
                    
                    for (Range range : ranges) {
                        if (range.contains(num)) {
                            sum++;
                            break;
                        }
                    }
                }
            }
            
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        System.out.println("Total Sum: " + sum);
    }
}
