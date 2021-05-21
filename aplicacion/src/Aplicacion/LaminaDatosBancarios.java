
package Aplicacion;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Clase para gestionar la entrada de datos bancarios
 *  
 * @author Ana I. Iglesias Martínez
 */
public class LaminaDatosBancarios extends JPanel{
    
    private JTextField entidad, oficina, dc, numCuenta, 
                       saldo, fechaApertura;
    private ControlarEntradas control = new ControlarEntradas();
    private Double interesRemuneracion = 0.0;
    private JRadioButton ahorro,personal,empresa;
    private ButtonGroup tipoCuenta;
   
    public LaminaDatosBancarios(){
            
        //definimos la disposición y el borde de la lámina Datos Bancarios
        setLayout(new GridLayout(4,0));
        setBorder(javax.swing.BorderFactory.createCompoundBorder(new EmptyBorder(10,10,0,10), 
            new TitledBorder(new EtchedBorder(),"Datos Entidad",
            TitledBorder.DEFAULT_JUSTIFICATION,
            TitledBorder.DEFAULT_POSITION,
            new Font(Font.DIALOG,Font.BOLD,14),
            Color.ORANGE)));                       
        //creamos la lámina para el número de cuenta
        JPanel panelCuenta = new JPanel();
            
        //damos disposición al panelCuenta 
        panelCuenta.setLayout(new FlowLayout(FlowLayout.LEFT));
            
        //creamos y añadimos los componentes a la lámina panelCuenta
        panelCuenta.add(new JLabel("     Nº de cuenta: "));
        entidad = new JTextField(4);
        oficina = new JTextField(4);
        dc = new JTextField(2);
        numCuenta = new JTextField(10);
        fechaApertura = new JTextField(10);
        panelCuenta.add(entidad);
        panelCuenta.add(oficina);
        panelCuenta.add(dc);
        panelCuenta.add(numCuenta);
        
        //controlamos la entrada de datos según las características de los atributos
        control.soloNumeros(entidad);
        control.limiteCaracteres(entidad, 4);
        control.soloNumeros(oficina);
        control.limiteCaracteres(oficina, 4);
        control.soloNumeros(dc);
        control.limiteCaracteres(dc, 2);
        control.soloNumeros(numCuenta);
        control.limiteCaracteres(numCuenta, 10);
        //agregamos el panelCuenta a la lámina DatosBanarios
        add(panelCuenta);
        
        /*la fecha de apertura de la cuenta es la del día actual,se toma del sistema,
          agregamos las etiquetas correspondientes a la lámina DatosBancarios*/
        JPanel panelFecha = new JPanel();
        panelFecha.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelFecha.add(new JLabel("Fecha apertura: "));
        fechaApertura.setText(Recursos.fechaSistema());
        panelFecha.add(new JLabel(Recursos.fechaSistema()));
        add(panelFecha);
        
        //Creamos la lámina para introducir el saldo
        JPanel panelSaldo = new JPanel();
        panelSaldo.setLayout(new FlowLayout(FlowLayout.LEFT));
        //agregamos el JTExtfield para el saldo y su etiqueta a la lámina DatosBAncarios
        panelSaldo.add(new JLabel("                 Saldo: "));
        saldo = new JTextField(8);
        panelSaldo.add(saldo);
        add(panelSaldo);    
        //contol de emtrada de datos
        control.soloDouble(saldo);
        control.limiteCaracteres(saldo, 8);
        
        //agregamos lau etiqueta de los botones de radio
        JPanel panelRadios = new JPanel();
        panelRadios.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelRadios.add(new JLabel(" Tipo de cuenta:"));
        JPanel botonesRadio = new JPanel();
        botonesRadio.setLayout(new FlowLayout());
        
        //creamos los radio button y les asignamos el nombre de la etiqueta
        ahorro = new JRadioButton("Ahorro",true);
        ahorro.setActionCommand("ahorro");
        personal = new JRadioButton("Personal",false);
        personal.setActionCommand("personal");
        empresa = new JRadioButton("Empresa",false);
        empresa.setActionCommand("empresa");
        
        //agrupamos los radio button para permitir seleccionar solo uno
        tipoCuenta = new ButtonGroup();
        //añadimos los botones al grupo
        tipoCuenta.add(ahorro);
        tipoCuenta.add(personal);
        tipoCuenta.add(empresa);
        
        //ponemos los botones a la escucha para introducir los datos de cada tipo de cuenta
        ahorro.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e) { 
               
              interesRemuneracion = Double.parseDouble(Recursos.pedirDatos("interés remuneración: "));
              
           }
        });
        
        personal.addActionListener(new ActionListener(){
            
           @Override
           public void actionPerformed(ActionEvent e) {
              
              MarcoDatos datosPersonal = new MarcoDatos("Cuenta personal");
              
              datosPersonal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
              
              
           }
        });
        
        empresa.addActionListener(new ActionListener(){
            
           @Override
           public void actionPerformed(ActionEvent e) { 
               
               //creamos un marco para introducir el interés de descubierto y la cantidad
               MarcoDatos datosEmpresa = new MarcoDatos("Cuenta empresa");
               
               datosEmpresa.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            
           }
        });
        
        //agragamos los botones a la lámina
        botonesRadio.add(ahorro);
        botonesRadio.add(personal);
        botonesRadio.add(empresa);
        panelRadios.add(botonesRadio);
        add(panelRadios);
        this.updateUI();
    }
    
    //Método getter
    public String dameTipoCuenta(){
        return tipoCuenta.getSelection().getActionCommand();
    }
    public String dameEntidadCuenta(){
        return entidad.getText();
    }
    public String dameOficinaCuenta(){
        return oficina.getText();
    }    
    public String dameDcCuenta(){
        return dc.getText();
    }
    public String dameNumCuenta(){
        return numCuenta.getText();
    }
    public String dameSaldoCuenta(){
        return saldo.getText();
    }
    public String dameFechaCuenta(){
        return fechaApertura.getText();
    }
    public String dameCuentaCompleta(){
        return entidad.getText()+oficina.getText()+dc.getText()+numCuenta.getText();
    }
    
    //Métodos setter
    public void estableceEntidadCuenta(String entidadCuenta){
        entidad.setText(entidadCuenta);
    }
    public void estableceOficionaCuenta(String oficinaCuenta){
        oficina.setText(oficinaCuenta);
    }
    public void estableceDcCuenta(String dcCuenta){
        dc.setText(dcCuenta);
    }
    public void estableceNumCuenta(String numeroCuenta){
        numCuenta.setText(numeroCuenta);
    }
    public void estableceSaldoCuenta(String saldoC){
        saldo.setText(saldoC);
    }
    public void estableceFechaCuenta(){
        fechaApertura.setText(Recursos.fechaSistema());
    }
    
}
