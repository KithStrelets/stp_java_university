
import java.util.Arrays;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;
/**
 *
 * @author Никита
 */
public class Lab2 {

    static Scanner sc = new Scanner(System.in); 
    public static void main(String[] args) {
        // TODO code application logic here
        part1();
        part2();
    }
    static void part1(){
        System.out.println("Enter the size of the array: "); 
        int[] arr = new int[checkedinput()]; 
        System.out.println("Enter the numbers of the array: ");
                for (int a = 0; a < arr.length; a++) {                           
                        arr[a] = checkedinput();  
                }
        System.out.println("There are "+((arr.length))+ " numbers in array" + "\nArray is: " + Arrays.toString(arr));
        Arrays.sort(arr);
        ArrayList<Integer> pair = new ArrayList<Integer>();
        int arraysum = 0, minmaxsum = 0, max = arr[arr.length - 1], min = arr[0];
        for(int a = 0; a < arr.length; a++)
        {
            if(arr[a]%2 == 0){
            pair.add(arr[a]);
            }
            arraysum += arr[a];
            if(arr[a] == min | arr[a] == max){
                minmaxsum += arr[a];
            }
        }
        int res = (Math.abs(min)>max)?min:max;
        System.out.println("Number of paired elems: " + pair.size() 
                + "\n3x multiplied sum: " + arraysum*3
                + "\nDif between min and max: " + Math.subtractExact(max,min)
                + "\nArithmetic mean: " + (arraysum/arr.length)
                + "\nSum of the largest and smallest numbers: " + minmaxsum
                + "\nMaximum by module element of the array: " + res);         
    }
    
    static void part2(){
        System.out.println("\n\nType in the range of numbers in matrix: \nMinimum: ");
        int min = checkedinput();
        System.out.println("\nMaximum: ");
        int max = checkedinput();
        System.out.println("\nType in the order N of the square matrix: ");
        int rang = checkedinput();
        Random rnd = new Random();        
        int[][] mat = new int[rang][rang];
        int[] vectorA = new int[rang], vectorB = new int[rang];
        ArrayList<Integer> vectorZero = new ArrayList<>(), vectorSameNum = new ArrayList<>();
        boolean checkedA = false, checkedB = false, zeroRow = true, sameNum = true;
        for(int i = 0; i < rang; i++){
            checkedA = checkedB = false;
            int boofer = 0;
            for (int j = 0; j < rang; j++) {
                mat[i][j] = rnd.nextInt(max-min) + min;
                System.out.print("\t" + mat[i][j]);
                if(!checkedA){                    
                    if(j == rang - 1 & mat[i][j] < 0){
                        vectorA[i] = 0;
                        checkedA = true;
                    }
                    if(j != rang - 1 & mat[i][j] < 0){
//                        for (int k = 0; k < j; k++) {
//                            vectorA[i] += mat[i][k];
//                        }
                        boofer = j;
                        checkedA =  true;
                    }
                }
                if(sameNum){
                    if(j != 0 && mat[i][j] != mat[i][j-1]){
                        if(mat[i][j] != 0){
                            zeroRow = false;
                        }
                        sameNum = false;
                    }                    
                }
            }
            System.out.println();
            if(!checkedA) vectorA[i] = 100;  
            else{
                for (int k = boofer + 1; k < rang; k++) {
                    vectorA[i] += mat[i][k];
                }
            }
            
            if(sameNum){
                if(zeroRow){
                    vectorZero.add(i);
                }
                vectorSameNum.add(i);
            }
            
            if(!checkedB){
                boolean checkedInside = false;
                for(int m = rang-1; m >= 0; m--){
                    if(checkedInside){
                        vectorB[i] += mat[i][m];                         
                    }
                    else{
                        if(mat[i][m] < 0) checkedInside = true;
                    }                  
                }
                if(!checkedInside)vectorB[i] = -1;
            }
        }
        System.out.println("Sum after the first negative: " + Arrays.toString(vectorA));
        System.out.println("Sum before the last negative: " + Arrays.toString(vectorB));
        System.out.println("Rows with all zero numbers: " + Arrays.toString(vectorZero.toArray()));
        System.out.println("Rows with all the same numbers: " + Arrays.toString(vectorSameNum.toArray()));
    }
    
    static int checkedinput(){
        int input;
        while(true){
        try{
            input = sc.nextInt();
            break;
        }
        catch(InputMismatchException | NegativeArraySizeException e){
            System.out.println("Input mistake, type another one:");
            checkedinput();
        }
        }
        return input;
    }
}
    

