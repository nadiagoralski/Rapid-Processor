package rapidprocessor.user;

import org.apache.commons.lang3.StringUtils;
import rapidprocessor.util.Constants;

import java.math.BigDecimal;

/**
 * User
 * Defines a user object with accessors to
 * - username
 * - userType
 * - userBalance
 */
public class User {
    /**
     * Valid user types
     * AA - Admin
     * BS - Buy Standard
     * FS - Full Standard
     * SS - Sell Standard
     */
    public enum UserType {
        ADMIN("AA"),
        BUY_STANDARD("BS"),
        FULL_STANDARD("FS"),
        SELL_STANDARD("SS");

        String code;    // user type code

        /**
         * Default constructor for UserType enum
         *
         * @param code
         */
        UserType(String code) {
            this.code = code;
        }

        /**
         * Converts string to UserType
         *
         * @param userType
         * @return matching UserType object
         */
        public static UserType fromString(String userType) {
            if (ADMIN.code.equals(userType)) {
                return ADMIN;
            } else if (BUY_STANDARD.code.equals(userType)) {
                return BUY_STANDARD;
            } else if (FULL_STANDARD.code.equals(userType)) {
                return FULL_STANDARD;
            } else if (SELL_STANDARD.code.equals(userType)) {
                return SELL_STANDARD;
            }
            return null;
        }

        /**
         * Get code value
         * @return the code value as a string
         */
        public String getCode() {
            return code;
        }
    }

    /*
     * User's username
     */
    private String username;
    /*
     * Type of user
     */
    private UserType userType;
    /*
     * Balance of user's account
     */
    private BigDecimal userBalance;

    /**
     * User default constructor
     */
    public User() {
    }

    /**
     * User constructor
     * @param username
     * @param userType
     * @param userBalance
     */
    public User(String username, String userType, BigDecimal userBalance) {
        this.username = username;
        this.userType = UserType.fromString(userType);
        this.userBalance = userBalance;
    }

    /**
     * Getter for username
     * @return the username
     */
    public String getUsername() {
        return username;
    }
    /**
     * Setter for username
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter for user type
     * @return the user type
     */
    public UserType getUserType() {
        return userType;
    }

    /**
     * Setter for user type
     * @param userType
     */
    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    /**
     * Getter for user's account balance
     * @return the account balance
     */
    public BigDecimal getUserBalance() {
        return userBalance;
    }
    /**
     * Setter for user's account balance
     * @param userBalance
     */
    public void setUserBalance(BigDecimal userBalance) {
        this.userBalance = userBalance;
    }

    /**
     * Overrides the toString() method to print out user information in the format
     * UUUUUUUUUUUUUUU_TT_CCCCCCCCC
     * Where
     * UUUUUUUUUUUUUUU is the username (right padded with spaces)
     * TT is the user type code
     * CCCCCCCCC is the user balance (left padded with 0's)
     * _ is a space
     * @return formatted user information
     */
    @Override
    public String toString() {
        // Pad the string values with spaces and numeric values with 0's
        String username = StringUtils.rightPad(this.username, Constants.MAX_USERNAME_LENGTH);
        String userType = StringUtils.rightPad(this.userType.code, 2);
        String userBalance = StringUtils.leftPad(this.userBalance.setScale(2, BigDecimal.ROUND_HALF_UP).toString(), 9, "0");

        return (username + " " + userType + " " + userBalance);
    }
}
