import org.junit.Assert;
import org.junit.Test;

import java.io.OutputStream;
import java.io.PrintStream;

public class AddressParserTest {
    public static final String TEST_ADDRESS_BLANK_VALUES = "{\"street\": \"\", \"housenumber\": \"\"}";
    public static final String BASIC1 = "Winterallee 3";
    public static final String BASIC1_PARSED = "{\"street\": \"Winterallee\", \"housenumber\": \"3\"}";
    public static final String BASIC2 = "Musterstrasse 45";
    public static final String BASIC2_PARSED = "{\"street\": \"Musterstrasse\", \"housenumber\": \"45\"}";
    public static final String BASIC3 = "Blaufeldweg 123B";
    public static final String BASIC3_PARSED = "{\"street\": \"Blaufeldweg\", \"housenumber\": \"123B\"}";
    public static final String BASIC31 = "Blaufeldweg123B";
    public static final String BASIC31_PARSED = "{\"street\": \"\", \"housenumber\": \"\"}";
    public static final String INTERMIDIATE1 = "Am Bächle 23";
    public static final String INTERMIDIATE1_PARSED = "{\"street\": \"Am Bächle\", \"housenumber\": \"23\"}";
    public static final String INTERMIDIATE11 = "Am Bächle 23a ";
    public static final String INTERMIDIATE11_PARSED = "{\"street\": \"Am Bächle\", \"housenumber\": \"23a\"}";
    public static final String INTERMIDIATE12 = "Am Bächle 23/4.";
    public static final String INTERMIDIATE12_PARSED = "{\"street\": \"Am Bächle\", \"housenumber\": \"23/4.\"}";
    public static final String INTERMIDIATE2 = "Auf der Vogelwiese 23 b";
    public static final String INTERMIDIATE2_PARSED = "{\"street\": \"Auf der Vogelwiese\", \"housenumber\": \"23 b\"}";
    public static final String ADVANCED1 = "4, rue de la revolution";
    public static final String ADVANCED1_PARSED = "{\"street\": \"rue de la revolution\", \"housenumber\": \"4\"}";
    public static final String ADVANCED2 = "200 Broadway Av";
    public static final String ADVANCED2_PARSED = "{\"street\": \"Broadway Av\", \"housenumber\": \"200\"}";
    public static final String ADVANCED3 = "Calle Aduana, 29";
    public static final String ADVANCED3_PARSED = "{\"street\": \"Calle Aduana\", \"housenumber\": \"29\"}";
    public static final String ADVANCED4 = "Calle 39 No 1540";
    public static final String ADVANCED4_PARSED = "{\"street\": \"Calle 39\", \"housenumber\": \"No 1540\"}";
    public static final String ADVANCED41 = "No 1540 Calle 39";
    public static final String ADVANCED41_PARSED = "{\"street\": \"Calle 39\", \"housenumber\": \"No 1540\"}";
    public static final String ADVANCED5 = "Strasse des 17. Juni 153";
    public static final String ADVANCED5_PARSED = "{\"street\": \"Strasse des 17. Juni\", \"housenumber\": \"153\"}";
    public static final String NO_HOUSE_NUMBER1 = "Strasse TEST";
    public static final String NO_HOUSE_NUMBER1_PARSED = "{\"street\": \"\", \"housenumber\": \"\"}";

    /**
     * Test start AddressParser.main.
     */
    @Test
    public void testAddressParserMainArgumentsNull () {
        OutputStream outputStream = new java.io.ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        AddressParser.main(null);
        String result = outputStream.toString();
        printStream.close();
        Assert.assertEquals("",result);
    }

    @Test
    public void testAddressParserMainArgumentsWithOneNullValue () {
        OutputStream outputStream = new java.io.ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        AddressParser.main(new String[]{null});
        String result = outputStream.toString();
        printStream.close();
        Assert.assertEquals(TEST_ADDRESS_BLANK_VALUES, result);
    }

    @Test
    public void testAddressParserMainArgumentsWithOneEmptyValue () {
        OutputStream outputStream = new java.io.ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        AddressParser.main(new String[]{null});
        String result = outputStream.toString();
        printStream.close();
        Assert.assertEquals(TEST_ADDRESS_BLANK_VALUES, result);
    }

    @Test
    public void testAddressParserMainArgumentsWithOneNonEmptyValue () {
        OutputStream outputStream = new java.io.ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        AddressParser.main(new String[]{ADVANCED5});
        String result = outputStream.toString();
        printStream.close();
        Assert.assertEquals(ADVANCED5_PARSED, result);
    }

    @Test
    public void testAddressParserMainArgumentsWithMultipleNonEmptyValues () {
        OutputStream outputStream = new java.io.ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        AddressParser.main(new String[]{ADVANCED5, NO_HOUSE_NUMBER1});
        String result = outputStream.toString();
        printStream.close();
        Assert.assertEquals(ADVANCED5_PARSED, result);
    }

    /**
     * Test start AddressParser.getSeparatedAddress.
     */
    @Test
    public void testGetSeparatedAddressNull() {
        String parsedAddress = AddressParser.getSeparatedAddress(null);
        Assert.assertEquals(TEST_ADDRESS_BLANK_VALUES, parsedAddress);
    }

    @Test
    public void testGetSeparatedAddressEmpty() {
        String parsedAddress = AddressParser.getSeparatedAddress("");
        Assert.assertEquals(TEST_ADDRESS_BLANK_VALUES, parsedAddress);
    }

    @Test
    public void testParsingBasic1() {
        String parsedAddress = AddressParser.getSeparatedAddress(BASIC1);
        Assert.assertEquals(BASIC1_PARSED, parsedAddress);
    }

    @Test
    public void testParsingBasic2() {
        String parsedAddress = AddressParser.getSeparatedAddress(BASIC2);
        Assert.assertEquals(BASIC2_PARSED, parsedAddress);
    }

    @Test
    public void testParsingBasic3() {
        String parsedAddress = AddressParser.getSeparatedAddress(BASIC3);
        Assert.assertEquals(BASIC3_PARSED, parsedAddress);
    }

    @Test
    public void testParsingBasic31() {
        String parsedAddress = AddressParser.getSeparatedAddress(BASIC31);
        Assert.assertEquals(BASIC31_PARSED, parsedAddress);
    }

    @Test
    public void testParsingIntermidiate1() {
        String parsedAddress = AddressParser.getSeparatedAddress(INTERMIDIATE1);
        Assert.assertEquals(INTERMIDIATE1_PARSED, parsedAddress);
    }

    @Test
    public void testParsingIntermidiate11() {
        String parsedAddress = AddressParser.getSeparatedAddress(INTERMIDIATE11);
        Assert.assertEquals(INTERMIDIATE11_PARSED, parsedAddress);
    }

    @Test
    public void testParsingIntermidiate12() {
        String parsedAddress = AddressParser.getSeparatedAddress(INTERMIDIATE12);
        Assert.assertEquals(INTERMIDIATE12_PARSED, parsedAddress);
    }

    @Test
    public void testParsingIntermidiate2() {
        String parsedAddress = AddressParser.getSeparatedAddress(INTERMIDIATE2);
        Assert.assertEquals(INTERMIDIATE2_PARSED, parsedAddress);
    }

    @Test
    public void testParsingAdvanced1() {
        String parsedAddress = AddressParser.getSeparatedAddress(ADVANCED1);
        Assert.assertEquals(ADVANCED1_PARSED, parsedAddress);
    }

    @Test
    public void testParsingAdvanced2() {
        String parsedAddress = AddressParser.getSeparatedAddress(ADVANCED2);
        Assert.assertEquals(ADVANCED2_PARSED, parsedAddress);
    }

    @Test
    public void testParsingAdvanced3() {
        String parsedAddress = AddressParser.getSeparatedAddress(ADVANCED3);
        Assert.assertEquals(ADVANCED3_PARSED, parsedAddress);
    }

    @Test
    public void testParsingAdvanced4() {
        String parsedAddress = AddressParser.getSeparatedAddress(ADVANCED4);
        Assert.assertEquals(ADVANCED4_PARSED, parsedAddress);
    }

    @Test
    public void testParsingAdvanced41() {
        String parsedAddress = AddressParser.getSeparatedAddress(ADVANCED41);
        Assert.assertEquals(ADVANCED41_PARSED, parsedAddress);
    }

    @Test
    public void testParsingAdvanced5() {
        String parsedAddress = AddressParser.getSeparatedAddress(ADVANCED5);
        Assert.assertEquals(ADVANCED5_PARSED, parsedAddress);
    }

    @Test
    public void testParsingNoHouseNumber() {
        String parsedAddress = AddressParser.getSeparatedAddress(NO_HOUSE_NUMBER1);
        Assert.assertEquals(NO_HOUSE_NUMBER1_PARSED, parsedAddress);
    }
}
