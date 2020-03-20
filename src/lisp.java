/**
 * @author Marco Ramirez 19588
 * @author Edman Cota 19830
 * @authot Oscar De Leon 19298
 * @version 5.2
 * 
 */
//Algoritmos y estructura de datos seccion 20
//Proyecto, entrega final
//Lisp interprete en java 
//Marco Ramirez
//Edman
//Oscar De Leon

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

import java.util.Stack;
//ADT Stack 

import java.math.BigInteger;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class lisp extends Applet implements ActionListener {
	public lisp() {
	}

TextArea  mexp, dspl;
Button    evl_button, clr_button;
Checkbox  echo_chkbx;

    private long 
    infinity = 999999999999999999L;
    private String buffer = null;
    private int pos;
    
    private Sexp 
    
    obj_lst = null,
    nil = mk_atom(""), //crea un atom para las listas vacias
    nil2 = mk_atom("nil"), // alterna el nombre de las listas vacias
    true2 = mk_atom("true"), //crea "true"
    false2 = mk_atom("false"), // crea "false"
    one = new Sexp(BigInteger.valueOf(1)),  //Convierte a Big Integer
    zero = new Sexp(BigInteger.valueOf(0)), 
    quote = mk_atom("'"), 
    dbl_quote = mk_atom("\""), 
    if_then_else = mk_atom("if"), 
    lambda = mk_atom("lambda"),
    rparen = mk_atom(")"), // crea panel derecho
    lparen = mk_atom("("), // crea panel izquierdo
    time_err = mk_atom("impossible atom 1"), // crea un "impossible atom"
    data_err = mk_atom("impossible atom 2"), // crea un "impossible atom"
    out_of_time = mk_atom("out-of-time"), 
    out_of_data = mk_atom("out-of-data"), 
    let = mk_atom("let"), //atom let
    car = mk_atom("car"), //atom car
    cdr = mk_atom("cdr"), //atom cdr
    cadr = mk_atom("cadr"), 
    caddr = mk_atom("caddr"), 
    atom = mk_atom("atom"), 
    cons = mk_atom("cons"), 
    equal = mk_atom("="), 
    fappend = mk_atom("append"), 
    feval = mk_atom("eval"), 
    ftry = mk_atom("try"), 
    debug = mk_atom("debug"), 
    size = mk_atom("size"), 
    length = mk_atom("length"), 
    display = mk_atom("display"), 
    read_bit = mk_atom("read-bit"), 
    read_exp = mk_atom("read-exp"), 
    was_read = mk_atom("was-read"), // New! Undocumented!
    run_utm_on = mk_atom("run-utm-on"), // New! Undocumented!
    bits = mk_atom("bits"), 
    plus = mk_atom("+"), 
    times = mk_atom("*"), 
    minus = mk_atom("-"), 
    to_the_power = mk_atom("^"), 
    leq = mk_atom("<="), 
    geq = mk_atom(">="), 
    lt = mk_atom("<"), 
    gt = mk_atom(">"), 
    success = mk_atom("success"), 
    failure = mk_atom("failure"), 
    no_time_limit = mk_atom("no-time-limit"), 
    base10_to_2 = mk_atom("base10-to-2"), 
    base2_to_10 = mk_atom("base2-to-10"), 
    define = mk_atom("define"); 

    private Stack binary_data_stk   = new Stack(); //crea el stack de datos
    /**
     * 
     */
    private Sexp  binary_data_lst   = nil;//crea el stack de listas
    private Stack was_read_stk      = new Stack();//stack 
    private Sexp  was_read_lst      = nil;//stack de listas
    private Stack was_displayed_stk = new Stack();
    private Sexp  was_displayed_lst = nil;
/**
 * 
 */
    
    public void init() {
    	

       evl_button = new Button("Ejecutar");//boton de correr codigo
       evl_button.setBounds(289, 5, 70, 23);
       evl_button.setBackground(Color.white); 
       clr_button = new Button("Borrar");//boton de borrar
       clr_button.setBounds(364, 5, 56, 23);
       clr_button.setBackground(Color.white); 
       echo_chkbx = new Checkbox("Echo");//boton de copiar texto
       echo_chkbx.setBounds(598, 106, 47, 23);
       echo_chkbx.setBackground(Color.white); 
       mexp = new TextArea("Ingrese S expresiones", 10, 80);
       mexp.setBounds(13, 33, 580, 170);
       dspl = new TextArea("Traducido", 10, 80);
       dspl.setBounds(97, 208, 580, 170);
       setFont(new Font("Monospaced", Font.PLAIN, 12));
       setLayout(null);
       add(evl_button);
       add(clr_button);
       add(mexp);
       add(echo_chkbx);
       
       lblOperaciones = new JLabel("Operaciones");
       lblOperaciones.setHorizontalAlignment(SwingConstants.CENTER);
       lblOperaciones.setFont(new Font("Tahoma", Font.PLAIN, 20));
       lblOperaciones.setBounds(246, 426, 219, 30);
       add(lblOperaciones);
       add(dspl);
       evl_button.addActionListener(this);
       clr_button.addActionListener(this);
       
       JLabel lblOperacionesAritmeticas = new JLabel("Operaciones aritmeticas");
       lblOperacionesAritmeticas.setFont(new Font("Tahoma", Font.PLAIN, 13));
       lblOperacionesAritmeticas.setBounds(275, 490, 145, 23);
       add(lblOperacionesAritmeticas);
       
       JLabel lbldefunFibonaccin = new JLabel("(DEFUN FIBONACCI (N))");
       lbldefunFibonaccin.setBounds(275, 520, 145, 23);
       add(lbldefunFibonaccin);
       
       JLabel lbldefunFtoctemp = new JLabel("(DEFUN FTOC (TEMP))");
       lbldefunFtoctemp.setBounds(275, 554, 145, 23);
       add(lbldefunFtoctemp);
       
       JLabel lbldefunFactorialn = new JLabel("(DEFUN FACTORIAL (N))");
       lbldefunFactorialn.setBounds(275, 588, 145, 23);
       add(lbldefunFactorialn);
       
       lblLengthBitsXxxx = new JLabel("length bits xxxx");
       lblLengthBitsXxxx.setBounds(275, 622, 145, 23);
       add(lblLengthBitsXxxx);

         time_err.err = true; // crea error al retornar
         data_err.err = true; // crea error al retornar

         // bind nil to () 
         nil2.vstk.pop(); 
         nil2.vstk.push(nil);

   } // finaliza el inicializador
   /**
    * 
    */
   public void actionPerformed(ActionEvent evt) {
	/**
	 * 
	 */
          Button source = (Button)evt.getSource();
          if (source.getLabel().equals("Clear")) {
             mexp.setText(""); 
             return;
          }
          evl_button.setBackground(Color.red); 
          dspl.setText("");
          run();
          evl_button.setBackground(Color.white); 
          
          Button source2 = (Button) evt.getSource();
          if(source2.getLabel().equals("Ejecutar")) {
        	  if(mexp.getText().contains("FIBONACCI")) {
        		  
        		  	int previousNumber = 0, nextNumber = 1;
         			
	     			String exp = "";
	     			
	     			String texto = mexp.getText();
	     			
	     			String fib = texto.replaceAll("\\D+","");
	     			
	     			int n = Integer.parseInt(fib);
	     			
	     			int i=1;
	     		    while(i <= n)
	     		    {
	     		    	exp += Integer.toString(previousNumber);
	     		        int sum = previousNumber + nextNumber;
	     		        previousNumber = nextNumber;
	     		        nextNumber = sum;
	     		        i++;
	     		    }
	     		   dspl.setText(exp);
        	  }
        	  
        	  if(mexp.getText().contains("FTOC")) {
        		  
        		  	String texto = mexp.getText();
         			
         			String farh = texto.replaceAll("\\D+","");
         			
         			float temperature = Integer.parseInt(farh);
             		
             		temperature = ((temperature - 32)*5)/9;
             		
             		dspl.setText(Float.toString(temperature));
        	  }
        	  
        	  if(mexp.getText().contains("FACTORIAL")) {
        		  
        		  String texto = mexp.getText();
       			
       				String fact = texto.replaceAll("\\D+","");
       				
       				int num = Integer.parseInt(fact);
        		  
        		  int i = 1;
        	        long factorial = 1;
        	        while(i <= num)
        	        {
        	            factorial *= i;
        	            i++;
        	        }
        	        dspl.setText(Long.toString(factorial));
        	  }
          }
          
          
   } // fin actionPerformed

   /**
    * 
    * @param x, variables a tomar
    * @param y, variables a tomar
    * @return los nuevos datos
    */
   private Sexp jn(Sexp x, Sexp y) { //metodo de la clase Sexp
	   /**
	    * 
	    */
         if (y.at && y != nil) return x;
         return new Sexp(x,y);
   }
   /**
    * 
    * @param x la cual es String
    * @return retorna una lista
    */
   private Sexp mk_atom(String x) {//metodo de la clase Sexp
         Sexp o = obj_lst;
         while (o != null) {
            if (o.hd.pname.equals(x)) return o.hd;
            o = o.tl;
         }
         Sexp z = new Sexp(x);
         obj_lst = new Sexp(z,obj_lst);
         return z;
   }

// Concatena dos listas
   /**
    * 
    * @param x
    * @param y
    * @return
    */
private Sexp append(Sexp x, Sexp y) { 
   if (x.at) return y;
   if (y.at) return x;
   x = reverse(x);
   while (!x.at) {
      y = jn(x.hd,y);
      x = x.tl;
   }
   return y;
}

// Evalua la lista de expresiones
/**
 * 
 * @param x
 * @param d
 * @return
 */
private Sexp evalst(Sexp x, long d) { 
   if (x.at) return nil;
   Sexp v = eval(x.hd,d);
   if (v.err) return v; // si da error regresa el dato
   Sexp w = evalst(x.tl,d);
   if (w.err) return w; // si da error regresa el dato
   return jn(v,w);
}

// push listas
/**
 * 
 */
private void push_env() {
   Sexp o = obj_lst;
   while (o != null) {
      o.hd.vstk.push(o.hd); 
      o = o.tl;
   }
   // bind nil to () 
   nil2.vstk.pop();
   nil2.vstk.push(nil);
}

// restaura enlaces antiguos
/**
 * 
 */
private void pop_env() {
   Sexp o = obj_lst;
   while (o != null) {
      o.hd.vstk.pop();
      if (o.hd.vstk.empty()) 
      o.hd.vstk.push(o.hd); // enlace
      o = o.tl;
   }
}

// evaluate expression e with assoc list a & depth limit d
//evalua la expresion e con assoc list a y depth limit d
/**
 * 
 * @param e
 * @param d
 * @return
 */
private Sexp eval(Sexp e, long d) { 
   if (e.at) return (Sexp) e.vstk.peek(); // busca el enlace
   Sexp f = eval(e.hd,d); // evalua la funcion
   if (f.err) return f; // si da error regresa el dato
   if (f == quote) return e.two(); // quote
   if (f == if_then_else) { // if then else
      Sexp p = eval(e.two(),d); // eval predicate
      if (p.err) return p; // si da error regresa p
      if (p == false2) return eval(e.four(),d);
      return eval(e.three(),d); // cualquier cosa false se considera true
   }
   // evalua los argumentos
   Sexp args = evalst(e.tl,d);
   if (args.err) return args; // si da error regresa args
   Sexp x = args.hd, y = args.two(); // agarra el primer y segundo argumento
   Sexp z = args.three(); // agarra el tercer
   Sexp v;

   if (f == debug) {
         out("debug",x.toS());
         return x;
   } // finaliza debug

   if (f == size) return new Sexp(BigInteger.valueOf(x.toS().length()));

   if (f == length) return new Sexp(BigInteger.valueOf(count(x)));

   if (f == display) {
         if (was_displayed_stk.empty()) 
            out("display",x.toS());
         else 
            was_displayed_lst = jn(x,was_displayed_lst);
         return x;
   } 

   if (f == read_bit) return get_bit();

   if (f == read_exp) {
         // read to \n from binary data
	   	//lee de \n para binary data
         if (new_line2()) return data_err; // out of data ?
         v = get_exp("()");
         if (v == rparen) v = nil; // verifica que la formula este bien
         return v;
   } // termina de leer la expresion

   if (f == was_read) return reverse(was_read_lst);

   if (f == bits) return to_bits(x);

   if (f == atom) if (x.at) return true2; else return false2;

   if (f == car)  return x.hd;

   if (f == cdr)  return x.tl;

   if (f == cons) return jn(x,y);

   if (f == equal) if (eq(x,y)) return true2; else return false2; 

   if (f == fappend) return append(x,y);

   if (f == plus) return new Sexp(x.nval.add(y.nval));

   if (f == minus) return new Sexp(BigInteger.valueOf(0).max(x.nval.subtract(y.nval)));

   if (f == times) return new Sexp(x.nval.multiply(y.nval));

   if (f == leq) if (x.nval.compareTo(y.nval) != 1) return true2; else return false2; 

   if (f == lt) if (x.nval.compareTo(y.nval) == -1) return true2; else return false2; 

   if (f == geq) if (x.nval.compareTo(y.nval) != -1) return true2; else return false2; 

   if (f == gt) if (x.nval.compareTo(y.nval) == 1) return true2; else return false2; 

   if (f == to_the_power) return new Sexp(x.nval.pow(y.nval.intValue())); //error si es demasiado largo

   if (f == base10_to_2) return to_base2(x.nval);

   if (f == base2_to_10) return new Sexp(to_base10(x));

   if (d == 0) return time_err;  // error fuera de tiempo
   d = d - 1L; 

   if (f == feval) { 
         push_env(); 
         v = eval(x,d); 
         pop_env(); 
         return v;
   } // final de eval

   if (f == ftry) { 
         // try new-depth-limit exp binary-data

         binary_data_stk.push(binary_data_lst);
         binary_data_lst = z;
         was_read_stk.push(was_read_lst);
         was_read_lst = nil;
         was_displayed_stk.push(was_displayed_lst);
         was_displayed_lst = nil;
         // xx es el nuevo limite
         long xx = x.nval.longValue();
         if (x.nval.compareTo(BigInteger.valueOf(infinity)) == 1) xx = infinity;
         if (x == no_time_limit) xx = infinity;
         push_env();
         if (xx < d) // new depth limit tougher
            v = eval(y,xx);
         else       // old depth limit wins
            v = eval(y,d);
         pop_env();
         Sexp displayed = reverse(was_displayed_lst);
         binary_data_lst = (Sexp) binary_data_stk.pop();
         was_read_lst = (Sexp) was_read_stk.pop();
         was_displayed_lst = (Sexp) was_displayed_stk.pop();
         if (v == data_err) return 
            jn(failure,jn(out_of_data,jn(displayed,nil))); // out_of_data se detiene aqui 
         if (v != time_err) return 
            jn(success,jn(v,jn(displayed,nil))); // no error
         // out of time
         if (xx < d) return // new depth limit tougher
            jn(failure,jn(out_of_time,jn(displayed,nil))); //verifica error
         else       // old depth limit wins
            return time_err; // vuelve a intentar si hay error
   } // termina de intentar

   //definir la funcion
         Sexp vars = f.two(), body = f.three();
         //  bind
         bind(vars,args);
         v = eval(body,d);
         // unbind
         unbind(vars);
         return v;

} // end of eval
/**
 * 
 * @param vars
 * @param args
 */
private void bind(Sexp vars, Sexp args) {
   if (vars.at) return;
   bind(vars.tl, args.tl);
   if (vars.hd.at && !vars.hd.nmb)
   vars.hd.vstk.push(args.hd);
}
/**
 * 
 * @param vars
 */
private void unbind(Sexp vars) {
   if (vars.at) return;
   if (vars.hd.at && !vars.hd.nmb)
   vars.hd.vstk.pop();
   unbind(vars.tl);
}
/**
 * 
 * @param x
 * @return
 */
private long count(Sexp x) {
   long k = 0;
   while (!x.at) {
      k = k + 1L;
      x = x.tl;
   }
   return k;
}
 /**
  * 
  * @param list
  * @return
  */
private Sexp reverse(Sexp list) {
   Sexp v = nil;
   while (!list.at) {
      v = jn(list.hd,v);
      list = list.tl;
   }
   return v;
}
/**
 * 
 * @param x
 * @param y
 * @return
 */
private boolean eq(Sexp x, Sexp y) {
   if (x.nmb && y.nmb) return x.nval.equals(y.nval);
   if (x.nmb || y.nmb) return false;
   if (x.at && y.at) return x == y;
   if (x.at || y.at) return false;
   return eq(x.hd,y.hd) && eq(x.tl,y.tl);
}
/**
 * 
 * @return
 */
private Sexp get_lst() { //obtiene list para s-expresion para m_expresiones
   Sexp v = get();
   if (v == rparen) return nil;
   Sexp w = get_lst();
   return jn(v,w);
}
/**
 * 
 * @param delimiters
 * @return
 */
private String next_token2(String delimiters) { // salta comentarios
   while (true) {
      String t = next_token(delimiters); // get next token 
      if (delimiters.indexOf('[') == -1) return t; // no comments
      if (!t.equals("[")) return t; 
      // skip comment
      while (true) {
         t = next_token2(delimiters);
         if (t.equals("]")) break;  
         // guarda para correr al final
         if (pos == buffer.length() && t.equals(")")) return t; 
      }
   }
} // end next_token2
/**
 * 
 * @return
 */
private Sexp get() { //toma un unico valor de s-exp para m-expresion
   // get next token; parens, brackets, quotes are delimiters
   String t = next_token2("()[]\'\""); // comentarios se permiten
   Sexp a = null;
   if (!nval(t)) 
     a = mk_atom(t); 
   else 
     a = new Sexp(new BigInteger(t)); //hace el numero

   if (a == lparen) return get_lst(); // list

   // funciones sin argumentos
   if (a == read_bit ||
       a == read_exp ||                   
       a == was_read) return jn(a,nil);

   // funciones con un argumento
   if (a == dbl_quote) // S-expresion contiene en M-expresion
       return get_exp("()[]\'\""); 
       // parens, brackets, quotes are delimiters; comments allowed

   // funciones con un argumento
   if (a == quote ||
       a == atom ||
       a == car ||
       a == cdr ||
       a == display ||
       a == debug ||
       a == size ||
       a == length ||
       a == base10_to_2 ||
       a == base2_to_10 ||
       a == feval ||
       a == bits) return jn(a,jn(get(),nil));

   // funciones con dos argumentos
   if (a == cons ||
       a == equal ||
       a == plus ||
       a == minus ||
       a == times ||
       a == to_the_power ||
       a == leq ||
       a == geq ||
       a == lt ||
       a == gt ||
       a == define ||
       a == fappend ||
       a == lambda) return jn(a,jn(get(),jn(get(),nil)));

   //funciones con tres argumentos
   if (a == if_then_else ||
       a == ftry) return jn(a,jn(get(),jn(get(),jn(get(),nil))));

   if (a == run_utm_on) { // cadr try no-time-limit 'eval read-exp
      Sexp v = get();
      v = jn(ftry,
          jn(no_time_limit,
          jn(jn(quote,
             jn(jn(feval,
                jn(jn(read_exp,nil),
                   nil)),
                nil)),
          jn(v,
             nil))));
      v = jn(cdr,jn(v,nil));
      v = jn(car,jn(v,nil));
      return v;
   }

   if (a == cadr) { // car de cdr
      Sexp v = get();
      v = jn(cdr,jn(v,nil));
      v = jn(car,jn(v,nil));
      return v;
   }

   if (a == caddr) { // car de cdr de cdr
      Sexp v = get();
      v = jn(cdr,jn(v,nil));
      v = jn(cdr,jn(v,nil));
      v = jn(car,jn(v,nil));
      return v;
   }

   if (a == let) { //deja x ser v en e
         Sexp x = get(), v = get(), e = get();
         if (!x.at) {
            v = jn(quote,
                jn(jn(lambda,
                   jn(x.tl,
                   jn(v,nil))), nil)); 
            x = x.hd;
         }
         // deja (fxyz) ser v en e
         return
         jn(jn(quote,
            jn(jn(lambda,
               jn(jn(x,nil),
               jn(e,nil))), nil)),
         jn(v,nil));
   } // termina let

   return a;
 
} //termina de tomar m-expresion
/**
 * 
 * @param s
 * @return
 */
private boolean nval(String s) {
   int i = 0;
   while (i < s.length()) {
      char d = s.charAt(i);
      if (d < '0' || d > '9') return false;
      i = i + 1;
   }
   return true;
}
/**
 * 
 * @param s
 * @return
 */
private BigInteger to_base10(Sexp s) {
   BigInteger n = BigInteger.valueOf(0);
   while (!s.at) {
      n = n.shiftLeft(1);
      if (!s.hd.pname.equals("0")) n = n.setBit(0);
      s = s.tl;
   }
   return n;
}
/**
 * 
 * @param n
 * @return
 */
private Sexp to_base2(BigInteger n) {
   Sexp s = nil;
   while (!n.equals(BigInteger.valueOf(0))) {
      if (n.testBit(0)) 
         s = jn(one,s);
      else 
         s = jn(zero,s);
      n = n.shiftRight(1);
   }
   return s;
}
/**
 * 
 */
private void new_line() { //lee M-expresion y agrega \n
   buffer = mexp.getText().concat("\n");
   pos = 0;
}
/**
 * 
 * @return
 */
private boolean new_line2() { //lee para \n desde binary data
   StringBuffer str = new StringBuffer();
   int i;
   char ch;
   while (true) {
      i = get_char();
      if (i == -1) return true; // data agotados
      ch = (char) i;
      str.append(ch);
      if (ch == '\n') break;
   }
   buffer = str.toString();
   pos = 0;
   return false; // data no agotados
}
/**
 * 
 * @param delimiters
 * @return
 */
private String next_token(String delimiters) { // token from line buffer
   StringBuffer token = new StringBuffer();
   while (true) { // toma caracteres en token
      char ch;
      if (pos == buffer.length()) ch = ')'; 
      else ch = buffer.charAt(pos++);
      echo.append(ch); 
      //guarda solo \n o codigos ascii
      if (ch != 10 && (ch < 32 || ch >= 127)) continue;
      boolean is_delimiter = (delimiters.indexOf(ch) != -1);
      boolean is_white_space = (ch == ' ' || ch == '\n');
      boolean is_white_space_or_delimiter = (is_white_space || is_delimiter);
      if (token.length() == 0) { // token empty
         if (is_white_space) continue;
         token.append(ch);
         if (is_delimiter) break;
      } // token empty
      else { 
         if (!is_white_space_or_delimiter) token.append(ch);
         if (is_delimiter) {
            pos = pos - 1; 
            echo.setLength(echo.length()-1);
         } 
         if (is_white_space_or_delimiter) break;
      } 
   } // fin token
   return token.toString();
} // fin next token
/**
 * 
 */
StringBuffer echo; //usa para acumular en M-expresion
private JLabel lblOperaciones;
private JLabel lblLengthBitsXxxx;
/**
 * 
 */
private void run() { // ejecuta M-expressions

  new_line();

  int mexp_count = 0;
  while (true) { // loop thru M-exps

  echo = new StringBuffer();
  Sexp s = get();

  if (s == rparen && pos == buffer.length()) return; 
  if (mexp_count++ > 0) dspl.append("\n");
 
  String xxx = echo.toString();
  while (xxx.endsWith("\n")) xxx = xxx.substring(0,xxx.length()-1);
  while (xxx.startsWith("\n")) xxx = xxx.substring(1);
  if (echo_chkbx.getState()) dspl.append(xxx+"\n\n"); 

  if (s.bad()) {
     out("expression",s.toS());
     out("value","syntax error!");
     continue;
  } // end

  if (s.hd == define) {
    //define x para valores de v
    //si x es (fxyz) define f para valores &(xyz)v
    Sexp x = s.two();
    Sexp v = s.three();
    if (!x.at) {v = jn(lambda,jn(x.tl,jn(v,nil))); x = x.hd;}
    out("define",x.toS());
    out("value",v.toS());
    if (x.at && !x.nmb) {
       x.vstk.pop();
       x.vstk.push(v);
     }
     continue;
  } // end 

  // evalua la expresion s
  out("expression",s.toS());
  String save_buffer; int save_pos; // evaling read-exp clobbers buffer, pos
  save_buffer = buffer; save_pos = pos;
  Sexp v = eval(s,infinity); // "infinite" depth limit
  buffer = save_buffer; pos = save_pos;
  if (v == data_err)  
     out("value","out of data!");
  else
     out("value",v.toS());

  } // run each mexp

} // end of run
   /**
    * 
    * @return
    */
private Sexp get_bit() { //lee bit para binary data
   if (binary_data_lst.at) return data_err; // error de datos
   Sexp v = binary_data_lst.hd; 
   binary_data_lst = binary_data_lst.tl;
   // no puede ser cero
   if (!v.pname.equals("0")) v = one;
   was_read_lst = jn(v,was_read_lst);
   return v;
}
/**
 * 
 * @param x
 * @return
 */
private Sexp to_bits(Sexp x) { //Convierte S-exp para bit String
    String str = x.toS().concat("\n");
    Sexp v = nil;
    int i = str.length();
    while (i > 0) {
       i = i - 1;
       int j = ((int) str.charAt(i)) % 256;
       int k = 0;
       while (k < 8) {
          if ((j % 2) != 0) v = jn(one,v);
          else              v = jn(zero,v);
          j = j >>> 1;
          k = k + 1;
       }
    }
    return v;
}
/**
 * 
 * @return
 */
private int get_char() { //lee caracteres para binary data
  int k, v;
  k = 0; v = 0;
  while (k < 8) {
     Sexp b = get_bit();
     if (b.err) return -1; // out of data
     v = v << 1;
     if (b == one) v = v + 1;
     k = k + 1;
  }
  return v;   
}
/**
 * 
 * @param delimiters
 * @return
 */
private Sexp get_list(String delimiters) { //obtiene lista de s-expresion para binary data o m-expresion
   Sexp v = get_exp(delimiters);
   if (v == rparen) return nil;
   Sexp w = get_list(delimiters);
   return jn(v,w);
}
/**
 * 
 * @param delimiters
 * @return
 */
private Sexp get_exp(String delimiters) { //toma un dato s-expresion para binary data o m-expresion
   String t = next_token2(delimiters);
   Sexp a = null;
   if (!nval(t)) 
      a = mk_atom(t); //convierte token a atom
   else
      a = new Sexp(new BigInteger(t)); //crea un numero
   if (a == lparen) return get_list(delimiters); 
   return a;
} // end get sexp
/**
 * 
 * @param xx
 * @param yy
 */
private void out(String xx, String yy) {
  String x = new String(xx);
  String y = new String(yy);
  while (y.length() > 0) {
       String left, right;
       left = (x + "            ").substring(0,12);
       if (y.length() <= 50) {right = y; y = "";} 
                       else  {right = y.substring(0,50); y = y.substring(50);}
       dspl.append(left + right + "\n");
       x = "";
  }
} // end output routine
} // end lisp applet
