/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/
import java.util.BitSet;
import java.util.Vector;
import java.util.ArrayList;

public class Main
{

	public static void PrintMats(ArrayList<byte[][]> R){
	    System.out.println(R.size());
	    for(int i=0; i<R.size(); i++){
	        System.out.println("R["+i+"]: ");
	        for(int j=0; j<R.get(i).length; j++){
	            for(int k=0; k<R.get(i)[j].length; k++){
	                System.out.print(R.get(i)[j][k] + "   ");
	            }
	            System.out.println();
	        }
	    }
	}
    
    
   	public static void PrintMats2(ArrayList<byte[][]> R){
	    System.out.println(R.size());
	    for(int i=0; i<R.size(); i++){
	        System.out.println("R["+i+"]: ");
	        for(int j=R.get(i).length-1; j>=0; j--){
	            for(int k=0; k<R.get(i)[j].length; k++){
	                System.out.print(R.get(i)[j][k] + "   ");
	            }
	            System.out.println();
	        }
	    }
	}
    
    
    public static int HammingWeight(int n) {
        int count = 0;
        if (n==0) return 0;
        int loop = (int) (Math.log(n)/Math.log(2));
        //System.out.print(loop);
        for (int i = 0; i <=loop ; ++i) {
            if (((n >>> i) & 1) == 1) {
                ++count;
                }
        }
        return count;
    }
    
    public static int HammingWeight(String n) {
        int count = 0;
        for(int i=0; i<n.length(); i++){
            if (n.charAt(i) =='1') count++;
        }
        return count;
    }    

    public static String Cumulative1(String strIn){
        StringBuilder strResult = new StringBuilder("");
	    strResult.append(strIn.charAt(0));
	    int intTemp=0;
	    for(int i=1; i<strIn.length(); i++){
	        intTemp = Character.getNumericValue(strResult.charAt(i-1)) + Character.getNumericValue(strIn.charAt(i));
	        strResult.append(intTemp);
	    }
	    return  strResult.toString();
    }    
    
    public static ArrayList<String> Cumulative(String strIn){
        ArrayList<String> arrStrResult = new ArrayList<String>();
        StringBuilder strResult = new StringBuilder("");
	    strResult.append(strIn.charAt(0));
	    arrStrResult.add(strResult.toString());
	    int intTemp=0;
	    for(int i=1; i<strIn.length(); i++){
	        intTemp = Integer.parseInt(arrStrResult.get(i-1)) + Character.getNumericValue(strIn.charAt(i));
	        arrStrResult.add(Integer.toString(intTemp));
	    }
	    return arrStrResult;
    }    

  public static BitSet convert(long value) {
    BitSet bits = new BitSet();
    int index = 0;
    while (value != 0L) {
      if (value % 2L != 0) {
        bits.set(index);
      }
      ++index;
      value = value >>> 1;
    }
    return bits;
  }

  public static long convert2(BitSet bits) {
    long value = 0L;
    for (int i = 0; i < bits.length(); ++i) {
      value += bits.get(i) ? (1L << i) : 0L;
    }
    return value;
  }
    public static String convert3(BitSet bits) {
    String value = "";
    for (int i = 0; i < bits.length(); ++i) {
      value += bits.get(i) ? "1" : "0";
    }
    return value;
  }

	public static void main(String[] args) {

        // n=32 L=7 and U=19 conflict boundaries! Scheme will accept 20,21,22
        // n=32 L=6 and U=19 conflict boundaries! Scheme will accept 5,20,21,22
        
	    int n = 32; //data value range; 0 <= L <= B < n-1
		int b = (int) (Math.log(n)/Math.log(2));
        int L = 6;  //L >= 0
	    int U = 19; //U <= n-1
	    
	    
	    System.out.println("n: " + n);
	    System.out.println("b: " + b);
	    System.out.println("Lower bound: " + L);
	    System.out.println("Upper bound: " + U);
	    
	    
	    ArrayList<String> QRange = new ArrayList<String>();
	    //BitSet bs = new BitSet();
	    String strTemp="";
	    for(int i=L; i<=U; i++){
	        strTemp = Integer.toBinaryString(n|i).substring(1);
	        QRange.add(strTemp);
	    }
	    
	    System.out.println("Query range (binary representation): ");
	    for (String Q : QRange) {
	        System.out.print(Q+", ");
	    }
	    System.out.println();
	    
	    System.out.println("Hamming weights: ");
	    for (String Q : QRange) {
	        System.out.print("    "+ HammingWeight(Q) + ", ");
	    }

	    ArrayList<ArrayList<String>> S = new ArrayList<ArrayList<String>>();
	    ArrayList<ArrayList<ArrayList<String>>> C = new ArrayList<ArrayList<ArrayList<String>>>();

	    ArrayList<String> SElement = new ArrayList<String>();
	    ArrayList<ArrayList<String>> CElement = new ArrayList<ArrayList<String>>();

	    for(int i=0; i<=b; i++){
	        S.add(i, SElement);
	        C.add(i, CElement);
	    }
	    
        int intIndex = 0;
        for(int i=0; i<=b; i++){
            SElement = new ArrayList<String>();
            CElement = new ArrayList<ArrayList<String>>();
            for (String Q : QRange){
                intIndex = HammingWeight(Q);
                if (intIndex == i){
                    SElement.add(Q);
                    CElement.add(Cumulative(Q));
                }
            }
            S.set(i,SElement);
            C.set(i,CElement);
        }

	    System.out.println("");
	    System.out.println("S: ");
	    System.out.println(S);
	    System.out.println("C: ");
	    System.out.println(C);

	    ArrayList<byte[][]> R = new ArrayList<byte[][]>();
	    byte[][] RElement;
	    for(int i=0; i<=b; i++){
	        RElement = new byte[i+1][b];
	        R.add(RElement);
	        //System.out.println(R);
	    }
	    
	    
	   // System.out.println(R.get(5).length);

	    for(int i=0; i<=b; i++)
	        for(int j=0; j<R.get(i).length; j++)
	            for(int k=0; k<R.get(i)[j].length; k++){
	                R.get(i)[j][k] = 0;
	            }
	            
   
	    int intTemp = 0;
	    for(int i=0; i<=b; i++){
	        for(int j=0; j<C.get(i).size(); j++){
	            for(int k=0; k<b;k++){
	                intTemp = Integer.parseInt(C.get(i).get(j).get(k));
	                R.get(i)[intTemp][k]=1;
	            }
	        }
	    }
	    
	    //PrintMats(R);
	    PrintMats2(R);
	    
	    
	    

        //Single point query
        /*
        int qryQ =10;
        String qryBinary = Integer.toBinaryString(n|qryQ).substring(1);
        System.out.println("Query: " + qryQ + "\nBinary: " + qryBinary);
        int qryHW = HammingWeight(qryBinary);
        System.out.println("Query HW:" + qryHW);
        ArrayList<String> qryCS = new ArrayList<String>();
        qryCS = Cumulative(qryBinary);
        System.out.println("Cummulative Sum:" + qryCS);
        System.out.print(R.get(qryHW)[Integer.parseInt(qryCS.get(0))][0]);
        for(int i=1;i<b;i++){
            System.out.print(" x " + R.get(qryHW)[Integer.parseInt(qryCS.get(i))][i]);
        }
	    */
	    
	    for(int qryQ=0; qryQ<n; qryQ++){
	        System.out.println("\n-----------------------");
	        String qryBinary = Integer.toBinaryString(n|qryQ).substring(1);
	        System.out.println("Query: " + qryQ);
	        int qryHW = HammingWeight(qryBinary);
	        //System.out.println("Query HW:" + qryHW);
	        ArrayList<String> qryCS = new ArrayList<String>();
	        qryCS = Cumulative(qryBinary);
	        //System.out.println("Cummulative Sum:" + qryCS);
	        System.out.print(R.get(qryHW)[Integer.parseInt(qryCS.get(0))][0]);
	        for(int i=1;i<b;i++){
	            System.out.print(" x " + R.get(qryHW)[Integer.parseInt(qryCS.get(i))][i]);
	            }
	    }
	    
	    
	    
	    
	}	
}


