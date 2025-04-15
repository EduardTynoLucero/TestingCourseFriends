package testingWeb.models;

//@autor Bryan Lucero

public class TheWebElement {
    private String id;
    private String name;
    private String type;
    private String text;
    private String xpath;


    public TheWebElement(){

    }

    public TheWebElement( String id, String name,  String type, String text, String xpath){
        this.id = id;
        this.name = name;
        this.type = type;
        this.text = text;
        this.xpath = xpath;
    }

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }

    public String getText(){
        return text;
    }

    public String getXpath(){
        return xpath;
    }

    
}
