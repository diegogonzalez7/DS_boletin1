package e2;

import java.util.Arrays;
/*
ALGORITMO PARA SENTARSE:
1º-Sentarse en la original.
2º-Copiar el resultado.
3º-Levantarse de la original.
4º-Comparar la copia y la original.
5º-Si son iguales para, si son distintas sigue.
*/
public class SocialDistance {
    public static Boolean isValidArray(char[][] layout) {
        if (layout == null) throw new IllegalArgumentException(); //Excepción de Array nulo

        int ps_colum = layout[0].length; //Nº posible de columnas

        for (char[] chars : layout) { //Recorro matriz por filas
            if (chars.length != ps_colum) { //Si en algun momento cambia el nº columnas el array es inválido
                throw new IllegalArgumentException("Invalid Layout :Nor square nor rectangular layout");
            }
            for (int j = 0; j < ps_colum; j++) { //Recorro la matriz elemento a elemento
                if (chars[j] != 'A' && chars[j] != '.' && chars[j] != '#') {    //Mira si hay char distintos de los esperados
                    throw new IllegalArgumentException("Invalid Layout : Invalid character in the layout");
                }
            }
        }
        for (char[] chars : layout) {
            for (int j = 0; j < ps_colum; j++) {    //Recorre para ver si el layout inicial está compuesto sólo por 'A'
                if (chars[j] == 'A') return true;
            }
        }
        return false;
    }

    public static char[][] everyoneSat(char[][] layout, int rows, int cols) {   //Recorre el layout cambiando 'A' por '#' (sienta a todos a la vez).
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (layout[i][j] == 'A') layout[i][j] = '#';
            }
        }
        return layout;
    }
    public static char[][] manageMatrix(char[][] layout, int rows, int cols,char option) {
        int conteo, g, h;
        boolean trigger;
        char[][] copyLayout =  getCopyLayout(layout);

        //Levantar gente
        if (option == 'l') {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (layout[i][j] == '#') {
                        conteo = 0;
                        h = -1; //Incremento filas
                        g = -1; //Incremento columnas
                        while (conteo < 4 && h < 2) {
                            if (i + h == -1 || i + h == rows) h++;
                            else {
                                if (j + g != -1 && j + g != cols) {
                                    if (i + h != i || j + g != j) {
                                        if (layout[i + h][j + g] == '#') conteo++;
                                    }
                                }
                                if (g == 1) {
                                    g = -2;
                                    h++;
                                }
                                g++;
                            }
                        }
                        if (conteo >= 4) copyLayout[i][j] = 'A';
                    }
                }
            }
        }
        //Sentar gente
        else if (option == 's') {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (layout[i][j] == 'A') {
                        trigger = true;
                        h = -1; //Incremento filas
                        g = -1; //Incremento columnas
                        while (trigger && h < 2) {
                            if (i + h == -1 || i + h == rows) h++;
                            else {
                                if (j + g != -1 && j + g != cols) {
                                    if (i + h != i || j + g != j) {
                                        if (layout[i + h][j + g] == '#') trigger = false;
                                    }
                                }
                                if (g == 1) {
                                    g = -2;
                                    h++;
                                }
                                g++;
                            }
                        }
                        if (trigger) copyLayout[i][j] = '#';
                    }
                }
            }
        } return copyLayout;
    }

    private static char[][] getCopyLayout(char[][] layout) {
        return Arrays.stream(layout).map(char[]::clone).toArray(char[][]::new);
    }
    //'A': Sitio libre    '#':Alguien sentado   '.':Sitio no disponible
    public static char[][] seatingPeople(char[][] layout) {
        if (isValidArray(layout)) {
            int rows = layout.length, cols = layout[0].length;
            char [][] lastLayout = getCopyLayout(everyoneSat(layout,rows,cols));
            do {
                layout = getCopyLayout(lastLayout);
                lastLayout = manageMatrix (lastLayout,rows,cols,'l');
                lastLayout = manageMatrix(lastLayout,rows,cols,'s');
            } while (!Arrays.deepEquals(lastLayout,layout));
        } return layout;
    }
}