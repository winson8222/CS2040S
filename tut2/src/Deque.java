public class Deque extends Queue {
    int first;

    public Deque(){
        super();
        this.first = 0;
    }

    public void dequeue_front(){
        dequeue();
    }

    public void enqueue_back(int i){
        enqueue(i);
    }

    public void dequeue_back(){
        for(int i = 0; i < this.last; i++){
            this.array[i] = this.array[i + 1];
        }
        this.array[this.last - 1] = 0;//default value
    }

    public void enqueue_front(int i){
        this.array[last] = i;
        last++;
    }
}
