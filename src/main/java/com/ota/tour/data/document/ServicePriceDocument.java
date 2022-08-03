package com.ota.tour.data.document;

import com.ota.tour.data.model.Currency;
import com.ota.tour.data.model.ZoneDateTimeRange;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.DayOfWeek;
import java.util.List;

@Data
@Document(collection = "servicePrice")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ServicePriceDocument extends SequenceBaseDocument {
    private static final long serialVersionUID = 1L;

    private Long serviceId;
    private Long occupancyId;
    private Double originPrice;
    private Double price;
    private Currency currency;
    private Double convertRateToVND;
    private ZoneDateTimeRange validity;
    private List<DayOfWeek> validDaysOfWeek;
    private Double priceBeforePromo;
}
