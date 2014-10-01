import java.io.*;

class Test{
  public static void main(String[] args){

    try {
      int max = 100000000;
      int sum = 0;
      for(int i=0; i < max; i++){
        sum ^= i;
      }

      BufferedReader in = new BufferedReader(new FileReader("./sample.txt"));
      String s;

      while ((s = in.readLine()) != null) {
        sum ^= Integer.parseInt(s);
      }
      System.out.println(sum);

      in.close();
    } catch (IOException e) {
        System.err.println(e);
        System.exit(1);
    }
  }
}
