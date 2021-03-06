package org.astelit.itunes.controller;

import lombok.RequiredArgsConstructor;
import org.astelit.itunes.dto.SearchRequest;
import org.astelit.itunes.dto.album.AlbumCreateRequest;
import org.astelit.itunes.dto.album.AlbumResponse;
import org.astelit.itunes.dto.album.AlbumUpdateRequest;
import org.astelit.itunes.dto.filters.AlbumFilter;
import org.astelit.itunes.service.AlbumService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/albums")
@RequiredArgsConstructor
public class AlbumController {
    private final AlbumService albumService;

    @PostMapping
    public AlbumResponse create(@Valid @RequestBody AlbumCreateRequest request) {
        return albumService.create(request);
    }

    @PatchMapping
    public AlbumResponse update(@Valid @RequestBody AlbumUpdateRequest object) {
        return albumService.update(object);
    }

    @GetMapping("{id}")
    public AlbumResponse view(@PathVariable long id) {
        return albumService.view(id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable long id) { albumService.delete(id);}

    @GetMapping
    public Page<AlbumResponse> search(AlbumFilter request) {
        return albumService.search(request);
    }
}
