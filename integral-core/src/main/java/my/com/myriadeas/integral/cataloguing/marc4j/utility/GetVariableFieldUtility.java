package my.com.myriadeas.integral.cataloguing.marc4j.utility;

import java.io.ByteArrayInputStream;
import java.util.Iterator;

import org.marc4j.MarcException;
import org.marc4j.MarcPermissiveStreamReader;
import org.marc4j.MarcReader;
import org.marc4j.marc.ControlField;
import org.marc4j.marc.DataField;
import org.marc4j.marc.Record;
import org.marc4j.marc.Subfield;
import org.marc4j.marc.VariableField;
import org.marc4j.marc.impl.Verifier;

public class GetVariableFieldUtility {
	
	public String getString(DataField dataField) {
        StringBuffer sb = new StringBuffer();

        if (dataField != null) {
	        Iterator<?> i = dataField.getSubfields().iterator();
	        while (i.hasNext()) {
	            Subfield sf = (Subfield) i.next();
	            //sb.append(sf.toString());
	            sb.append(sf.getData());
	        }
        }
        
        return sb.toString();
	}
	
	public String getStringWithSubfields(DataField dataField) {
        StringBuffer sb = new StringBuffer();

        if (dataField != null) {
	        Iterator<?> i = dataField.getSubfields().iterator();
	        while (i.hasNext()) {
	            Subfield sf = (Subfield) i.next();
	            //sb.append(sf.toString());
	            sb.append("|" + sf.getCode() + sf.getData());
	        }
        }
        
        return sb.toString();
	}
	
	public String getBibData(VariableField field, boolean withSubfields) {
		String bibData = "";
		if (!Verifier.isControlField(field.getTag())) {
			DataField dataField = (DataField) field;
			if (withSubfields){
				bibData = getStringWithSubfields(dataField);
			}else{
				bibData = getString(dataField);
			}
		}else{
			ControlField controlField = (ControlField) field;
			bibData = controlField.getData();
		}
		return bibData;
	}
	
	public String getBibData(VariableField field) {
		return getBibData(field, false);
	}
	
	protected Record toRecord(String marcData) {
		Record record = null;

		if (marcData != null) {
			ByteArrayInputStream inputStream = new ByteArrayInputStream(marcData.getBytes());
	
			boolean permissive = true;
	
			boolean convertToUTF8 = true;
			
			try {
				MarcReader reader = new MarcPermissiveStreamReader(inputStream, permissive, convertToUTF8);
		
				while(reader.hasNext()) {
					record = reader.next();
				}
			} catch(MarcException e) {
				e.printStackTrace(System.err);
			}
		}
		
		return record;
	}
}
