
package Aplicacion;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import static Aplicacion.AplicacionCuentaBancaria.listaCuentas;

/**
 * Clase LaminaOperciones que crea la lámina que permitirá buscar un cuenta para
 * ralizar la operación correspondiente
 * @author Ana I. Iglesias Martínez
 */
class LaminaOperaciones extends JPanel{
    
    private JTextField entidad, oficina, dc, numCuenta; 
    private ControlarEntradas control = new ControlarEntradas();
    private JButton buscar;
    private String numeroCuenta;
    private CuentaBancaria cuenta;
    private String cantidad;
    private Double montante;
    public static int indice=0;
    
    
    public LaminaOperaciones(String titulo){
        
       //disposición de la lámina Consulta
       setLayout(new BorderLayout());
        
       //creamos una lámina para buscar la cuenta bancaria y le damos disposición
       JPanel miLaminaBuscar = new JPanel(new FlowLayout());
       //creamos y agregamos los componentes a la lámina
       entidad = new JTextField(4);
       oficina = new JTextField(4);
       dc = new JTextField(2);
       numCuenta = new JTextField(10);
       buscar = new JButton("Buscar");
       //asignamos una descripción al boton buscar
       buscar.setToolTipText("Busca la cuenta bancaria");
       
       miLaminaBuscar.add(new JLabel("Nº de cuenta: "));
       miLaminaBuscar.add(entidad);
       miLaminaBuscar.add(oficina);
       miLaminaBuscar.add(dc);
       miLaminaBuscar.add(numCuenta);
       
       //controlamos la entrada de datos según las características de los atributos
       control.soloNumeros(entidad);
       control.limiteCaracteres(entidad, 4);
       control.soloNumeros(oficina);
       control.limiteCaracteres(oficina, 4);
       control.soloNumeros(dc);
       control.limiteCaracteres(dc, 2);
       control.soloNumeros(numCuenta);
       control.limiteCaracteres(numCuenta, 10);
       
       //ponemos el botón a la escucha creando una clase interna anónima 
       buscar.addActionListener(new ActionListener() {//clase interna anónima 
            
            private String numeroCuenta;
            private boolean encontrado= false;
            private LaminaResultado lamina;
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                numeroCuenta = entidad.getText()+oficina.getText()+dc.getText()+numCuenta.getText();
                
                if (numeroCuenta.length()<20 || !CuentaBancaria.comprobarDigito(Integer.parseInt(entidad.getText()), 
                        Integer.parseInt(oficina.getText()), Integer.parseInt(dc.getText()), 
                        Long.parseLong(numCuenta.getText()))){
                    
                   JOptionPane.showMessageDialog(null,"Número de cuenta erróneo.", "Cuenta", JOptionPane.ERROR_MESSAGE);
                   try{
                      lamina.removeAll();
                      lamina.updateUI();
                   }catch (Exception exp){} 
                }else{
                //buscamos el numero de cuenta
                   encontrado = false;
                   indice = 0;
                   while(indice<listaCuentas.size() && !encontrado){
                       
                     if (listaCuentas.get(indice).dameCuenta().equals(numeroCuenta)){
                         
                         encontrado = true;  
                         cuenta = listaCuentas.get(indice);
                     }else
                         indice ++;
                   }
                   
                   if (!encontrado){
                      JOptionPane.showMessageDialog(null, "Cuenta no existente", "Cuentas",JOptionPane.INFORMATION_MESSAGE);
                      try{
                         lamina.removeAll();
                         lamina.updateUI();
                      }catch (Exception exp){}   
                   }else{
                      
                      //según la opción elegida se hará una operación u otra  
                      switch (titulo) {
                        
                        case "Ingresar ":
                           //si se encuentra el nº de cuenta se pide la cantidad a retirar
                           cantidad = Recursos.pedirDatos("cantidad: ");
                                                                             
                           Double montante = Double.parseDouble(cantidad);
                           if (montante>0){
                              //vemos que tipo de cuenta es para permitir descubierto o no 
                                if (cuenta instanceof CuentaAhorro){
                                          
                                    lamina = new LaminaResultado(montante,cuenta.dameSaldo(),"Ingreso cuenta ahorro");
                                    add(lamina, BorderLayout.CENTER);
                                    lamina.updateUI();
                                    cuenta.estableceSaldo(cuenta.dameSaldo()+montante);
                                                                    
                                }else if (cuenta instanceof CuentaPersonal){
                                           
                                    lamina = new LaminaResultado(montante,cuenta.dameSaldo(),"Ingreso cuenta personal");
                                    add(lamina, BorderLayout.CENTER);
                                    lamina.updateUI();
                                    cuenta.estableceSaldo(cuenta.dameSaldo()+montante);                   
                                           
                                }else if (cuenta instanceof CuentaEmpresa){
                                           
                                    lamina = new LaminaResultado(montante,cuenta.dameSaldo(),"Ingreso cuenta empresa");
                                    add(lamina, BorderLayout.CENTER);
                                    lamina.updateUI();
                                    cuenta.estableceSaldo(cuenta.dameSaldo()+montante);
                                             
                                }
                           }    
                           break;
                           
                        case "Reintegro ":
                           //si se encuentra el nº de cuenta se pide la cantidad a retirar
                           cantidad = Recursos.pedirDatos("cantidad: ");
                                       
                           montante = Double.parseDouble(cantidad);
                           if (montante>0){
                             //vemos que tipo de cuenta es para permitir descubierto o no 
                             if (cuenta instanceof CuentaAhorro){
                                           
                                if (cuenta.dameSaldo()<montante){
                                    JOptionPane.showMessageDialog(null,
                                    "La cantidad que se quiere retirar sobrepasa el saldo de la cuenta\n"
                                    + "EL saldo actual de la cuenta es: " + cuenta.dameSaldo()+" €");
                                               
                                } else{
         
                                    //creamos una lámina para visualizar los cambios realizados en al cuenta
                                    lamina = new LaminaResultado(montante,cuenta.dameSaldo(),"Reintegro cuenta ahorro");
                                    add(lamina, BorderLayout.CENTER);
                                    lamina.updateUI();
                                    cuenta.estableceSaldo(cuenta.dameSaldo()-montante);
                                              
                                } 
                                           
                            }else if (cuenta instanceof CuentaPersonal){
                                           
                                if (cuenta.dameSaldo()<montante){
                                               
                                    JOptionPane.showMessageDialog(null,
                                     	"La cantidad que se quiere retirar sobrepasa el saldo de la cuenta\n"
                                        + "EL saldo actual de la cuenta es: " + cuenta.dameSaldo()+" €");
                                               
                                }else{
                                              
                                    lamina = new LaminaResultado(montante,cuenta.dameSaldo(),"Reintegro cuenta personal");
                                    add(lamina, BorderLayout.CENTER);
                                    lamina.updateUI();
                                    cuenta.estableceSaldo(cuenta.dameSaldo()-montante);
                                              
                                }
                                           
                            }else if (cuenta instanceof CuentaEmpresa){
                                           
                                if ((((CuentaEmpresa) cuenta).dameDescubierto()+cuenta.dameSaldo())<montante){
                                               
                                   JOptionPane.showMessageDialog(null,
                                      	"La cantidad que se quiere retirar sobrepasa la cantidad de descubierto permitido\n"
                                        + "EL saldo actual de la cuenta es: " + cuenta.dameSaldo()+" €"
                                        + "\nCantidad descubierto permitida: " + ((CuentaEmpresa) cuenta).dameDescubierto()+" €");
                                               
                                } else{
                                               
                                   lamina = new LaminaResultado(montante,cuenta.dameSaldo(),"Reintegro cuenta empreesa");
                                   add(lamina, BorderLayout.CENTER);
                                   lamina.updateUI();
                                   cuenta.estableceSaldo(cuenta.dameSaldo()-montante);
                                              
                                }
                            }    
                        }
                        
                        break;
                        
                        case "Consulta saldo ":
                            JOptionPane.showMessageDialog(null, "Saldo " + cuenta.dameSaldo()+ " €", "Consulta saldo",JOptionPane.INFORMATION_MESSAGE);
                            break;
                      }     
                   }       
                } 
            }
        });
       
       //añadimos el botón buscar 
       miLaminaBuscar.add(buscar);
       //añadimos esta lámina a la zona norte del la LaminaOperaciones
       this.add(miLaminaBuscar, BorderLayout.NORTH); 
    }
    
    //lámina para visualizar los datos después de la operación de ingresar o retirar cantidad
    private class LaminaResultado extends JPanel{
        
        public LaminaResultado(Double cantidad, Double saldoCuenta, String cadena){
            
            setLayout(new GridLayout(3,0));
            setBorder(javax.swing.BorderFactory.createCompoundBorder(new EmptyBorder(10,10,0,10), new TitledBorder(cadena)));
            
            add(new JLabel("Saldo anterior:          " + saldoCuenta+ " €   "));
            add(new JLabel("Cantidad a reintegrar:   " + cantidad + " €     "));
            if (cadena.substring(0,9).equals("Reintegro"))
              add(new JLabel("Saldo actual:            "+(saldoCuenta - cantidad) + " €     "));
            else
              add(new JLabel("Saldo actual:            "+(saldoCuenta + cantidad) + " €     "));  
        }
    }
}
