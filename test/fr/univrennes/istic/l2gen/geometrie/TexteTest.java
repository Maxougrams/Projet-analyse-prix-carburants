package fr.univrennes.istic.l2gen.geometrie;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TexteTest {
	private Texte texte, texteVide;

	@Before
	public void setUP() {
		texte = new Texte(192, 128, 64, "Istic L2GEN");
		texteVide = new Texte();
	}

	@Test
	public void testCentre() {
		assertTrue(texte.centre().equals(new Point(192, 128)));
	}

	@Test
	public void testCentre2() {
		assertTrue(texteVide.centre().equals(new Point(0, 0)));
	}

	@Test
	public void testDeplacer() {
		texte.deplacer(0, 0);
		assertEquals(192, texte.centre().x(), 0.00001);
		assertEquals(128, texte.centre().y(), 0.00001);
	}

	@Test
	public void testDeplacer2() {
		texte.deplacer(10, 50);
		assertEquals(202, texte.centre().x(), 0.00001);
		assertEquals(178, texte.centre().y(), 0.00001);
	}

	@Test
	public void testDeplacer3() {
		texteVide.deplacer(0, 0);
		assertEquals(0, texteVide.centre().x(), 0.00001);
		assertEquals(0, texteVide.centre().y(), 0.00001);
	}

	@Test
	public void testDeplacer4() {
		texteVide.deplacer(10, 50);
		assertEquals(10, texteVide.centre().x(), 0.00001);
		assertEquals(50, texteVide.centre().y(), 0.00001);
	}

	@Test
	public void testDescription() {
		assertEquals("Texte centre=192.0,128.0 taille=64 texte=Istic L2GEN couleur=black et de rotation 0",
				texte.description(0));
	}

	@Test
	public void testDescription2() {
		assertEquals("  Texte centre=0.0,0.0 taille=0 texte= couleur=black et de rotation 0", texteVide.description(1));
	}

	@Test
	public void testDescription3() {
		// test quand changement de couleur
		texte.colorier("red");
		assertEquals("Texte centre=192.0,128.0 taille=64 texte=Istic L2GEN couleur=red et de rotation 0",
				texte.description(0));
	}

	@Test
	public void testDupliquer() {
		Texte texte2 = (Texte) texte.dupliquer();
		assertTrue(texte2.centre().equals(texte.centre()));
		assertEquals(texte.description(0), texte2.description(0));
		assertEquals(texte.enSVG(), texte2.enSVG());
		texte.deplacer(10, 10);
		assertFalse(texte2.centre().equals(texte.centre()));
	}

	@Test
	public void testEnSVG() {
		assertEquals(
				"<text x=\"192.0\" y=\"128.0\" font-size=\"64\" text-anchor=\"middle\" fill=\"black\" stroke=\"black\" transform=\"rotate(0)\">Istic L2GEN</text>",
				texte.enSVG());
	}

	@Test
	public void testEnSVG2() {// avec changement de couleur
		texte.colorier("red");
		assertEquals(
				"<text x=\"192.0\" y=\"128.0\" font-size=\"64\" text-anchor=\"middle\" fill=\"red\" stroke=\"black\" transform=\"rotate(0)\">Istic L2GEN</text>",
				texte.enSVG());
	}

	@Test
	public void testEnSVG3() {
		assertEquals(
				"<text x=\"0.0\" y=\"0.0\" font-size=\"0\" text-anchor=\"middle\" fill=\"black\" stroke=\"black\" transform=\"rotate(0)\"></text>",
				texteVide.enSVG());
	}

	@Test
	public void testHauteur() {
		assertEquals(76.0, texte.hauteur(), 0.00001);
	}

	@Test
	public void alignergauche() {
		texte.aligner(Alignement.GAUCHE, 0.0);
		assertEquals(0, texte.centre().x(), 0.00001);
		assertEquals(128, texte.centre().y(), 0.00001);
	}

	@Test
	public void alignerdroite() {
		texte.aligner(Alignement.DROITE, 0.0);
		assertEquals(0.0, texte.centre().x(), 0.00001);
		assertEquals(128, texte.centre().y(), 0.00001);
	}

	@Test
	public void alignerhaut() {
		texte.aligner(Alignement.HAUT, 0.0);
		assertEquals(192, texte.centre().x(), 0.00001);
		assertEquals(10.0, texte.centre().y(), 0.00001);
	}

	@Test
	public void alignerbas() {
		texte.aligner(Alignement.BAS, 0.0);
		assertEquals(192, texte.centre().x(), 0.00001);
		assertEquals(-10, texte.centre().y(), 0.00001);
	}
}
