///////////////////////////////////
// This is the main shift register class.
// Notice that it implements the ILFShiftRegister interface.
// You will need to fill in the functionality.
///////////////////////////////////

/**
 * class ShiftRegister
 * @author
 * Description: implements the ILFShiftRegister interface.
 */
public class ShiftRegister implements ILFShiftRegister {
    ///////////////////////////////////
    // Create your class variables here
    ///////////////////////////////////
    // TODO:
    int[] register;
    int len;
    int tapval;

    ///////////////////////////////////
    // Create your constructor here:
    ///////////////////////////////////
    ShiftRegister(int size, int tap) {
        // TODO:
        len = size;
        tapval = tap;

    }

    ///////////////////////////////////
    // Create your class methods here:
    ///////////////////////////////////
    /**
     * setSeed
     * @param seed
     * Description: I set the register of this the new class initialised to be the seed
     * given as an array
     */
    @Override
    public void setSeed(int[] seed) {
        // TODO:
        register = seed;

    }

    /**
     * shift
     * @return
     * Description: Find the result of the XOR of the last byte in the array(most significant)
     * with the (value of tp)th element. Make a new array and set the first elemt(least significant) to the
     * result of XOR. Set the ith element of the new array to the i-1th element of the old register
     */
    @Override
    public int shift() {
        // TODO:

        int[] newSeed = new int[len];
        int feedbackBit = register[len - 1] ^ register[tapval];
        newSeed[0] = feedbackBit;
        for (int i = 1; i < len; i++) {
            newSeed[i] = register[i - 1];

        }
        setSeed(newSeed);
        return feedbackBit;
    }
//        if(register[len - 1] == register[tapval]){
//            newSeed[0] = 0;
//            for(int i = 1; i < len; i++){
//                newSeed[i] = register[i - 1];
//            }
//
//            setSeed(newSeed);
//            return 0;
//        } else {
//            newSeed[0] = 1;
//            for(int i = 1; i < len; i++){
//                newSeed[i] = register[i - 1];
//            }
//            setSeed(newSeed);
//            return 1;
//        }
//    }

    /**
     * generate
     * @param k
     * @return
     * Description: setting v to 0, value of v is updated using the return value of shift
     * to give the integer value of the binary
     */
    @Override
    public int generate(int k) {
        // TODO:
        int v = 0;

        for(int i = 0; i < k; i++){
            v = v*2 + shift();
        }


        return v;
    }

    /**
     * Returns the integer representation for a binary int array.
     * @param array
     * @return
     */
    private int toDecimal(int[] array) {
        // TODO:
        return 0;
    }
}