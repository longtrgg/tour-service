package com.ota.tour.data.document;

import com.ota.tour.data.model.ZoneDateTimeRange;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.DayOfWeek;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "inventory")
@NoArgsConstructor
public class InventoryDocument extends SequenceBaseDocument {
    private static final long serialVersionUID = 1L;

    private Long activityId;
    private Long serviceId;
    private ZoneDateTimeRange validity;
    private List<DayOfWeek> validDaysOfWeek;
    private Boolean block;
    private Integer available;
}
