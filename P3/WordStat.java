/*Sarah Whelan
 * slw96
 * P3
 * 11/18/2014
 */

public class WordStat{
  private Tokenizer token;
  public HashTable table;
  private HashTable pairTable;
  private java.util.ArrayList<HashEntry> ranks;
  public WordStat(String file){
    try{
      token = new Tokenizer(file);
    } catch (Exception e){
      System.out.println("The file was not found please try again.");
    }
    this.createTable();
  }
  
  public WordStat(String[] array){
    token = new Tokenizer(array);
    this.createTable();
  }
  
  public void createTable(){
    table = new HashTable();
    pairTable = new HashTable();
    for(int i = 0; i < token.wordList().size(); i++){
      if(table.get(token.wordList().get(i)) == -1){
        table.put(token.wordList().get(i), 1);
      } else {
        table.update(token.wordList().get(i), table.get(token.wordList().get(i))+1);
      }
      if(i > 0){
        if(pairTable.get(token.wordList().get(i-1)+ " " +token.wordList().get(i)) == -1){
          pairTable.put(token.wordList().get(i-1)+" "+token.wordList().get(i), 1);
        } else {
          pairTable.update(token.wordList().get(i-1)+" "+token.wordList().get(i), pairTable.get(token.wordList().get(i-1)+" "+token.wordList().get(i))+1);
        } 
      }
    }
    table.sortRanks();
    pairTable.sortRanks();
  }
  
  public int wordCount(String word){
    return table.get(Tokenizer.normalize(word));
  }
  
  public int wordRank(String value){
    return table.wordRank(Tokenizer.normalize(value));
  }
  
  public String[] mostCommonWords(int k){
    return table.mostCommonWords(k);
  }
  
  public String[] leastCommonWords(int k){
    return table.leastCommonWords(k);
  }
  
  public int wordPairCount(String w1, String w2){
    return pairTable.get(Tokenizer.normalize(w1) + " " + Tokenizer.normalize(w2));
  }
  
  public int wordPairRank(String w1, String w2){
    return pairTable.wordRank(w1 + " " + w2);
  }
  
  public String[] mostCommonWordPairs(int k){
    return pairTable.mostCommonWords(k);
  }
  
  public String[] mostCommonCollocs(int k, String baseWord, int i){
    return pairTable.mostCommonCollocs(k, baseWord, i, new String[0]);
  }
  
  public String[] mostCommonCollocsExc(int k, String baseWord, int i, String[] exclusions){
    return pairTable.mostCommonCollocs(k, baseWord, i, exclusions);
  }
  
  public String generateWordString(int k, String startWord){
    return pairTable.generateWordString(k, startWord);
  }
}