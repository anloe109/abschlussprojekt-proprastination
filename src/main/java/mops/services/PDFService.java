package mops.services;

import mops.model.Document;
import mops.model.DocumentWithBachelor;
import mops.model.classes.Address;
import mops.model.classes.Applicant;
import mops.model.classes.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
@Service
public class PDFService {

    private Logger logger = LoggerFactory.getLogger(PDFService.class);

    private Document document;


    /**
     * Generates an Application PDF given the parameters.
     *
     * @param application Application.
     * @param applicant   Applicant.
     * @return filepath to file.
     */
    public File generatePDF(final Application application, final Applicant applicant) {
        File tmpFile = null;
        try {
            tmpFile = File.createTempFile("bewerbung", ".pdf");
            tmpFile.deleteOnExit();
            document = new DocumentWithBachelor();
            addApplicationInfoToPDF(application);
            addApplicantInfoToPDF(applicant);
            document.addGeneralInfos();
            document.save(tmpFile);
            logger.debug("Saved PDF to:" + tmpFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Saving PDF failed for: " + tmpFile);
        }
        return tmpFile;
    }

    private void addApplicationInfoToPDF(final Application application) throws IOException {
        document.setField("Stunden", String.valueOf(application.getFinalHours()));
        document.setField("Vertragsart", "Einstellung");
    }

    private void addApplicantInfoToPDF(final Applicant applicant) throws IOException {
        document.setField("E-Mail", applicant.getUniserial() + "@hhu.de");
        document.setField("Vorname", applicant.getFirstName());
        document.setField("Name", applicant.getSurname());
        document.setField("Geburtsdatum", applicant.getBirthday());
        document.setField("Geburtsort", applicant.getBirthplace());
        document.setField("Staatsangehörigkeit", applicant.getNationality());
        document.setField("Studiengang", applicant.getCourse());
        document.setGender("männlich");
        addApplicantAdressInfoToPDF(applicant.getAddress());
    }

    private void addApplicantAdressInfoToPDF(final Address address) throws IOException {
        document.setField("Anschrift (Straße)", address.getStreet());
        document.setField("Anschrift (Hausnummer)", address.getHouseNumber());
        document.setField("Anschrift (PLZ)", String.valueOf(address.getZipcode()));
        document.setField("Anschrift (Ort)", address.getCity());
        document.setField("Anschrift (Land)", CSVService.getCodeForCountry(address.getCountry()));
    }

}
