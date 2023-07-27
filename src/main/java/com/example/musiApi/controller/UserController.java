package com.example.musiApi.controller;

import com.example.musiApi.dto.SignUpOutput;
import com.example.musiApi.model.Song;
import com.example.musiApi.model.User;
import com.example.musiApi.service.SongService;
import com.example.musiApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class UserController {
    @Autowired
    UserService userService;
   @Autowired
   SongService songService;
    @PostMapping("user")
    public SignUpOutput adduser(@RequestBody User user){
        return userService.adduser(user);
    }
    @GetMapping("allsong/{email}/{password}")
    public List<Song> allsong(@PathVariable String email,@PathVariable String password){
        if(userService.verifyUserCredentials(email,password)){
            return songService.allsong();
        }else {
            throw new RuntimeException("Invalid email or password."); // Or use a more specific exception type if needed
        }
    }

    @PostMapping("add-song-to-playlist/{email}/{password}/{songId}")
    public SignUpOutput addSongToPlaylist(
            @PathVariable String email,
            @PathVariable String password,
            @PathVariable Long songId
    ) {
        if (userService.verifyUserCredentials(email, password)) {
            User user = userService.getUserByEmail(email);
            List<Song> playlist = user.getSongs();

            // Get the Song entity by songId
           Song song = songService.getSongById(songId);
            // Check if the song is already in the playlist
            if(song==null) {
                return new SignUpOutput(false, "SongId is Invalid...");
            }
                for (Song ele : playlist) {
                    if (ele.getSongId().equals(songId)) {
                        return new SignUpOutput(false, "Song is already in the playlist.");
                    }
                }

       // Add the song to the user's playlist
            playlist.add(song);
            user.setSongs(playlist);
            userService.saveUser(user);

            return new SignUpOutput(true, "Song added to playlist successfully.");
        } else {
            throw new RuntimeException("Invalid email or password.");
        }
    }


    @GetMapping("playlist/{email}/{password}")
    public List<Song> getUserPlaylist(@PathVariable String email, @PathVariable String password) {
        if (userService.verifyUserCredentials(email, password)) {
            User user = userService.getUserByEmail(email);
            return user.getSongs();
        } else {
            throw new RuntimeException("Invalid email or password.");
        }
    }



}
