/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Никита
 */
public class Lab3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int[] TEST = new int[150], example;
        Random rnd = new Random();
        Runtime runtime = Runtime.getRuntime();
        for(int a = 0; a < TEST.length; a++){
            TEST[a] = rnd.nextInt();
        }
        
        long startTime, usedMemoryBefore;
        int count = 3;
        while(count > 0){
            example = TEST.clone();
            startTime = System.nanoTime(); usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
            bubble(example);
            System.out.println("Bubble sort: nanosec:\t" 
                    + (System.nanoTime() - startTime) 
                    + " memory: " 
                    + (runtime.totalMemory() - runtime.freeMemory() - usedMemoryBefore));
            
            example = TEST.clone();
            startTime = System.nanoTime();usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
            insertion(example);
            System.out.println("Insertion sort: nanosec:" 
                    + (System.nanoTime() - startTime)
                    + " memory: " 
                    + (runtime.totalMemory() - runtime.freeMemory() - usedMemoryBefore));
            
            example = TEST.clone();
            startTime = System.nanoTime();usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
            quick(example, 0, example.length - 1);
            System.out.println("Quick sort: nanosec:" 
                    + (System.nanoTime() - startTime)
                    + " memory: " 
                    + (runtime.totalMemory() - runtime.freeMemory() - usedMemoryBefore));
            
            example = TEST.clone();
            startTime = System.nanoTime();usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
            cycle(example);
            System.out.println("Cycle sort: nanosec:" 
                    + (System.nanoTime() - startTime)
                    + " memory: " 
                    + (runtime.totalMemory() - runtime.freeMemory() - usedMemoryBefore));
            
            example = TEST.clone();
            startTime = System.nanoTime();usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
            shell(example);
            System.out.println("Shell sort: nanosec:" 
                    + (System.nanoTime() - startTime)
                    + " memory: " 
                    + (runtime.totalMemory() - runtime.freeMemory() - usedMemoryBefore));
            
            example = TEST.clone();
            startTime = System.nanoTime();usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
            Arrays.sort(example);
            System.out.println("Collection sort: nanosec:" 
                    + (System.nanoTime() - startTime)
                    + " memory: " 
                    + (runtime.totalMemory() - runtime.freeMemory() - usedMemoryBefore));
            
            example = TEST.clone();
            startTime = System.nanoTime();usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
            cocktail(example);
            System.out.println("Cocktail sort: nanosec:" 
                    + (System.nanoTime() - startTime)
                    + " memory: " 
                    + (runtime.totalMemory() - runtime.freeMemory() - usedMemoryBefore) 
                    + "\n");
            count--;
        }
    }

    static void bubble(int array[]){
    int n = array.length;
    boolean swapped;
    do{
        swapped = false;
        for(int i = 0; i < n; i++){
            if(i != n-1 && array[i] > array[i+1]){
                int temp = array[i];
                array[i] = array[i+1];
                array[i+1] = temp;
                swapped = true;
            }
        }
    }
    while (swapped);
  
    //System.out.println("Sorted list of numbers");   
    //System.out.println(Arrays.toString(array));     
    }
    
    static void insertion(int array[]){         
        int n = array.length; 
         
        for (int j = 1; j < n; j++) {           
            int extracted = array[j], i = j - 1;                          
            while ( (i >= 0) && ( array [i] > extracted ) ) {               
                array [i+1] = array [i]; 
                i--;                  
            }              
            array[i+1] = extracted;          
        }
        //System.out.println("Sorted array: \n" + Arrays.toString(array));
    }
    
    static int partition(int arr[], int low, int high)
    {
        int i = low-1;
        for (int j = low; j < high; j++)
        {
            if (arr[j] <= arr[high])
            {
            i++;
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            }
        }

        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;
        return i+1;
    }

    static void quick(int arr[], int low, int high)
    {
        if (low < high)
        {
        int mid = partition(arr, low, high);

        quick(arr, low, mid-1);
        quick(arr, mid+1, high);
        }
    }
    
    
    static void cycle(int array[]){
        int n = array.length;
        for (int cycleStart = 0; cycleStart < n; cycleStart++) {
            int value = array[cycleStart];
            int position = cycleStart;
            for (int i = cycleStart + 1; i < n; i++) if(array[i] < value) position++;
            if (position == cycleStart) continue;

            while (value == array[position]) position++;

            int temp = array[position];
            array[position] = value;
            value = temp;

            while (position != cycleStart) {
                position = cycleStart;
                for (int i = cycleStart + 1; i < n; i++) if(array[i] < value) position++;

                while (value == array[position]) position++;

                temp = array[position];
                array[position] = value;
                value = temp;
            }
        }
        //System.out.println("Sorted array cycle: \n" + Arrays.toString(array));
    }
    
    static void shell(int array[]){
        
        int n = array.length;
        
        for(int d = n / 2; d > 0; d /= 2){
            for(int i = d; i < n; i++){
                int temp = array[i], j;
                for(j = i; j >= d && array[j - d] > temp; j -= d){
                    array[j] = array[j - d];
                }
                array[j] = temp;
            }
        }        
        //System.out.println("lab3.Lab3.shell()" + Arrays.toString(array));
    }

    static void cocktail(int array[]){
        int n = array.length, start = 0, end = n - 1, temp;
        boolean swapped = true;
        
        while (swapped)
        {
        // reset the swapped flag on entering
        // the loop, because it might be true from
        // a previous iteration.
        swapped = false;
        for (int i = start; i < end; ++i)
        {
        if (array[i] > array[i + 1])
        {
        temp = array[i];
        array[i] = array[i + 1];
        array[i + 1] = temp;
        swapped = true;
        }
        }

        // if nothing moved, then array is sorted.
        if (!swapped) break;

        // otherwise, reset the swapped flag so that it
        // can be used in the next stage
        swapped = false;

        // move the end point back by one, because
        //  item at the end is in its rightful spot
        end--;

        // from right to left, doing the
        // same comparison as in the previous stage
        for (int i = end - 1; i >= start; --i)
        {
            if (array[i] > array[i + 1])
            {
                temp = array[i];
                array[i] = array[i + 1];
                array[i + 1] = temp;
                swapped = true;
            } 
        }

        // increase the starting point, because
        // the last stage would have moved the next
        // smallest number to its rightful spot.
        start++;
        }    
        //System.out.println("lab3.Lab3.cocktail()" + Arrays.toString(array));
    }
}
