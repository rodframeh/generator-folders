/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ulasalle.pcc.generator.folders;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;


/**
 *
 * @author Rodrigo Mendoza Melgar
 */
public class Aplicacion
{
    public static void main(String[] args) throws IOException
    {
        ObjectMapper mapper=new ObjectMapper();
       
        Folder[] folders=mapper.readValue(new File("archivo.json"),Folder[].class);
        
        for(int i=0;i<folders.length;i++)
        {
            String cadena=folders[i].getLabel();
            String[] sub=cadena.split(" / ");
            
            String acumulador="";
            for(int j=0;j<sub.length;j++)
            {
                acumulador+=sub[j].trim().replaceAll(" ", "-")+"/";
            }
            cadena=acumulador.replaceAll(",", "").replaceAll("--", "-");
            if(cadena.contains("-(see-also"))
                cadena=cadena.substring(0,cadena.indexOf("-(see-also") )+"/";
            File folder = new File("generado/"+cadena.replace('*', ' ').replaceAll("- ", "").toLowerCase());
            folder.mkdirs();

        }
        
    }
}
