/* Sarah Whelan
 * slw96
 * 9/25/2014
 * 
 * P1
 * 
 * NumArrayList implements the NumList methods using an array as the underlying structure.
 */

public class NumArrayList implements NumList{
  private double[] numArray;
  
  //to avoid counting elements - keep track of the size in a field
  private int size = 0;
  
  //empty constructor creates an empty list
  public NumArrayList(){
    numArray = new double[0];
  }
  
  //constructor with specified value creates an array with a capacity of the value entered
  public NumArrayList(int n){
    if(n > 0){
      numArray = new double[n];
    } else {
      numArray = new double[0];
    }
  }
  
  //returns the size ie the number of elements in the list
  public int size(){
    return size;
  }
  
  //the maximum numbers of elements in the sequences before the array storing the sequences must be recreated with
  //more storage
  public int capacity(){
    return numArray.length;
  }
  
  //adds an element to the end of the sequence
  public void add(double value){
    if(capacity() == 0 || size() + 1 > capacity()){
      doubleArray();
    }
    numArray[size()] = value;
    size++;
  }
  
  //a helper method that doubles the size of the array when the number of elements 
  //in the sequence is higher than the capacity of the current array
  public void doubleArray(){
    int newCapacity;
    if(capacity() > 0){
      newCapacity = capacity()*2;
    } else {
      newCapacity = 1;
    }
    double[] tempArray = new double[newCapacity];
    for(int i = 0; i < capacity(); i++){
      tempArray[i] = numArray[i];
    }
    numArray = tempArray; 
  } 
  
  //inserts the value at before the ith element in the sequence
  //(or at the end of the sequence if there aren't i elements)
  public void insert(int i, double value){
    if(i < 0){
      i = 0;
    }
    //if it is at the end of the sequence just add it like normal
    if(i > size()){
      add(value);
    } else {
      if(size() + 1 > capacity()){
        //double the size of the array if necessary
        doubleArray();
      }
      //move the elements past i to the right to make space
      for(int j = size()+1; j > i; j--){
        if(numArray[j-1] != 0){
          numArray[j] = numArray[j-1];
        }
      }
      //actually insert the value
      if(i <= size()){
        numArray[i] = value;
        size++;
      }
    }
  }
  
  //removes the value at the index i (starting at 0)
  public void remove(int i){
    if(i <= size() && i >= 0){
      for(int j = i; j < capacity()-1; j++){
        numArray[j] = numArray[j+1];
      }
      size--;
    }
  }
  
  //looks for a specific value within the sequence
  public boolean contains(double value){
    for(int i = 0; i < size(); i++){
      if(value == numArray[i]){
        return true;
      }
    }
    return false;
  }
  
  //returns the value at a specific index - throws an exception if the sequence has less that i+1 elements
  public double lookup(int i) throws Exception{
    if(size() < i+1 || i < 0){
      throw new IndexOutOfBoundsException();
    }
    return numArray[i];
  }
  
  //checks if two NumLists are equal
  public boolean equals(NumList otherList){
    if(size() != otherList.size()){
      return false;
    } else {
      //check each value in order against the value of the other list
      for(int i = 0; i < size(); i++){
        try{
          double otherValue = otherList.lookup(i);
          if(numArray[i] != otherValue){
            return false;
          }
        }
        catch(Exception IndexOutOfBoundsException){
          return false;
        }
      }
      return true;
    }
  }
  
  //removes duplicates of a list
  public void removeDuplicates(){
    //checks every value in the array against
    for(int i = 0; i < capacity(); i++){
      //every following element
      int j = i+1; 
      while(j < size()){
        if(numArray[i] == numArray[j]){
          remove(j);
        } else {
          //only increase the incrementor if an elment wasn't removed 
          //if an element was removed j is already at the correct value
          j++;
        }
      }
    }
  }
  
  //returns the sequence seperated by spaces
  public String toString(){
    StringBuilder builder = new StringBuilder();
    for(int i = 0; i < size(); i++){
      builder.append(numArray[i]);
      if(i < size()-1){
        builder.append(" ");
      }
    }
    return builder.toString();
  }
}