import java.io.*;
class Test3{
  public static void main(String args[]){
    int max = 100000000;
    int sum = 0;
    BufferedReader in = new BufferedReader(new FileReader("./sample.txt"));
    String s;
    while((s = in.readLine()) != null){
      sum += Integer.parseInt(s);
    }
    int result = (max)*(max+1)/2-sum;
    System.out.println(result);

    in.close();
  }
}
