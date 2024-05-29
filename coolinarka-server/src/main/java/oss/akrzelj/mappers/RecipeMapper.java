package oss.akrzelj.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.web.multipart.MultipartFile;
import oss.akrzelj.dtos.RecipeResDto;
import oss.akrzelj.dtos.recipe.RecipeDto;
import oss.akrzelj.exceptions.InvalidArgumentsException;
import oss.akrzelj.exceptions.ObjectDoesntExistException;
import oss.akrzelj.models.Recipe;
import oss.akrzelj.services.interfaces.UserService;

import java.io.IOException;
import java.util.List;

@Mapper(componentModel = "spring", uses = { UserService.class })
public interface RecipeMapper {

    @Mapping(source = "image.originalFilename", target = "image")
    @Mapping(source = "user", target = "user")
    Recipe recipeDtoToRecipe(RecipeDto recipeDto);

    RecipeResDto recipeToRecipeDto(Recipe recipe);

    List<RecipeResDto> recipeListToRecipeDtoList(List<Recipe> recipeList);
}
