package com.sonatype.numberspeller.service.impl;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class NumberSpellerServiceImplTest {

    private static final String EMPTY_RESULT = "No input was found";
    private static final String INVALID_RESULT = " is not a valid integer or long number";

    @Test
    public void spellEmptyInput() {
        NumberSpellerServiceImpl service = new NumberSpellerServiceImpl();
        List<String> result = service.spell();

        assertEquals(Collections.singletonList(EMPTY_RESULT), result);
    }

    @Test
    public void spellNullInput() {
        String[] input = null;

        NumberSpellerServiceImpl service = new NumberSpellerServiceImpl();
        List<String> result = service.spell(input);

        assertEquals(Collections.singletonList(EMPTY_RESULT), result);
    }

    @Test
    public void spellNullValueInput() {
        String input = null;

        NumberSpellerServiceImpl service = new NumberSpellerServiceImpl();
        List<String> result = service.spell(input);

        assertEquals(Collections.singletonList("\"null\"" + INVALID_RESULT), result);
    }

    @Test
    public void spellSingleNumberInput() {
        String input = "20";

        NumberSpellerServiceImpl service = new NumberSpellerServiceImpl();
        List<String> result = service.spell(input);

        assertEquals(Collections.singletonList("twenty"), result);
    }

    @Test
    public void spellSingleCharacterInput() {
        String input = "test";

        NumberSpellerServiceImpl service = new NumberSpellerServiceImpl();
        List<String> result = service.spell(input);

        assertEquals(Collections.singletonList("\"test\"" + INVALID_RESULT), result);
    }

    @Test
    public void spellMultipleNumbersInput() {
        String[] input = {"0", "13", "85", "5237", "5361232510"};

        NumberSpellerServiceImpl service = new NumberSpellerServiceImpl();
        List<String> result = service.spell(input);

        List<String> expected = Arrays.asList(
                "zero",
                "thirteen",
                "eighty-five",
                "five thousand two hundred thirty-seven",
                "five billion three hundred sixty-one million two hundred thirty-two thousand five hundred ten");
        assertEquals(expected, result);
    }

    @Test
    public void spellMultipleNumbersWithNegativesInput() {
        String[] input = {"0", "-50", "13", "85", "5237", "-1"};

        NumberSpellerServiceImpl service = new NumberSpellerServiceImpl();
        List<String> result = service.spell(input);

        List<String> expected = Arrays.asList(
                "zero",
                "minus fifty",
                "thirteen",
                "eighty-five",
                "five thousand two hundred thirty-seven",
                "minus one");
        assertEquals(expected, result);
    }

    @Test
    public void spellMultipleNumbersWithCharactersInput() {
        String[] input = {"", "0", null, "13", "test1", "85", "5237", " ", "-1", "test 2"};

        NumberSpellerServiceImpl service = new NumberSpellerServiceImpl();
        List<String> result = service.spell(input);

        List<String> expected = Arrays.asList(
                "\"\"" + INVALID_RESULT,
                "zero",
                "\"null\"" + INVALID_RESULT,
                "thirteen",
                "\"test1\"" + INVALID_RESULT,
                "eighty-five",
                "five thousand two hundred thirty-seven",
                "\" \"" + INVALID_RESULT,
                "minus one",
                "\"test 2\"" + INVALID_RESULT);
        assertEquals(expected, result);
    }

    @Test
    public void spellMultipleCharactersAndDoublesInput() {
        String[] input = {"", null, "test1", " ", "1.5", "test 2"};

        NumberSpellerServiceImpl service = new NumberSpellerServiceImpl();
        List<String> result = service.spell(input);

        List<String> expected = Arrays.asList(
                "\"\"" + INVALID_RESULT,
                "\"null\"" + INVALID_RESULT,
                "\"test1\"" + INVALID_RESULT,
                "\" \"" + INVALID_RESULT,
                "\"1.5\"" + INVALID_RESULT,
                "\"test 2\"" + INVALID_RESULT);
        assertEquals(expected, result);
    }
}
