package com.rishabh.watchlistservice.repository;

import com.rishabh.watchlistservice.model.WatchListItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

// extends is inheritance
// JpaRepository already declares save, findAll, findById, deleteById and others. By extending it,
// your interface declares all of those too — so you can call watchlistRepository.save(item)
// even though you never typed that method.

public interface WatchlistRepository extends JpaRepository<WatchListItem,Long> {
    // The catch is that an interface only declares methods; it has no code in it. So who actually implements them?
    // Spring does. At startup it finds every interface extending JpaRepository, generates a class behind the scenes,
    // and hands you an object of that class. That’s why you never write new WatchlistRepository() — there’s nothing to construct.


    // one good thing is that I am extending capabilities of Jpa but other thing is this findbysymbol and all doesnot
    // exists in jparepo , I invented those and jpa read them and wrote sql , this is code generation at startup
    // So i am asking for new queries by asking for them that is why it is an interface otherwise if it would be a class
    // then I would have to write the code for it myself , spring already knows how to write it , it doesnot know
    // which table and condition and that is what my interface will give it
    Optional<WatchListItem> findBySymbol(String symbol);

    boolean existsBySymbol(String symbol);


    List<WatchListItem> findByCompanynameContainingIgnoreCase(String Keyword);
}
