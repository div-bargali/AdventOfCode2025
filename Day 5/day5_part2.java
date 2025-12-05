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
            
            Collections.sort(ranges, new Comparator<Range>() {
                @Override
                public int compare(Range r1, Range r2) {
                    return Long.compare(r1.end, r2.end);
                }
            });
            
            List<Range> merged = mergeRanges(ranges);
            
            for (Range r : merged) {
                sum += r.end - r.start + 1;
            }        
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        System.out.println("Total Sum: " + sum);
    }
    
    private static List<Range> mergeRanges(List<Range> ranges) {
        if (ranges.isEmpty()) {
            return ranges;
        }
        
        List<Range> merged = new ArrayList<>();
        Range current = ranges.get(0);
        
        for (int i = 1; i < ranges.size(); i++) {
            Range next = ranges.get(i);
            
            if (current.end >= next.start) {
                current.end = Math.max(current.end, next.end);
                current.start = Math.min(current.start, next.start);
            } else {
                merged.add(current);
                current = next;
            }
        }
        merged.add(current);
        return merged;
    }
}
