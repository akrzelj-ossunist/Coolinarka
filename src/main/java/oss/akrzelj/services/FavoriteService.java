package oss.akrzelj.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import oss.akrzelj.repositories.FavoriteRepository;

@Service
public class FavoriteService {
    @Autowired
    FavoriteRepository favoriteRepository;

}
