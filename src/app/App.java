package app;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class App {

    // Components
    private final GUI GUI;
    private final Listener LISTENER;

    public App() {
        GUI = new GUI();
        LISTENER = new Listener();
        integrate();
    }

    /**
     * Integrate the GUI and LISTENER by adding the necessary action listeners
     * to the components. This method is called in the constructor of this
     * class.
     */
    private void integrate() {
        // Add action listener to the semester combo box to refresh the course list
        JComboBox<String> semesterComboBox = GUI.getSemesterComboBox();
        semesterComboBox.addActionListener(_ -> LISTENER.refreshList(GUI));
        GUI.setSemesterComboBox(semesterComboBox);

        // Add action listener to the create semester button to create a new semester
        JButton createSemesterButton = GUI.getCreateSemesterButton();
        createSemesterButton.addActionListener(_ -> LISTENER.createSemester(GUI));
        GUI.setCreateSemesterButton(createSemesterButton);

        // Add action listener to the create course button to create a new course
        JButton createCourseButton = GUI.getCreateCourseButton();
        createCourseButton.addActionListener(_ -> LISTENER.createCourse(GUI));
        GUI.setCreateCourseButton(createCourseButton);

        // Add action listener to the open folder button to open the selected course folder
        JButton openFolderButton = GUI.getOpenFolderButton();
        openFolderButton.addActionListener(_ -> LISTENER.openFolder(GUI));
        GUI.setOpenFolderButton(openFolderButton);

        // Add action listener to the delete folder button to delete the selected course folder
        JButton deleteFolderButton = GUI.getDeleteFolderButton();
        deleteFolderButton.addActionListener(_ -> LISTENER.deleteFolder(GUI));
        GUI.setDeleteFolderButton(deleteFolderButton);
    }

}
