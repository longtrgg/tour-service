package com.ota.tour.service.impl;

import com.ota.tour.data.document.DatabaseSequence;
import com.ota.tour.service.SequenceGeneratorService;
import com.ota.tour.util.Generators;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SequenceGeneratorServiceImpl implements SequenceGeneratorService {

    private final MongoOperations mongoOperations;

    public SequenceGeneratorServiceImpl(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    @Override
    public Long generateSequence(String collectionName) {
        DatabaseSequence counter = mongoOperations.findAndModify(
                Query.query(Criteria.where("_id").is(Generators.buildSequenceName(collectionName))),
                new Update().inc("seq", 1),
                FindAndModifyOptions.options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }

    @Override
    public <T> String getCollectionName(Class<T> tClass) {
        return Generators.buildSequenceName(mongoOperations.getCollectionName(tClass));
    }

    @Override
    public <T> Long generateSequence(Class<T> tClass) {
        return generateSequence(Generators.buildSequenceName(getCollectionName(tClass)));
    }
}
