import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.testng.Assert.assertEquals;

public class SimpleTest {

    boolean interactive = false;
    String createFilePath = "CreateFileTest";
    String deleteFilePath = "DeleteFileTest";
    String renameFilePath = "RenameFileTest";
    String renamePathNew = "RenameFileTestNew";

    @Parameters({"interactive"})
    SimpleTest(boolean interactive) {
        this.interactive = interactive;
    }

    @BeforeClass
    public void setUp() {
        // code that will be invoked when this test is instantiated
    }

    @Test(groups = "functest")
    public void testCreateFile() {
        FileManager fileManager = new FileManager(interactive);
        fileManager.createFile(createFilePath);
        assertEquals(true, Files.exists(Paths.get(createFilePath)));
    }

    @Test(groups = "functest")
    public void testDeleteFile() throws IOException {
        FileManager fileManager = new FileManager(interactive);
        fileManager.createFile(deleteFilePath);
        fileManager.deleteFile(deleteFilePath);
        assertEquals(false, Files.exists(Paths.get(deleteFilePath)));
    }

    @Test(groups = "functest")
    public void testRenameFile() throws IOException {
        FileManager fileManager = new FileManager(interactive);
        fileManager.createFile(renameFilePath);
        assertEquals(true, Files.exists(Paths.get(renameFilePath)));
        fileManager.renameFile(renameFilePath, renamePathNew);
        assertEquals(false, Files.exists(Paths.get(renameFilePath)));
        assertEquals(true, Files.exists(Paths.get(renamePathNew)));
    }
}
