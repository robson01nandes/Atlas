package br.usjt.desmob.atlas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;

/**
 * @author RA 81617543 Igor Almeida
 * DEVMOBI
 * CCP3AN-MCA
 */
public class MainActivity extends Activity {
    Spinner spinnerContinente;
    //public static final String CHAVE = "br.usjt.desmob.atlas.txtContinente";
    public static final String PAISES = "br.usjt.desmob.atlas.paises";
    String continente = "all";
    public static final String URL = "https://restcountries.eu/rest/v2/";
    Pais[] paises;
    Intent intent;
    ProgressBar timer;

    /**
     * @author RA 81617543 Igor Almeida
     * apresentar detalhes do pais selecionado
     * @param savedInstanceState dados recebidos de outra activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinnerContinente = findViewById(R.id.spinnerContinente);
        spinnerContinente.setOnItemSelectedListener(new ContinenteSelecionado());
        timer = findViewById(R.id.timer);
        timer.setVisibility(View.INVISIBLE);
    }

    /**
     * @author RA 81617543 Igor Almeida
     * Método resposavel por chamar a
     * activity de listar paises pelo continente
     */
    public void listarPaises(View view){
        intent = new Intent(this, ListaPaisesActivity.class);

        if(PaisNetwork.isConnected(this)) {
            timer.setVisibility(View.VISIBLE);
            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            try {
                                paises = PaisNetwork.buscarPaises(URL, continente);
                                runOnUiThread(new Runnable() {
                                                  @Override
                                                  public void run() {
                                                      intent.putExtra(PAISES, paises);
                                                      startActivity(intent);
                                                      timer.setVisibility(View.INVISIBLE);
                                                  }
                                              }
                                );
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
        } else {
            Toast.makeText(this, "Rede inativa.", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * @author RA 81617543 Igor Almeida
     * DEVMOBI
     * CCP3AN-MCA
     */
    private class ContinenteSelecionado implements AdapterView.OnItemSelectedListener {
        /**
         * @author RA 81617543 Igor Almeida
         */
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            continente = (String) parent.getItemAtPosition(position);
            if (continente.equals("Todos")) {
                continente = "all";
            } else {
                continente = "region/"+continente.toLowerCase();
            }
        }
        /**
         * @author RA 81617543 Igor Almeida
         */
        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
}
