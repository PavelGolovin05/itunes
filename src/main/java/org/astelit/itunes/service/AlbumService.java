package org.astelit.itunes.service;

import lombok.RequiredArgsConstructor;
import org.astelit.itunes.dto.SearchRequest;
import org.astelit.itunes.dto.album.AlbumCreateRequest;
import org.astelit.itunes.dto.album.AlbumResponse;
import org.astelit.itunes.dto.album.AlbumUpdateRequest;
import org.astelit.itunes.dto.filters.AlbumFilter;
import org.astelit.itunes.entity.Album;
import org.astelit.itunes.repository.AlbumRepository;
import org.astelit.itunes.repository.SingerRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import static org.astelit.itunes.utils.Exceptions.ALBUM_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class AlbumService {
    private final AlbumRepository albumRepository;
    private final SingerRepository singerRepository;

    public AlbumResponse create(AlbumCreateRequest request) {
        Album album = new Album();
        album.setName(request.getName());
        album.setGenre(request.getGenre());
        album.setDate_realize(request.getDate_realize());
        album.setSinger(singerRepository.findById(request.getSinger()).orElseThrow(ALBUM_NOT_FOUND));

        albumRepository.save(album);
        return new AlbumResponse(album);
    }

    public AlbumResponse update(AlbumUpdateRequest request) {
        Album album = albumRepository.findById(request.getId()).orElseThrow(ALBUM_NOT_FOUND);
        album.setName(request.getName());
        album.setGenre(request.getGenre());
        album.setDate_realize(request.getDate_realize());

        albumRepository.save(album);
        return new AlbumResponse(album);
    }

    public AlbumResponse view(long id) {
        Album album = albumRepository.findById(id).orElseThrow(ALBUM_NOT_FOUND);
        return new AlbumResponse(album);
    }

    public void delete(long id) {
        Album album = albumRepository.findById(id).orElseThrow(ALBUM_NOT_FOUND);
        albumRepository.delete(album);
    }

    public Page<AlbumResponse> search(AlbumFilter request) {
        return albumRepository.search(request).map(AlbumResponse::new);
    }
}
