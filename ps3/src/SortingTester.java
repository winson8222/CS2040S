import javax.security.auth.kerberos.KerberosTicket;
import java.util.Random;
public class SortingTester {

    public static boolean checkSort(ISort sorter, int size) {
        // TODO: implement this
        Random rand = new Random();
        KeyValuePair[] intarray = new KeyValuePair[size];
        for(int i = 0; i < size; i++){
            intarray[i] = new KeyValuePair(rand.nextInt(10), 1 );
        }
        int cost = sorter.sort(intarray);
        for (int i = 1; i < size; i++) {
            if(intarray[i].compareTo(intarray[i - 1]) == -1){
                return false;
            }
        }
        System.out.format("Cost: %d\n", cost);
        return true;
    }

    public static boolean isStable(ISort sorter, int size) {

        // TODO: implement this
        Random rand = new Random();
        KeyValuePair[] intarray = new KeyValuePair[size];
        for (int i = 0; i < size; i++) {
            intarray[i] = new KeyValuePair(rand.nextInt(2), i);
        }
        sorter.sort(intarray);

        for (int i = 1; i < size; i++) {
            if (intarray[i - 1].compareTo(intarray[i]) == 0) {
                if (intarray[i - 1].getValue() > intarray[i].getValue()) {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean checkSortSample(ISort sorter, KeyValuePair[] sample) {
        // TODO: implement this
        int cost = sorter.sort(sample);
        boolean state = true;
        for (int i = 1; i < sample.length; i++) {
//            System.out.format("(%d , %d)\n", sample[i].getKey(), sample[i].getValue());
            if(sample[i].compareTo(sample[i - 1]) == -1){
                state = false;
            }
        }
        System.out.format("Cost: %d\n", cost);
        return state;
    }


    public static void main(String[] args) {
        // TODO: implement this
        KeyValuePair[] sample = new KeyValuePair[10];
        int[] key = new int[] {2,3,4,5,6,7,8,9,10,1};
//        int[] key = new int[] {1,1,1,1,1,1,1,1,1,1}; //input the array that I want to test
        int[] value = new int[]{0,0,0,0,0,0,0,0,0,0};

        for(int i = 0; i < sample.length; i++){
            sample[i] = new KeyValuePair(key[i], value[i]);
        }

        ISort sortingObject = new SorterE();
        System.out.println(checkSortSample(sortingObject, sample));
        System.out.println(isStable(sortingObject, 1000000));
        System.out.println(checkSort(sortingObject, 1000000));
        System.out.println(checkSort(sortingObject, 1000000));
        System.out.println(checkSort(sortingObject, 1000000));
        System.out.println(checkSort(sortingObject, 1000000));

    }
}
