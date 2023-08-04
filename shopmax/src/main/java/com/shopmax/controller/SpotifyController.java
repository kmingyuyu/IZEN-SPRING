package com.shopmax.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopmax.service.CreateToken;
import com.shopmax.service.MusicSearch;

@RestController
public class SpotifyController {
	
	// CreateToken 객체를 생성하여 access token을 얻어옵니다.
    private final CreateToken createToken = new CreateToken();
    private final String accessToken = createToken.accesstoken();

    @GetMapping("/search")
    public ResponseEntity<String> searchSong(@RequestParam("q") String query) {
        MusicSearch musicSearch = new MusicSearch();
        String response = musicSearch.search(accessToken, query);
        System.out.println(accessToken +  query);
        return ResponseEntity.ok(response);
    }
	
	
}
