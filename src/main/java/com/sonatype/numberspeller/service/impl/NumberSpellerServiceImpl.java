package com.sonatype.numberspeller.service.impl;

import com.ibm.icu.text.RuleBasedNumberFormat;
import com.ibm.icu.util.ULocale;
import com.sonatype.numberspeller.service.NumberSpellerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class NumberSpellerServiceImpl implements NumberSpellerService {

    private static Logger LOGGER = LoggerFactory.getLogger(NumberSpellerServiceImpl.class);

    @Override
    public List<String> spell(String... strings) {
        LOGGER.debug("About to process: {}", strings == null ? null : String.join(", ", strings));

        List<String> result;

        if (strings == null || strings.length == 0) {
            result = Collections.singletonList("No input was found");
        } else {
            ULocale locale = ULocale.forLocale(Locale.US);
            RuleBasedNumberFormat formatter = new RuleBasedNumberFormat(locale, RuleBasedNumberFormat.SPELLOUT);

            result = Arrays.stream(strings).map(it -> {
                try {
                    return formatter.format(Long.parseLong(it), "%spellout-numbering-verbose")
                            .replace(",", "")
                            .replace("minus", "negative");

                } catch (NumberFormatException e) {
                    return String.format("\"%s\" is not a valid integer or long number", it);
                }
            }).collect(Collectors.toList());
        }

        LOGGER.debug("Result: {}", result);
        return result;
    }
}
