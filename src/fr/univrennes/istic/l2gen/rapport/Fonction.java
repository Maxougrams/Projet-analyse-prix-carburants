package fr.univrennes.istic.l2gen.rapport;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Fonction {
    /**
     * Crée un fichier HTML contenant le contenu SVG spécifié.
     * 
     * @param listeSvgContent La liste du contenu des fichiers svg à ajouter dans la
     *                        page web.
     * @param htmlTitle       Le titre de la page HTML.
     * @param fileName        Le nom du fichier HTML à créer.
     */
    public static void createHTMLFile(ArrayList<String> listeSvgContent, String htmlTitle, String fileName) {
        String svgContent = "";

        svgContent = String.join("\n", listeSvgContent);

        String htmlTemplate = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "<meta charset=\"UTF-8\">\n" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "<title>" + htmlTitle + "</title>\n" +
                "<style>\n" +
                "  body {\n" +
                "    background-color: #f0f0f0;\n" +
                "    font-family: 'Times New Roman', serif;\n" + // Changer la police ici
                "  }\n" +
                "  svg {\n" +
                "    display: block;\n" +
                "    margin: 0;\n" +
                "    width:  80%;\n" +
                "    background-color: white; \n" +
                "  }\n" +
                "  h2 {\n" +
                "    position: down;\n" +
                "    bottom: 150px;\n" +
                "    left: 10px;\n" +
                "    animation: blink 7s linear infinite;\n" +
                "  }\n" +
                "  @keyframes blink {\n" +
                "    0% { opacity: 1; }\n" +
                "    50% { opacity: 0; }\n" +
                "    100% { opacity: 1; }\n" +
                "  }\n" +
                "  form {\n" +
                "    display: flex;\n" +
                "    flex-direction: column;\n" +
                "    width: 200px;\n" +
                "    position: down; \n" +
                "    bottom: 10px; \n" +
                "    left: 10px; \n" +
                "  }\n" +
                "  input, textarea {\n" +
                "    margin-bottom: 10px;\n" +
                "  }\n" +
                "  img {\n" +
                "    position: fixed;\n" +
                "    bottom: 0;\n" +
                "    right: 0;\n" +
                "    width: 130px;\n" +
                "    height: 130px;\n" +
                "  }\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +

                svgContent +

                "<h2>Laissez Nous votre avis</h2>\n" +
                "<form>\n" +
                "  <label for=\"name\">Mail:</label><br>\n" +
                "  <input type=\"text\" id=\"name\" name=\"name\"><br>\n" +
                "  <label for=\"review\">Votre avis</label><br>\n" +
                "  <textarea id=\"review\" name=\"review\"></textarea><br>\n" +
                "  <input type=\"submit\" value=\"Soumettre\">\n" +
                "</form>\n" +
                "<img src='https://www.gifsanimes.com/data/media/1423/pompe-a-essence-image-animee-0005.gif'>\n" +
                "</body>\n" +
                "</html>";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName + ".html"))) {
            writer.write(htmlTemplate);
            System.out.println("Fichier HTML créé avec succès !");
        } catch (IOException e) {
            System.err.println("Erreur lors de la création du fichier HTML : " + e.getMessage());
        }
    }
}
