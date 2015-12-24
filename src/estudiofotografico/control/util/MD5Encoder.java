package Utilidades;

import java.security.*;
import java.math.*;

/*  
    Java MD5
    OBTIDO EM http://www.devmedia.com.br/md5-java-em-poucas-linhas/17213
    11-06-2013 03:35
    Adaptado por Rogerio Eduardo Pereira
*/

public class MD5Encoder 
{
    public String MD5Encode(String original) throws NoSuchAlgorithmException
    {
        MessageDigest m=MessageDigest.getInstance("MD5");
        m.update(original.getBytes(),0,original.length());
        
        return new BigInteger(1,m.digest()).toString(16); 
    }
    
    public static void main(String args[])
    {
       /*String s="Texto de Exemplo";
       MessageDigest m=MessageDigest.getInstance("MD5");
       m.update(s.getBytes(),0,s.length());
       System.out.println("MD5: "+new BigInteger(1,m.digest()).toString(16));*/
    }
}