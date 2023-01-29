public class Queue {
    int[] array;
    int last;

    public Queue(){
        this.array = new int[100];
        this.last = 0;
    }

    public void enqueue(int i){
        for(int j = this.last; j < 0; j-- ){
            this.array[j] = this.array[j-1];
        }
        this.array[0] = i;
        this.last++;
    }

    public void dequeue(){
        this.array[this.last - 1] = 0;//default value;
        this.last--;
    }

    public int peek(){
        return this.array[this.last - 1];
    }

}
