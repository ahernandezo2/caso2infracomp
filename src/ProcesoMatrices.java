import java.util.Random;

public class ProcesoMatrices {
    private int[][] mat1;
    private int[][] mat2;
    private int[][] mat3;
    private int nf1;
    private int nc1;
    private int nc2;
    private int tamPagina;

    public ProcesoMatrices(int nf1, int nc1, int nc2) {
        this.nf1 = nf1;
        this.nc1 = nc1;
        this.nc2 = nc2;
        mat1 = new int[nf1][nc1];
        mat2 = new int[nc1][nc2];
        mat3 = new int[nf1][nc2];
    }

    public void generarReferencias(int tamPagina) {
        this.tamPagina = tamPagina;
        Random rand = new Random();
        int numRows = nf1 * tamPagina / 4; 

        for (int i = 0; i < numRows; i++) {
            int row1 = rand.nextInt(nf1);
            int col1 = rand.nextInt(nc1);
            int row2 = rand.nextInt(nc1);
            int col2 = rand.nextInt(nc2);
            int value = mat1[row1][col1] * mat2[row2][col2]; 

            int pageIndex = (row1 * nc1 * 4) / (tamPagina * 4);
            System.out.println("Referencia a página " + pageIndex + " con valor " + value);
        }
    }

    public int calcularFallasDePagina(int numMarcos) {
        int numFallas = 0;
        int numPaginas = (nf1 * nc1 * 4) / (tamPagina * 4);

        int[] marcos = new int[numMarcos];
        int[] edades = new int[numMarcos];

        for (int i = 0; i < numMarcos; i++) {
            marcos[i] = -1; 
            edades[i] = 0;
        }

        Random rand = new Random();

        for (int i = 0; i < numPaginas; i++) {
            int pageIndex = i;
            boolean paginaEnMemoria = false;

            for (int j = 0; j < numMarcos; j++) {
                if (marcos[j] == pageIndex) {
                    paginaEnMemoria = true;
                    edades[j] = 0;
                    break;
                }
            }

            if (!paginaEnMemoria) {
                numFallas++;
                int reemplazar = 0;
                int maxEdad = edades[0];

                for (int j = 1; j < numMarcos; j++) {
                    if (edades[j] > maxEdad) {
                        maxEdad = edades[j];
                        reemplazar = j;
                    }
                }

                marcos[reemplazar] = pageIndex;
            }

            for (int j = 0; j < numMarcos; j++) {
                edades[j]++;
            }
        }

        System.out.println("Número de fallas de página: " + numFallas);
        return numFallas;
    }
}
