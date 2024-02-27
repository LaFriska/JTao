package com.friska.jtao.frontend;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

import java.awt.image.BufferedImage;

public class TeXRenderer {

    public static BufferedImage renderImage(String tex){
        TeXFormula formula = new TeXFormula(tex);
        TeXIcon icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 100);
        BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        icon.paintIcon(null, image.getGraphics(), 0, 0);
        return image;
    }

}
