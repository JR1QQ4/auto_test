package base;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.XMLReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.util.List;
import java.util.Properties;

public class MyLog {
    private static Logger logger = Logger.getLogger(MyLog.class);

    public static void main(String[] args) {
        logger.info("info----------------");
        logger.debug("info----------------");
        logger.error("info----------------");
    }
}

/**
 * 异常处理
 */
class MyException {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String path = System.getProperty("user.dir") + "/src/main/resources/log4j.properties";

        Properties p = new Properties();
        File f = new File("main/resources/log4j.properties");
        InputStream fi = null;

//        try {
//            fi = new FileInputStream(f);
//        } catch (FileNotFoundException e) {
//            // e.printStackTrace();
//            System.out.println(e.toString());
//        }
//        try {
//            p.load(fi);  // 找不到文件出现 ”运行时异常“ NullPointerException
//
//        } catch (IOException e) {
//            System.out.println(e.toString());
//        }

        try {
            fi = new FileInputStream(f);
            p.load(fi);  // 找不到文件出现 ”运行时异常“ NullPointerException
        } catch (FileNotFoundException e) {
            System.out.println("\033[1;43;30m" + "文件没有找到");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("\033[45m" + "文件读取错误");
        } finally {
            System.out.println("\033[1;93;45m" + "finally" + "\033[m");
        }

        System.out.println(p.getProperty("log4j.rootLogger"));
    }
}

/**
 * 文件处理
 */
class MyIO {
    public static void main(String[] args) throws IOException {
//        String dirPath = "./a/b";
//        String filePath = "/c.txt";
//        File d = new File(dirPath);
//        File f = new File(dirPath + filePath  );
//
//        System.out.println("After dir : " + d.exists());
//        System.out.println("After file: " + f.exists());
//
//        d.mkdirs();
//        f.createNewFile();
//
//        System.out.println("Before dir : " + d.exists());
//        System.out.println("Before file: " + f.exists());

        String oldPic = "C:\\Users\\东夋壬\\Desktop\\middle.jpg";
        String newPic = "C:\\Users\\东夋壬\\Desktop\\middle1.jpg";

        FileInputStream f = new FileInputStream(new File(oldPic));
        FileOutputStream o = new FileOutputStream(new File(newPic));
        int size = 0;
        while ((size = f.read()) != -1) {
            o.write(size);
        }
        f.close();
        o.close();
    }
}

/**
 * 处理 XML
 */
class HandleXML {
    public static void main(String[] args) throws Exception {
        String filePath = "src/main/resources/cd_catalog.xml";

        // 第一种方式：使用 DOM 方式处理 XML
        // HandleXML.handleXMLWithDOM(filePath);

        // 第二种方式，使用 dom4j 方式处理 XML
        HandleXML.handleXMLWithDom4j(filePath);
    }

    /**
     * 使用 Element 方式
     */
    public static void element(NodeList list) {
        for (int i = 0; i < list.getLength(); i++) {
            Element element = (Element) list.item(i);
            NodeList childNodes = element.getChildNodes();
            for (int j = 0; j < childNodes.getLength(); j++) {
                if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
                    // 获取节点
                    System.out.print(childNodes.item(j).getNodeName() + ":");
                    // 获取节点值
                    System.out.println(childNodes.item(j).getFirstChild().getNodeValue());
                }
            }
            System.out.println("----------------------------------------------");
        }
    }

    public static void node(NodeList list) {
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            NodeList childNodes = node.getChildNodes();
            for (int j = 0; j < childNodes.getLength(); j++) {
                if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
                    System.out.print(childNodes.item(j).getNodeName() + ":");
                    System.out.println(childNodes.item(j).getFirstChild().getNodeValue());
                }
            }
            System.out.println("-----------------------------------------------");
        }
    }

    public static void handleXMLWithDOM(String filePath){
        // 1.创建DocumentBuilderFactory对象
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // 2.创建DocumentBuilder对象
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document d = builder.parse(filePath);
            NodeList sList = d.getElementsByTagName("CD");
            element(sList);
            // node(sList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void handleXMLWithDom4j(String filePath) throws DocumentException {
        // 1. 创建解析器 SaxReader 对象
        SAXReader reader = new SAXReader();
        // 2. 获取 Document 对象
        org.dom4j.Document document = reader.read(new File(filePath));
        // 3. 获取根元素
        org.dom4j.Element root = document.getRootElement();
        // 4. 获取根元素下的子元素
        List<org.dom4j.Element> elments = root.elements("CD");
        for (org.dom4j.Element ele :
                elments) {
            org.dom4j.Element titleElement = ele.element("TITLE");
            org.dom4j.Element artistElement = ele.element("ARTIST");
            org.dom4j.Element countryElement = ele.element("COUNTRY");
            org.dom4j.Element companyElement = ele.element("COMPANY");
            org.dom4j.Element priceElement = ele.element("PRICE");
            org.dom4j.Element yearElement = ele.element("YEAR");

            // System.out.println(ele.elements());

            System.out.println("TITLE: " + ele.elementText("TITLE"));

            System.out.println("TITLE: " + titleElement.getText());
            System.out.println("ARTIST: " + artistElement.getText());
            System.out.println("COUNTRY: " + countryElement.getText());
            System.out.println("COMPANY: " + companyElement.getText());
            System.out.println("PRICE: " + priceElement.getText());
            System.out.println("YEAR: " + yearElement.getText());

            System.out.println("--------------------------------------");
        }
    }
}




