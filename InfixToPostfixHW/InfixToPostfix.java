package InfixToPostfix;

import javax.swing.*;
import java.util.Scanner;

public class InfixToPostfix {

    static int precedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
            case '%':
                return 2;

            case '^':
                return 3;
        }
        return -1;
    }

    static String InfixToPostfix(String str) {
        String ret = new String("");
        Stack stack = new Stack(str.length());
        str = str.replaceAll("\\s+", ""); // Removes all spaces

        for (int i = 0; i < str.length(); i++) {
            // Read item from output
            char c = str.charAt(i);

            // Item read is letter or digit
            // Write it to output
            if (Character.isLetterOrDigit(c)) {
                ret += c;
                // Following while loop allows support for ints > 9 and floating point numbers
                // While current letter or digit is succeeded by a decimal or another letter or digit
                // Write next to output
                while (i + 1 < str.length() && (str.charAt(i + 1) == '.' || Character.isLetterOrDigit(str.charAt(i + 1)))) {
                    ret += str.charAt(++i);
                }
                ret += ' ';
            }

            // Item read is open parenthesis
            // Push it on stack
            else if (c == '(') {
                stack.push(c);
            }

            // Item read is close parenthesis
            // While stack is not empty, repeat the following
            // Pop an item
            // If item is not (, write it to output
            // Quit loop if item is (
            else if (c == ')') {
                while(!stack.isEmpty() && stack.peek() != '(') {
                    ret += (char) stack.pop(); // pop and write item to output
                    ret += ' ';
                }
                stack.pop();
            }

            // Item read is operator (opThis)
            // If stack empty
            // Push opThis
            // Otherwise
            // While stack not empty, repeat
            // Pop an item
            // If item is (, push it
            // If item is operator (opTop), and
            // If opTop < opThis, push opTop
            // if opTop >= opThis, output opTop
            // Quit loop if opTop < opThis or item is (
            else {
                while (!stack.isEmpty() && precedence((char) stack.peek()) >= precedence(c)) {
                    ret += (char) stack.pop(); // pop and output opTop if opTop < opThis and stack not empty
                    ret += ' ';
                }
                stack.push(c); // if item is ( or opTop < opThis push it
            }
        }

        // No more items read
        // While stack not empty
        // Pop item, output it
        while (!stack.isEmpty()) {
            ret += (char) stack.pop();
            ret += ' ';
        }

        return ret;
    }

    public static float evaluatePostfix(String str) {
        // TODO create postfix evaluation function which takes postfix expression input as string and outputs evaluation as float
        // must support multi-digit and floating point numbers
        // must return "Invalid expression" if expression is invalid
        String nums = "";
        Stack stack = new Stack(str.length());
        float num1; //will store the first number of the expression
        float num2; //will store the second number of the expression
        float answer = Float.POSITIVE_INFINITY; //encoded with a sentinel value incase something goes wrong
        for(int i = 0; i < str.length(); i++)//goes through the entire string allowing each character to be analyzed one by one
        {
            System.out.println("EXECUTING");
            System.out.println("The next character is: " + str.charAt(i));
            if(Character.isDigit(str.charAt(i)))//determines whether the current character is a number or not
            {
                nums = "";//empty string needed so you can add characters to it
                while(str.charAt(i) != ' ')//used for larger numbers
                {
                    System.out.println("EXECUTING WHILE LOOP");
                    nums += str.charAt(i++);
                }
                System.out.println(nums + " Will be added");
                stack.push(Float.parseFloat(nums));//adds the first number to the stack
                System.out.println("executed push");
            }
            else if(str.charAt(i) != ' ')
            {
                System.out.println("EXECUTING ELSE STATEMENT");
                num2 = stack.pop();//pops the second number
                num1 = stack.pop();//pops the first number
                switch(str.charAt(i))//used to do the arithmetic
                {
                    case '+':
                        System.out.println("Will add " + num1 + " to " + num2);
                        answer = num1 + num2;
                        System.out.println("The answer is: " + answer);
                        stack.push(answer);
                        break;
                    case '-':
                        System.out.println("Will add " + num1 + " by " + num2);
                        answer = num1 - num2;
                        System.out.println("The answer is: " + answer);
                        stack.push(answer);
                        break;
                    case '*':
                        System.out.println("Will multiply " + num1 + " with " + num2);
                        answer = num1 * num2;
                        System.out.println("The answer is: " + answer);
                        stack.push(answer);
                        break;
                    case '/':
                        System.out.println("Will divide" + num1 + " by " + num2);
                        answer = num1 / num2;
                        System.out.println("The answer is: " + answer);
                        stack.push(answer);
                        break;
                    case '%':
                        System.out.println("Will mod " + num1 + " with " + num2);
                        answer = num1 % num2;
                        System.out.println("The answer is: " + answer);
                        stack.push(answer);
                        break;
                    case '^':
                        System.out.println("Will raise " + num1 + " to the power of " + num2);
                        answer = (float) Math.pow(num1, num2);
                        System.out.println("The answer is: " + answer);
                        stack.push(answer);
                        break;
                    default://setinel case
                        System.out.println("ERROR NOT A RECOGNIZED OPERATOR");
                        return Float.POSITIVE_INFINITY;
                }
            }
        }
        if(answer == Float.POSITIVE_INFINITY)
        {
            System.out.println("ERROR INVALID EXPRESSION");
            return Float.POSITIVE_INFINITY;
        }
        return answer;
    }

    public static void main(String[] args) {
        // Receives expression as string input
        //System.out.println("Enter an infix expression: ");
        //Scanner keyboard = new Scanner(System.in);
        //String expression = keyboard.nextLine();
        String expression = JOptionPane.showInputDialog("Enter  an Infix Expression");
        // Infix to postfix
        JOptionPane.showMessageDialog(null, InfixToPostfix(expression), "Infix To Post", JOptionPane.PLAIN_MESSAGE);
        //System.out.println("Postfix expression: " + InfixToPostfix(expression));

        // Evaluate postfix
        JOptionPane.showMessageDialog(null, evaluatePostfix(InfixToPostfix(expression)), "Postfix expression evaluated", JOptionPane.PLAIN_MESSAGE);
        //System.out.println("Postfix expression evaluated: " + evaluatePostfix(InfixToPostfix(expression)));
		
        //keyboard.close();
    }
}