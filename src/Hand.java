import java.util.*;

/**
 * A poker hand is a list of cards, which can be of some "kind" (pair, straight, etc.)
 * 
 */
public class Hand implements Comparable<Hand> {

    public enum Kind {HIGH_CARD, PAIR, TWO_PAIR, THREE_OF_A_KIND, STRAIGHT, 
        FLUSH, FULL_HOUSE, FOUR_OF_A_KIND, STRAIGHT_FLUSH}

    private final List<Card> cards;

    
    /**
     * Create a hand from a string containing all cards (e,g, "5C TD AH QS 2D")
     */
    public Hand(String c) {
    	//Initialize global variables
    	cards = new ArrayList<Card>();
    	
    	//Split so that you can get all the cards separated
        String[] temp = c.split(" ");
        
        //Create a new card for every card in the original string statement
        for(int i =0; i <= temp.length-1; i++) {
        	Card newCard = new Card(temp[i]);
        	
        	cards.add(newCard);
        }
        int k;
        for (int m = cards.size(); m >= 0; m--) {
            for (int i = 0; i < cards.size() - 1; i++) {
               k = i+1;
                if (cards.get(i).getRank().ordinal() > cards.get(k).getRank().ordinal()) {
                    Card tempCard = cards.get(i);
                    cards.set(i, cards.get(k));
                    cards.set(k, tempCard);
                    
                }
            }}
       for(int i = 0; i < cards.size() ; i++) {
    	   System.out.println(cards.get(i).getRank() + " " + cards.get(i).getSuit());
       }
        
    }
    
    /**
     * @returns true if the hand has n cards of the same rank
	 * e.g., "TD TC TH 7C 7D" returns True for n=2 and n=3, and False for n=1 and n=4
     */
    protected boolean hasNKind(int n) {
    	
    	boolean kind = false;
    	int countOfSame = 0;
    	for(int x =0; x<= cards.size()-1; x++) {
    		for(int i =0; i<= cards.size()-1; i++) {
            	if(cards.get(i).getRank() == cards.get(x).getRank() && x !=i) {
            		countOfSame++;
            	}
            }
    		if(countOfSame == n-1) {
        		kind = true;
        		break;
        	}
        	countOfSame = 0;        		
    	}
    	
    	return kind;
    }
    
    /**
	 * Optional: you may skip this one. If so, just make it return False
     * @returns true if the hand has two pairs
     */
    public boolean isTwoPair() {
    	Card.Rank dontCheck =null;
    	int count = 0;
    	int countOfSame = 0;
    	for(int x =0; x<= cards.size()-1; x++) {
    		for(int i =0; i<= cards.size()-1; i++) {
    			
            	if(cards.get(i).getRank() == cards.get(x).getRank() && x !=i && cards.get(i).getRank() != dontCheck) {
            		count++;
            		System.out.println(dontCheck);
            	}
            }
    		if(count == 2) {
    			dontCheck = cards.get(x).getRank();
    			countOfSame++;
        	}
    		count =0;
    		        		
    	}
    	
    	if (countOfSame == 2) {
    		return true;
    	}
    	return false;
    }   
    
    /**
     * @returns true if the hand is a straight 
     */
    public boolean isStraight() {
    	for(int i =1; i<= cards.size()-1; i++) {
        	if(cards.get(i-1).getRank().ordinal() - cards.get(i).getRank().ordinal() !=-1 ) {
        	if(cards.get(cards.size() -1).getRank() == Card.Rank.ACE && cards.get(0).getRank() == Card.Rank.TWO) {
        		return true;
        	}else {
        		return false;
        	}
        	}
        }
    	return true;
    }
    
    /**
     * @returns true if the hand is a flush
     */
    public boolean isFlush() {
        boolean flush = true;
        for(int i =1; i<= cards.size()-1; i++) {
        	if(cards.get(i-1).getSuit() != cards.get(i).getSuit()) {
        		flush = false;
        	}
        }
        return flush;
    }
    
    @Override
    public int compareTo(Hand h) {
        //hint: delegate!
		//and don't worry about breaking ties
    	int compare = h.kind().compareTo(this.kind());
    	if(compare>0) {
    		return 1;
    	}else if(h.kind() == this.kind()){
    		return 0;
    	}else {
    		return -1;
    	}
    }
    
    /**
	 * This method is already implemented and could be useful! 
     * @returns the "kind" of the hand: flush, full house, etc.
     */
    public Kind kind() {
        if (isStraight() && isFlush()) return Kind.STRAIGHT_FLUSH;
        else if (hasNKind(4)) return Kind.FOUR_OF_A_KIND; 
        else if (hasNKind(3) && hasNKind(2)) return Kind.FULL_HOUSE;
        else if (isFlush()) return Kind.FLUSH;
        else if (isStraight()) return Kind.STRAIGHT;
        else if (hasNKind(3)) return Kind.THREE_OF_A_KIND;
        else if (isTwoPair()) return Kind.TWO_PAIR;
        else if (hasNKind(2)) return Kind.PAIR; 
        else return Kind.HIGH_CARD;
    }

}
