import org.junit.Assert;
import org.junit.Test;

public class AddressTest {
    public static final String TEST_ADDRESS_BLANK_VALUES = "{\"street\": \"\", \"housenumber\": \"\"}";
    public static final String TEST_ADDRESS_WITH_DELIMITERS = "4, rue de la revolution";
    public static final String TEST_ADDRESS_WITH_DELIMITERS_PARSED = "{\"street\": \"rue de la revolution\", \"housenumber\": \"4\"}";
    public static final String TEST_ADDRESS_WITHOUT_DELIMITERS = "Strasse des - 17. Juni 153";
    public static final String TEST_ADDRESS_WITHOUT_DELIMITERS_PARSED = "{\"street\": \"Strasse des - 17. Juni\", \"housenumber\": \"153\"}";
    public static final String TEST_ADDRESS_WITH_PREFIX = "Test address 39 with house number No 534";
    public static final String TEST_ADDRESS_WITH_PREFIX_PARSED = "{\"street\": \"Test address 39 with house number\", \"housenumber\": \"No 534\"}";
    public static final String TEST_ADDRESS_WITH_SUFFIX = "Am Bächle 23 a";
    public static final String TEST_ADDRESS_WITH_SUFFIX_PARSED = "{\"street\": \"Am Bächle\", \"housenumber\": \"23 a\"}";
    public static final String TEST_ADDRESS_WITHOUT_NUMBERS = "Test address without a number";
    public static final String TEST_ADDRESS_WITHOUT_NUMBERS_PARSED = "{\"street\": \"\", \"housenumber\": \"\"}";


    /**
     * Test start Address constructor.
     */
    @Test
    public void testAddressConstructorWithAddressNull() {
        Address theAddress = new Address(null);
        Assert.assertNotNull(theAddress);
        Assert.assertNull(theAddress.address);
        Assert.assertEquals("", theAddress.street);
        Assert.assertEquals("", theAddress.housenumber);
    }

    @Test
    public void testAddressConstructorWithAddressEmpty() {
        Address theAddress = new Address("");
        Assert.assertNotNull(theAddress);
        Assert.assertEquals("", theAddress.address);
        Assert.assertEquals("", theAddress.street);
        Assert.assertEquals("", theAddress.housenumber);
    }

    @Test
    public void testAddressConstructorWithAddressNonEmptyString() {
        Address theAddress = new Address(TEST_ADDRESS_WITHOUT_DELIMITERS);
        Assert.assertNotNull(theAddress);
        Assert.assertEquals(TEST_ADDRESS_WITHOUT_DELIMITERS, theAddress.address);
        Assert.assertEquals("", theAddress.street);
        Assert.assertEquals("", theAddress.housenumber);
    }

    /**
     * Test start Address.getSeparatedAddress.
     */
    @Test
    public void testAddressGetSeparatedAddressNull() {
        Address theAddress = new Address(null);
        theAddress = theAddress.getSeparatedAddress();
        Assert.assertEquals(TEST_ADDRESS_BLANK_VALUES, theAddress.toString());
    }

    @Test
    public void testAddressGetSeparatedAddressEmpty() {
        Address theAddress = new Address("");
        theAddress = theAddress.getSeparatedAddress();
        Assert.assertEquals(TEST_ADDRESS_BLANK_VALUES, theAddress.toString());
    }

    @Test
    public void testAddressGetSeparatedAddressNonEmptyStringWithoutDelimiters() {
        Address theAddress = new Address(TEST_ADDRESS_WITHOUT_DELIMITERS);
        Assert.assertNotNull(theAddress);
        theAddress = theAddress.getSeparatedAddress();
        Assert.assertNotNull(theAddress);
        Assert.assertEquals(TEST_ADDRESS_WITHOUT_DELIMITERS, theAddress.address);
        Assert.assertNotNull(theAddress.street);
        Assert.assertTrue(theAddress.street.length() > 0);
        Assert.assertNotNull(theAddress.housenumber);
        Assert.assertTrue(theAddress.housenumber.length() > 0);
        Assert.assertEquals(TEST_ADDRESS_WITHOUT_DELIMITERS_PARSED, theAddress.toString());
    }

    @Test
    public void testAddressGetSeparatedAddressNonEmptyStringWithDelimiters() {
        Address theAddress = new Address(TEST_ADDRESS_WITH_DELIMITERS);
        Assert.assertNotNull(theAddress);
        theAddress = theAddress.getSeparatedAddress();
        Assert.assertNotNull(theAddress);
        Assert.assertEquals(TEST_ADDRESS_WITH_DELIMITERS, theAddress.address);
        Assert.assertNotNull(theAddress.street);
        Assert.assertTrue(theAddress.street.length() > 0);
        Assert.assertNotNull(theAddress.housenumber);
        Assert.assertTrue(theAddress.housenumber.length() > 0);
        Assert.assertEquals(TEST_ADDRESS_WITH_DELIMITERS_PARSED, theAddress.toString());
    }

    @Test
    public void testAddressGetSeparatedAddressNonEmptyStringWithoutNumbers() {
        Address theAddress = new Address(TEST_ADDRESS_WITHOUT_NUMBERS);
        Assert.assertNotNull(theAddress);
        theAddress = theAddress.getSeparatedAddress();
        Assert.assertNotNull(theAddress);
        Assert.assertEquals(TEST_ADDRESS_WITHOUT_NUMBERS, theAddress.address);
        Assert.assertEquals("", theAddress.street);
        Assert.assertEquals("", theAddress.housenumber);
        Assert.assertEquals(TEST_ADDRESS_WITHOUT_NUMBERS_PARSED, theAddress.toString());
    }

    @Test
    public void testAddressGetSeparatedAddressNonEmptyStringWithPrefixedNumber() {
        Address theAddress = new Address(TEST_ADDRESS_WITH_PREFIX);
        Assert.assertNotNull(theAddress);
        theAddress = theAddress.getSeparatedAddress();
        Assert.assertNotNull(theAddress);
        Assert.assertEquals(TEST_ADDRESS_WITH_PREFIX, theAddress.address);
        Assert.assertNotNull(theAddress.street);
        Assert.assertTrue(theAddress.street.length() > 0);
        Assert.assertNotNull(theAddress.housenumber);
        Assert.assertTrue(theAddress.housenumber.length() > 0);
        Assert.assertEquals(TEST_ADDRESS_WITH_PREFIX_PARSED, theAddress.toString());
    }

    @Test
    public void testAddressGetSeparatedAddressNonEmptyStringWithSuffix() {
        Address theAddress = new Address(TEST_ADDRESS_WITH_SUFFIX);
        Assert.assertNotNull(theAddress);
        theAddress = theAddress.getSeparatedAddress();
        Assert.assertNotNull(theAddress);
        Assert.assertEquals(TEST_ADDRESS_WITH_SUFFIX, theAddress.address);
        Assert.assertNotNull(theAddress.street);
        Assert.assertTrue(theAddress.street.length() > 0);
        Assert.assertNotNull(theAddress.housenumber);
        Assert.assertTrue(theAddress.housenumber.length() > 0);
        Assert.assertEquals(TEST_ADDRESS_WITH_SUFFIX_PARSED, theAddress.toString());
    }

    /**
     * Test start Address.parseIfHouseNumberPrefixExistAddress.
     */
    @Test
    public void testAddressParseIfHouseNumberPrefixExistAddressNull() {
        Address theAddress = new Address(null);
        Assert.assertNotNull(theAddress);
        theAddress.parseIfHouseNumberPrefixExist();
        Assert.assertNotNull(theAddress);
        Assert.assertNull(theAddress.address);
        Assert.assertEquals("", theAddress.street);
        Assert.assertEquals("", theAddress.housenumber);
        Assert.assertEquals(TEST_ADDRESS_BLANK_VALUES, theAddress.toString());
    }

    @Test
    public void testAddressParseIfHouseNumberPrefixExistAddressEmpty() {
        Address theAddress = new Address(null);
        Assert.assertNotNull(theAddress);
        theAddress.parseIfHouseNumberPrefixExist();
        Assert.assertNotNull(theAddress);
        Assert.assertNull(theAddress.address);
        Assert.assertEquals("", theAddress.street);
        Assert.assertEquals("", theAddress.housenumber);
        Assert.assertEquals(TEST_ADDRESS_BLANK_VALUES, theAddress.toString());
    }

    @Test
    public void testAddressParseIfHouseNumberPrefixExistAddressNonEmptyString() {
        Address theAddress = new Address(TEST_ADDRESS_WITH_PREFIX);
        Assert.assertNotNull(theAddress);
        theAddress.parseIfHouseNumberPrefixExist();
        Assert.assertNotNull(theAddress);
        Assert.assertEquals(TEST_ADDRESS_WITH_PREFIX, theAddress.address);
        Assert.assertNotNull(theAddress.street);
        Assert.assertTrue(theAddress.street.length() > 0);
        Assert.assertNotNull(theAddress.housenumber);
        Assert.assertTrue(theAddress.housenumber.length() > 0);
        Assert.assertEquals(TEST_ADDRESS_WITH_PREFIX_PARSED, theAddress.toString());
    }

}
