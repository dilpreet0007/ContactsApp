package com.filter;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import com.contacts.Contact;
import com.tags.Tag;

public class FilterHandler {
    public void filterContacts(Scanner sc, Map<String, Contact> contactList) {
        List<Filter> filters = new ArrayList<>();

        System.out.print("Filter by tag? (y/n): ");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            System.out.print("Enter tag name: ");
            filters.add(new TagFilter(new Tag(sc.nextLine())));
        }

        System.out.print("Filter by date added after (yyyy-mm-dd)? (y/n): ");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            System.out.print("Enter date: ");
            filters.add(new DateFilter(LocalDate.parse(sc.nextLine())));
        }

        System.out.print("Filter by frequency (min count)? (y/n): ");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            System.out.print("Enter min count: ");
            filters.add(new FrequencyFilter(Integer.parseInt(sc.nextLine())));
        }

        Filter composite = new CompositeFilter(filters);

        List<Contact> results = contactList.values().stream()
                .filter(c -> composite.matches(c))
                .collect(Collectors.toList());

        System.out.println("Sort results by: \n 1. Name \n 2. Date \n 3. Frequency");
        int sortChoice = sc.nextInt();
        sc.nextLine();

        Comparator<Contact> comparator = switch (sortChoice) {
            case 1 -> Comparator.comparing(Contact::getName, String.CASE_INSENSITIVE_ORDER);
            case 2 -> Comparator.comparing(Contact::getDate);
            case 3 -> Comparator.comparing(Contact::getContactCount).reversed();
            default -> Comparator.comparing(Contact::getName);
        };

        results.sort(comparator);

        if (results.isEmpty()) {
            System.out.println("No contacts matched filters.");
        } else {
            System.out.println("Filtered Results:");
            results.forEach(System.out::println);
        }
    }
}