package main.java.stuff;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MyFileChooser extends JFileChooser {

    /**
     * serial ID
     */
    private static final long serialVersionUID = -5653493046848901035L;

    public MyFileChooser() {
	super(new File("."));
	setAcceptAllFileFilterUsed(false);
	setMultiSelectionEnabled(false);
	setFileFilter(new FileNameExtensionFilter("*.txt", "txt"));

    }

}
