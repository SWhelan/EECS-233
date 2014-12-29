/*Sarah Whelan
 * slw96
 * P3
 * 11/18/2014
 */

import java.util.*;

public class HashTable {
  //store the HashEntries in an array of LinkedLists
  private LinkedList<HashEntry>[] table;
  //also keep track of ranks of words
  private ArrayList<HashEntry> ranks = new ArrayList<HashEntry>();
  //the number of entries in the HashTable
  private int numStrings;
  
  //create the HashTable of specified size
  public HashTable(int size){
    table = new LinkedList[size];
    for(int i = 0; i < size; i++){
      table[i] = new LinkedList<HashEntry>();
    }
  }
  
  //default size
  public HashTable(){
    this(1001);
  }
  
  //resize table and rehash
  public void grow(){
    if(numStrings/table.length >= 1){
      LinkedList<HashEntry>[] temp = new LinkedList[table.length*2];
      for(int i = 0; i < temp.length; i++){
        temp[i] = new LinkedList<HashEntry>();
      }
      for(int i = 0; i < table.length; i++){
        for(int j =0; j < table[i].size(); j++){
          int hashCode = Math.abs(table[i].get(j).getKey().hashCode()%temp.length);
          temp[hashCode].add(new HashEntry(table[i].get(j).getKey(), table[i].get(j).getValue()));
        }
      }
      table = temp;
    }
  }
  
  public void put(String key, int value){
    this.grow();
    numStrings++;
    int hashCode = Math.abs(key.hashCode()%table.length);
    table[hashCode].add(new HashEntry(key, value));
    ranks.add(table[hashCode].getLast());
  }
  
  public void put(String key, int value, int hashCode){
    numStrings++;
    table[hashCode].add(new HashEntry(key, value));
    ranks.add(table[hashCode].getLast());
  }
  
  public void update(String key, int value){
    if(this.get(key) != -1){
      int hashCode = Math.abs(key.hashCode()%table.length);
      for(int i = 0; i < table[hashCode].size(); i++){
        if(table[hashCode].get(i).getKey().equals(key)){
          HashEntry index = table[hashCode].get(i);
          table[hashCode].get(i).setValue(value);
          ranks.set(this.getRankIndexOf(index), table[hashCode].get(i));
        }
      }
    } else {
      this.put(key, value);
    }
  }
  public int getRankIndexOf(HashEntry index){
    int toReturn = -1;
    for(int i = 0; i < ranks.size(); i++){
      if(ranks.get(i).getKey().equals(index.getKey())){
        toReturn = i;
      }
    }
    return toReturn;
  }
  
  public int get(String key){
    int hashCode = Math.abs(key.hashCode()%table.length);
    return this.get(key, hashCode);
  }
  
  public int get(String key, int hashCode){
    for(int i = 0; i < table[hashCode].size(); i++){
      if(table[hashCode].get(i).getKey().equals(key)){
        return table[hashCode].get(i).getValue();
      }
    }
    return -1;
  }
  
  public void sortRanks(){
    Collections.sort(ranks);
  }
  
  public int wordRank(String word){
    boolean countChange = false;
    int rank = 1;
    for(int i = 0; i < ranks.size(); i++){
      countChange = false;
      if(i > 0 && ranks.get(i-1).getValue() > ranks.get(i).getValue()){
        countChange = true;
      }
      if(countChange){
        rank = i+1;
      }
      if(ranks.get(i).getKey().equals(word)){
        return rank;
      }
    }
    return -1;
  }
  
  public String[] mostCommonWords(int k){
    if(k > ranks.size()){
      k = ranks.size();
    }
    String[] commonWords = new String[k];
    for(int i = 0; i < k; i++){
      commonWords[i] = ranks.get(i).getKey();
    }
    return commonWords;
  }
  
  public String[] leastCommonWords(int k){
    if(k > ranks.size()){
      k = ranks.size();
    }
    String[] commonWords = new String[k];
    int index = 0;
    int lowestRank = ranks.get(ranks.size()-1).getValue();
    for(int i = 0; i < ranks.size() && index < k; i++){
      if(ranks.get(i).getValue() <= lowestRank || k == ranks.size()){
        commonWords[index] = ranks.get(i).getKey();
        index++;
      }
    }
    return commonWords;
  }
  
  public String[] mostCommonCollocs(int k, String baseWord, int direction, String[] exclusions){
    baseWord = Tokenizer.normalize(baseWord);
    String[] collocs = new String[k];
    boolean excluded = false;
    int index = 0;
    if(direction == 1){
      int i = 0;
      while(i < ranks.size() && index < k){
        excluded = false;
        if(ranks.get(i).getKey().split(" ")[0].equals(baseWord)){
          if(exclusions.length > 0){
            for(int j = 0; j < exclusions.length; j++){
              if(exclusions[j] != null && !exclusions[j].equals("") && exclusions[j].equals(ranks.get(i).getKey().split(" ")[1])){
                excluded = true;
              }
            }
          }
          if(!excluded){
            collocs[index]=ranks.get(i).getKey().split(" ")[1];
            index++;
          }
        }
        i++;
      }
    } else if(direction == -1){
      int i = 0;
      while(i < ranks.size() && index < k){
        excluded = false;
        if(ranks.get(i).getKey().split(" ")[1].equals(baseWord)){
          if(exclusions.length > 0){
            for(int j = 0; j < exclusions.length; j++){
              if(exclusions[j] != null && !exclusions[j].equals("") && exclusions[j].equals(ranks.get(i).getKey().split(" ")[0])){
                excluded = true;
              }
            }
          }
          if(!excluded){
            collocs[index]=ranks.get(i).getKey().split(" ")[0];
            index++;
          }
        }
        i++;
      }
    }
    return collocs;
  }
  
  public String generateWordString(int k, String startWord){
    String toAppend;
    java.lang.StringBuilder builder = new java.lang.StringBuilder();
    String[] exclude = new String[2];
    builder.append(Tokenizer.normalize(startWord)+ " ");
    exclude[0] = Tokenizer.normalize(startWord);
    int index = 1;
    for(int i = 0; i < k-1; i++){
      if(startWord != null){
        toAppend = this.mostCommonCollocs(1, startWord, 1, exclude)[0];
        if(toAppend != null){
          builder.append(toAppend);
          if(index >= exclude.length){
            exclude = growArray(exclude);
          }
          exclude[index] = toAppend;
          index++;
          startWord = toAppend;
          if(i < k - 1){
            builder.append(" ");
          }
        }
      }
    }
    if(builder.charAt(builder.length()-1) == ' '){
      builder.deleteCharAt(builder.length()-1);
    }
    return builder.toString();
  }
  
  public String[] growArray(String[] array){
    String[] tempArray = new String[array.length*2];
    for(int i = 0; i < array.length; i++){
      tempArray[i] = array[i];
    }
    return tempArray; 
  } 
  
}