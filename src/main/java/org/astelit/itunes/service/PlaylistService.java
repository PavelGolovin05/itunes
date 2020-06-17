package org.astelit.itunes.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.astelit.itunes.dto.filters.PlaylistFilter;
import org.astelit.itunes.dto.playlist.PlaylistCreateRequest;
import org.astelit.itunes.dto.playlist.PlaylistResponse;
import org.astelit.itunes.dto.playlist.PlaylistUpdateRequest;
import org.astelit.itunes.entity.Playlist;
import org.astelit.itunes.entity.Song;
import org.astelit.itunes.entity.User;
import org.astelit.itunes.repository.PlaylistRepository;
import org.astelit.itunes.repository.SongRepository;
import org.astelit.itunes.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static org.astelit.itunes.utils.Exceptions.*;

@Service
@RequiredArgsConstructor
public class PlaylistService {
    private final PlaylistRepository playlistRepository;
    private final UserRepository userRepository;
    private final SongRepository songRepository;

    @Transactional
    public PlaylistResponse create(PlaylistCreateRequest request) {
        User author = userRepository.findById(request.getAuthor()).orElseThrow(USER_NOT_FOUND);
        Set<Song> songs = songRepository.findAllById(request.getSongs()).stream().filter(Objects::nonNull).collect(Collectors.toSet());

        Playlist playlist = new Playlist();
        playlist.setAuthor(author);
        playlist.setName(request.getName());
        playlistRepository.save(playlist);
        playlist.setSongs(songs);
        playlistRepository.save(playlist);
        return new PlaylistResponse(playlist);
    }

    public PlaylistResponse update(PlaylistUpdateRequest request) {
        Playlist playlist = playlistRepository.findById(request.getId()).orElseThrow(PLAYLIST_NOT_FOUND);
        playlist.setName(request.getName());
        playlistRepository.save(playlist);
        return new PlaylistResponse(playlist);
    }

    public PlaylistResponse view(long id) {
        Playlist playlist = playlistRepository.findById(id).orElseThrow(PLAYLIST_NOT_FOUND);
        return new PlaylistResponse(playlist);
    }

    public void delete(long id) {
        Playlist playlist = playlistRepository.findById(id).orElseThrow(PLAYLIST_NOT_FOUND);
        playlistRepository.delete(playlist);
    }

    @Transactional
    public Page<PlaylistResponse> search(PlaylistFilter request) {
        return playlistRepository.search(request).map(PlaylistResponse::new);
    }

    @SneakyThrows
    public void addSongToPlaylist(long playlistId, long songId) {
        Song song = songRepository.findById(songId).orElseThrow(SONG_NOT_FOUND);
        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow(PLAYLIST_NOT_FOUND);

        if (!playlist.getSongs().contains(song)) {
            playlist.getSongs().add(song);
            playlistRepository.save(playlist);
        } else {
            throw new IllegalAccessException("Такая песня уже есть в плейлисте");
        }
    }

    @SneakyThrows
    @Transactional
    public void deleteSongFromPlaylist(long playlistId, long songId) {
        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow(PLAYLIST_NOT_FOUND);
        Song song = songRepository.findById(songId).orElseThrow(SONG_NOT_FOUND);

        if (playlist.getSongs().contains(song)) {
            playlist.getSongs().remove(song);
            playlistRepository.save(playlist);
        } else {
            throw new IllegalAccessException("Такой песни нету в плейлисте");
        }
    }
}
