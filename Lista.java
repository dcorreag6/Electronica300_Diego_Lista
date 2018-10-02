/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebalista;

/**
 *
 * @author didas
 */
public class Lista {
    private Nodo inicio;
    
    Lista()
    {
        inicio = null;
    }
    
    Lista(int x)
    {
        insertarInicio(x);
    }
    
    Lista(String s)
    {
        inicio = null;
        int i = 0;
        int lon = s.length();
        int x = 0;
        int sign = 1;
        while(i<lon)
        {
            if(s.charAt(i)-48>=0 && s.charAt(i)-48<=9){
                x = x*10 + s.charAt(i)-48;
            }
            else if(s.charAt(i)=='-'){
                sign = -1;
            }
            else{
                insertarFinal(x*sign);
                x = 0;
                sign = 1;
            }
            i++;
        }
        insertarFinal(x*sign);
    }
    
    public int longitud()
    {
        int i = 0;
        Nodo n = inicio;
        while(n!=null)
        {
            n = n.getSigNodo();
            i++;
        }
        return i;
    }
    
    public void insertarFinal(int x)
    {
        Nodo n = inicio;
        if(n!=null)
        {
            while(n.getSigNodo()!=null)
                n = n.getSigNodo();
            n.setSigNodo(new Nodo(x));
        }
        else
            insertarInicio(x);
    }
    
    public void insertarInicio(int x)
    {
        Nodo n = new Nodo(x);
        n.setSigNodo(inicio);
        inicio = n;
    }
    
    public void insertarN(int x,int nu) throws Exception
    {
        Nodo n = inicio;
        Nodo nuevo = new Nodo(x);
        int i=1;
        while(i+1<nu&&n!=null)
        {
            n = n.getSigNodo();
            i++;
        }
        if(i+1==nu)
        {
            nuevo.setSigNodo(n.getSigNodo());
            n.setSigNodo(nuevo);
        }
        else if(nu==1)
        {
            insertarInicio(x);
        }
        else
            throw new Exception();
    }
    
    public void insertarOrdenado(int x) throws Exception
    {
        Nodo n = inicio;
        Nodo nuevo = new Nodo(x);
        while(n.getSigNodo().getInfo()<x){
            n = n.getSigNodo();
        }
        if(x<=inicio.getInfo()){
            insertarInicio(x);
        }
        else if(x<=n.getSigNodo().getInfo()){
            nuevo.setSigNodo(n.getSigNodo());
            n.setSigNodo(nuevo);
        }
        else if(n==null){
            insertarFinal(x);
        }
        else
            throw new Exception();
    }
    
    private boolean ordenado()
    {
        Nodo n = inicio;
        while(n!=null)
        {
            if(n.getInfo()>n.getSigNodo().getInfo())
                return false;
            n = n.getSigNodo();
        }
        return true;
    }
    
    public void ordenar() throws Exception
    {
        Nodo ultimo;
        if(inicio!=null)
        {
            try
            {
                while(!ordenado())
                {
                    ultimo = inicio;
                    while(ultimo.getSigNodo()!=null)
                        ultimo = ultimo.getSigNodo();
                    insertarOrdenado(ultimo.getInfo());
                    eliminarFinal();
                }
            }
            catch(Exception ex)
            {}
        }
        else
            throw new Exception();
    }
    
    public int buscarPosicionXInfo(int x) throws Exception
    {
        Nodo n = inicio;
        int i=1;
        while(x!=n.getInfo()&&n!=null)
        {
            n = n.getSigNodo();
            i++;
        }
        if(x==n.getInfo())
            return i;
        else
            throw new Exception();
    }
    
    public Nodo buscarNodoXPosicion(int nu) throws Exception
    {
        Nodo n = inicio;
        int i=1;
        while(i+1<nu&&n!=null)
        {
            n = n.getSigNodo();
            i++;
        }
        if(i+1==nu)
        {
            return n.getSigNodo();
        }
        else if(nu==1)
        {
            return inicio;
        }
        else
            throw new Exception();
    }
    
    public void eliminarFinal() throws Exception
    {
        Nodo n = inicio;
        if(n!=null&&n.getSigNodo()!=null)
        {
            while(n.getSigNodo().getSigNodo()!=null)
                n = n.getSigNodo();
            n.setSigNodo(null);
        }
        else if(n==null)
            throw new Exception();
        else if(n.getSigNodo()==null)
            eliminarInicio();
    }
    
    public void eliminarInicio() throws Exception
    {
        if(inicio!=null)
        {
            inicio = inicio.getSigNodo();
        }
        else
            throw new Exception();
        
    }
    
    public void eliminarN(int nu) throws Exception
    {
        Nodo n = inicio;
        int i=1;
        while(i+1<nu&&n!=null)
        {
            n = n.getSigNodo();
            i++;
        }
        if(i+1==nu)
        {
            n.setSigNodo(n.getSigNodo().getSigNodo());
        }
        else if(nu==1)
        {
            eliminarInicio();
        }
        else
            throw new Exception();
    }
    
    public String toString()
    {
        Nodo n = inicio;
        String str = "";
        if(n!=null)
        {
            while(n!=null)
            {
                str = str  + n.getInfo() + " ";
                n = n.getSigNodo();
            }
            return str + "\n";
        }
        return null;
    }
}
