import java.util.*;

public class node{
  private String word;
  private int count = 1;
  private LinkedList<edge> in = new LinkedList<edge>();
  private LinkedList<edge> out = new LinkedList<edge>();
  private int outDegree;
  private int inDegree;
  
  public node(String word){
    this.word = word;
  }
  
  public String getWord(){
    return word;
  }
  
  public int getOutDegree(){
    return outDegree;
  }
  
  public int getInDegree(){
    return inDegree;
  }
  
  public void incrementInDegree(){
    inDegree++;
  }
  
  public void incrementOutDegree(){
    outDegree++;
  }
  
  public void addIn(edge in){
    this.in.add(in);
  }
  
  public void addOut(edge out){
    this.out.add(out);
  }
  
  public void incrementOutEdge(node end){
    int i = 0;
    boolean found = false;
    while(i < out.size() && !found){
      if(out.get(i).getEndNode() == end){
        out.get(i).incrementWeight();
        found = true;
      }
      i++;
    }
  }
  
  public void incrementInEdge(node start){
    int i = 0;
    boolean found = false;
    while(i < in.size() && !found){
      if(in.get(i).getStartNode() == start){
        in.get(i).incrementWeight();
        found = true;
      }
      i++;
    }
  }
  
  public String[] getOutStrings(){
    String[] toReturn = new String[outDegree];
    for(int i = 0; i < outDegree; i++){
      toReturn[i] = out.get(i).getEndNode().getWord();
    }
    return toReturn;
  }
  
  public String[] getInStrings(){
    String[] toReturn = new String[inDegree];
    for(int i = 0; i < inDegree; i++){
      toReturn[i] = in.get(i).getStartNode().getWord();
    }
    return toReturn;
  }
  
  public int getCount(){
    return count;
  }
  
  public void incrementCount(){
    count++;
  }
  
  public boolean hasEdge(node end){
    int i = 0;
    boolean found = false;
    while(i < out.size() && !found){
      if(out.get(i).getEndNode() == end){
        found = true;
      }
      i++;
    }
    return found;
  }
  
  public edge getOutEdge(node end){
    int i = 0;
    boolean found = false;
    while(i < out.size() && !found){
      if(out.get(i).getEndNode() == end){
        found = true;
      }
      i++;
    }
    return out.get(--i);
  }
  
  
}