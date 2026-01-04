package Elements;

public class Card {
    private final int value; // 0 = Skip-Bo wild, 1–12 normal cards

    // assign value of a card. will be between 0 and 12
    public Card(int value) {
        this.value = value;
    }

    // determine if card is a wildcard. if it is, assign value 0
    public boolean isWild() {
        return value == 0;
    }

    // return value of card
    public int getValue() {
        return value;
    }

    // String description of a card
    @Override
    public String toString() {
        if (isWild()) {
            return "SKIP-BO";
        } else {
            return Integer.toString(value);
        }
    }
}
