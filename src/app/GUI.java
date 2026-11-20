package app;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import utils.Utility;

public class GUI extends JFrame {

    // Main Panel
    private JPanel mainPanel;

    // Directory Details
    private JLabel directoryLabel;

    // Semester Details and Buttons
    private JComboBox<String> semesterComboBox;
    private JTextField semesterTextField;
    private JButton createSemesterButton;

    // Course Details and Buttons
    private JTextField courseCodeField, courseTitleField;
    private JRadioButton withLabRadio, withoutLabRadio;
    private ButtonGroup courseTypeGroup;
    private JButton createCourseButton;

    // List of Courses in a Semester
    private DefaultListModel<String> courseListModel;
    private JList<String> courseList;
    private JScrollPane courseListScrollPane;

    // Folder Management Buttons
    private JButton openFolderButton, deleteFolderButton;

    // Utility Class
    private final Utility UTILITY;

    // Constructor
    public GUI() {
        super("Directory Management System");
        setSize(new Dimension(800, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        UTILITY = new Utility();
        initComponents();
    }

    /**
     * Initializes the components of the GUI
     */
    private void initComponents() {

        // Main Panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Directory Details
        directoryLabel = new JLabel("Current Directory: " + UTILITY.getDirectoryPath());

        // Directory Panel
        JPanel directoryPanel = new JPanel(new BorderLayout(5, 5));
        directoryPanel.add(directoryLabel, BorderLayout.CENTER);
        directoryPanel.setMaximumSize(new Dimension(600, 30));

        // Semester Details
        semesterComboBox = new JComboBox<>(Utility.listSemesters(UTILITY.getDirectoryPath()));
        semesterComboBox.setPreferredSize(new Dimension(200, 25));
        semesterTextField = new JTextField(10);
        createSemesterButton = new JButton("Create");

        // Semester Panel
        JPanel semesterPanel = new JPanel();
        semesterPanel.add(new JLabel("Semester:"));
        semesterPanel.add(semesterComboBox);
        semesterPanel.add(new JLabel("or New:"));
        semesterPanel.add(semesterTextField);
        semesterPanel.add(createSemesterButton);

        // Course Details
        courseCodeField = new JTextField(10);
        courseTitleField = new JTextField(20);

        // Course Panel
        JPanel coursePanel = new JPanel();
        coursePanel.add(new JLabel("Course Code:"));
        coursePanel.add(courseCodeField);
        coursePanel.add(new JLabel("Course Title:"));
        coursePanel.add(courseTitleField);

        // Course Buttons
        withLabRadio = new JRadioButton("With Lab");
        withoutLabRadio = new JRadioButton("Without Lab");
        courseTypeGroup = new ButtonGroup();
        courseTypeGroup.add(withLabRadio);
        courseTypeGroup.add(withoutLabRadio);
        createCourseButton = new JButton("Create Course");

        // Course Buttons Panel
        JPanel courseButtonsPanel = new JPanel();
        courseButtonsPanel.add(new JLabel("Course Type:"));
        courseButtonsPanel.add(withLabRadio);
        courseButtonsPanel.add(withoutLabRadio);
        courseButtonsPanel.add(createCourseButton);

        // Course List
        courseListModel = new DefaultListModel<>();
        courseList = new JList<>(courseListModel);
        courseListScrollPane = new JScrollPane(courseList);
        courseListScrollPane.setPreferredSize(new Dimension(400, 200));
        courseListScrollPane.setBorder(BorderFactory.createTitledBorder("Existing Courses:"));

        // Folder Management Buttons
        openFolderButton = new JButton("Open Folder");
        deleteFolderButton = new JButton("Delete Folder");

        // Folder Button Panel
        JPanel folderButtonPanel = new JPanel();
        folderButtonPanel.add(openFolderButton);
        folderButtonPanel.add(deleteFolderButton);

        // Add Components to Main Panel
        mainPanel.add(directoryPanel);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(semesterPanel);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(coursePanel);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(courseButtonsPanel);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(courseListScrollPane);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(folderButtonPanel);
        this.setContentPane(mainPanel);
        this.pack();
    }

    /**
     * Gets the semester combo box.
     *
     * @return the semester combo box.
     */
    public JComboBox<String> getSemesterComboBox() {
        return semesterComboBox;
    }

    /**
     * Sets the semester combo box.
     *
     * @param semesterComboBox the semester combo box.
     */
    public void setSemesterComboBox(JComboBox<String> semesterComboBox) {
        this.semesterComboBox = semesterComboBox;
    }

    /**
     * Gets the create semester button.
     *
     * @return the create semester button.
     */
    public JButton getCreateSemesterButton() {
        return createSemesterButton;
    }

    /**
     * Sets the create semester button.
     *
     * @param createSemesterButton the create semester button.
     */
    public void setCreateSemesterButton(JButton createSemesterButton) {
        this.createSemesterButton = createSemesterButton;
    }

    /**
     * Gets the create course button.
     *
     * @return the create course button.
     */
    public JButton getCreateCourseButton() {
        return createCourseButton;
    }

    /**
     * Sets the create course button.
     *
     * @param createCourseButton the create course button.
     */
    public void setCreateCourseButton(JButton createCourseButton) {
        this.createCourseButton = createCourseButton;
    }

    /**
     * Gets the open folder button.
     *
     * @return the open folder button.
     */
    public JButton getOpenFolderButton() {
        return openFolderButton;
    }

    /**
     * Sets the open folder button.
     *
     * @param openFolderButton the open folder button.
     */
    public void setOpenFolderButton(JButton openFolderButton) {
        this.openFolderButton = openFolderButton;
    }

    /**
     * Gets the delete folder button.
     *
     * @return the delete folder button.
     */
    public JButton getDeleteFolderButton() {
        return deleteFolderButton;
    }

    /**
     * Sets the delete folder button.
     *
     * @param deleteFolderButton the delete folder button.
     */
    public void setDeleteFolderButton(JButton deleteFolderButton) {
        this.deleteFolderButton = deleteFolderButton;
    }

    public JLabel getDirectoryLabel() {
        return directoryLabel;
        /**
         * Returns the JLabel component that displays the current directory.
         *
         * @return the directory label JLabel component
         */
    }

    /**
     * Returns the JList component containing the course list.
     *
     * @return the course list JList component
     */
    public JList<String> getCourseList() {
        return courseList;
    }

    /**
     * Gets the default list model for the course list.
     *
     * @return the default list model for the course list.
     */
    public DefaultListModel<String> getCourseListModel() {
        return courseListModel;
    }

    /**
     * Returns the text field for the semester name.
     *
     * @return the JTextField for the semester name
     */
    public JTextField getSemesterTextField() {
        return semesterTextField;
    }

    /**
     * Returns the text field for the course code.
     *
     * @return the JTextField for the course code
     */
    public JTextField getCourseCodeField() {
        return courseCodeField;
    }

    /**
     * Returns the text field for the course title.
     *
     * @return the JTextField for the course title
     */
    public JTextField getCourseTitleField() {
        return courseTitleField;
    }

    /**
     * Returns the radio button indicating whether the course includes a lab.
     *
     * @return the JRadioButton for "With Lab"
     */
    public JRadioButton getWithLabRadio() {
        return withLabRadio;
    }

}
