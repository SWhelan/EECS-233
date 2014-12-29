public class HuffmanCode {
  private HuffmanList list;
  private HuffmanList originalList;
  private HuffmanList tree;
  private byte b;
  private int numNodes;
  private int nextIndex = 0;
  private byte[] bytes;
  private String[] codes;
  
  //create huffman code from an array of bytes
  public HuffmanCode(byte[] b){
    list = new HuffmanList(b);
    originalList = new HuffmanList(b);
    this.initialize();
  }
  
  //create huffman code from an input file
  public HuffmanCode(String file){
    list = new HuffmanList(file);
    originalList = new HuffmanList(file);
    this.initialize();
  }
  
  //create huffman code from array of bytes and counts
  public HuffmanCode(byte[] b, int[] counts) throws RuntimeException{
    list = new HuffmanList(b, counts);
    originalList = new HuffmanList(b, counts);
    this.initialize();
  }
  
  //creates the huffman code by removing the first two nodes and creating and inserting the new nodes until there is a tree
  public void initialize(){
    numNodes = list.size();
    while(list.size() > 1){
      HuffmanNode first = list.removeFirst();
      HuffmanNode second = list.removeFirst();
      int newCount =  first.getCount() + second.getCount();
      //do a for loop until place to insert is found
      int index = 0;
      java.util.ListIterator<HuffmanNode> iter = list.listIterator();
      Boolean found = false;
      while(iter.hasNext() && !found){
        if(iter.next().getCount() < newCount){
          index++;
        } else {
          found = true;
        }
      }
      list.add(index, new HuffmanNode((byte)-1, newCount, first, second));
    }
    bytes = new byte[this.numNodes];
    codes = new String[this.numNodes];
    this.preOrderTraversal(list.getFirst(), "");
  }
  
  
  //returns the boolean array of the code of the byte b
  public boolean[] code(byte b){
    String code = this.codeString(b);
    boolean[] array = new boolean[code.length()];
    for(int i = 0; i < code.length(); i++){
      if(code.charAt(i) == (char)'0'){
        array[i] = false;
      } else {
        array[i] = true;
      }
    }
    return array;
  }
  
  //traverses the nodes and sets the codes on the nodes and stores the bytes and codes in arrays as part of the Huffman Code class
  public void preOrderTraversal(HuffmanNode root, String numToAdd){
    if (root != null){
      //if the root is a leaf
      if(root.getByte() != -1){
        //set the code
        root.setCode(numToAdd);
        //save the code and byte in HuffmanCode class
        bytes[nextIndex] = root.getByte();
        codes[nextIndex] = root.getCodeString();
        nextIndex++;
        numToAdd = "";
      }
      //Recursively go down each subtree until a leaf is reached
      if (root.getLeft() != null){
        numToAdd = numToAdd + "0";
        preOrderTraversal(root.getLeft(), numToAdd);
        //this resets the code if we went down a left subtree and came back and don't want the 0 from the left subtree anymore
        if(root.getRight() != null){
          numToAdd = numToAdd.substring(0, numToAdd.length()-1);
        }
      }
      if (root.getRight() != null){
        numToAdd = numToAdd + "1";
        preOrderTraversal(root.getRight(), numToAdd);
      }
    }
  }
  
  //returns the code of byte b in string form
  public String codeString(byte b){
    int index = 0;
    while(index >= 0 && index < bytes.length && bytes[index] != b){
      index++;
    }
    return codes[index];
  }
  
  //returns all of the codes in a table
  public String toString(){
    originalList.setSetOrderToString(true);
    java.util.Collections.sort(originalList);
    java.lang.StringBuilder builder = new StringBuilder();
    for(int i = numNodes-1; i >= 0; i--){
      builder.append(originalList.get(i).getByte() + ": " + this.codeString(originalList.get(i).getByte()));
      if(i > 0){
        builder.append("\n");
      }
    }                             
    return builder.toString();
  }
}