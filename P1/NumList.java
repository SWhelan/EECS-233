/* Sarah Whelan
 * slw96
 * 9/25/2014
 * 
 * P1
 * 
 * The interface for the NumList to be implemented by two clasess 
 * (with an array and a linked list as underlying structures)
 */

public interface NumList {
  int size();
  void add(double value);
  void insert(int i, double value);
  void remove(int i);
  boolean contains(double value);
  double lookup(int i) throws Exception;
  boolean equals(NumList otherList);
  void removeDuplicates();
  String toString();
}