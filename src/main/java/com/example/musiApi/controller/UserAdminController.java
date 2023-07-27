package com.example.musiApi.controller;

import com.example.musiApi.dto.SignIn;
import com.example.musiApi.dto.SignUpOutput;
import com.example.musiApi.model.Song;
import com.example.musiApi.model.UserAdmin;
import com.example.musiApi.service.SongService;
import com.example.musiApi.service.UserAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserAdminController {
    @Autowired
    UserAdminService userAdminService;

    @Autowired
    SongService songService;


    @PostMapping("admin")
    public SignUpOutput addadmin(@RequestBody UserAdmin userAdmin){
        return userAdminService.addAdmin(userAdmin);
    }
    @GetMapping("admin")
    public SignUpOutput signin(@RequestBody SignIn signIn){
        return userAdminService.signin(signIn);
    }
    @GetMapping("all-admin")
    public List<UserAdmin> allAdmin(@RequestBody SignIn signIn){
        if(userAdminService.verifyUserCredentials(signIn.getSignInEmail(), signIn.getSignInPassword())){
            return userAdminService.getAllAdmin();
        }else {
            throw new RuntimeException("Invalid email or password."); // Or use a more specific exception type if needed
        }

    }
    @GetMapping("song/{email}/{password}")
    public String addSong(@PathVariable String email, @PathVariable String password, @RequestBody List<Song> song){
        if(userAdminService.verifyUserCredentials(email, password)){
            songService.saveSong(song);
            return "song Added...";
        }else {
            throw new RuntimeException("Invalid email or password."); // Or use a more specific exception type if needed
        }

    }
    @PutMapping("song/{email}/{password}")
    public String updateSong(@PathVariable String email, @PathVariable String password,@RequestBody Song song){
        if(userAdminService.verifyUserCredentials(email, password)) {
            songService.updateSong(song);
            return "Updated Successfully....";
        }else {
            throw new RuntimeException("Invalid email or password."); // Or use a more specific exception type if needed
        }
    }
    @DeleteMapping("byid/{id}/{email}/{password}")
    public String deleteById(@PathVariable Long id,@PathVariable String email, @PathVariable String password){
        if(userAdminService.verifyUserCredentials(email, password)){
            Song tempSong=songService.getSongById(id);
            if(tempSong!=null){
                songService.deleteById(id);
                return "Deleted SUccessfully....";
            }else{
                throw new RuntimeException("Invalid SongId..");
            }
        }else {
            throw new RuntimeException("Invalid email or password."); // Or use a more specific exception type if needed
        }
    }
}
