package day01;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Day01 {
    public static void main(String[] args) throws IOException {

        File input = new File("src/main/java/day01/input.dat");
        Scanner reader = new Scanner(input);

        List<Long> list1 = new ArrayList<Long>();
        List<Long> list2 = new ArrayList<Long>();
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            String[] ids = line.split("\\s+");
            list1.add(Long.parseLong(ids[0]));
            list2.add(Long.parseLong(ids[1]));
        }

        reader.close();

        Long[] sortedList1 = list1.stream().sorted().toArray(Long[]::new);
        Long[] sortedList2 = list2.stream().sorted().toArray(Long[]::new);

        int ptr = 0;

        Long diff = 0l;
        while (ptr < sortedList1.length && ptr < sortedList2.length) {
            diff += Math.abs(sortedList1[ptr] - sortedList2[ptr]);
            ptr++;
        }

        System.out.println(diff);


        Map<Long, Long> list2Count = new HashMap<Long, Long>();
        for (int i=0; i<list2.size(); i++) {
            list2Count.put(list2.get(i), list2Count.getOrDefault(list2.get(i), 0l) + 1l);
        }

        Long similarityScore = 0l;
        for (int i=0; i<list1.size(); i++) {
            similarityScore += sortedList1[i] * list2Count.getOrDefault(sortedList1[i], 0l);
        }

        System.out.println(similarityScore);
    }
}
