package com.example.personxmlinformation;

import android.content.Context;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class PeopleXMLData {

    private  Person [] data;
    private Context context;

    public PeopleXMLData(Context c){
        this.context = c;

        //get an inputstream to xml
        InputStream stream = this.context.getResources().openRawResource(R.raw.people);


        DocumentBuilder builder = null;
        Document xml = null;
        //parse xml to a document
        try{
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            xml = builder.parse(stream);
        }
        catch(Exception e){}

        //slice document into 5 Nodelists for name,address etc
        NodeList nameList = xml.getElementsByTagName("name");
        NodeList addList = xml.getElementsByTagName("address");
        NodeList phoneList = xml.getElementsByTagName("phone");
        NodeList imageList = xml.getElementsByTagName("image");
        NodeList urlList = xml.getElementsByTagName("url");

        //traverse the list to make data
        data = new Person[nameList.getLength()];

        for(int i= 0; i<data.length; i++){
            String name = nameList.item(i).getFirstChild().getNodeValue();
            String address = addList.item(i).getFirstChild().getNodeValue();
            String phone = phoneList.item(i).getFirstChild().getNodeValue();
            String image = imageList.item(i).getFirstChild().getNodeValue();
            String url = urlList.item(i).getFirstChild().getNodeValue();

            data[i] =new Person(name, address, phone, image, url);
        }


    }

    public Person getPerson(int i){return  data[i];}
    public int getLength(){return data.length;}

    public String[] getNames(){
        //construct the string[]

        String [] names= new String[data.length];
        for (int i = 0; i < data.length; i++){
            names[i] = data[i].getName();
        }
        return names;
    }
}
