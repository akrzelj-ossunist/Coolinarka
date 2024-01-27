package oss.akrzelj.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import oss.akrzelj.repositories.RecipeRepository;

@Service
public class RecipeService {
    @Autowired
    RecipeRepository recipeRepository;

}
