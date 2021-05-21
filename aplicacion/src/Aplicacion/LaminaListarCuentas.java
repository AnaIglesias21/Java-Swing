
package Aplicacion;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import static Aplicacion.AplicacionCuentaBancaria.listaCuentas;

/**
 * clase que crea la lámina que contendrá los componentes necesarios para el listado de cuentas
 * @author Ana I. Iglesias Martínez
 */

class LaminaListarCuentas extends JPanel{
 
    private JTable miTabla;
    private JScrollPane scroll;
    private final String [] nombreColumnas= {"Nº Cuenta","Titular","Saldo"};
    private final Object [][] datosFila = new Object[listaCuentas.size()][3];
    private JButton imprimir;
    
    public LaminaListarCuentas(){

        //indicamos la disposición de la lámina
        setLayout(new BorderLayout());
        setBackground(Color.GRAY);
        
        //comprobamos que haya cuentas creadas
        if (listaCuentas.isEmpty())
            
            JOptionPane.showMessageDialog(null, "No hay ninguna cuenta registrada");
            
        else{
            //cramos la lamina para el título
            JPanel laminaTitulo = new JPanel();
            laminaTitulo.setBackground(Color.GRAY);
            JLabel titulo = new JLabel("Listado de Cuentas");
            titulo.setForeground(new Color(245,252,114));
            titulo.setFont(new Font(Font.MONOSPACED,Font.BOLD,25));
            laminaTitulo.add(titulo);
            add(laminaTitulo, BorderLayout.NORTH);
            /*recorremos el ArrayList listaCuentas para crear el array de objetos que
               necesitamos para crear la JTable*/ 
            for (int i=0;i<listaCuentas.size();i++){
                
                datosFila [i][0] = listaCuentas.get(i).dameCuenta();
                datosFila [i][1] = listaCuentas.get(i).dameTitular().dameNombre()+
                        " "+listaCuentas.get(i).dameTitular().dameApellido1()+" "+
                        listaCuentas.get(i).dameTitular().dameApellido2();
                datosFila [i][2] = listaCuentas.get(i).dameSaldo();
                
            }
            JPanel listado = new JPanel();
            listado.setBackground(Color.GRAY);
            ModeloTabla modelo = new ModeloTabla(datosFila,nombreColumnas);
            miTabla = new JTable();
            miTabla.setModel(modelo);
            JTableHeader th = miTabla.getTableHeader();
            th.setBackground(Color.ORANGE);
            th.setForeground(Color.DARK_GRAY);
            th.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD, 20));
            //no permitimos que se cambien las columnas de sitio
            th.setReorderingAllowed(false);
            //le damos un ancho a cada celda
            miTabla.getColumnModel().getColumn(0).setPreferredWidth(35);
            miTabla.getColumnModel().getColumn(1).setPreferredWidth(195);
            miTabla.getColumnModel().getColumn(2).setPreferredWidth(15);
            //alineamos los datos de la columna del saldo de la  JTable
            DefaultTableCellRenderer alinear = new DefaultTableCellRenderer();
            alinear.setHorizontalAlignment(SwingConstants.CENTER);
            miTabla.getColumnModel().getColumn(2).setCellRenderer(alinear);
            miTabla.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            miTabla.setBackground(new Color(253,254,192));
            //creamos el scroll para visualizar los datos
            scroll = new JScrollPane();
            //le damos tamaño
            scroll.setPreferredSize(new Dimension(600,250));
            //añadimos nuesta tabla al scroll
            scroll.setViewportView(miTabla);
            //establecemos un color de fondo
            scroll.setBackground(new Color(245,252,114));
            
            //añadimos el Scroll a la lámiona
            listado.add(scroll);
            add(listado, BorderLayout.CENTER);
            
            //Creamos un alámina para el botón de imprimir
            JPanel panelImprimir = new JPanel();
            panelImprimir.setBackground(Color.GRAY);
            //creamos el botón
            imprimir = new JButton("Imprimir");
            imprimir.setToolTipText("Listado impreso de cuentas");
            //añadimos el botoón a la lámiana
            panelImprimir.add(imprimir);
            //añadimos el apnel a la zona norte de la lámina contenedora
            add(panelImprimir, BorderLayout.SOUTH);
            //ponemos el botón imprimir a la escucha
            imprimir.addActionListener(new ActionListener() {
                //cramos la clase interna anónima para gestionar la acción a realizar por el botón
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        miTabla.print();
                        JOptionPane.showMessageDialog(null, "Imprimiendo ...", "Imprimir", JOptionPane.INFORMATION_MESSAGE);
                    }catch(Exception exc){
                       JOptionPane.showMessageDialog(null, "Error en la impresión", "Imprimir", JOptionPane.ERROR_MESSAGE);
                    }
                }    
            });
        }
    }
    /*clase que define nuestro modelo de tabla yno spermite que las celdas no sean
     *para así no poder modificar datos. Solo consultar
     */
    private class ModeloTabla extends DefaultTableModel{
        private String [] titulos;
        private Object [][] datos;
        
        public ModeloTabla(){
            
        }
        public ModeloTabla (Object[][]datos, String[] titulos){
            super();
            this.titulos = titulos;
            this.datos = datos;
            setDataVector(datos,titulos);
        }
        //método que impide que las celdas de la Jtable sean editables
        @Override
        public boolean isCellEditable(int rows, int column){
            return false;
        }
    }
   
}
