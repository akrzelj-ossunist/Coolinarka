package oss.akrzelj.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import oss.akrzelj.dtos.recipe.RecipeDto;
import oss.akrzelj.models.Recipe;
import oss.akrzelj.services.interfaces.UserService;

@Mapper(componentModel = "spring", uses = { UserService.class })
public interface RecipeMapper {

    @Mapping(source = "image.originalFilename", target = "image")
    @Mapping(source = "user", target = "user")
    Recipe recipeDtoToRecipe(RecipeDto recipeDto);

}
