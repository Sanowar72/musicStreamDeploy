package com.example.musiApi.service;

import com.example.musiApi.model.Song;
import com.example.musiApi.repo.SongRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongService {
    @Autowired
    SongRepo songRepo;

    public void saveSong(List<Song> song) {
        songRepo.saveAll(song);
    }

    public List<Song> allsong() {
        return songRepo.findAll();
    }

    public Song getSongById(Long songId) {
        return songRepo.findFirstBySongId(songId);
    }

    public void deleteById(Long id) {
        songRepo.deleteById(id);
    }

    public void updateSong(Song song) {
        songRepo.save(song);
    }
}
