// Please note that this code is slightly different from the textbook code
//to reflect the fact that the Node class is implemented using data encapsulation

/*
 * Purpose: Data Structure and Algorithms Lab X Problem Y
 * Status: Incomplete
 * Last update: 09/20/17
 * Submitted:  09/26/17
 * Comment: test suite and sample run attached
 * @author: William Jacobs
 * @version: 2017.09.20
 */

// ****************************************************
// Reference-based implementation of ADT list.
// ****************************************************
public class MyListReferenceBased implements ListInterface
{
  // reference to linked list of items
  private Node head;

  public MyListReferenceBased()
  {
    head = null;
  }  // end default constructor

  public boolean isEmpty()
  {
          if (head == null) return true;
          else return false;
  }  // end isEmpty

  public int size()
  {
        int count = 0;
        Node current = head;
        while (current != null)
        {
                current = current.getNext();
                count++;
        }  //end while
        return count;
  }  // end size

  private Node find(int index)
  {
  // --------------------------------------------------
  // Locates a specified node in a linked list.
  // Precondition: index is the number of the desired
  // node. Assumes that 0 <= index <= numItems
  // Postcondition: Returns a reference to the desired
  // node.
  // --------------------------------------------------
    Node curr = head;
    for (int skip = 0; skip < index; skip++)
    {
      curr = curr.getNext();
    } // end for
    return curr;
  } // end find

  public Object get(int index)
                throws ListIndexOutOfBoundsException
  {
    if (index >= 0 && index < this.size())
    {
      // get reference to node, then data in node
      Node curr = find(index);
      Object dataItem = curr.getItem();
      return dataItem;
    }
    else
    {
      throw new ListIndexOutOfBoundsException(
                     "List index out of bounds exception on get");
    } // end if
  } // end get

  public void add(int index, Object item)
                  throws ListIndexOutOfBoundsException
  {
    if (index >= 0 && index < this.size()+1)
    {
      if (index == 0)
      {
        // insert the new node containing item at
        // beginning of list
        Node newNode = new Node(item, head);
        head = newNode;
      }
      else
      {
        Node prev = find(index-1);
        // insert the new node containing item after
        // the node that prev references
        Node newNode = new Node(item, prev.getNext());
        prev.setNext(newNode);
      } // end if
    }
    else
    {
      throw new ListIndexOutOfBoundsException(
       "List index out of bounds exception on add");
    } // end if
  }  // end add

  public void remove(int index)
                   throws ListIndexOutOfBoundsException
  {
    if (index >= 0 && index < this.size())
    {
      if (index == 0)
      {
        // delete the first node from the list
        head = head.getNext();
      }
      else
      {
        Node prev = find(index-1);
        // delete the node after the node that prev
        // references, save reference to node
        Node curr = prev.getNext();
        prev.setNext(curr.getNext());
      } // end if
    } // end if
    else
    {
      throw new ListIndexOutOfBoundsException(
                   "List index out of bounds exception on remove");
    } // end if
  }   // end remove

  public void removeAll()
  {
    // setting head to null causes list to be
    // unreachable and thus marked for garbage
    // collection
    head = null;
  } // end removeAll

  public String toString()
  {
        Node curr = head;
        String msg = "{ ";
        while(curr != null)
        {
                if (curr.getNext() != null) msg += "[" + curr.getItem() + "]->";
                else msg += "[" + curr.getItem() + "]";
                curr = curr.getNext();
        }
        return msg += " }";
  }
} // end ListReferenceBased
