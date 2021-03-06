package mops.model.classes.webclasses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mops.model.classes.Priority;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
public class WebDistributorApplication {
    private String module;
    private String moduleShort;
    private Priority applicantPriority;
    private Priority organizerPriority;
    private String minHours;
    private String maxHours;
    private String organizerHours;
}
