package com.example;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class MythicalMysfitsService {


    public Mysfits getAllMysfits() {

        List<Mysfit> mysfits = new ArrayList<>();
        mysfits.add(getMysfit("1L"));
        Mysfits allMysfits = new Mysfits(mysfits);

        return allMysfits;
    }


    public Mysfit getMysfit(String mysfitId){
        
        Mysfit mysfit = new Mysfit();
        mysfit.setMysfitId(mysfitId);
        mysfit.setName("Hasla 1.3");
        mysfit.setSpecies("Haetae");
        mysfit.setLawchaos("Neutral");
        mysfit.setAge(2000000000);
        mysfit.setGoodevil("Good");
        mysfit.setDescription("Hasla's presence warms every room. For the last 2 billion years, she's made visitors from far-away lands and the galaxy next door feel immediately at ease. Usually it's because of her big heart, but sometimes it's because of the fire she breathes—especially after eating garlic and starlight. Hasla loves togetherness, board games, and asking philosophical questions that leave people pondering the meaning of life as they fall asleep at night.");
        return mysfit;
    }

    public void likeMysfit(String mysfitId) {

        Mysfit mysfitToUpdate = new Mysfit();
        mysfitToUpdate.setMysfitId(mysfitId);
        mysfitToUpdate.setName("Rygard");               
        Integer likes = mysfitToUpdate.getLikes() + 1;
        mysfitToUpdate.setLikes(likes);    
    }

    public void adoptMysfit(String mysfitId) {
        Mysfit mysfitToUpdate = new Mysfit();
        mysfitToUpdate.setAdopted(true);
    }

    public Mysfits queryMysfits(String filter, String value) {
        Mysfits allMysfits = getAllMysfits();
        return allMysfits;
    }

}
