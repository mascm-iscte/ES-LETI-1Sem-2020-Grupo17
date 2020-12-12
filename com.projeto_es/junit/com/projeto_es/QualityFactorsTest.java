package com.projeto_es;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.InputMismatchException;

import org.junit.Test;

public class QualityFactorsTest {
	
	public boolean DCI(String tool, String def) {
		return tool.equals("TRUE") && def.equals("TRUE");
	}
	public boolean DII(String tool, String def) {
		return tool.equals("TRUE") && def.equals("FALSE");
	}
	public boolean ADCI(String tool, String def) {
		return tool.equals("FALSE") && def.equals("FALSE");
	}
	public boolean ADII(String tool, String def) {
		return tool.equals("FALSE") && def.equals("TRUE");
	}
	
	@Test
	public void testDCI() {
		String tool = "TRUE";
		String def = "TRUE";
		assert(DCI(tool,def) == true);
	}

	@Test
	public void testDII() {
		String tool = "TRUE";
		String def = "FALSE";
		assert(DII(tool,def) == true);
	}

	@Test
	public void testADCI() {
		String tool = "FALSE";
		String def = "FALSE";
		assert(ADCI(tool,def) == true);
	}

	@Test
	public void testADII() {
		String tool = "FALSE";
		String def = "TRUE";
		assert(ADII(tool,def) == true);
	}

	@Test
	public void testWhatFactor() {
		String tool = "TRUE";
		String def = "FALSE";
			try {
				if(DCI(tool, def)) {
					assertTrue("DCI", true);
				}
				else if(DII(tool, def)) {
					assertTrue("DII", true);
				}
				else if(ADCI(tool, def)) {
					assertTrue("ADCI", true);
				}
				else if(ADII(tool, def)) {
					assertTrue("ADII", true);
				}
				fail("Expecting InputMismatchException");
				fail("Expecting NullPointerException");
			}
		catch(InputMismatchException e){
			/*
			 * assertThat(e.getMessage(), is("false(??)");
			 */
		}
		catch(NullPointerException e) {
			/*
			 * assertThat(e.getMessage(), is("false(??)");
			 */
		}
	}

}
