import java.awt.*;
import java.awt.event.*;
import java.util.Map;
import java.util.Set;
import javax.swing.*;
import javax.swing.event.*;

/**
 * The CritterFrame is the top level GUI for the program.  It contains
 * the CritterPanel that renders the world along with buttons and other widgits
 * that allow the user to control stepping as well as get additional
 * info about the current simulation.
 */

public class CritterFrame extends JFrame {
	private static final long serialVersionUID = -4250377699984602669L;
	private CritterModel model;
	private CritterPanel critterPanel;
	private Timer timer;
	private JLabel stepsCountLabel;
	private JLabel[] critterCounts;
	
	/** @return the newly initialized bottom panel of the frame. */ 
	private JPanel initializeBottomPanel() {
		JPanel panel = new JPanel();
		// Timer slider
		JSlider timerSlider = new JSlider(JSlider.HORIZONTAL, 0, 500, 250);
		// N.B. these shouldn't be hard coded, but I don't have a good way
		// to calculate the size of the slider off-hand.
		timerSlider.setPreferredSize(new Dimension(100, 50));
		timerSlider.setMajorTickSpacing(250);
		timerSlider.setMinorTickSpacing(50);
		timerSlider.setSnapToTicks(true);
		timerSlider.setPaintTicks(true);
		timerSlider.setPaintLabels(true);
		timerSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider slider = (JSlider) e.getSource();
				timer.setDelay(slider.getValue());
			}
		});
		panel.add(timerSlider);
		panel.add(Box.createVerticalGlue());
		
		// Continuous step button
		final JButton runButton = new JButton("Run!");
		runButton.setMnemonic('R');
		runButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!timer.isRunning()) {
					timer.start();
					runButton.setText("Stop!");
					runButton.repaint();
				} else {
					timer.stop();
					runButton.setText("Run!");
					runButton.repaint();
				}
			}
		});
		panel.add(runButton);
		panel.add(Box.createVerticalGlue());
		
		// Single step button
		JButton stepButton = new JButton("Step!");
		stepButton.setMnemonic('S');
		stepButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!timer.isRunning()) {
					stepOnce();
				}
			}
		});
		panel.add(stepButton);
		panel.add(Box.createVerticalGlue());
		
		// Step label
		stepsCountLabel = new JLabel("");
		updateStepsLabel();
		panel.add(stepsCountLabel);
		panel.add(Box.createVerticalGlue());
		
		// Repopulate checkbox
		JCheckBox repopCheckbox = new JCheckBox("Repopulate", model.isRepopulatingWorld());
		repopCheckbox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				JCheckBox cb = (JCheckBox) e.getItem();
				model.setRepopulatingWorld(cb.isSelected());
			}
		});
		panel.add(repopCheckbox);
		panel.add(Box.createVerticalGlue());
		
		return panel;
	}
	
	/** @return the newly initialized right panel of the frame */
	private JPanel initializeRightPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		critterCounts = new JLabel[model.getMaxActiveSpecies()];
		for (int i = 0; i < critterCounts.length; i++) {
			critterCounts[i] = new JLabel();
			panel.add(critterCounts[i]);
			panel.add(Box.createVerticalGlue());
		}
		updateCountsLabels();
		return panel;
	}
	
	/**
	 * Constructs a new CritterFrame with the given model.
	 * @param width the width of the panel
	 * @param height the height of the panel
	 * @param model the model this frame renders
	 */
	public CritterFrame(int width, int height, final CritterModel model) {
		setTitle("CSC 207 (Fall 2015) Critter Zoo!");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.model = model;
		this.critterPanel = new CritterPanel(model);
		timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stepOnce();
			}
		});
		timer.setInitialDelay(0);
		
		add(critterPanel, BorderLayout.CENTER);
		add(initializeBottomPanel(), BorderLayout.SOUTH);
		add(initializeRightPanel(), BorderLayout.EAST);
	}
	
	/** Updates the label that displays the number of steps taken so far. */
	public void updateStepsLabel() {
		stepsCountLabel.setText("Steps: " + model.getNumSteps());
		stepsCountLabel.repaint();
	}
	
	/** Updates the label that displays the populations of each species. */
	public void updateCountsLabels() {
		Map<Class<?>, Integer> counts = model.getCritterCounts();
		Set<Class<?>> species = counts.keySet();
		for (int i = 0; i < critterCounts.length; i++) {
			critterCounts[i].setText("");
		}
		int i = 0;
		for (Class<?> type : species) {
			critterCounts[i++].setText(type.getName() + ": " + counts.get(type));
		}
		if (critterCounts.length > 0) {
			critterCounts[0].getParent().repaint();
		}
	}
	
	/** Steps the simulation once and renders the model to the frame. */
	public void stepOnce() {
		model.step();
		updateStepsLabel();
		updateCountsLabels();
		critterPanel.repaint();
	}
}