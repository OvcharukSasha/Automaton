import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class State
{
    public int state;
    public ArrayList<State> prevstate= new ArrayList<>();
    public ArrayList<State> nextstate=new ArrayList<>();
    public ArrayList <Transition> transitionsToThis= new ArrayList<>();
    public boolean isfinal;
    public boolean isfirst;
    public int check;

    public State(int num)
    {
        state=num;
        isfirst=false;
        check=0;

    }
    public void SetFirst()
    {
        isfirst=true;
    }
    public void SetFinal(boolean fin)
    {
        isfinal=fin;
    }
    public void addPrev(State prev)
    {
        prevstate.add(prev);
        prevstate.sort(Comparator.comparingInt(State::GetState).reversed());
    }
    public void addNext(State next)
    {
        nextstate.add(next);
        nextstate.sort(Comparator.comparingInt(State::GetState));
    }
    public void addTranstothis(Transition tr)
    {
        transitionsToThis.add(tr);
        transitionsToThis.sort(Comparator.comparingInt(Transition::getFrom).reversed());
    }
    public int GetState()
    {
        return state;
    }
    public void setCheck(int c)
    {
        check=c;
    }
    public boolean isterminate()
    {
        return isfinal;
    }

}
