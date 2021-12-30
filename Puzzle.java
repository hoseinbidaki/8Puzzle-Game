import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Scanner;

public class Puzzle
{
    Control control = new Control();
    public Puzzle()
    {
        while(true)
        {
            System.out.println("Pleas ٍ Enter Your Choice : ");
            System.out.println("1. Input ");
            System.out.println("2. Random  ");
            System.out.println("3. Exit");
            System.out.println("====================================================");
            int choise = new Scanner(System.in).nextInt();
            if (choise == 1 )Input();
            else if (choise == 2) Random();
            else if (choise == 3)System.exit(0);
        }
    }

    public void DrawBoard()
    {
        int[] arr = control.getCurrent();
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
        System.out.println("Enter any key to Solve...");
        new Scanner(System.in).nextLine();
    }
    public void Input()
    {
        control.input();
        DrawBoard();
        control.solve();
    }
    public void Random()
    {
        control.RandomSort();
        DrawBoard();
        control.solve();
    }
}
