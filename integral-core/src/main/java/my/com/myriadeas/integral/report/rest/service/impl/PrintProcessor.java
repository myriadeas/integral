package my.com.myriadeas.integral.report.rest.service.impl;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import my.com.myriadeas.integral.report.exception.FileNotFoundException;
import my.com.myriadeas.integral.report.exception.PrinterNotFoundException;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.pdfbox.pdmodel.PDDocument;

public class PrintProcessor implements Processor {
	public void process(Exchange exchange) throws Exception {
		Map<String, String> msg = new HashMap<String, String>();

		String printerName = exchange.getIn().getHeader("PrinterName",
				String.class);
		PrintService selectedPrinter = null;
		PrintService[] services = PrintServiceLookup.lookupPrintServices(null,
				null);

		for (PrintService printService : services) {

			System.out.println(printService.getName() + " = " + printerName);
			if (printService.getName().equalsIgnoreCase(printerName)) {
				selectedPrinter = printService;
				break;
			}

		}

		if (selectedPrinter == null) {
			throw new PrinterNotFoundException("Printer: " + printerName
					+ " not found");
		}

		PDDocument doc = null;
		try {
			PrinterJob job = PrinterJob.getPrinterJob();
			job.setPrintService(selectedPrinter);
			doc = PDDocument.load(exchange.getIn().getBody(InputStream.class));
			doc.silentPrint(job);

		} catch (PrinterException pe) {
			throw new PrinterException("Printer: " + printerName + " exception: "
					+ pe.toString());
		} catch (IOException ioe) {
			throw new FileNotFoundException("IOException: " + ioe.getMessage());
		} finally {
			if (doc != null) {
				doc.close();
			}
		}

		msg.put("status", "Successful");
		msg.put("message", "Report was sent to printer " + printerName
				+ " successfully.");

		exchange.getIn().setBody(msg);

	}
}