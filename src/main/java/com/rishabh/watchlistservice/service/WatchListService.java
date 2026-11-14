package com.rishabh.watchlistservice.service;

import com.rishabh.watchlistservice.model.WatchListItem;
import com.rishabh.watchlistservice.repository.WatchlistRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WatchListService {
    private final WatchlistRepository watchlistRepository;

    public WatchListService(WatchlistRepository watchlistRepository){
        this.watchlistRepository = watchlistRepository;
    }
    public WatchListItem addItem(WatchListItem item){
        String symbol = item.getSymbol().trim().toUpperCase();

        if(watchlistRepository.existsBySymbol(symbol)){
            throw new IllegalStateException(symbol + "is already in your watchlist");
        }

        item.setSymbol(symbol);
        item.setAddedAt(LocalDateTime.now());
        return watchlistRepository.save(item);
    }

    public List<WatchListItem> getAll(){
        return watchlistRepository.findAll();
    }

    public WatchListItem getById(Long id){
        return watchlistRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No watchlist item with id" + id));
    }

    public void deleteById(Long id){
        if( ! watchlistRepository.existsById(id)){
            throw new IllegalArgumentException("No watchlist item by id");
        }
        watchlistRepository.deleteById(id);
    }

    public List<WatchListItem> searchBySymbol(String symbol){
        return watchlistRepository.findBySymbol(symbol.trim().toUpperCase())
                .map(List::of)
                .orElse(List.of());
    }
}