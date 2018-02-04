package test;

import static org.junit.Assert.*;
import main.StackWidget;
import main.WidgetElementType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StackWidgetTest {

	StackWidget myStackWidgetTester;
	
	@Before
	public void setUp() throws Exception {
		myStackWidgetTester = new StackWidget();
	}

	@After
	public void tearDown() throws Exception {
	}

	
//--- This section tests adding widgets with valid parametrization to WidgetStack
	
	@Test
	public void testAddFirstWellParametrizedWidgetSucceeds() {
		assertTrue("Adding a widget element should succeed", myStackWidgetTester.addWidgetElement(WidgetElementType.WIDGETELEMENT_FUNNYTEXTFIELD, "1234", 0, 100, 20, 50));
		assertTrue("Widget with id=1234 should be in list", myStackWidgetTester.isWidgetElementInList("1234"));
		assertEquals("There should be 1 widget in the StackWidget", myStackWidgetTester.getNumWidgetElements(), 1);
	}

//This section tests adding widgets//
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddWidgetElementIDLengthTooLong(){
		//Test *1*
		myStackWidgetTester.addWidgetElement(WidgetElementType.WIDGETELEMENT_FUNNYTEXTFIELD, "12345", 1, 2, 1, 2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddWidgetElementIDLengthTooShort(){
		//Test *2*
		myStackWidgetTester.addWidgetElement(WidgetElementType.WIDGETELEMENT_FUNNYTEXTFIELD, "123", 1, 2, 1, 2);
	}
	
	@Test
	public void testAddWidgetElementIDLengthCorrect(){
		//Test *3*
		assertTrue("Widget with id=1234 should be added to the list", myStackWidgetTester.addWidgetElement(WidgetElementType.WIDGETELEMENT_FUNNYTEXTFIELD, "1234", 1, 2, 1, 2));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddEmptyWidgetElementID(){
		//Test *4*
		myStackWidgetTester.addWidgetElement(WidgetElementType.WIDGETELEMENT_FUNNYTEXTFIELD, "", 1, 2, 1, 2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddCharactersWidgetElementID(){
		//Test *5*
		myStackWidgetTester.addWidgetElement(WidgetElementType.WIDGETELEMENT_FUNNYTEXTFIELD, "abcd", 1, 2, 1, 2);
	}
	
	@Test
	public void testAddAlreadyExistsWidgetElementID(){
		//Test *6*
		myStackWidgetTester.addWidgetElement(WidgetElementType.WIDGETELEMENT_FUNNYTEXTFIELD, "1234", 1, 2, 1, 2);
		assertFalse(myStackWidgetTester.addWidgetElement(WidgetElementType.WIDGETELEMENT_FUNNYTEXTFIELD, "1234", 1, 2, 1, 2));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddNegativeMinHeight(){
		//Test *7*
		myStackWidgetTester.addWidgetElement(WidgetElementType.WIDGETELEMENT_FUNNYTEXTFIELD, "1234", -1, 2, 1, 2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddNegativeMaxHeight(){
		//Test *8*
		myStackWidgetTester.addWidgetElement(WidgetElementType.WIDGETELEMENT_FUNNYTEXTFIELD, "1234", 1, -1, 1, 2);
	}
	
	@Test
	public void testAddNoMoreThan5Widgets(){
		//Test *9*
		myStackWidgetTester.addWidgetElement(WidgetElementType.WIDGETELEMENT_FUNNYTEXTFIELD, "1234", 1, 2, 1, 2);
		myStackWidgetTester.addWidgetElement(WidgetElementType.WIDGETELEMENT_FUNNYTEXTFIELD, "1235", 1, 2, 1, 2);
		myStackWidgetTester.addWidgetElement(WidgetElementType.WIDGETELEMENT_FUNNYTEXTFIELD, "1236", 1, 2, 1, 2);
		myStackWidgetTester.addWidgetElement(WidgetElementType.WIDGETELEMENT_FUNNYTEXTFIELD, "1237", 1, 2, 1, 2);
		myStackWidgetTester.addWidgetElement(WidgetElementType.WIDGETELEMENT_FUNNYTEXTFIELD, "1238", 1, 2, 1, 2);
		assertFalse("Return false as there are too many widget elements", myStackWidgetTester.addWidgetElement(WidgetElementType.WIDGETELEMENT_FUNNYTEXTFIELD, "1239", 1, 2, 1, 2));
	}
	
//This section tests deleting widgets//
	
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveWidgetElementIDLengthTooLong(){
		//test *10*
	myStackWidgetTester.removeWidgetElement("12345");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveWidgetElementIDLengthTooShort(){
	//test *11*
	myStackWidgetTester.removeWidgetElement("123");
	}
	
	@Test
	public void testRemoveWidgetElementIDLengthCorrect(){
	//test *12*
		myStackWidgetTester.addWidgetElement(WidgetElementType.WIDGETELEMENT_FUNNYTEXTFIELD, "1234", 1, 2, 1, 2);
		assertTrue("Widget with id 1234 should be removed", myStackWidgetTester.removeWidgetElement("1234"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRemoveEmptyWidgetElementID(){
	//test *13*
		myStackWidgetTester.removeWidgetElement("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveCharactersWidgetElementID(){
	//test *14*
		myStackWidgetTester.removeWidgetElement("abcd");
	}
	
	@Test
	public void testRemoveNonExistingWidgetElementID(){
	//test *15*
		myStackWidgetTester.removeWidgetElement("1234");
		if (!myStackWidgetTester.isWidgetElementInList("1234")){
			assertFalse("Return False as Widget not within the list",myStackWidgetTester.removeWidgetElement("1234"));
		}
	}

	@Test
	public void testRemoveFromEmptyWidgetStack(){
	//test *16*
			assertFalse("Return False as widget stack is empty",myStackWidgetTester.removeWidgetElement("1234"));
	}
	
}
