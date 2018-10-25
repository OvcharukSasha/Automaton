public class Transition {

        public int from;
        public int to;
        public char letter;
public boolean check;
        Transition()
        {

        }
        Transition(int from,  char letter, int to)
        {
           this.from=from;
            this.letter = letter;
            this.to = to;
        }

        @Override
        public String toString()
        {
            return "{ " + from + ", "+ letter + ", " + to + " } ";
        }
        public int GetFrom()
        {
            return from;
        }

    public char GetLetter()
    {
        return letter;
    }
    public int getFrom()
    {
        return from;
    }
    public int getTo()
    {
        return to;
    }
    public void SetCheck(boolean r)
    {
       check=r;
    }
    public boolean GetCheck()
    {
        return check;
    }


}
