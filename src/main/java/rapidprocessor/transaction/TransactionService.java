package rapidprocessor.transaction;

import rapidprocessor.ticketBatch.TicketBatch;
import rapidprocessor.user.User;

import java.math.BigDecimal;

public class TransactionService {
    private Transaction transaction;

    /**
     * Creates the transaction string for adding credit to a user account
     * @param buyer
     * @param amount
     */
    public void addCredit(User buyer, BigDecimal amount) {
        // TODO: Implement
        // XX_UUUUUUUUUUUUUUU_TT_CCCCCCCCC
    }

    /**
     * Creates the transaction string for buying a ticket
     * @param sellBatch
     * @param buyAmount
     */
    public void buy(TicketBatch sellBatch, Integer buyAmount) {
        // TODO: Implement
        // XX_EEEEEEEEEEEEEEEEEEE_SSSSSSSSSSSSS_TTT_PPPPPP
    }

    /**
     * Creates the transaction string for creating a user
     * @param user
     */
    public void create(User user) {
        // TODO: Implement
        // XX_UUUUUUUUUUUUUUU_TT_CCCCCCCCC
    }

    /**
     * Creates the transaction string for deleting a user
     * @param user
     */
    public void delete(User user) {
        // TODO: Implement
        // XX_UUUUUUUUUUUUUUU_TT_CCCCCCCCC
    }

    /**
     * Creates the transaction string for when the user logs out (end of session)
     * @param user
     */
    public void endSession(User user) {
        // TODO: Implement
        // XX_UUUUUUUUUUUUUUU_TT_CCCCCCCCC
    }

    /**
     * Creates the transaction string for refunding an amount
     * @param buyer
     * @param seller
     * @param amount
     */
    public void refund(User buyer, User seller, BigDecimal amount) {
        // TODO: Implement
        // XX_UUUUUUUUUUUUUUU_SSSSSSSSSSSSSSS_CCCCCCCCC
    }

    /**
     * Creates the transaction string for selling a ticket
     * @param sellBatch
     */
    public void sell(TicketBatch sellBatch) {
        // TODO: Implement
        // XX_EEEEEEEEEEEEEEEEEEE_SSSSSSSSSSSSS_TTT_PPPPPP
    }
}
