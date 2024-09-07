package proyectocompi2s24.analisis;

//importaciones
import java_cup.runtime.Symbol;
import java.util.LinkedList;
import proyectocompi2s24.ttokens.TablaTokens;
import static proyectocompi2s24.ttokens.TablaTokens.tokenList;
import proyectocompi2s24.errores.Errores;
%%

//codigo de usuario
%{
    public LinkedList<Errores> listaErrores = Errores.errorList;
    String cadena = "";
%}

%init{
    yyline = 1;
    yycolumn = 1;
    listaErrores = Errores.errorList;
%init}

//caracteristicas de jflex
%cup
%class scanner
%public
%line
%column
%char
%full
//%debug

//creacion de estados
%state CADENA

//simbolos
FINCADENA = ";"
MULT = "*"
DIV = "/"
MAS = "+"
MENOS = "-"
UNION = "u"
COMPLEMENTO = "^"
INTERSECCION = "&"
PAR1 = "("
PAR2 = ")"
PUNTO = "."
COMA = ","
DOSPUNTOS = ":"
FDER = "->"
VIRGULILLA = "~"
LBRACE = "{"
RBRACE = "}"
VAR = [0-9A-Za-z_]+
BLANCOS = [\ \r\t\f\n]+
NUMEROS = [x21-x7E] // aceptamos enteros y decimales

// palabras reservadas
TKCONSOLE = "console"
TKLOG = "log"
CONJ = "conj"
OPERA = "opera"
SCOPE = "scope"
EVALUAR = "evaluar"

%%

<YYINITIAL> {TKCONSOLE}
{
    TablaTokens token = new TablaTokens("CONSOLE", true, yyline, yycolumn, yytext());
    tokenList.add(token);
    return new Symbol(sym.TKCONSOLE,yyline,yycolumn,yytext())
;}
<YYINITIAL> {TKLOG}
{
    TablaTokens token = new TablaTokens("LOG", true, yyline, yycolumn, yytext());
    tokenList.add(token);
    return new Symbol(sym.TKLOG,yyline,yycolumn,yytext())
;}

<YYINITIAL> {CONJ}
{
    TablaTokens token = new TablaTokens("CONJ", true, yyline, yycolumn, yytext());
    tokenList.add(token);
    return new Symbol(sym.CONJ,yyline,yycolumn,yytext())
;}

<YYINITIAL> {OPERA}
{
    TablaTokens token = new TablaTokens("OPERA", true, yyline, yycolumn, yytext());
    tokenList.add(token);
    return new Symbol(sym.OPERA,yyline,yycolumn,yytext())
;}

<YYINITIAL> {EVALUAR}
{
    TablaTokens token = new TablaTokens("EVALUAR", true, yyline, yycolumn, yytext());
    tokenList.add(token);
    return new Symbol(sym.EVALUAR,yyline,yycolumn,yytext())
;}

<YYINITIAL> {UNION}
{
    TablaTokens token = new TablaTokens("UNION", true, yyline, yycolumn, yytext());
    tokenList.add(token);
    return new Symbol(sym.UNION,yyline,yycolumn,yytext());

}

<YYINITIAL> {INTERSECCION}
{
    TablaTokens token = new TablaTokens("INTERSECCION", true, yyline, yycolumn, yytext());
    tokenList.add(token);
    return new Symbol(sym.INTERSECCION,yyline,yycolumn,yytext());

}

<YYINITIAL> {COMPLEMENTO}
{
    TablaTokens token = new TablaTokens("COMPLEMENTO", true, yyline, yycolumn, yytext());
    tokenList.add(token);
    return new Symbol(sym.COMPLEMENTO,yyline,yycolumn,yytext());

}

<YYINITIAL> {SCOPE}
{
    TablaTokens token = new TablaTokens("SCOPE", true, yyline, yycolumn, yytext());
    tokenList.add(token);
    return new Symbol(sym.SCOPE,yyline,yycolumn,yytext())
;}

<YYINITIAL> {DOSPUNTOS}
{
    TablaTokens token = new TablaTokens("DOSPUNTOS", true, yyline, yycolumn, yytext());
    tokenList.add(token);
    return new Symbol(sym.DOSPUNTOS,yyline,yycolumn,yytext())
;}

<YYINITIAL> {FDER}
{
    TablaTokens token = new TablaTokens("FDER", true, yyline, yycolumn, yytext());
    tokenList.add(token);
    return new Symbol(sym.FDER,yyline,yycolumn,yytext())
;}

<YYINITIAL> {VIRGULILLA}
{
    TablaTokens token = new TablaTokens("VIRGULILLA", true, yyline, yycolumn, yytext());
    tokenList.add(token);
    return new Symbol(sym.VIRGULILLA,yyline,yycolumn,yytext())
;}

<YYINITIAL> {LBRACE}
{
    TablaTokens token = new TablaTokens("LBRACE", true, yyline, yycolumn, yytext());
    tokenList.add(token);
    return new Symbol(sym.LBRACE,yyline,yycolumn,yytext())
;}

<YYINITIAL> {RBRACE}
{
    TablaTokens token = new TablaTokens("RBRACE", true, yyline, yycolumn, yytext());
    tokenList.add(token);
    return new Symbol(sym.RBRACE,yyline,yycolumn,yytext())
;}

<YYINITIAL> {NUMEROS}
{
    TablaTokens token = new TablaTokens("NUMERO", true, yyline, yycolumn, yytext());
    tokenList.add(token);
    return new Symbol(sym.NUMEROS,yyline,yycolumn,yytext());
}


<YYINITIAL> {VAR}
{
    TablaTokens token = new TablaTokens("IDENTIFICADOR", true, yyline, yycolumn, yytext());
    tokenList.add(token);
    return new Symbol(sym.VAR,yyline,yycolumn,yytext());
}

<YYINITIAL> {PUNTO}
{
    TablaTokens token = new TablaTokens("PUNTO", true, yyline, yycolumn, yytext());
    tokenList.add(token);
    return new Symbol(sym.PUNTO,yyline,yycolumn,yytext());

}

<YYINITIAL> {COMA}
{
    TablaTokens token = new TablaTokens("COMA", true, yyline, yycolumn, yytext());
    tokenList.add(token);
    return new Symbol(sym.COMA,yyline,yycolumn,yytext());

}

<YYINITIAL> {FINCADENA}
{
    TablaTokens token = new TablaTokens("FINCADENA", true, yyline, yycolumn, yytext());
    tokenList.add(token);
    return new Symbol(sym.FINCADENA,yyline,yycolumn,yytext());

}
<YYINITIAL> {MULT}
{
    TablaTokens token = new TablaTokens("MULTIPLICACION", true, yyline, yycolumn, yytext());
    tokenList.add(token);
    return new Symbol(sym.MULT,yyline,yycolumn,yytext());

}
<YYINITIAL> {DIV}
{
    TablaTokens token = new TablaTokens("DIVISION", true, yyline, yycolumn, yytext());
    tokenList.add(token);
    return new Symbol(sym.DIV,yyline,yycolumn,yytext());

}
<YYINITIAL> {MAS}
{
    TablaTokens token = new TablaTokens("SUMA", true, yyline, yycolumn, yytext());
    tokenList.add(token);
    return new Symbol(sym.MAS,yyline,yycolumn,yytext());

}
<YYINITIAL> {MENOS}
{
    TablaTokens token = new TablaTokens("RESTA", true, yyline, yycolumn, yytext());
    tokenList.add(token);
    return new Symbol(sym.MENOS,yyline,yycolumn,yytext());

}

<YYINITIAL> {PAR1}
{
    TablaTokens token = new TablaTokens("PARENTESIS_A", true, yyline, yycolumn, yytext());
    tokenList.add(token);
    return new Symbol(sym.PAR1,yyline,yycolumn,yytext());

}
<YYINITIAL> {PAR2}
{
    TablaTokens token = new TablaTokens("PARENTESIS_C", true, yyline, yycolumn, yytext());
    tokenList.add(token);
    return new Symbol(sym.PAR2,yyline,yycolumn,yytext());

}
<YYINITIAL> {BLANCOS} {}

// <! \n !>
// "asdasdasd"

<YYINITIAL> [\"] {yybegin(CADENA);cadena ="";}

<CADENA> {
    [\"]    {String tmp = cadena; cadena =""; yybegin(YYINITIAL);
            return new Symbol(sym.CADENA, yyline, yycolumn, tmp);}
    [^\"]   {cadena+=yytext();}
}

<YYINITIAL> {BLANCOS} {}

<YYINITIAL> \#.*\n { /* Ignore */}
<YYINITIAL> \<\![\s\S]*?\!\> { /* Ignore */ }

<YYINITIAL> . {
    Errores error = new Errores("LEXICO", "El caracter " + yytext() + " no pertenece al lenguaje", yyline, yycolumn);
    listaErrores.add(error);
}