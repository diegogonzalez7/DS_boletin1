package e2;

import java.util.Arrays;

public class SocialDistance {

    public static Boolean isValidArray(char[][] layout) {
        if (layout == null) throw new IllegalArgumentException();

        int ps_colum = layout[0].length; //Nº posible de columnas

        for (char[] chars : layout) { //Recorro matriz por filas
            if (chars.length != ps_colum) { //Si en algun momento cambian las columnas algo va mal
                throw new IllegalArgumentException("Invalid Layout :Nor square nor rectangular layout");
            }
            for (int j = 0; j < ps_colum; j++) { //Recorro la matriz elemento a elemento
                if (chars[j] != 'A' && chars[j] != '.' && chars[j] != '#') {    //Mira si hay char extraños
                    throw new IllegalArgumentException("Invalid Layout : Invalid character in the layout");
                }
            }
        }
        for (char[] chars : layout) {
            for (int j = 0; j < ps_colum; j++) {
                if (chars[j] == 'A') return true;
            }
        }
        return false;
    }

    public static char[][] everyoneSat(char[][] layout, int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (layout[i][j] == 'A') layout[i][j] = '#';
            }
        }
        return layout;
    }
    public static char[][] standUpPPl(char[][] layout, int rows, int cols) {
        int conteo, g, h;
        char[][] copyLayout = Arrays.stream(layout).map(char[]::clone).toArray(char[][]::new);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                //Levantar gente

                if (layout[i][j] == '#' && rows!=1 && cols!=1) {

                    //Levanta gente de la primera fila
                    if (i == 0 && (j != 0 && j != cols - 1)) {

                        conteo = 0;
                        h = 0; //Incremento filas
                        g = -1; //Incremento columnas

                        while (conteo < 4 && h < 2) {
                            if (i + h != i || j + g != j) {
                                if (layout[i + h][j + g] == '#') conteo++;
                            }
                            if (g == 1) {
                                g = -2;
                                h++;
                            }
                            g++;
                        }
                        if (conteo >= 4) copyLayout[i][j] = 'A';
                    }

                    //Levanta gente de la ultima fila
                    else if (i == rows - 1 && (j != 0 && j != cols - 1)) {
                        conteo = 0;
                        g = -1;
                        h = -1;
                        while (conteo < 4 && h < 1) {
                            if (i + h != i || j + g != j) {
                                if (layout[i + h][j + g] == '#') conteo++;
                            }
                            if (g == 1) {
                                g = -2;
                                h++;
                            }
                            g++;
                        }
                        if (conteo >= 4) copyLayout[i][j] = 'A';
                    }

                    //Levanta gente de la columna de la izda
                    else if (j == 0 && (i != 0 && i != rows - 1)) {
                        conteo = 0;
                        g = 0;
                        h = -1;
                        while (conteo < 4 && h < 2) {
                            if (i + h != i || j + g != j) {
                                if (layout[i + h][j + g] == '#') conteo++;
                            }
                            if (g == 1) {
                                g = -1;
                                h++;
                            }
                            g++;
                        }
                        if (conteo >=4) copyLayout[i][j] = 'A';
                    }

                    //Levanta gente de la columna de la dcha
                    else if (j == cols - 1 && (i != 0 && i != rows - 1)) {
                        conteo = 0;
                        g = -1;
                        h = -1;
                        while (conteo < 4 && h < 2) {
                            if (i + h != i || j + g != j) {
                                if (layout[i + h][j + g] == '#') conteo++;
                            }
                            if (g == 0) {
                                g = -2;
                                h++;
                            }
                            g++;
                        }
                        if (conteo >= 4) copyLayout[i][j] = 'A';
                    }

                    //Levantar gente de dentro
                    else if (i != 0 && i != rows - 1 && j != 0 && j != cols - 1) {
                        conteo = 0;
                        g = -1;
                        h = -1;
                        while (conteo < 4 && h < 2) {
                            if (i + h != i || j + g != j) {
                                if (layout[i + h][j + g] == '#') conteo++;
                            }
                            if (g == 1) {
                                g = -2;
                                h++;
                            }
                            g++;
                        }
                        if (conteo >= 4) copyLayout[i][j] = 'A';
                    }
                }
            }
        }
        return copyLayout;
    }

    public static char[][] finalArray(char[][] layout, int rows, int cols) {
        //4 casos especiales en los que solo necesitarán tener 5 sitios adyacentes libres (primera y última fila, primera y última columna)
        //Caso general en el que necesitarán tener 8 sitios libres adyacentes.
        int g, h;
        boolean trigger;
        char[][] copyLayout = getCopyLayout(layout);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(layout[i][j]=='A' && rows!=1 && cols!=1) {
                    if (i == 0 && (j != 0 && j != cols - 1)) {
                        trigger = true;
                        h = 0; //Incremento filas
                        g = -1; //Incremento columnas

                        while (trigger && h < 2) {
                            if (i + h != i || j + g != j) {
                                if (layout[i + h][j + g] == '#') trigger = false;
                            }
                            if (g == 1) {
                                g = -2;
                                h++;
                            }
                            g++;
                        }
                        if (trigger) copyLayout[i][j] = '#';
                    } else if (i == rows - 1 && (j != 0 && j != cols - 1)) {
                        trigger = true;
                        g = -1;
                        h = -1;
                        while (trigger && h < 1) {
                            if (i + h != i || j + g != j) {
                                if (layout[i + h][j + g] == '#') trigger = false;
                            }
                            if (g == 1) {
                                g = -2;
                                h++;
                            }
                            g++;
                        }
                        if (trigger) copyLayout[i][j] = '#';
                    } else if (j == 0 && (i != 0 && i != rows - 1)) {
                        trigger = true;
                        g = 0;
                        h = -1;
                        while (trigger && h < 2) {
                            if (i + h != i || j + g != j) {
                                if (layout[i + h][j + g] == '#') trigger = false;
                            }
                            if (g == 1) {
                                g = -1;
                                h++;
                            }
                            g++;
                        }
                        if (trigger) copyLayout[i][j] = '#';
                    } else if (j == cols - 1 && (i != 0 && i != rows - 1)) {
                        trigger = true;
                        g = -1;
                        h = -1;
                        while (trigger && h < 2) {
                            if (i + h != i || j + g != j) {
                                if (layout[i + h][j + g] == '#') trigger = false;
                            }
                            if (g == 0) {
                                g = -2;
                                h++;
                            }
                            g++;
                        }
                        if (trigger) copyLayout[i][j] = '#';
                    } else if ((i != 0 && i != rows - 1) && (j != 0 && j != cols - 1)) {
                        trigger = true;
                        g = -1;
                        h = -1;
                        while (trigger && h < 2) {
                            if (i + h != i || j + g != j) {
                                if (layout[i + h][j + g] == '#') trigger = false;
                            }
                            if (g == 1) {
                                g = -2;
                                h++;
                            }
                            g++;
                        }
                        if (trigger) copyLayout[i][j] = '#';
                    }
                }
            }
        }
        return copyLayout;
    }

    private static char[][] getCopyLayout(char[][] layout) {
        return Arrays.stream(layout).map(char[]::clone).toArray(char[][]::new);
    }

    //A: Sitio libre #:Alguien sentado .:Sitio no disponible

    public static char[][] seatingPeople(char[][] layout) {
        if (isValidArray(layout)) {
            int rows = layout.length, cols = layout[0].length;
            char [][] lastLayout;
            lastLayout= getCopyLayout(everyoneSat(layout,rows,cols));
            do {
                layout = getCopyLayout(lastLayout);
                lastLayout = standUpPPl(layout, rows, cols);
                lastLayout = finalArray(lastLayout,rows,cols);
            } while (!Arrays.deepEquals(lastLayout,layout));
        } return layout;
    }
}

/*
ALGORITMO PARA SENTARSE:
1º-Sentarse en la original.
2º-Copiar el resultado.
3º-Levantarse de la original.
4º-Comparar la copia y la original.
5º-Si son iguales para, si son distintas sigue.
*/
