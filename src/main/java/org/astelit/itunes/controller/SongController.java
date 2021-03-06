package org.astelit.itunes.controller;

import lombok.RequiredArgsConstructor;
import org.astelit.itunes.dto.SearchRequest;
import org.astelit.itunes.dto.filters.SongFilter;
import org.astelit.itunes.dto.song.SongCreateRequest;
import org.astelit.itunes.dto.song.SongResponse;
import org.astelit.itunes.dto.song.SongUpdateRequest;
import org.astelit.itunes.service.SongService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/songs")
@RequiredArgsConstructor
public class SongController {
    private final SongService songService;

    @PostMapping
    public SongResponse create(@Valid @RequestBody SongCreateRequest request) {
        return songService.create(request);
    }

    @PatchMapping
    public SongResponse update(@Valid @RequestBody SongUpdateRequest object) {
        return songService.update(object);
    }

    @GetMapping("{id}")
    public SongResponse view(@PathVariable long id) {
        return songService.view(id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable long id) { songService.delete(id);}

    @GetMapping
    public Page<SongResponse> search(SongFilter request) {
        return songService.search(request);
    }
}
