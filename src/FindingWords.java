
import java.util.*;
import java.io.*;
import java.lang.*;
import java.io.File;

public class FindingWords
{
    public State firststate;
    public ArrayList<State> automata;
public ArrayList<Transition>transitions= new ArrayList<>();
    public ArrayList<String> word;
    HashSet<String> Automataword = new HashSet<>();

    public void solve(String filename) {

        try {

            readData(filename);

        } catch (FileNotFoundException e) {
            System.out.println(String.format("file %s not found", filename));
        }
    }

    public void readData(String filename) throws FileNotFoundException
    {
        Scanner sc = new Scanner(new File(filename));
        int n = sc.nextInt();
        automata = new ArrayList<State>();
        int first=sc.nextInt();

        for(int i=0; i<n;i++)
        {
            if(i==first)
            {
                firststate= new State(first);
                firststate.SetFirst();
                automata.add(firststate);
            }
            else
            automata.add(new State(i));
        }

        int term = sc.nextInt();

        while (term-- > 0) {
            int state = sc.nextInt();
            automata.get(state).isfinal=true;
        }

        while (sc.hasNextLine()) {
            int from = sc.nextInt();
            char edge = sc.next().charAt(0);
            int to = sc.nextInt();

            Transition transition = new Transition(from, edge, to);
            transition.SetCheck(false);
            if(transition!=null) {
                transitions.add(transition);

                automata.get(to).addPrev(automata.get(from));
                automata.get(to).addTranstothis(transition);
                automata.get(from).addNext(automata.get(to));

            }
        }

        transitions.sort(Comparator.comparingInt(Transition::GetFrom));
       // Printtrans();

        for(int i=automata.size()-1;i>=0;i--)
        {
            word=new ArrayList<String>();
            if(automata.get(i).isfinal==true)
            {for(Transition e: transitions)
                e.SetCheck(false);
            for(State au:automata)
                au.setCheck(0);
                SearchWords(automata.get(i));
            }
        }

           PrintWords();
    }







    public void PrintWords()
    {
        Iterator<String> iterator = Automataword.iterator();
        int i=1;
        while (iterator.hasNext())
        {
            System.out.println(i+": "+iterator.next());
           i++;
        }
    }
    public void SearchWords(State fi)
    {
        if(fi.isfirst==false)
        {
            for (Transition prev : fi.transitionsToThis)
            {
                if (prev.GetCheck() != true) {

                    int fr = prev.getFrom();
                    int to = fi.GetState();
                    String w = String.valueOf(prev.GetLetter());

                    if (fr == to) {

                       /* w = "(" + w + ")";
                        word.add(w);
                        */
                        prev.SetCheck(true);

                    } else {
                        word.add(w);

                        {prev.SetCheck(true);}

                        State stt = GetState(fr);
                        stt.setCheck(-1);
                        SearchWords(GetState(fr));
                    }
                }
            }


            if((fi.check==-1||fi.check==1)&& word.size()>=1) {

                word.remove(word.size()-1);
                fi.setCheck(1);

            }

        }
        else { String w="";
            for(int i=word.size()-1;i>=0;i--)
            {
                w+=word.get(i);

            }
            Automataword.add(w);


            fi.setCheck(1);
            word.remove(word.size()-1);


             for(Transition t:transitions)
             {
                  if(!oneTransition(t.from, t.to))
                   t.SetCheck(false);
             }


        }
    }
public State GetState(int state)
{
    State st=null;
    for(State t:automata)
    {
        if (t.GetState()==state)
        {return t;}
    }
    return st;
}
    public boolean TwoTransitionToOneState(State current)
    {
       boolean result=false;
       for(int i=0; i<current.transitionsToThis.size(); i++)
       {
           for(int j=0; j<current.transitionsToThis.size(); j++)
           {if(i!=j)
               if(current.transitionsToThis.get(i).getFrom()==current.transitionsToThis.get(j).getFrom())
                   result=true;
           }


       }
       return result;

    }



    public void Printtrans() {
        for (Transition tr : transitions)
            System.out.println(tr.from + " " + tr.letter + " " + tr.to);
    }

    public boolean oneTransition(int from, int to)
    {boolean result =true;
        for(int i=0; i<transitions.size(); i++)
            for( int j=0; j<transitions.size(); j++)
            {
                if(i!=j)
                {
                    if(transitions.get(i).getFrom()==transitions.get(j).getFrom()&&transitions.get(i).getTo()==transitions.get(j).getTo())
                    {
                        result=false;
                    }
                }
            }
            return result;
    }
    public Transition GetTransition (int from, int to)
    {
        Transition tr=null;
        for( int j=0; j<transitions.size(); j++)
        {

                if(transitions.get(j).getFrom()==from&&transitions.get(j).getTo()==to)
                {
                    tr=transitions.get(j);
                }

        }
        return tr;
    }
}
