package com.ota.tour.data.document;

import com.ota.tour.data.model.OccupancyDescriptionType;
import com.ota.tour.data.model.Translation;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "occupancy")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class OccupancyDocument extends SequenceBaseDocument {
    private static final long serialVersionUID = 1L;

    private Long activityId;
    private List<Translation> name;
    private List<Translation> description;
    private Integer minOccupancy;
    private Integer maxOccupancy;
    private Integer minAge;
    private Integer maxAge;
    private Integer minLengthCm;
    private Integer maxLengthCm;
    private Boolean primary;
    private Boolean isDelete = false;
    private OccupancyDescriptionType descriptionType = OccupancyDescriptionType.HEIGHT;
}
