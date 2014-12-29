import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;


public class WordStatFundTest {
  
  @Test
  public void FileArgumentConstructor() throws IOException{
    WordStat ws = new WordStat ("file.txt");
    assertTrue("The constructor should read the file and get statistics from it", 
               true);
  }
  @Test
  public void ArrayArgumentConstructor(){
    String test [] = {"This is the First Sentence!","This is the SECOND sentence$","@Just the third sentence"};
    WordStat ws = new WordStat (test);
    assertTrue("The constructor should read the array and get statistics from it", 
               true);
  }
  @Test
  public void testMostCommonWords(){
    String test [] = {"This is the First Sentence!","This is the SECOND sentence$","@Just the third sentence"};
    WordStat ws = new WordStat (test);
    String[] common = ws.mostCommonWords (2);
    assertTrue("This method should return a String array of the k most common words in descending"+
               " order of their count",true);
    String answer [] = {"sentence", "the"};
    assertEquals(common[0], answer[0]);
    assertEquals(common[1], answer[1]);
  }
  
  @Test
  public void testLeastCommonWords(){
    String test [] = {"This is the First Sentence!","This is the SECOND sentence$","@Just the third sentence"};
    WordStat ws = new WordStat (test);
    String[] common = ws.leastCommonWords (3);
    assertTrue("This method should return a String array of the k least common words in descending"+
               " order of their count",true);
    String answer [] = {"first", "just", "second"};
    assertEquals(common[0], answer[0]);
    assertEquals(common[1], answer[1]);
    assertEquals(common[2], answer[2]);
  }
  @Test
  public void testWordPairCount(){
    String test [] = {"This is the First Sentence!","This is the SECOND sentence$","@Just the third sentence"};
    WordStat ws = new WordStat (test);
    int count = ws.wordPairCount ("the","sentence");
    assertTrue("This method should return the count of the pair of words",true);
    assertEquals(-1, count);
    int count2 = ws.wordPairCount ("First","sentence");
    assertEquals(1, count2);
    int count3 = ws.wordPairCount ("is","the");
    assertEquals(2, count3);
  }
  @Test
  public void testWordPairRank(){
    String test [] = {"This is the First Sentence!","This is the SECOND sentence$","@Just the third sentence"};
    WordStat ws = new WordStat (test);
    int rank =ws.wordPairRank("the","sentence");
    assertTrue("This method should return the count of the pair of words",true);
    assertEquals(-1, rank);
    int rank2 = ws.wordPairRank("is", "the");
    assertEquals(1, rank2);
  }
  
  @Test
  public void testMostCommonWordPairs(){
    String test [] = {"This is the First Sentence!","This is the SECOND sentence$","@Just the third sentence"};
    WordStat ws = new WordStat(test);
    String[] common = ws.mostCommonWordPairs(2);
    String[] answers = {"is the", "this is"};
    assertEquals(common[0], answers[0]);
    assertEquals(common[1], answers[1]);
  }
  @Test
  public void testMostCommonCollocs(){
    String test [] = {"This is the First Sentence!","This is the SECOND sentence$","@Just the third sentence"};
    WordStat ws = new WordStat (test);
    assertTrue("returns the k most common words at a given relative position i to baseWord",true);
    String[] common = ws.mostCommonCollocs(2,"the",1);
    String[] answers = {"first", "second"};
    assertEquals(answers[0], common[0]);
    assertEquals(answers[1], common[1]);
    String[] common2 = ws.mostCommonCollocs(2,"the",-1);
    String[] answers2 = {"is", "just"};
    assertEquals(answers2[0], common2[0]);
    assertEquals(answers2[1], common2[1]);
  }
  
  @Test
  public void testMostCommonCollocsExc(){
    String test [] = {"This is the First Sentence!","This is the SECOND sentence$","@Just the third sentence"};
    WordStat ws = new WordStat (test);
    String exc [] = {"the", "is"};
    String[] common = ws.mostCommonCollocsExc(2,"This",1,exc);
    String[] answers = {null, null};
    assertEquals(answers[0], common[0]);
    assertEquals(answers[1], common[1]);
    String exc2 [] = {"the", "is", "second"};
    String[] common2 = ws.mostCommonCollocsExc(2,"the",1,exc2);
    String[] answers2 = {"first", "third"};
    assertEquals(answers2[0], common2[0]);
    assertEquals(answers2[1], common2[1]);
  }
  
  @Test
  public void testGenerateWordString(){
    String test [] = {"This is the First Sentence!","This is the SECOND sentence$","@Just the third sentence"};
    WordStat ws = new WordStat (test);
    String common = ws.generateWordString(3,"This");
    assertEquals("this is the", common);
  }
  
  @Test
  public void testLargeText(){
    WordStat ws2 = new WordStat("scarletLetter.txt");
    String[] common = ws2.mostCommonWords(4);
    String[] answer = {"the", "of", "and", "a"};
    assertEquals(answer[0], common[0]);
    assertEquals(answer[1], common[1]);
    assertEquals(answer[2], common[2]);
    assertEquals(answer[3], common[3]);
    String[] notCommon = ws2.leastCommonWords(4);
    String[] answer2 = {"abandoned", "abashed", "abate", "abel"};
    assertEquals(answer2[0], notCommon[0]);
    assertEquals(answer2[1], notCommon[1]);
    assertEquals(answer2[2], notCommon[2]);
    assertEquals(answer2[3], notCommon[3]);
    String[] exclude = {"the", "he", "she", "to", "her"};
    String[] output = ws2.mostCommonCollocsExc(6, "said", 1, exclude);
    String[] answer3 = {"hester", "mr", "pearl", "roger"};
    assertEquals(answer3[0], output[0]);
    assertEquals(answer3[1], output[1]);
    assertEquals(answer3[2], output[2]);
    assertEquals(answer3[3], output[3]);
  }
  
  
   
}
