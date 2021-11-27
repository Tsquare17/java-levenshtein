package com.trevorthompson;

import java.util.Arrays;
import java.util.Locale;

public class Levenshtein {
    private boolean ignoreCapitalization = true;

    public int getDistance(String a, String b) {
        return levenshtein(a, b, -1, Integer.MAX_VALUE);
    }

    public int getDistance(String a, String b, int maxDistance, int maxCheckLength) {
        return levenshtein(a, b, maxDistance, maxCheckLength);
    }

    public int getDistance(String a, String b, int maxDistance) {
        return levenshtein(a, b, maxDistance, Integer.MAX_VALUE);
    }

    private int levenshtein(String a, String b, int maxDistance, int maxCheckLength) {
        if (this.ignoreCapitalization) {
            a = a.toLowerCase(Locale.ROOT);
            b = b.toLowerCase(Locale.ROOT);
        }

        if (a.equals(b)) {
            return 0;
        }

        int lengthA = a.length();
        int lengthB = b.length();

        int[][] cost = new int[lengthA + 1][lengthB + 1];

        int indexA = 0;
        int indexB = 0;

        for (int i = 0; i <= lengthA && i < maxCheckLength; i++) {
            for (int j = 0; j <= lengthB && j < maxCheckLength; j++) {
                if (i == 0) {
                    cost[i][j] = j;
                    continue;
                }

                if (j == 0) {
                    cost[i][j] = i;
                    continue;
                }

                int distance = min(
                        cost[i - 1][j] + 1,
                        cost[i][j - 1] + 1,
                        cost[i - 1][j - 1] + (a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1)
                );

                cost[i][j] = distance;

                if (maxDistance == distance) {
                    indexB = j;
                    break;
                }
            }

            if (maxDistance == cost[i][indexB]) {
                indexA = i;
                break;
            }
        }

        return (maxDistance < 0 || maxDistance > lengthA || maxDistance > lengthB)
                ? cost[lengthA][lengthB]
                : cost[indexA][indexB];
    }

    private int min(int... numbers) {
        return Arrays.stream(numbers).min().orElse(0);
    }

    public void setIgnoreCapitalization(boolean ignoreCapitalization) {
        this.ignoreCapitalization = ignoreCapitalization;
    }
}
