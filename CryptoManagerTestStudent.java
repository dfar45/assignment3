package assignment3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CryptoManagerTestStudent {

	
	@Test
    public void testIsStringInBoundsTrue() {
        assertTrue(CryptoManager.isStringInBounds("EVERYONE"));
    }

    @Test
    public void testIsStringInBoundsFalse() {
        assertFalse(CryptoManager.isStringInBounds("everyone"));
    }
	
	@Test
	void testVigenereEncryption() {
		String text = "MONTGOMERY";
		String key = "KEY";
		String encrypted = CryptoManager.vigenereEncryption(text, key);
		String decrypted = CryptoManager.vigenereDecryption(encrypted, key);
		assertEquals(text, decrypted);
	}
	@Test
	void testPlayfairEncryption() {
		String text = "UNIVERSE";
		String key = "STAR";
		String encrypted = CryptoManager.playfairEncryption(text, key);
		String decrypted = CryptoManager.playfairDecryption(encrypted, key);
		assertEquals(text, decrypted);
	}
	@Test
	void testCaesarEncryption() {
		String text = "SCREENS";
		int key = 4;
		String encrypted = CryptoManager.caesarEncryption(text, key);
		String decrypted = CryptoManager.caesarDecryption(encrypted, key);
		assertEquals(text, decrypted);
	}

}
