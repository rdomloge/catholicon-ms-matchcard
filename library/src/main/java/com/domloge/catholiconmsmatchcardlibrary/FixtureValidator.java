package com.domloge.catholiconmsmatchcardlibrary;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("beforeCreateFixtureValidator")
public class FixtureValidator implements Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(FixtureValidator.class);

    private static final Pattern matchDatePattern = 
        Pattern.compile("\\d{4}-[01]\\d-[0-3]\\d");

    @Override
    public boolean supports(Class<?> clazz) {
        return Fixture.class.equals(clazz);
    }

    @Override
    public void validate(Object value, Errors errors) {
        Fixture f = (Fixture) value;
        if(matchDateIsValid(f.getMatchDate())) {
            LOGGER.debug("Fixture match date valid: {}", f.getMatchDate());
        }
        else {
            errors.rejectValue("matchDate", "matchDate.notValid");
            LOGGER.debug("Fixture match date INVALID: {}", f.getMatchDate());
        }
    }

    private boolean matchDateIsValid(String input) {
        
        return null != input && (matchDatePattern.matcher(input).matches());
    }
}
