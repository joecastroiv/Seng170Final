package WindowBuilder.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Stream;

public class driveErrorsTest {

	public static void main(String[] args) throws IOException {
		
		LoginChecker test = new LoginChecker();
		
		test.isMatchLogin(null);

	}

}
