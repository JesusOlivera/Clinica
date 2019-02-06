package src.com.certicom.scpf.utils;
import org.jdom2.Content;
import org.jdom2.Document;         // |
import org.jdom2.Element;          // |\ Librer�as
import org.jdom2.JDOMException;    // |/ JDOM
import org.jdom2.input.SAXBuilder; // |

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.certicom.scpf.domain.RespuestaSunat;

public class LeerFileXML {

	public RespuestaSunat leerXml(String ruta, String tipo_comprobate){
		RespuestaSunat respuestaSunat= new RespuestaSunat();
		System.out.println("RUTA:"+ruta);
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File(ruta);
		try {
			Document document =builder.build(xmlFile);
			Element rootNode = document.getRootElement();
			int contador =0;
			int respuesta=0;
			for(Content e : rootNode.getContent()){
				System.out.println("CONTENIDO "+e.toString());
				if(e.toString().contains("DocumentResponse")){
					respuesta=contador;
				}
				contador=contador+1;
			}
//				
//			Element contenidoRespuesta=(Element) rootNode.getContent(9);
//			Element contenidoResponse=(Element) contenidoRespuesta.getContent(0);
//			
//			Element contenidoReferenceID=(Element) contenidoResponse.getContent(0);			
//			Element contenidoResponseCode=(Element) contenidoResponse.getContent(1);
//			Element contenidoDescription=(Element) contenidoResponse.getContent(2); 
////			
//			System.out.println("contenidoReferenceID ===>"+contenidoReferenceID.getValue());
//			System.out.println("contenidoResponseCode ===>"+contenidoResponseCode.getValue());
//			System.out.println("contenidoDescription ===>"+contenidoDescription.getValue());
			
//			int numero_linea=0;
//			if(tipo_comprobate.equalsIgnoreCase("03")){
//				numero_linea=13;
//			}else{
//				if(tipo_comprobate.equalsIgnoreCase("01")){
//					numero_linea=respuesta;
//				}
//				
//			}
			Element contenidoRespuesta=(Element) rootNode.getContent(respuesta);
			Element contenidoResponse=(Element) contenidoRespuesta.getContent(0);
			
			Element contenidoReferenceID=(Element) contenidoResponse.getContent(0);			
			Element contenidoResponseCode=(Element) contenidoResponse.getContent(1);
			Element contenidoDescription=(Element) contenidoResponse.getContent(2); 
			
			System.out.println("contenidoReferenceID ===>"+contenidoReferenceID.getValue());
			System.out.println("contenidoResponseCode ===>"+contenidoResponseCode.getValue());
			System.out.println("contenidoDescription ===>"+contenidoDescription.getValue());
			
			
			
			respuestaSunat.setReferenceID(contenidoReferenceID.getValue());
			respuestaSunat.setResponseCode(Integer.parseInt(contenidoResponseCode.getValue()));
			respuestaSunat.setDescription(contenidoDescription.getValue());
			
			Element contenidoDocumentReference=(Element) contenidoRespuesta.getContent(1);
			Element contenidoID=(Element) contenidoDocumentReference.getContent(0);
			System.out.println("contenidoID ===>"+contenidoID.getValue());
			

			} catch (IOException io) {
			System.out.println(io.getMessage());
			} catch (JDOMException jdomex) {
			System.out.println(jdomex.getMessage());
			}
		return respuestaSunat;
	}
	
}
