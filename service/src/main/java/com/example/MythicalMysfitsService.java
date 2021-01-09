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
        mysfit.setName("Rygard 1.53");
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
