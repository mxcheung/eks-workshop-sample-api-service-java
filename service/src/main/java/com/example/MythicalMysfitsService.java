package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import org.springframework.stereotype.Service;

@Service
public class MythicalMysfitsService {

    private final AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
    private DynamoDBMapper mapper = new DynamoDBMapper(client);
    
    public Mysfits getAllMysfits() {

        List<Mysfit> mysfits = mapper.scan(Mysfit.class, new DynamoDBScanExpression());
        Mysfits allMysfits = new Mysfits(mysfits);

        return allMysfits;
    }


    public Mysfit getMysfit(String mysfitId){
        
        Mysfit mysfit = new Mysfit();
        mysfit.setMysfitId(mysfitId);
        mysfit.setName("Hasla 1.4.005");
        mysfit.setSpecies("Haetae");
        mysfit.setLawchaos("Neutral");
        mysfit.setAge(2000000000);
        mysfit.setGoodevil("Good");
        mysfit.setDescription("Hasla's presence warms every room. For the last 2 billion years, she's made visitors from far-away lands and the galaxy next door feel immediately at ease. Usually it's because of her big heart, but sometimes it's because of the fire she breathes—especially after eating garlic and starlight. Hasla loves togetherness, board games, and asking philosophical questions that leave people pondering the meaning of life as they fall asleep at night.");
        
        return mapper.load(Mysfit.class, mysfitId);
    }

    public void likeMysfit(String mysfitId) {
        Mysfit mysfitToUpdate = mapper.load(Mysfit.class, mysfitId);
        Integer likes = mysfitToUpdate.getLikes() + 1;
        mysfitToUpdate.setLikes(likes);
        mapper.save(mysfitToUpdate);
    }

    public void adoptMysfit(String mysfitId) {
        Mysfit mysfitToUpdate = new Mysfit();
        mysfitToUpdate.setAdopted(true);
    }

    public Mysfits queryMysfits(String filter, String value) {
        HashMap<String, AttributeValue> attribValue = new HashMap<String, AttributeValue>();
        attribValue.put(":"+value,  new AttributeValue().withS(value));

        DynamoDBQueryExpression<Mysfit> queryExpression = new DynamoDBQueryExpression<Mysfit>()
                .withIndexName(filter+"Index")
                .withKeyConditionExpression(filter + "= :" + value)
                .withExpressionAttributeValues(attribValue)
                .withConsistentRead(false);

        List<Mysfit> mysfits = mapper.query(Mysfit.class, queryExpression);
        Mysfits allMysfits = new Mysfits(mysfits);

        return allMysfits;        
    }

}
