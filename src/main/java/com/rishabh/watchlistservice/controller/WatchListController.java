package com.rishabh.watchlistservice.controller;

import com.rishabh.watchlistservice.model.WatchListItem;
import com.rishabh.watchlistservice.service.WatchListService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
