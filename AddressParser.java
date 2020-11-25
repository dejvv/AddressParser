/**
 * @author David Zagoršek
 * Receives address as first argument, output to stdout as JSON object that contains separated street and housenumber.
 */
public class AddressParser {
    public static void main(String[] args) {
        if (args != null && args.length > 0) {
            System.out.print(getSeparatedAddress(args[0]));
        }
    }

    public static String getSeparatedAddress (String address) {
        return new Address(address).getSeparatedAddress().toString();
    }
}
