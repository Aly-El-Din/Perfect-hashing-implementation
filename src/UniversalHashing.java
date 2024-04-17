import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class UniversalHashing {
    private int bits_key;//Number of bits of the key
    private int bits_indices;//Number of bbits_indicests of indices
    private int M;//Size of the hash table/ number of buckets
    private int number_of_keys;
    private ArrayList<ArrayList<Integer>>h;
    public UniversalHashing(int bits_key,int M){
        this.bits_key=bits_key;
        this.number_of_keys= bits_key%2==0 ? (int)Math.pow(2,bits_key)+1:(int)(Math.pow(2,bits_key));
        this.M=M;
        this.bits_indices=(int)(Math.log(M)/Math.log(2)); //i = log2(M)
        h=new ArrayList<>();
    }
    public void computeIndex(int key){
        System.out.println("I "+bits_indices+" u "+bits_key);
        randomizeMatrix();
        ArrayList<Integer> E=getBinaryKey(key);
        ArrayList<Integer> index=matrixMultiplication(E);
        System.out.println("index:");
        for(int e:index){
            System.out.print(e+" ");
        }
        System.out.print("\nh(x)= ");
        int hash=convertToDecimal(index);
        System.out.println(hash);
    }
    private void randomizeMatrix(){
        int rows=bits_indices;
        int cols=bits_key;
        for(int i=0;i<rows;i++){
            ArrayList<Integer>innerList=new ArrayList<>();
            for(int j=0;j<cols;j++){
                int randomBinary=new Random().nextInt(2);
                innerList.add(randomBinary);
            }
            h.add(innerList);
        }
        System.out.println("h:");
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                System.out.print(h.get(i).get(j));
            }
            System.out.println();
        }
    }
    private ArrayList<Integer> getBinaryKey(int key){
        ArrayList<Integer>binary=new ArrayList<>();
        for(int i=0;i<bits_key;i++){
            binary.add(0);
        }
        int i=0;
        while(key>0){
            binary.add(i,key%2);
            key/=2;
            i++;
        }
        System.out.println("E:");
        for(int j=0;j<bits_key;j++){
            System.out.print(binary.get(j)+" ");
        }
        System.out.println();
        return binary;
    }
    private ArrayList<Integer>matrixMultiplication(ArrayList<Integer>binaryKey){
        ArrayList<Integer>index=new ArrayList<>();

        for(int i=0;i<bits_indices;i++){
            int element=0;
            for(int j=0;j<bits_key;j++){
               element+=h.get(i).get(j)*binaryKey.get(j);
            }
            index.add(element%2);
        }
        return index;
    }
    private int convertToDecimal(ArrayList<Integer>binaryArray){
        int decimal = 0;
        for (int i = 0; i < binaryArray.size(); i++) {
            int power = binaryArray.size() - 1 - i; // Calculate the power of 2 for the current bit
            decimal += binaryArray.get(i) * Math.pow(2, power);
        }
        return decimal;
    }
}