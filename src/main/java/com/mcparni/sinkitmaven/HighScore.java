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
    private ArrayList<String> names;
    private ArrayList<Integer> times;
    private int test;
    private String name;
    
    public HighScore() throws IOException  {
        this.filename = "highscore.txt";
        this.namesAndTimes = new ArrayList();
        this.times = new ArrayList();
        this.names = new ArrayList();
        this.test = 290;
        readFromFile();
    }
    
    
    private void readFromFile() {
        try(BufferedReader br = new BufferedReader(new FileReader(this.filename))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();   
                this.namesAndTimes.add(line);
                
            }
            System.out.println(this.namesAndTimes.get(0) + " : " + this.namesAndTimes.size());
            updateArrays();
        } catch(Exception e) { 
            System.out.println("exception: " + e);
        }
    }
    private void updateArrays() {
        for(int i = 0; i < this.namesAndTimes.size()- 1; i++) {
            //System.out.println("start" + this.namesAndTimes.get(i));
            String time = this.namesAndTimes.get(i);
            int start = time.indexOf(" ");
            String name = time.substring(0,start);
            time = time.substring(start + 1, time.length());
            this.times.add(Integer.parseInt(time));
            this.names.add(name);
        }
    }
    public void setName(String name) {
        this.name = name;
    }
    
    private void writeToFile() {
        try {
            BufferedWriter bw = null;
            bw = new BufferedWriter(new FileWriter(this.filename));
            bw.newLine();
            for (int i = 0; i < this.namesAndTimes.size() - 1; i++) {
              bw.write(this.namesAndTimes.get(i).toString());
              bw.newLine();
            }
            bw.flush();  
            bw.close();  
        } catch(Exception e) {
            System.out.println("error: " + e);
        }
    }

    public ArrayList<String> getNamesAndTimes() {
        return this.namesAndTimes;
    }
    
    public ArrayList<String> getNames() {
        return this.names;
    }
    
    public ArrayList<Integer> getTimes() {
        return this.times;
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
    
    public void enterNewEntry(int seconds, int index, String name) {
        this.times.add(index, seconds);
        this.names.add(index, name);
        this.namesAndTimes.add(index, name + " " + seconds);
        this.times.remove((this.times.size() - 1));
        this.names.remove((this.names.size() - 1));
        this.namesAndTimes.remove((this.namesAndTimes.size() -1));
        
        for (int i = 0; i < this.namesAndTimes.size() - 1; i++) {
              System.out.println("s: " + this.namesAndTimes.get(i));
        }
        
        try {
            writeToFile();
        } catch (Exception e) {
            System.out.println("exception: " + e);
        }
    }


}
