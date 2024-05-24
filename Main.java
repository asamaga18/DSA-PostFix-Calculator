import java.io.*;
import java.util.Scanner;
import java.util.Stack;
//we gave up on extra credit
//errorRep was used to try and fix an issue but as the program was debugged error and errorRep are used interchangibly

class Main {
  static Stack<Integer> calculator = new Stack<Integer>();
  static boolean error = false;//boolean flag
  static boolean errorRep = false; //weird flag used to fix repeating issue
  public static void main(String[] args) {
    
    try 
    {
      File myObj = new File("PostFixInput.txt");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String line = myReader.nextLine();
        errorRep = false;
        while(!calculator.isEmpty()){calculator.pop();}//had an issue iwth the stack not being clear when the previous line had an issue
        for (int i = 0; i < line.length(); i++)
        {
          char opr = line.charAt(i);
          error = false;
          if ((opr == '+'||opr == '-'||opr == '*'||opr == '/')&&(!errorRep)) // if operator, do operation
          {
            if (opr == '+')
              add();
            if (opr == '-')
              subtract();
            if (opr == '*')
              multiply();
            if (opr == '/')
              divide();
          }
            
          else if ((opr >= 48 )&&(opr  <= 57)) //if number, add to stack
          {
           calculator.push(Character.getNumericValue(opr));//convert to number and add to stack
          }
            
          else if (!errorRep)
          { 
            error = true;
            errorRep = true;
            System.out.println(line+"-> Invalid Character Detected"); //if not number throw error
          }
        }
        if (!error && !errorRep) //if no errors found show answer, otherwise, next line
        {
          int answer = calculator.pop();
          if (calculator.isEmpty()) //looks if empty, throws error if not empty
          {
            System.out.println(line + " = "+ answer);
          }
          else
          {
            System.out.println(line+"-> Too many Numbers");
          }
        }
      }
    }
    catch (FileNotFoundException e) {//if file not found
      System.out.println("-> An error occurred.");
      e.printStackTrace();
    }
    
    
  }

// all operations check if empty before and after popping the second number
  public static void add() {  //addition
    if (!calculator.isEmpty()) {
       int temp = calculator.pop();
       if (!calculator.isEmpty())
       {
         calculator.push(calculator.pop() + temp);
       }
       else 
       {
         if (!errorRep){System.out.println("Line Has Too Many Operators");}
         errorRep=true;
         error = true;
       }
     }
    else 
    {
      System.out.println("Line Has Too Many Operators");
      error = true;
      errorRep = true;
    }
  }
  public static void subtract() {//subtraction
    if (!calculator.isEmpty()) {
       int temp = calculator.pop();
       if (!calculator.isEmpty())
       {
         calculator.push(calculator.pop() - temp);
       }
       else 
       {
         if (!errorRep){System.out.println("Line Has Too Many Operators");}
         errorRep=true;
         error = true;
       }
     }
    else 
    {
      System.out.println("Line Has Too Many Operators");
      error = true;
      errorRep = true;
    }
  }
    public static void multiply() {//multiplication
    if (!calculator.isEmpty()) {
       int temp = calculator.pop();
       if (!calculator.isEmpty())
       {
         calculator.push(calculator.pop() * temp);
       }
       else 
       {
         if (!errorRep){System.out.println("Line Has Too Many Operators");}
         errorRep=true;
         error = true;
       }
     }
    else 
    {
      System.out.println("Line Has Too Many Operators");
      error = true;
      errorRep = true;
    }
  }
  
    public static void divide() { //division
    if (!calculator.isEmpty()) {
      int temp = calculator.pop();
      if (temp == 0)
      {
        System.out.println("Line Contains Division By Zero");//also checks for division by 0
        error = true;
        errorRep = true;
      }
      else
        if (!calculator.isEmpty())
       {
         calculator.push(calculator.pop() / temp);
         
       }
       else 
       {
         if (!errorRep){System.out.println("Line Has Too Many Operators");}
         errorRep=true;
         error = true;
       }
     }
    else 
    {
      System.out.println("Line Has Too Many Operators");
      error = true;
      errorRep = true;
    }
  }

}