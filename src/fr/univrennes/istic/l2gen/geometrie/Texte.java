package fr.univrennes.istic.l2gen.geometrie;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Represents a text shape that implements the {@link IForme} interface.
 */
public class Texte implements IForme {
	private String couleur, texte;
	private double x;
	private double y;
	private double hauteur;
	private double largeur;
	private int taille;
	private int angle;

	// Getters and Setters

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public String getTexte() {
		return texte;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getHauteur() {
		return hauteur;
	}

	public void setHauteur(double hauteur) {
		this.hauteur = hauteur;
	}

	public double getLargeur() {
		return largeur;
	}

	public void setLargeur(double largeur) {
		this.largeur = largeur;
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}

	// Bloc d'initialisation
	{
		couleur = "black";
		hauteur = 20.0;
		largeur = 0.0;
	}

	@Override
	public IForme tourner(int angle) {
		if (angle < 0) {
			throw new IllegalArgumentException("L'angle ne peut pas être négatif.");
		}
		this.angle = angle;
		return this;
	}

	@Override
	public IForme aligner(Alignement alignement, double cible) {
		if (cible < 0) {
			throw new IllegalArgumentException("La cible ne peut pas être négative.");
		}
		switch (alignement) {
			case HAUT:
				y = cible + hauteur / 2;
				break;
			case BAS:
				y = cible - hauteur / 2;
				break;
			case DROITE:
				x = cible + largeur / 2;
				break;
			case GAUCHE:
				x = cible - largeur / 2;
				break;
		}
		return this;
	}

	/**
	 * @param x      coordonnée x du texte
	 * @param y      coordonnée y du texte
	 * @param taille la taille du texte
	 * @param texte  la string affichée
	 */
	public Texte(double x, double y, int taille, String texte) {
		if (x < 0 || y < 0 || taille < 0) {
			throw new IllegalArgumentException("x, y et taille doivent être positifs.");
		}
		this.x = x;
		this.y = y;
		this.taille = taille;
		this.texte = texte;
	}

	public Texte() {
		this(0, 0, 0, "");
	}

	@Override
	public Point centre() {
		return new Point(x + largeur() / 2, y + hauteur() / 2);
	}

	public void setCentre(Point centre) {
		this.x = centre.x();
		this.y = centre.y();
	}

	@Override
	public double hauteur() {
		// Créer une police avec la taille spécifiée
		Font font = new Font("Arial", Font.PLAIN, taille);

		// Créer une image tampon pour obtenir les métriques de la police
		BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		Graphics g = img.getGraphics();
		g.setFont(font);

		// Obtenir les métriques de la police
		FontMetrics fm = g.getFontMetrics();

		// Calculer la largeur du texte
		int hauteurTexte = fm.getHeight();

		return hauteurTexte;
	}

	@Override
	public double largeur() {
		// Créer une police avec la taille spécifiée
		Font font = new Font("Arial", Font.PLAIN, taille);

		// Créer une image tampon pour obtenir les métriques de la police
		BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		Graphics g = img.getGraphics();
		g.setFont(font);

		// Obtenir les métriques de la police
		FontMetrics fm = g.getFontMetrics();

		// Calculer la largeur du texte
		int largeurTexte = fm.stringWidth(texte);

		return largeurTexte + 2; // +2 car marge d'erreur car imprécie0
	}

	@Override
	public IForme deplacer(double dx, double dy) {
		// Logique pour déplacer le texte
		x += dx;
		y += dy;
		if (x < 0 || y < 0) {
			throw new IllegalStateException("x et y doivent être positifs.");
		}
		return this; // Retourne la référence à la forme modifiée
	}

	@Override
	public IForme dupliquer() {
		// Crée une nouvelle instance de la classe avec les mêmes propriétés
		Texte nouvelleForme = new Texte();
		nouvelleForme.couleur = this.couleur;
		nouvelleForme.x = this.x;
		nouvelleForme.y = this.y;
		nouvelleForme.hauteur = this.hauteur;
		nouvelleForme.largeur = this.largeur;
		nouvelleForme.taille = this.taille;
		nouvelleForme.texte = this.texte;
		return nouvelleForme;
	}

	@Override
	public String description(int indentation) {
		if (indentation < 0) {
			throw new IllegalArgumentException("L'indentation ne doit pas être inférieure à 0.");
		} else {// Génère une description avec un certain niveau d'indentation
			String sb = "";
			for (int i = 0; i < indentation; i++) {
				sb += "  ";
			}
			sb += "Texte centre=" + x + "," + y + " taille=" + taille + " texte=" + texte + " couleur=" + couleur
					+ " et de rotation " + angle;
			return sb.toString();
		}
	}

	@Override
	public String enSVG() {
		// Génère la représentation SVG du texte avec les dimensions mises à jour
		return "<text x=\"" + x + "\" y=\"" + y + "\" font-size=\"" + taille + "\" text-anchor=\"middle\" fill=\""
				+ couleur + "\" stroke=\"black\" transform=\"rotate(" + angle + ")\">" + texte + "</text>";
	}

	@Override
	public IForme colorier(String... couleurs) {
		couleur = couleurs[0];
		return this;
	}

	/**
	 * Ré-ajuste la hauteur et la largeur du texte.
	 *
	 * @param hauteur La hauteur de redimmensionement
	 * @param largeur La largeur de redimmensionement
	 * @throws IllegalArgumentException si la hauteur ou largeur de red. est égale à
	 *                                  0 ou moins.
	 * @return Une référence à l'instance du texte, pour permettre les
	 *         opérations en chaîne. La hauteur du texte va être multipliée par
	 *         celle de redimmensionnement,
	 *         pareil pour la largeur.
	 */
	@Override
	public IForme redimmensioner(double h, double l) {
		if (h < 0 || l < 0) {
			throw new IllegalArgumentException("Hauteur et Largeur doivent être positifs.");
		}
		hauteur = h;
		largeur = l;
		return this;
	}

	/**
	 * Crée un fichier SVG représentant le rectangle.
	 */
	@Override
	public void createSvgFile() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.svg"))) {
			writer.write("<svg xmlns=\"http://www.w3.org/2000/svg\">");
			writer.write(enSVG() + "\n");
			writer.write("</svg>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
