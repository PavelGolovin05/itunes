package org.astelit.itunes.controller;

import lombok.RequiredArgsConstructor;
import org.astelit.itunes.dto.SearchRequest;
import org.astelit.itunes.dto.singer.SingerCreateRequest;
import org.astelit.itunes.dto.singer.SingerResponse;
import org.astelit.itunes.dto.singer.SingerUpdateRequest;
import org.astelit.itunes.dto.user.CreateUserRequest;
import org.astelit.itunes.dto.user.UpdateUserRequest;
import org.astelit.itunes.dto.user.UserResponse;
import org.astelit.itunes.service.SingerService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/singers")
@RequiredArgsConstructor
public class SingerController {
    private final SingerService singerService;

    @PostMapping
    public SingerResponse create(@Valid @RequestBody SingerCreateRequest request) {
        return singerService.create(request);
    }

    @PatchMapping
    public SingerResponse update(@Valid @RequestBody SingerUpdateRequest object) {
        return singerService.update(object);
    }

    @GetMapping("{id}")
    public SingerResponse view(@PathVariable long id) {
        return singerService.view(id);
    }

    @GetMapping
    public Page<SingerResponse> search(SearchRequest request) {
        return singerService.search(request);
    }
}
