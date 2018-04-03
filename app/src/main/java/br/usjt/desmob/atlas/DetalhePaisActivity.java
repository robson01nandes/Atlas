package br.usjt.desmob.atlas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * @author RA 81617543 Igor Almeida
 * DEVMOBI
 * CCP3AN-MCA
 */
public class DetalhePaisActivity extends Activity {

    /**
     * @author RA 81617543 Igor Almeida
     * apresentar detalhes do pais selecionado
     * @param savedInstanceState dados recebidos de outra activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_pais);
        TextView txtPais = findViewById(R.id.txtPais);
        Intent intent = getIntent();
        Pais pais = (Pais)intent.getSerializableExtra(ListaPaisesActivity.PAIS);
        txtPais.setText(pais.toString());
    }
}
