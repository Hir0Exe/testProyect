package principal;

import clases.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author miguelropero
 */
public class LecturaArchivos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Mundial brasil2014 = new Mundial("Brasil", 2014);
        brasil2014.setPartidos(cargarPartidos("src/files/partidos2014.csv"));
        brasil2014.setEquipos(cargarEquipos("src/files/equipos.csv"));
        
        Mundial rusia2018 = new Mundial("Rusia", 2018);
        rusia2018.setPartidos(cargarPartidos("src/files/partidos2018.csv"));
        rusia2018.setEquipos(cargarEquipos("src/files/equipos.csv"));
        
        System.out.println("total goles brasil 2014 "+getGolesMundial(brasil2014));
        System.out.println("total goles rusia 2018 "+getGolesMundial(rusia2018)+"\n");
        
        System.out.println("getPromedioGolesPorPartido brasil2014 "+getPromedioGolesPorPartido(brasil2014));
        System.out.println("getPromedioGolesPorPartido rusia2018 "+getPromedioGolesPorPartido(rusia2018)+"\n");
        
        getEquipoConMasGoles(brasil2014);
        getEquipoConMasGoles(rusia2018);
        System.out.println("");
        
        getEquiposPorContinente(brasil2014);
        System.out.println("");
        getEquiposPorContinente(rusia2018);
        System.out.println("");
        
        //No muestra todos los equipos debido a que el archivo equipos.csv está desactualizado.
        System.out.println("\nMundial     "+brasil2014.getPais()+"\n");
        getEquipoClasificadosPorGrupo(brasil2014);
        System.out.println("\nMundial     "+rusia2018.getPais()+"\n");
        getEquipoClasificadosPorGrupo(rusia2018);
        
    }
    
    private static List<Partido> cargarPartidos(String fileName) {
        
        List<Partido> partidos = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            
            String linea;
            br.readLine(); // se salta la primera línea (encabezados)
            
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(",");
                Partido partido = new Partido(campos[0], campos[1], campos[2], campos[3], campos[4], Integer.parseInt(campos[5]), Integer.parseInt(campos[6]));
                partidos.add(partido);
            }
            
        } 
        catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        
        return partidos;
    }
    
    private static List<Equipo> cargarEquipos(String fileName) {
        List<Equipo> equipos = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            
            String linea;
            br.readLine(); // se salta la primera línea (encabezados)
            
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(",");
                Equipo equipo = new Equipo(campos[0], campos[1], campos[2], campos[3]);
                equipos.add(equipo);
            }
            
        } 
        catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        
        return equipos;
    }
    
    public static int getGolesMundial(Mundial mundial) {
        List<Partido> partidos = mundial.getPartidos();
        int totalGoles = 0;
        for (int i = 0; i < partidos.size(); i++) {
            totalGoles += partidos.get(i).getGolesLocal() + partidos.get(i).getGolesVisitante();
        }
        return totalGoles;
    }
    
    public static String getPromedioGolesPorPartido(Mundial mundial) {
        List<Partido> partidos = mundial.getPartidos();
        double goles = 0;
        double auxInt = 0;
        for (int i = 0; i < partidos.size(); i++) {
            goles += (partidos.get(i).getGolesLocal() + partidos.get(i).getGolesVisitante());
            auxInt ++;
        }
        String auxString = String.valueOf((goles / auxInt));
        return auxString.substring(0, 3);
    }
    
    // Imprime en cuantos partidos hubo un ganador y en cuantos se dio empate
    private static void getpartidosGanadosEmpatados(Mundial mundial) {}
    
    // imprime los partidos dónde jugaron equipos del mismo continente
    private static void getPartidosEquiposMismoContinente(Mundial mundial) {}
    
    // Imprime el o los equipos con MAS goles anotados
    private static void getEquipoConMasGoles(Mundial mundial) {
        String equipo = null;
        int goles = 0;
        List<Partido> partidos = mundial.getPartidos();
        List<String> auxEquipos = new ArrayList<>();
        for (int i = 0; i < partidos.size(); i++) {
            int golesTemp = 0; //partidos.get(i).getGolesLocal();
            String equipoTemp = partidos.get(i).getEquipoLocal();
            if (!validarRepetido(auxEquipos, equipoTemp)) {
                for (int j = 0; j < partidos.size(); j++) {
                    if (partidos.get(j).getEquipoLocal().equals(equipoTemp)) {
                        golesTemp += partidos.get(j).getGolesLocal();
                    }
                    if (partidos.get(j).getEquipoVisitante().equals(equipoTemp)) {
                        golesTemp += partidos.get(j).getGolesVisitante();
                    }
                }
                auxEquipos.add(equipoTemp);
            }
            if (goles < golesTemp) {
                equipo = equipoTemp;
                goles = golesTemp;
            }
            golesTemp = 0;
            equipoTemp = partidos.get(i).getEquipoVisitante();
            if (!validarRepetido(auxEquipos, equipoTemp)) {
                for (int j = 0; j < partidos.size(); j++) {
                    if (partidos.get(j).getEquipoLocal().equals(equipoTemp)) {
                        golesTemp += partidos.get(j).getGolesLocal();
                    }
                    if (partidos.get(j).getEquipoVisitante().equals(equipoTemp)) {
                        golesTemp += partidos.get(j).getGolesVisitante();
                    }
                }
                auxEquipos.add(equipoTemp);
            }
            if (goles < golesTemp) {
                equipo = equipoTemp;
                goles = golesTemp;
            }
        }
        System.out.println("Equipo con mas goles del mundial "+mundial.getPais()+" es "+equipo+" con un total de "+goles+" goles en el año "+mundial.getAño());
    }
    
    private static boolean validarRepetido(List<String> aux, String equipoTemp){
        boolean res = false;
        for (int i = 0; i < aux.size(); i++) {
            if (aux.get(i).equals(equipoTemp)) {
                res = true;
            }
        }
        return res;
    }
    
    // Imprime el o los equipos con MENOS goles anotados
    private static void getEquipoConMenosGoles(Mundial mundial) {}
    
    // Imprime los equipos por cada Continente
    private static void getEquiposPorContinente(Mundial mundial) {
        String auxString;
        List<Equipo> equipos = mundial.getEquipos();
        List<String> continentes = new ArrayList<>();
        System.out.println("\nMUNDIAL "+mundial.getPais()+" del año "+mundial.getAño()+"\n");
        for (int i = 0; i < equipos.size(); i++) {
            String continenteTemp = equipos.get(i).getContinente();
            if (!validarRepetido(continentes, continenteTemp)) {
                auxString = "Equipos del continente de "+continenteTemp;
                for (int j = 0; j < equipos.size(); j++) {
                    if (equipos.get(j).getContinente().equals(continenteTemp)) {
                        auxString += "\n    "+equipos.get(j).toString();
                    }
                }
                System.out.println(auxString);
                continentes.add(continenteTemp);
            }
        }
    }
    
    // Imprime los equipos con más goleadores por continente
    private static void getEquipoMasGolesContinente(Mundial mundial) {}
    
    // Imprime el o los equipo con MAS puntos conseguidos
    private static void getEquipoConMasPuntos(Mundial mundial) {}
    
    // Imprime el o los equipo con MENOS puntos conseguidos
    private static void getEquipoConMenosPuntos(Mundial mundial) {}
    
    // Imprime el o los partidos con más goles
    private static void getPartidosConMasGoles(Mundial mundial) {}
    
    // Imprime los equipos clasificados en cada grupo
    private static void getEquipoClasificadosPorGrupo(Mundial mundial) {
    List<Partido> partidos = mundial.getPartidos();
    List<Equipo> equipos = mundial.getEquipos();
    List<String> gruposTemp = new ArrayList<>();
    List<String> equiposTemp = new ArrayList<>();
    String auxString = null;
        for (int i = 0; i < partidos.size(); i++) {
            String grupoTemp = partidos.get(i).getGrupo();
            if (!validarRepetido(gruposTemp, grupoTemp)) {
                for (int j = 0; j < partidos.size(); j++) {
                    String equipoTemp = partidos.get(j).getEquipoLocal();
                    int golesTemp = 0;
                    if (partidos.get(j).getGrupo().equals(grupoTemp) && !validarRepetido(equiposTemp, equipoTemp)) {
                        for (int k = 0; k < partidos.size(); k++) {
                            if (partidos.get(k).getEquipoLocal().equals(equipoTemp)) {
                                golesTemp += (partidos.get(j).getGolesLocal() - partidos.get(j).getGolesVisitante());
                            }
                            if (partidos.get(k).getEquipoVisitante().equals(equipoTemp)) {
                                golesTemp += (partidos.get(j).getGolesVisitante() - partidos.get(j).getGolesLocal());
                            }
                        }
                        for (int k = 0; k < equipos.size(); k++) {
                            if (equipos.get(k).getEquipo().equals(equipoTemp)) {
                                equipos.get(k).setGrupo(grupoTemp);
                                equipos.get(k).setPuntos(golesTemp);
                                equiposTemp.add(equipoTemp);
                                break;
                            }
                        }
                    }
                    equipoTemp = partidos.get(j).getEquipoVisitante();
                    golesTemp = 0;
                    if (partidos.get(j).getGrupo().equals(grupoTemp) && !validarRepetido(equiposTemp, equipoTemp)) {
                        for (int k = 0; k < partidos.size(); k++) {
                            if (partidos.get(k).getEquipoLocal().equals(equipoTemp)) {
                                golesTemp += (partidos.get(j).getGolesLocal() - partidos.get(j).getGolesVisitante());
                            }
                            if (partidos.get(k).getEquipoVisitante().equals(equipoTemp)) {
                                golesTemp += (partidos.get(j).getGolesVisitante() - partidos.get(j).getGolesLocal());
                            }
                        }
                        for (int k = 0; k < equipos.size(); k++) {
                            if (equipos.get(k).getEquipo().equals(equipoTemp)) {
                                equipos.get(k).setGrupo(grupoTemp);
                                equipos.get(k).setPuntos(golesTemp);
                                equiposTemp.add(equipoTemp);
                                break;
                            }
                        }
                    }
                }
                
                gruposTemp.add(grupoTemp);
            }
        }
        for (int i = 0; i < gruposTemp.size(); i++) {
            List<Equipo> aux = new ArrayList<>();
            for (int j = 0; j < equipos.size(); j++) {
                if (equipos.get(j).getGrupo() != null && gruposTemp.get(i).equals(equipos.get(j).getGrupo())) {
                    aux.add(equipos.get(j));
                }
            }
            Equipo auxTemp;
            for (int j = 0; j < aux.size() - 1; j++) {
                for (int d = 0; d < aux.size() - j - 1; d++) {                                                              
                    if (aux.get(d + 1).getPuntos() > aux.get(d).getPuntos()) {
                        auxTemp = aux.get(d+1);
                        aux.set(d+1, aux.get(d));
                        aux.set(d,auxTemp);
                    }
                }
            }
            auxString = "                  GRUPO "+gruposTemp.get(i)+"\n\n";
            for (int j = 0; j < aux.size(); j++) {
                if (j == 0) {
                    auxString += "      CLASIFICADOS\n";
                }
                if (j == 2) {
                    auxString += "      NO CALIFICADOS\n";
                }
                auxString += aux.get(j).toString()+", Puntos="+aux.get(j).getPuntos()+"\n";
            }
            System.out.println(auxString);
        }
    }
    
}
