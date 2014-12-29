/*Sarah Whelan
 * slw96
 * P3
 * 11/18/2014
 */

import java.util.*;
public class Tokenizer {
  private ArrayList<String> wordList = new ArrayList<String>();
  
  public Tokenizer(String path) throws java.io.FileNotFoundException, java.io.IOException{
    try{
      java.io.BufferedReader in = new java.io.BufferedReader(new java.io.FileReader(path));
      try{
        while(in.ready()){
          this.split(in.readLine());
        }
        in.close();
      } catch(java.io.IOException e){
        throw e;
      }
    } catch(java.io.FileNotFoundException e){
      throw e;
    }
  }
  
  public Tokenizer(String[] array){
    for(int i = 0; i < array.length; i++){
      String[] splitArray = array[i].split(" ");
      this.makeWordList(splitArray);
    }
  }
  
  public ArrayList<String> wordList(){
    return wordList;
  }
  
  public void split(String line){
    String[] words = line.split("\\s+");
    this.makeWordList(words);
  }
  
  public void makeWordList(String[] words){
    for(int i = 0; i < words.length; i++){
      String word = normalize(words[i]);
      if(word.length() > 0){
        wordList.add(word);
      }
    }
  }
  
  public static String normalize(String word){
    StringBuilder builder = new StringBuilder();
    for(int i = 0; i < word.length(); i++){
      if(word.charAt(i) >= 'a' && word.charAt(i) <= 'z'){
        builder.append(word.charAt(i));
      } if (word.charAt(i) >= 'A' && word.charAt(i) <= 'Z'){
        builder.append((char)(word.charAt(i) + ('a'-'A')));
      }
    }
    return builder.toString();
  }
}