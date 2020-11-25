import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author David Zagoršek
 * Often used functions to assit parsing of an address.
 */
public class AddressUtil {
    public static final int INVALID_INDEX = -1;
    public static final Set<Character> ADDRESS_PARTS_DELIMITERS = new HashSet<Character>(Arrays.asList(','));
    public static final Set<Character> PARTS_OF_STREET = new HashSet<Character>(Arrays.asList('.'));
    public static final Set<String> HOUSE_NUMBER_PREFIXES = new HashSet<String>(Arrays.asList("no"));

    /**
     * @author David Zagoršek
     * Counts how many numbers exists in given string.
     * @param theString The string in which to count occurrence of numbers.
     */
    public static int countNumbersInString (String theString) {
        int numbersCounter = 0;
        if (isNotBlankString(theString)) {
            boolean foundNumber = false;
            for (char character : theString.toCharArray()) {
                if (Character.isDigit(character)) {
                    if (!foundNumber) {
                        foundNumber = true;
                        numbersCounter++;
                    }
                    continue;
                }
                foundNumber = false;
            }
        }
        return numbersCounter;
    }

    /**
     * @author David Zagoršek
     * Removes all address delimiters defined in ADDRESS_PARTS_DELIMITERS from given string.
     * @param theString The string from which delimiters should be removed.
     */
    public static String removeAddressPartDelimiters(String theString) {
        if (isNotBlankString(theString)) {
            for (Character addressPartDelimiter : AddressUtil.ADDRESS_PARTS_DELIMITERS) {
                theString = theString.replaceAll(String.valueOf(addressPartDelimiter), "");
            }
        }
        return theString;
    }

    /**
     * @author David Zagoršek
     * Checks whether given string is an integer.
     * @param theString The string to check if it's an integer.
     */
    public static Boolean isInteger (String theString) {
        if (isBlankString(theString)) {
            return false;
        }
        try {
            int parsedString = Integer.parseInt(theString);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * @author David Zagoršek
     * Checks whether given string is null or empty.
     * @param theString The string to check if it's null or empty.
     */
    public static Boolean isBlankString (String theString) {
        return theString == null || theString.trim().equals("");
    }

    /**
     * @author David Zagoršek
     * Checks whether given string is not null or empty.
     * @param theString The string to check if it's not null or empty.
     */
    public static Boolean isNotBlankString (String theString) {
        return !isBlankString((theString));
    }
}
