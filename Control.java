import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.Timer;
public class Control
{
    public static final int[] Ans = { 1, 2, 3, 4, 5, 6, 7, 8, 0 };
    private static int[] current = {1 ,2 ,3 ,4 ,5 ,6 ,7 , 8 ,0};
    private final int n = 9;
    private boolean finish = false;
    public static enum MOVES {UP, DOWN, RIGHT, LEFT};


    public boolean isSolved()
    {
        return Arrays.equals(Ans,current);
    }
    public boolean isSolvable(int arr[])
    {
        int count = 0;
        for (int i=0 ;i<arr.length; i++)
        {
            if (arr[i] == 0)continue;
            for (int j=i+1 ;j< arr.length; j++)
                if (arr[j]>0 && arr[i]>arr[j]) count++;
        }
        return count%2==0 ? true : false;
    }
    public void Reset()
    {
        for (int i=0 ;i<n; i++)current[i] = i+1;
        current[n-1] = 0;
    }
    private int[] getRandom()
    {
        boolean exist[] = new boolean[n];
        int tmp [] = new int[n];
        Random rnd = new Random();
        for (int i=0; i<n ;i++)
        {
            int x = rnd.nextInt(n);
            while(exist[x] == true)x = rnd.nextInt(9);
            exist[x] = true;
            tmp[i] = x;
        }
        return tmp;
    }
    public void RandomSort()
    {
        int temp[] = getRandom();
        while(isSolvable(temp) == false)temp = getRandom();
        current = temp;
    }
    public static void swap(int arr[],int f,int s)
    {
        int temp = arr[f];
        arr[f] = arr[s];
        arr[s] = temp;
    }
    public static int getBlankIndex(int arr[])
    {
        for (int i=0; i<arr.length ;i++)if (arr[i] == 0)return i;
        return -1;
    }
    public boolean isSolving()
    {
        return this.finish;
    }
    public int [] getCurrent()
    {
        return current.clone();
    }
    public void setCurrent(int arr[])
    {
        current = arr;
    }
    private String make(int[] arr)
    {
        String str = "";
        for (int i = 0; i < arr.length; ++i)
        {
            str += String.valueOf(arr[i]);
        }
        return str;
    }
    public void solve()
    {
        Map<String, int[]> parent = null;
        this.finish = true;
        parent = Algorithm.aStar(getCurrent().clone());
        Stack<int[]> nextBoard = new Stack<>();
        nextBoard.add(Ans.clone());
        int c = 0;
        while (!Arrays.equals(nextBoard.peek(), this.current))
        {
            nextBoard.add(parent.get(make(nextBoard.peek())));
            c++;
        }
        for (int i = 0; i <= c; i++) {
            System.out.println(i+"-th levels");
            int arr[] = nextBoard.pop();
            print(arr);
        }
        System.out.println("The Puzzle was Solved !\n Press any key to Exit...");
        new Scanner(System.in).nextLine();
        System.exit(0);
    }

    public static void move(int[] arr, MOVES toMove)
    {
        int blank = getBlankIndex(arr);
        if (blank < 0 ) return;
        switch (toMove)
        {
            case UP : if (blank / 3 != 0) swap(arr, blank, blank - 3);
            case DOWN :  if (blank / 3 != 2) swap(arr, blank, blank + 3);
            case RIGHT : if (blank % 3 != 2) swap(arr, blank, blank + 1);
            case LEFT: if (blank % 3 != 0) swap(arr, blank, blank - 1);
        }
    }
    public void print(int arr[])
    {
        if (arr==null)return ;
        int k = 0;
        for (int i = 0; i < arr.length; i++)
        {
            k++;
            System.out.print(arr[i] + " ");
            if (k==3)
            {
                System.out.println("");
                k = 0;
            }
        }
        System.out.println("====================================================");
    }
    public void input()
    {
        System.out.println("Please Enter ur Array (0-8) : ");
        int tmp [] = new int[9];
        for (int i=0;i<9;i++)
        {
            System.out.print((i+1)+" -th : ");
            tmp[i] = new Scanner(System.in).nextInt();
        }
        if (Arrays.equals(tmp,Ans))
        {
            System.out.println("The Array Was Solved!");
            System.exit(0);
        }
        if (isSolvable(tmp))
        {
            setCurrent(tmp);
        }
        else{
            System.out.println("The Array is not Solvable!");
            System.out.println("Press Any kay to Exit...");
            new Scanner(System.in).nextLine();
            System.exit(0);
        }
    }
}
