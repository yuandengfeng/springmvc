package tool.xml;

import java.io.FileOutputStream;
import java.io.IOException;


import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;


/**
 * Created by yuandengfeng on 2016/11/3.
 */
public class CreateXml {


        Book[] books = new Book[]
                {
                        new Book("1","唐诗三百首"),
                        new Book("2","Think in Java"),
                        new Book("3","神雕侠侣"),
                        new Book("4","葵花宝典")
                };

        public void BuildXMLDoc() throws IOException, JDOMException {
            // 创建根节点 并设置它的属性 ;
            Element root = new Element("books").setAttribute("count", "4");
            Element version = new Element("version").setText("2");
            Element author = new Element("author").setAttribute("name","yuandengfeng");
            Element sex = new Element("sex").setText("男");
            Element age = new Element("age").setText("二十一");
            Element addr = new Element("addr").setText("中国");
            author.addContent(sex);
            author.addContent(age);
            author.addContent(addr);
            root.addContent(version);
            root.addContent(author);
            for (int i = 0; i < books.length; i++) {
                // 创建节点 book;
                Element elements = new Element("book");
                // 给 book 节点添加子节点并赋值；
                elements.addContent(new Element("id").setText(books[i].getBook_id()));
                elements.addContent(new Element("name").setText(books[i].getBook_name()));
                root.addContent(elements);
            }
            // 使xml文件 缩进效果
            Format format = Format.getPrettyFormat();
            format.setEncoding("GBK");
            XMLOutputter XMLOut = new XMLOutputter(format);

            // 将根节点添加到文档中；
            Document Doc = new Document(root);
            XMLOut.output(Doc, new FileOutputStream("G:\\坤腾\\超汇VIPLog接口\\cc\\sss.xml"));

        }




        public static void main(String[] args) {
            try {
                CreateXml j2x = new CreateXml();
                System.out.println("正在生成 books.xml 文件...");
                j2x.BuildXMLDoc();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("G:\\坤腾\\超汇VIPLog接口\\cc\\sss.xml 文件已生成");
        }




}
