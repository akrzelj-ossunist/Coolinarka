package oss.akrzelj.controllers.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import oss.akrzelj.controllers.interfaces.FavoriteController;
import oss.akrzelj.dtos.FavoritesDto;
import oss.akrzelj.dtos.RecipeResDto;
import oss.akrzelj.exceptions.AlreadyExistException;
import oss.akrzelj.exceptions.InvalidArgumentsException;
import oss.akrzelj.exceptions.ObjectDoesntExistException;
import oss.akrzelj.services.interfaces.FavoriteService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/favorite")
@CrossOrigin
public class FavoriteControllerImpl implements FavoriteController {
    private final FavoriteService favoriteService;

    @Autowired
    public FavoriteControllerImpl(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<Boolean> addToFavorites(FavoritesDto favoritesDto) throws InvalidArgumentsException, ObjectDoesntExistException, AlreadyExistException {
        favoriteService.addToFavorites(favoritesDto);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> removeFromFavorites(@PathVariable("id") String favoritesId) throws ObjectDoesntExistException, InvalidArgumentsException {
        favoriteService.remove(favoritesId);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @Override
    @GetMapping("/list/user/{id}")
    public ResponseEntity<List<RecipeResDto>> getAll(@PathVariable("id") String userId, @RequestParam Map<String, String> allParams) throws ObjectDoesntExistException, InvalidArgumentsException {
        int page = Integer.parseInt(allParams.get("page"));
        int size = Integer.parseInt(allParams.get("size"));
        List<RecipeResDto> favorites = favoriteService.getAll(userId, page, size);
        return ResponseEntity.ok().body(favorites);
    }

    @Override
    @GetMapping("/query/user/{id}")
    public ResponseEntity<List<RecipeResDto>> listAllFavorites(@PathVariable("id") String userId, @RequestParam Map<String, String> allParams) {
        List<RecipeResDto> filteredFavorites = favoriteService.filterByParams(userId, allParams);
        return ResponseEntity.ok().body(filteredFavorites);
    }
}
