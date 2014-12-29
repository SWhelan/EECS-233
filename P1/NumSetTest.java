/* Sarah Whelan
 * slw96
 * 9/25/2014
 * 
 * P1
 */

import static org.junit.Assert.*;

import org.junit.Test;

public class NumSetTest {
  @Test
  public void TestConstructorAndToString(){
    double[] doubleArray = {4, 5, 6};
    NumSet set = new NumSet(doubleArray);
    assertEquals("4.0 5.0 6.0", set.toString());
  }
  
  @Test
  public void TestIntersect(){
    double[] doubleArray = {4, 5, 6};
    NumSet set = new NumSet(doubleArray);
    double[] doubleArray2 = {3, 5, 7};
    NumSet set2 = new NumSet(doubleArray2);
    assertEquals("5.0", NumSet.intersect(set, set2).toString());
    
    double[] test = {1, 2};
    double[] test2 = {};
    assertEquals(0, (NumSet.intersect(new NumSet(test), new NumSet(test2))).size());
    
  }
  
  @Test
  public void TestUnion(){
    double[] doubleArray = {4, 5, 6};
    NumSet set = new NumSet(doubleArray);
    double[] doubleArray2 = {3, 5, 7};
    NumSet set2 = new NumSet(doubleArray2);
    assertEquals("4.0 5.0 6.0 3.0 7.0", NumSet.union(set, set2).toString());
    
    
    double[] doubleArray3 = {4, 5, 6, 9, 10, 11, 12};
    NumSet set3 = new NumSet(doubleArray3);
    double[] doubleArray4 = {3, 5, 7};
    NumSet set4 = new NumSet(doubleArray4);
    assertEquals("4.0 5.0 6.0 9.0 10.0 11.0 12.0 3.0 7.0", NumSet.union(set3, set4).toString());
  }
  
  @Test
  public void TestEquivalence(){
    double[] doubleArray = {4, 5, 6};
    NumSet set = new NumSet(doubleArray);
    double[] doubleArray2 = {4, 5, 6};
    NumSet set2 = new NumSet(doubleArray2);
    assertTrue(NumSet.equivalence(set, set2));
    set2.add(7);
    assertFalse(NumSet.equivalence(set, set2));
    set.remove(0);
    set.remove(0);
    set.remove(0);
    assertFalse(NumSet.equivalence(set, set2));
    set2.remove(0);
    set2.remove(0);
    set2.remove(0);
    set2.remove(0);
    assertTrue(NumSet.equivalence(set, set2));
  }
  
}