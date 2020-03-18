package mops.model.classes;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.Singular;

import java.util.List;

@Builder(builderClassName = "ApplicantBuilder", toBuilder = true)
@Getter
@EqualsAndHashCode
@ToString
@JsonDeserialize(builder = Applicant.ApplicantBuilder.class)
public class Applicant {
    private final String title;
    private final String firstName;
    private final String surname;
    private final String nameSuffix;
    private final Address address;
    private final String gender;
    private final String birthday;
    private final String nationality;
    private final String birthplace;
    private final String course;
    private final Status status;
    private final Certificate certs;
    @Singular
    private final List<Application> applications;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ApplicantBuilder {

    }

}
