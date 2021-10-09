import java.io.*;
import java.util.*;


public class analyse
{

    int analysis(String input, String output, String O) throws IOException {
        // PrintWriter object for output file
        PrintWriter pw = new PrintWriter(output);
        // BufferedReader object for input file
        BufferedReader br = new BufferedReader(new FileReader(input));
        String line = br.readLine();
        float sum=0;
        int avg=0, c=0;

        // loop for each line of input file
        while(line != null)
        {
            String[] words = line.split(",");
            int l = words.length;

            if(l==3) {
                if(words[1].equals(O)){
                    sum = sum+Float.parseFloat(words[2]);
                    c++;
                    pw.println(line);
                    pw.flush();
                }
            }
            line = br.readLine();
        }
        // Calculating Average
        avg = (int)sum/c;

        // closing resources
        br.close();
        pw.close();
        return avg;
    }
    public static void main(String[] args) throws IOException
    {
        analyse ob = new analyse();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of Lines");
        int N = sc.nextInt();
        System.out.println("Enter the Origin");
        String O = sc.next();

        // reading input from input.txt file and creating temporary file (temp.txt)
        int avg = ob.analysis("input.txt", "temp.txt", O);

        // PrintWriter object for output file
        PrintWriter pw = new PrintWriter("output.txt");
        // BufferedReader object for input file
        BufferedReader br1 = new BufferedReader(new FileReader("temp.txt"));
        String line = br1.readLine();
        ArrayList<Float> num = new ArrayList<Float>();
        ArrayList<String> ls = new ArrayList<String>();
        int c=0, i=0;

        // loop for each line of input file
        while (line != null)
        {
            String[] words = line.split(",");
            num.add(Float.parseFloat(words[2]));
            ls.add(line);
            c++;
            line = br1.readLine();
        }
        //Sorting Arraylist with the help of Collections
        Collections.sort(num, Collections.reverseOrder());

        for (float l : num)
        {
            while(i < c)
            {
                String line1 = ls.get(i);
                String[] words = line1.split(",");
                if(l==(Float.parseFloat(words[2])))
                {
                    pw.println(line1);
                    pw.flush();
                    ls.remove(line1);
                    break;
                }
                i++;
            }
            i=0;
        }

        // BufferedReader object for input file
        BufferedReader br2 = new BufferedReader(new FileReader("output.txt"));
        String ll = br2.readLine();
        int res=0;

        System.out.println("");

        while(res<N) {
            String[] wrds = ll.split(",");
            int nn = (int)Float.parseFloat(wrds[2]);
            if(nn>avg) {
                System.out.println(ll);
            }
            res++;
            ll = br2.readLine();
        }

        // closing
        br1.close();
        br2.close();
        pw.close();

    }
}
