package com.example.java;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by JOE on
 **/
public class TrainAdventureTest {

    public static int[] readFile(){
        BufferedReader in = null;
        int[] input = new int[0];
        try {
            File filePath = new File("File/index.txt");
            in = new BufferedReader(new FileReader(filePath));
            String data = in.readLine();
            String[] dataArray = data.split(" ");
            input = new int[dataArray.length];
            for (int i = 0; i < dataArray.length; i++){
                input[i] = Integer.parseInt(dataArray[i]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }

    public static void train(){
        int[] station = TrainAdventureTest.readFile();
        HashSet<Set<Integer>> journey = new HashSet<>();
        int next;
        int count = 0;
        for (int i = 1; i < station.length; i++) {
            Set<Integer> possible = new HashSet<>();
            // store the first station number
            possible.add(i);
            // if the first station is odd, count++
            if (i % 2 != 0) {
                count += 1;
            }
            // set the value to next variable as second station
            next = station[i];
            // store the second station number
            possible.add(next);
            // if the second station is odd, count++
            if (next % 2 != 0) {
                count += 1;
            }
            // the in the first 2 station only one station is odd, then we can go to next station
            if (count <= 1) {
                // while next station is not 0 which is start station, then we can keep going to next
                // if the next station is 0, which means this path is already finished
                while(next != 0) {
                    // create a array list which can contain repeated station numbers
                    ArrayList<Integer> repeated = new ArrayList<>(possible);
                    // go to next station
                    next = station[next];
                    // store the station number
                    // if the path make it into this while loop, it would has 0 as its last station,
                    // which could be taken as the beginning station.
                    // and the path has no more than 1 odd station
                    possible.add(next);
                    repeated.add(next);
                    // if there is a odd station
                    if (next % 2 != 0){
                        // count++
                        count++;
                        // check if count > 1
                        if (count > 1) {
                            // because we did not contain the beginning station at the beginning
                            possible.add(0);
                            // if count > 1, then break the loop, which means this path is already finished
                            break;
                        }
                    }
                    // if the repeated array list's length is longer than possible set, then break the the loop
                    if (repeated.size() > possible.size()) {
                        break;
                    }
                }
            }
            // Set journey collect the paths
            journey.add(possible);
            // reset the count for the next path
            count = 0;
        }
        // to get the max length of paths
        int max = 0;
        for (Set<Integer> set : journey) {
            if (set.size() > max) {
                max = set.size();
            }
            System.out.println(set + " : " + set.size());
        }
        System.out.println(max);
    }

    public static void main(String args[]) {
        TrainAdventureTest.train();
    }

}
