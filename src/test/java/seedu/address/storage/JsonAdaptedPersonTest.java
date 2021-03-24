package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Date;
import seedu.address.model.person.Description;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Phone;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@1hel";
    private static final String INVALID_DATE = "55-02-2021";
    private static final String INVALID_DESCRIPTION = " ";
    private static final String INVALID_NRIC = "S1146H";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_DATE = BENSON.getDate().toString();
    private static final String VALID_DESCRIPTION = BENSON.getDescription().toString();
    private static final String VALID_NRIC = BENSON.getNric().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_REMARK = BENSON.getRemark().toString();
    private static final String VALID_FOLLOWUP = BENSON.getFollowUp().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
        .map(JsonAdaptedTag::new)
        .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
            new JsonAdaptedPerson(INVALID_NAME, VALID_DATE, VALID_NRIC, VALID_PHONE,
                    VALID_EMAIL, VALID_ADDRESS, VALID_DESCRIPTION, VALID_REMARK, VALID_FOLLOWUP, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
            new JsonAdaptedPerson(null, VALID_DATE, VALID_NRIC, VALID_PHONE,
                    VALID_EMAIL, VALID_ADDRESS, VALID_DESCRIPTION, VALID_REMARK, VALID_FOLLOWUP, VALID_TAGS);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, INVALID_DATE, VALID_NRIC, VALID_PHONE,
                        VALID_EMAIL, VALID_ADDRESS, VALID_DESCRIPTION, VALID_REMARK, VALID_FOLLOWUP, VALID_TAGS);
        String expectedMessage = Date.MESSAGE_DATE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, null, VALID_NRIC, VALID_PHONE,
                        VALID_EMAIL, VALID_ADDRESS, VALID_DESCRIPTION, VALID_REMARK, VALID_FOLLOWUP, VALID_TAGS);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidIc_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_DATE, INVALID_NRIC, VALID_PHONE,
                        VALID_EMAIL, VALID_ADDRESS, VALID_DESCRIPTION, VALID_REMARK, VALID_FOLLOWUP, VALID_TAGS);
        String expectedMessage = Nric.MESSAGE_NRIC_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullIc_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_DATE, null, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_DESCRIPTION, VALID_REMARK, VALID_FOLLOWUP, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Nric.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
            new JsonAdaptedPerson(VALID_NAME, VALID_DATE, VALID_NRIC, INVALID_PHONE, VALID_EMAIL,
                    VALID_ADDRESS, VALID_DESCRIPTION, VALID_REMARK, VALID_FOLLOWUP, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
            new JsonAdaptedPerson(VALID_NAME, VALID_DATE, VALID_NRIC, null, VALID_EMAIL,
                    VALID_ADDRESS, VALID_DESCRIPTION, VALID_REMARK, VALID_FOLLOWUP, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
            new JsonAdaptedPerson(VALID_NAME, VALID_DATE, VALID_NRIC, VALID_PHONE, INVALID_EMAIL,
                    VALID_ADDRESS, VALID_DESCRIPTION, VALID_REMARK, VALID_FOLLOWUP, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
            new JsonAdaptedPerson(VALID_NAME, VALID_DATE, VALID_NRIC, VALID_PHONE, null,
                    VALID_ADDRESS, VALID_DESCRIPTION, VALID_REMARK, VALID_FOLLOWUP, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person =
            new JsonAdaptedPerson(VALID_NAME, VALID_DATE, VALID_NRIC, VALID_PHONE,
                    VALID_EMAIL, INVALID_ADDRESS, VALID_DESCRIPTION, VALID_REMARK, VALID_FOLLOWUP, VALID_TAGS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person =
            new JsonAdaptedPerson(VALID_NAME, VALID_DATE, VALID_NRIC, VALID_PHONE, VALID_EMAIL,
                    null, VALID_DESCRIPTION, VALID_REMARK, VALID_FOLLOWUP, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedPerson person =
            new JsonAdaptedPerson(VALID_NAME, VALID_DATE, VALID_NRIC, VALID_PHONE,
                    VALID_EMAIL, VALID_ADDRESS, INVALID_DESCRIPTION, VALID_REMARK, VALID_FOLLOWUP, VALID_TAGS);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedPerson person =
            new JsonAdaptedPerson(VALID_NAME, VALID_DATE, VALID_NRIC, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, null, VALID_REMARK, VALID_FOLLOWUP, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person =
            new JsonAdaptedPerson(VALID_NAME, VALID_DATE, VALID_NRIC, VALID_PHONE, VALID_EMAIL,
                    VALID_ADDRESS, VALID_DESCRIPTION, VALID_REMARK, VALID_FOLLOWUP, invalidTags);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
