package ua.nure.uvarov.util;


import ua.nure.uvarov.bean.UsersBean;
import ua.nure.uvarov.constants.Messages;
import ua.nure.uvarov.constants.Parameters;
import ua.nure.uvarov.constants.Regexes;
import ua.nure.uvarov.entity.User;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class ValidateUtil {

    public boolean validateByRegex(String value, Pattern pattern) {
        if (value != null) {
            return pattern.matcher(value).find();
        } else {
            return false;
        }
    }


    public Map<String, String> validateAuthorize(User user) {
        Map<String, String> errors = new HashMap<>();
        if (!validateByRegex(user.getEmail(), Regexes.EMAIL_PATTERN)) {
            errors.put(Parameters.S_ERRORS, Messages.INVALID_EMAIL);
        }
        if (!validateByRegex(user.getPassword(), Regexes.PASSWORD)) {
            errors.put(Parameters.S_ERRORS, Messages.INVALID_PASSWORD);
        }
        return errors;
    }


    public Map<String, String> validateBean(UsersBean usersBean) {
        Map<String, String> errors = new HashMap<>();
        if (!validateByRegex(usersBean.getEmail(), Regexes.EMAIL_PATTERN)) {
            errors.put(Parameters.EMAIL, Messages.INVALID_EMAIL);
        }
        if (usersBean.getPassword() != null
                && usersBean.getPasswordRepeat() != null
                && usersBean.getPassword().equals(usersBean.getPasswordRepeat())) {
            if (!validateByRegex(usersBean.getPassword(), Regexes.PASSWORD)) {
                errors.put(Parameters.PASSWORD, Messages.INVALID_PASSWORD);
            }
        } else {
            errors.put(Parameters.PASSWORD_REPEAT, Messages.PASSWORD_NOT_EQUALS);
        }

        return errors;
    }




}
