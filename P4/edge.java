public class edge{
  private node startNode;
  private node endNode;
  private int weight = 1;
  
  public edge(node start, node end){
    this.startNode = start;
    this.endNode = end;
  }
  
  public node getStartNode(){
    return this.startNode;
  }
  
  public node getEndNode(){
    return this.endNode;
  }
  
  public int getWeight(){
    return this.weight;
  }
  
  public void incrementWeight(){
    this.weight++;
  }
}