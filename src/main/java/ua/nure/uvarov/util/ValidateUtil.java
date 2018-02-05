package ua.nure.uvarov.util;


import ua.nure.uvarov.bean.FilterParams;
import ua.nure.uvarov.bean.UsersBean;
import ua.nure.uvarov.constants.Messages;
import ua.nure.uvarov.constants.Parameters;
import ua.nure.uvarov.constants.Regexes;
import ua.nure.uvarov.entity.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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

    public Map<String, String> validateFilterParams(FilterParams filterParams) {
        Map<String, String> errors = new HashMap<>();
        if (filterParams.getName() != null
                && !filterParams.getName().isEmpty()
                && !validateByRegex(filterParams.getName(), Regexes.BOOK_STRING_PATTERN)) {
            errors.put(Parameters.NAME, Messages.INVALID);
        }
        if (filterParams.getAuthor() != null
                && !filterParams.getAuthor().isEmpty()
                && !validateByRegex(filterParams.getAuthor(), Regexes.BOOK_STRING_PATTERN)) {
            errors.put(Parameters.AUTHOR, Messages.INVALID);
        }
        if (filterParams.getEdition() != null
                && !filterParams.getEdition().isEmpty()
                && !validateByRegex(filterParams.getEdition(), Regexes.BOOK_STRING_PATTERN)) {
            errors.put(Parameters.EDITION, Messages.INVALID);
        }
        if (filterParams.getGenreId() != null
                && !filterParams.getGenreId().isEmpty()
                && !validateByRegex(filterParams.getGenreId(), Regexes.BOOK_GENRE_PATTERN)) {
            errors.put(Parameters.GENRE, Messages.INVALID);
        }
        if (filterParams.getOrderBy() != null
                && !filterParams.getOrderBy().isEmpty()
                && !(filterParams.getOrderBy().equals("ASC") || filterParams.getOrderBy().equals("DESC"))) {
            errors.put(Parameters.ORDER_BY, Messages.INVALID);
        }
        if (filterParams.getSortBy() != null
                && !filterParams.getSortBy().isEmpty()
                && (!Arrays.stream(filterParams.getClass().getDeclaredFields())
                .map(field -> field.getName())
                .collect(Collectors.toList())
                .contains(filterParams.getSortBy())))
        {
            errors.put(Parameters.SORT_BY, Messages.INVALID);
        }
        if (filterParams.getPublicationDate() != null && !filterParams.getPublicationDate().isEmpty()) {
            String pattern = "yyyy-MM-dd";
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            try {
                format.parse(filterParams.getPublicationDate());
            } catch (ParseException e) {
                errors.put(Parameters.PUBLICATION_DATE, Messages.INVALID);
            }
        }

        return errors;
    }


}
