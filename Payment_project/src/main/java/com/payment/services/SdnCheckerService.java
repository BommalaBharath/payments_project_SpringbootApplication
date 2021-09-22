package com.payment.services;
import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;
import java.io.IOException;
public class SdnCheckerService{
public boolean checker(String phrase) throws IOException{
      Scanner fileScanner = new Scanner(new File(".\\src\\main\\resources\\sdnlist.txt"));
      int lineID= 0;
      boolean f=true;
      Pattern pattern =  Pattern.compile(phrase);
      Matcher matcher = null;
      while(fileScanner.hasNextLine()){
            String line = fileScanner.nextLine();
            lineID++;
            matcher = pattern.matcher(line);
            if(matcher.find()){
                f=false;
             
            }
           
        }
        return f;
    }
}