import java.util.ArrayList;
import java.util.List;

public class SalamanderSearch {
    public static void main(String[] args) {
        char[][] enclosure1 = {
            {'.','.','.','.','.','.'},
            {'W','.','W','W','.','.'},
            {'.','.','W','.','.','W'},
            {'f','W','.','.','W','.'},
            {'W','.','W','s','.','.'},
        };

        char[][] enclosure2 = {
            {'.','.','.','.','.','.'},
            {'W','W','W','W','s','.'},
            {'.','.','W','.','.','W'},
            {'f','W','.','.','W','.'},
            {'W','.','W','.','.','.'},
        };
    }

    /**
     * Returns whether a salamander can reach the food in an enclosure.
     * 
     * The enclosure is represented by a rectangular char[][] that contains
     * ONLY the following characters:
     * 
     * 's': represents the starting location of the salamander
     * 'f': represents the location of the food
     * 'W': represents a wall
     * '.': represents an empty space the salamander can walk through
     * 
     * The salamander can move one square at a time: up, down, left, or right.
     * It CANNOT move diagonally.
     * It CANNOT move off the edge of the enclosure.
     * It CANNOT move onto a wall.
     * 
     * This method should return true if there is any sequence of steps that
     * the salamander could take to reach food.
     * 
     * @param enclosure
     * @return whether the salamander can reach the food
     * @throws IllegalArgumentException if the enclosure does not contain a salamander
     */
    public static boolean canReach(char[][] enclosure) {
        boolean[][] visited = new boolean[enclosure.length][enclosure[0].length];
        return canReach(enclosure, salamanderLocation(enclosure), visited);
    }
    public static boolean canReach(char[][] enclosure, int[] loc, boolean[][] seen) {
        int curR = loc[0];
        int curC = loc[1];
        if(enclosure[curR][curC] == 'f')return true;
        if(seen[curR][curC]) return false;

        seen[curR][curC] = true;

        List<int[]> validLocs = possibleMoves(enclosure, loc);
        for(int[] move: validLocs){
            if(canReach(enclosure, move, seen)){
                return true;
            }
        }
        return false;
    }

    public static List<int[]> possibleMoves(char[][] enclosure, int[] location){
        int curR = location[0];
        int curC = location[1];

        List<int[]> validLocs = new ArrayList<>();


        //up
        int newR = curR-1;
        int newC = curC;
        if(newR >= 0 && enclosure[newR][newC] != 'W'){
            validLocs.add(new int[]{newR, newC});
        }
        //down
        newR = curR+1;
        newC = curC;
        if(newR < enclosure.length && enclosure[newR][newC] != 'W'){
            validLocs.add(new int[]{newR, newC});
        }
        //right
        newR = curR;
        newC = curC+1;
        if(newC < enclosure[0].length && enclosure[newR][newC] != 'W'){
            validLocs.add(new int[]{newR, newC});
        }
        //left
        newR = curR;
        newC = curC-1;
        if(newC >= 0 && enclosure[newR][newC] != 'W'){
            validLocs.add(new int[]{newR, newC});
        }
        return validLocs;
    }

    public static int[] salamanderLocation(char[][] enclosure){
        int count = 0;
        for (char[] arr : enclosure) {
            for(int i = 0 ; i < arr.length; i++){
                if(arr[i] == 's'){
                    return new int[]{count, i};
                }
            }
            count++;
        }

        throw new IllegalArgumentException("No salamander present");
    }
}
