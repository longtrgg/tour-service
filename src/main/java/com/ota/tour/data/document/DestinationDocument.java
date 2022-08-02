package com.ota.tour.data.document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "destination")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class DestinationDocument extends SequenceBaseDocument {
    private static final long serialVersionUID = 1L;

    private String name;
    private String slug;
    private String fullName;
}
