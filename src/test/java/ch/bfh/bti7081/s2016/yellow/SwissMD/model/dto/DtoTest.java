package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Diagnosis;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Doctor;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Drug;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Illness;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Meeting;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Patient;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Prescription;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.IllegalDateRangeException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.util.DangerStateType;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.util.DateRange;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.util.MeetingStateType;

public abstract class DtoTest {
	public static String ILLNESS_CODE = "ilness-code";
	public static Long ILLNESS_ID = 1898L;
	public static String ILLNESS_NAME = "ilness-name";
	public static Long DRUG_ID = 12312L;
	private static final int DRUG_MAXDOSE = 1232135;
	private static final String DRUG_SUBSTANCE = "Sand und Schmerz";
	private static final String DRUG_SUBSTANCEQUANTITY = "quantity";
	private static final String DRUG_TRADENAME = "TRADENAME";
	private static final int PRESCRIPTION_DOSIS = 999;
	private static final Long PRESCRIPTION_ID = 1994L;
	private static final Date DATARANGE_FROM = new Date(757386061);
	private static final Date DATARANGE_TO = new Date();
	private static final String PATIENT_ADDRESS = "Infinity Loop 99";
	private static final Date PATIENT_BIRTHDATE = new Date(757389061);
	private static final String PATIENT_CITY = "Bay Area City";
	private static final DangerStateType PATIENT_DANGERSTATETYPE = DangerStateType.CRISIS;
	private static final String PATIENT_DTYPE = "Patient";
	private static final Long PATIENT_FAMILYDOCTORID = 788778L;
	private static final String PATIENT_NAME = "Au weh weh";
	private static final String PATIENT_ZIP = "3000";
	private static final String PATIENT_MOBILE = "0777777777";
	private static final String PATIENT_LANDLINE = "0313333333";
	private static final Long PATIENT_LEGALAID = 444L;
	private static final Long PATIENT_ID = 77333L;
	private static final Long DIAGNOSIS_ID = 68483882L;
	private static final Date DIAGNOSIS_DATE = new Date();
	private static final String DIAGNOSIS_NOTES = "Notizen Diagnose";
	private static final Long MEETING_ID = 432188L;
	private static final String DOCTOR_DTYPE = "Doctor";
	private static final String MEETING_NOTES = "Der Patient ist nervös.";
	private static final Date MEETING_DATE = new Date(757389061);
	private static final MeetingStateType MEETING_STATE = MeetingStateType.PERFORMED;

	/**
	 * Testet, ob beim DTO(Entity)-Konstruktor alle Attribute korrekt übernommen
	 * werden
	 */
	@Test
	public abstract void testConstructor();

	public Diagnosis createTestDiagnosis() {
		Diagnosis diagnosis = new Diagnosis();
		diagnosis.setId(DIAGNOSIS_ID);
		diagnosis.setDate(DIAGNOSIS_DATE);
		diagnosis.setIllness(createTestIllness());
		diagnosis.setPatient(createTestPatient());
		diagnosis.setNotes(DIAGNOSIS_NOTES);
		return diagnosis;
	}

	public void validateDiagnosis(DiagnosisDTO sut) {
		Assert.assertEquals(DIAGNOSIS_NOTES, sut.getNotes());
		Assert.assertEquals(DIAGNOSIS_DATE, sut.getDate());
		Assert.assertEquals(DIAGNOSIS_ID, sut.getId());
		validateIllness(sut.getIllness());
		validatePatient(sut.getPatient());
	}

	public Patient createTestPatient() {
		Patient patient = new Patient();
		patient.setId(PATIENT_ID);
		patient.setDtype(PATIENT_DTYPE);
		patient.setName(PATIENT_NAME);
		patient.setBirthdate(PATIENT_BIRTHDATE);
		patient.setAddress(PATIENT_ADDRESS);
		patient.setZip(PATIENT_ZIP);
		patient.setCity(PATIENT_CITY);
		patient.setMobile(PATIENT_MOBILE);
		patient.setLandline(PATIENT_LANDLINE);
		patient.setLegalAid(PATIENT_LEGALAID);
		patient.setFamilyDoctor(PATIENT_FAMILYDOCTORID);
		patient.setDangerState(PATIENT_DANGERSTATETYPE);
		// TODO add meetings
		// TODO add prescriptions
		return patient;
	}

	public void validatePatient(PatientDTO sut) {
		Assert.assertEquals(PATIENT_ID, sut.getId());
		Assert.assertEquals(PATIENT_DTYPE, sut.getDtype());
		Assert.assertEquals(PATIENT_NAME, sut.getName());
		Assert.assertEquals(PATIENT_BIRTHDATE, sut.getBirthdate());
		Assert.assertEquals(PATIENT_ADDRESS, sut.getAddress());
		Assert.assertEquals(PATIENT_ZIP, sut.getZip());
		Assert.assertEquals(PATIENT_CITY, sut.getCity());
		Assert.assertEquals(PATIENT_MOBILE, sut.getMobile());
		Assert.assertEquals(PATIENT_LANDLINE, sut.getLandline());
		Assert.assertEquals(PATIENT_LEGALAID, sut.getLegalAid());
		Assert.assertEquals(PATIENT_FAMILYDOCTORID, sut.getFamilyDoctor());
		// Assert.assertEquals(PATIENT_DANGERSTATETYPE, sut.getDangerState());
		// TODO test meetings
		// TODO test prescriptions
	}

	public Doctor createTestDoctor() {
		Doctor doctor = new Doctor();
		doctor.setId(PATIENT_ID);
		doctor.setDtype(DOCTOR_DTYPE);
		doctor.setName(PATIENT_NAME);
		doctor.setBirthdate(PATIENT_BIRTHDATE);
		doctor.setAddress(PATIENT_ADDRESS);
		doctor.setZip(PATIENT_ZIP);
		doctor.setCity(PATIENT_CITY);
		doctor.setMobile(PATIENT_MOBILE);
		doctor.setLandline(PATIENT_LANDLINE);
		// OfficeNumber not included, as it is not used in DoctorDTO
		// TODO: add Meetings
		return doctor;
	}

	public void validateDoctor(DoctorDTO sut) {
		Assert.assertEquals(PATIENT_ID, sut.getId());
		Assert.assertEquals(DOCTOR_DTYPE, sut.getDtype());
		Assert.assertEquals(PATIENT_NAME, sut.getName());
		Assert.assertEquals(PATIENT_BIRTHDATE, sut.getBirthdate());
		Assert.assertEquals(PATIENT_ADDRESS, sut.getAddress());
		Assert.assertEquals(PATIENT_ZIP, sut.getZip());
		Assert.assertEquals(PATIENT_CITY, sut.getCity());
		Assert.assertEquals(PATIENT_MOBILE, sut.getMobile());
		Assert.assertEquals(PATIENT_LANDLINE, sut.getLandline());
	}

	public Illness createTestIllness() {
		Illness illness = new Illness();
		illness.setId(ILLNESS_ID);
		illness.setCode(ILLNESS_CODE);
		illness.setName(ILLNESS_NAME);
		return illness;
	}

	public void validateIllness(IllnessDTO sut) {
		Assert.assertEquals(ILLNESS_ID, sut.getId());
		Assert.assertEquals(ILLNESS_CODE, sut.getCode());
		Assert.assertEquals(ILLNESS_NAME, sut.getName());
	}

	public Prescription createTestPrescription()
			throws IllegalDateRangeException {
		Prescription prescription = new Prescription();
		prescription.setId(PRESCRIPTION_ID);
		prescription.setDosisInMilligrams(PRESCRIPTION_DOSIS);
		prescription.setDrug(createTestDrug());
		prescription.setValidity(createTestDateRange());
		prescription.setPatient(createTestPatient());
		return prescription;
	}

	public void validatePrescription(PrescriptionDTO sut) {
		Assert.assertEquals(PRESCRIPTION_ID, sut.getId());
		Assert.assertEquals(PRESCRIPTION_DOSIS, sut.getDosisInMilligrams());
		validateDrug(sut.getDrug());
		validateDateRange(sut.getValidity());
		validatePatient(sut.getPatient());
	}

	public Drug createTestDrug() {
		Drug drug = new Drug();
		drug.setId(DRUG_ID);
		drug.setMaxDose(DRUG_MAXDOSE);
		drug.setSubstance(DRUG_SUBSTANCE);
		drug.setSubstanceQuantitiy(DRUG_SUBSTANCEQUANTITY);
		drug.setTradeName(DRUG_TRADENAME);
		return drug;
	}

	public void validateDrug(DrugDTO sut) {
		Assert.assertEquals(DRUG_ID, sut.getId());
		Assert.assertEquals(DRUG_MAXDOSE, sut.getMaxDose());
		Assert.assertEquals(DRUG_SUBSTANCE, sut.getSubstance());
		Assert.assertEquals(DRUG_SUBSTANCEQUANTITY, sut.getSubstanceQuantitiy());
		Assert.assertEquals(DRUG_TRADENAME, sut.getTradeName());
	}

	public Meeting createTestMeeting() {
		Meeting meeting = new Meeting();
		meeting.setId(MEETING_ID);
		meeting.setPatient(createTestPatient());
		meeting.setDoctor(createTestDoctor());
		meeting.setNotes(MEETING_NOTES);
		meeting.setAppointmentTime(MEETING_DATE);
		meeting.setStateType(MEETING_STATE);
		return meeting;
	}

	public void validateMeeting(MeetingDTO sut) {
		Assert.assertEquals(MEETING_ID, sut.getId());
		validatePatient(sut.getPatient());
		validateDoctor(sut.getDoctor());
		Assert.assertEquals(MEETING_NOTES, sut.getNotes());
		Assert.assertEquals(MEETING_DATE, sut.getAppointmentTime());
		Assert.assertEquals(MEETING_STATE, sut.getMeetingState());
	}

	public DateRange createTestDateRange() throws IllegalDateRangeException {
		DateRange dateRange = new DateRange(DATARANGE_FROM, DATARANGE_TO);
		return dateRange;
	}

	public void validateDateRange(DateRange sut) {
		Assert.assertEquals(DATARANGE_FROM, sut.getFrom());
		Assert.assertEquals(DATARANGE_TO, sut.getTo());
	}
}
