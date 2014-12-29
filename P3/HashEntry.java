/*Sarah Whelan
 * slw96
 * P3
 * 11/18/2014
 */

public class HashEntry implements Comparable<HashEntry>{
  private String key;
  private int value;
  
  public HashEntry(String key, int value){
    this.key = key;
    this.value = value;
  }
  
  public String getKey(){
    return this.key;
  }
  
  public int getValue(){
    return this.value;
  }
  
  public void setValue(int value){
    this.value = value;
  }
  
  public int compareTo(HashEntry other){
    if(this.value < other.getValue()){
      return 1;
    } else if(this.value > other.getValue()){
      return -1;
    } else {
      return this.key.compareTo(other.getKey());        
    }
  }
}