package app;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import utils.Utility;

public class Listener implements ActionListener {

    private final Utility UTILITY;

    // Constructor
    public Listener() {
        UTILITY = new Utility();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Unimplemented Method
    }

    /**
     * Refresh the course list by clearing the model and adding new courses from
     * the selected semester.
     *
     * @param gui the GUI object
     */
    public void refreshList(GUI gui) {
        // Clear the model
        gui.getCourseListModel().clear();
        // Get the selected semester
        String semester = (String) gui.getSemesterComboBox().getSelectedItem();
        // No semester is selected, do nothing
        if (semester == null) {
            return;
        }
        // Get the semester folder
        File semesterFolder = new File(UTILITY.getDirectoryPath(), semester);
        // List all the courses in the semester folder
        File[] courses = semesterFolder.listFiles(File::isDirectory);
        if (courses != null) {
            // Add each course to the model
            for (File course : courses) {
                gui.getCourseListModel().addElement(course.getName());
            }
        }
    }

    /**
     * Create a new semester by adding it to the semester combo box.
     *
     * @param gui the GUI object
     */
    public void createSemester(GUI gui) {
        String semester = gui.getSemesterTextField().getText().trim();
        if (!semester.isEmpty()) {
            boolean created = Utility.createSemester(UTILITY.getDirectoryPath(), semester);
            // Semester created successfully
            if (created) {
                JOptionPane.showMessageDialog(gui, "Semester created successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                // Clear the semester combo box and add new semesters
                gui.getSemesterComboBox().removeAllItems();
                String[] semesters = Utility.listSemesters(UTILITY.getDirectoryPath());
                // Add new semesters to the combo box
                for (String s : semesters) {
                    gui.getSemesterComboBox().addItem(s);
                }
                // Clear the semester text field
                gui.getSemesterTextField().setText("");
            } else {
                // Semester already exists
                JOptionPane.showMessageDialog(gui, "Semester already exists", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Semester name is empty
            JOptionPane.showMessageDialog(gui, "Please enter a semester name.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Create a new course and add it to the course list.
     *
     * @param gui the GUI object
     */
    public void createCourse(GUI gui) {
        String semester = (String) gui.getSemesterComboBox().getSelectedItem();
        String courseCode = gui.getCourseCodeField().getText().trim();
        String courseTitle = gui.getCourseTitleField().getText().trim();
        boolean withLab = gui.getWithLabRadio().isSelected();

        if (semester == null || semester.isEmpty()) {
            // Semester combo box is empty
            JOptionPane.showMessageDialog(gui, "Please select a semester.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (courseCode.isEmpty() || courseTitle.isEmpty()) {
            // Course code or title is empty
            JOptionPane.showMessageDialog(gui, "Please enter a course code and title.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String course = courseCode + " - " + courseTitle;
        boolean created = Utility.createCourse(UTILITY.getDirectoryPath(), semester, course, withLab);

        if (created) {
            // Course created successfully
            refreshList(gui);
            JOptionPane.showMessageDialog(gui, "Course created successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            gui.getCourseCodeField().setText("");
            gui.getCourseTitleField().setText("");
        } else {
            // Error creating course
            JOptionPane.showMessageDialog(gui, "Error creating course.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * Open the course folder using the default file manager.
     *
     * @param gui the GUI object
     */
    public void openFolder(GUI gui) {
        String semester = (String) gui.getSemesterComboBox().getSelectedItem();
        String course = gui.getCourseList().getSelectedValue();

        if (semester == null || course == null) {
            JOptionPane.showMessageDialog(gui, "Please select a semester and course to open.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        File courseFolder = new File(UTILITY.getDirectoryPath(), semester + "/" + course);
        try {
            Desktop.getDesktop().open(courseFolder);
        } catch (IOException e) {
            // Error opening course folder
            JOptionPane.showMessageDialog(gui, "Error opening course folder: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Delete a course folder using the default file manager.
     *
     * @param gui the GUI object
     */
    public void deleteFolder(GUI gui) {
        String semester = (String) gui.getSemesterComboBox().getSelectedItem();
        String course = gui.getCourseList().getSelectedValue();

        if (semester == null || course == null) {
            // Semester or course is empty
            JOptionPane.showMessageDialog(gui, "Please select a semester and course to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Confirm with the user that they want to delete the course
        int confirm = JOptionPane.showConfirmDialog(gui, "Are you sure you want to delete this course?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            File courseFolder = new File(UTILITY.getDirectoryPath(), semester + "/" + course);
            Utility.deleteDirectoryRecursively(courseFolder);
            refreshList(gui);
            // Course deleted successfully
            JOptionPane.showMessageDialog(gui, "Course deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
