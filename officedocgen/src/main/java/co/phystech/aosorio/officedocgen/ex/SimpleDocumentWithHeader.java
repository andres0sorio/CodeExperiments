package co.phystech.aosorio.officedocgen.ex;

/* 
====================================================================
Licensed to the Apache Software Foundation (ASF) under one or more
contributor license agreements.  See the NOTICE file distributed with
this work for additional information regarding copyright ownership.
The ASF licenses this file to You under the Apache License, Version 2.0
(the "License"); you may not use this file except in compliance with
the License.  You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==================================================================== 
*/

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;

/**
 * 
 * @author Richard Ngo
 *
 */
public class SimpleDocumentWithHeader {

	private static XWPFParagraph[] pars;

	public static void generate() throws IOException {

		XWPFDocument doc = new XWPFDocument();

		//... Body
		XWPFParagraph p = doc.createParagraph();

		XWPFRun r = p.createRun();
		r.setText("Some Text");
		r.setBold(true);
		r.addCarriageReturn();
		r = p.createRun();
		r.setText("Goodbye");
		r.addCarriageReturn();
		
		r = p.createRun();
		String imgFile="JD.png";
		InputStream myImage =  new FileInputStream(imgFile);
		try {
			r.addPicture(myImage, XWPFDocument.PICTURE_TYPE_PNG, imgFile, Units.toEMU(50), Units.toEMU(50));
		} catch (InvalidFormatException e1) {
			e1.printStackTrace();
		}
		myImage.close();
		
		//. Header
		//. https://stackoverflow.com/questions/43044114/how-can-i-add-image-in-paragraph-run-using-poi-word
		//.. create header-footer
		XWPFHeaderFooterPolicy headerFooterPolicy = doc.getHeaderFooterPolicy();
		if (headerFooterPolicy == null) headerFooterPolicy = doc.createHeaderFooterPolicy();

		// create header start
		XWPFHeader header = headerFooterPolicy.createHeader(XWPFHeaderFooterPolicy.DEFAULT);
		XWPFParagraph paragraph = doc.createParagraph();
		paragraph = header.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.LEFT);

		XWPFRun run = paragraph.createRun();  
		run.setText("The Header:");

		InputStream a = new FileInputStream("JD.png");
		try {
			paragraph.createRun().addPicture(a, XWPFDocument.PICTURE_TYPE_PNG, imgFile, Units.toEMU(20), Units.toEMU(20));
		} catch (InvalidFormatException e2) {
			e2.printStackTrace();
		}
		a.close();
				
		
		//.. Footer
		
		CTP ctP = CTP.Factory.newInstance();
		CTText t = ctP.addNewR().addNewT();
		t.setStringValue("My Footer");
		pars = new XWPFParagraph[1];
		pars[0] = new XWPFParagraph(ctP, doc);
		XWPFHeaderFooterPolicy hfPolicy = doc.createHeaderFooterPolicy();
		hfPolicy.createFooter(XWPFHeaderFooterPolicy.DEFAULT, pars);

		try {
			OutputStream os = new FileOutputStream(new File("header.docx"));
			doc.write(os);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}