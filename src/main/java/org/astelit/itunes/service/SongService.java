package org.astelit.itunes.service;

import lombok.RequiredArgsConstructor;
import org.astelit.itunes.dto.SearchRequest;
import org.astelit.itunes.dto.album.AlbumCreateRequest;
import org.astelit.itunes.dto.album.AlbumResponse;
import org.astelit.itunes.dto.album.AlbumUpdateRequest;
import org.astelit.itunes.dto.song.SongCreateRequest;
import org.astelit.itunes.dto.song.SongResponse;
import org.astelit.itunes.dto.song.SongUpdateRequest;
import org.astelit.itunes.entity.Album;
import org.astelit.itunes.entity.Song;
import org.astelit.itunes.repository.AlbumRepository;
import org.astelit.itunes.repository.SongRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import static org.astelit.itunes.utils.Exceptions.ALBUM_NOT_FOUND;
import static org.astelit.itunes.utils.Exceptions.SONG_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class SongService {
    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;

    public SongResponse create(SongCreateRequest request) {
        Song song = new Song();
        song.setName(request.getName());
        song.setDuration(request.getDuration());
        song.setAlbum(albumRepository.findById(request.getAlbum()).orElseThrow(ALBUM_NOT_FOUND));

        songRepository.save(song);
        return new SongResponse(song);
    }

    public SongResponse update(SongUpdateRequest request) {
        Song song = songRepository.findById(request.getId()).orElseThrow(SONG_NOT_FOUND);
        song.setName(request.getName());
        song.setDuration(request.getDuration());

        songRepository.save(song);
        return new SongResponse(song);
    }

    public SongResponse view(long id) {
        Song song = songRepository.findById(id).orElseThrow(SONG_NOT_FOUND);
        return new SongResponse(song);
    }

    public Page<SongResponse> search(SearchRequest request) {
        return songRepository.search(request).map(SongResponse::new);
    }
}
