package tool.xml;

/**
 * Created by yuandengfeng on 2016/11/3.
 */
public class Book {

    public String getBook_id() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBook_name() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String id;
    private String name;

    public Book(String id,String name)
    {
        this.id=id;
        this.name=name;
    }

}
