public class Primos {
    public Primos() {
    }

    public String verificarPrimo(int n) {
        String result = "Es primo";
        for (int i = 2; i < n; i++) {
            int r = n % i;
            if (r == 0) {
                result = "No es primo";
            }
        }
        return result;
    }

    public int cuentaPrimos(int vrInicial, int vrFinal) {
        int contador = 0;

        for (int i = vrInicial; i <= vrFinal; i++) {
            if (verificarPrimo(i) == "Es primo") {
                contador++;
            }
        }

        return contador;
    }

    public int primosEncontradosE(int vrInicial, int vrFinal) {

        return 5;
    }
}
