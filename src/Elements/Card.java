package Elements;

/**
 * @param value 0 = Skip-Bo wild, 1–12 normal cards
 */
public record Card(int value) {
    // assign value of a card. will be between 0 and 12

    // determine if card is a wildcard. if it is, assign value 0
    public boolean isWild() {
        return value == 0;
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
