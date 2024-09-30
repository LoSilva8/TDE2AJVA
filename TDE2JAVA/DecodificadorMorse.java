import java.util.HashMap;
import java.util.Map;

public class DecodificadorMorse {

    private NoArvore raizMorse;
    private Map<Character, String> mapaMorse;

    public DecodificadorMorse() {
        construirArvoreMorse();
        construirMapaMorse();
    }

    private void construirArvoreMorse() {
        raizMorse = new NoArvore(
            '\0',
            construirSubArvoreE(),
            construirSubArvoreT()
        );
    }

    private NoArvore construirSubArvoreE() {
        return new NoArvore(
            'E',
            new NoArvore(
                'I',
                new NoArvore('S',
                    new NoArvore('H', null, null),
                    new NoArvore('V', null, null)
                ),
                new NoArvore('U',
                    new NoArvore('F', null, null),
                    new NoArvore('\0', null, null)
                )
            ),
            new NoArvore(
                'A',
                new NoArvore('R',
                    new NoArvore('L', null, null),
                    new NoArvore('\0', null, null)
                ),
                new NoArvore('W',
                    new NoArvore('P', null, null),
                    new NoArvore('J', null, null)
                )
            )
        );
    }

    private NoArvore construirSubArvoreT() {
        return new NoArvore(
            'T',
            new NoArvore(
                'N',
                new NoArvore('D',
                    new NoArvore('B', null, null),
                    new NoArvore('X', null, null)
                ),
                new NoArvore('K',
                    new NoArvore('C', null, null),
                    new NoArvore('Y', null, null)
                )
            ),
            new NoArvore(
                'M',
                new NoArvore('G',
                    new NoArvore('Z', null, null),
                    new NoArvore('\0', null, null)
                ),
                new NoArvore('O',
                    new NoArvore('\0', null, null),
                    new NoArvore('\0', null, null)  
                )
            )
        );
    }

    private void construirMapaMorse() {
        mapaMorse = new HashMap<>();
        mapaMorse.put('A', ".-");
        mapaMorse.put('B', "-...");
        mapaMorse.put('C', "-.-.");
        mapaMorse.put('D', "-..");
        mapaMorse.put('E', ".");
        mapaMorse.put('F', "..-.");
        mapaMorse.put('G', "--.");
        mapaMorse.put('H', "....");
        mapaMorse.put('I', "..");
        mapaMorse.put('J', ".---");
        mapaMorse.put('K', "-.-");
        mapaMorse.put('L', ".-..");
        mapaMorse.put('M', "--");
        mapaMorse.put('N', "-.");
        mapaMorse.put('O', "---");
        mapaMorse.put('P', ".--.");
        mapaMorse.put('Q', "--.-");
        mapaMorse.put('R', ".-.");
        mapaMorse.put('S', "...");
        mapaMorse.put('T', "-");
        mapaMorse.put('U', "..-");
        mapaMorse.put('V', "...-");
        mapaMorse.put('W', ".--");
        mapaMorse.put('X', "-..-");
        mapaMorse.put('Y', "-.--");
        mapaMorse.put('Z', "--..");
        mapaMorse.put(' ', "/");
    }

    private char morseParaCaractere(NoArvore raiz, String sequencia, int indice) {
        if (raiz == null) {
            return '\0';
        }

        if (indice == sequencia.length()) {
            return raiz.getCaractere();
        } else {
            char simbolo = sequencia.charAt(indice);
            if (simbolo == '.') {
                return morseParaCaractere(raiz.getFilhoEsquerdo(), sequencia, indice + 1);
            } else if (simbolo == '-') {
                return morseParaCaractere(raiz.getFilhoDireito(), sequencia, indice + 1);
            } else {
                return '\0';
            }
        }
    }

    public String decodificarMorse(String morse) {
        StringBuilder decodificado = new StringBuilder();
        String[] sequencias = morse.trim().split("\\s+");

        for (String seq : sequencias) {
            if (seq.equals("/")) {
                decodificado.append(' ');
            } else {
                char caractere = morseParaCaractere(raizMorse, seq, 0);
                if (caractere != '\0') {
                    decodificado.append(caractere);
                } else {
                    decodificado.append('?');
                }
            }
        }

        return decodificado.toString();
    }

    public String codificarMorse(String texto) {
        StringBuilder codigoMorse = new StringBuilder();

        for (int i = 0; i < texto.length(); i++) {
            char caractere = texto.charAt(i);
            if (mapaMorse.containsKey(caractere)) {
                codigoMorse.append(mapaMorse.get(caractere)).append(" ");
            } else {
                codigoMorse.append("? ");
            }
        }

        return codigoMorse.toString().trim();
    }
}
