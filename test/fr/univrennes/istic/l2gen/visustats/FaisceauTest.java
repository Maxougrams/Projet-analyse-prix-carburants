package fr.univrennes.istic.l2gen.visustats;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import fr.univrennes.istic.l2gen.geometrie.IForme;
import fr.univrennes.istic.l2gen.geometrie.Rectangle;
import static org.junit.Assert.assertEquals;

public class FaisceauTest {

    @Test
    public void testCreationFaisceau() {
        Faisceau faisceau = new Faisceau("MonFaisceau", 10, 20, 30);
        assertEquals("MonFaisceau", faisceau.getNom());
        assertEquals(3, faisceau.getListFormes().size());
    }

    @Test
    public void testAgencerVertical() {
        // Créer un groupe de formes
        Faisceau faisceau = new Faisceau("Faisceau", 10, 20, 30);
        // Ajouter des formes au groupe
        faisceau.ajouter(new Rectangle(10.0, 25.0, 18.0, 20.0));
        // Appeler la méthode agencer avec orientation verticale
        faisceau.agencer(25.0, 2400.0, 50.0, 30.0, true);
        // Vérifier que les éléments ont été alignés correctement
        assertEquals(475.0, faisceau.centre().x(), 0.0001);
        assertEquals(1200.0, faisceau.centre().y(), 0.0001);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testAgencerVerticalErreur(){
         // Créer un groupe de formes
         Faisceau faisceau = new Faisceau("Faisceau", 10, 20, 30);
         // Ajouter des formes au groupe
         faisceau.ajouter(new Rectangle(10.0, 25.0, 18.0, 20.0));
         // Appeler la méthode agencer avec orientation verticale
         faisceau.agencer(25.0, -2400.0, 50.0, 30.0, true);
    }

    @Test
    public void testAgencerHorizontal() {
        // Créer un groupe de formes
        Faisceau faisceau = new Faisceau("Faisceau", 10, 20, 30);
        // Ajouter des formes au groupe
        faisceau.ajouter(new Rectangle(10.0, 25.0, 18.0, 20.0));
        // Appeler la méthode agencer avec orientation horizontale
        faisceau.agencer(25.0, 900.0, 50.0, 30.0, false);
        // Vérifier que les éléments ont été alignés correctement
        assertEquals(66.25, faisceau.centre().x(), 0.0001);
        assertEquals(450.0, faisceau.centre().y(), 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAgencerHorizontalErreur(){
        // Créer un groupe de formes
        Faisceau faisceau = new Faisceau("Faisceau", 10, 20, 30);
        // Ajouter des formes au groupe
        faisceau.ajouter(new Rectangle(10.0, 25.0, 18.0, 20.0));
        // Appeler la méthode agencer avec orientation horizontale
        faisceau.agencer(25.0, -900.0, 50.0, 30.0, false);
    }

    @Test
    public void testColorier() {
        Faisceau faisceau = new Faisceau("ColorFaisceau", 10, 20, 30);
        faisceau.colorier("red", "green", "blue");
        List<IForme> formes = faisceau.getListFormes();
        List<Rectangle> barres = new ArrayList<>();
        for (IForme forme : formes) {
            if (forme instanceof Rectangle) {
                Rectangle rectangle = (Rectangle) forme;
                barres.add(rectangle);
            }
        }
        assertEquals("red", barres.get(0).getCouleur());
        assertEquals("green", barres.get(1).getCouleur());
        assertEquals("blue", barres.get(2).getCouleur());
    }

    @Test
    public void testDupliquer() {
        Faisceau original = new Faisceau("OriginalFaisceau", 10, 20, 30);
        Faisceau copie = (Faisceau) original.dupliquer();
        assertEquals(original.getNom(), copie.getNom());
        assertEquals(original.getListFormes().size(), copie.getListFormes().size());
    }
}
