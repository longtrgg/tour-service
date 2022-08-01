package com.ota.tour.data.document;

import com.ota.tour.data.model.Translation;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "scheduleActivity")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ScheduleActivityDocument extends SequenceBaseDocument {
    private static final long serialVersionUID = 1L;

    private List<Translation> name;
    private List<Translation> description;
    private List<Translation> note;
    private String startAt;
    private Long scheduleId;
    private Boolean isDelete = false;
}
