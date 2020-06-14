package org.astelit.itunes.service;

import lombok.RequiredArgsConstructor;
import org.astelit.itunes.dto.SearchRequest;
import org.astelit.itunes.dto.singer.SingerCreateRequest;
import org.astelit.itunes.dto.singer.SingerResponse;
import org.astelit.itunes.dto.singer.SingerUpdateRequest;
import org.astelit.itunes.entity.Singer;
import org.astelit.itunes.repository.SingerRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import static org.astelit.itunes.utils.Exceptions.SINGER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class SingerService {
    public final SingerRepository singerRepository;

    public SingerResponse create(SingerCreateRequest request) {
        Singer singer = new Singer();
        singer.setName(request.getName());

        singerRepository.save(singer);
        return new SingerResponse(singer);
    }

    public SingerResponse update(SingerUpdateRequest request) {
        Singer singer = singerRepository.findById(request.getId()).orElseThrow(SINGER_NOT_FOUND);
        singer.setName(request.getName());
        singerRepository.save(singer);
        return new SingerResponse(singer);
    }

    public SingerResponse view(long id) {
        Singer singer = singerRepository.findById(id).orElseThrow(SINGER_NOT_FOUND);
        return new SingerResponse(singer);
    }

    public Page<SingerResponse> search(SearchRequest request) {
        return singerRepository.search(request).map(SingerResponse::new);
    }
}
