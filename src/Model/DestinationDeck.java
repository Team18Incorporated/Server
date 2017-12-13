package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DestinationDeck implements Serializable{

	ArrayList<DestinationCard> deckList;
	ArrayList<DestinationCard> discardList;

	public DestinationDeck() {
		deckList = new ArrayList<DestinationCard>();
		discardList = new ArrayList<DestinationCard>();
		createDestinationDeck();

	}

	private void createDestinationDeck() {
		deckList.add(new DestinationCard("Los Angeles", "New York City", 21));
		deckList.add(new DestinationCard("Duluth", "Houston", 8));
		deckList.add(new DestinationCard("Sault St Marie", "Nashville", 8));
		deckList.add(new DestinationCard("New York City", "Atlanta", 6));
		deckList.add(new DestinationCard("Portland", "Nashville", 17));
		deckList.add(new DestinationCard("Vancouver", "Montreal", 20));
		deckList.add(new DestinationCard("Duluth", "El Paso", 10));
		deckList.add(new DestinationCard("Toronto", "Miami", 10));
		deckList.add(new DestinationCard("Portland", "Phoenix", 11));
		deckList.add(new DestinationCard("Dallas", "New York City", 11));
		deckList.add(new DestinationCard("Calgary", "Salt Lake City", 7));
		deckList.add(new DestinationCard("Calgary", "Phoenix", 13));
		deckList.add(new DestinationCard("Los Angeles", "Miami", 20));
		deckList.add(new DestinationCard("Winnipeg", "Little Rock", 11));
		deckList.add(new DestinationCard("San Francisco", "Atlanta", 17));
		deckList.add(new DestinationCard("Kansas City", "Houston", 5));
		deckList.add(new DestinationCard("Los Angeles", "Chicago", 16));
		deckList.add(new DestinationCard("Denver", "Pittsburgh", 11));
		deckList.add(new DestinationCard("Chicago", "Santa Fe", 9));
		deckList.add(new DestinationCard("Vancouver", "Santa Fe", 13));
		deckList.add(new DestinationCard("Boston", "Miami", 12));
		deckList.add(new DestinationCard("Chicago", "New Orleans", 7));
		deckList.add(new DestinationCard("Montreal", "Atlanta", 9));
		deckList.add(new DestinationCard("Seattle", "New York City", 22));
		deckList.add(new DestinationCard("Denver", "El Paso", 4));
		deckList.add(new DestinationCard("Helena", "Los Angeles", 8));
		deckList.add(new DestinationCard("Winnipeg", "Houston", 12));
		deckList.add(new DestinationCard("Montreal", "New Orleans", 13));
		deckList.add(new DestinationCard("Sault St Marie", "Oklahoma City", 9));
		deckList.add(new DestinationCard("Seattle", "Los Angeles", 9));

	}

	public void shuffle() {
		Collections.shuffle(deckList);
	}

	public void discard(List<DestinationCard> list) {
		discardList.addAll(list);
	}

	public void reshuffle() {
		deckList.addAll(discardList);
		discardList.clear();
		Collections.shuffle(deckList);
	}

	public ArrayList<DestinationCard> drawCards(int num) {
		ArrayList<DestinationCard> cards = new ArrayList<>();
		for (int i = 0; i < num; i++) {
			cards.add(deckList.get(0));
			deckList.remove(0);
		}
		return cards;
	}

	public int getSize() {
		return deckList.size();
	}
}
