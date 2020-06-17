package org.astelit.itunes.controller;

import lombok.RequiredArgsConstructor;
import org.astelit.itunes.dto.filters.PlaylistFilter;
import org.astelit.itunes.dto.playlist.*;
import org.astelit.itunes.service.PlaylistService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/playlists")
@RequiredArgsConstructor
public class PlaylistController {
    private final PlaylistService playlistService;

    @PostMapping
    public PlaylistResponse create(@Valid @RequestBody PlaylistCreateRequest request) {
        return playlistService.create(request);
    }

    @PatchMapping
    public PlaylistResponse update(@Valid @RequestBody PlaylistUpdateRequest object) {
        return playlistService.update(object);
    }

    @GetMapping("{id}")
    public PlaylistResponse view(@PathVariable long id) {
        return playlistService.view(id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable long id) { playlistService.delete(id);}

    @GetMapping
    public Page<PlaylistResponse> search(PlaylistFilter request) {
        return playlistService.search(request);
    }

    @PostMapping("addSong")
    public void addSongToPlaylist(@Valid @RequestBody PlaylistSongRequest request) {
        playlistService.addSongToPlaylist(request.getPlaylist(), request.getSong());
    }

    @PostMapping("deleteSong")
    public void deleteSongFromPlaylist(@Valid @RequestBody PlaylistSongRequest request) {
        playlistService.deleteSongFromPlaylist(request.getPlaylist(), request.getSong());
    }
}
