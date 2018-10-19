package com.sharedpreferences;

//CharedPreferences é interessante caso você deseje gravar as preferências dos usuários. Exemplo: quando o seu app permite que cada usuário possa configurar a cor de
//fundo do aplicativo


import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText textoNome;
    private Button botaoSalvar;
    private TextView textoExibicao;

    private static final String ARQUIVO_PREFERENCIA = "ArquivoPreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textoNome = (EditText) findViewById(R.id.textoNomeId);
        textoExibicao = (TextView)  findViewById(R.id.textView2);
        botaoSalvar = (Button) findViewById(R.id.salvarId);

        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Recebe dois parâmetros String Name e segundo parâmetro é o módulo de leitura deste arquivo. 0 (zero) arquivo privado só pode ser lido pela aplicação
                SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
                //Permite editar o arquivo
                SharedPreferences.Editor editor = sharedPreferences.edit();

                if (textoNome.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Favor preencher o nome", Toast.LENGTH_LONG).show();
                } else {
                    editor.putString("nome",textoNome.getText().toString());
                    editor.commit();
                    textoExibicao.setText("Olá " + textoNome.getText().toString());
                }
            }
        });

        //Recupera o nome salvo
        SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
        //Criado o arquivo com a chave nome e agora recuperamos o nome digitado
        if (sharedPreferences.contains("nome")) {
            // Traz o nome gravado na chave nome e caso não tenha nada, traz o conteúdo do s1
            String nomeUsuario = sharedPreferences.getString("nome","usuário não definido");
            // Encontrando mostrará o nome do usuário
            textoExibicao.setText("Olá " + nomeUsuario);
        } else {
            textoExibicao.setText("Olá usuário não definido");

        }

    }
}
