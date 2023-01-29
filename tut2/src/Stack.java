public class Stack {
    int[] array;
    int last;

    public Stack(){
        this.array = new int[10];
        this.last = 0;
    }

    public void push(int i){
        this.array[last] = i;
        last++;
    }

    public void pop(){
        last--;
        this.array[last] = 0; //default value;
    }

    public int peek(){
        return this.array[last - 1];
    }
}
