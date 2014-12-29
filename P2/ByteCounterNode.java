public class ByteCounterNode implements Comparable<ByteCounterNode>{
  private byte b;
  private int count;
  private String sortOrder;
  
  //basic constructor
  public ByteCounterNode(byte b, int count){
    this.b = b;
    this.count = count;
    this.sortOrder = "byte";
  }
  
  //changes the sortOrder of the node - all nodes in a list/set should have the same sortOrder
  public void setOrder(String order){
    this.sortOrder = order;
  }
  
  //returns the byte
  public byte getByte(){
    return b;
  }
  
  //increases the count of the byte
  public void incrementCount(){
    count++;
  }
  
  //return count
  public int getCount(){
    return count;
  }
  
  //overrides the compare to method to make ByteCounterNodes comparayble by byte or count depending on sortOrder
  @Override
  public int compareTo(ByteCounterNode otherNode){
    if(this.sortOrder.equals("byte")){
      if(this.b > otherNode.getByte()){
        return 1;
      } else if(this.b < otherNode.getByte()){
        return -1;
      } else {
        return 0;
      }
    } else if(this.sortOrder.equals("countInc")){
      if(this.count > otherNode.getCount()){
        return 1;
      } else if(this.count < otherNode.getCount()){
        return -1;
      } else {
        return 0;
      }
    } else{
      if(this.count < otherNode.getCount()){
        return 1;
      } else if(this.count > otherNode.getCount()){
        return -1;
      } else {
        return 0;
      }
    }
  }
}
