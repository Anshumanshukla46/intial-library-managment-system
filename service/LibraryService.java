package service;

import entities.Book;
import entities.User;

import java.util.*;

public class LibraryService {

    // TODO: Done = But User does NOT override: equals(), hashCode()
    private Map<User, List<Book>> userToBookMapping = new HashMap<>(); // TODO: done = initialise

    // LOCK: but will lock whole service
    // public synchronized boolean borrowBook(User user, Book book) {...}


    public boolean borrowBook(User user, Book book) {
        synchronized (book) {
            if (book.getTotalCopies() == 0)
                return false;

            // TODO: done = Same user can borrow same book multiple times
            if (userToBookMapping.containsKey(user) && userToBookMapping.get(user).contains(book))
                return false;

            book.setTotalCopies(book.getTotalCopies() - 1);
            if (userToBookMapping.containsKey(user)) {
                List<Book> bookList = userToBookMapping.get(user);
                bookList.add(book);
                userToBookMapping.put(user, bookList);
            } else {
                userToBookMapping.put(user, new ArrayList<>(List.of(book))); // TODO: done = not List.of()
            }
        }
        return true;
    }

    public boolean returnBook(User user, Book book) {
        // TODO: done = What if user doesn’t exist? AND What if book not borrowed?
        if (!userToBookMapping.containsKey(user))
            return false;

        if (userToBookMapping.containsKey(user) && !userToBookMapping.get(user).contains(book))
            return false;

        List<Book> bookList = userToBookMapping.get(user);
        bookList.remove(book);
        book.setTotalCopies(book.getTotalCopies() + 1);
        return true;
    }


    public List<Book> getBooksByUser(User user) {
        if (userToBookMapping.containsKey(user)) {
            return userToBookMapping.get(user);
        }
        return Collections.emptyList(); // TODO: done = Leads to NPE later
    }
}
