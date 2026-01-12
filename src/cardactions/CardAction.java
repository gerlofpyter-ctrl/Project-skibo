package cardactions;

import Elements.*;

public interface CardAction {
    Card getCard();
}

// Hand -> Building Piles
// Stock -> Building Piles
// Discard -> Building Piles
// Hand -> Discard Piles (end of turn)