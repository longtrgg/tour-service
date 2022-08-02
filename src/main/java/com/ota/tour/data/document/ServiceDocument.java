package com.ota.tour.data.document;

import com.ota.tour.data.model.Timing;
import com.ota.tour.data.model.Translation;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "service")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ServiceDocument extends SequenceBaseDocument {
    private static final long serialVersionUID = 1L;

    private List<Translation> name;
    private List<List<Translation>> inclusions;
    private List<List<Translation>> exclusions;
    private Boolean refundable;
    private Boolean hasInventory;
    private Boolean status;
    private Long activityId;
    private Timing timing;
    private Boolean isDelete = false;
}
