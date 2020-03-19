/**
 * @author Marco Ramirez 19588
 * @author Edman 
 * @authot Oscar De Leon
 * @version 5.2
 * 
 */
//Algoritmos y estructura de datos seccion 20
//Proyecto, entrega final
//Lisp interprete en java 
//Marco Ramirez
//Edman
//Oscar De Leon

import java.util.Stack;
import java.math.BigInteger;

class Sexp { // clase S-expressions

   public Sexp hd = null, tl = null;
   public boolean at, nmb, err = false;
   public String pname = null;
   public BigInteger nval = null;
   public Stack vstk = null;
   private StringBuffer str = null; 
   /**
    * 
    * @param h, head
    * @param t, title
    */
   public Sexp(Sexp h, Sexp t) { // creacion del primer metodo
      at = false;
      nmb = false;
      hd = h; tl = t; 
      pname = new String("");
      nval = BigInteger.valueOf(0);
      vstk = null;
   } // fin
   /**
    * 
    * @param s, texto a evaluar
    */
   public Sexp(String s) { // creacion del segundo metodo
      at = true;
      nmb = false;
      pname = new String(s);
      nval = BigInteger.valueOf(0);
      hd = this; // hd & tl of atom is atom
      tl = this;
      vstk = new Stack();
      vstk.push(this); 
   } //fin
   /**
    * 
    * @param n convierte a numero 
    */
   public Sexp(BigInteger n) { //creacion del tercer metodo
      at = true;
      nmb = true;
      nval = n;
      pname = n.toString();
      hd = this; // hd & tl of atom is atom
      tl = this;
      vstk = new Stack();
      vstk.push(this); // no cambia 
   } // fin
   /**
    * 
    * @return regresa title y head
    */
   public Sexp two() { return this.tl.hd; }
   /**
    * 
    * @return, regresa title y head
    */
   public Sexp three() { return this.tl.tl.hd; }
   public Sexp four() { return this.tl.tl.tl.hd; }
   /**
    * 
    * @return, regrese title y head
    */
   public boolean bad() { // prueba la formula
      if (at) return pname.equals(")");
      return hd.bad() || tl.bad();
   } // fin 
   /**
    * 
    * @return el texto convetido
    */
   public String toS() { //Convierte S-expresion a String
      str = new StringBuffer();
      toS2(this);
      String str2 = str.toString();
      str = null;
      return str2;
   } // toS
   /**
    * 
    * @param x, siendo esta la expresion
    */
   private void toS2(Sexp x) { //muestra
      if (x.at && !x.pname.equals("")) { // pname "" is nil !
         str.append(x.pname); 
         return;
      }
      str.append('(');
      while (!x.at) {
         toS2(x.hd);
         x = x.tl;
         if (!x.at) 
         str.append(' ');
      }
      str.append(')');
   } // toS2 

} // fin clase
