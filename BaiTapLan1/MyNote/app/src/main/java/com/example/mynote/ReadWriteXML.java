package com.example.mynote;


import android.content.Context;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class ReadWriteXML {

    List<Note> noteList;
    File file;
    Context context;
    String xmlFile;

    public ReadWriteXML(String xmlFile, Context context) {
        this.noteList = new ArrayList<Note>();
        this.context = context;
        this.xmlFile = xmlFile;
        file = new File(this.context.getExternalFilesDir("/"), xmlFile);
        read();
    }

    public void write( boolean append) {

        String data = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
        data += "\n<NotePad>";
        try {
            File outputFile = new File(context.getExternalFilesDir("/"), xmlFile);
            FileOutputStream outputStream = new FileOutputStream(outputFile, append);

            for (Note item : noteList) {
                data += "\n<note>" +
                        "\n<title>" + item.getTitle() + "</title>" +
                        "\n<lastUpdate>" + item.getLastUpdate() + "</lastUpdate>" +
                        "\n<content>" + item.getContent() + "</content>" +
                        "\n</note>";
            }

            data += "\n</NotePad>";
            outputStream.write(data.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            Log.e("Read and Write XML file", "Error saving file");
        }
    }

    public void read() {
        try {
            noteList.clear();
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

            factory.setNamespaceAware(false);

            XmlPullParser xpp = factory.newPullParser();

            FileInputStream inputStream = new FileInputStream(file);
            xpp.setInput(inputStream, "UTF_8");

            boolean insideItem = false;

            int eventType = xpp.getEventType();
            Note note = new Note();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {

                    if (xpp.getName().equalsIgnoreCase("note")) {
                        insideItem = true;
                    } else if (xpp.getName().equalsIgnoreCase("title")) {
                        if (insideItem) {
                            note.setTitle(xpp.nextText());

                        }
                    } else if (xpp.getName().equalsIgnoreCase("content")) {
                        if (insideItem) {
                            note.setContent(xpp.nextText());
                        }
                    }
                    else if (xpp.getName().equalsIgnoreCase("lastUpdate")) {
                        if (insideItem) {
                            note.setLastUpdate(xpp.nextText());
                            this.noteList.add(note);
                        }
                    }

                } else if (eventType == XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("note")) {
                    insideItem = false;
                    note = new Note();
                }

                eventType = xpp.next();
            }

        } catch (FileNotFoundException e) {
            Log.e("Read and Write XML file", "Error saving file");
        } catch (XmlPullParserException e) {
            Log.e("Read and Write XML file", "Error parser xml");
        } catch (IOException e) {
            Log.e("Read and Write XML file", "Error input ");
        }
    }

    public List<Note> getNotes() {
        return this.noteList;
    }

    public void editNote(int position,Note note)
    {
        noteList.set(position,note);
    }
    public void addNote(Note note)
    {
        this.noteList.add(note);
    }
    public void deleteNote(int position)
    {
        this.noteList.remove(position);
    }
}
