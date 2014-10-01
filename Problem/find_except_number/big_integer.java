import java.io.*;
import java.math.BigInteger;
class Test2{
  public static void main(String[] args){

    try {
      int max = 100000000;
      BigInteger big = new BigInteger("0");

      BufferedReader in = new BufferedReader(new FileReader("./sample.txt"));
      String s;

      while ((s = in.readLine()) != null) {
        big = big.add(BigInteger.valueOf(Integer.parseInt(s) - max/2));
      }
      System.out.println(BigInteger.valueOf(max).subtract(big).intValue());

      in.close();
    } catch (IOException e) {
        System.err.println(e);
        System.exit(1);
    }
  }
}
