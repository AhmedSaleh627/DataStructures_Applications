import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;



interface IStack {
  
  
  public Object pop();

  public Object peek();
  

  public void push(Object element);

  public boolean isEmpty();
  

  public int size();
}

class MyStack implements IStack {
    private int top;
    private int capacity;
    private Object[] stack;
    private int[] inputArray;

    MyStack() {
        top = -1;
        capacity = 100;
        stack = new Object[capacity];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public Object pop() {
        if (isEmpty()) {
            throw new NullPointerException();
        }
        else
        {
            return stack[top--];
        }
        
    }

    
    
    public Object peek() {
        if (isEmpty()) {
            throw new NullPointerException();
        }
        else
        {
            return stack[top];
        }
        
    }

    public int size() {
        return top + 1;
    }

public void push(Object data) {
    try {
        if (top < capacity - 1) {
            stack[++top] = data;
        } else {
            System.out.println("Error");
        }
    } catch (NumberFormatException e) {
        System.out.println("Error");
    }
}


    public void printStack() {
        if (isEmpty()) {
            System.out.println("[]");
        } else {
            System.out.print("[");
            for (int i = top; i >= 0; i--) {
                System.out.print(stack[i]);
                if (i > 0) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        }
    }

   

    private static int[] parseInputArray(String inputString) {
    String[] inputArrayStr = inputString.replaceAll("\\[|\\]", "").split(", ");
    int[] inputArray = new int[inputArrayStr.length];

    for (int i = 0; i < inputArrayStr.length; i++) {
        try {
            inputArray[i] = Integer.parseInt(inputArrayStr[i].trim());
        } catch (NumberFormatException e) {
            System.out.println("Error");
            // Handle the error or exit the program as needed
            System.exit(1);
        }
    }

    return inputArray;
}

}

interface IExpressionEvaluator {
  

public String infixToPostfix(String expression);
  

  
public int evaluate(String expression);

}


public class Evaluator implements IExpressionEvaluator {
    
    int a;
    int b;
    int c;
    
  public void Setters(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    public int Getters(char x) {
        switch (x) {
        case 'a':
            return a;
        case 'b':
            return b;
        case 'c':
            return c;
        default:
            throw new RuntimeException();
        }
    }
    
    
    public int prec(char x) {
        if (x == '+' || x == '-')  
        return 1;  
        if (x == '*' || x == '/' || x == '%')  
        return 2;
        if (x == '^')  
        return 3;
        return 0;  
    }
    
    
    public String infixToPostfix(String expression) {
             
        char symbol;  
        String postfix = "";  
        MyStack stack = new MyStack();
        for(int i=0;i<expression.length();++i)  
        {  
            symbol = expression.charAt(i);  
            if (Character.isLetter(symbol))
            {
                postfix = postfix + symbol;
            }                
              
            else if (symbol=='(')  
            {  
                stack.push(symbol);  
            }  
            else if (symbol==')')  
            {  
                while ((char)stack.peek() != '(')  
                {  
                    postfix = postfix + stack.pop();  
                }  
                stack.pop();         
            }  
            else  
         
            {  
                while (!stack.isEmpty() && !((char)stack.peek()=='(') && prec(symbol) <= prec((char)stack.peek()))
                {
                    postfix = postfix + stack.pop();  
                }                        
                stack.push(symbol);  
            }  
        }  
        while (!stack.isEmpty())
        {
            postfix = postfix + stack.pop();  
        }            
        
        return postfix;
    }
    
    
public int evaluate(String expression) {
    MyStack stack = new MyStack();
    for (int i = 0; i < expression.length(); ++i) {
        char current_operator = expression.charAt(i);
        if (!Character.isLetter(current_operator)) {
                int operand_1 = 0;
                int operand_2 = 0;
                
                try {
                    operand_2 = (int) stack.pop();
                    operand_1 = (int) stack.pop();
                } catch (Exception e) {
                    if (current_operator != '-')
                        throw new RuntimeException();
                    operand_1 = 0;
                }
                switch (current_operator) {
                case '*':
                    stack.push(operand_1 * operand_2);
                    break;
                case '/':
                    stack.push(operand_1 / operand_2);
                    break;
                case '^':
                    if (operand_2 < 0)
                        stack.push(0);
                    else
                       stack.push( (int) Math.pow(operand_1, operand_2) );
                    break;    
                case '+':
                    stack.push(operand_1 + operand_2);
                    break;
                case '-':
                    stack.push(operand_1 - operand_2);
                    break;
                default:
                    throw new RuntimeException();
                }  
            }
            else  {
                stack.push(Getters(current_operator));  
            }
        }
        return (int) stack.pop();
    }

public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String expression = scanner.nextLine().replace("^--", "^").replace("*--", "*").replace("/--", "/").replace("+--", "+").replace("(--", "(").replace("(---", "-");

    expression = processDoubleNegatives(expression);

    int a = readVariable("a", scanner);
    int b = readVariable("b", scanner);
    int c = readVariable("c", scanner);

    scanner.close();
    Evaluator evaluator = new Evaluator();

    try {
        evaluator.Setters(a, b, c);

        String postfixExpression = evaluator.infixToPostfix(expression);

        // Check if the last element of the infix expression is an operator
        char lastChar = expression.charAt(expression.length() - 1);
        if (isOperator(lastChar)) {
            System.out.println("Error");
            return; // Exit the program or handle the error as needed
        }

        int result = evaluator.evaluate(postfixExpression);
        System.out.println(postfixExpression);
        System.out.println(result);
    } catch (RuntimeException e) {
        System.out.println("Error");
    }
}

// Helper method to check if a character is an operator
private static boolean isOperator(char c) {
    return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
}

    private static String processDoubleNegatives(String expression) {
        if (expression.startsWith("--")) {
            expression = expression.replaceFirst("--", "");
        }
        if (expression.contains("--")) {
            expression = expression.replaceAll("--", "+");
        }
        return expression;
    }

    private static int readVariable(String variableName, Scanner scanner) {
    try {
        String variableInput = scanner.nextLine().replaceAll("[a-c]=", "");
        return Integer.parseInt(variableInput);
    } catch (NumberFormatException e) {
        System.out.println("Error");
        System.exit(1); // Exiting the program in case of invalid input
        return 0; 
    }
}
}
