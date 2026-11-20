package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Utility {

    // Base Directory Path
    private final String BASE_DIRECTORY;

    // Constructor
    public Utility() {
        BASE_DIRECTORY = loadPath();
    }

    /**
     * Gets the base directory where the semesters are stored.
     *
     * @return The base directory.
     */
    public String getDirectoryPath() {
        return BASE_DIRECTORY;
    }

    /**
     * Gets the base directory path from the assets/path.txt file.
     *
     * @return The base directory path if the file exists, otherwise null.
     */
    private String loadPath() {
        // Load the path from the assets/path.txt file
        InputStream path = getClass().getResourceAsStream("/assets/path.txt");

        // If the file does not exist, return null
        if (path == null) {
            return null;
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(path))) {
            // Read the line from the file and return it
            return reader.readLine();
        } catch (IOException e) {
            // Handle the IO Exception
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Creates a course folder with appropriate sub-folders.
     *
     * @param path the base directory path
     * @param semester the semester name
     * @param course the course name
     * @param withLab whether to create the lab related folders
     * @return true if successful, false otherwise
     */
    public static boolean createCourse(String path, String semester, String course, boolean withLab) {
        File semesterFolder = new File(path, semester);
        File courseFolder = new File(semesterFolder, course);

        if (!semesterFolder.exists()) {
            createSemester(path, semester);
        }

        if (!courseFolder.exists()) {
            courseFolder.mkdirs();
        }

        try {
            if (withLab) {
                // Create Lectures folder
                File lecturesFolder = new File(courseFolder, "Lectures");
                lecturesFolder.mkdirs();

                // Create Assignments and Notes folders inside Lectures
                File assignmentsFolder = new File(lecturesFolder, "Assignments");
                File notesFolder = new File(lecturesFolder, "Notes");

                assignmentsFolder.mkdirs();
                notesFolder.mkdirs();

                // Create Labs folder
                File labsFolder = new File(courseFolder, "Labs");
                labsFolder.mkdirs();

                // Create Assignments, Activities and Project folders inside Labs
                File labAssignmentsFolder = new File(labsFolder, "Assignments");
                File labActivitiesFolder = new File(labsFolder, "Activities");
                File labProjectFolder = new File(labsFolder, "Project");

                labAssignmentsFolder.mkdirs();
                labActivitiesFolder.mkdirs();
                labProjectFolder.mkdirs();

            } else {
                // Create Assignments and Notes folders
                File assignmentFolder = new File(courseFolder, "Assignments");
                File notesFolder = new File(courseFolder, "Notes");

                assignmentFolder.mkdirs();
                notesFolder.mkdirs();
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    /**
     * Creates a new semester folder in the specified path.
     *
     * @param path The path to create the semester folder in.
     * @param semester The name of the semester.
     * @return true if the semester folder was created successfully, false
     * otherwise.
     */
    public static boolean createSemester(String path, String semester) {
        File semesterFile = new File(path, semester);

        // Check if the semester folder already exists
        if (!semesterFile.exists()) {
            // Create the semester folder
            return semesterFile.mkdirs();
        }

        // Semester folder already exists
        return false;
    }

    /**
     * Returns a list of semester names from the specified path.
     *
     * @param path The path to list the semesters from.
     * @return An array of semester names.
     */
    public static String[] listSemesters(String path) {
        File dir = new File(path);
        ArrayList<String> semesters = new ArrayList<>();

        // Get a list of all files and directories in the path
        File[] files = dir.listFiles();

        if (files != null) {
            // Iterate through the list of files and directories
            for (File f : files) {
                // Check if the file is a directory (i.e. a semester)
                if (f.isDirectory()) {
                    // Add the semester name to the list
                    semesters.add(f.getName());
                }
            }
        }

        // Convert the list to an array and return it
        return semesters.toArray(String[]::new);
    }

    /**
     * Deletes a directory recursively.
     *
     * This method deletes all files and subdirectories within the specified
     * directory, then deletes the directory itself.
     *
     * @param dir The directory to be deleted.
     */
    public static void deleteDirectoryRecursively(File dir) {
        // Check if the file is a directory
        if (dir.isDirectory()) {
            // List all files and subdirectories in the directory
            File[] children = dir.listFiles();
            if (children != null) {
                // Recursively delete each child
                for (File child : children) {
                    deleteDirectoryRecursively(child);
                }
            }
        }
        // Delete the directory or file
        dir.delete();
    }
}
