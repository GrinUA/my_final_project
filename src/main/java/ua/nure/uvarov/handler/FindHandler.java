package ua.nure.uvarov.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface FindHandler {
    void execute(HttpServletRequest req, HttpServletResponse resp);
}
