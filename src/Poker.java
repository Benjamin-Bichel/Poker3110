import java.util.*;
/**
 * Poker distributes the hands and can determine the winner
 * 
 * @author babak 
 * @version 0.0
 */
public class Poker {

    private Collection<Hand> hands;
    
    /**
     * Create a new game of poker (empty at first)
     */
    public Poker(){
        hands = new ArrayList<Hand>();
    
    }

    /**
     * Add a new hand
     */
    public void addHand(Hand h) {
        hands.add(h);
    }
    
    
    /**
     * @return the best hand 
     */
    public Hand bestHand(){
       return Collections.max(hands);
    }
    
    public static void main(String[] args) {
		Poker test = new Poker();
		
		Hand hand1 = new Hand("AS 3S 4S 5S 2S");
		System.out.println(hand1.isStraight());
		System.out.println(hand1.isFlush());
		Hand hand2 = new Hand("4C 5S KC 4D 4S");
		System.out.println(hand2.hasNKind(3));

	}
}
