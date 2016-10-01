/**
 * 
 */
package com.atom217;

import com.jimmoores.quandl.DataSetRequest;
import com.jimmoores.quandl.QuandlSession;
import com.jimmoores.quandl.TabularResult;

/**
 * @author pushpendra.paliwal
 *
 */
public class QClient {

	/**
	 * 
	 */
	private final static String QUANDL_TOKEN = "DipYgysX2_KDDjqx-BSU";
	public QClient() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hello world");
		QuandlSession session = QuandlSession.create();
		TabularResult tabularResult = session.getDataSet(DataSetRequest.Builder.of("NSE/CNXALPHAINDEX").withMaxRows(4).build());
		System.out.println(tabularResult.toPrettyPrintedString());

	}

}
