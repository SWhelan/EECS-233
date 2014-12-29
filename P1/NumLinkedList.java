/* Sarah Whelan
 * slw96
 * 9/25/2014
 * 
 * P1
 * 
 * NumLinkedList implements the NumList methods using a linked list as the underlying structure.
 */

public class NumLinkedList implements NumList{
  
  /* 
   * LinkedListElement is an inner class representing a single element of the linked list implementation of NumList.
   */
  
  public class LinkedListElement{
    //fiels for the value and the next element in the linked list
    private LinkedListElement next;
    private double value;
    
    //sets initial cvalues of the element
    public LinkedListElement(double value, LinkedListElement next){
      this.value = value;
      this.next = next;
    }
    
    //returns the next element in the linked list
    public LinkedListElement getNext(){
      return next;
    }
    
    //returns this element's value
    public double getValue(){
      return value;
    }
    
    //changes the next element in the linked list
    public void setNext(LinkedListElement element){
      next = element;
    }
    
    //changes the value of this element in the list
    public void setValue(double newValue){
      value = newValue;
    }
  }
  
  //keep track of the number of elements in the sequence to 
  //avoid iterating through the entire list just to get the size
  private int size = 0;
  //the beginning of the list
  private LinkedListElement head = new LinkedListElement(0, null);
  
  //returns the size - variable kept track of in all methods that change the number of elements in the sequence
  public int size(){
    return size;
  }
  
  //returns the first element in the linked list to avoid direct access of private fields
  public LinkedListElement getFront(){
    return head;
  }
  
  //adds an element to the end of the sequence
  public void add(double value){
    //start at the front of the list
    LinkedListElement pointer = getFront();
    //and get the next value until the sequence has no more values
    while(pointer.getNext() != null){
      pointer = pointer.getNext();
    }
    //once the end of the list is reached add the desired value
    pointer.setNext(new LinkedListElement(value, null));
    size++;
  }
  
  //insert a value at a specified index or at the end if the index is more than the size of the sequence
  public void insert(int i, double value){
    //handle the negative case
    if(i<0){
      i=0;
    }
    //start at the beginning of the list
    LinkedListElement pointer = getFront();
    int j = 0;
    //get the next value until the end of the list or i is reached (whichever is first)
    while(pointer.getNext() != null && j < i){
      pointer = pointer.getNext();
      j++;
    }
    //add the value where the pointer stopped
    pointer.setNext(new LinkedListElement(value, pointer.getNext()));
    size++;
  }
  
  //remove a value at a specified index
  public void remove(int i){
    //if the index is greater than the index do nothing
    if(i < size && i >= 0){
      size--;
      //start at the beginning of the list
      LinkedListElement pointer = getFront();
      int j = 0;
      //get the next value in the list until the index or the end is reached (whichever is first)
      while(pointer.getNext() != null && j < i){
        pointer = pointer.getNext();
        j++;
      }
      //remove the element when the pointer gets to it
      pointer.setNext(pointer.getNext().getNext());
    }
  }
  public boolean swap(LinkedListElement p) {
    if(size() <= 1){
      return false;
    } else {
      LinkedListElement pointer = head;

        while(pointer.getNext() != null && pointer.getNext() != p){
          pointer = pointer.getNext();
        }

        if(pointer.getNext() == p){
        pointer.setNext(p.getNext());
        p.setNext(p.getNext().getNext()); 
        return true;
      } else {
        return false;
      }
    }
  }
  
  //returns wheather the list contians a specific value
  public boolean contains(double value){
    //start at the beginning of the list
    LinkedListElement pointer = getFront();
    //check every value of the list
    while(pointer.getNext() != null){
      pointer = pointer.getNext();
      if(pointer.getValue() == value){
        return true;
      }
    }
    //didn't find the value
    return false;
  }
  
  //returns the value at a specific index trhows an exception if the index is not in the list
  public double lookup(int i) throws Exception{
    if(i < size && i >= 0){
      LinkedListElement pointer = getFront();
      int j = 0;
      //get the next value until the index is reached
      while(pointer.getNext() != null && j < i){
        pointer = pointer.getNext();
        j++;
      }
      //return the value
      return pointer.getNext().getValue();
    } else {
      throw new IndexOutOfBoundsException();
    }
  }
  
  //returns wheather or not to lists are equal by checking every value
  public boolean equals(NumList otherList){
    //if the sizes aren't the same don't do any more work
    if(size() != otherList.size()){
      return false;
    } else {
      LinkedListElement pointer = getFront();
      int i = 0;
      while(pointer.getNext() != null){      
        pointer = pointer.getNext();
        try{
          //check every value in this list against the other list
          double otherValue = otherList.lookup(i);
          if(pointer.getValue() != otherValue){
            return false;
          }
        } catch (Exception IndexOutOfBoundsException){
          return false;
        }
        i++;
      }
      return true;
    }
  }  
  
  //remove the duplicate numbers from a list
  public void removeDuplicates(){
    //checks every value in the array against
    for(int i = 0; i < size(); i++){
      //every following element
      int j = i+1; 
      while(j < size()){
        try{
          if(lookup(i) == lookup(j)){
            remove(j);
          } else {
            //only increase the incrementor if an elment wasn't removed 
            //if an element was removed j is already at the correct value
            j++;
          }
        } catch (Exception NotInSequenceException){
        } 
      }
    }
  }
  
  //returns the list i string form with each double separated by spaces
  public String toString(){
    StringBuilder builder = new StringBuilder();
    LinkedListElement pointer = getFront();
    while(pointer.getNext() != null){
      pointer = pointer.getNext();
      builder.append(pointer.getValue());
      if(pointer.getNext() != null){
        builder.append(" ");
      }
    }
    return builder.toString();
  }
}