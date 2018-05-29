
package Data;

import Domain.Person;
import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;


public class XMLPersonManager {
    //variables
    public Document document;
    public Element root;
    public String path;

    public XMLPersonManager(String path) throws JDOMException, IOException {
        this.path = path;
        
        File filePerson = new File(path);
        if (filePerson.exists()) {
            //1. El archivo ya existe, entonces lo cargo en memoria
            
            //toma la estructura de datos y las carga en memoria
            SAXBuilder saxBuilder = new SAXBuilder();
            saxBuilder.setIgnoringElementContentWhitespace(true);
            
            //cargar en memoria
            this.document=saxBuilder.build(this.path);
            this.root = this.document.getRootElement();
            
        }else{
            //2. El archivo no existe, lo creo y lo guardo
            
            //creo el elemento raiz
            this.root = new Element("people");
            
            //creamos el documento
            this.document = new Document(this.root);
            
            //guardar el archivo en disco duro
            storeXML();
        }
    }
    
    private void storeXML() throws FileNotFoundException, IOException{
        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.output(this.document, new PrintWriter(this.path));
    }
    
    //insertar un studiante
    public void insertPerson(Person person) throws IOException{
        addChildren(person);
        //debemos crear elemento por elemento respect al estudiante
        Element ePerson = new Element("person");
        ePerson.setAttribute("identification", person.getIdPerson());
        //nombre
        Element eName = new Element("name");
        eName.addContent(person.getName());
        //lastname
        Element eLastName1 = new Element("lastName1");
        eLastName1.addContent(person.getLastName1());
        //birthDate
        Element eBirthDate = new Element("birthDate");
        eBirthDate.addContent(person.getBirthDate());
        //country
        Element eCountry = new Element("country");
        eCountry.addContent(person.getCountry());
        //idParent
        Element eIdParent = new Element("idParent");
        eIdParent.addContent(person.getIdParent());
        //Children
        Element eChildren = new Element("Children");
        eChildren.addContent(person.getChildren());
        //agregar el elemento student el name y el admissiongrade
        ePerson.addContent(eName);
        ePerson.addContent(eLastName1);
        ePerson.addContent(eBirthDate);
        ePerson.addContent(eCountry);
        ePerson.addContent(eIdParent);
        ePerson.addContent(eChildren);
        //agregamos al root
        this.root.addContent(ePerson);
        //guardar en disco duro
        storeXML();
    }
    
    //leer todos los hijos de la raíz
    public Person[] getAllPeople(){
        int peopleQuantity = this.root.getContentSize();
        //obtenemos una lista con todos los elementos de root
        java.util.List elementList = this.root.getChildren();
        //definir el tamaño del arreglo
        Person[] peopleArray = new Person[peopleQuantity];
        //recorrer la lista para ir insertando en el arreglo
        int count = 0;
        for (Object currentObject: elementList) {
            //casting de object a element
            Element currentElement = (Element)currentObject;
            //crea persona
            Person currentPerson = new Person();
            //id
            currentPerson.setIdPerson(currentElement.getAttributeValue("identification"));
            //name
            currentPerson.setName(currentElement.getChild("name").getValue());
            //lastName
            currentPerson.setLastName1(currentElement.getChild("lastName1").getValue());
            //birthDate
            currentPerson.setBirthDate(currentElement.getChild("birthDate").getValue());
            //country
            currentPerson.setCountry(currentElement.getChild("country").getValue());
            //idParent
            currentPerson.setIdParent(currentElement.getChild("idParent").getValue());
            //children
            currentPerson.setChildren(currentElement.getChild("Children").getValue());
            peopleArray[count++]=currentPerson;
        }
        return peopleArray;
    }
    
    public void deleteStudent(String id) throws IOException{
        int peopleQuantity = this.root.getContentSize();
        java.util.List elementList = this.root.getChildren();
        
        //recorrer la lista para ir insertando en el arreglo
        for (int i = 0; i < peopleQuantity; i++) {
            Element currentElement = (Element) elementList.get(i);
            if (currentElement.getAttributeValue("identification").equals(id)) {
                elementList.remove(i);
            }
        }
        storeXML();
    }
    
    public void addChildren(Person person) throws IOException{
        int peopleQuantity = this.root.getContentSize();
        java.util.List elementList = this.root.getChildren();
        //recorrer la lista para ir insertando en el arreglo
        for (int i = 0; i < peopleQuantity; i++) {
            Element currentElement = (Element) elementList.get(i);
            if (currentElement.getAttributeValue("identification").equals(person.getIdParent())) {
                
                Element ePerson = new Element("person");
                ePerson.setAttribute("identification", currentElement.getAttributeValue("identification"));
                //nombre
                Element eName = new Element("name");
                eName.addContent(currentElement.getChild("name").getValue());
                //lastname
                Element eLastName1 = new Element("lastName1");
                eLastName1.addContent(currentElement.getChild("lastName1").getValue());
                //birthDate
                Element eBirthDate = new Element("birthDate");
                eBirthDate.addContent(currentElement.getChild("birthDate").getValue());
                //country
                Element eCountry = new Element("country");
                eCountry.addContent(currentElement.getChild("country").getValue());
                //idParent
                Element eIdParent = new Element("idParent");
                eIdParent.addContent(currentElement.getChild("idParent").getValue());
                //Children
                Element eChildren = new Element("Children");
                eChildren.addContent(person.getIdPerson());
                //agregar el elemento student el name y el admissiongrade
                ePerson.addContent(eName);
                ePerson.addContent(eLastName1);
                ePerson.addContent(eBirthDate);
                ePerson.addContent(eCountry);
                ePerson.addContent(eIdParent);
                ePerson.addContent(eChildren);
                
                elementList.remove(i);
                
                //agregamos al root
                this.root.addContent(ePerson);
            }
        }
        storeXML();
    }
    
    public void edit(String idPerson, String name, String lastName1, String birthDate, String country) throws IOException{
        int peopleQuantity = this.root.getContentSize();
        java.util.List elementList = this.root.getChildren();
        //recorrer la lista para ir insertando en el arreglo
        for (int i = 0; i < peopleQuantity; i++) {
            Element currentElement = (Element) elementList.get(i);
            if (currentElement.getAttributeValue("identification").equals(idPerson)) {
                
                Element ePerson = new Element("person");
                ePerson.setAttribute("identification", currentElement.getAttributeValue("identification"));
                //nombre
                Element eName = new Element("name");
                eName.addContent(name);
                //lastname
                Element eLastName1 = new Element("lastName1");
                eLastName1.addContent(lastName1);
                //birthDate
                Element eBirthDate = new Element("birthDate");
                eBirthDate.addContent(birthDate);
                //country
                Element eCountry = new Element("country");
                eCountry.addContent(country);
                //idParent
                Element eIdParent = new Element("idParent");
                eIdParent.addContent(currentElement.getChild("idParent").getValue());
                //Children
                Element eChildren = new Element("Children");
                eChildren.addContent(currentElement.getChild("Children").getValue());
                //agregar el elemento student el name y el admissiongrade
                ePerson.addContent(eName);
                ePerson.addContent(eLastName1);
                ePerson.addContent(eBirthDate);
                ePerson.addContent(eCountry);
                ePerson.addContent(eIdParent);
                ePerson.addContent(eChildren);
                
                elementList.remove(i);
                
                //agregamos al root
                this.root.addContent(ePerson);
            }
        }
        storeXML();
    }
}
