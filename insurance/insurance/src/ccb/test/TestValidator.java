package ccb.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ccb.Validator;

public class TestValidator {

	@Test
	public void testIsValidLocalNullInValid() {
		assertFalse(Validator.isValidLocal(null));
	}
	
	@Test
	public void testIsValidLocalBlankInValid() {
		assertFalse(Validator.isValidLocal(""));
	}
	
	
	
	@Test
	public void testIsValidLocalADMValid() {
		assertTrue(Validator.isValidLocal("ADM - PEDREIRA - SP"));
	}
	
	@Test
	public void testIsValidLocalADMInvalid() {
		assertFalse(Validator.isValidLocal("ADM PEDREIRA - SP"));
	}
	
	@Test
	public void testIsValidLocalPIAValid() {
		assertTrue(Validator.isValidLocal("PIA - AMPARO"));
	}
	
	@Test
	public void testIsValidLocalPIAInvalid() {
		assertFalse(Validator.isValidLocal("PIA-AMPARO"));
	}
	
	@Test
	public void testIsValidLocalDLValid() {
		assertTrue(Validator.isValidLocal("DL - PEDREIRA"));
	}
	
	@Test
	public void testIsValidLocalDLInvalid() {
		assertFalse(Validator.isValidLocal("DL PEDREIRA"));
	}
	
	@Test
	public void testIsValidLocalBRValid() {
		assertTrue(Validator.isValidLocal("BR 22-2413"));
	}
	
	@Test
	public void testIsValidLocalBRInvalid() {
		assertFalse(Validator.isValidLocal("BR22-2413"));
	}
	
	@Test
	public void testIsValidLocalDRValid() {
		assertTrue(Validator.isValidLocal("DR - AMPARO"));
	}
	
	@Test
	public void testIsValidLocalDRInvalid() {
		assertFalse(Validator.isValidLocal("DR AMPARO"));
	}
	
	


}
