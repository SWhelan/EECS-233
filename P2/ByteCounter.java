/*
 * Sarah Whelan
 * 
 * slw96
 * 
 * P3
 * ByteCounter - counts frequency of bytes
 */

public class ByteCounter extends java.util.LinkedList<ByteCounterNode>{  
  
  //constructor for just a byteArray
  public ByteCounter(byte[] byteArray){
    this.countBytes(byteArray);
  }
  
  //constructor for an input file to be read in binary throws IOException if the fle was not found
  public ByteCounter(String path) throws java.io.IOException{
    java.nio.file.Path file = java.nio.file.Paths.get(path);
    try{
      byte[] byteArray = java.nio.file.Files.readAllBytes(file);
      this.countBytes(byteArray);
    }
    catch(java.io.IOException e){
      throw new java.io.IOException("The file was not found.");
    }
  }
  
  //go through the bytes and count the occurances
  private void countBytes(byte[] byteArray){
    for(int i = 0; i<byteArray.length; i++){
      int index = this.contains(byteArray[i]);
      if(index == -1){
        this.add(new ByteCounterNode(byteArray[i], 1));
      } else {
        get(index).incrementCount(); 
      }
    }
    java.util.Collections.sort(this);
  }
  
  //returns the count of a byte
  public int getCount(byte b){
    return get(contains(b)).getCount();
  }
  
  //returns the counts in an array of an array of bytes 
  public int[] getCount(byte[] b){
    int[] countArray = new int[this.size()];
    for(int i = 0; i < this.size(); i++){
      countArray[i] = getCount(b[i]);
    }
    return countArray;
  }
  
  //return an array of bytes - all of the bytes that have been counted
  public byte[] getElements(){
    byte[] arrayElements = new byte[this.size()];
    java.util.ListIterator<ByteCounterNode> iter = this.listIterator();
    int i = 0;
    while(iter.hasNext()){
      arrayElements[i] = iter.next().getByte();
      i++;
    }
    return arrayElements;
  }
  
  //set the order of the bytes options are byte, count increasing, or count decreasing
  public void setOrder(String order) throws RuntimeException{
    java.util.ListIterator<ByteCounterNode> iter = this.listIterator();
    //byte is the default
    if(order.equals("byte")){
      while(iter.hasNext()){
        iter.next().setOrder("byte");
      }
    } else if (order.equals("countInc")){
      while(iter.hasNext()){
        iter.next().setOrder("countInc");
      }
    } else if (order.equals("countDec")){
      while(iter.hasNext()){
        iter.next().setOrder("countDec");
      }
    } else {
      throw new RuntimeException("That was not a valid order.");
    }
    java.util.Collections.sort(this);
  }
  
  //returns the index of a byte if it has been counted
  public int contains(byte b){
    //start at the beginning of the list
    java.util.ListIterator<ByteCounterNode> iter = this.listIterator();
    //check every value of the list
    int i = 0;
    while(iter.hasNext()){
      ByteCounterNode thisNode = iter.next();
      if(thisNode.getByte() == b){
        return i;
      }
      i++;
    }
    //didn't find the value
    return -1;
  }
  
  //overrides the toString method
  public String toString(){
    return this.toString("byte");
  }
  
  //the toString method accepts a String format of char
  public String toString(String format){
      StringBuilder builder = new StringBuilder();
      java.util.ListIterator<ByteCounterNode> iter = this.listIterator();
      while(iter.hasNext()){
        ByteCounterNode thisNode = iter.next();
        if(format.equals("char")){
          builder.append((char)(thisNode.getByte()));
        } else {
          int value = (int)thisNode.getByte();
          builder.append(value);
        }
        builder.append(":" + thisNode.getCount());
        if(iter.hasNext()){
          builder.append(" ");
        }
      }
      return builder.toString();
  }
}