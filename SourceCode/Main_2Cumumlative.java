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
    

    public static ArrayList<String> CCumulative(String strIn){
        ArrayList<String> arrStrResult = new ArrayList<String>();
	    arrStrResult.add(Character.toString(strIn.charAt(0)));
	    int intTemp=0;
	    for(int i=1; i<strIn.length(); i++){
	        intTemp = Integer.parseInt(arrStrResult.get(i-1)) + Character.getNumericValue(strIn.charAt(i));
	        arrStrResult.add(Integer.toString(intTemp));
	    }
	    
	    ArrayList<String> arrStrResultFinal = new ArrayList<String>();
	    arrStrResultFinal.add(arrStrResult.get(0));
	    intTemp=0;
	    for(int i=1; i<strIn.length(); i++){
	        intTemp = Integer.parseInt(arrStrResultFinal.get(i-1)) + Integer.parseInt(arrStrResult.get(i));
	        arrStrResultFinal.add(Integer.toString(intTemp));
	    }
	    
	    return arrStrResultFinal;
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

	    int n = 32; //data value range; 0 <= L <= B < n-1
		int b = (int) (Math.log(n)/Math.log(2));
        int L = 14;  //L >= 0
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
	    ArrayList<ArrayList<ArrayList<String>>> CC = new ArrayList<ArrayList<ArrayList<String>>>();

	    ArrayList<String> SElement = new ArrayList<String>();
        ArrayList<ArrayList<String>> CCElement = new ArrayList<ArrayList<String>>();


	    for(int i=0; i<=b; i++){
	        S.add(i, SElement);
	        CC.add(i, CCElement);
	    }
	    
        int intIndex = 0;
        for(int i=0; i<=b; i++){
            SElement = new ArrayList<String>();
            CCElement = new ArrayList<ArrayList<String>>();
            for (String Q : QRange){
                intIndex = HammingWeight(Q);
                if (intIndex == i){
                    SElement.add(Q);
                    CCElement.add(CCumulative(Q));
                }
            }
            S.set(i,SElement);
            CC.set(i,CCElement);
        }

	    System.out.println("");
	    System.out.println("S: ");
	    System.out.println(S);
	    System.out.println("CC: ");
	    System.out.println(CC);
	    

	    ArrayList<byte[][]> R = new ArrayList<byte[][]>();
	    byte[][] RElement;
	    int rowNo = 0;
	    for(int i=0; i<=b; i++){
	        if ((i==0)||(i==b)){
	            rowNo=1;
    	        RElement = new byte[1][1];

	        }else{
	            rowNo = ((i*(i-1))/2) + ((b-i+1)*i);    
	            RElement = new byte[rowNo+1][b];//In prevous PPRQ, rowNo = i
	        }
	        
	        R.add(RElement);
	    }
	   
	    
	 

	    for(int i=0; i<=b; i++)
	        for(int j=0; j<R.get(i).length; j++)
	            for(int k=0; k<R.get(i)[j].length; k++){
	                R.get(i)[j][k] = 0;
	            }
	            
	   //System.out.println(CC.get(w).get(w*...).get(b));
	   
    
        int intTemp = 0;
	    for(int i=1; i<b; i++){
	        for(int j=0; j<CC.get(i).size(); j++){
	            for(int k=0; k<b;k++){
	                intTemp = Integer.parseInt(CC.get(i).get(j).get(k));
	                R.get(i)[intTemp][k]=1;
	            }
	        }
	    }
	    if(L==0) R.get(0)[0][0] = 1;
	    if(U==n-1) R.get(b)[0][0] = 1;
	    
	    
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
            System.out.println("Query: " + qryQ);
	        if(qryQ==0) {
	            System.out.print(R.get(0)[0][0]);
	        }else if(qryQ==n-1){
	            System.out.print(R.get(b)[0][0]);
	        } else{
	            String qryBinary = Integer.toBinaryString(n|qryQ).substring(1);
	            int qryHW = HammingWeight(qryBinary);
	            //System.out.println("Query HW:" + qryHW);
	            ArrayList<String> qryCS = new ArrayList<String>();
	            qryCS = CCumulative(qryBinary);
	            //System.out.println("Cummulative Sum:" + qryCS);
	            System.out.print(R.get(qryHW)[Integer.parseInt(qryCS.get(0))][0]);
	            for(int i=1;i<b;i++){
	                System.out.print(" x " + R.get(qryHW)[Integer.parseInt(qryCS.get(i))][i]);
	                }
	        }
	    }
	    
	    
	    
	    
	}	
}