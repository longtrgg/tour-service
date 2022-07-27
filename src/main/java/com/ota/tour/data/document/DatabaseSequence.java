package com.ota.tour.data.document;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@TypeAlias("databaseSequence")
@Document(collection = "databaseSequence")
@NoArgsConstructor
public class DatabaseSequence {
    private static final long serialVersionUID = 1L;

    @Id
    protected String id;

    private long seq;
}
