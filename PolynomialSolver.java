import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


interface ILinkedList {
    
   
    
    public void clear();
    
    public boolean isEmpty();
    
    public int size();
    
    public void Display();
    
    public void add(Object element);
    
    
}

class ListNode {
    
    Object data;
    ListNode next;

    ListNode(Object data) {
        this.data = data;
        this.next = null;
    }
}
class SingleLinkedList implements ILinkedList {
    int size;
    ListNode head;

    SingleLinkedList() {
        size = 0;
        head = null;
    }

  
   
    
    
    public void clear ()
    {
       
        ListNode current = head;
        while (current != null) {
        ListNode nextNode = current.next; 
        current.next = null; 
        current = nextNode; 
        }
        head = null; 
    }
    
    
    
    
    public boolean isEmpty() {
      
        if(head==null)
        {
          return true;
        }
       
        else
        {
          return false;
        }
     }
    
    
    
    public int size() {
        if(head==null)
        {
          return 0;
        }
        else
        {
          int count=0;
          ListNode current=head;
          while (current!=null)
          {
            count++;
            current=current.next;
          }
          size=count;
           return size;
          
        }
    
    }


   
   

    
    public void add(Object element) {
       
         ListNode newNode = new ListNode(element);
        if (head == null) {
            head = newNode;
            size();
            return;
        } else {
            ListNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }

    }
    
   

   public void Display()
   {
     
        System.out.print("[");
        ListNode current=head;
        int i = 0;
        while (current != null) {
            System.out.print(current.data);
            i++;
             current=current.next;
            if (i < size()) {
                System.out.print(", ");
            }
        }
        System.out.print("]");
       
       
   }

    
}
class PolyNode {
    int coefficient;
    int exponent;

    PolyNode(int coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
    }
}
interface IPolynomialSolver {

void setPolynomial(char poly, int[][] terms);

String print(char poly);

void clearPolynomial(char poly);

float evaluatePolynomial(char poly, float value);

int[][] add(char poly1, char poly2);

int[][] subtract(char poly1, char poly2);

int[][] multiply(char poly1, char poly2);
}

class PolynomialSolver implements IPolynomialSolver {
    
    SingleLinkedList A;
    SingleLinkedList B;
    SingleLinkedList C;
    SingleLinkedList R;
     PolynomialSolver()
     {
        A = new SingleLinkedList();
        B = new SingleLinkedList();
        C = new SingleLinkedList();
        R = new SingleLinkedList();
     }
    
    public void setPolynomial(char poly, int[][] terms) {
        SingleLinkedList Selected = null;
        switch (poly) {
        case 'A':
            Selected = A;
            break;
        case 'B':
            Selected = B;
            break;
        case 'C':
            Selected = C;
            break;
        default:
            System.out.print("Error");
            System.exit(0);
        }
        try {
            for (int[] term : terms) {
                PolyNode t = new PolyNode(term[0], term[1]);
                Selected.add(t);
            }
        } catch (Exception e) {
            System.out.print("Error");
            System.exit(0);
        }
       
    }
    
    public String print(char poly) {
    SingleLinkedList selectedList = null;
    StringBuilder output = new StringBuilder();

    try {
        switch (poly) {
            case 'A':
                selectedList = A;
                break;
            case 'B':
                selectedList = B;
                break;
            case 'C':
                selectedList = C;
                break;
            case 'R':
                selectedList = R;
                break;
            default:
                throw new IllegalArgumentException("Error");
        }

        if (selectedList == null || selectedList.head == null) {
            throw new NullPointerException("Error");
        }

        ListNode currentNode = selectedList.head;
        boolean flag = false;

        while (currentNode != null) {
            PolyNode currentTerm = (PolyNode) currentNode.data;
            double coefficient = currentTerm.coefficient;
            int exponent = currentTerm.exponent;

            if (coefficient != 0) {
                if (flag && coefficient > 0) {
                    output.append("+");
                }

                if (coefficient < 0) {
                    if(coefficient==-1)
                    {
                        output.append("-1");
                    }
                    else
                    {
                        output.append("-");
                    }
                }

                double absCoefficient = Math.abs(coefficient);
                if (absCoefficient != 1 || exponent == 0) {
                    if (absCoefficient == (int) absCoefficient) {
                        output.append((int) absCoefficient); 
                    } else {
                        output.append(absCoefficient);
                    }
                }

                if (exponent >= 2) {
                    output.append("x^").append(exponent);
                } else if (exponent == 1) {
                    output.append("x");
                }

                flag = true;
            }

            currentNode = currentNode.next;
        }
    } catch (Exception e) {
        output = new StringBuilder("Error: " + e.getMessage());
    }

    return output.toString();
}


    public void clearPolynomial(char poly) {
        SingleLinkedList Selected = null;
        switch (poly) {
        case 'A':
            Selected = A;
            break;
        case 'B':
            Selected = B;
            break;
        case 'C':
            Selected = C;
            break;
        default:
            System.out.print("Error");
            System.exit(0);
        }
        try {

            Selected.clear();
            Selected.Display();
        } catch (Exception e) {
            System.out.print("Error");
            System.exit(0);
        }

    }
  
    
public float evaluatePolynomial(char poly, float value) {
        SingleLinkedList Selected = null;
        switch (poly) {
        case 'A':
            Selected = A;
            break;
        case 'B':
            Selected = B;
            break;
        case 'C':
            Selected = C;
            break;
        case 'R':
            Selected = R;
            break;
        default:
            System.out.print("Error");
            System.exit(0);
        }

       if (Selected == null || Selected.head == null) {
        System.out.println("Error");
         System.exit(0);
    }

    float result = 0f;
    ListNode p = Selected.head;
    
    while (p != null) {
        PolyNode t = (PolyNode) p.data;
        result += t.coefficient * Math.pow(value, t.exponent);
        p = p.next;
    }

    return result;
    }
    
    
    
public int[][] add(char poly1, char poly2) {
    SingleLinkedList Selected1;
    SingleLinkedList Selected2;

    switch (poly1) {
        case 'A':
            Selected1 = A;
            break;
        case 'B':
            Selected1 = B;
            break;
        case 'C':
            Selected1 = C;
            break;
        default:
            System.out.print("Error");
            System.exit(0);
            return null; // Added return statement
    }

    switch (poly2) {
        case 'A':
            Selected2 = A;
            break;
        case 'B':
            Selected2 = B;
            break;
        case 'C':
            Selected2 = C;
            break;
        default:
            System.out.print("Error");
            System.exit(0);
            return null; // Added return statement
    }

    if (Selected1 == null || Selected2 == null) {
        System.out.print("Error");
        System.exit(0);
        return null; // Added return statement
    }

    ListNode current1 = Selected1.head;
    ListNode current2 = Selected2.head;
    ListNode currentR = R.head;

    while (current1 != null && current2 != null) {
        PolyNode temp1 = (PolyNode) current1.data;
        PolyNode temp2 = (PolyNode) current2.data;

        if (temp1.exponent > temp2.exponent) {
            R.add(new PolyNode(temp1.coefficient, temp1.exponent));
            current1 = current1.next;
        } else if (temp1.exponent < temp2.exponent) {
            R.add(new PolyNode(temp2.coefficient, temp2.exponent));
            current2 = current2.next;
        } else {
            int sum = temp1.coefficient + temp2.coefficient;
            if (sum != 0) {
                R.add(new PolyNode(sum, temp1.exponent));
            }
            current1 = current1.next;
            current2 = current2.next;
        }
    }

    while (current1 != null) {
        PolyNode temp = (PolyNode) current1.data;
        R.add(new PolyNode(temp.coefficient, temp.exponent));
        current1 = current1.next;
    }

    while (current2 != null) {
        PolyNode temp = (PolyNode) current2.data;
        R.add(new PolyNode(temp.coefficient, temp.exponent));
        current2 = current2.next;
    }

    currentR = R.head;
    int[][] res = new int[R.size()][2];
    int i = 0;

    while (currentR != null) {
        PolyNode temp = (PolyNode) currentR.data;
        res[i][0] = temp.coefficient;
        res[i][1] = temp.exponent;
        i++;
        currentR = currentR.next;
    }

    return res;
}
    
  public int[][] subtract(char poly1, char poly2) {

        ListNode current1;
        ListNode current2;
        ListNode currentr;
        SingleLinkedList SL1 = null;
        SingleLinkedList SL2 = null;
        int i = 0;
        int j = 0;
        try {
            R.clear();
            switch (poly1) {
            case 'A':
                SL1 = A;
                break;
            case 'B':
                SL1 = B;
                break;
            case 'C':
                SL1 = C;
                break;
            default:
                System.out.print("Error");
                System.exit(0);
            }

            switch (poly2) {
            case 'A':
                SL2 = A;
                break;
            case 'B':
                SL2 = B;
                break;
            case 'C':
                SL2 = C;
                break;
            default:
                System.out.print("Error");
                System.exit(0);
            }

            current1 = SL1.head;
            current2 = SL2.head;
            if (current1 == null || current2 == null) {
                System.out.print("Error");
                System.exit(0);
            }
            currentr = R.head;

            while (current1 != null && current2 != null) {
                PolyNode temp1 = (PolyNode) current1.data;
                PolyNode temp2 = (PolyNode) current2.data;
                if (temp1.exponent > temp2.exponent) 
                {
                    PolyNode t = new PolyNode(temp1.coefficient, temp1.exponent);
                    R.add(t);
                    i++;
                    current1 = current1.next;
                } else if (temp1.exponent < temp2.exponent) {

                    PolyNode t = new PolyNode(-temp2.coefficient, temp2.exponent);
                    R.add(t);
                    j++;
                    current2 = current2.next;
                } else {
                     int coeff = temp1.coefficient - temp2.coefficient;
                    if (coeff != -1) {
                        R.add(new PolyNode(coeff, temp1.exponent));
                    } else {
                        R.add(new PolyNode(-1, temp1.exponent));
                    }
                    i++;
                    j++;
                    current1 = current1.next;
                    current2 = current2.next;
                }
            }
            if (current1 == null)
            {
                while (current2 != null) {
                    PolyNode temp = (PolyNode) current2.data;
                    PolyNode t = new PolyNode(0 - temp.coefficient, temp.exponent);
                    R.add(t);
                    j++;
                    current2 = current2.next;
                }
            }
            if (current2 == null)
            {
                while (current1 != null) {
                    PolyNode temp = (PolyNode) current1.data;
                    PolyNode t = new PolyNode(temp.coefficient, temp.exponent);
                    R.add(t);
                    // now advance pointer and iterator of first List
                    i++;
                    current1 = current1.next;
                }

            }

        } catch (Exception e) {
            System.out.println("Error");
            System.exit(0);

        }
        currentr = R.head;
        int[][] res = new int[R.size()][2];
        i = 0;
        try {
            while (currentr != null) {
                PolyNode temp = (PolyNode) currentr.data;
                res[i][0] = temp.coefficient;
                res[i][1] = temp.exponent;
                i++;
                currentr = currentr.next;
            }
        } catch (Exception e) {
            System.out.println("Error");
            System.exit(0);
        }
        
        return res;

    }
    
public int[][] multiply(char poly1, char poly2) {

   SingleLinkedList Selected1 = null;
        SingleLinkedList Selected2 = null;

  switch (poly1) {
            case 'A':
                Selected1 = A;
                break;
            case 'B':
                Selected1 = B;
                break;
            case 'C':
                Selected1 = C;
                break;
            default:
                System.out.print("Error");
                System.exit(0);
            }

            switch (poly2) {
            case 'A':
                Selected2 = A;
                break;
            case 'B':
                Selected2 = B;
                break;
            case 'C':
                Selected2 = C;
                break;
            default:
                System.out.print("Error");
                System.exit(0);
            }


    if (Selected1 == null || Selected2 == null ) {
        System.out.println("Error");
         System.exit(0);
    }

    int range = calculateRange(Selected1, Selected2);
    AddResultList(range);

    performMultiplication(Selected1, Selected2);

    return convertResultToArray();
}


private int calculateRange(SingleLinkedList Selected1, SingleLinkedList Selected2) {
    ListNode currentOne = Selected1.head;
    ListNode currentTwo = Selected2.head;
    if (currentOne != null && currentTwo != null) {
        PolyNode temp1 = (PolyNode) currentOne.data;
        PolyNode temp2 = (PolyNode) currentTwo.data;
        return temp1.exponent + temp2.exponent;
    }
    return 0;
}

private void AddResultList(int range) {
    for (int i = range; i >= 0; i--) {
        R.add(new PolyNode(0, i));
    }
}

private void performMultiplication(SingleLinkedList Selected1, SingleLinkedList Selected2) {
    ListNode resultNode = R.head;

    while (resultNode != null) {
        PolyNode resultTerm = (PolyNode) resultNode.data;
        int temp = 0;
        ListNode currentOne = Selected1.head;

        while (currentOne != null) {
            PolyNode termOne = (PolyNode) currentOne.data;
            int currentExponent = resultTerm.exponent - termOne.exponent;
            ListNode currentTwo = Selected2.head;

            while (currentTwo != null) {
                PolyNode termTwo = (PolyNode) currentTwo.data;

                if (termTwo.exponent == currentExponent) {
                    temp += termTwo.coefficient * termOne.coefficient;
                    break; // Exit the loop since we found the matching exponent
                }

                currentTwo = currentTwo.next;
            }

            currentOne = currentOne.next;
        }

        resultTerm.coefficient = temp;
        resultNode = resultNode.next;
    }
}

private int[][] convertResultToArray() {
    ListNode resultNode = R.head;
    int[][] result = new int[R.size()][2];
    int i = 0;

    while (resultNode != null) {
        PolyNode temp = (PolyNode) resultNode.data;
        result[i][0] = temp.coefficient;
        result[i][1] = temp.exponent;
        i++;
        resultNode = resultNode.next;
    }
    return result;
}

}



public class Solution {

    public static void main(String[] args) {
        PolynomialSolver poly = new PolynomialSolver();

        Scanner sc = new Scanner(System.in);
       
        while (sc.hasNext()) {
            String command = sc.next();
            sc.nextLine();
            char ch;
            char ch1;
            char ch2;
            switch (command) {
             case "add":
                ch1 = sc.next().charAt(0);
                sc.nextLine();
                ch2 = sc.next().charAt(0);
                poly.add(ch1, ch2);
                System.out.println(poly.print('R'));
                break;
                
            case "set":
                try {
                    String test = sc.nextLine();
                    ch = test.charAt(0);

                    String sin = sc.nextLine().replaceAll("\\[|\\]", "");
                    String[] s = sin.split(",");
                    int[][] arr = new int[s.length][2];
                    if (s.length == 1 && s[0].isEmpty()) {
                        System.out.println("Error");
                        System.exit(0);
                    } else {
                        int j = 0;
                        while (j < s.length) {
                            if (s[j].isEmpty() || sin.charAt(sin.length() - 1) == ',' || sin.contains(" ")) {
                                System.out.println("Error");
                                System.exit(0);
                            }
                            j++;
                        }

                        for (int i = 0; i < s.length; ++i) {
                            arr[i][0] = Integer.parseInt(s[i]);
                            arr[i][1] = s.length - i - 1;
                        }
                    }
                    poly.setPolynomial(ch, arr);
                    break;
                } catch (Exception e) {
                    System.out.println("Error");
                    System.exit(0);
                }

          case "print":
                ch = sc.next().charAt(0);
                System.out.println(poly.print(ch));
                break;

        case "clear":
                ch = sc.next().charAt(0);
                poly.clearPolynomial(ch);
                break;
         case "sub":
                ch1 = sc.next().charAt(0);
                sc.nextLine();
                ch2 = sc.next().charAt(0);
                poly.subtract(ch1, ch2);
                System.out.println(poly.print('R'));
                break;
                
                
        case "mult":
                ch1 = sc.next().charAt(0);
                sc.nextLine();
                ch2 = sc.next().charAt(0);
                poly.multiply(ch1, ch2);
                System.out.println(poly.print('R'));
                break;
                
                
        case "eval":
                ch = sc.next().charAt(0);
                sc.nextLine();
                float in = sc.nextFloat();
                float out = poly.evaluatePolynomial(ch, in);
                if (out - (int) out == 0.0)
                    System.out.println((int) out);
                else
                    System.out.println((out));

                break;

            default:
                System.out.println("Error");
                System.exit(0);
            }

        }

    }
}
