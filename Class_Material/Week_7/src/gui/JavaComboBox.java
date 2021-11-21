/**
 * Source: https://www.softwaretesttips.com/java-swing-tutorial/
 */
package gui;

import javax.swing.*;
public class JavaComboBox {    
JavaComboBox(){   
    JFrame f=new JFrame("Java Swing");   
    String language[]={"C","C++","Python","Java","HTML"};       
    JComboBox cb1=new JComboBox(language);   
    cb1.setBounds(50, 50,90,20);   
    f.add(cb1);       
    f.setLayout(null);   
    f.setSize(500,500);   
    f.setVisible(true);        
}   
public static void main(String[] args) {   
    new JavaComboBox();        
}   
}   