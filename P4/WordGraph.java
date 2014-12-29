import java.util.*;

public class WordGraph {
  private int numNodes;
  private int numEdges;
  public ArrayList<node> nodes = new ArrayList<node>();
  private node[] path;
  private int totalWords;
  public ArrayList<node> finalized;
  
  public static void main(String[] args){
    System.out.println("1. When attempting to open a file that does not exist the program should tell the user the file doesn't exist and take no further action.");
    WordGraph test1 = new WordGraph("non-existent-file.txt");
    System.out.println("For the following series of test a file will be used containing: \"this is a test a this is a more words for test\" will be used contained in test.txt");
    WordGraph test2 = new WordGraph("test.txt");
    //num nodes
    System.out.println("Test Num Nodes");
    System.out.println("2. The number of nodes created from this file should be seven.");
    System.out.println(test2.numNodes());
    //num edges
    System.out.println("Test Num Edges");
    System.out.println("3. The number of edges created from this file should be nine.");
    System.out.println(test2.numEdges());
    //word counts
    System.out.println("Test Word Counts");
    System.out.println("4. The word count for a word appearing once should be one (using more)");
    System.out.println(test2.wordCount("more"));
    System.out.println("5. The word count for a word appearing twice should be two (using this)");
    System.out.println(test2.wordCount("this"));
    System.out.println("6. The word count for a word not appearing in the source text should be negative one (using not)");
    System.out.println(test2.wordCount("not"));
    //in degrees
    System.out.println("Test In Degrees");
    System.out.println("7. The indegree for \"more\" should be: 1");
    System.out.println(test2.inDegree("more"));
    System.out.println("8. The indegree for \"this\" should be: 1");
    System.out.println(test2.inDegree("this"));
    System.out.println("9. The indegree for \"not\" should be: -1");
    System.out.println(test2.inDegree("not"));
    System.out.println("10. The indegree for \"test\" should be: 2");
    System.out.println(test2.inDegree("test"));
    //out degrees
    System.out.println("Test Out Degrees");
    System.out.println("11. The outdegree for \"more\" should be: 1");
    System.out.println(test2.outDegree("more"));
    System.out.println("12. The outdegree for \"this\" should be: 1");
    System.out.println(test2.outDegree("this"));
    System.out.println("13. The outdegree for \"not\" should be: -1");
    System.out.println(test2.outDegree("not"));
    System.out.println("14. The outdegree for \"a\" should be: 3");
    System.out.println(test2.outDegree("a"));
    //prev words
    System.out.println("Test Previous Words");
    System.out.println("15. The prevWords for \"more\" should be: \"a\"");
    System.out.println(test2.prevWords("more")[0]);
    System.out.println("16. The prevWords for \"this\" should be: \"a\"");
    System.out.println(test2.prevWords("this")[0]);
    System.out.println("17. The prevWords for \"not\" should be: null");
    System.out.println(test2.prevWords("not"));
    System.out.println("18. The prevWords for \"a\" should be: \"is, test\"");
    System.out.println(test2.prevWords("a")[0] + " " + test2.prevWords("a")[1]);
    System.out.println("19. The prevWords for \"test\" should be: \"a, for\"");
    System.out.println(test2.prevWords("test")[0] + " " + test2.prevWords("test")[1]);
    //next words
    System.out.println("Test Next Words");
    System.out.println("20. The nextWords for \"more\" should be: \"words\"");
    System.out.println(test2.nextWords("more")[0]);
    System.out.println("21. The nextWords for \"this\" should be: \"is\"");
    System.out.println(test2.nextWords("this")[0]);
    System.out.println("22. The nextWords for \"not\" should be: null");
    System.out.println(test2.nextWords("not"));
    System.out.println("23. The nextWords for \"a\" should be: \"test, this, more\"");
    System.out.println(test2.nextWords("a")[0] + " " + test2.nextWords("a")[1] + " " + test2.nextWords("a")[2]);
    System.out.println("24. The nextWords for \"test\" should be: \"a\"");
    System.out.println(test2.nextWords("test")[0]);
    //wordSeqCount
    System.out.println("Test Word Sequence Cost");
    String[] strings = {"this", "is", "a"};
    System.out.println("25. When using \"this\", \"is\", \"a\" as a test for word sequence cost the program should return: ~1.79.");
    System.out.println(test2.wordSeqCost(strings));
    String[] strings2 = {"this"};
    System.out.println("26. When using \"this\" as a test for word sequence cost the program should return: ~1.79.");
    System.out.println(test2.wordSeqCost(strings2));
    String[] strings3 = {"is", "a", "more", "words", "for", "test"};
    System.out.println("26. When using \"is\", \"a\", \"more\", \"words\", \"for\", \"test\" as a test for word sequence cost the program should return: ~2.89.");
    System.out.println(test2.wordSeqCost(strings3));
    System.out.println("27. Testing when a word is not in the graph is not applicable because the specifications said to assume the words are in the graph.");
    //generatePhrase
    System.out.println("Tests of Generate Phrase with starting word, ending word, and limit parameters");
    System.out.println("28. Generate Phrase with \"this\", \"is\" and 5 should return: \"this is\".");
    System.out.println(test2.generatePhrase("this", "is", 5));
    System.out.println("29. Generate Phrase with \"this\", \"is\" and 1 should return: \"\".");
    System.out.println(test2.generatePhrase("this", "is", 1));  
    System.out.println("30. Generate Phrase with \"this\", \"for\" and 7 should return: \"this is a more words for\".");
    System.out.println(test2.generatePhrase("this", "for", 7));
    System.out.println("31. Generate Phrase with \"this\", \"this\" and 5 should return: \"this\".");
    System.out.println(test2.generatePhrase("this", "this", 5));
    System.out.println("32. Generate Phrase with \"test\", \"for\" and 5 should return: \"test a more words for\".");
    System.out.println(test2.generatePhrase("test", "for", 5));
    System.out.println("32. Generate Phrase with \"test\", \"for\" and 4 should return: \"\".");
    System.out.println(test2.generatePhrase("test", "for", 4));
    System.out.println("33. Generate Phrase with \"test\", \"not\" and 4 should return: \"\".");
    System.out.println(test2.generatePhrase("test", "not", 4));
    //generatePhrase with repetitions
    System.out.println("Tests of Generate Phrase with starting word, ending word, limit and repetitions parameters.");
    System.out.println("34. Generate Phrase with \"this\", \"is\", 5, and 3 should return: \"this is\".");
    System.out.println(test2.generatePhrase("this", "is", 5, 3));
    System.out.println("35. Generate Phrase with \"this\", \"is\", 1, and 5 should return: \"\".");
    System.out.println(test2.generatePhrase("this", "is", 1, 5));  
    System.out.println("36. Generate Phrase with \"this\", \"for\" 7, and 6 should return: \"this is a more words for\".");
    System.out.println(test2.generatePhrase("this", "for", 7, 6));
    System.out.println("37. Generate Phrase with \"this\", \"this\" 5, and 12 should return: \"this\".");
    System.out.println(test2.generatePhrase("this", "this", 5, 12));
    System.out.println("38. Generate Phrase with \"test\", \"for\" 5, and 2 should return: \"test a more words for\".");
    System.out.println(test2.generatePhrase("test", "for", 5, 2));
    System.out.println("39. Generate Phrase with \"test\", \"for\" 4, and 3 should return: \"\".");
    System.out.println(test2.generatePhrase("test", "for", 4, 3));
    System.out.println("40. Generate Phrase with \"test\", \"not\" 4, and 1 should return: \"\".");
    System.out.println(test2.generatePhrase("test", "not", 4, 1));
    System.out.println("Testing an Empty File");
    System.out.println("41. Testing making a graph of an empty file. Should have num nodes of 0.");
    WordGraph test3 = new WordGraph("empty.txt");
    System.out.println(test3.numNodes());
    System.out.println("Testing a one word file");
    System.out.println("42. Testing making a graph of a file with one word. Should have num nodes of 1.");
    WordGraph test4 = new WordGraph("hello.txt");
    System.out.println(test4.numNodes());
    System.out.println("43. Testing making a graph of a file with one word. Should have indegree of 0.");
    System.out.println(test4.inDegree("hello"));
    System.out.println("44. Testing making a graph of a file with one word. Should have outdegree of 0.");
    System.out.println(test4.inDegree("hello"));                       
  }
  
  WordGraph(String file){
    Tokenizer token = new Tokenizer(file);
    ArrayList<String> wordList = token.wordList();
    int i = 0;
    totalWords = wordList.size();
    while(i < wordList.size()){
      String word = wordList.get(i);
      int nodeIndex = getIndex(word);
      //if the word has already been added once
      if(nodeIndex != -1){
        //increase count
        updateNode(nodeIndex);
        //if this also isn't the first node awkward becuase this can never not be true
        if(i > 0){
          if(nodes.get(getIndex(wordList.get(i-1))).hasEdge(nodes.get(nodeIndex))){
            updateEdges(nodes.get(getIndex(wordList.get(i-1))), nodes.get(nodeIndex));
          } else {
            addEdge(nodes.get(getIndex(wordList.get(i-1))), nodes.get(nodeIndex));
          }
        }
      } else{
        addNode(word);
        nodeIndex = nodes.size()-1;
        if(i > 0){
          addEdge(nodes.get(getIndex(wordList.get(i-1))), nodes.get(nodeIndex));
        }
      }
      i++;
    }
  } 
  
  public int getIndex(String word){
    boolean repeat = false;
    int j = 0;
    while(j < nodes.size() && !repeat){
      if(nodes.get(j).getWord().equals(word)){
        repeat = true;
      }
      j++;
    } 
    if(!repeat){
      return -1;
    } else {
      return --j;
    }
  }
  
  public void addNode(String word){
    numNodes++;
    nodes.add(new node(word));
  }
  
  public void addEdge(node start, node end){
    numEdges++;
    edge newEdge = new edge(start, end);
    start.incrementOutDegree();
    start.addOut(newEdge);
    end.incrementInDegree();
    end.addIn(newEdge);    
  }
  
  public void updateNode(int i){
    nodes.get(i).incrementCount();
  }
  
  public void updateEdges(node start, node end){
    start.incrementOutEdge(end);
    start.incrementInEdge(end); 
  }
  
  public boolean hasEdge(node start, node end){
    return start.hasEdge(end);
  }
  
  public int numNodes(){
    return this.numNodes;
  }
  
  public int numEdges(){
    return this.numEdges;
  }
  
  public int wordCount(String w){
    w = Tokenizer.normalize(w);
    int index = getIndex(w);
    if(index != -1){
      return nodes.get(index).getCount();
    } else {
      return -1;
    }
  }
  
  public int inDegree(String w){
    w = Tokenizer.normalize(w);
    int index = getIndex(w);
    if(index != -1){
      return nodes.get(index).getInDegree();
    } else {
      return -1;
    }
  }
  
  public int outDegree(String w){
    w = Tokenizer.normalize(w);
    int index = getIndex(w);
    if(index != -1){
      return nodes.get(index).getOutDegree();
    } else {
      return -1;
    }
  }
  
  public String[] prevWords(String w){
    w = Tokenizer.normalize(w);
    int index = getIndex(w);
    if(index != -1){
      return nodes.get(index).getInStrings();
    } else {
      return null;
    }
  }
  
  public String[] nextWords(String w){
    w = Tokenizer.normalize(w);
    int index = getIndex(w);
    if(index != -1){
      return nodes.get(index).getOutStrings();
    } else {
      return null;
    }
  }
  
  public node[] dijsktra(String startWord, int limit, int repetition){
    //finalized nodes
    finalized = new ArrayList<node>();
    //starting point
    int startIndex = getIndex(startWord);
    //finalize the starting point
    finalized.add(nodes.get(startIndex));
    //keep track of costs
    double[] pathCost = new double[nodes.size()];
    //previous nodes
    path = new node[nodes.size()];
    //limit of paths lengths
    int[] limits = new int[nodes.size()];
    //limit of that particular node repetitions
    int[] repetitions = new int[nodes.size()];
    //initialize all of the adjacent nodes of startWord
    for(int i = 0; i < nodes.size(); i++){
      //the initial part of the cost
      pathCost[i] += Math.log(totalWords/nodes.get(startIndex).getCount());
      //if the current node is adjacent to the starting node
      if(this.hasEdge(nodes.get(startIndex), nodes.get(i))){
        //the ssumming part of the cost
        pathCost[i] += Math.log(nodes.get(startIndex).getCount()/nodes.get(startIndex).getOutEdge(nodes.get(i)).getWeight());
        //the previous node of this path was the start index
        path[i] = nodes.get(startIndex);
        //this path length of current lowest path is one
        limits[i]++;
        //the current node has been used once
        repetitions[i]++;
      } else {
        //the current node is not adjacent to the start node and the cost is infinity
        pathCost[i] = Double.MAX_VALUE;
      }
    }
    int j = 0;
    while(j < nodes.size()*limit || finalized.size() < nodes.size()){
      //find the node not finalized such that the cost is a minimum
      int minIndex = minIndex(pathCost, finalized);
      if((limits[minIndex] >= limit || repetitions[minIndex] < repetition) || !hasNode(finalized, nodes.get(minIndex))){
        finalized.add(nodes.get(minIndex));
      }
      //update path cost for all nodes adjacent to the min node
      for(int i = 0; i < nodes.size(); i++){
        //if the node isn't finalized and is adjacent to the min node
        if(!hasNode(finalized, nodes.get(i)) && this.hasEdge(nodes.get(minIndex), nodes.get(i))){
          double oldCost = pathCost[i];
          //update the path cost the new cost to i is either the old cost to i or the known cost to minIndex plus cost from minIndex to i
          pathCost[i] = Math.min(pathCost[i], pathCost[minIndex] + Math.log(nodes.get(minIndex).getCount()/nodes.get(minIndex).getOutEdge(nodes.get(i)).getWeight()));
          if(oldCost != pathCost[i]){
            path[i] = nodes.get(minIndex);
            repetitions[i]++;
          }
          limits[i]++;
        } 
        j++;
      }
    }   
    return path;
  }
  
  public int minIndex(double[] pathCost, ArrayList<node> finalized){
    double min = Double.MAX_VALUE; 
    int minIndex = 0;
    for(int i = 0; i < pathCost.length; i++){
      if(pathCost[i] <= min && !this.hasNode(finalized, nodes.get(i))){
        min = pathCost[i];
        minIndex = i;
      }
    }
    return minIndex;
  }
  
  public boolean hasNode(ArrayList<node> arrayList, node toLookFor){
    boolean hasNode = false;
    for(int i = 0; i < arrayList.size()-1; i++){
      if(arrayList.get(i).equals(toLookFor)){
        hasNode = true;
      }
    }
    return hasNode;
  }
  
  public double wordSeqCost(String[] wordSeq){
    double count = 0;
    count += Math.log(totalWords/nodes.get(getIndex(wordSeq[0])).getCount());
    for(int i = 0; i < wordSeq.length-1; i++){
      count += Math.log(nodes.get(getIndex(wordSeq[i])).getCount()/nodes.get(getIndex(wordSeq[i])).getOutEdge(nodes.get(getIndex(wordSeq[i+1]))).getWeight());
    }
    return count;
  }
  
  public String generatePhrase(String startWord, String endWord, int N){
    return this.generatePhrase(startWord, endWord, N, 1);
  }
  
  public String generatePhrase(String startWord, String endWord, int N, int r){
    startWord = Tokenizer.normalize(startWord);
    endWord = Tokenizer.normalize(endWord);
    if(getIndex(endWord) == -1 || getIndex(startWord) == -1){
      return "";
    }
    dijsktra(startWord, N, r);
    StringBuilder builder = new StringBuilder();
    int oldIndex = getIndex(endWord);
    Boolean foundIt = false;
    int i = 0; 
    while(i < N-1 && !foundIt){
      if(path[oldIndex] != null){
        builder.insert(0,path[oldIndex].getWord());
        if(path[oldIndex].getWord().equals(startWord)){
          foundIt = true;
        }
        builder.insert(0, " ");
        oldIndex = getIndex(path[oldIndex].getWord());
      }
      i++;
    }
    if(foundIt || startWord.equals(endWord)){
      builder.append(" "+endWord);
      builder.deleteCharAt(0);
      return builder.toString();
    } else {
      return "";
    }
  }
}