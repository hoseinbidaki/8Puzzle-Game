import java.util.ArrayList;
import java.util.Arrays;

public class State implements Comparable<State>
{
    private int [] board ;
    private int cost ,weight;
    public State(int arr [] ,int cost)
    {
        this.board = arr;
        this.cost = cost ;
        this.weight = cost + Calcute();
    }
    public int getCost()
    {
        return this.cost;
    }
    public int[] getBoard()
    {
        return this.board;
    }
    private int Calcute()
    {
        int sum = 0;
        for (int i = 0; i < board.length; ++i)
        {
            if (board[i] == 0) continue;
            int dr = Math.abs(i / 3 - (board[i] - 1) / 3);
            int dc = Math.abs(i % 3 - (board[i] - 1) % 3);
            sum += (dr + dc);
        }
        return sum;
    }
    public int compareTo(State o)
    {
        return (this.weight - o.weight);
    }
    public ArrayList<State> getNextStates()
    {
        ArrayList<State> states = new ArrayList<>();
        for (Control.MOVES move : Control.MOVES.values())
        {
            int newBoard[] = this.board.clone();
            Control.move(newBoard, move);
            if (!Arrays.equals(this.board, newBoard)) states.add(new State(newBoard, this.cost + 1));

        }
        return states;
    }

}
