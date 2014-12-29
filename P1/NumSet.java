/* Sarah Whelan
 * slw96
 * 9/25/2014
 * 
 * P1
 * 
 * NumSet uses the NumLinkeList class to implement a Set - a list with no explicit order but the items are unique
 */

public class NumSet extends NumLinkedList {
  
  //creates an empty set
  public NumSet(){
  }
  
  //creates a set with given values
  public NumSet(double[] set){
    for(int i = 0; i < set.length; i++){
      if(!this.contains(set[i])){
        add(set[i]);
      }
    }
  }
  
  //add a value to a set only if that value isn't already in the sequence
  public void add(double value){
    if(!this.contains(value)){
      super.add(value);
    }
  }
  
  //insert a value to a set only if that value isn't already in the sequence
  public void insert(int i, double value){
    if(!this.contains(value)){
      super.insert(i, value);
    }
  }
  
  //returns the intersect of two sets
  public static NumSet intersect(NumSet S1, NumSet S2){
    NumSet newSet = new NumSet();
    //go through all of the values in one set
    for(int i = 0; i < S1.size(); i++){  
      try {
        //if the value is in both sets add it to the newSet
        if(S2.contains(S1.lookup(i))){
          newSet.add(S1.lookup(i));
        }
      } catch (Exception IndexOutOfBoundsException){
      }
      //sets can't have duplicates
      newSet.removeDuplicates();
    }
    return newSet;
  }
  
  //returns the union of two sets
  public static NumSet union(NumSet S1, NumSet S2){
    NumSet newSet = new NumSet();
    //go through both sets
    for(int i = 0; i < S1.size(); i++){  
      //add each value from both sets to the newSet
      try {
        newSet.add(S1.lookup(i));
      } catch (Exception IndexOutOfBoundsException){
      }
    }
    for(int j = 0; j < S2.size(); j++){
      try {
        newSet.add(S2.lookup(j));
      } catch (Exception IndexOutOfBoundsException){
      }
    }
    //sets can't have duplicates
    newSet.removeDuplicates();
    return newSet;
  }
  
  //return whether two sets are equal
  public static boolean equivalence(NumSet S1, NumSet S2){
    return S1.equals(S2);
  }
}