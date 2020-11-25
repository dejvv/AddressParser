import org.junit.Test;
import org.junit.Assert;

public class AddressUtilTest {
    public static final String TEST_ADDRESS_WITH_DELIMITERS = "Test, Strasse des - 17. Juni 153";
    public static final String TEST_ADDRESS_WITHOUT_DELIMITERS = "Strasse des - 17. Juni 153";

    /**
     * Test start AddressUtil.countNumberInString.
     */
    @Test
    public void testCountNumberInStringNullString () {
        int numbersInString = AddressUtil.countNumbersInString(null);
        Assert.assertEquals(0, numbersInString);
    }

    @Test
    public void testCountNumberInStringEmptyString () {
        int numbersInString = AddressUtil.countNumbersInString("");
        Assert.assertEquals(0, numbersInString);
    }

    @Test
    public void testCountNumberInStringNoNumbers () {
        int numbersInString = AddressUtil.countNumbersInString("Without numbers.");
        Assert.assertEquals(0, numbersInString);
    }

    @Test
    public void testCountNumberInStringOneNumber () {
        int numbersInString = AddressUtil.countNumbersInString("Only 1 number.");
        Assert.assertEquals(1, numbersInString);
    }

    @Test
    public void testCountNumberInStringMultipleNumbers () {
        int numbersInString = AddressUtil.countNumbersInString("Few 34 numbers 1 here and there 42.");
        Assert.assertEquals(3, numbersInString);
    }

    @Test
    public void testCountNumberInStringOnlyNumbers () {
        int numbersInString = AddressUtil.countNumbersInString("42 11 2234134143");
        Assert.assertEquals(3, numbersInString);
    }

    @Test
    public void testCountNumberInStringWithNegativeNumbers () {
        int numbersInString = AddressUtil.countNumbersInString("42 -11 test.");
        Assert.assertEquals(2, numbersInString);
    }

    @Test
    public void testCountNumberInStringBigNumber () {
        int numbersInString = AddressUtil.countNumbersInString("1943820498034820948129037187319283793847298347982742938472938473298719231321");
        Assert.assertEquals(1, numbersInString);
    }

    /**
     * Test start AddressUtil.removeAddressPartDelimiters.
     */
    @Test
    public void testRemoveAddressPartDelimitersNullString () {
        Assert.assertNotNull(AddressUtil.ADDRESS_PARTS_DELIMITERS);
        Assert.assertTrue(AddressUtil.ADDRESS_PARTS_DELIMITERS.size() > 0);
        String withoutAddressDelimiters = AddressUtil.removeAddressPartDelimiters(null);
        Assert.assertNull(withoutAddressDelimiters);
    }

    @Test
    public void testRemoveAddressPartDelimitersEmptyString () {
        Assert.assertNotNull(AddressUtil.ADDRESS_PARTS_DELIMITERS);
        Assert.assertTrue(AddressUtil.ADDRESS_PARTS_DELIMITERS.size() > 0);
        String withoutAddressDelimiters = AddressUtil.removeAddressPartDelimiters("");
        for (Character delimiter : AddressUtil.ADDRESS_PARTS_DELIMITERS) {
            Assert.assertFalse(withoutAddressDelimiters.contains(String.valueOf(delimiter)));
        }
    }

    @Test
    public void testRemoveAddressPartDelimitersWithDelimiters () {
        Assert.assertNotNull(AddressUtil.ADDRESS_PARTS_DELIMITERS);
        Assert.assertTrue(AddressUtil.ADDRESS_PARTS_DELIMITERS.size() > 0);
        String withoutAddressDelimiters = AddressUtil.removeAddressPartDelimiters(TEST_ADDRESS_WITH_DELIMITERS);
        Assert.assertNotEquals(TEST_ADDRESS_WITH_DELIMITERS, withoutAddressDelimiters);
        for (Character delimiter : AddressUtil.ADDRESS_PARTS_DELIMITERS) {
            Assert.assertFalse(withoutAddressDelimiters.contains(String.valueOf(delimiter)));
        }
    }

    @Test
    public void testRemoveAddressPartDelimitersWithoutDelimiters () {
        Assert.assertNotNull(AddressUtil.ADDRESS_PARTS_DELIMITERS);
        Assert.assertTrue(AddressUtil.ADDRESS_PARTS_DELIMITERS.size() > 0);
        String withoutAddressDelimiters = AddressUtil.removeAddressPartDelimiters(TEST_ADDRESS_WITHOUT_DELIMITERS);
        Assert.assertEquals(TEST_ADDRESS_WITHOUT_DELIMITERS, withoutAddressDelimiters);
    }

    /**
     * Test start AddressUtil.isInteger.
     */
    @Test
    public void testIsIntegerNullString () {
        Assert.assertFalse(AddressUtil.isInteger(null));
    }

    @Test
    public void testIsIntegerEmptyString () {
        Assert.assertFalse(AddressUtil.isInteger(""));
    }

    @Test
    public void testIsIntegerRegularString () {
        Assert.assertFalse(AddressUtil.isInteger("Without an integer"));
    }

    @Test
    public void testIsIntegerWithInteger () {
        Assert.assertFalse(AddressUtil.isInteger("With an 42 integer"));
    }

    @Test
    public void testIsIntegerOnlyInteger () {
        Assert.assertTrue(AddressUtil.isInteger("42"));
    }

    @Test
    public void testIsIntegerOnlyDouble () {
        Assert.assertFalse(AddressUtil.isInteger("42.42"));
    }

    @Test
    public void testIsIntegerBigNumber () {
        Assert.assertFalse(AddressUtil.isInteger("42532490238402840928409283409238420923482"));
    }

    @Test
    public void testIsIntegerNegativeNumber () {
        Assert.assertTrue(AddressUtil.isInteger("-42"));
    }
}
