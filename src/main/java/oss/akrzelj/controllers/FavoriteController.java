package oss.akrzelj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import oss.akrzelj.services.FavoriteService;

@RestController
@RequestMapping("/favorite")
@CrossOrigin
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

}
