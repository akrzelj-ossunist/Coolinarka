package oss.akrzelj.controllers.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import oss.akrzelj.controllers.interfaces.FavoriteController;
import oss.akrzelj.dtos.FavoritesDto;
import oss.akrzelj.dtos.RecipeResDto;
import oss.akrzelj.dtos.recipe.response.RecipeResponseDto;
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
    public ResponseEntity<Boolean> addToFavorites(@RequestBody FavoritesDto favoritesDto) throws InvalidArgumentsException, ObjectDoesntExistException, AlreadyExistException {
        favoriteService.addToFavorites(favoritesDto);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @Override
    @DeleteMapping("/recipe/{id}/user/{uid}")
    public ResponseEntity<Boolean> removeFromFavorites(@PathVariable("id") String favoritesId, @PathVariable("uid") String userId) throws ObjectDoesntExistException, InvalidArgumentsException {
        favoriteService.remove(favoritesId, userId);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @Override
    @GetMapping("/list/user/{id}")
    public ResponseEntity<List<RecipeResponseDto>> getAll(@PathVariable("id") String userId) throws ObjectDoesntExistException, InvalidArgumentsException {
        List<RecipeResponseDto> favorites = favoriteService.getAll(userId);
        return ResponseEntity.ok().body(favorites);
    }
}
