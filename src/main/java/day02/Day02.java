package day02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day02 {

    public static void main(String[] args) throws FileNotFoundException {
        
        File input = new File("src/main/java/day02/test.dat");
        Scanner reader = new Scanner(input);

        long safeCountP1 = 0;
        long safeCountP2 = 0;
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            List<Integer> levels = Arrays.stream(line.split("\\s+")).map(Integer::parseInt).toList();
            // if (testLevels(levels, false)) {
            //     safeCountP1++;
            // }
            if (testLevels(levels, true)) {
                safeCountP2++;
            } else {
                System.out.println(levels);
            }
        }
        reader.close();

        // System.out.println(safeCountP1);
        System.out.println(safeCountP2);
    }

    private static boolean testLevels(List<Integer> levels, boolean canTolerateBadLevel) {
        if (levels.size() < 2) {
            return true;
        }
        int delta = levels.get(0) - levels.get(1);
        if (delta > 0) {
            return testLevelsDecreasing(levels, canTolerateBadLevel);
        } else if (delta < 0) {
            return testLevelsIncreasing(levels, canTolerateBadLevel);
        } else if (canTolerateBadLevel) {
            return testLevels(levels.subList(1, levels.size()), false);
        }
        return false;
    }

    private static boolean testLevelsDecreasing(List<Integer> levels, boolean canTolerateBadLevel) {
        if (levels.size() < 2) {
            return true;
        }
        int delta = levels.get(0) - levels.get(1);
        if (delta > 0 && delta <= 3) {
            return testLevelsDecreasing(levels.subList(1, levels.size()), canTolerateBadLevel);
        } else if (canTolerateBadLevel) {
            return testLevelsDecreasing(levels.subList(1, levels.size()), false);
        }
        return false;
    }

    private static boolean testLevelsIncreasing(List<Integer> levels, boolean canTolerateBadLevel) {
        if (levels.size() < 2) {
            return true;
        }
        int delta = levels.get(1) - levels.get(0);
        if (delta > 0 && delta <= 3) {
            return testLevelsIncreasing(levels.subList(1, levels.size()), canTolerateBadLevel);
        } else if (canTolerateBadLevel) {
            return testLevelsIncreasing(levels.subList(1, levels.size()), false);
        }
        return false;
    }
}
