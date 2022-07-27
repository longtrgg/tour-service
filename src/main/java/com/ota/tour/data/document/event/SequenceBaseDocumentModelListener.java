package com.ota.tour.data.document.event;

import com.ota.tour.data.document.SequenceBaseDocument;
import com.ota.tour.service.SequenceGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SequenceBaseDocumentModelListener<T extends SequenceBaseDocument> extends AbstractMongoEventListener<T> {

    private final SequenceGeneratorService sequenceGeneratorService;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<T> event) {
        if (event.getSource().getId() == null || event.getSource().getId() < 1) {
            event.getSource().setId(sequenceGeneratorService.generateSequence(event.getCollectionName()));
        }
    }
}
