public class HuffmanList extends java.util.LinkedList<HuffmanNode>{
  
  private boolean setOrderToString = false;
  
  //constructor with byte array 
  public HuffmanList(byte[] b){
    //count bytes
    ByteCounter counter = new ByteCounter(b);
    //add the bytes from the byte counter to the HuffmanNode List
    this.initialAddToList(counter);
  }
  
  //constructor with input file
  public HuffmanList(String file){
    try {
      ByteCounter counter = new ByteCounter(file);
      this.initialAddToList(counter);
    } catch (java.io.IOException e){
      System.out.println("File Not Found.");
    }
  }
  
  //create a huffman list from arrays of bytes and counts
  public HuffmanList(byte[] b, int[] counts) throws RuntimeException{
    boolean unique = true;
    boolean positive = true;
    if(b.length == counts.length){
      for(int i = 0; i < b.length; i++){
        for(int j = i+1; j < b.length; j++){
          if(b[i] == b[j]){
            unique = false;
          }
        }
      }
      for(int i = 0; i < counts.length; i++){
        if(counts[i] < 0){
          positive = false;
        }
      }
      if(unique && positive){
        for(int i = 0; i < b.length; i++){
          this.add(new HuffmanNode(b[i], counts[i]));
        }
        java.util.Collections.sort(this);
      } else {
        throw new RuntimeException("There were duplicate bytes in the input array or one or more of the counts was negative");
      }
    } else {
        throw new RuntimeException("The number of bytes and the number of counts do not match.");
      }
  }
  
  //add the counts to the huffman node list
  private void initialAddToList(ByteCounter counter){
    counter.setOrder("countInc");
    java.util.ListIterator<ByteCounterNode> iter = counter.listIterator();
    while(iter.hasNext()){
      ByteCounterNode thisNode = iter.next();
      this.add(new HuffmanNode(thisNode.getByte(), thisNode.getCount()));
    }
  }
  
  public void setSetOrderToString(boolean s){
    java.util.ListIterator<HuffmanNode> iter = this.listIterator();
    while(iter.hasNext()){
      HuffmanNode node = iter.next();
      node.setOrder(true);
    }
  }
  
  //returns the index of the byte if it is in the list
  public int contains(byte b){
    //start at the beginning of the list
    java.util.ListIterator<HuffmanNode> iter = this.listIterator();
    //check every value of the list
    int i = 0;
    while(iter.hasNext()){
      HuffmanNode thisNode = iter.next();
      if(thisNode.getByte() == b){
        return i;
      }
      i++;
    }
    //didn't find the value
    return -1;
  }
}
