import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;


public class HuffmanCoderFundTest {

 /* Other JUnit tests.
     * 
     * Write your own JUnit tests below to check the correctness of your implementation.
     * 
     * When you turn in your draft (and final) we will run our own test suite on your code 
     * and provide you with the feedback.
     * 
     * Your draft code should contain a complete set of methods as specified in the assignment.
     * Any methods not yet implemented should be written as skeleton methods with an empty body. 
     * 
  * The JUnit tests below help to ensure that your methods compile with our test suite and have 
  * correctly typed arguments. You can replace them with more meaningful tests to test their
  * functionalities.
     */
 
 @Test
 public void testConstructor() throws IOException{
     HuffmanCoder hc = new HuffmanCoder("test2.txt", "output.txt");
     assertEquals("test2.txt", hc.getInputFile());
     assertEquals("output.txt", hc.getOutputFile());
 }
 
 @Test
 public void testCompressMethod() throws IOException{
     HuffmanCoder hc = new HuffmanCoder("twoCharTest.txt", "output.txt");
     assertEquals("twoCharTest.txt", hc.getInputFile());
     assertEquals("output.txt", hc.getOutputFile());
     hc.compress();
     boolean toEqual = hc.getArray()[0];
     assertEquals(toEqual, true);
 }

}