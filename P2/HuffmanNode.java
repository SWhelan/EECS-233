public class HuffmanNode implements Comparable<HuffmanNode>{
  public byte b;
  public int count;
  public boolean[] code;
  public HuffmanNode left;
  public HuffmanNode right;
  public String codeString = "";
  public boolean sortOrder;
  
  //default constructor sets byte and count
  public HuffmanNode(byte b, int c){
    this.count = c;
    this.b = b;
    this.left = null;
    this.right = null;
    this.sortOrder = false;
  }
  
  //constructor for HuffmanNode that sets bbyte count and the left and right nodes
  public HuffmanNode(byte b, int c, HuffmanNode left, HuffmanNode right){
    this.count = c;
    this.b = b;
    this.left = left;
    this.right = right;
    this.sortOrder = false;
  }
  
  //returns count
  public int getCount(){
    return count;
  }
  
  //returns byte
  public byte getByte(){
    return b;
  }
  
  //sets the left node
  public void setLeft(HuffmanNode left){
    this.left = left;
  }
  
  //sets the right node
  public void setRight(HuffmanNode right){
    this.right = right;
  }
  
  //returns the left node
  public HuffmanNode getLeft(){
    return left;
  }
  
  //returns the right node
  public HuffmanNode getRight(){
    return right;
  }
  
  //overrides the compareTo method to make a HuffmanNode comparable by byte and count depending on sortOrder
  @Override
  public int compareTo(HuffmanNode otherNode){
    if(this.count > otherNode.getCount()){
      return 1;
    } else if(this.count < otherNode.getCount()){
      return -1;
    } else {
      if(this.sortOrder){
        if(this.b > otherNode.getByte()){
          return -1;
        } else {
          return 1;
        }
      } else {
        if(this.b < otherNode.getByte()){
          return -1;
        } else {
          return 1;
        }
      }
    }
  }
  
  
//set the code of the huffman node
  public void setCode(String code){
    this.codeString = code;
    boolean[] array = new boolean[code.length()];
    for(int i = 0; i < code.length(); i++){
      if(code.charAt(i) == '0'){
        array[i] = false;
      } else {
        array[i] = true;
      }
    }
    this.code = array;
  }
  
//returns the huffman code in a boolean array
  public boolean[] getCode(){
    return this.code; 
  }
  
  public void setOrder(boolean s){
    this.sortOrder = s;
  }
  
//return the string code of the node
  public String getCodeString(){
    return this.codeString;
  }
}