package com.tags;

import java.util.*;

public class TagRepository {
    private static final Map<String, Tag> tagPool = new HashMap<>();

    public static Tag getTag(String name) {
        String key = name.trim().toLowerCase();
        return tagPool.computeIfAbsent(key, Tag::new);
    }

    public static Set<Tag> getAllTags() {
        return new HashSet<>(tagPool.values());
    }
}