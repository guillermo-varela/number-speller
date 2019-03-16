package com.sonatype.numberspeller.service;

import java.util.List;

public interface NumberSpellerService {

    /**
     * Takes Strings as input and for every element, if it's an integer/long, it will try to spell it in words.
     *
     * @param strings Integers as Strings to spell.
     * @return A list with each number spelled in the same order as the input.
     *         If a specific element is not an integer/long, an error message will be at its index.
     */
    List<String> spell(String... strings);
}
