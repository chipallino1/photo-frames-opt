package com.ramkiopt.main.services.utils;

import com.ramkiopt.main.entities.Identity;

import java.util.ArrayList;
import java.util.List;

public class JpaCollections {
    private JpaCollections() {
    }

    public static <T extends Identity> Iterable<Long> getIterableById(List<T> list) {
        List<Long> iterable = new ArrayList<>();
        list.forEach(item -> {
            iterable.add(item.getId());
        });
        return iterable;
    }
}
