public class Homework {
    public static int findmax(int[] hw){
        int maxk = 0;
        for(int i = 0; i < hw.length; i++){
            maxk = maxk + hw[i];
        }
        return maxk;
    }

    public static boolean hoursNeeded(int[] hw, int k, int h){
        int hours = 0;
        int currentK = k;
        int i = 0;
        while(i < hw.length){
            if(hw[i] > currentK){
                currentK = currentK * 2;
                hours++;
            }
            if(hw[i] < currentK) {
                hours++;
                i++;
                currentK = k;
            } else {
                hours++;
                i++;
                currentK = k;
            }
        }
        return hours<=h;
    }
    public static void main(String[] args){
        int h = //maxh
        int mink = 0;
        int maxk;
        maxk = findmax();
        //binary search from 0 to max to find smallest maxk that hoursNeeded return ture

    }
}
