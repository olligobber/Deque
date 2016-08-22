import static org.junit.Assert.*;

import org.junit.Test;

public class ExpandingArrayDequeTest {

	@Test
	public void testEmpty() {
		Deque<Integer> test = new ExpandingArrayDeque<Integer>();
		assertTrue(test.isEmpty());
		assertEquals(0, test.size());
	}
	
	@Test
	public void testLenientRetrievals() {
		Deque<Integer> test = new ExpandingArrayDeque<Integer>();
		
		assertNull(test.peekFirst());
		assertNull(test.peekLast());
		assertNull(test.pollFirst());
		assertNull(test.pollLast());
	}
	
	@Test
	public void testStrictRetrievals() {
		Deque<Integer> test = new ExpandingArrayDeque<Integer>();
		
		try {
			test.getFirst();
			fail("No exception thrown");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalStateException);
		}
		
		try {
			test.getLast();
			fail("No exception thrown");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalStateException);
		}
		
		try {
			test.removeFirst();
			fail("No exception thrown");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalStateException);
		}
		
		try {
			test.removeLast();
			fail("No exception thrown");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalStateException);
		}
	}
	
	@Test
	public void testLenientAddition() {
		Deque<Integer> test = new ExpandingArrayDeque<Integer>(0);
		
		assertFalse(test.offerFirst(1));
		assertFalse(test.offerLast(2));
		
		test = new ExpandingArrayDeque<Integer>(2);
		
		assertTrue(test.offerFirst(1));
		assertTrue(test.offerLast(-1));
		assertFalse(test.offerFirst(0));
		assertFalse(test.offerLast(0));
	}
	
	@Test
	public void testStrictAddition() {
		Deque<Integer> test = new ExpandingArrayDeque<Integer>(0);
		
		try {
			test.addFirst(2);
			fail("No exception thrown");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalStateException);
		}
		
		try {
			test.addLast(1);
			fail("No exception thrown");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalStateException);
		}
		
		test = new ExpandingArrayDeque<Integer>(2);
		
		test.addFirst(-1);
		test.addLast(2);
		
		try {
			test.addFirst(0);
			fail("No exception thrown");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalStateException);
		}
		
		try {
			test.addLast(1);
			fail("No exception thrown");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalStateException);
		}
	}
	
	@Test
	public void largeTest() {
		final int size = 10000000;
		
		Deque<Integer> test = new ExpandingArrayDeque<Integer>();
		
		for (int i=0; i<size; i++) {
			if (!test.offerFirst(i)) {
				break;
			}
		}
		
		assertEquals(new Integer(test.size()-1), test.peekFirst());
		
		if (test.size() != size) {
			System.out.println("Default size: " + test.size());
		}
		
		int i = test.size();
		
		for (; i != 0; i--) {
			test.removeLast();
		}
		
		assertTrue(test.isEmpty());
		
	}
	
	@Test
	public void smallTest() {
		Deque<Integer> test = new ExpandingArrayDeque<Integer>(10);
		test.addLast(2);
		assertTrue(test.offerFirst(-1));
		assertTrue(test.offerLast(11));
		// [-1, 2, 11]
		assertEquals(3, test.size());
		assertFalse(test.isEmpty());
		assertEquals(new Integer(-1), test.getFirst());
		assertEquals(new Integer(11), test.peekLast());
		test.addFirst(-100);
		// [-100, -1, 2, 11]
		assertEquals(4, test.size());
		assertEquals(new Integer(-100), test.removeFirst());
		assertEquals(new Integer(-1), test.pollFirst());
		assertEquals(new Integer(2), test.removeFirst());
		// [11]
		test.addLast(9);
		test.addLast(3);
		assertEquals(3, test.size());
		test.addLast(3);
		// [11, 9, 3, 3]
		assertEquals(new Integer(3), test.removeLast());
		assertEquals(new Integer(3), test.pollLast());
		assertEquals(new Integer(9), test.removeLast());
		assertEquals(new Integer(11), test.removeLast());
		assertNull(test.pollLast());
		assertTrue(test.isEmpty());
		
	}
	
	@Test
	public void testString() {
		Deque<String> test = new ExpandingArrayDeque<String>(3);
		
		test.addFirst("Test");
		test.addFirst("More test");
		test.addLast("This is an extremely long string and eventually it will end ok maybe not... It contains various @$(!| sympols including \" and ' and // and \\\\ and \\* and /* and *\\ and a null char no jk");
		assertEquals("More test", test.removeFirst());
		assertEquals(2, test.size());
		test.removeLast();
		assertEquals("Test", test.removeLast());
	}
	
	@Test
	public void testNull() {
		Deque<Object> test = new ExpandingArrayDeque<Object>(3);
		
		assertTrue(test.offerLast(null));
		assertNull(test.removeFirst());
	}

}
