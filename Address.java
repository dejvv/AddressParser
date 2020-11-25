/**
 * @author David Zagoršek
 * Separate an address consisting of concatenated street name and house number to two parts (street and house number).
 */
public class Address {
    public String address;
    public String street;
    public String housenumber;

    public Address (String address) {
        if (address != null) {
            this.address = address.trim();
        }
        this.street = "";
        this.housenumber = "";
    }

    /**
     * @author David Zagoršek
     * Seprates street and house number from address and returns object containing separated values.
     */
    public Address getSeparatedAddress () {
        this.separateAddress();
        return this;
    }

    /**
     * @author David Zagoršek
     * Seprates street and house number from address.
     */
    private void separateAddress () {
        if (AddressUtil.isBlankString(this.address)) {
            return;
        }
        int numbersInString = AddressUtil.countNumbersInString(this.address);
        if (numbersInString == 0) {
            return;
        }
        this.parseIfHouseNumberPrefixExist();
        if (AddressUtil.isNotBlankString(this.street) && AddressUtil.isNotBlankString(this.housenumber)) {
            return;
        }
        int[] houseNumberInterval = this.getHouseNumberInterval(this.address, numbersInString);
        this.parseAddressWithHouseNumberInterval(houseNumberInterval);
    }

    /**
     * @author David Zagoršek
     * Finds start and end index of housenumber from given address.
     * @param address Address that contains street and house number.
     * @param numbersInAddress Occurrences of numbers in the given address.
     */
    private int[] getHouseNumberInterval(String address, int numbersInAddress) {
        int housenumberStartIndex = AddressUtil.INVALID_INDEX;
        int housenumberEndIndex = AddressUtil.INVALID_INDEX;
        int foundNumbersInAddress = 0;
        boolean foundNumber = false;
        if (AddressUtil.isNotBlankString(address) && numbersInAddress != 0) {
            int currentIndex = -1;
            for (char character : address.toCharArray()) {
                currentIndex++;
                if (Character.isDigit(character)) {
                    if (!foundNumber) {
                        foundNumber = true;
                        foundNumbersInAddress++;
                    }
                    if (housenumberStartIndex == AddressUtil.INVALID_INDEX) {
                        // 24.11.2020, David Zagoršek - requires at least one space in front of the house number
                        if (currentIndex == 0 || currentIndex > 0 && address.charAt(currentIndex - 1) == ' ') {
                            housenumberStartIndex = currentIndex;
                        }
                    }
                    continue;
                }
                foundNumber = false;

                // 24.11.2020, David Zagoršek - numbers ending with a dot(.) are not part of the house number
                if (foundNumbersInAddress != 0
                        && foundNumbersInAddress != numbersInAddress
                        && Character.isDigit(address.charAt(currentIndex - 1))
                        && AddressUtil.PARTS_OF_STREET.contains(character)) {
                    housenumberStartIndex = AddressUtil.INVALID_INDEX;
                    continue;
                }

                if (housenumberStartIndex != AddressUtil.INVALID_INDEX) {
                    if (AddressUtil.ADDRESS_PARTS_DELIMITERS.contains(character)) {
                        housenumberEndIndex = currentIndex - 1;
                        break;
                    }
                    // 21.11.2020, David Zagoršek - allows one additional charater after number, e.g. 23 b or 23b.
                    if (Character.isLetter(character)
                            && Character.isLetter(address.charAt(currentIndex - 1))
                            && !Character.isLetter(address.charAt(currentIndex - 2))
                            && !Character.isDigit(address.charAt(currentIndex - 2))) {
                        housenumberEndIndex = currentIndex - 3;
                        break;
                    }
                }
            }
            if (housenumberStartIndex != AddressUtil.INVALID_INDEX && housenumberEndIndex == AddressUtil.INVALID_INDEX){
                housenumberEndIndex = address.length() - 1;
            }
        }
        int[] indicies = new int [2];
        indicies[0] = housenumberStartIndex;
        indicies[1] = housenumberEndIndex;
        return indicies;
    }

    /**
     * @author David Zagoršek
     * Split the address into street and house number based on the interval where house number is supposed to start and end.
     * @param houseNumberInterval Array where first element represents start index of house number and second element end index of house number in the address.
     */
    public void parseAddressWithHouseNumberInterval (int[] houseNumberInterval) {
        if (AddressUtil.isNotBlankString(this.address)
                && houseNumberInterval != null
                && houseNumberInterval[0] != AddressUtil.INVALID_INDEX
                && houseNumberInterval[1] != AddressUtil.INVALID_INDEX) {
            StringBuilder street = new StringBuilder();
            StringBuilder housenumber = new StringBuilder();
            for (int i = 0; i < this.address.length(); i++) {
                if (i >= houseNumberInterval[0] && i <= houseNumberInterval[1]) {
                    housenumber.append(this.address.charAt(i));
                } else {
                    street.append(this.address.charAt(i));
                }
            }
            this.street = AddressUtil.removeAddressPartDelimiters(street.toString()).trim();
            this.housenumber = housenumber.toString().trim();
        }
    }

    /**
     * @author David Zagoršek
     * Checks whether the address has a string that represents house number's prefix. If it does check whether next sequence
     * of character is an integer. If both conditions are met, they combine the house number. Therefore street and
     * house number can be assigned.
     */
    public void parseIfHouseNumberPrefixExist() {
        if (AddressUtil.isNotBlankString(this.address)) {
            String[] addressSplittedBySpace = this.address.split(" ");
            String housenumber = null;
            for (int i = 0; i < addressSplittedBySpace.length - 1; i++) {
                // 24.11.2020, David Zagoršek - house number follows it's prefix
                if (AddressUtil.HOUSE_NUMBER_PREFIXES.contains(addressSplittedBySpace[i].toLowerCase())) {
                    if (AddressUtil.isInteger(addressSplittedBySpace[i + 1])) {
                        housenumber = addressSplittedBySpace[i] + " " + addressSplittedBySpace[i + 1];
                    }
                }
            }
            if (housenumber != null) {
                this.street = AddressUtil.removeAddressPartDelimiters(this.address.replace(housenumber, "")).trim();
                this.housenumber = housenumber.trim();
            }
        }
    }

    @Override
    public String toString () {
        return "{\"street\": \"" + this.street + "\", \"housenumber\": \"" + this.housenumber+ "\"}";
    }
}
