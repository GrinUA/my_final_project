package ua.nure.uvarov.services;

import java.util.Map;

public interface OrderService {

    Map<String,Integer> getDataAboutOrderedBooks(String groupId);
}
