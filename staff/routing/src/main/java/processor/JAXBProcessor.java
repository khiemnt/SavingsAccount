package processor;

import org.apache.camel.Exchange;
import org.apache.camel.component.cxf.CxfPayload;
import org.apache.cxf.binding.soap.SoapHeader;
import org.springframework.beans.factory.InitializingBean;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: wadeb
 * Date: 9/5/11
 * Time: 6:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class JAXBProcessor implements InitializingBean
{
    JAXBContext jaxbContext;
    Unmarshaller unmarshaller;
    Marshaller marshaller;

    /**
     * Process a Camel exchange with a CXF payload by unmarshalling it using JAXB.
     * Note that there must be only one element in the CXF body.
     *
     * @param exchange
     * @throws javax.xml.bind.JAXBException
     */
    public synchronized void unmarshall(Exchange exchange) throws JAXBException
    {

        CxfPayload<SoapHeader> requestPayload = exchange.getIn().getBody(CxfPayload.class);

        List<Element> inElements = requestPayload.getBody();
        if (inElements.size() == 1)
        {
            JAXBContext jaxbContext = JAXBContext.newInstance("com.discorp.staff.xml");
            unmarshaller = jaxbContext.createUnmarshaller();
            Object object = unmarshaller.unmarshal(inElements.get(0));
            exchange.getIn().setBody(object);
        }
    }

    /**
     * Marshalls an object into an appropriate XML representation for CXF
     *
     * @param exchange
     * @throws Exception
     */
    public synchronized void marshall(Exchange exchange) throws Exception
    {

        jaxbContext = JAXBContext.newInstance("com.discorp.staff.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.newDocument();
        marshaller.marshal(exchange.getIn().getBody(), doc);
        List outputElement = new ArrayList();
        outputElement.add(doc.getDocumentElement());
        CxfPayload<SoapHeader> responsePayload = new CxfPayload<SoapHeader>(null, outputElement);
        exchange.getIn().setBody(responsePayload);
    }

    public void afterPropertiesSet() throws Exception
    {
        jaxbContext = JAXBContext.newInstance("com.discorp.staff.xml");
        unmarshaller = jaxbContext.createUnmarshaller();
        marshaller = jaxbContext.createMarshaller();
    }
}
