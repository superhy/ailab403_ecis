package com.test.util;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import org.junit.Test;

import com.iiimms.util.HttpClientJsoupDocumentUtil;

public class HttpClientJsoupDocumentUtilTest {

	@Test
	public void testGetDocument() {

		String testUrl = new Scanner(System.in).next();

		String strResult = HttpClientJsoupDocumentUtil.getDocument(testUrl)
				.toString();

		try {
			File fileResult = new File("./file/html/testHtml.txt");
			if (!fileResult.exists()) {
				fileResult.createNewFile();
			}

			FileWriter fw = new FileWriter(fileResult);
			fw.write(strResult);

			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// System.out.println(strResult);
	}
}
