package com.example.printtextactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private EditText editSocketAdress;
    private EditText editPort;
    private Button printButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        printButton = findViewById(R.id.buttonPrint);
        printButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText = findViewById(R.id.editTextForPrinting);
                editSocketAdress = findViewById(R.id.editSocketAdreess);
                editPort = findViewById(R.id.editPort);
                new ConnectionWithPrinter(editText.getText().toString(), editSocketAdress.getText().toString(), Integer.parseInt(editPort.getText().toString())).execute();

            }
        });
    }

    private class ConnectionWithPrinter extends AsyncTask<Void, Void, Void> {


        private String editTextString;
        private String socket;
        private int port;

        public ConnectionWithPrinter(String editTextString, String socket, int port) {
            this.editTextString = editTextString;
            this.socket = socket;
            this.port = port;
        }

        @Override
        protected Void doInBackground(Void... voids) {


            try {

                Socket sock = new Socket(socket, port);
                PrintWriter oStream = new PrintWriter(sock.getOutputStream());

                oStream.print("\n");

                //select code page for Greek in this printer is 24 (CP737)
                oStream.write(27);
                oStream.write(116);
                oStream.write(24);

                for (int i = 0; i < editTextString.length(); i++) {

                    if (!checkLatinCharacterAndNumber(editTextString.charAt(i))) {

                        if (editTextString.charAt(i) == 'Ρ' || editTextString.charAt(i) == 'ρ') {
                            oStream.print('P');
                        } else {
                            oStream.write(27);
                            oStream.write(116);
                            oStream.print(convertGreekCharacter(editTextString.charAt(i)));
                        }
                    } else {
                        oStream.print(editTextString.charAt(i));
                    }
                }


                oStream.print("\n\n\n");
                oStream.write(27);
                oStream.write(109);

                oStream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;

        }
    }

    private char convertGreekCharacter(char androidChar) {

        switch (androidChar) {
            //Capital letters

            case 'Α':
                return 'π';

            case 'Β':
                return 'ρ';

            case 'Γ':
                return 'ς';

            case 'Δ':
                return 'σ';

            case 'Ε':
                return 'τ';

            case 'Ζ':
                return 'υ';

            case 'Η':
                return 'φ';

            case 'Θ':
                return 'χ';

            case 'Ι':
                return 'ψ';

            case 'Κ':
                return 'ω';

            case 'Λ':
                return 'ϊ';

            case 'Μ':
                return 'ϋ';

            case 'Ν':
                return 'Ό';

            case 'Ξ':
                return 'ύ';

            case 'Ο':
                return 'ώ';

            case 'Π':
                return 'Ώ';

            case 'Ρ':
                return '═';

            case 'Σ':
                return 'Α';

            case 'Τ':
                return 'Β';

            case 'Υ':
                return 'Γ';

            case 'Φ':
                return 'Δ';

            case 'Χ':
                return 'Ε';

            case 'Ψ':
                return 'Ζ';

            case 'Ω':
                return 'Η';

            //small letters
            case 'α':
                return 'π';

            case 'β':
                return 'ρ';

            case 'γ':
                return 'ς';

            case 'δ':
                return 'σ';

            case 'ε':
                return 'τ';

            case 'ζ':
                return 'υ';

            case 'η':
                return 'φ';

            case 'θ':
                return 'χ';

            case 'ι':
                return 'ψ';

            case 'κ':
                return 'ω';

            case 'λ':
                return 'ϊ';

            case 'μ':
                return 'ϋ';

            case 'ν':
                return 'Ό';

            case 'ξ':
                return 'ύ';

            case 'ο':
                return 'ώ';

            case 'π':
                return 'Ώ';

            case 'ρ':
                return '═';

            case 'σ':
                return 'Α';

            case 'τ':
                return 'Β';

            case 'υ':
                return 'Γ';

            case 'φ':
                return 'Δ';

            case 'χ':
                return 'Ε';

            case 'ψ':
                return 'Ζ';

            case 'ω':
                return 'Η';
            case 'ς':
                return 'Α';
            //vocals small letters

            case 'ά':
                return 'π';

            case 'έ':
                return 'τ';

            case 'ή':
                return 'φ';

            case 'ί':
                return 'ψ';

            case 'ύ':
                return 'Γ';

            case 'ώ':
                return 'Η';

            case 'ό':
                return 'ώ';

            //capital vocal letters

            case 'Ά':
                return 'π';

            case 'Έ':
                return 'τ';

            case 'Ή':
                return 'φ';

            case 'Ί':
                return 'ψ';

            case 'Ύ':
                return 'Γ';

            case 'Ώ':
                return 'Η';

            case 'Ό':
                return 'π';


        }
        return androidChar;
    }

    private boolean checkLatinCharacterAndNumber(char androidChar) {

        HashMap<Object, Character> latinCharacterHashMap = new HashMap<>();
        latinCharacterHashMap.put('A', 'A');
        latinCharacterHashMap.put('B', 'B');
        latinCharacterHashMap.put('C', 'C');
        latinCharacterHashMap.put('D', 'D');
        latinCharacterHashMap.put('E', 'E');
        latinCharacterHashMap.put('F', 'F');
        latinCharacterHashMap.put('G', 'G');
        latinCharacterHashMap.put('H', 'H');
        latinCharacterHashMap.put('I', 'I');
        latinCharacterHashMap.put('J', 'J');
        latinCharacterHashMap.put('K', 'K');
        latinCharacterHashMap.put('L', 'L');
        latinCharacterHashMap.put('M', 'M');
        latinCharacterHashMap.put('N', 'N');
        latinCharacterHashMap.put('O', 'O');
        latinCharacterHashMap.put('P', 'P');
        latinCharacterHashMap.put('Q', 'Q');
        latinCharacterHashMap.put('R', 'R');
        latinCharacterHashMap.put('S', 'S');
        latinCharacterHashMap.put('T', 'T');
        latinCharacterHashMap.put('U', 'U');
        latinCharacterHashMap.put('V', 'V');
        latinCharacterHashMap.put('W', 'W');
        latinCharacterHashMap.put('X', 'X');
        latinCharacterHashMap.put('Y', 'Y');
        latinCharacterHashMap.put('Z', 'Z');
        latinCharacterHashMap.put('a', 'a');
        latinCharacterHashMap.put('b', 'b');
        latinCharacterHashMap.put('c', 'c');
        latinCharacterHashMap.put('d', 'd');
        latinCharacterHashMap.put('e', 'e');
        latinCharacterHashMap.put('f', 'f');
        latinCharacterHashMap.put('g', 'g');
        latinCharacterHashMap.put('h', 'h');
        latinCharacterHashMap.put('i', 'i');
        latinCharacterHashMap.put('j', 'j');
        latinCharacterHashMap.put('k', 'k');
        latinCharacterHashMap.put('l', 'l');
        latinCharacterHashMap.put('m', 'm');
        latinCharacterHashMap.put('n', 'n');
        latinCharacterHashMap.put('o', 'o');
        latinCharacterHashMap.put('p', 'p');
        latinCharacterHashMap.put('q', 'q');
        latinCharacterHashMap.put('r', 'r');
        latinCharacterHashMap.put('s', 's');
        latinCharacterHashMap.put('t', 't');
        latinCharacterHashMap.put('u', 'u');
        latinCharacterHashMap.put('v', 'v');
        latinCharacterHashMap.put('w', 'w');
        latinCharacterHashMap.put('x', 'x');
        latinCharacterHashMap.put('y', 'y');
        latinCharacterHashMap.put('z', 'z');
        latinCharacterHashMap.put('1', '1');
        latinCharacterHashMap.put('2', '2');
        latinCharacterHashMap.put('3', '3');
        latinCharacterHashMap.put('4', '4');
        latinCharacterHashMap.put('5', '5');
        latinCharacterHashMap.put('6', '6');
        latinCharacterHashMap.put('8', '8');
        latinCharacterHashMap.put('9', '9');
        latinCharacterHashMap.put('0', '0');
        latinCharacterHashMap.put('=', '=');
        latinCharacterHashMap.put('+', '+');
        latinCharacterHashMap.put('-', '-');
        latinCharacterHashMap.put('/', '/');
        latinCharacterHashMap.put('*', '*');
        latinCharacterHashMap.put('?', '?');
        latinCharacterHashMap.put(' ', ' ');
        return latinCharacterHashMap.containsKey(androidChar);
    }
}