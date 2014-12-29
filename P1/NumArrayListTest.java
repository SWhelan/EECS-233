import static org.junit.Assert.*;

import org.junit.Test;

public class NumArrayListTest {
  
  /* 
   * JUnit tests of fundamental functionality.
   *   
   * Use these JUnit tests to ensure that your code compiles
   * and correctly implements the fundamental functionality.
   * 
   * Code that does not pass these tests will not be graded.
   * Your draft submission needs to pass these tests in order
   * to receive a non-zero grade on the assignment.
   */
  
  @Test
  public void testDefaultConstructorAndToString() {
    NumArrayList list = new NumArrayList();     
    assertEquals("With no parameters, your constructors should initialize an list size 0. " + 
                 "It also can be the problem in method TOSTRING.", 
                 "", list.toString());
  }
  
  @Test
  public void testAddAndToString() {
    NumArrayList list = new NumArrayList();
    
    list.add(1.0);
    list.add(3.0);
    list.add(2.0);
    
    assertEquals("Add method should add element to the end of list each time. " +
                 "It's also can be the problem in method TOSTRING.",
                 "1.0 3.0 2.0", list.toString());
  }
  
  @Test
  public void testSize() {
    NumArrayList list = new NumArrayList();
    
    assertEquals("Method SIZE should return 0, when list is constructed by default constructor.",
                 0, list.size());
    
    list.add(1.0);
    list.add(2.0);
    list.add(3.0);
    
    assertEquals("Method SIZE should return the size of the list, " + 
                 "i.e. the number of elements, in the sequence.",
                 3, list.size());
  }
  
  @Test
  public void testEquals() {
    NumArrayList listA = new NumArrayList();
    NumArrayList listB = new NumArrayList();
    NumArrayList listC = new NumArrayList();
    
    listA.add(1.0);
    listA.add(3.0);
    
    assertFalse("EQUALS method should return FALSE, when two lists are not the same.",
                listA.equals(listB));
    
    listB.add(1.0);
    listB.add(3.0);
    
    assertTrue("EQUALS method should return TRUE, when two lists are the same.",
               listA.equals(listB));
    
    listC.add(3.0);
    listC.add(1.0);
    
    assertFalse("EQUALS method should return FALSE, even if the same " + 
                "numbers are in different order in two lists.",
                listA.equals(listC));
  }
  
  /* Other JUnit tests.
   * 
   * Write your own JUnit tests below to check the correctness of your implementation.
   * 
   * When you turn in your draft (and final) we will run our own test suite on your code 
   * and provide you with the feedback.
   */
  
  @Test
  public void testConstructorandCapacity(){
    NumArrayList listA = new NumArrayList(0);
    NumArrayList listB = new NumArrayList(-1);
    NumArrayList listC = new NumArrayList(10);
    
    assertEquals("Method Capacity should return 0, when array is empty.",
                 0, listA.capacity());
    assertEquals("Method Capacity should return 0, when list is constructed by a negative value.",
                 0, listB.capacity());
    assertEquals("Method Capacity should return the number of spaces in the array," +
                 " when array is constructed by a positive value.",
                 10, listC.capacity());
  }
  
  @Test
  public void testInsert(){
    NumArrayList list = new NumArrayList(10);
    
    list.insert(5, 1);
    assertEquals("Insert past the size of sequence should add element to the end of list each time.",
                 "1.0", list.toString());
    list.insert(5, 1);
    assertEquals("Insert past the size of sequence should add element to the end of list each time.",
                 "1.0 1.0", list.toString());
    list.insert(0, 0);
    assertEquals("Insert of sequence should add element before the specified insert point.",
                 "0.0 1.0 1.0", list.toString());
    list.insert(1, 3);
    assertEquals("Insert of sequence should add element before the specified insert point.",
                 "0.0 3.0 1.0 1.0", list.toString());
    list.insert(3, 4);
    assertEquals("Insert of sequence should add element before the specified insert point.",
                 "0.0 3.0 1.0 4.0 1.0", list.toString());
    
  }
  
  @Test
  public void testRemove(){
    NumArrayList list = new NumArrayList(10);
    list.add(1);
    list.add(2);
    list.add(3);
    list.remove(0);
    assertEquals("2.0 3.0", list.toString());
    list.remove(1);
    assertEquals("2.0", list.toString());
    list.remove(65);
    assertEquals("2.0", list.toString());
    list.remove(0);
    assertEquals("", list.toString());
    list.remove(1);
    assertEquals("", list.toString());
  }
  
  @Test
  public void testContains(){
    NumArrayList list = new NumArrayList(10);
    list.add(1);
    list.add(2);
    list.add(3);
    list.add(4);
    assertTrue(list.contains(1));
    assertTrue(list.contains(3));
    assertTrue(list.contains(4));
    assertFalse(list.contains(7));
  }
  
  @Test
  public void testlookup(){
    NumArrayList list = new NumArrayList(10);
    list.add(1);
    list.add(2);
    list.add(3);
    list.add(4);      
    try{
      assertEquals(1.0, list.lookup(0), 0);
    } catch (Exception NotInSequenceException) {
    }      
  }
  
  
  @Test
  public void testLookup2(){
    NumArrayList list = new NumArrayList(10);
    list.add(1);
    list.add(2);
    list.add(3);
    list.add(4);  
    try{
      list.lookup(9);
    } catch (Exception IndexOutOfBounsException){
      assertTrue(true);
    }
  }
  
  @Test
  public void testLookup3(){
    NumArrayList list = new NumArrayList(10);
    list.add(1);
    list.add(2);
    list.add(3);
    list.add(4);  
    try{
      list.lookup(4);
    } catch (Exception IndexOutOfBounsException){
      assertTrue(true);
    }
  }
  
  
  @Test
  public void testRemoveDuplicates(){
    NumArrayList listA = new NumArrayList(0);
    listA.removeDuplicates();
    assertEquals("", listA.toString());
    NumArrayList listB = new NumArrayList(10);
    listB.add(1);
    listB.add(2);
    listB.add(1);
    listB.add(2);
    listB.removeDuplicates();
    assertEquals("1.0 2.0", listB.toString());
  }
  
  @Test
  public void testEqualsEmpty(){
    NumArrayList listA = new NumArrayList();
    NumArrayList listB = new NumArrayList();
    assertTrue(listA.equals(listB));
  }
}


