package com.ota.tour.service;

public interface SequenceGeneratorService {

    Long generateSequence(String collectionName);

    <T> String getCollectionName(Class<T> tClass);

    <T> Long generateSequence(Class<T> tClass);

}
