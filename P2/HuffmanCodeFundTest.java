import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;


public class HuffmanCodeFundTest {
 
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
 public void testByteArrayArgumentConstructor() {
     HuffmanCode hc = new HuffmanCode(new byte [] {(byte)'a', (byte)'b'});
     String s = hc.toString();

     assertEquals("The constructor make a HuffmanCode using byte array","97: 0\n98: 1", s);
      HuffmanCode hc2 = new HuffmanCode(new byte [] {(byte)'a', (byte)'a',(byte)'a',(byte)'a',(byte)'a',(byte)'a',
        (byte)'a',(byte)'a',(byte)'a',(byte)'a',(byte)'a',(byte)'a',(byte)'b', (byte)'b', (byte)'b', (byte)'b'});
      String s2 = hc2.toString();
      assertEquals("The constructor make a HuffmanCode using byte array","97: 1\n98: 0", s2);
 }
 
 @Test
 public void testStringArgumentConstructor() throws IOException {
     HuffmanCode hc = new HuffmanCode("test2.txt");
     String s = hc.toString();
     assertEquals("The constructor make a HuffmanCode from a file", "97: 1\n98: 01\n99: 00", s);
 }
 
 @Test
 public void testByteAndCountArraysConstructor() {
     HuffmanCode hc = new HuffmanCode(new byte [] {(byte)'a', (byte)'b'}, new int [] {2, 3});
     String s = hc.toString();
     assertEquals("The constructor make a HuffmanCode using byte and count arrays", "98: 1\n97: 0", s); 
     HuffmanCode hc2 = new HuffmanCode(new byte [] {(byte)'b', (byte)'a'}, new int [] {3, 2});
     String s2 = hc2.toString();
     assertEquals("The constructor make a HuffmanCode using byte and count arrays", "98: 1\n97: 0", s2);
 }
 
 @Test
 public void testCodeMethod() {
     HuffmanCode hc = new HuffmanCode(new byte [] {(byte)'a', (byte)'b'}, new int [] {2, 3});
     boolean[] code = hc.code((byte)'a');
     boolean[] code2 = hc.code((byte)'b');
     assertEquals("This method reurns the code of specific byte", false,  code[0]);
     assertEquals(true, code2[0]);
 }
 
 @Test
 public void testToStringMethod() {
     HuffmanCode hc = new HuffmanCode(new byte [] {(byte)'a', (byte)'b'}, new int [] {2, 3});
     String s = hc.toString();
     assertEquals("This method returns astring containing the table of the binary encodings of each byte in the Huffman tree",
   "98: 1\n97: 0", s);
 }
}