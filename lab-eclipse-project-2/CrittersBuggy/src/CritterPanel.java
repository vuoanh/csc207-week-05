import java.awt.*;
import javax.swing.*;

/**
 * The CritterPanel is our "view" of the model.  Here we render the model
 * onto a JPanel which is "blank slate" Java Swing GUI component.
 */
public class CritterPanel extends JPanel {
	private static final long serialVersionUID = -6411955578595419157L;
	public static final int FONT_SIZE = 12;
	public static final Font FONT = new Font("Monospace", Font.BOLD, FONT_SIZE + 4);
	private static final Color BACKGROUND_COLOR = new Color(220, 255, 220);
	
	private CritterModel model;
	
	/**
	 * Constructs a new CritterPanel tied to the given model.
	 * @param model the model this panel renders
	 */
	public CritterPanel(CritterModel model) {
		this.model = model;
		setFont(FONT);
		setBackground(BACKGROUND_COLOR);
		setPreferredSize(new Dimension(FONT_SIZE * model.getWidth() + 1,
		                               FONT_SIZE * model.getHeight() + FONT_SIZE / 2));
	}
	
	/**
	 * Renders the model to the panel each time the panel is repainted.
	 * @param g the graphics component to draw on
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < model.getWidth(); i++) {
			for (int j = 0; j < model.getHeight(); j++) {
				String text = model.getGlyphAt(i, j);
				Color color = model.getColorAt(i, j);
				if (!text.equals(" ")) {
					drawWithShadow(g, i * FONT_SIZE + 10, j * FONT_SIZE + 20, text, color);
				}
			}
		}
	}
	
	/**
	 * Draws the given text to the graphics context with a little drop shadow.
	 * @param g the graphics context to draw on
	 * @param x the x-coordinate to render the text
	 * @param y the y-coordinate to render the text
	 * @param text the text to render
	 * @param color the color with which to render this text
	 */
	private void drawWithShadow(Graphics g, int x, int y, String text, Color color) {
		g.setColor(Color.BLACK);
		g.drawString(text, x+1, y+1);
		g.setColor(color);
		g.drawString(text, x, y);
	}
}