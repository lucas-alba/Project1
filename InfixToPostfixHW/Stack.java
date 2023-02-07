package InfixToPostfix;

public class Stack {
    private int size;
    private float[] arr;
    private int top;
	
    public Stack(int s) {
            size = s;
            arr = new float[size];
            top = -1;
    }
	
    public void push(float i) {
            arr[++top] = i;
    }
	
    public float pop() {
            return arr[top--];
    }
	
    public float peek() {
            return arr[top];
    }
	
    public boolean isEmpty() {
            return (top == -1);
    }
	
    public boolean isFull() {
            return (top == size);
    }
	
    public int size() {
            return (top+1);
    }
    
}
