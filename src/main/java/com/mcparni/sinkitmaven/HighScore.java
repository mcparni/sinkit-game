package com.mcparni.sinkitmaven;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;


public class HighScore {
    private final String filename;
    private ArrayList<String> namesAndTimes;
    private ArrayList<Integer> times;
    private int test;
    private String name;
    
    public HighScore() throws IOException  {
        this.filename = "highscore.txt";
        this.namesAndTimes = new ArrayList();
        this.times = new ArrayList();
        this.test = 290;
        
        readFromFile();
    }
    private void readFromFile() throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader(this.filename))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();   
                this.namesAndTimes.add(line);
                
            }
            
            for(int i = 0; i < this.namesAndTimes.size()- 1; i++) {
                //System.out.println("start" + this.namesAndTimes.get(i));
                String s = this.namesAndTimes.get(i);
                int start = s.indexOf(" ");
                s = s.substring(start + 1, s.length());
                this.times.add(Integer.parseInt(s));
                
        
               // System.out.println("start" + this.times[i]);

            }
            String everything = sb.toString();
            
            int index = testHighScore(this.test);
            if(index > -1) {
                System.out.println(this.times.get(index));
                this.times.add(index, this.test);

                
            } 
            this.times.remove(this.times.size() - 1);
            
            //Arrays.sort(this.times);
            for(int i = 0; i < this.times.size(); i++) {
                 System.out.println("times: " +this.times.get(i));
                 
                 //System.out.println(this.times[0]);
            }
            
            writeToFile();

        } 
    }
    public void setName(String name) {
        this.name = name;
    }
    
    private void writeToFile() throws IOException{
        BufferedWriter outputWriter = null;
        outputWriter = new BufferedWriter(new FileWriter("tscore.txt"));
        outputWriter.newLine();
        for (int i = 0; i < this.times.size(); i++) {
          // Maybe:
          outputWriter.write(this.times.get(i).toString());
          outputWriter.newLine();
        }
        outputWriter.flush();  
        outputWriter.close();  
    }
    
    
    public int testHighScore(int score) {
        int index = -1;
        //System.out.println(this.times[0]);
        for(int i = 0; i < this.times.size(); i++) {
            
            if(score < this.times.get(i)) {
                    index = i;
                    return index;
            }
        }
        return index;
    }


}
