package com.rishabh.watchlistservice.controller;

import com.rishabh.watchlistservice.model.WatchListItem;
import com.rishabh.watchlistservice.service.WatchListService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/watchlist")
public class WatchListController {
    private final WatchListService watchListService;

    public WatchListController(WatchListService watchListService){
        this.watchListService = watchListService;
    }

    @PostMapping
    public ResponseEntity<WatchListItem> add(@Valid @RequestBody WatchListItem item){
        WatchListItem saved = watchListService.addItem(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public List<WatchListItem> getAll(){
        return watchListService.getAll();
    }

    @GetMapping("/{id}")
    public WatchListItem getById(@PathVariable Long id){
        return watchListService.getById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<WatchListItem> delete(@PathVariable Long id){
        watchListService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<WatchListItem> search(@RequestParam String symbol){
        return watchListService.searchBySymbol(symbol);
    }
}
